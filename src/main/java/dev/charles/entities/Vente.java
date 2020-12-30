package dev.charles.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("V")
public class Vente extends Operation {
	
	public Vente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vente(Date dateOperation, Logement logement, Utilisateur utilisateur) {
		super(dateOperation, logement, utilisateur);
		// TODO Auto-generated constructor stub
	}
}
