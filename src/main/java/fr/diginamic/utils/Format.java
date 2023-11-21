package fr.diginamic.utils;

/**
 * Classe utilitaire fournissant des méthodes de formatage pour la conversion de
 * chaînes en nombres.
 */
public class Format {
	/**
	 * Convertit une chaîne représentant un nombre à virgule flottante en un double.
	 *
	 * @param nb La chaîne représentant le nombre.
	 * @return La valeur double représentée par la chaîne, ou 0 si la chaîne est
	 *         "NA".
	 */
	public static double parseDouble(String nb) {
		double result;
		if (nb.equals("NA")) {
			nb = "0";
			result = Double.parseDouble(nb);
		} else {
			result = Double.parseDouble(nb);
		}
		return result;
	}

	/**
	 * Convertit une chaîne représentant un nombre entier en un int.
	 *
	 * @param nb La chaîne représentant le nombre.
	 * @return La valeur entière représentée par la chaîne, ou 0 si la chaîne est
	 *         "NA".
	 */
	public static int parseInt(String nb) {
		int result;
		if (nb.equals("NA")) {
			nb = "0";
			result = Integer.parseInt(nb);
		} else {
			result = Integer.parseInt(nb);
		}
		return result;
	}

}
