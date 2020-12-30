package dev.charles.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class ModePaiement implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idModePaiement;
	private String nomPaiement;
	private String logoPaiement;
	@OneToMany(mappedBy="modePaiement")
	private Collection<Paye> payes;
	
	public ModePaiement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ModePaiement(String nomPaiement, String logoPaiement) {
		super();
		this.nomPaiement = nomPaiement;
		this.logoPaiement = logoPaiement;
	}

	public Long getIdModePaiement() {
		return idModePaiement;
	}

	public void setIdModePaiement(Long idModePaiement) {
		this.idModePaiement = idModePaiement;
	}

	public String getNomPaiement() {
		return nomPaiement;
	}

	public void setNomPaiement(String nomPaiement) {
		this.nomPaiement = nomPaiement;
	}

	public String getLogoPaiement() {
		return logoPaiement;
	}

	public void setLogoPaiement(String logoPaiement) {
		this.logoPaiement = logoPaiement;
	}

	public Collection<Paye> getPayes() {
		return payes;
	}

	public void setPayes(Collection<Paye> payes) {
		this.payes = payes;
	}
}
