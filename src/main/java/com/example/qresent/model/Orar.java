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
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "ORAR")
public class Orar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MATERIE_ID")
	private Materie materie;

	@Column(name = "DATA_CURS_ORA", nullable = false)
	@Size(min = 3, max = 50)
	private LocalDate ora;

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

	public LocalDate getOra() {
		return ora;
	}

	public void setOra(LocalDate ora) {
		this.ora = ora;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Orar)) {
			return false;
		}
		Orar orar = (Orar) o;
		return id.equals(orar.id) && materie.equals(orar.materie) && ora.equals(orar.ora);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, materie, ora);
	}

	@Override
	public String toString() {
		return "Orar{" +
				"id=" + id +
				", materie=" + materie +
				", ora='" + ora + '\'' +
				'}';
	}
}
