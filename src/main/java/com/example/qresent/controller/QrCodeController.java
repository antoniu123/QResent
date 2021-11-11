package com.example.qresent.controller;

import com.example.qresent.model.QrCodeProcessingResult;
import com.example.qresent.model.QrCodeUrl;
import com.example.qresent.model.Student;
import com.example.qresent.model.StudentMateriePrezenta;
import com.example.qresent.service.QrCodeEncoder;
import com.example.qresent.service.StudentMateriePrezentaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
public class QrCodeController {

    private final static String PAGE_INDEX = "index";
    private final static String PAGE_RESULT = "result";
    private final static String PAGE_QR_CODE_URL = "qr-code-url";
    private final static String QR_CODE = "image";
    private final static String TEXT_TO_BE_ENCODED = "text";
    private final static String SUCCESS_MESSAGE = "successMessage";
    private final static String ERROR_MESSAGE = "errorMessage";

    private final QrCodeEncoder qrCodeEncoder;

    private final StudentMateriePrezentaService studentMateriePrezentaService;

    public QrCodeController(QrCodeEncoder qrCodeEncoder, StudentMateriePrezentaService studentMateriePrezentaService) {
        this.qrCodeEncoder = qrCodeEncoder;
        this.studentMateriePrezentaService = studentMateriePrezentaService;
    }

    @GetMapping("/prezente/generare/{materieId}")
    public void genereazaPrezenteGoaleMaterieZi(@PathVariable Long materieId){
        studentMateriePrezentaService.generarePrezenta(materieId, LocalDate.now());
    }

    @GetMapping("/prezente/all/{materieId}")
    public ModelAndView afiseazaPrezenteMaterie(@PathVariable Long materieId){
        List<StudentMateriePrezenta> studentMateriePrezentaList = studentMateriePrezentaService.findAllByMaterieId(materieId);
        ModelAndView modelAndView = new ModelAndView("studentMateriePrezente");
        modelAndView.addObject("studentMateriePrezentaList", studentMateriePrezentaList);
        return modelAndView;
    }

    @GetMapping("/prezente/{studentId}")
    public ModelAndView afiseazaPrezentePosibileStudent(@PathVariable Long studentId){
        List<StudentMateriePrezenta> studentMateriePrezentaList = studentMateriePrezentaService.findAllActiveByStudentId(studentId);
        ModelAndView modelAndView = new ModelAndView("studentMateriePrezenteStudent");
        modelAndView.addObject("studentMateriePrezentaList", studentMateriePrezentaList);
        return modelAndView;
    }


    @GetMapping("prezenta/{uuid}")
    public String showUrl(Model model, @Valid @PathVariable UUID uuid) {
        addCommonModelAttributes(model);
        QrCodeUrl qrCodeUrl = new QrCodeUrl(String.format("http://%s/prezenta/set/%s", "192.168.1.198", uuid));
        log.info("generate QR Code for Url {}", qrCodeUrl.getUrlToBeEncoded());
        QrCodeProcessingResult result = this.qrCodeEncoder.generateQrCodeUrl(qrCodeUrl);
        this.addResultModelAttributes(model, result);
        return PAGE_RESULT;
    }

    @GetMapping("prezenta/set/{uuid}")
    public void processUrl(UUID uuid) {
        studentMateriePrezentaService.setPrezenta(uuid);
    }

    private void addCommonModelAttributes(@NotNull Model model) {
        model.addAttribute("titleMessage", "test");
        model.addAttribute("appInfo", "qr code");
    }

    private void addResultModelAttributes(@NotNull Model model, @NotNull QrCodeProcessingResult result) {
        model.addAttribute(QR_CODE, result.getImage());
        model.addAttribute(TEXT_TO_BE_ENCODED, result.getEncodedText());
        if (result.isSuccessfull()) {
            model.addAttribute(SUCCESS_MESSAGE, result.getSuccessMessage());
        } else {
            model.addAttribute(ERROR_MESSAGE, result.getErrorMessage());
        }
    }
}