package dev.charles.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Ville implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idVille;
	@NotEmpty
	@Size(min=2, max=100)
	private String nomVille;
	@ManyToOne
	@JoinColumn(name="ID_DEPARTEMENT")
	private Departement departement;
	@OneToMany(mappedBy="ville", fetch=FetchType.LAZY)
	private Collection<Logement> logement;
	@OneToMany(mappedBy="ville")
	private Collection<Entreprise> entreprises;
	
	public Ville() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ville(String nomVille, Departement departement) {
		super();
		this.nomVille = nomVille;
		this.departement = departement;
	}

	public Long getIdVille() {
		return idVille;
	}

	public void setIdVille(Long idVille) {
		this.idVille = idVille;
	}

	public String getNomVille() {
		return nomVille;
	}

	public void setNomVille(String nomVille) {
		this.nomVille = nomVille;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	
	public Collection<Logement> getBiens() {
		return logement;
	}

	public void setBiens(Collection<Logement> logement) {
		this.logement = logement;
	}

	public Collection<Entreprise> getEntreprises() {
		return entreprises;
	}

	public void setEntreprises(Collection<Entreprise> entreprises) {
		this.entreprises = entreprises;
	}
}
