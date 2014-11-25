package gameComponents.movimientos;

import monitor.Tablero;

public class Izquierda extends Movimiento{

	public Izquierda(Tablero t) {
		super(t);
	}

	@Override
	public void mover() throws InterruptedException {
		if(this.getJugador().getEquipo() == "E1"){
			this.getTablero().moverHorizontal(this.getJugador(), 1);
		}
		else{
			this.getTablero().moverHorizontal(this.getJugador(), -1);
		}
	}
}
