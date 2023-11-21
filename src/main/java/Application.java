import java.util.Scanner;

import fr.diginamic.service.MenuService;
import fr.diginamic.service.TableauMedaille;
import fr.diginamic.service.TableauMedailleParSport;
import fr.diginamic.service.TableauMedailleParSportEtEpreuve;

/**
 * La classe principale de l'application des Jeux Olympiques. Permet aux
 * utilisateurs d'interagir avec diverses fonctionnalités de recherche liées aux
 * Jeux Olympiques.
 */
public class Application {
	/**
	 * La méthode principale de l'application des Jeux Olympiques.
	 *
	 * @param args Les arguments de la ligne de commande (non utilisés dans cette
	 *             application).
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MenuService recherche = null;
		int choix = 0;
		// Boucle d'application
		while (choix != 9) {
			afficherMenu();
			choix = scanner.nextInt();
			scanner.nextLine();
			// Gérer le choix de l'utilisateur
			switch (choix) {
			case 1:
				recherche = new TableauMedaille();
				recherche.traiter(scanner);
				break;
			case 2:
				recherche = new TableauMedailleParSport();
				recherche.traiter(scanner);
				break;
			case 3:
				recherche = new TableauMedailleParSportEtEpreuve();
				recherche.traiter(scanner);
				break;
			default:
				System.out.println("Choix invalide. Veuillez réessayer.");
			}
		}
		scanner.close(); // Fermer le scanner lorsque l'application se termine
	}

	/**
	 * Affiche le menu principal de l'application des Jeux Olympiques.
	 */
	private static void afficherMenu() {
		System.out.println("***** Recherche Jeux Olympique *****");
		System.out.println("1. Tableau des medailles");
		System.out.println("2. Tableau des medailles par Sport");
		System.out.println("3. Tableau des medailles par Sport et Par epreuve");
		System.out.println("99. Sortir");
	}
}
