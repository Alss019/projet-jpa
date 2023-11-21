package fr.diginamic.utils;

import java.util.Arrays;

public class Parseur {
	/**
	 * Divise la ligne en morceaux en utilisant le délimiteur ";". Vérifie et ajuste
	 * le nombre de colonnes s'il est inférieur au minimum requis.
	 *
	 * @param ligne       Ligne à diviser
	 * @param numeroLigne Numéro de la ligne
	 * @return Tableau de String représentant les morceaux de la ligne
	 */
	public static String[] Colone(String ligne, int numeroLigne) {
		// Divise la ligne en morceaux en utilisant le délimiteur ";"
		String[] morceaux = ligne.split(";");
		// Vérifie et ajuste le nombre de colonnes s'il est inférieur au minimum requis
		if (morceaux.length < 15) {
			morceaux = Arrays.copyOf(morceaux, 15);
		}
		return morceaux;
	}

	/**
	 * Divise la chaîne de caractères en deux parties en utilisant "Men's",
	 * "Women's" ou "Mixed". Si "Men's", "Women's" ou "Mixed" n'est pas trouvé, les
	 * deux parties sont égales à la chaîne originale.
	 *
	 * @param inputString Chaîne de caractères à diviser
	 * @return Tableau de String représentant les deux parties de la chaîne
	 */
	public static String[] splitString(String inputString) {
		String[] result = new String[2];

		// Utiliser StringBuilder pour construire la première partie
		StringBuilder part1 = new StringBuilder();
		int index = inputString.indexOf("Men's");
		if (index == -1) {
			index = inputString.indexOf("Women's");
		}
		if (index == -1) {
			index = inputString.indexOf("Mixed");
		}

		if (index != -1) {
			part1.append(inputString, 0, index);
			result[0] = part1.toString().trim(); // Tout avant "Men's" ou "Women's"
			result[1] = inputString.substring(index).trim(); // "Men's" ou "Women's" inclus dans la deuxième partie
		} else {
			// Si "Men's" ou "Women's" n'est pas trouvé, utiliser la chaîne originale pour
			// les deux parties
			result[0] = inputString.trim();
			result[1] = inputString.trim();
		}

		return result;
	}
}
