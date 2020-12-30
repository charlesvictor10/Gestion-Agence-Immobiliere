package dev.charles.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("L")
public class Location extends Operation {
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Location(Date dateOperation, Logement logement, Utilisateur utilisateur) {
		super(dateOperation, logement, utilisateur);
		// TODO Auto-generated constructor stub
	}
}
