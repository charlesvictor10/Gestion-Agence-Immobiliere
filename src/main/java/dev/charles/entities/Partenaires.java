package dev.charles.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Partenaires implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPartenaire;
	private String nomPartenaire;
	private String logoPartenaire;
	
	public Partenaires() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Partenaires(String nomPartenaire, String logoPartenaire) {
		super();
		this.nomPartenaire = nomPartenaire;
		this.logoPartenaire = logoPartenaire;
	}

	public Long getIdPartenaire() {
		return idPartenaire;
	}

	public void setIdPartenaire(Long idPartenaire) {
		this.idPartenaire = idPartenaire;
	}

	public String getNomPartenaire() {
		return nomPartenaire;
	}

	public void setNomPartenaire(String nomPartenaire) {
		this.nomPartenaire = nomPartenaire;
	}

	public String getLogoPartenaire() {
		return logoPartenaire;
	}

	public void setLogoPartenaire(String logoPartenaire) {
		this.logoPartenaire = logoPartenaire;
	}
}
