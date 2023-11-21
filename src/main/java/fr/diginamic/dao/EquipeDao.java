package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Equipe;
import fr.diginamic.entites.Organisation;

/**
 * Cette classe fournit des méthodes d'accès aux données pour l'entité Equipe
 * dans la base de données. Elle permet d'effectuer des opérations de lecture et
 * d'écriture liées à l'entité Equipe.
 * 
 * @author [Corentin Plaa]
 * @version 1.0
 * @since [21/11/2023]
 */
public class EquipeDao {
	/**
	 * Méthode pour insérer une équipe en BDD.
	 * 
	 * @param em    EntityManager
	 * @param ligne Tableau de String représentant les informations de l'équipe
	 * @return Equipe créée et persistée en BDD
	 */
	public static Equipe insert(EntityManager em, String[] ligne) {
		Organisation orga = OrganisationDao.getByCioCode(em, ligne[7]);
		Equipe t = new Equipe();
		t.setName(ligne[6]);
		t.setOrganisation(orga);
		em.persist(t);
		return t;
	}

	/**
	 * Méthode pour rechercher l'existence d'une équipe par son nom.
	 * 
	 * @param em    EntityManager
	 * @param ligne Nom de l'équipe à rechercher
	 * @return Equipe si elle existe, sinon null
	 */
	public static Equipe getByName(EntityManager em, String ligne) {
		TypedQuery<Equipe> query = em.createQuery("SELECT e FROM Equipe e WHERE e.name = :param", Equipe.class);
		query.setParameter("param", ligne);
		List<Equipe> e = query.getResultList();
		if (!e.isEmpty()) {
			return e.get(0);
		}
		return null;
	}
}
