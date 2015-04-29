import java.io.*;
import java.util.Scanner;

public class Personnage {
	private int _x;
	private int _y;
	private int _dir;
	private Scanner _scan;
	
	// Constructeur
	public Personnage() {
		this.setX(0);
		this.setY(0);
		this.setDir(0);
		this._scan = new Scanner(System.in);
	}
	
	// Setters
	public void setX(int val) {
		this._x = val;
	}
	
	public void setY(int val) {
		this._y = val;
	}
	
	public void setDir(int val) {
		this._dir = val;
	}

	// Getters
	public int getX() {
		return this._x;
	}
	
	public int getY() {
		return this._y;
	}
	
	public int getDir() {
		return this._dir;
	}
	


	// Déplacement
	public void move(Jeu j) {		
		// On entre une direction
		System.out.println("Veuillez entrer une direction :");
		System.out.println("	8 - Haut");
		System.out.println("	4 - Gauche");
		System.out.println("	2 - Bas");
		System.out.println("	6 - Droite");

		
		int dir = this._scan.nextInt();
		while((dir != 2) && (dir != 4) && (dir != 6) && (dir != 8) ) {
			System.out.println("Valeur incorrecte, veuillez recommencer");
			dir = this._scan.nextInt();
		}
		
		// On mets la valeur entrée dans this._dir (si elle est valide)
		this.setDir(dir);
		
		// On se déplace en fonction de this._dir
		switch(this._dir) {
		case 8:
			switch (j.getTab(this._x, this._y-1)) {
			case '=':
				break;
				
			case 'B':
				if ((j.getTab(this._x, this._y-2) == ' ') || (j.getTab(this._x, this._y-2) == 'O')) {
					
					if (j.isStorage(this._x, this._y-1)) {
						j.setPlateau(this._x, this._y-1, 'O');
						j.setPlateau(this._x, this._y-2, 'B');
						this._y--;

					}
					else {
						j.setPlateau(this._x, this._y-1, ' ');
						j.setPlateau(this._x, this._y-2, 'B');
						this._y--;
					}
				}
				else
					break;
				break;
				
				default:
					this._y--;
			}
			break;
			
		case 4:
			switch (j.getTab(this._x-1, this._y)) { 
			case '=':
				break;
				
			case 'B':
				if ((j.getTab(this._x-2, this._y) == ' ') || (j.getTab(this._x-2, this._y) == 'O')) {
					
					if (j.isStorage(this._x-1, this._y)) {
						j.setPlateau(this._x-1, this._y, 'O');
						j.setPlateau(this._x-2, this._y, 'B');
						this._x--;

					}
					else {
						j.setPlateau(this._x-1, this._y, ' ');
						j.setPlateau(this._x-2, this._y, 'B');
						this._x--;
					}
				}
				else
					break;
				break;
				
				default:
					this._x--;
			}
			break;
			
		case 2:
			switch (j.getTab(this._x, this._y+1)) {
			case '=':
				break;
				
			case 'B':
				if ((j.getTab(this._x, this._y+2) == ' ') || (j.getTab(this._x, this._y+2) == 'O')) {
					
					if (j.isStorage(this._x, this._y+1)) {
						j.setPlateau(this._x, this._y+1, 'O');
						j.setPlateau(this._x, this._y+2, 'B');
						this._y++;

					}
					else {
						j.setPlateau(this._x, this._y+1, ' ');
						j.setPlateau(this._x, this._y+2, 'B');
						this._y++;
					}
				}
				else
					break;
				break;
				
				default:
					this._y++;
			}
			break;
			
		case 6:
			switch (j.getTab(this._x+1, this._y)) {
			case '=':
				break;
				
			case 'B':
				if ((j.getTab(this._x+2, this._y) == ' ') || (j.getTab(this._x+2, this._y) == 'O')) {
					
					if (j.isStorage(this._x+1, this._y)) {
						j.setPlateau(this._x+1, this._y, 'O');
						j.setPlateau(this._x+2, this._y, 'B');
						this._x++;

					}
					else {
						j.setPlateau(this._x+1, this._y, ' ');
						j.setPlateau(this._x+2, this._y, 'B');
						this._x++;
					}
				}
				else
					break;
				break;
				
				default:
					this._x++;
			}
			break;
		}
	}
}

