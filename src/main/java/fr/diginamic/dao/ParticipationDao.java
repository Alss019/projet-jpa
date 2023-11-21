package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Athlete;
import fr.diginamic.entites.Edition;
import fr.diginamic.entites.Epreuve;
import fr.diginamic.entites.Equipe;
import fr.diginamic.entites.Participation;
import fr.diginamic.utils.Format;

/**
 * Cette classe fournit des méthodes d'accès aux données pour l'entité
 * Participation dans la base de données. Elle permet d'effectuer des opérations
 * d'insertion et de recherche liées à l'entité Participation.
 * 
 * @author [Corentin Plaa]
 * @version 1.0
 * @since [21/11/2023]
 */
public class ParticipationDao {
	/**
	 * Insère une nouvelle participation dans la base de données à partir des
	 * informations fournies.
	 * 
	 * @param em      L'EntityManager pour accéder à la persistance.
	 * @param ligne   Un tableau de chaînes représentant les informations sur la
	 *                participation.
	 * @param equipe  L'équipe à laquelle l'athlète participe.
	 * @param edition L'édition des Jeux Olympiques à laquelle l'athlète participe.
	 * @param athlete L'athlète participant à la participation.
	 * @param epreuve L'épreuve à laquelle l'athlète participe.
	 * @return L'objet Participation créé et persisté dans la base de données.
	 */
	public static Participation insertParticipation(EntityManager em, String[] ligne, Equipe equipe, Edition edition,
			Athlete athlete, Epreuve epreuve) {

		Participation p = new Participation();
		p.setAge(Format.parseInt(ligne[3]));
		p.setMedal(ligne[14]);
		p.setAthlete(athlete);
		p.setEdition(edition);
		p.setEquipe(equipe);
		p.setEpreuve(epreuve);
		em.persist(p);
		return p;
	}

	/**
	 * Recherche une participation par son ID dans la base de données.
	 * 
	 * @param em L'EntityManager pour accéder à la persistance.
	 * @param id L'ID de la participation à rechercher.
	 * @return L'objet Participation correspondant à l'ID donné, ou null si la
	 *         participation n'est pas trouvée.
	 */
	public static Participation getById(EntityManager em, int id) {
		TypedQuery<Participation> query = em.createQuery("SELECT p FROM Participation p WHERE p.id = :param",
				Participation.class);
		query.setParameter("param", id);
		List<Participation> p = query.getResultList();
		if (!p.isEmpty()) {
			return p.get(0);
		}
		return null;
	}
}
