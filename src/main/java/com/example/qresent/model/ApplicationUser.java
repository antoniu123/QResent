package com.example.qresent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "USER")
public class ApplicationUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "USERNAME", unique = true, nullable = false)
	@Size(min = 3, max = 30)
	private String username;

	@Column(name = "PASSWORD", unique = true, nullable = false)
	@Size(min = 8, max = 60)
	private String password;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STUDENT_ID")
	private Student student;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TIP_USER_ID")
	private TipUser tip_user;

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Student getStudent() {
		return student;
	}

	public TipUser getTip_user() {
		return tip_user;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setTip_user(TipUser tip_user) {
		this.tip_user = tip_user;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ApplicationUser)) {
			return false;
		}
		ApplicationUser that = (ApplicationUser) o;
		return id.equals(that.id) && username.equals(that.username) && password.equals(that.password) && student.equals(that.student) && tip_user.equals(that.tip_user);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, password, student, tip_user);
	}

	@Override
	public String toString() {
		return "ApplicationUser{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", student=" + student +
				", tip_user=" + tip_user +
				'}';
	}
}
