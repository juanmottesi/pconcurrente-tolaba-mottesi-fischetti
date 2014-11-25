package gameComponents;

import auxiliar.Celda;
import auxiliar.Objeto;

public class Vacio implements Objeto {

	private Celda celdaActual;
	
	@Override
	public String getIdentificacion() {
		return "Vacio";
	}

	@Override
	public String getEquipo() {
		return "";
	}

	@Override
	public void setCelda(Celda celda) {
		this.setCeldaActual(celda);		
	}

	public Celda getCeldaActual() {
		return celdaActual;
	}

	public void setCeldaActual(Celda celdaActual) {
		this.celdaActual = celdaActual;
	}

	@Override
	public boolean esJugadorDeEquipo(String equipo) {
		return false;
	}

}
