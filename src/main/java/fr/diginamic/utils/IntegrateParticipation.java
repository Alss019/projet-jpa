package fr.diginamic.utils;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.diginamic.dao.AthleteDao;
import fr.diginamic.dao.EditionDao;
import fr.diginamic.dao.EpreuveDao;
import fr.diginamic.dao.EquipeDao;
import fr.diginamic.dao.ParticipationDao;
import fr.diginamic.entites.Athlete;
import fr.diginamic.entites.Edition;
import fr.diginamic.entites.Epreuve;
import fr.diginamic.entites.Equipe;

/**
 * Cette classe fournit des méthodes pour intégrer les données de participation
 * d'un fichier dans la base de données. Elle lit le fichier, traite les données
 * et insère les entités pertinentes (Athlete, Equipe, Edition, Epreuve,
 * Participation) dans la base de données en utilisant l'API de persistance Java
 * (JPA).
 * 
 * @author [Corentin Plaa]
 * @version 1.0
 * @since [21/11/2023]
 */
public class IntegrateParticipation {
	/**
	 * Intègre les données de participation à partir d'un fichier spécifié dans la
	 * base de données.
	 * 
	 * @param path   Le chemin vers le fichier contenant les données de
	 *               participation.
	 * @param ligneD L'index de la ligne de départ pour lire les données dans le
	 *               fichier.
	 */
	public static void editionBdd(Path path, int ligneD) {
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
			for (int i = ligneD; i < 1000; i++) {
				// Extraire les colonnes de la ligne à l'aide du parseur
				String[] ligne = Parseur.Colone(lines.get(i), i + 1);
				// Recheche si l'equipe existe en BDD
				Equipe equipe = EquipeDao.getByName(em, ligne[6]);
				if (equipe == null) {
					equipe = EquipeDao.insert(em, ligne);
				}
				// Recheche si l'edition existe en BDD
				Edition edition = EditionDao.getByGame(em, ligne[8]);
				if (edition == null) {
					edition = EditionDao.insert(em, ligne);
				}
				// Recherche si l'athlete existe en BDD
				Athlete athlete = AthleteDao.getByName(em, ligne[1]);
				if (athlete == null) {
					athlete = AthleteDao.insert(em, ligne);
				}
				// Mettre à jour l'épreuve dans la BDD
				Epreuve epreuve = EpreuveDao.update(em, ligne);
				// Insérer une participation dans la BDD
				ParticipationDao.insertParticipation(em, ligne, equipe, edition, athlete, epreuve);
				System.out.println(i);
			}
			// Validation de la transaction
			tr.commit();
		} catch (IOException e) {
			// Gérer l'exception en affichant ou en journalisant l'erreur
			e.printStackTrace();
		} finally {
			// Fermeture de l'EntityManager
			em.close();
			emf.close();
		}
	}
}
