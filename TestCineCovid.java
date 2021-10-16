package ejerciciosRepasoSemanaSanta;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TestCineCovid {
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) throws SinDineroExcepcion, EdadNopermitidaException {
		int respuesta=0;
		Pelicula [] cartelera=cartelera();
		CineCovid [] multicine=multicine(cartelera);
		do {
		CineCovid salaSeleccionada=menuBienvenida(multicine);
		Espectador[] grupo=generarGrupo(salaSeleccionada);
		venderEntradas(grupo,salaSeleccionada);
		System.out.println("Hay más clientes? pulse 2 para finalizar, 1 para seguir introduciendo clientes");
		respuesta=sc.nextInt();
		}while (respuesta!=2);
		System.out.println("Gracias.Adios!");
		
	}
	public static  Pelicula [] cartelera(){
		Pelicula [] cartelera=new Pelicula[4];
		cartelera[0]=new Pelicula("Papa piquillo", 120,10,Genero.COMEDIA,1);
		cartelera[1]=new Pelicula("El niño del pijmama de rayas", 180,16,Genero.DRAMA,9);
		cartelera[2]=new Pelicula("El principe de Zamunda", 190,10,Genero.COMEDIA,10);
		cartelera[3]=new Pelicula("Dracula", 120,18,Genero.TERROR,18);
		return cartelera;
	}
	
	public static  CineCovid menuBienvenida(CineCovid [] multicine) {
		System.out.println("   Bienvenido \n --------------"
				+ "\n Actualmente tenemos en cartelera los siguientes titulos:"
				+ "\n ");
			for(int i=0;i<multicine.length;i++) {
				System.out.println("SALA "+(i+1)+" -------- "+multicine[i].getProyectada().getTitulo()+" con un aforo de "+multicine[i].contadorEspectadores);
			}
			System.out.println("\n seleccione su pelicula ");
			int respuesta=sc.nextInt();
			while(!(respuesta>0 && respuesta<=multicine.length)) {
				System.out.println(" Solo tenemos "+multicine.length+" peliculas en cartelea.");
				System.out.println("\n seleccione su pelicula ");
				respuesta=sc.nextInt();
			}
			return multicine[respuesta-1];
	}
	public static CineCovid [] multicine(Pelicula [] cartelera) {
		
		CineCovid [] multicine=new CineCovid[cartelera.length];
		for(int i=0;i<multicine.length;i++) {
			multicine[i]=new CineCovid(10,10,cartelera[i]);
		}
		return multicine;
		}
	public static Espectador [] generarGrupo(CineCovid seleccion) throws SinDineroExcepcion, EdadNopermitidaException {
		
		System.out.println("Ha seleccionado "+seleccion.getProyectada().getTitulo());
		System.out.println("¿Cuantas localidades desea comprar?");
		int numeroLocalidades=sc.nextInt();
		while(!(numeroLocalidades>0 && numeroLocalidades<=seleccion.contadorDeLibres() && numeroLocalidades<=seleccion.devuelveLongitud())) {
			 System.out.println("Debido a la pandemia no podemos albergar un grupo de ese tamaño en esta sala. Por favor, seleccione un grupo mas pequeño");	
			 numeroLocalidades=sc.nextInt();
			}
		System.out.println("Inserte: nombre, apellido, dinero y edad, separado por espacios y pulse intro");
		String nombre=sc.next();
		
		String apellido=sc.next();
		int dinero=sc.nextInt();
			if(!(dinero>=(seleccion.getProyectada().getPrecio()*numeroLocalidades))) {
						throw new SinDineroExcepcion((seleccion.getProyectada().getPrecio()*numeroLocalidades),dinero);
					}
		int edad=sc.nextInt();
			if(!(edad>=seleccion.getProyectada().getEdadMinima())) {
				throw new EdadNopermitidaException(edad);
			}
		Espectador []grupo=new Espectador[numeroLocalidades];
		grupo[0]=new Espectador(nombre,apellido,dinero,edad);
		for(int i=1;i<grupo.length;i++) {
			grupo[i]=new Espectador("Acompañante","numero "+i,0,0);
		}
		return grupo;
	}
	public static void venderEntradas(Espectador[]grupo, CineCovid seleccion) {
		int respuesta;
		boolean correcto = true;
		
		int fila;
		char columna = 0;
		System.out.println("Este es el aspecto actual de la sala. \n \n");
		seleccion.informeSala();
		System.out.println("\n ¿Desea elegir las butacas--pulse 1-- o dejar que lo hagamos nosotros--pulse 2?");
		respuesta=sc.nextInt();
		while (respuesta!=1 || respuesta!=2) {
			System.out.println("solo 1 o 2 por favor");
			respuesta=sc.nextInt();
		}
			
		
		switch(respuesta) {
			case 1:
				correcto=true;
				do {
				System.out.println("Teclee el numero de la fila y pulse enter");
				fila=sc.nextInt();
				System.out.println("Teclee la letra de la butaca mas a la izquierda del grupo de butacas seleccionadas");
				columna=Character.toUpperCase(sc.next().charAt(0));     
				correcto=seleccion.venderAsiento(fila, columna, grupo);
				}while(!correcto);
				generarTicket(fila, columna,seleccion,grupo);
				break;
			case 2:
				correcto=true;
				Butaca aleatoria;
				do {
				aleatoria=seleccion.seleccionarAleatorio(grupo);
				int columnas=(int)aleatoria.getLetra()-65;
				correcto=seleccion.comprobarLongitudGrupo(columnas, grupo.length,seleccion.devuelveLongitud());
				}while(!correcto);
				generarTicket(aleatoria.getNumero(),aleatoria.getLetra(),seleccion,grupo);
				
				break;
			default:System.out.println("Las opciones disponibles son 1 o 2");
			correcto=false;
		}
	}
	public static void generarTicket(int fila, char columna, CineCovid seleccion, Espectador [] grupo) {
		System.out.println(" Precio de la compra -------- "+(seleccion.getProyectada().getPrecio()*grupo.length)+" €");
		System.out.println("Recuerde que sus localidades son: \n");
		for(int i=0;i<grupo.length;i++) {
			System.out.print((fila)+" "+(char)(columna+i)+", \n");
		}
		System.out.println("\n");
		seleccion.informeSala();
	}
	
	}

	


