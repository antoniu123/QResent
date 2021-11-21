package com.example.qresent.controller;

import com.example.qresent.model.Orar;
import com.example.qresent.model.Student;
import com.example.qresent.model.StudentMateriePrezenta;
import com.example.qresent.repository.OrarRepository;
import com.example.qresent.service.ApplicationUserService;
import com.example.qresent.service.StudentMateriePrezentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Controller
public class OrarController {


	private final OrarRepository orarRepository;

	private ApplicationUserService usersService;

	private final StudentMateriePrezentaService studentMateriePrezentaService;

	@Autowired
	public OrarController(OrarRepository orarRepository,
						  StudentMateriePrezentaService studentMateriePrezentaService,
						  ApplicationUserService usersService) {
		this.orarRepository = orarRepository;
		this.studentMateriePrezentaService = studentMateriePrezentaService;
		this.usersService = usersService;
	}

	@GetMapping(value = "/orar/all")
	public ModelAndView allOrars() {
		String all = "all";
		List<Orar> orarList = orarRepository.findAll()
				.stream()
				.filter(orar -> LocalDateTime.now().isAfter(orar.getZi()) &&
						LocalDateTime.now().isBefore(orar.getZi().plus(120L, ChronoUnit.MINUTES)))
				.collect(toList());
		ModelAndView modelAndView = new ModelAndView("orars");
		modelAndView.addObject("orarList", orarList);
		modelAndView.addObject("all", all);
		return modelAndView;
	}

	@GetMapping(value = "/orar/prezente")
	public ModelAndView allOrarPrezente() {
		String all = "all";
		List<StudentMateriePrezenta> studentMateriePrezentaList = studentMateriePrezentaService.findAll()
				.stream()
				.sorted((a, b) -> (int) (b.getDataCurs().toEpochSecond(ZoneOffset.UTC) - a.getDataCurs().toEpochSecond(ZoneOffset.UTC)))
				.collect(toList());
		ModelAndView modelAndView = new ModelAndView("allMateriePrezenta");
		modelAndView.addObject("studentMateriePrezentaList", studentMateriePrezentaList);
		modelAndView.addObject("all", all);
		return modelAndView;
	}

	@GetMapping(value = "/orar/myplot")
	public String index(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Student myStudent = usersService.findByName(user.getUsername()).getStudent();
		Long studentId = null;
		if (Objects.nonNull(myStudent)) {
			studentId = myStudent.getId();
		}
		model.addAttribute("chartAllData", getAllChartData());
		model.addAttribute("chartAllConfirmedData", getAllConfirmedChartData());
		return "prezentaChart";
	}

	private List<List<Object>> getAllChartData() {
		List<List<Object>> myFirst =
				studentMateriePrezentaService.findAll().stream()
						.map(x -> x.getStudentMaterie().getStudent().getLastName()+" "+ x.getStudentMaterie().getStudent().getFirstName())
						.collect(groupingBy(Function.identity(), counting()))
						.entrySet().stream()
						.map(e -> {
							List<Object> l = new ArrayList<>();
							l.add(e.getKey());
							l.add(Math.toIntExact(e.getValue()));
							return l;
						})
						.collect(toList());

		return myFirst;
	}

	private List<List<Object>> getAllConfirmedChartData() {
		List<List<Object>> mySecond =
				studentMateriePrezentaService.findAll().stream()
						.filter(smp -> smp.getPrezenta().equals(1))
						.map(x -> x.getStudentMaterie().getStudent().getLastName()+" "+ x.getStudentMaterie().getStudent().getFirstName())
						.collect(groupingBy(Function.identity(), counting()))
						.entrySet().stream()
						.map(e -> {
							List<Object> l = new ArrayList<>();
							l.add(e.getKey());
							l.add(Math.toIntExact(e.getValue()));
							return l;
						})
						.collect(toList());

		return mySecond;
	}
}
