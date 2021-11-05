package com.example.qresent.repository;

import com.example.qresent.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
	ApplicationUser findByUsername(String username);
}
