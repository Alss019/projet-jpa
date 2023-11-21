package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Edition;

/**
 * Cette classe fournit des méthodes d'accès aux données pour l'entité Edition
 * dans la base de données. Elle permet d'effectuer des opérations de lecture et
 * d'écriture liées à l'entité Edition.
 * 
 * @author [Corentin Plaa]
 * @version 1.0
 * @since [21/11/2023]
 */
public class EditionDao {
	/**
	 * Insère un nouveau edition dans la base de données à partir des informations
	 * fournies.
	 * 
	 * @param em    L'EntityManager pour accéder à la persistance.
	 * @param ligne Un tableau de chaînes représentant les informations sur
	 *              l'edition.
	 * @return L'objet Edition créé et persisté dans la base de données.
	 */
	public static Edition insert(EntityManager em, String[] ligne) {
		Edition newEdition = new Edition();
		newEdition.setGame(ligne[8]);
		newEdition.setCity(ligne[11]);
		newEdition.setSeason(ligne[10]);
		newEdition.setYear(ligne[9]);
		em.persist(newEdition);
		return newEdition;
	}
    /**
     * Recherche une edition par son nom dans la base de données.
     * 
     * @param em L'EntityManager pour accéder à la persistance.
     * @param ligne Le nom de l'edition.
     * @return L'objet Edition correspondant au nom donné, ou null si l'edition n'est pas trouvé.
     */
	public static Edition getByGame(EntityManager em, String ligne) {
		TypedQuery<Edition> query = em.createQuery("SELECT e FROM Edition e WHERE e.game = :param", Edition.class);
		query.setParameter("param", ligne);
		List<Edition> edition = query.getResultList();
		if (!edition.isEmpty()) {
			return edition.get(0);
		}
		return null;
	}
}
