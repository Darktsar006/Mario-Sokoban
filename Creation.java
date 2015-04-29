import java.io.*;
import java.util.Scanner;

public class Creation {
	private int _x;
	private int _y;
	private int _nbBox;
	private char[][] plateau;
	private Scanner _scan;
	
	public Creation() {
		String str;
		// Ouverture du scanner pour récupérer les infos
		this._scan = new Scanner(System.in);
		
		// Affectation de x, y et nombre de box
		System.out.println("Veuillez entrer le nombre de ligne voulues");
		this._x = this._scan.nextInt();
		System.out.println("Veuillez entrer le nombre de colonnes voulues");
		this._y = this._scan.nextInt();
		System.out.println("Veuillez entrer le nombre de boites voulues");
		this._nbBox = this._scan.nextInt();
		
		System.out.print("\n\n");
		// Initialisation du tableau de char
		this.plateau = new char[this._x][this._y];
		
		// Mise de murs aux extrémités de la map
		for(int j=0;j<this._y;j++) {
			for(int i=0;i<this._x;i++) {
				if((i == 0) || (j == 0) || (i == this._x-1) || (j == this._y-1))
					this.plateau[i][j] = '=';
			}
		}
		System.out.println("Mise de murs aux extremites de la map");
		// Scan pour permettre de récupérer une ligne au prochain appel de scan
		this._scan.nextLine();
		System.out.println("---------------------------------------------------");
		System.out.println("Veuillez entrer le contenu du tableau :");
		System.out.println("	' ' : Espace vide");
		System.out.println("	'=' : Mur");
		System.out.println("	'X' : Départ du personnage");
		System.out.println("	'B' : Emplacement de départ des boites");
		System.out.println("	'O' : Emplacement de Stockage des boites");
		System.out.println("Rappel : Le premier et le dernier caractere d'une ligne sont des =");
		System.out.println("---------------------------------------------------");
		
		// Affichage du nombre de colonne pour aider l'utilisateur
		for(int j=0;j<this._y;j++) {
			System.out.print(j);
		}
		System.out.print("\n");
		
		int cptPlayer=0;
		int cptBox=0;
		int cptStorage=0;
		
		// Boucle de remplissage du plateau
		for (int i=1;i<this._x-1;i++) {
			// On entre une ligne qui sera traitée par la suite 
			str = this._scan.nextLine();

			// Traitement du string
			for(int j=1;j<this._y-1;j++) {					
					// Cas Joueur
					if ((str.charAt(j) == 'X') && (cptPlayer == 0)) {
							this.plateau[i][j] = str.charAt(j);
							cptPlayer=1;
					}
						
					// Cas Box
					else if ((str.charAt(j) == 'B') && (cptBox < this._nbBox)) {
						this.plateau[i][j] = str.charAt(j);
						cptBox++;	
					}
						
					// Cas Storage
					else if ((str.charAt(j) == 'O') && (cptStorage < this._nbBox)) {
						this.plateau[i][j] = str.charAt(j);
						cptStorage++;	
					}
						
					// Les autres cas
					else if ((str.charAt(j) == ' ') || (str.charAt(j) == '='))
						this.plateau[i][j] = str.charAt(j);
					else
						this.plateau[i][j] = ' ';
			}
		}

			
		// Affichage du tableau :
		System.out.print("\n\n");
		System.out.println("Voici le tableau tel qu'entre :");
		for (int i=0;i<this._x;i++) {
			for (int j=0;j<this._y;j++) {
				System.out.print(this.plateau[i][j]);
			}
			System.out.print("\n");
		}
			
	}

	public void EcritureFichier() {
	
		// On commence par récupérer premier numéro non utilisé de niveau
		int niveau = 1;
		String testfile = "maps\\level".concat(Integer.toString(niveau)).concat(".sok");
		File f = new File(testfile);
		while (f.exists() ) {
			niveau++;
			testfile = "maps\\level".concat(Integer.toString(niveau)).concat(".sok");
			f = new File(testfile);
		}
		
		try {
			System.out.println("Ecriture dans le fichier ".concat(testfile));
			FileWriter fwrite = new FileWriter(f, true);
			BufferedWriter bwrite = new BufferedWriter(fwrite);
			
			bwrite.write(Integer.toString(this._x));
			bwrite.write("\n");
			bwrite.write(Integer.toString(this._y));
			bwrite.write("\n");
			bwrite.write(Integer.toString(this._nbBox));
			bwrite.write("\n");
			for(int i=0;i<this._x;i++) {
				for (int j=0;j<this._y;j++) {
					bwrite.write(this.plateau[i][j]);
				}
				bwrite.write("\n");
			}
			bwrite.close();
			fwrite.close();
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}