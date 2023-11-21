package fr.diginamic.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Cette classe représente l'entité "Participation" dans le contexte des Jeux
 * Olympique. Elle stocke les informations sur la participation d'un athlète à
 * une épreuve donnée au sein d'une édition spécifique, au sein d'une équipe.
 * 
 * @author [Corentin Plaa]
 * @version 1.0
 * @since [21/11/2023]
 */
@Entity
@Table(name = "PARTICIPATION")
public class Participation {
	/**
	 * ID de la participation
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/**
	 * AGE de l'athlete lors de sa participation
	 */
	@Column(name = "AGE")
	private int age;
	/**
	 * MEDAL medaille de l'athlete si il y en a eu une lors de sa participation
	 */
	@Column(name = "MEDAL", nullable = true)
	private String medal;
	/**
	 * Athlète participant à cette participation.
	 */
	@ManyToOne
	@JoinColumn(name = "ID_ATHLETE")
	private Athlete athlete;
	/**
	 * Épreuve à laquelle l'athlète participe.
	 */
	@ManyToOne
	@JoinColumn(name = "ID_EPREUVE")
	private Epreuve epreuve;
	/**
	 * Équipe à laquelle l'athlète appartient lors de cette participation.
	 */
	@ManyToOne
	@JoinColumn(name = "ID_EQUIPE")
	private Equipe equipe;
	/**
	 * Édition des Jeux Olympiques à laquelle l'athlète participe.
	 */
	@ManyToOne
	@JoinColumn(name = "ID_EDITION")
	private Edition edition;

	/**
	 * Constructeur par défaut
	 */
	public Participation() {
		super();
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
	 * @return the athlete
	 */
	public Athlete getAthlete() {
		return athlete;
	}

	/**
	 * Setter
	 * 
	 * @param athlete the athlete to set
	 */
	public void setAthlete(Athlete athlete) {
		this.athlete = athlete;
	}

	/**
	 * Getter
	 * 
	 * @return the epreuve
	 */
	public Epreuve getEpreuve() {
		return epreuve;
	}

	/**
	 * Setter
	 * 
	 * @param epreuve the epreuve to set
	 */
	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}

	/**
	 * Getter
	 * 
	 * @return the equipe
	 */
	public Equipe getEquipe() {
		return equipe;
	}

	/**
	 * Setter
	 * 
	 * @param equipe the equipe to set
	 */
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	/**
	 * Getter
	 * 
	 * @return the edition
	 */
	public Edition getEdition() {
		return edition;
	}

	/**
	 * Setter
	 * 
	 * @param edition the edition to set
	 */
	public void setEdition(Edition edition) {
		this.edition = edition;
	}

	/**
	 * Getter
	 * 
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Setter
	 * 
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Getter
	 * 
	 * @return the medal
	 */
	public String getMedal() {
		return medal;
	}

	/**
	 * Setter
	 * 
	 * @param medal the medal to set
	 */
	public void setMedal(String medal) {
		this.medal = medal;
	}

}
