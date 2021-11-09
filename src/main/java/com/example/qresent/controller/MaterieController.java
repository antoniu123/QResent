package com.example.qresent.controller;

import com.example.qresent.model.Materie;
import com.example.qresent.model.Student;
import com.example.qresent.service.MaterieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class MaterieController {

    @Autowired
    MaterieService materieService;

    @GetMapping(value = "/materii")
    public ModelAndView materii() {
        String all = new String("all");
        Iterable<Materie> materieList = materieService.listMaterii();
        ModelAndView modelAndView = new ModelAndView("materii");
        modelAndView.addObject("materii", materieList);
        modelAndView.addObject("all", all);
        return modelAndView;
    }

    @GetMapping(value = "/materii/add")
    public ModelAndView addMaterie() {
        Materie materie = new Materie();
        ModelAndView modelAndView = new ModelAndView("materieForm");
        modelAndView.addObject("materie", materie);
        return modelAndView;
    }

    @PostMapping(value = "/materie")
    public String saveStudent(@Valid @ModelAttribute("materie") Materie materie, BindingResult bindingResult, Model model) {
        ModelAndView modelAndView = new ModelAndView("materieForm");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("errors", bindingResult.getAllErrors().toString());
            return "materieForm";
        }

        materieService.saveMaterie(materie);

        return "redirect:/materii";
    }

    @GetMapping(value = "/materie/{id}")
    public String editMaterie(@PathVariable Long id, Model model) {
        model.addAttribute("materie", materieService.findById(id));
        return "materieForm";
    }

    @GetMapping(value = "/materie/delete/{id}")
    public String deleteMaterie(@PathVariable Long id) {
        materieService.deleteMaterie(id);
        return "redirect:/materii";
    }

}
