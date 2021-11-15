package com.example.qresent.controller;

import com.example.qresent.model.Orar;
import com.example.qresent.model.Student;
import com.example.qresent.repository.OrarRepository;
import com.example.qresent.service.ApplicationUserService;
import com.example.qresent.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
public class OrarController {


	private final OrarRepository orarRepository;

	@Autowired
	public OrarController(OrarRepository orarRepository) {
		this.orarRepository = orarRepository;
	}

	@GetMapping(value = "/orar/all")
	public ModelAndView allOrars() {
		String all = "all";
		List<Orar> orarList = orarRepository.findAll();
		ModelAndView modelAndView = new ModelAndView("orars");
		modelAndView.addObject("orarList", orarList);
		modelAndView.addObject("all", all);
		return modelAndView;
	}
}
