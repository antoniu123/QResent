package com.example.qresent.controller;

import com.example.qresent.model.Orar;
import com.example.qresent.model.StudentMateriePrezenta;
import com.example.qresent.repository.OrarRepository;
import com.example.qresent.service.StudentMateriePrezentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrarController {


	private final OrarRepository orarRepository;

	private final StudentMateriePrezentaService studentMateriePrezentaService;

	@Autowired
	public OrarController(OrarRepository orarRepository,
						  StudentMateriePrezentaService studentMateriePrezentaService) {
		this.orarRepository = orarRepository;
		this.studentMateriePrezentaService = studentMateriePrezentaService;
	}

	@GetMapping(value = "/orar/all")
	public ModelAndView allOrars() {
		String all = "all";
		List<Orar> orarList = orarRepository.findAll()
				.stream()
				.filter(orar->LocalDateTime.now().isAfter(orar.getZi()) &&
						LocalDateTime.now().isBefore(orar.getZi().plus(120L, ChronoUnit.MINUTES)))
				.collect(Collectors.toList());
		ModelAndView modelAndView = new ModelAndView("orars");
		modelAndView.addObject("orarList", orarList);
		modelAndView.addObject("all", all);
		return modelAndView;
	}

	@GetMapping(value = "/orar/prezente")
	public ModelAndView allOrarPrezente() {
		String all = "all";
		List<StudentMateriePrezenta> studentMateriePrezentaList = studentMateriePrezentaService.findAll();
		ModelAndView modelAndView = new ModelAndView("allMateriePrezenta");
		modelAndView.addObject("studentMateriePrezentaList", studentMateriePrezentaList);
		modelAndView.addObject("all", all);
		return modelAndView;
	}
}
