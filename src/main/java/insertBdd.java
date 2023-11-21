import java.nio.file.Path;
import java.nio.file.Paths;

import fr.diginamic.utils.IntegrateEpreuve;
import fr.diginamic.utils.IntegrateOrganisation;
import fr.diginamic.utils.IntegrateParticipation;
import fr.diginamic.utils.IntegrateSport;

public class insertBdd {
	/**
	 * Classe principale pour l'insertion de données dans la base de données.
	 */
	public static void main(String[] args) {
		// Mesurer le temps d'exécution
		long debut = System.currentTimeMillis();

		// Chemins des fichiers CSV
		Path organisationFilePath = Paths.get(
				"/Users/corentin/Documents/workspace-spring-tool-suite-4-4.20.0.RELEASE/projet-jpa/src/main/resources/liste_des_organisations.csv");
		Path sportFilePath = Paths.get(
				"/Users/corentin/Documents/workspace-spring-tool-suite-4-4.20.0.RELEASE/projet-jpa/src/main/resources/liste_des_sports.csv");
		Path epreuveFilePath = Paths.get(
				"/Users/corentin/Documents/workspace-spring-tool-suite-4-4.20.0.RELEASE/projet-jpa/src/main/resources/liste_des_epreuves.csv");
		Path participationFilePath = Paths.get(
				"/Users/corentin/Documents/workspace-spring-tool-suite-4-4.20.0.RELEASE/projet-jpa/src/main/resources/evenements.csv");

		// Intégration des organisations
		IntegrateOrganisation.organisationBdd(organisationFilePath, 1);

		// Intégration des sports
		IntegrateSport.sportBdd(sportFilePath, 1);

		// Intégration des épreuves
		IntegrateEpreuve.epreuveBdd(epreuveFilePath, 1);

		// Intégration des participations
		IntegrateParticipation.editionBdd(participationFilePath, 1);
		// Mesurer le temps d'exécution
		long fin = System.currentTimeMillis();
		long differenceTempsMillis = fin - debut;
		double differenceTempsSecondes = differenceTempsMillis / 1000.0;

		System.out.println("Temps d'exécution : " + differenceTempsSecondes + " secondes");
	}
}
