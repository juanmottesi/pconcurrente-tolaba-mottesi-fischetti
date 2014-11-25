package gameComponents.movimientos;

import gameComponents.Jugador;
import monitor.Tablero;

public abstract class Movimiento {

	private Jugador jugador;
	private Tablero tablero;

	public Movimiento(Tablero t){
		this.setTablero(t);
	}
	
	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}
	
	public abstract void mover() throws InterruptedException;
	
}
