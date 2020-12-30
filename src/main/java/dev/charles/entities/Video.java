package dev.charles.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Video implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idVideo;
	private String nomVideo;
	private String urlVideo;
	@OneToOne
	@JoinColumn(name="ID_LOGEMENT")
	private Logement logement;
	
	public Video() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Video(String nomVideo, String urlVideo) {
		super();
		this.nomVideo = nomVideo;
		this.urlVideo = urlVideo;
	}

	public Long getIdVideo() {
		return idVideo;
	}

	public void setIdVideo(Long idVideo) {
		this.idVideo = idVideo;
	}

	public String getNomVideo() {
		return nomVideo;
	}

	public void setNomVideo(String nomVideo) {
		this.nomVideo = nomVideo;
	}

	public String getUrlVideo() {
		return urlVideo;
	}

	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}

	public Logement getLogement() {
		return logement;
	}

	public void setLogement(Logement logement) {
		this.logement = logement;
	}
}
