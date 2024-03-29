package auxiliar;

import java.util.Random;

public class NumeroAleatorio {

	/**
	 * 
	 * @param min este numero si es un posible resultado
	 * @param max este numero no es una opcion como resultado.
	 * @return
	 */
	public static int random(int min, int max){
		int aux = new Random().nextInt(max);
		if(aux >= min){
			int medio = (int) ((max/2) + 0.5f);
			if(aux == medio){return random(min, max);}
			else{return aux;}
		}
		else{return random(min, max);}
	}
	
	
	public static int random(int min, int max, String equipo){
		int aux = new Random().nextInt(max);
		if(equipo == "E2"){
			if(aux >= min && aux < (max/2)){
				return aux;
			}
		}
		else{
			if(aux >= (max/2)+1.5f && aux < max){
				return aux;
			}
		}
		return random(min,max,equipo);
	}
}
