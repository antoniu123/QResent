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
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "STUDENT_MATERIE_PREZENTA")
public class StudentMateriePrezenta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STUDENT_MATERIE_ID")
	private StudentMaterie studentMaterie;

	@Column(name = "DATA_CURS", nullable = false)
	private LocalDateTime dataCurs;

	@Column(name = "IDENTIFICATOR", nullable = false)
	private UUID uuid;

	@Column(name = "VALABILITATE", nullable = false)
	private LocalDateTime valabilitate;

	@Column(name = "PREZENTA", nullable = false)
	private Integer prezenta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StudentMaterie getStudentMaterie() {
		return studentMaterie;
	}

	public void setStudentMaterie(StudentMaterie studentMaterie) {
		this.studentMaterie = studentMaterie;
	}

	public LocalDateTime getDataCurs() {
		return dataCurs;
	}

	public void setDataCurs(LocalDateTime data_curs) {
		this.dataCurs = data_curs;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public LocalDateTime getValabilitate() {
		return valabilitate;
	}

	public void setValabilitate(LocalDateTime valabilitate) {
		this.valabilitate = valabilitate;
	}

	public Integer getPrezenta() {
		return prezenta;
	}

	public void setPrezenta(Integer prezenta) {
		this.prezenta = prezenta;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof StudentMateriePrezenta)) {
			return false;
		}
		StudentMateriePrezenta that = (StudentMateriePrezenta) o;
		return id.equals(that.id) && studentMaterie.equals(that.studentMaterie) && dataCurs.equals(that.dataCurs)
				&& uuid.equals(that.uuid) && valabilitate.equals(that.valabilitate) && prezenta.equals(that.prezenta);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, studentMaterie, dataCurs, uuid, valabilitate, prezenta);
	}

	@Override
	public String toString() {
		return "Student_Materie_Prezenta{" +
				"id=" + id +
				", materie=" + studentMaterie +
				", data_curs='" + dataCurs + '\'' +
				", uuid=" + uuid +
				", valabilitate='" + valabilitate + '\'' +
				", prezenta=" + prezenta +
				'}';
	}
}
