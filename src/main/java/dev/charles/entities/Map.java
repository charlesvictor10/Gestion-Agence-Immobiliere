package dev.charles.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Map implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idMap;
	private String lienMap;
	@OneToOne
	@JoinColumn(name="ID_LOGEMENT")
	private Logement logement;
	
	public Map() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Map(String lienMap) {
		super();
		this.lienMap = lienMap;
	}

	public Long getIdMap() {
		return idMap;
	}

	public void setIdMap(Long idMap) {
		this.idMap = idMap;
	}

	public String getLienMap() {
		return lienMap;
	}

	public void setLienMap(String lienMap) {
		this.lienMap = lienMap;
	}

	public Logement getLogement() {
		return logement;
	}

	public void setLogement(Logement logement) {
		this.logement = logement;
	}
}
