package ejerciciosRepasoSemanaSanta;

public class EdadNopermitidaException extends Exception{
	public EdadNopermitidaException (int edad) {
		System.out.println("La edad minima para ver esta pelicula es de "+edad+". Por favor, vuelva con alguien que sea mas mayor.");
	}
}
