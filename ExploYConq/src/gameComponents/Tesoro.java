package gameComponents;

import auxiliar.Celda;
import auxiliar.Objeto;

public class Tesoro implements Objeto{

	private String equipo;
	private Celda celda;
	
	public Tesoro(String equipo){
		this.setEquipo(equipo);
	}
	
	public String getIdentificacion() {
		return "T: "+this.getEquipo();
	}

	public String getEquipo() {
		return equipo;
	}
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	@Override
	public void setCelda(Celda celda) {
		this.celda= celda;		
	}
	
	public Celda getCelda() {
		return this.celda;
		
	}

	@Override
	public boolean esJugadorDeEquipo(String equipo) {
		return false;
	}
		
}
