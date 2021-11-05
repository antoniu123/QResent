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
@Table(name = "MATERIE")
public class Materie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "INFO", nullable = false)
	@Size(min = 3, max = 100)
	private String info;

	@Column(name = "CERINTE", nullable = false)
	@Size(min = 3, max = 100)
	private String cerinte;

	@Column(name = "BONUS", unique = true, nullable = false)
	@Size(min = 3, max = 100)
	private String bonus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getCerinte() {
		return cerinte;
	}

	public void setCerinte(String cerinte) {
		this.cerinte = cerinte;
	}

	public String getBonus() {
		return bonus;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Materie)) {
			return false;
		}
		Materie materie = (Materie) o;
		return id.equals(materie.id) && info.equals(materie.info) && cerinte.equals(materie.cerinte) && bonus.equals(materie.bonus);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, info, cerinte, bonus);
	}

	@Override
	public String toString() {
		return "Materie{" +
				"id=" + id +
				", info='" + info + '\'' +
				", cerinte='" + cerinte + '\'' +
				", bonus='" + bonus + '\'' +
				'}';
	}
}
