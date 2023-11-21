package fr.diginamic.service;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class TableauMedailleParSportTopNation extends MenuService {

	@Override
	public void traiter(Scanner scanner) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jo");
		EntityManager em = entityManagerFactory.createEntityManager();

		TypedQuery<Object[]> query = em.createQuery("SELECT s.nameFr, "
				+ "SUM(CASE WHEN p.medal = 'gold' THEN 1 ELSE 0 END) as goldCount, "
				+ "SUM(CASE WHEN p.medal = 'silver' THEN 1 ELSE 0 END) as silverCount, "
				+ "SUM(CASE WHEN p.medal = 'bronze' THEN 1 ELSE 0 END) as bronzeCount " + "FROM Participation p "
				+ "JOIN p.epreuve ep " + "JOIN ep.sport s " + "WHERE p.medal IN ('gold', 'silver', 'bronze') "
				+ "GROUP BY s.nameFr " + "ORDER BY goldCount DESC, s.nameFr", Object[].class);

		List<Object[]> resultats = query.getResultList();

		System.out.printf("%-45s %-20s %-20s %-20s%n", "Pays", "Médailles d'or", "Médailles d'argent",
				"Médailles de bronze");
		System.out.println("--------------------------------------------------");

		for (Object[] result : resultats) {
			String nomPays = (String) result[0];
			Long goldCount = (Long) result[1];
			Long silverCount = (Long) result[2];
			Long bronzeCount = (Long) result[3];

			System.out.printf("%-50s %-20d %-20d %-20d%n", nomPays, goldCount, silverCount, bronzeCount);

			System.out.println("-------------------------");
		}

		System.out.println("================================================\n");
	}

}
