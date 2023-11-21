package fr.diginamic.entites;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Cette classe représente l'entité Sport dans le contexte des Jeux
 * Olympique.Elle stocke les informations sur le nom en anglais et le nom en
 * francais.Un Sport peut être associé à plusieurs épreuves (epreuve).
 * 
 * @author [Corentin Plaa]
 * @version 1.0
 * @since [21/11/2023]
 */
@Entity
@Table(name = "SPORT")
public class Sport {
	/** ID unique du sport */
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/**
	 * nameFr est le nom en français du sport
	 */
	@Column(name = "NAME_FR", length = 255)
	private String nameFr;
	/**
	 * nameEn est le nom en anglais du sport
	 */
	@Column(name = "NAME_EN", length = 255)
	private String nameEn;
	/** Ensemble des épreuves associées à ce sport */
	@OneToMany(mappedBy = "sport")
	private Set<Epreuve> epreuve;

	/**
	 * Constructeur par défaut de l'entité Sport. 
	 * Initialise l'ensemble des épreuves associées.
	 */
	public Sport() {
		super();
		this.epreuve = new HashSet<>();
	}

	/**
	 * Getter
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter
	 * 
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter
	 * 
	 * @return the nameFr
	 */
	public String getNameFr() {
		return nameFr;
	}

	/**
	 * Setter
	 * 
	 * @param nameFr the nameFr to set
	 */
	public void setNameFr(String nameFr) {
		this.nameFr = nameFr;
	}

	/**
	 * Getter
	 * 
	 * @return the nameEn
	 */
	public String getNameEn() {
		return nameEn;
	}

	/**
	 * Setter
	 * 
	 * @param nameEn the nameEn to set
	 */
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	/**
	 * Getter
	 * 
	 * @return the epreuve
	 */
	public Set<Epreuve> getEpreuve() {
		return epreuve;
	}

	/**
	 * Setter
	 * 
	 * @param epreuve the epreuve to set
	 */
	public void setEpreuve(Set<Epreuve> epreuve) {
		this.epreuve = epreuve;
	}

}
