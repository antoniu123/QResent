package com.example.qresent.repository;

import com.example.qresent.model.ApplicationUser;
import com.example.qresent.model.Orar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrarRepository extends JpaRepository <Orar,Long> {
}
