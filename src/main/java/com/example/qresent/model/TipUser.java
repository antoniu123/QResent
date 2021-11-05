package com.example.qresent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "TIP_USER")
public class TipUser {
	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME", unique = true, nullable = false)
	@Size(min = 1, max = 20)
	private String name;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof TipUser)) {
			return false;
		}
		TipUser tipUser = (TipUser) o;
		return id.equals(tipUser.id) && name.equals(tipUser.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return "TipUser{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
