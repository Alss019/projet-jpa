package fr.diginamic.service;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class TableauMedailleParSportEtEpreuve extends MenuService {

	@Override
	public void traiter(Scanner scanner) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jo");
		EntityManager em = entityManagerFactory.createEntityManager();

		// Demande du choix du tri a l'utilisateur
		System.out.println(
				"\n================================================\n 1. Tableau des médailles tier par medaille d'Or\n 2. Tableau des médailles tier par medille total");
		System.out.print("Entrez votre choix : ");
		String choix = scanner.next();
		if (choix.equals("1")) {
			System.out.print("\n----- Trie par médaille d'or obtnenu -----\n");
			TypedQuery<Object[]> query = em.createQuery("SELECT s.nameFr, ep.nameFr, "
					+ "SUM(CASE WHEN p.medal = 'gold' THEN 1 ELSE 0 END) as goldCount, "
					+ "SUM(CASE WHEN p.medal = 'silver' THEN 1 ELSE 0 END) as silverCount, "
					+ "SUM(CASE WHEN p.medal = 'bronze' THEN 1 ELSE 0 END) as bronzeCount " + "FROM Participation p "
					+ "JOIN p.epreuve ep " + "JOIN ep.sport s " + "WHERE p.medal IN ('gold', 'silver', 'bronze') "
					+ "GROUP BY s.nameFr, ep.nameFr " + "ORDER BY goldCount DESC, s.nameFr, ep.nameFr", Object[].class);

			List<Object[]> resultats = query.getResultList();

			System.out.printf("%-65s%-20s%-20s%-27s%-10s%n", " ", "Médailles d'or",
					"Médailles d'argent", "Médailles de bronze", "Total");
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

			for (Object[] result : resultats) {
				String nomSport = (String) result[0];
				String nomEpreuve = (String) result[1];
				Long goldCount = (Long) result[2];
				Long silverCount = (Long) result[3];
				Long bronzeCount = (Long) result[4];
				Long totalMedaille =  goldCount + silverCount + bronzeCount;
				String string = nomSport + " : " +  nomEpreuve;
				System.out.printf("%-69s %-20d %-20d %-20d %-10d%n", string ,goldCount, silverCount, bronzeCount, totalMedaille);
			
				// Requete qui recheche les medailles obtenue lors de chaque epreuve pour chaque nation 
				TypedQuery<Object[]> topNationsQuery = em.createQuery(
						"SELECT p.equipe.organisation.nameFr, "
								+ "SUM(CASE WHEN p.medal = 'gold' THEN 1 ELSE 0 END) as goldCount, "
								+ "SUM(CASE WHEN p.medal = 'silver' THEN 1 ELSE 0 END) as silverCount, "
								+ "SUM(CASE WHEN p.medal = 'bronze' THEN 1 ELSE 0 END) as bronzeCount "
								+ "FROM Participation p " + "JOIN p.epreuve ep " + "JOIN ep.sport s "
								+ "WHERE p.medal IN ('gold', 'silver', 'bronze') " + "AND s.nameFr = :sportName "
								+ "AND ep.nameFr = :epreuveName " + "GROUP BY p.equipe.organisation.nameFr "
								+ "ORDER BY goldCount DESC, silverCount DESC, bronzeCount DESC, s.nameFr",
						Object[].class);
				// Ajout des parametre a la requete
				topNationsQuery.setParameter("sportName", nomSport);
				topNationsQuery.setParameter("epreuveName", nomEpreuve);

				List<Object[]> topNations = topNationsQuery.getResultList();

				// Affichez les résultats pour les nations qui ont obtenue des médailles
				System.out.println("Top 3 Nations :");
				for (Object[] nationResult : topNations) {
					String nomNation = (String) nationResult[0];
					Long nationGoldCount = (Long) nationResult[1];
					Long nationSilverCount = (Long) nationResult[2];
					Long nationBronzeCount = (Long) nationResult[3];

					Long totalMedailles = nationGoldCount + nationSilverCount + nationBronzeCount;
					System.out.printf("   -%-65s %-20d %-20d %-20d %-10d%n", nomNation, nationGoldCount, nationSilverCount,
							nationBronzeCount,totalMedailles);
				}
				System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
			}

			System.out.printf("%-65s%-20s%-20s%-27s%-10s%n\n", " ", "Médailles d'or",
					"Médailles d'argent", "Médailles de bronze", "Total");
		}
		
		if(choix.equals("2")) {
			
			TypedQuery<Object[]> query = em.createQuery("SELECT s.nameFr, ep.nameFr, "
					+ "SUM(CASE WHEN p.medal = 'gold' THEN 1 ELSE 0 END) as goldCount, "
					+ "SUM(CASE WHEN p.medal = 'silver' THEN 1 ELSE 0 END) as silverCount, "
					+ "SUM(CASE WHEN p.medal = 'bronze' THEN 1 ELSE 0 END) as bronzeCount " + "FROM Participation p "
					+ "JOIN p.epreuve ep " + "JOIN ep.sport s " + "WHERE p.medal IN ('gold', 'silver', 'bronze') "
					+ "GROUP BY s.nameFr, ep.nameFr " + "ORDER BY s.nameFr, ep.nameFr", Object[].class);

			List<Object[]> resultats = query.getResultList();

			System.out.printf("%-65s%-20s%-20s%-27s%-10s%n", " ", "Médailles d'or",
					"Médailles d'argent", "Médailles de bronze", "Total");
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------");

			for (Object[] result : resultats) {			
				String nomSport = (String) result[0];
				String nomEpreuve = (String) result[1];
				Long goldCount = (Long) result[2];
				Long silverCount = (Long) result[3];
				Long bronzeCount = (Long) result[4];
				Long totalMedaille =  goldCount + silverCount + bronzeCount;
				String string = nomSport + " : " +  nomEpreuve;
				System.out.printf("%-69s %-20d %-20d %-20d %-10d%n", string ,goldCount, silverCount, bronzeCount, totalMedaille);
				// Requete qui recheche les medailles obtenue lors de chaque epreuve pour chaque nation 
				TypedQuery<Object[]> topNationsQuery = em.createQuery(
						"SELECT p.equipe.organisation.nameFr, "
								+ "SUM(CASE WHEN p.medal = 'gold' THEN 1 ELSE 0 END) as goldCount, "
								+ "SUM(CASE WHEN p.medal = 'silver' THEN 1 ELSE 0 END) as silverCount, "
								+ "SUM(CASE WHEN p.medal = 'bronze' THEN 1 ELSE 0 END) as bronzeCount "
								+ "FROM Participation p " + "JOIN p.epreuve ep " + "JOIN ep.sport s "
								+ "WHERE p.medal IN ('gold', 'silver', 'bronze') " + "AND s.nameFr = :sportName "
								+ "AND ep.nameFr = :epreuveName " + "GROUP BY p.equipe.organisation.nameFr "
								+  "ORDER BY (SUM(CASE WHEN p.medal = 'gold' THEN 1 ELSE 0 END) + " +
					            "          SUM(CASE WHEN p.medal = 'silver' THEN 1 ELSE 0 END) + " +
					            "          SUM(CASE WHEN p.medal = 'bronze' THEN 1 ELSE 0 END)) DESC",
						Object[].class);
				// Ajout des parametre a la requete
				topNationsQuery.setParameter("sportName", nomSport);
				topNationsQuery.setParameter("epreuveName", nomEpreuve);

				List<Object[]> topNations = topNationsQuery.getResultList();

				// Affichez les résultats pour les nations qui ont obtenue des médailles
				System.out.println("Top 3 Nations :");
				for (Object[] nationResult : topNations) {
					String nomNation = (String) nationResult[0];
					Long nationGoldCount = (Long) nationResult[1];
					Long nationSilverCount = (Long) nationResult[2];
					Long nationBronzeCount = (Long) nationResult[3];
					Long totalMedailles = nationGoldCount + nationSilverCount + nationBronzeCount;
					System.out.printf("   -%-65s %-20d %-20d %-20d %-10d%n", nomNation, nationGoldCount, nationSilverCount,
							nationBronzeCount,totalMedailles);
				}
				System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
			}
			System.out.printf("%-65s%-20s%-20s%-27s%-10s%n\n", " ", "Médailles d'or",
					"Médailles d'argent", "Médailles de bronze", "Total");
		}
	}

}
