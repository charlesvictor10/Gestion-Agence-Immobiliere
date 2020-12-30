package dev.charles.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Paye implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idPaye;
	@ManyToOne
	@JoinColumn(name="CODE_UTILISATEUR")
	private Utilisateur utilisateur;
	@ManyToOne
	@JoinColumn(name="id_operation")
	private Operation operation;
	@ManyToOne
	@JoinColumn(name="id_mode_paiement")
	private ModePaiement modePaiement;
	@NotEmpty
	private String numeroCarte;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateExpiration;
	
	public Paye() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Paye(Utilisateur utilisateur, Operation operation, ModePaiement modePaiement, String numeroCarte,
			Date dateExpiration) {
		super();
		this.utilisateur = utilisateur;
		this.operation = operation;
		this.modePaiement = modePaiement;
		this.numeroCarte = numeroCarte;
		this.dateExpiration = dateExpiration;
	}

	public Long getIdPaye() {
		return idPaye;
	}

	public void setIdPaye(Long idPaye) {
		this.idPaye = idPaye;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public ModePaiement getModePaiement() {
		return modePaiement;
	}

	public void setModePaiement(ModePaiement modePaiement) {
		this.modePaiement = modePaiement;
	}

	public String getNumeroCarte() {
		return numeroCarte;
	}

	public void setNumeroCarte(String numeroCarte) {
		this.numeroCarte = numeroCarte;
	}

	public Date getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
}
