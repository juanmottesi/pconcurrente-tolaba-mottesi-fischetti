package auxiliar;

import gameComponents.Tesoro;

public class Matriz {

	private Celda[][] matriz;

	public Matriz(int alto, int ancho) {
		this.setMatriz(new Celda[alto][ancho]);
		this.rellenar();
	}

	private void rellenar() {
		for (int x = 0; x < this.getMatriz().length; x++) {
			for (int y = 0; y < this.getMatriz()[x].length; y++) {
				this.getMatriz()[x][y] = new Celda(y,x);
			}
		}
	}

	public Celda[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(Celda[][] matriz) {
		this.matriz = matriz;
	}

	public void mostrarMatriz() {
		for (int x = 0; x < this.getMatriz().length; x++) {
			System.out.print("|");
			for (int y = 0; y < this.getMatriz()[x].length; y++) {
				System.out.print(this.getMatriz()[x][y].getObjeto().getIdentificacion());
				if (y != this.getMatriz()[x].length - 1)
					System.out.print("\t");
			}
			System.out.println("|");
		}
		System.out.println();
	}

	public Celda get(int x, int y) {
		return this.getMatriz()[y][x];
	}
	
	public void setIn(int x, int y, Celda c){
		this.getMatriz()[y][x] = c;
	}
	

	public static void main(String[] args) {
		Matriz m = new Matriz(5, 5);
		m.mostrarMatriz();
		m.setIn(0, 1, new Celda(new Tesoro("E1"), 0, 1));
		System.out.println("otra");
		m.mostrarMatriz();
	}
}
