package com.example.qresent.controller;

import com.example.qresent.model.Materie;
import com.example.qresent.model.Student;
import com.example.qresent.model.StudentMaterie;
import com.example.qresent.service.ApplicationUserService;
import com.example.qresent.service.MaterieService;
import com.example.qresent.service.StudentMaterieService;
import com.example.qresent.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
public class StudentMaterieController {

	private final StudentService studentService;

	private final MaterieService materieService;

	private final StudentMaterieService studentMaterieService;

	private final ApplicationUserService applicationUserService;

	@Autowired
	public StudentMaterieController(final StudentService studentService,
									final MaterieService materieService,
									final StudentMaterieService studentMaterieService,
									final ApplicationUserService applicationUserService) {
		this.studentService = studentService;
		this.materieService = materieService;
		this.studentMaterieService = studentMaterieService;
		this.applicationUserService = applicationUserService;
	}

	@GetMapping("/studentmaterii")
	public ModelAndView getAllStudentMateries() {
		String all = "all";
		ModelAndView modelAndView = new ModelAndView("studentMaterieList");
		List<StudentMaterie> studentMaterieList = studentMaterieService.findAll();
		modelAndView.addObject("studentMaterieList", studentMaterieList);
		modelAndView.addObject(all);
		return modelAndView;
	}

	@GetMapping("/studentmaterii/{studentId}")
	public ModelAndView getMateriesByStudent(@PathVariable Long studentId) {
		ModelAndView modelAndView = new ModelAndView("studentMaterieForm");
		List<Materie> materieList = studentMaterieService.findAllMaterieByStudent(studentId);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Student myStudent = applicationUserService.findByName(user.getUsername()).getStudent();
		Long id = null;
		if (Objects.nonNull(myStudent)) {
			id = myStudent.getId();
		}
		modelAndView.addObject("materii", materieList);
		modelAndView.addObject("studentId", id);
		return modelAndView;
	}

	@GetMapping("/studentmaterie/add")
	public ModelAndView addStudentMaterie() {
		StudentMaterie studentMaterie = new StudentMaterie();
		List<Student> studentList = studentService.listAllStudents();
		List<Materie> materieList = materieService.listMaterii();
		ModelAndView modelAndView = new ModelAndView("studentMaterieForm");
		modelAndView.addObject("studentList", studentList);
		modelAndView.addObject("materieList", materieList);
		modelAndView.addObject("studentMaterie", studentMaterie);
		return modelAndView;
	}

	@GetMapping("/studentmaterie/{studentmaterieId}")
	public ModelAndView editStudentMaterie(@PathVariable Long studentmaterieId, Model model) {
		StudentMaterie studentMaterie = studentMaterieService.findById(studentmaterieId).orElseGet(StudentMaterie::new);
		ModelAndView modelAndView = new ModelAndView("studentMaterieForm");
		modelAndView.addObject("studentId", studentMaterie.getStudent().getId());
		modelAndView.addObject("materieId", studentMaterie.getMaterie().getId());
		modelAndView.addObject("studentMaterie", studentMaterie);
		return modelAndView;
	}

	@PostMapping("/studentmaterie")
	public String saveStudentMaterie(@ModelAttribute("studentMaterie") StudentMaterie studentMaterie, Model model) {
		studentMaterieService.save(studentMaterie);
		return "redirect:/studentmaterii";
	}

//	@GetMapping("/studentmaterie/{materieId}")
//	public ModelAndView editStudentMaterie(@PathVariable Long materieId, Model model) {
//		Materie materie = materieService.findMaterieById(materieId).orElseGet(Materie::new);
//		ModelAndView modelAndView = new ModelAndView("studentMaterieForm");
//		modelAndView.addObject("grade", materie);
//		return modelAndView;
//	}

	@GetMapping("/studentmaterie/delete/{studentMaterieId}")
	public String deleteStudentMaterie(@PathVariable Long studentMaterieId) {
		studentMaterieService.delete(studentMaterieId);
		return "redirect:/studentmaterii";
	}

}
