package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Epreuve;
import fr.diginamic.entites.Sport;
import fr.diginamic.utils.Parseur;

/**
 * Cette classe fournit des méthodes d'accès aux données pour l'entité Epreuve
 * dans la base de données. Elle permet d'effectuer des opérations de lecture et
 * d'écriture liées à l'entité Epreuve.
 * 
 * @author [Corentin Plaa]
 * @version 1.0
 * @since [21/11/2023]
 */
public class EpreuveDao {
	/**
	 * Insère une nouvelle épreuve dans la base de données à partir des informations
	 * fournies.
	 * 
	 * @param em    L'EntityManager pour accéder à la persistance.
	 * @param ligne Un tableau de chaînes représentant les informations sur
	 *              l'épreuve.
	 * @return L'objet Epreuve créé et persisté dans la base de données.
	 */
	public static Epreuve insert(EntityManager em, String[] ligne) {
		Epreuve e = new Epreuve();
		e.setNameEn(ligne[0]);
		e.setNameFr(ligne[1]);
		em.persist(e);
		return e;
	}

	/**
	 * Met à jour l'épreuve en lui associant le sport lié à l'épreuve, à partir des
	 * informations fournies.
	 * 
	 * @param em    L'EntityManager pour accéder à la persistance.
	 * @param ligne Un tableau de chaînes représentant les informations sur
	 *              l'épreuve.
	 * @return L'objet Epreuve mis à jour avec l'association au sport, ou null si
	 *         l'épreuve n'existe pas.
	 */
	public static Epreuve update(EntityManager em, String[] ligne) {
		// Utilisation de Parseur.splitString pour diviser la chaîne de caractere
		String[] parts = Parseur.splitString(ligne[13]);

		// Utilisation de la deuxième partie après la division pour le nom de l'epreuve
		String epreuveName = parts[1];

		// Vérifie si l'épreuve existe déjà
		Epreuve existingEpreuve = getByName(em, epreuveName);

		if (existingEpreuve != null) {

			// Récupérer l'objet Sport par son nom
			Sport sport = SportDao.getByName(em, ligne[12]);

			if (sport != null) {
				// Associer l'objet Sport à l'épreuve
				existingEpreuve.setSport(sport);
				// Mise à jour dans la base de données
				em.merge(existingEpreuve);

				return existingEpreuve;
			} else {
				return null;
			}
		}
		return null;

	}

	/**
	 * Vérifie l'existence d'une épreuve par son nom en anglais dans la base de
	 * données.
	 * 
	 * @param em       L'EntityManager pour accéder à la persistance.
	 * @param gameName Le nom de l'épreuve en anglais.
	 * @return true si l'épreuve existe, false sinon.
	 */
	public static boolean existsByGame(EntityManager em, String gameName) {
		TypedQuery<Long> query = em.createQuery("SELECT COUNT(e) FROM Epreuve e WHERE e.nameEn = :param", Long.class);
		query.setParameter("param", gameName);
		Long count = query.getSingleResult();
		return count > 0;
	}

	/**
	 * Recherche une épreuve par son nom en anglais dans la base de données.
	 * 
	 * @param em   L'EntityManager pour accéder à la persistance.
	 * @param line Le nom de l'épreuve en anglais.
	 * @return L'objet Epreuve correspondant au nom donné, ou null si l'épreuve
	 *         n'est pas trouvée.
	 */
	public static Epreuve getByName(EntityManager em, String ligne) {
		TypedQuery<Epreuve> query = em.createQuery("SELECT e FROM Epreuve e WHERE e.nameEn = :param", Epreuve.class);
		query.setParameter("param", ligne);
		List<Epreuve> edition = query.getResultList();
		if (!edition.isEmpty()) {
			return edition.get(0);
		}
		return null;
	}
}
