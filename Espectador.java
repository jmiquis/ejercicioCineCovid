package ejerciciosRepasoSemanaSanta;

public class Espectador {
	private String nombre;
	private String apellido;
	private int dinero;
	private int edad;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDinero() {
		return dinero;
	}

	public void setDinero(int dinero) {
		this.dinero = dinero;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Espectador [nombre=" + nombre + ", apellido=" + apellido + ", dinero=" + dinero + ", edad=" + edad
				+ "]";
	}
	public static Espectador [] crearGrupo(int numeroEspectadores, Espectador comprador){
		Espectador [] grupoDeEspectadores=new Espectador[numeroEspectadores];
		grupoDeEspectadores[0]=comprador;
		int contador=1;
		for (int i=1;i<grupoDeEspectadores.length;i++) {
			grupoDeEspectadores[i]=new Espectador( "acompañante numero"+contador," de "+grupoDeEspectadores[0].getNombre(),
			grupoDeEspectadores[0].getDinero(),grupoDeEspectadores[0].getEdad());
			contador++;
			}
		return grupoDeEspectadores;
		}

	public Espectador(String nombre, String apellido, int dinero, int edad) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dinero = dinero;
		this.edad = edad;
	}

}
