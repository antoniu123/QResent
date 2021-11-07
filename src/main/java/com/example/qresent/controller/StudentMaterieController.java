package com.example.qresent.controller;

import com.example.qresent.model.Student;
import com.example.qresent.model.Student_Materie;
import com.example.qresent.repository.MaterieRepository;
import com.example.qresent.service.ApplicationUserService;
import com.example.qresent.service.StudentMaterieService;
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
import java.util.Optional;

@Controller
public class StudentMaterieController {
	private StudentMaterieService studentMaterieService;

	private MaterieRepository materieRepository;

	private ApplicationUserService applicationUserService;

	@Autowired
	public StudentMaterieController(StudentMaterieService studentMaterieService, MaterieRepository materieRepository,
									ApplicationUserService applicationUserService) {
		this.studentMaterieService = studentMaterieService;
		this.materieRepository = materieRepository;
		this.applicationUserService = applicationUserService;
	}

	@GetMapping("/student_materie/all")
	public ModelAndView getAllGrades() {
		String all = new String("all");
		ModelAndView modelAndView = new ModelAndView("Student_Materie_List");
		List<Student_Materie> student_materieList = studentMaterieService.findAll();
		modelAndView.addObject("student_materie", student_materieList);
		modelAndView.addObject(all);
		return modelAndView;
	}

	@GetMapping("/student/{studentId}/studentmaterie")
	public ModelAndView getGradesByStudent(@PathVariable Long studentId) {
		ModelAndView modelAndView = new ModelAndView("Student_Materie_List");
		List<Student_Materie> student_materieList = studentMaterieService.findByStudentId(studentId);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Student myStudent = applicationUserService.findByName(user.getUsername()).getStudent();
		Long id = null;
		if (Objects.nonNull(myStudent)) {
			id = myStudent.getId();
		}
		modelAndView.addObject("student_materie", student_materieList);
		modelAndView.addObject("studentId", id);
		return modelAndView;
	}

	@GetMapping("/studentmaterie/add")
	public ModelAndView addGrade() {
		Student_Materie grade = new Student_Materie();
		List<Student_Materie> student_materieList = studentMaterieService.findAll();
		ModelAndView modelAndView = new ModelAndView("Student_Materie_Form");
		modelAndView.addObject("student_materieList", student_materieList);
		return modelAndView;
	}

	@PostMapping("/studentmaterie")
	public String saveGrade(@ModelAttribute("grade") Student_Materie student_materie, Model model) {
		studentMaterieService.save(student_materie);
		return "redirect:/grades/all";
	}

	@GetMapping("/studentmaterie/{studentMaterieId}")
	public ModelAndView editStudentMaterie(@PathVariable Long studentMaterieId, Model model) {
		Student_Materie student_materie = studentMaterieService.findById(studentMaterieId).orElseGet(Student_Materie::new);
		ModelAndView modelAndView = new ModelAndView("gradesForm");
		modelAndView.addObject("studentId", student_materie.getStudent().getId());
		modelAndView.addObject("subjectId", student_materie.getMaterie().getId());
		modelAndView.addObject("grade", student_materie);
		return modelAndView;
	}

	@GetMapping("/studentmaterie/delete/{studentMaterieId}")
	public String deleteGrade(@PathVariable Long studentMaterieId) {
		studentMaterieService.delete(studentMaterieId);
		return "redirect:/studentmaterie/all";
	}

}
