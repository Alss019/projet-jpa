package fr.diginamic.service;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class TableauMedailleParSport extends MenuService {

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
			TypedQuery<Object[]> query = em.createQuery("SELECT s.nameFr, "
					+ "SUM(CASE WHEN p.medal = 'gold' THEN 1 ELSE 0 END) as goldCount, "
					+ "SUM(CASE WHEN p.medal = 'silver' THEN 1 ELSE 0 END) as silverCount, "
					+ "SUM(CASE WHEN p.medal = 'bronze' THEN 1 ELSE 0 END) as bronzeCount " + "FROM Participation p "
					+ "JOIN p.epreuve ep " + "JOIN ep.sport s " + "WHERE p.medal IN ('gold', 'silver', 'bronze') "
					+ "GROUP BY s.nameFr " + "ORDER BY goldCount DESC, silverCount DESC, bronzeCount DESC",
					Object[].class);

			List<Object[]> resultats = query.getResultList();

			System.out.printf("%-45s %-20s %-20s %-20s  %-20s%n", " ", "Médailles d'or", "Médailles d'argent",
					"Médailles de bronze", "Total");

			for (Object[] result : resultats) {
				System.out.println("==========================================");
				String nomSport = (String) result[0];
				Long goldCount = (Long) result[1];
				Long silverCount = (Long) result[2];
				Long bronzeCount = (Long) result[3];
				Long totalMedaille = goldCount + silverCount + bronzeCount;
				System.out.printf("%-48s %-20d %-20d %-20d %-20d%n", nomSport, goldCount, silverCount, bronzeCount,
						totalMedaille);

				// Ajout de la requête pour les 5 nations ayant le plus de médailles pour chaque
				// sport
				TypedQuery<Object[]> topNationsQuery = em.createQuery("SELECT p.equipe.organisation.nameFr, "
						+ "SUM(CASE WHEN p.medal = 'gold' THEN 1 ELSE 0 END) as goldCount, "
						+ "SUM(CASE WHEN p.medal = 'silver' THEN 1 ELSE 0 END) as silverCount, "
						+ "SUM(CASE WHEN p.medal = 'bronze' THEN 1 ELSE 0 END) as bronzeCount "
						+ "FROM Participation p " + "JOIN p.epreuve ep " + "JOIN ep.sport s "
						+ "WHERE p.medal IN ('gold', 'silver', 'bronze') " + "AND s.nameFr = :sportName "
						+ "GROUP BY p.equipe.organisation.nameFr "
						+ "ORDER BY goldCount DESC, silverCount DESC, bronzeCount DESC", Object[].class);

				topNationsQuery.setParameter("sportName", nomSport);

				List<Object[]> topNations = topNationsQuery.getResultList();

				// Affichez les résultats pde la requete
				System.out.println("Top 5 Nations :");
				for (Object[] nationResult : topNations) {
					String nomNation = (String) nationResult[0];
					Long nationGoldCount = (Long) nationResult[1];
					Long nationSilverCount = (Long) nationResult[2];
					Long nationBronzeCount = (Long) nationResult[3];
					Long totalMedailleNation = nationGoldCount + nationSilverCount + nationBronzeCount;
					System.out.printf("   %-45s %-20d %-20d %-20d %-20d%n", nomNation, nationGoldCount,
							nationSilverCount, nationBronzeCount, totalMedailleNation);
				}

			}
			System.out.printf("%-45s %-20s %-20s %-20s  %-20s%n", " ", "Médailles d'or", "Médailles d'argent",
					"Médailles de bronze", "Total");
		}
		if (choix.equals("2")) {
			System.out.print("\n----- Trie par total médailles obtnenu -----\n");
			TypedQuery<Object[]> query = em.createQuery("SELECT s.nameFr, "
					+ "SUM(CASE WHEN p.medal = 'gold' THEN 1 ELSE 0 END) as goldCount, "
					+ "SUM(CASE WHEN p.medal = 'silver' THEN 1 ELSE 0 END) as silverCount, "
					+ "SUM(CASE WHEN p.medal = 'bronze' THEN 1 ELSE 0 END) as bronzeCount " + "FROM Participation p "
					+ "JOIN p.epreuve ep " + "JOIN ep.sport s " + "WHERE p.medal IN ('gold', 'silver', 'bronze') "
					+ "GROUP BY s.nameFr " + "ORDER BY goldCount DESC, silverCount DESC, bronzeCount DESC",
					Object[].class);

			List<Object[]> resultats = query.getResultList();

			System.out.printf("%-45s %-20s %-20s %-20s  %-20s%n", " ", "Médailles d'or", "Médailles d'argent",
					"Médailles de bronze", "Total");

			for (Object[] result : resultats) {
				System.out.println("==========================================");
				String nomSport = (String) result[0];
				Long goldCount = (Long) result[1];
				Long silverCount = (Long) result[2];
				Long bronzeCount = (Long) result[3];
				Long totalMedaille = goldCount + silverCount + bronzeCount;
				System.out.printf("%-48s %-20d %-20d %-20d %-20d%n", nomSport, goldCount, silverCount, bronzeCount,
						totalMedaille);

				// Ajout de la requête pour les nations ayant le plus de médailles pour chaque
				// sport
				TypedQuery<Object[]> topNationsQuery = em.createQuery(
						"SELECT p.equipe.organisation.nameFr, "
								+ "SUM(CASE WHEN p.medal = 'gold' THEN 1 ELSE 0 END) as goldCount, "
								+ "SUM(CASE WHEN p.medal = 'silver' THEN 1 ELSE 0 END) as silverCount, "
								+ "SUM(CASE WHEN p.medal = 'bronze' THEN 1 ELSE 0 END) as bronzeCount "
								+ "FROM Participation p " + "JOIN p.epreuve ep " + "JOIN ep.sport s "
								+ "WHERE p.medal IN ('gold', 'silver', 'bronze') " + "AND s.nameFr = :sportName "
								+ "GROUP BY p.equipe.organisation.nameFr "
								+ "ORDER BY (SUM(CASE WHEN p.medal = 'gold' THEN 1 ELSE 0 END) + "
								+ "          SUM(CASE WHEN p.medal = 'silver' THEN 1 ELSE 0 END) + "
								+ "          SUM(CASE WHEN p.medal = 'bronze' THEN 1 ELSE 0 END)) DESC",
						Object[].class);

				topNationsQuery.setParameter("sportName", nomSport);

				List<Object[]> topNations = topNationsQuery.getResultList();

				// Affichez les résultats pde la requete
				System.out.println("Top 5 Nations :");
				for (Object[] nationResult : topNations) {
					String nomNation = (String) nationResult[0];
					Long nationGoldCount = (Long) nationResult[1];
					Long nationSilverCount = (Long) nationResult[2];
					Long nationBronzeCount = (Long) nationResult[3];
					Long totalMedailles = nationGoldCount + nationSilverCount + nationBronzeCount;
					System.out.printf("   %-45s %-20d %-20d %-20d %-20d%n", nomNation, nationGoldCount,
							nationSilverCount, nationBronzeCount, totalMedailles);
				}

			}
			System.out.printf("%-45s %-20s %-20s %-20s  %-20s%n", " ", "Médailles d'or", "Médailles d'argent",
					"Médailles de bronze", "Total");
		}
	}

}
