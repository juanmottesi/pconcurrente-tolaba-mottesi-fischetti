package gameComponents;

import gameComponents.movimientos.Movimiento;

import java.util.List;

import auxiliar.Celda;
import auxiliar.Objeto;

public class Jugador extends Thread implements Objeto{

	private String id;
	private String equipo;
	private List<Movimiento> movimientos;
	private Celda celdaActual;
	
	public Jugador(String id, String equipo, List<Movimiento>movs){
		this.setMovimientos(movs);
		this.setId(id);
		this.setEquipo(equipo);
		for(Movimiento m : this.getMovimientos()){
			m.setJugador(this);
		}
	}
	
	public String getIdentificacion() {
		return this.getEquipo()+this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getEquipo() {
		return equipo;
	}
	
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}
	
	public List<Movimiento> getMovimientos() {
		return movimientos;
	}
	
	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
	
	public Celda getCeldaActual() {
		return celdaActual;
	}

	public void setCeldaActual(Celda celdaActual) {
		this.celdaActual = celdaActual;
	}
	
	public void run(){
		for(Movimiento m : movimientos){
			try {
				m.mover();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void setCelda(Celda celda) {
		this.setCeldaActual(celda);
		
	}

	@Override
	public boolean esJugadorDeEquipo(String equipo) {
		return this.getEquipo() == equipo;
	}


}
