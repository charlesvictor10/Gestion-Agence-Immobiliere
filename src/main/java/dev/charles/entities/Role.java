package dev.charles.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Role implements Serializable {
	@Id
	private String role;
	private String description;
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(String role, String description) {
		super();
		this.role = role;
		this.description = description;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
