package dev.charles.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Region implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idRegion;
	@NotEmpty
	@Size(min=2, max=50)
	private String nomRegion;
	@OneToMany(mappedBy="region", fetch=FetchType.LAZY)
	private Collection<Departement> departements;
	
	public Region() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Region(String nomRegion) {
		super();
		this.nomRegion = nomRegion;
	}

	public Long getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(Long idRegion) {
		this.idRegion = idRegion;
	}

	public String getNomRegion() {
		return nomRegion;
	}

	public void setNomRegion(String nomRegion) {
		this.nomRegion = nomRegion;
	}

	public Collection<Departement> getDepartements() {
		return departements;
	}

	public void setDepartements(Collection<Departement> departements) {
		this.departements = departements;
	}
}
