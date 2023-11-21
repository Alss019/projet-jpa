package fr.diginamic.entites;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Cette classe représente l'entité Equipe dans le contexte des Jeux
 * Olympique.Elle stocke les informations sur le nom de l'équipe. Une Equipe est
 * associée a une organisation et peut avoir plusieurs participations.
 * 
 * @author [Corentin Plaa]
 * @version 1.0
 * @since [21/11/2023]
 */
@Entity
@Table(name = "EQUIPE")
public class Equipe {
	/** ID unique  de l'equipe **/
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/**
	 * NAME nom de l'equipe
	 **/
	@Column(name = "NAME", length = 255)
	private String name;
	/** Ensemble des participations associées à cette équipe */
	@OneToMany(mappedBy = "equipe")
	private Set<Participation> participation;
	/** L'organisation associé à cette épreuve */
	@ManyToOne
	@JoinColumn(name = "ID_ORGANISATION")
	private Organisation organisation;

	/**
	 * Constructeur
	 * Initialise l'ensemble des participations associées.
	 */
	public Equipe() {
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

	/**
	 * Getter
	 * 
	 * @return the organisation
	 */
	public Organisation getOrganisation() {
		return organisation;
	}

	/**
	 * Setter
	 * 
	 * @param organisation the organisation to set
	 */
	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

}
