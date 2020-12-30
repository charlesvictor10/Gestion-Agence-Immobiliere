package dev.charles.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Logement implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idLogement;
	@NotEmpty
	private String etatLogement;
	private String referenceLogement;
	private String superficieLogement;
	private int nombrePieceLogement;
	private int nombreChambreLogement;
	@NotEmpty
	private String proprietaireLogement;
	private double prixLogement;
	private int nombreSalleBainLogement;
	private String photoLogement;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateEnregistrement;
	@OneToMany(mappedBy="logement")
	private Collection<Image> images;
	@OneToOne(mappedBy="logement")
	private Video video;
	@OneToOne(mappedBy="logement")
	private Map map;
	@ManyToOne
	@JoinColumn(name="ID_VILLE")
	private Ville ville;
	@ManyToOne
	@JoinColumn(name="ID_ENTREPRISE")
	private Entreprise entreprise;
	@ManyToOne
	@JoinColumn(name="ID_TYPE_BIEN")
	private TypeBien typeBien;
	@OneToMany(mappedBy="logement")
	private Collection<Operation> operations;
	
	public Logement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Logement(String etatLogement, String referenceLogement, String superficieLogement, int nombrePieceLogement, int nombreChambreLogement,
			String proprietaireLogement, double prixLogement, int nombreSalleBainLogement, Ville ville,
			TypeBien typeBien, Entreprise entreprise) {
		super();
		this.etatLogement = etatLogement;
		this.referenceLogement = referenceLogement;
		this.superficieLogement = superficieLogement;
		this.nombrePieceLogement = nombrePieceLogement;
		this.nombreChambreLogement = nombreChambreLogement;
		this.proprietaireLogement = proprietaireLogement;
		this.prixLogement = prixLogement;
		this.nombreSalleBainLogement = nombreSalleBainLogement;
		this.ville = ville;
		this.typeBien = typeBien;
		this.entreprise = entreprise;
	}

	public Long getIdLogement() {
		return idLogement;
	}

	public void setIdLogement(Long idLogement) {
		this.idLogement = idLogement;
	}

	public String getEtatLogement() {
		return etatLogement;
	}

	public void setEtatLogement(String etatLogement) {
		this.etatLogement = etatLogement;
	}

	public String getReferenceLogement() {
		return referenceLogement;
	}

	public void setReferenceLogement(String referenceLogement) {
		this.referenceLogement = referenceLogement;
	}

	public String getSuperficieLogement() {
		return superficieLogement;
	}

	public void setSuperficieLogement(String superficieLogement) {
		this.superficieLogement = superficieLogement;
	}

	public int getNombrePieceLogement() {
		return nombrePieceLogement;
	}

	public void setNombrePieceLogement(int nombrePieceLogement) {
		this.nombrePieceLogement = nombrePieceLogement;
	}

	public int getNombreChambreLogement() {
		return nombreChambreLogement;
	}

	public void setNombreChambreLogement(int nombreChambreLogement) {
		this.nombreChambreLogement = nombreChambreLogement;
	}

	public String getProprietaireLogement() {
		return proprietaireLogement;
	}

	public void setProprietaireLogement(String proprietaireLogement) {
		this.proprietaireLogement = proprietaireLogement;
	}

	public double getPrixLogement() {
		return prixLogement;
	}

	public void setPrixLogement(double prixLogement) {
		this.prixLogement = prixLogement;
	}

	public int getNombreSalleBainLogement() {
		return nombreSalleBainLogement;
	}

	public void setNombreSalleBainLogement(int nombreSalleBainLogement) {
		this.nombreSalleBainLogement = nombreSalleBainLogement;
	}

	public String getPhotoLogement() {
		return photoLogement;
	}

	public void setPhotoLogement(String photoLogement) {
		this.photoLogement = photoLogement;
	}
	
	public Date getDateEnregistrement() {
		return dateEnregistrement;
	}

	public void setDateEnregistrement(Date dateEnregistrement) {
		this.dateEnregistrement = dateEnregistrement;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public TypeBien getTypeBien() {
		return typeBien;
	}

	public void setTypeBien(TypeBien typeBien) {
		this.typeBien = typeBien;
	}

	public Collection<Image> getImages() {
		return images;
	}

	public void setImages(Collection<Image> images) {
		this.images = images;
	}
	
	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Collection<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}
}
