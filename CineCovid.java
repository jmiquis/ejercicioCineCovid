package ejerciciosRepasoSemanaSanta;

public class CineCovid {
	
		private static Espectador covidFree=new Espectador("Covidfree","Asiento",0,0);
		private Butaca [][] sala;
		public int contadorEspectadores=0;
		private Pelicula proyectada;

		//contructor
		public CineCovid(int numeroFilas,int numeroColumnas, Pelicula proyectada) {
			sala=new Butaca [numeroFilas][numeroColumnas];
			this.proyectada=proyectada;
			
			//asigna numeros y letras a las butacas
			for(int i=0;i<numeroFilas;i++) {
				for (int j=0;j<numeroColumnas;j++) {
				 sala[i][j]=new Butaca(sala.length-i,(char)('A'+j));
				}
			}
		}
		public  int devuelveLongitud() {
			return sala[0].length;
		}
		
		public  Pelicula getProyectada() {
			return proyectada;
		}
		public void setProyectada(Pelicula proyectada) {
			this.proyectada = proyectada;
		}
		
		//check si el cliente que compra las entradas tiene dinero y edad suficientes. No chequea a los acompañantes.
		public  boolean comprobarComprador ( Espectador[]E) {
			Espectador comprador=E[0];
			return comprador.getDinero()>=(proyectada.getPrecio()*E.length) && comprador.getEdad()>=proyectada.getEdadMinima();
		}
		
		
		
		@Override
		public String toString() {
			return "CineCovid [devuelveLongitud()=" + devuelveLongitud() + ", getProyectada()=" + getProyectada()
					+ ", getRecaudacion()=" + getRecaudacion() + ", toString()=" + super.toString() + "]";
		}
		//chequea si el grupo cabe en la fila y si puede haber separacion entre los grupos. Ademas si se cumple todo coloca los sitios libres
		public  boolean comprobarDisponibilidad(Espectador[] grupo, int numeroFila, int numeroColumna ) {
			for(int i=numeroColumna;i<grupo.length;i++) {
				if(!(sala[numeroFila][i].getEspectador()==null || sala[numeroFila][i].getEspectador().equals(covidFree))) {
					return false;
				}
			}
			//si el grupo ocupa toda la fila no hay que hacer comprobaciones
			if(grupo.length==sala[numeroFila].length) {
				return true;
			}
			//si la primera posicion esta a la izquierda del todo de la fila
			if(numeroColumna==0) {
			
					if(!(sala[numeroFila][numeroColumna+(grupo.length)].getEspectador()==null ||sala[numeroFila][numeroColumna+(grupo.length)].getEspectador().equals(covidFree))){
						return false;	
					}
					else {
						sala[numeroFila][numeroColumna+(grupo.length)].setEspectador(covidFree);
						this.contadorEspectadores++;
						return true;
					}
			}
			
			else {
				//si la ultima posicion del grupo esta la ultima de la fila
				if(numeroColumna+(grupo.length)==sala[numeroFila].length) {
					if(!(sala[numeroFila][numeroColumna-1].getEspectador()==null || sala[numeroFila][numeroColumna-1].getEspectador().equals(covidFree))) {
						return false;
					}
					else {
						sala[numeroFila][numeroColumna-1].setEspectador(covidFree);
						contadorEspectadores++;
						return true;
					}
				}
					//si el grupo ocupa localidades con libres a los lados
					else {
						if(!(sala[numeroFila][numeroColumna-1].getEspectador()==null || sala[numeroFila][numeroColumna-1].getEspectador().equals(covidFree)||
								sala[numeroFila][numeroColumna+(grupo.length)].getEspectador()==null || sala[numeroFila][numeroColumna+(grupo.length)].getEspectador().equals(covidFree) )) {
							return false;
						}
						else {
							sala[numeroFila][numeroColumna-1].setEspectador(covidFree);
							sala[numeroFila][numeroColumna+(grupo.length)].setEspectador(covidFree);
							contadorEspectadores+=2;
							return true;
						}
					}
				}
		}
			
		/*genera aleatoriamete posiciones hasta que se cumplen las condiciones y la seleccionada
		 deja de ser null */
		public  Butaca seleccionarAleatorio(Espectador[] grupo) {
			int filas;
			char columnas;
			do {
				filas=generaEnteroAleatorio(1,sala[0].length);
				columnas=(char)generaEnteroAleatorio(65, sala.length+64);
				venderAsiento(filas,columnas,grupo);
			}while(sala[seleccionarFila(filas)][columnas-65].getEspectador()==null);
			return sala[seleccionarFila(filas)][columnas-65];
		}
		

		public void informeSala() {
		for (int i=0;i<this.sala[0].length;i++){
			   System.out.printf("%2c", 'A'+i);	
			}
		 System.out.println("");
		for (int i=0;i<this.sala.length;i++) {
			for(int j=0;j<=sala[0].length;j++) {
				if(j==sala[0].length) {
					System.out.print(" "+(sala[i][j-1].getNumero()));
					System.out.println(" ");
				}
					else {
				
				if(sala[i][j].getEspectador()==null) {
					System.out.print(" O");
				}
				else {
					if(sala[i][j].getEspectador().equals(covidFree)) {
						System.out.print(" -");
					}
					else {
						System.out.print(" X");
				
				System.out.print("");
					}
						}
				}
				}
			}
				}
				
		
		public int getRecaudacion() {
			return contadorEspectadores*proyectada.getPrecio();
		}
		
		private static int generaEnteroAleatorio(int minimo, int maximo)
		{
			return (int)Math.floor(Math.random()* (minimo-(maximo+1))+(maximo+1));
		}
		
		//Devuelve el numero maximo de asientos posibles en todas las filas
		public int contadorDeLibres() {
			int aux=0;
			int [] libres=new int[sala.length];
			for(int i=0;i<this.sala[0].length;i++) {
				int contador=0;
				for (int j=0;j<this.sala.length;j++) {
				contador=(sala[i][j].getEspectador()==null)?contador+1:contador;
				}
				libres[i]=contador;
				aux=(libres[i]>aux)?libres[i]:aux;			}
			return aux;
		}
	
		//vende el asiento mas a la izquierda de la fila al comprador. El resto se coloca a la derecha 
		public boolean venderAsiento(int numeroFila, char letraColumna, Espectador [] grupo) {
				int numColumna=letraColumna-65;
				int numFila=seleccionarFila(numeroFila);
				if(comprobarComprador(grupo) && grupo.length<=
						contadorDeLibres()&& comprobarLongitudGrupo(numColumna,grupo.length,devuelveLongitud())&& comprobarDisponibilidad(grupo,numFila,numColumna)) {
					
				for (int i=0;i<grupo.length;i++) {
						sala[numFila][numColumna+i].setEspectador(grupo[i]);
						contadorEspectadores++;
				}
				grupo[0].setDinero(grupo[0].getDinero()-(proyectada.getPrecio()*grupo.length));
				return true;
				
				}
				else
					System.out.println("No se ha podido realizar la operacion");
				return false;
				
		}
		//iguala la fila que aporta el usuario a la que tiene en la matriz
		public int seleccionarFila(int numeroFila) {
			for(int i=0;i<this.sala.length;i++) {
				if(sala[i][0].getNumero()==numeroFila) {
					return i;
				}
			}
			return -1;
		}
		public boolean comprobarLongitudGrupo(int columna, int longitudGrupo,int longitudFila) {
			return longitudFila>=(columna+longitudGrupo);
		}
	
}
