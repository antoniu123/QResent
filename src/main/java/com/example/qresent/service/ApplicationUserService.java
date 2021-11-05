package com.example.qresent.service;

import com.example.qresent.model.ApplicationUser;
import com.example.qresent.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService {
	private final ApplicationUserRepository applicationUserRepository;

	@Autowired
	public ApplicationUserService(ApplicationUserRepository applicationUserRepository) {
		this.applicationUserRepository = applicationUserRepository;
	}

	public ApplicationUser findByName(String username) {
		return applicationUserRepository.findByUsername(username);
	}
}
