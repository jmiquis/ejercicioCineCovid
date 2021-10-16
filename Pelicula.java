package ejerciciosRepasoSemanaSanta;

public class Pelicula {

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public int getEdadMinima() {
		return edadMinima;
	}
	public void setEdadMinima(int edadMinima) {
		this.edadMinima = edadMinima;
	}
	public Genero getGeneroPelicula() {
		return generoPelicula;
	}
	public void setGeneroPelicula(Genero generoPelicula) {
		this.generoPelicula = generoPelicula;
	}
	@Override
	public String toString() {
		return "Pelicula [titulo=" + titulo + ", duracion=" + duracion + ", edadMinima=" + edadMinima
				+ ", generoPelicula=" + generoPelicula + "]";
	}
	public Pelicula(String titulo, int duracion, int edadMinima, Genero generoPelicula, int precio) {
		super();
		this.titulo = titulo;
		this.duracion = duracion;
		this.edadMinima = edadMinima;
		this.generoPelicula = generoPelicula;
		this.precio=precio;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	//título, duración(minutos), edad mínima, genero.
	private String titulo;
	private int duracion;
	private int edadMinima;
	private Genero generoPelicula;
	private int precio;
	
}
