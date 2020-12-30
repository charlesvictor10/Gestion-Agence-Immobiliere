package dev.charles.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Temoignage implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idTemoignage;
	@NotEmpty
	private String message;
	@ManyToOne
	@JoinColumn(name="CODE_UTILISATEUR")
	private Utilisateur utilisateur;
	
	public Temoignage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Temoignage(String message, Utilisateur utilisateur) {
		super();
		this.message = message;
		this.utilisateur = utilisateur;
	}

	public Long getIdTemoignage() {
		return idTemoignage;
	}

	public void setIdTemoignage(Long idTemoignage) {
		this.idTemoignage = idTemoignage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
}
