package fr.diginamic.service;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class TableauMedaille extends MenuService {

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
			TypedQuery<Object[]> query = em.createQuery("SELECT e.organisation.nameFr, "
					+ "SUM(CASE WHEN p.medal = 'gold' THEN 1 ELSE 0 END) as goldCount, "
					+ "SUM(CASE WHEN p.medal = 'silver' THEN 1 ELSE 0 END) as silverCount, "
					+ "SUM(CASE WHEN p.medal = 'bronze' THEN 1 ELSE 0 END) as bronzeCount " + "FROM Participation p "
					+ "JOIN p.equipe e " + "WHERE p.medal IN ('gold', 'silver', 'bronze') "
					+ "GROUP BY e.organisation.nameFr " + "ORDER BY goldCount DESC, silverCount DESC, bronzeCount DESC",
					Object[].class);

			List<Object[]> resultats = query.getResultList();

			if (resultats.size() == 0) {
				System.out.println("Aucune médaille n'a été remportée pour le moment.");
			} else {
				System.out.printf("%-45s %-20s %-20s %-25s %-20s%n", "Pays", "Médailles d'or", "Médailles d'argent",
						"Médailles de bronze", "Total");

				for (Object[] result : resultats) {
					String nomPays = (String) result[0];
					Long goldCount = (Long) result[1];
					Long silverCount = (Long) result[2];
					Long bronzeCount = (Long) result[3];
					Long totalMedaille = goldCount + silverCount + bronzeCount;
					System.out.println("-------------------------");
					System.out.printf("%-50s %-20d %-20d %-20d %-20d%n", nomPays, goldCount, silverCount, bronzeCount,
							totalMedaille);
				}

				System.out.println("================================================\n");
			}
		}
		if (choix.equals("2")) {
			System.out.print("\n----- Trie par total médailles obtnenu -----\n");
		    TypedQuery<Object[]> query = em.createQuery(
		            "SELECT e.organisation.nameFr, " +
		            "SUM(CASE WHEN p.medal = 'gold' THEN 1 ELSE 0 END) as goldCount, " +
		            "SUM(CASE WHEN p.medal = 'silver' THEN 1 ELSE 0 END) as silverCount, " +
		            "SUM(CASE WHEN p.medal = 'bronze' THEN 1 ELSE 0 END) as bronzeCount " +
		            "FROM Participation p " +
		            "JOIN p.equipe e " +
		            "WHERE p.medal IN ('gold', 'silver', 'bronze') " +
		            "GROUP BY e.organisation.nameFr " +
		            "ORDER BY (SUM(CASE WHEN p.medal = 'gold' THEN 1 ELSE 0 END) + " +
		            "          SUM(CASE WHEN p.medal = 'silver' THEN 1 ELSE 0 END) + " +
		            "          SUM(CASE WHEN p.medal = 'bronze' THEN 1 ELSE 0 END)) DESC", // Tri par le total des médailles
		            Object[].class);

			List<Object[]> resultats = query.getResultList();

			if (resultats.size() == 0) {
				System.out.println("Aucune médaille n'a été remportée pour le moment.");
			} else {
				System.out.printf("%-45s %-20s %-20s %-25s %-20s%n", "Pays", "Médailles d'or", "Médailles d'argent",
						"Médailles de bronze", "Total");

				for (Object[] result : resultats) {
					String nomPays = (String) result[0];
					Long goldCount = (Long) result[1];
					Long silverCount = (Long) result[2];
					Long bronzeCount = (Long) result[3];
					Long totalMedaille = goldCount + silverCount + bronzeCount;
					System.out.println("-------------------------");
					System.out.printf("%-50s %-20d %-20d %-20d %-20d%n", nomPays, goldCount, silverCount, bronzeCount,
							totalMedaille);
				}

				System.out.println("================================================\n");
			}
		}
	}

}