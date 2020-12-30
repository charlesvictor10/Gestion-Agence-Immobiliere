package dev.charles.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Utilisateur implements Serializable {
	@NotEmpty
	@Size(min=3, max=50)
	private String prenomUtilisateur;
	@NotEmpty
	@Size(min=3, max=20)
	private String nomUtilisateur;
	@Email
	private String emailUtilisateur;
	@Id
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	private boolean active;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateNaissanceUtilisateur;
	@NotEmpty
	private String telephoneUtilisateur;
	@NotEmpty
	private String sexeUtilisateur;
	private String professionUtilisateur;
	@NotEmpty
	private String nationaliteUtilisateur;
	private String photoUtilisateur;
	@ManyToMany
	@JoinTable(name="UTILISATEUR_ROLE")
	private Collection<Role> roles;
	@OneToMany(mappedBy="utilisateur", fetch=FetchType.LAZY)
	private Collection<Operation> operations;
	@OneToMany(mappedBy="utilisateur", fetch=FetchType.LAZY)
	private Collection<Temoignage> temoignages;
	@OneToMany(mappedBy="utilisateur")
	private Collection<Paye> payes;
	
	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Utilisateur(String prenomUtilisateur,	String nomUtilisateur, String username, String password,
			Boolean active, Date dateNaissanceUtilisateur, String telephoneUtilisateur, String sexeUtilisateur,
			String professionUtilisateur, String nationaliteUtilisateur, String photoUtilisateur) {
		super();
		this.prenomUtilisateur = prenomUtilisateur;
		this.nomUtilisateur = nomUtilisateur;
		this.username = username;
		this.password = password;
		this.active = active;
		this.dateNaissanceUtilisateur = dateNaissanceUtilisateur;
		this.telephoneUtilisateur = telephoneUtilisateur;
		this.sexeUtilisateur = sexeUtilisateur;
		this.professionUtilisateur = professionUtilisateur;
		this.nationaliteUtilisateur = nationaliteUtilisateur;
		this.photoUtilisateur = photoUtilisateur;
	}

	public String getPrenomUtilisateur() {
		return prenomUtilisateur;
	}

	public void setPrenomUtilisateur(String prenomUtilisateur) {
		this.prenomUtilisateur = prenomUtilisateur;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getEmailUtilisateur() {
		return emailUtilisateur;
	}

	public void setEmailUtilisateur(String emailUtilisateur) {
		this.emailUtilisateur = emailUtilisateur;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getDateNaissanceUtilisateur() {
		return dateNaissanceUtilisateur;
	}

	public void setDateNaissanceUtilisateur(Date dateNaissanceUtilisateur) {
		this.dateNaissanceUtilisateur = dateNaissanceUtilisateur;
	}

	public String getTelephoneUtilisateur() {
		return telephoneUtilisateur;
	}

	public void setTelephoneUtilisateur(String telephoneUtilisateur) {
		this.telephoneUtilisateur = telephoneUtilisateur;
	}

	public String getSexeUtilisateur() {
		return sexeUtilisateur;
	}

	public void setSexeUtilisateur(String sexeUtilisateur) {
		this.sexeUtilisateur = sexeUtilisateur;
	}

	public String getProfessionUtilisateur() {
		return professionUtilisateur;
	}

	public void setProfessionUtilisateur(String professionUtilisateur) {
		this.professionUtilisateur = professionUtilisateur;
	}

	public String getNationaliteUtilisateur() {
		return nationaliteUtilisateur;
	}

	public void setNationaliteUtilisateur(String nationaliteUtilisateur) {
		this.nationaliteUtilisateur = nationaliteUtilisateur;
	}

	public String getPhotoUtilisateur() {
		return photoUtilisateur;
	}

	public void setPhotoUtilisateur(String photoUtilisateur) {
		this.photoUtilisateur = photoUtilisateur;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public Collection<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}

	public Collection<Temoignage> getTemoignages() {
		return temoignages;
	}

	public void setTemoignages(Collection<Temoignage> temoignages) {
		this.temoignages = temoignages;
	}

	public Collection<Paye> getPayes() {
		return payes;
	}

	public void setPayes(Collection<Paye> payes) {
		this.payes = payes;
	}
}
