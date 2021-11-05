package com.example.qresent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "STUDENT")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "PRENUME", nullable = false)
	@Size(min = 3, max = 20)
	private String firstName;

	@Column(name = "NUME", nullable = false)
	@Size(min = 3, max = 20)
	private String lastName;

	@Column(name = "GRUPA", unique = true, nullable = false)
	@Size(min = 3, max = 20)
	private String group;

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getGroup() {
		return group;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Student)) {
			return false;
		}
		Student student = (Student) o;
		return id.equals(student.id) && firstName.equals(student.firstName) && lastName.equals(student.lastName) && group.equals(student.group);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, lastName, group);
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", group='" + group + '\'' +
				'}';
	}
}
