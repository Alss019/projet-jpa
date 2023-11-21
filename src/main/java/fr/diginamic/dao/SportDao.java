package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Sport;

/**
 * Cette classe fournit des méthodes d'accès aux données pour l'entité Sport
 * dans la base de données. Elle permet d'effectuer des opérations de lecture et
 * d'écriture liées à l'entité Sport.
 * 
 * @author [Corentin Plaa]
 * @version 1.0
 * @since [21/11/2023]
 */
public class SportDao {
	/**
	 * Insère un nouveau sport dans la base de données à partir des informations
	 * fournies.
	 * 
	 * @param em    L'EntityManager pour accéder à la persistance.
	 * @param ligne Un tableau de chaînes représentant les informations sur le
	 *              sport.
	 * @return L'objet Sport créé et persisté dans la base de données.
	 */
	public static Sport insert(EntityManager em, String[] ligne) {
		Sport s = new Sport();
		s.setNameEn(ligne[0]);
		s.setNameFr(ligne[1]);
		em.persist(s);
		return s;
	}
    /**
     * Recherche un sport par son nom en anglais dans la base de données.
     * 
     * @param em L'EntityManager pour accéder à la persistance.
     * @param ligne Le nom du sport en anglais.
     * @return L'objet Sport correspondant au nom donné, ou null si le sport n'est pas trouvé.
     */
	public static Sport getByName(EntityManager em, String ligne) {
		TypedQuery<Sport> query = em.createQuery("SELECT s FROM Sport s WHERE s.nameEn = :param", Sport.class);
		query.setParameter("param", ligne);
		List<Sport> s = query.getResultList();
		if (!s.isEmpty()) {
			return s.get(0);
		}
		return null;
	}
}
