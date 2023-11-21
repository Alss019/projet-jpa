package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Athlete;
import fr.diginamic.utils.Format;

/**
 * Cette classe fournit des méthodes d'accès aux données pour l'entité Athlete
 * dans la base de données. Elle permet d'effectuer des opérations de lecture et
 * d'écriture liées à l'entité Athlete.
 * 
 * @author [Corentin Plaa]
 * @version 1.0
 * @since [21/11/2023]
 */
public class AthleteDao {
	/**
	 * Insère un nouveau athlete dans la base de données à partir des informations
	 * fournies.
	 * 
	 * @param em    L'EntityManager pour accéder à la persistance.
	 * @param ligne Un tableau de chaînes représentant les informations sur
	 *              l'athlete.
	 * @return L'objet Athlete créé et persisté dans la base de données.
	 */
	public static Athlete insert(EntityManager em, String[] ligne) {
		Athlete a = new Athlete();
		a.setName(ligne[1]);
		a.setSex(ligne[2]);
		a.setHeight(Format.parseDouble(ligne[4]));
		a.setWeight(Format.parseDouble(ligne[5]));
		em.persist(a);
		return a;
	}
    /**
     * Recherche un athlete par son nom dans la base de données.
     * 
     * @param em L'EntityManager pour accéder à la persistance.
     * @param ligne Le nom de l'athlete.
     * @return L'objet Athlete correspondant au nom donné, ou null si l'athlete n'est pas trouvé.
     */
	public static Athlete getByName(EntityManager em, String ligne) {
		TypedQuery<Athlete> query = em.createQuery("SELECT a FROM Athlete a WHERE a.name = :param", Athlete.class);
		query.setParameter("param", ligne);
		List<Athlete> a = query.getResultList();
		if (!a.isEmpty()) {
			return a.get(0);
		}
		return null;
	}
}
