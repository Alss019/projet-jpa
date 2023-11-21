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
 * Cette classe représente l'entité Organisation dans le contexte d'une compétition sportive.
 * Une Organisation peut être une nation ou une entité organisatrice.
 * Elle est associée à des équipes et contient des informations telles que son nom en français et en anglais,
 * les codes ISO et CIO, et son statut d'obsolescence.
 * 
 * @author [Votre nom]
 * @version 1.0
 * @since [Date]
 */
@Entity
@Table(name="ORGANISATION")
public class Organisation {
	
	/** ID d'une organisation(Nation) */
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/** 
	 * nameFr est le nom en français de organisation
	 * */
	@Column(name="NAME_FR", length=255)
	private String nameFr;
	/** 
	 * nameEn est le nom en anglais de organisation
	 * */
	@Column(name="NAME_EN", length=255)
	private String nameEn;
	/** 
	 * isoCode est le code ISO de l'oganisation
	 * */
	@Column(name="ISO_CODE", length=10)
	private String isoCode;
	/** 
	 * cioCode est le code CIO de l'oganisation
	 * */
	@Column(name="CIO_CODE", length=10)
	private String cioCode;
	/** obsolete est le statut d'obsolescence de l'organisation  */
	@Column(name="OBSOLETE", length=1)
	private String obsolete;
	/** Ensemble des équipes associées à cette organisation */
	@OneToMany(mappedBy="organisation")
	Set<Equipe> equipe;

    /**
     * Constructeur par défaut de l'entité Organisation.
     * Initialise l'ensemble des équipes associées.
     */
	public Organisation() {
		super();
		this.equipe = new HashSet<>();
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
	 * @return the isoCode
	 */
	public String getIsoCode() {
		return isoCode;
	}

	/** Setter
	 * @param isoCode the isoCode to set
	 */
	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	/** Getter
	 * @return the cioCode
	 */
	public String getCioCode() {
		return cioCode;
	}

	/** Setter
	 * @param cioCode the cioCode to set
	 */
	public void setCioCode(String cioCode) {
		this.cioCode = cioCode;
	}
	/** Getter
	 * @return the obsolete
	 */
	public String getObsolete() {
		return obsolete;
	}
	
	/** Setter
	 * @param obsolete the obsolete to set
	 */
	public void setObsolete(String obsolete) {
		this.obsolete = obsolete;
	}

	/** Getter
	 * @return the equipe
	 */
	public Set<Equipe> getEquipe() {
		return equipe;
	}

	/** Setter
	 * @param equipe the equipe to set
	 */
	public void setEquipe(Set<Equipe> equipe) {
		this.equipe = equipe;
	}

	
}
