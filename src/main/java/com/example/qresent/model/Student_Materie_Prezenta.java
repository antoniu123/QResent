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
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "STUDENT_MATERIE_PREZENTA")
public class Student_Materie_Prezenta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STUDENT_MATERIE_ID")
	private Materie materie;

	@Column(name = "DATA_CURS", nullable = false)
	@Size(min = 3, max = 30)
	private String data_curs;

	@Column(name = "IDENTIFICATOR", nullable = false)
	private UUID uuid;

	@Column(name = "VALABILITATE", nullable = false)
	@Size(min = 3, max = 30)
	private String valabilitate;

	@Column(name = "PREZENTA", nullable = false)
	private Integer prezenta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Materie getMaterie() {
		return materie;
	}

	public void setMaterie(Materie materie) {
		this.materie = materie;
	}

	public String getData_curs() {
		return data_curs;
	}

	public void setData_curs(String data_curs) {
		this.data_curs = data_curs;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getValabilitate() {
		return valabilitate;
	}

	public void setValabilitate(String valabilitate) {
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
		if (!(o instanceof Student_Materie_Prezenta)) {
			return false;
		}
		Student_Materie_Prezenta that = (Student_Materie_Prezenta) o;
		return id.equals(that.id) && materie.equals(that.materie) && data_curs.equals(that.data_curs) && uuid.equals(that.uuid) && valabilitate.equals(that.valabilitate) && prezenta.equals(that.prezenta);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, materie, data_curs, uuid, valabilitate, prezenta);
	}

	@Override
	public String toString() {
		return "Student_Materie_Prezenta{" +
				"id=" + id +
				", materie=" + materie +
				", data_curs='" + data_curs + '\'' +
				", uuid=" + uuid +
				", valabilitate='" + valabilitate + '\'' +
				", prezenta=" + prezenta +
				'}';
	}
}
