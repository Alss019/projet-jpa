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
 * Cette classe représente l'entité Epreuve dans le contexte ddans le contexte des Jeux
 * Olympique.Elle stocke les informations sur le nom en anglais et le nom en
 * francais.Une Epreuve est associée à un Sport et peut avoir plusieurs participations.
 * 
 * @author [Corentin Plaa]
 * @version 1.0
 * @since [21/11/2023]
 */
@Entity
@Table(name="EPREUVE")
public class Epreuve {
	/** ID unique d'une epreuve */
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/** nameFr est le nom en français de l'epreuve */
	@Column(name="NAME_FR", length=255)
	private String nameFr;
	/** nameEn est le nom en anglais de l'epreuve */
	@Column(name="NAME_EN", length=255)
	private String nameEn;
	/** Le sport associé à cette épreuve */
	@ManyToOne
	@JoinColumn(name="ID_SPORT")
	private Sport sport;
	/** Ensemble des participations associées à cette épreuve */
	@OneToMany(mappedBy="epreuve")
	private Set<Participation> participation = new HashSet<>();
	
	/**
	 * Constructeur par défaut.
	 */
	public Epreuve() {
		super();
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
	 * @return the nameFr
	 */
	public String getNameFr() {
		return nameFr;
	}

	/** Setter
	 * @param nameFr the nameFr to set
	 */
	public void setNameFr(String nameFr) {
		this.nameFr = nameFr;
	}

	/** Getter
	 * @return the nameEn
	 */
	public String getNameEn() {
		return nameEn;
	}

	/** Setter
	 * @param nameEn the nameEn to set
	 */
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	/** Getter
	 * @return the sport
	 */
	public Sport getSport() {
		return sport;
	}

	/** Setter
	 * @param sport the sport to set
	 */
	public void setSport(Sport sport) {
		this.sport = sport;
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

	@Override
	public String toString() {
		return "Epreuve [id=" + id + ", nameFr=" + nameFr + ", nameEn=" + nameEn + ", sport=" + sport
				+ ", participation=" + participation + "]";
	}
}
