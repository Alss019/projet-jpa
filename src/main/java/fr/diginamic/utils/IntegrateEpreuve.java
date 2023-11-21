package fr.diginamic.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.diginamic.dao.EpreuveDao;
import fr.diginamic.entites.Epreuve;

/**
 * Cette classe fournit une méthode permettant d'intégrer des données d'épreuves
 * à partir d'un fichier CSV dans une base de données.
 *
 * @author [Corentin Plaa]
 * @version 1.0
 * @since [21/11/2023]
 */
public class IntegrateEpreuve {
	/**
	 * Intègre les données d'épreuves à partir d'un fichier CSV dans une base de
	 * données.
	 * 
	 * @param path   Le chemin du fichier CSV contenant les données d'épreuves.
	 * @param ligneD La ligne à partir de laquelle commencer la lecture du fichier
	 *               CSV.
	 */
	public static void epreuveBdd(Path path, int ligneD) {
		// Création des gestionnaires d'entités et de transactions
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jo");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tr = em.getTransaction();
		try {
			// Lecture des lignes du fichier CSV
			List<String> lines = Files.readAllLines(path);
			// Début de la transaction
			tr.begin();
			// Parcours des lignes du fichier CSV et traite les données
			for (int i = ligneD; i < lines.size(); i++) {
				// Extraire les colonnes de la ligne à l'aide du parseur
				String[] ligne = Parseur.Colone(lines.get(i), i + 1);
				// Recherche si l'epreuve existe en BDD
				Epreuve epreuve = EpreuveDao.getByName(em, ligne[0]);
				if (epreuve == null) {
					// Insertion de l'épreuve en BDD
					epreuve = EpreuveDao.insert(em, ligne);
				}
			}
			// Validation de la transaction
			tr.commit();
		} catch (IOException e) {
			// Gestion des exceptions liées à la lecture du fichier
			e.printStackTrace();
		} finally {
			// Fermeture de l'EntityManager
			em.close();
			emf.close();
		}
	}
}
