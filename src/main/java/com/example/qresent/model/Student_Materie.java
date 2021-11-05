package com.example.qresent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "STUDENT_MATERIE")
public class Student_Materie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STUDENT_ID")
	private Student student;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MATERIE_ID")
	private Materie materie;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Materie getMaterie() {
		return materie;
	}

	public void setMaterie(Materie materie) {
		this.materie = materie;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Student_Materie)) {
			return false;
		}
		Student_Materie that = (Student_Materie) o;
		return id.equals(that.id) && student.equals(that.student) && materie.equals(that.materie);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, student, materie);
	}

	@Override
	public String toString() {
		return "Student_Materie{" +
				"id=" + id +
				", student=" + student +
				", materie=" + materie +
				'}';
	}
}
