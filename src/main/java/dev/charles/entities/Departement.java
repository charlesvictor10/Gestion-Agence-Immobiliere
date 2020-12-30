package dev.charles.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Departement implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idDepartement;
	@NotEmpty
	@Size(min=2, max=100)
	private String nomDepartement;
	@ManyToOne
	@JoinColumn(name="ID_REGION")
	private Region region;
	@OneToMany(mappedBy="departement", fetch=FetchType.LAZY)
	private Collection<Ville> villes;
	
	public Departement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Departement(String nomDepartement, Region region) {
		super();
		this.nomDepartement = nomDepartement;
		this.region = region;
	}

	public Long getIdDepartement() {
		return idDepartement;
	}

	public void setIdDepartement(Long idDepartement) {
		this.idDepartement = idDepartement;
	}

	public String getNomDepartement() {
		return nomDepartement;
	}

	public void setNomDepartement(String nomDepartement) {
		this.nomDepartement = nomDepartement;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Collection<Ville> getVilles() {
		return villes;
	}

	public void setVilles(Collection<Ville> villes) {
		this.villes = villes;
	}
}
