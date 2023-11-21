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
 * Cette classe représente l'entité "Athlete" dans le contexte des Jeux
 * Olympique. Elle stocke les informations sur un athlete (son nom, son sexe,
 * son poid, sa taille). Un Athlete peut être associé à plusieurs participation.
 * 
 * @author [Corentin Plaa]
 * @version 1.0
 * @since [21/11/2023]
 */
@Entity
@Table(name = "ATHLETE")
public class Athlete {
	/** ID unique de l'athlete */
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/**
	 * NAME nom de l'athlete
	 */
	@Column(name = "NAME", length = 255)
	private String name;
	/**
	 * SEX sexe de l'athlete
	 */
	@Column(name = "SEX", length = 1)
	private String sex;
	/**
	 * HEIGHT taille de l'athlete
	 */
	@Column(name = "HEIGHT", length = 255, nullable = true)
	private double height;
	/**
	 * WEIGHT poids de l'athlete
	 */
	@Column(name = "WEIGHT", length = 255, nullable = true)
	private double weight;
	/**
	 * Liste des participations de l'athlète dans des épreuves
	 */
	@OneToMany(mappedBy = "athlete")
	private Set<Participation> participation;

	/**
	 * Constructeur par défaut.
	 * Initialise l'ensemble des participations associées.
	 */
	public Athlete() {
		super();
		this.participation = new HashSet<>();
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter
	 * 
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * Setter
	 * 
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * Getter
	 * 
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Setter
	 * 
	 * @param height the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * Getter
	 * 
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Setter
	 * 
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * Getter
	 * 
	 * @return the participation
	 */
	public Set<Participation> getParticipation() {
		return participation;
	}

	/**
	 * Setter
	 * 
	 * @param participation the participation to set
	 */
	public void setParticipation(Set<Participation> participation) {
		this.participation = participation;
	}

}
