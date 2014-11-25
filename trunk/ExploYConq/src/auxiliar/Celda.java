package auxiliar;

import gameComponents.Vacio;

public class Celda {
	private int x;
	private int y;
	private boolean ocupada;
	private Objeto objeto;
	
	public Celda(int x, int y){
		this.setOcupada(false);
		this.setObjeto(new Vacio());
		this.setX(x);
		this.setY(y);
	}
	public Celda(Objeto objeto, int x, int y){
		this.setOcupada(true);
		this.setObjeto(objeto);
		this.setX(x);
		this.setY(y);
		objeto.setCelda(this);
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}
	
	public void ocupar(Objeto obj){
		this.setOcupada(true);
		this.setObjeto(obj);
	}

	public Objeto getObjeto() {
		return objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}
	
	public void liberar() {
		this.setOcupada(false);
		this.setObjeto(new Vacio());		
	}
	
}
