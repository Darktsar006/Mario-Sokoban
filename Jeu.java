import java.io.*;

public class Jeu {
	private long _timer;
	private int _nbBox;	
	private int _x;
	private int _y;
	private char[][] plateau;
	private int[][] zonefin;
	
	// Constructeur
	public Jeu(int niveau, Personnage mario) {	
		// Initialisation des variables
		int x = 0;
		int y = 0;
		int st = 0;
		this._timer = System.currentTimeMillis();
		
		// Lecture de fichier
		String str = "maps\\level".concat(Integer.toString(niveau)).concat(".sok");
		try {
			File f = new File(str);
			FileReader fread = new FileReader(f);
			BufferedReader bread = new BufferedReader(fread);

		     // Récupération du nombre de ligne de la map
			String line = bread.readLine();
			this._x = Integer.parseInt(line);
			
			// Récupération du nombre de colonne de la map
			line = bread.readLine();
			this._y = Integer.parseInt(line);
			
			// Récupération du nombre de box de la map(et de Storage)
			line = bread.readLine();
			this._nbBox = Integer.parseInt(line);
			
			// Initialisation des tableaux
			this.plateau = new char[this._x][this._y];
			this.zonefin = new int[this._nbBox][2];
			
			// Remplissage du tableau de caractère
			int c = bread.read();			
			while (c != -1) {
				switch ((char) c) {
				case ' ':
					this.plateau[x][y] = (char) c;
					x++;
					if (x == this._x) {
						y++;
						x=0;
					}
					break;
				
				case '=':
					this.plateau[x][y] = (char) c;
					x++;
					if (x == this._x) {
						y++;
						x=0;
					}
					break;	
				
				case 'O':
					// Si c'est une zone de Stockage, on récupère les coordonnées dans un autre tab
					this.plateau[x][y] = (char) c;
					this.zonefin[st][0] = x;
					this.zonefin[st][1] = y;
					st++;
					x++;
					if (x == this._x) {
						y++;
						x=0;
					}
					break;
					
				case 'X':
					// Si c'est le perso, on donne la valeur vide et on récupère la pos 
					this.plateau[x][y] = ' ';
					mario.setX(x);
					mario.setY(y);
					x++;
					if (x == this._x) {
						y++;
						x=0;
					}
					break;
					
				case 'B':
					this.plateau[x][y] = 'B';
					x++;
					if (x == this._x) {
						y++;
						x=0;
					}
					break;
				}
				c = bread.read();
			}			
			bread.close();
			fread.close();
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Getters
	public long getTime() {
		return this._timer;
	}
	
	public char getTab(int x, int y) {
		return this.plateau[x][y];
	}
	
	// Teste si la case est une case de Storage ou pas
	public boolean isStorage(int x, int y) {
		for (int i = 0; i < this._nbBox; i++) {
			if ((this.zonefin[i][0] == x ) && (this.zonefin[i][1] == y))
				return true;
		}
		return false;
	}
	
	// Setters
	public void setPlateau(int x, int y, char c) {
		this.plateau[x][y] = c;
	}


	// Affichage du tableau
	public void affiche_tab(Personnage mario) {
		// On commence par effacer la console
		for (int i=0;i<5;i++){
			System.out.println();
		}
		
		// On dessine ensuite le tableau
		for (int j=0;j<this._y;j++) {
			for(int i=0;i<this._x;i++) {
				// On place mario si les coordonnées correspondent
				if ((j == mario.getY()) && (i == mario.getX())) {
					System.out.print("X");
				}
				
				// Sinon on écrit le contenu du tableau
				else {
				System.out.print(this.plateau[i][j]);
				}
			}
			System.out.print("\n");
		}
	}

	// Test de fin 
	public boolean Continuer() {
		int x = 0;
		for (int j = 0; j < this._y;j++) {
			for (int i=0;i<this._x;i++) {
				if ((this.plateau[i][j] == 'B') && (this.isStorage(i, j)))
						x++;
			}
		}
		if (x == this._nbBox) 
			return false;
		else
			return true;
	}

	// Ecriture résultat
	public void Result(int map, String duree) {
		String str = "maps\\level".concat(Integer.toString(map)).concat(".result");

		try {
			File f = new File(str);
			FileWriter fwrite = new FileWriter(f, true);
			BufferedWriter bwrite = new BufferedWriter(fwrite);
			
			bwrite.write(duree);
			bwrite.write("\n");
			bwrite.close();
			fwrite.close();
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}