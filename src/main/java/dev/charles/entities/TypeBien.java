package dev.charles.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class TypeBien implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idTypeBien;
	@NotEmpty
	@Size(min=5, max=250)
	private String designation;
	@OneToMany(mappedBy="typeBien")
	private Collection<Logement> logements;
	
	public TypeBien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TypeBien(String designation) {
		super();
		this.designation = designation;
	}

	public Long getIdTypeBien() {
		return idTypeBien;
	}

	public void setIdTypeBien(Long idTypeBien) {
		this.idTypeBien = idTypeBien;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Collection<Logement> getLogements() {
		return logements;
	}

	public void setBiens(Collection<Logement> logements) {
		this.logements = logements;
	}
}
