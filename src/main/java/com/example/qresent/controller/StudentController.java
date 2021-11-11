package com.example.qresent.controller;

import com.example.qresent.model.Student;
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
import java.util.Objects;

@Controller
public class StudentController {


	StudentService studentService;

	ApplicationUserService usersService;

	@Autowired
	public StudentController(StudentService studentService, ApplicationUserService usersService) {
		this.studentService = studentService;
		this.usersService = usersService;
	}

	@GetMapping(value = "/students")
	public ModelAndView students() {
		String all = new String("all");
		Iterable<Student> studentList = studentService.listAllStudents();
		ModelAndView modelAndView = new ModelAndView("students");
		modelAndView.addObject("students", studentList);
		modelAndView.addObject("all", all);
		return modelAndView;
	}

	@GetMapping(value = "/student/add")
	public ModelAndView addStudent() {
		Student student = new Student();
		ModelAndView modelAndView = new ModelAndView("studentForm");
		modelAndView.addObject("student", student);
		return modelAndView;
	}

	@PostMapping(value = "/student")
	public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {
		ModelAndView modelAndView = new ModelAndView("studentForm");
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("errors", bindingResult.getAllErrors().toString());
			return "studentForm";
		}

		studentService.saveStudent(student);
		return "redirect:/students";
	}

	@GetMapping(value = "/student/{id}")
	public String editStudent(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.findStudentById(id));
		return "studentForm";
	}

	@GetMapping(value = "/students/{id}")
	public ModelAndView viewStudent(@PathVariable Long id, Model model) {
		String my = new String("my");
		ModelAndView modelAndView = new ModelAndView("students");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Student myStudent = usersService.findByName(user.getUsername()).getStudent();
		Long studentId = null;
		if (Objects.nonNull(myStudent)) {
			studentId = myStudent.getId();
		}
		modelAndView.addObject("students", studentService.findStudentById(id).orElseGet(Student::new));
		modelAndView.addObject("my", my);
		modelAndView.addObject("studentId", studentId);
		return modelAndView;
	}

	@GetMapping(value = "/student/delete/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return "redirect:/students";
	}
}
