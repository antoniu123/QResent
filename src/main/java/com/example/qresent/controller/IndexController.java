package com.example.qresent.controller;

import com.example.qresent.model.Student;
import com.example.qresent.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
public class IndexController {

	ApplicationUserService usersService;

	@Autowired
	public IndexController(ApplicationUserService usersService) {
		this.usersService = usersService;
	}

	@GetMapping("/")
	public ModelAndView indexpage() {
		ModelAndView mav = new ModelAndView();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Student myStudent = usersService.findByName(user.getUsername()).getStudent();
		Long id = null;
		if (Objects.nonNull(myStudent)) {
			id = myStudent.getId();
		}
		mav.setViewName("index");
		mav.addObject("studentId", id);
		return mav;
	}

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("custom-login");
		return mav;
	}

	@GetMapping("/error")
	public ModelAndView error() {
		ModelAndView mav = new ModelAndView();
		String errorMessage = "You are not authorized for the requested data.";
		mav.addObject("errorMsg", errorMessage);
		mav.setViewName("403");
		return mav;
	}
}
