package gameComponents.movimientos;

import monitor.Tablero;

public class Adelante extends Movimiento{

	public Adelante(Tablero t) {
		super(t);
	}

	@Override
	public void mover() throws InterruptedException {
		if(this.getJugador().getEquipo() == "E1"){
			this.getTablero().moverAdelante(this.getJugador(), 1);
		}
		else{
			this.getTablero().moverAdelante(this.getJugador(), -1);
		}
	}

}
