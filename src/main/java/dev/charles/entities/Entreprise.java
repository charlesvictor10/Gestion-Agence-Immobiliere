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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Entreprise implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idEntreprise;
	@NotEmpty
	@Size(min=3, max=20)
	private String nomEntreprise;
	@NotEmpty
	private String telephoneEntreprise;
	@NotEmpty
	@Email
	private String emailEntreprise;
	private String siteWebEntreprise;
	private String logoEntreprise;
	@OneToMany(mappedBy="entreprise")
	private Collection<Logement> logement;
	@ManyToOne
	@JoinColumn(name="ID_VILLE")
	private Ville ville;
	
	public Entreprise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Entreprise(String nomEntreprise, String telephoneEntreprise, String emailEntreprise,
			String siteWebEntreprise, String logoEntreprise, Ville ville) {
		super();
		this.nomEntreprise = nomEntreprise;
		this.telephoneEntreprise = telephoneEntreprise;
		this.emailEntreprise = emailEntreprise;
		this.siteWebEntreprise = siteWebEntreprise;
		this.logoEntreprise = logoEntreprise;
		this.ville = ville;
	}

	public Long getIdEntreprise() {
		return idEntreprise;
	}

	public void setIdEntreprise(Long idEntreprise) {
		this.idEntreprise = idEntreprise;
	}

	public String getNomEntreprise() {
		return nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}

	public String getTelephoneEntreprise() {
		return telephoneEntreprise;
	}

	public void setTelephoneEntreprise(String telephoneEntreprise) {
		this.telephoneEntreprise = telephoneEntreprise;
	}

	public String getEmailEntreprise() {
		return emailEntreprise;
	}

	public void setEmailEntreprise(String emailEntreprise) {
		this.emailEntreprise = emailEntreprise;
	}

	public String getSiteWebEntreprise() {
		return siteWebEntreprise;
	}

	public void setSiteWebEntreprise(String siteWebEntreprise) {
		this.siteWebEntreprise = siteWebEntreprise;
	}
	
	public String getLogoEntreprise() {
		return logoEntreprise;
	}

	public void setLogoEntreprise(String logoEntreprise) {
		this.logoEntreprise = logoEntreprise;
	}

	public Collection<Logement> getBiens() {
		return logement;
	}

	public void setBiens(Collection<Logement> logement) {
		this.logement = logement;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}
}
