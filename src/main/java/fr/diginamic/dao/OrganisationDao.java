package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.diginamic.entites.Organisation;
/**
 * Cette classe fournit des méthodes d'accès aux données pour l'entité Organisation
 * dans la base de données. Elle permet d'effectuer des opérations de lecture et
 * d'écriture liées à l'entité Organisation.
 * 
 * @author [Corentin Plaa]
 * @version 1.0
 * @since [21/11/2023]
 */
public class OrganisationDao {
	/**
	 * Insère une nouvelle organisation dans la base de données à partir des informations
	 * fournies.
	 * 
	 * @param em    L'EntityManager pour accéder à la persistance.
	 * @param ligne Un tableau de chaînes représentant les informations sur le
	 *              sport.
	 * @return L'objet Organisation créé et persisté dans la base de données.
	 */
	public static Organisation insert(EntityManager em, String[] ligne) {
			Organisation o = new Organisation();
			o.setCioCode(ligne[0]);
			o.setNameFr(ligne[1]);
			o.setNameEn(ligne[2]);
			o.setIsoCode(ligne[3]);
			o.setObsolete(ligne[4]);
			
			em.persist(o);
			return o;
	}
    /**
     * Recherche une organisation dans la base de données en utilisant son code CIO.
     * 
     * @param em     L'EntityManager pour accéder à la persistance.
     * @param cioCode Le code CIO de l'organisation à rechercher.
     * @return L'objet Organisation correspondant au code CIO, ou null si non trouvé.
     */
	public static Organisation getByCioCode(EntityManager em, String cioCode) {
		TypedQuery<Organisation> query = em.createQuery("SELECT c FROM Organisation c WHERE c.cioCode = :param", Organisation.class);
        query.setParameter("param", cioCode);
        List<Organisation> organisation = query.getResultList();
        if (!organisation.isEmpty()) {
            return organisation.get(0);
        }
        return null;
    }
    /**
     * Recherche une organisation dans la base de données en utilisant son identifiant.
     * 
     * @param em L'EntityManager pour accéder à la persistance.
     * @param id L'identifiant de l'organisation à rechercher.
     * @return L'objet Organisation correspondant à l'identifiant, ou null si non trouvé.
     */
	public static Organisation getById(EntityManager em, int id) {
		TypedQuery<Organisation> query = em.createQuery("SELECT c FROM Organisation c WHERE c.id = :param", Organisation.class);
		query.setParameter("param", id);
		List<Organisation> organisation = query.getResultList();
		if (!organisation.isEmpty()) {
			return organisation.get(0);
		}
		return null;
	}

}
