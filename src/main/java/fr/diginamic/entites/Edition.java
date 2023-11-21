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
 * Cette classe représente l'entité "Edition" dans le contexte des Jeux Olympiques.
 * Elle stocke les informations sur une édition spécifique des Jeux Olympiques, telles que le nom, la ville,
 * la saison et l'année. Elle est également associée à des participations d'athlètes.
 * 
 * @author [Corentin Plaa]
 * @version 1.0
 * @since [21/11/2023]
 */
@Entity
@Table(name="EDITION")
public class Edition {

	/** 
	 * ID de l'edition des Jeux Olympique
	 *  */
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/** 
	 * GAME nom de l'edition des Jeux Olympique
	 *  */
	@Column(name="GAME", length=255)
	private String game;
	/** 
	 * CITY ville de l'edition des Jeux Olympique
	 *  */
	@Column(name="CITY", length=255)
	private String city;
	/** 
	 * SEASON saison de l'edition des Jeux Olympique
	 *  */
	@Column(name="SEASON", length=255)
	private String season;
	/** 
	 * YEAR années de l'edition des Jeux Olympique
	 *  */
	@Column(name="YEAR", length=255)
	private String year;
	 /**
     * Liste des participations associées à cette édition
     */
	@OneToMany(mappedBy = "edition")
	private Set<Participation> participation;

	/**
	 * Constructeur par défaut
	 * Initialise l'ensemble des participations associées.
	 */
	public Edition() {
		super();
		this.participation = new HashSet<>();
	}

	/** Getter
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/** Setter
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/** Getter
	 * @return the game
	 */
	public String getGame() {
		return game;
	}

	/** Setter
	 * @param game the city to set
	 */
	public void setGame(String game) {
		this.game = game;
	}
	/** Getter
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/** Setter
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/** Getter
	 * @return the season
	 */
	public String getSeason() {
		return season;
	}

	/** Setter
	 * @param season the season to set
	 */
	public void setSeason(String season) {
		this.season = season;
	}

	/** Getter
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/** Setter
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/** Getter
	 * @return the participation
	 */
	public Set<Participation> getParticipation() {
		return participation;
	}

	/** Setter
	 * @param participation the participation to set
	 */
	public void setParticipation(Set<Participation> participation) {
		this.participation = participation;
	}


}
