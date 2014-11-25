package gameComponents.movimientos;

import monitor.Tablero;

public class Derecha extends Movimiento{

	public Derecha(Tablero t) {
		super(t);
	}

	@Override
	public void mover() throws InterruptedException {
		if(this.getJugador().getEquipo() == "E1"){
			this.getTablero().moverHorizontal(this.getJugador(), -1);
		}
		else{
			this.getTablero().moverHorizontal(this.getJugador(), 1);
		}
		
	}

}
