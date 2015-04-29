import java.io.*;
import java.util.Scanner;

public class Sokoban {

	public static void main(String[] args) {
		// Récupération des arguments
		int nbArguments = args.length;
		int niveau = 1;
		int action = 1;
		
		if (nbArguments >= 1) {
			if (args[0].equals("--level")) {
				niveau = Integer.parseInt(args[1]);
			}
			
			if (args[0].equals("--create")) {
				action = 2;
				String testfile = "maps\\level".concat(Integer.toString(niveau)).concat(".sok");
				File f = new File(testfile);
				while(f.exists()) {
					niveau++;
					testfile = "maps\\level".concat(Integer.toString(niveau)).concat(".sok");
					f = new File(testfile);
				}
				System.out.println(niveau);
			}
			
			if (args[0].equals("--score")) {
				action = 3;
				niveau = Integer.parseInt(args[1]);
			}
		}
		
		
		String testfile = "maps\\level".concat(Integer.toString(niveau)).concat(".sok");
		File f = new File(testfile);
		
		// Boucle de jeu, si rien ou --level a été précisé (parcours tous les niveaux jusqu'au dernier
		while (f.exists() && action == 1) {
			System.out.println("---------------------------------------------------------");
			System.out.println("Lancement du niveau ".concat(Integer.toString(niveau)));
			// Initialisation du personnage
			Personnage mario = new Personnage();
			
			// Initialisation du plateau de jeu et des positions de départ
			Jeu j = new Jeu(niveau, mario);
			j.affiche_tab(mario);

			while(j.Continuer()) {
				mario.move(j);
				j.affiche_tab(mario);
			}
			long chrono=(System.currentTimeMillis() - j.getTime())/1000;
			String chr = (Long.toString(chrono)).concat("s");
			j.Result(niveau, chr);
			System.out.println("Votre temps :".concat(chr));
			niveau++;
			testfile = "maps\\level".concat(Integer.toString(niveau)).concat(".sok");
			f = new File(testfile);
			}
		
		// Boucle de création de map si --create
		if (action == 2) {
			System.out.println("Lancement editeur de map");
			Creation create = new Creation();
			create.EcritureFichier();
		}
	
		// Affichage des resultats
		if (action == 3) {
			AfficheResult(niveau);
		}
	}

	// Fonction d'affichage du resultat
	public static void AfficheResult(int niveau) {
		String str = "maps\\level".concat(Integer.toString(niveau)).concat(".result");
		
		// Lecture du fichier result si il existe
		try {
			File f = new File(str);
			FileReader fread = new FileReader(f);
			BufferedReader bread = new BufferedReader(fread);
			System.out.println("Resultats du niveau ".concat(Integer.toString(niveau)));
			while ( (str=bread.readLine() )!= null ){
				System.out.println(str);
			}
			bread.close();
			fread.close();
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}