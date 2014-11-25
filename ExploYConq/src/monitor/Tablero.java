package monitor;

import java.util.ArrayList;


import gameComponents.Jugador;
import gameComponents.Tesoro;
import gameComponents.movimientos.Adelante;
import gameComponents.movimientos.Derecha;
import gameComponents.movimientos.Izquierda;
import gameComponents.movimientos.Movimiento;
import auxiliar.Celda;
import auxiliar.Matriz;
import auxiliar.NumeroAleatorio;

public class Tablero {

	private Matriz matriz;
	private int alto;
	private int ancho;
	private int cantTesorosFaltantesDeE1;
	private int cantTesorosFaltantesDeE2;
	
	public int getCantTesorosFaltantesDeE1() {
		return cantTesorosFaltantesDeE1;
	}

	public void setCantTesorosFaltantesDeE1(int cantTesorosE1) {
		this.cantTesorosFaltantesDeE1 = cantTesorosE1;
	}

	public int getCantTesorosFaltantesDeE2() {
		return cantTesorosFaltantesDeE2;
	}

	public void setCantTesorosFaltantesDeE2(int cantTesorosE2) {
		this.cantTesorosFaltantesDeE2 = cantTesorosE2;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public Matriz getMatriz() {
		return matriz;
	}

	public void setMatriz(Matriz matriz) {
		this.matriz = matriz;
	}
	
	public Tablero(int alto, int ancho, int cantTesoros){
		this.setMatriz(new Matriz(alto, ancho));
		this.setAlto(alto);
		this.setAncho(ancho);
		this.generarTesoros(cantTesoros);
	}
	
	private void generarTesoros(int cantTesoros){
		int aux = cantTesoros/2;
		this.setCantTesorosFaltantesDeE1(aux);
		this.setCantTesorosFaltantesDeE2(aux);
		this.generarTesoros(aux,"E1");
		this.generarTesoros(aux,"E2");	
	} 
	
	private void generarTesoros(int cantTesoros, String equipo){
		int contador = 0;
		while(contador < cantTesoros){
			int x1 = NumeroAleatorio.random(1, this.getAncho()-1, equipo);
			int y1 = NumeroAleatorio.random(1, this.getAlto()-1, equipo);
			if(!this.getMatriz().get(x1, y1).isOcupada()){
				this.getMatriz().setIn(x1,y1, new Celda(new Tesoro(equipo),x1,y1));
				contador++;
			}
		}
	}
	
	public synchronized void moverAdelante(Jugador jugador, int i) throws InterruptedException {
		if(this.terminoJuego()){System.out.println("Termino el Juego gano"+this.ganador());return;}
		if(jugador.getCeldaActual().getY() == 0 && i == -1){
			System.out.println(jugador.getIdentificacion()+ ": Esta en el borde");
			return;
		}
		if(jugador.getCeldaActual().getY() == this.getAlto()-1 && i == 1){
			System.out.println(jugador.getIdentificacion()+ ": Esta en el borde");
			return;
		}
		while(!this.puedoMovermeAdelante(jugador, i)){
			wait();
		}
		this.ejecutarMovimiento(jugador, this.getMatriz().get(jugador.getCeldaActual().getX(), jugador.getCeldaActual().getY()+i));
	}

	private boolean puedoMovermeAdelante(Jugador jugador, int i) {
		Celda celda = jugador.getCeldaActual();
		Celda celdaAux = this.getMatriz().get(celda.getX(), celda.getY()- i);
		if(celdaAux.isOcupada()){
			if(celdaAux.getObjeto().esJugadorDeEquipo(jugador.getEquipo())){return true;}
		}
		return this.companieroHorizontal(jugador, celda, 1) || this.companieroHorizontal(jugador, celda, -1) || 
				this.companieroHorizontal(jugador, celdaAux, 1) || this.companieroHorizontal(jugador, celdaAux, -1);
	}

	private boolean companieroHorizontal(Jugador jugador, Celda celda, int i) {
		if(celda.getX() == 0 && i == -1){
			return false;
		}
		if(jugador.getCeldaActual().getX() == this.getAncho()-1 && i == 1){
			return false;
		}
		
		Celda celdaAux = this.getMatriz().get(celda.getX() + i, celda.getY());
		if(celdaAux.isOcupada()){
			if(celdaAux.getObjeto().esJugadorDeEquipo(jugador.getEquipo())){return true;}
		}
		return false;
	}

	public synchronized void moverHorizontal(Jugador jugador, int i) throws InterruptedException {
		if(this.terminoJuego()){System.out.println("Termino el Juego gano"+this.ganador());return;}
		if(jugador.getCeldaActual().getX() == 0 && i == -1){
			System.out.println(jugador.getIdentificacion()+ ": Esta en el borde");
			return;
		}
		if(jugador.getCeldaActual().getX() == this.getAncho()-1 && i == 1){
			System.out.println(jugador.getIdentificacion()+ ": Esta en el borde");
			return;
		}
		
		while(!this.puedoMovermeHorizontal(jugador.getCeldaActual(), i)){
			this.wait();
			}
		this.ejecutarMovimiento(jugador, this.getMatriz().get(jugador.getCeldaActual().getX()+ i, jugador.getCeldaActual().getY()));	
		
	}
	
	private boolean terminoJuego(){
		return this.getCantTesorosFaltantesDeE1() == 0 || this.getCantTesorosFaltantesDeE2() == 0;
	}
	
	private String ganador(){
		if(this.getCantTesorosFaltantesDeE1() == 0){
			return "E1";
		}
		return "E2";
	}
	
	private boolean puedoMovermeHorizontal(Celda celda, int i) {
		Celda celdaAux = this.getMatriz().get(celda.getX()+ i, celda.getY());
		if(celdaAux.isOcupada()){
			if(celdaAux.getObjeto().getIdentificacion().contains("T")){return true;}
			else{return false;}
		}
		return true;
	}

	public void ejecutarMovimiento(Jugador jugador, Celda celdaNueva){
		if(celdaNueva.isOcupada()){
			if(celdaNueva.getObjeto().getEquipo() == jugador.getEquipo()){return;}
			else{
				this.conquistarTesoro(jugador.getEquipo());
			}
		} 	
		jugador.getCeldaActual().liberar();
		celdaNueva.ocupar(jugador);
		jugador.setCeldaActual(celdaNueva);
		this.notifyAll();
		this.getMatriz().mostrarMatriz();
		
	}

	private void conquistarTesoro(String equipo) {
		if(equipo == "E1"){
			this.setCantTesorosFaltantesDeE1(this.getCantTesorosFaltantesDeE1()-1);
		}
		else{
			this.setCantTesorosFaltantesDeE2(this.getCantTesorosFaltantesDeE2()-1);
		}
		System.out.println("Conquisto tesoro el equipo :"+ equipo);
		
	}
	
	
	public static void main(String[] args) {
		Tablero t = new Tablero(11, 11, 2);
		
		ArrayList<Movimiento> movs1 = new ArrayList<Movimiento>();
		ArrayList<Movimiento> movs2 = new ArrayList<Movimiento>();
		ArrayList<Movimiento> movs3 = new ArrayList<Movimiento>();
		ArrayList<Movimiento> movs4 = new ArrayList<Movimiento>();
		
		
		movs1.add(new Adelante(t));
		movs1.add(new Derecha(t));
		movs1.add(new Derecha(t));
		movs1.add(new Derecha(t));
		movs1.add(new Derecha(t));
		movs1.add(new Derecha(t));
		movs1.add(new Derecha(t));
		
		movs2.add(new Izquierda(t));
		movs2.add(new Izquierda(t));
		movs2.add(new Izquierda(t));
		movs2.add(new Izquierda(t));
		movs2.add(new Izquierda(t));
		movs2.add(new Izquierda(t));
		movs2.add(new Izquierda(t));
		movs2.add(new Izquierda(t));
		
	//	movs3.add(new Adelante(t));
		movs3.add(new Izquierda(t));
		movs3.add(new Izquierda(t));
		movs3.add(new Izquierda(t));
		movs3.add(new Izquierda(t));
		movs3.add(new Izquierda(t));
		
		
		//movs4.add(new Adelante(t));
		movs4.add(new Derecha(t));
		movs4.add(new Derecha(t));
		movs4.add(new Derecha(t));
		movs4.add(new Derecha(t));
		movs4.add(new Derecha(t));
		movs4.add(new Derecha(t));
		
		Jugador jugador1 = new Jugador("Ju1","E1", movs1);
		Jugador jugador2 = new Jugador("Ju2","E1", movs2);
		Jugador jugador3 = new Jugador("Ju3","E2", movs3);
		Jugador jugador4 = new Jugador("Ju4","E2", movs4);
		
		t.getMatriz().setIn(2, 2, new Celda(jugador1,2,2));
		System.out.println("Jugador 1 = POSACTUAL (2,2)");
		t.getMatriz().setIn(3, 2, new Celda(jugador2,3,2));
		System.out.println("Jugador 2 = POSACTUAL (3,1)");
		t.getMatriz().setIn(1, 7, new Celda(jugador3,1,7));
		System.out.println("Jugador 3 = POSACTUAL (1,7)");
		t.getMatriz().setIn(7, 9, new Celda(jugador4,7,9));
		System.out.println("Jugador 4 = POSACTUAL (7,9)");
		
		t.getMatriz().mostrarMatriz();
		jugador1.start();
		jugador2.start();
		jugador3.start();
		jugador4.start();


	}
	
//	public static void main(String[] args) {
//		Tablero t = new Tablero(11, 11, 6);
//		t.getMatriz().mostrarMatriz();
//	}
	
}

