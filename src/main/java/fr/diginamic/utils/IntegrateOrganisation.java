package fr.diginamic.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.diginamic.dao.OrganisationDao;
import fr.diginamic.entites.Organisation;

/**
 * Cette classe fournit des méthodes pour intégrer les données d'organisation à
 * partir d'un fichier dans la base de données. Elle lit le fichier, traite les
 * données, et insère les entités d'organisation pertinentes dans la base de
 * données en utilisant l'API de persistance Java (JPA).
 * 
 * @author [Corentin Plaa]
 * @version 1.0
 * @since [21/11/2023]
 */
public class IntegrateOrganisation {
	/**
	 * Intègre les données d'organisation à partir d'un fichier spécifié dans la
	 * base de données.
	 * 
	 * @param path   Le chemin vers le fichier contenant les données d'organisation.
	 * @param ligneD L'index de la ligne de départ pour lire les données dans le
	 *               fichier.
	 */
	public static void organisationBdd(Path path, int ligneD) {
		// Création des gestionnaires d'entités et de transactions
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jo");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		try {
			// Lire les lignes du fichier
			List<String> lines = Files.readAllLines(path);

			tr.begin();
			for (int i = ligneD; i < lines.size(); i++) {
				// Extraire les colonnes de la ligne à l'aide du parseur
				String[] ligne = Parseur.Colone(lines.get(i), i + 1);
				// Recheche si l'organisation existe en BDD
				Organisation organisation = OrganisationDao.getByCioCode(em, ligne[0]);
				if (organisation == null) {
					// Insere une organisation dans la BDD
					organisation = OrganisationDao.insert(em, ligne);
				}
			}
			tr.commit();
		} catch (IOException e) {
			// Gérer l'exception en affichant ou en journalisant l'erreur
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

}
