package ejerciciosRepasoSemanaSanta;

public class SinDineroExcepcion extends Exception{
	public SinDineroExcepcion(int dineroNecesario, int dineroDelGrupo) {
		System.out.println("El precio de las entradas es de "+dineroNecesario+". Le faltan "+(dineroNecesario-dineroDelGrupo)*-1+" euros.");
	}
}
