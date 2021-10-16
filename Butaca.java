package ejerciciosRepasoSemanaSanta;

public class Butaca {
	

  //hola brorjas
	
	 private int numero;
	 private char letra;
	 private Espectador espectador;
	 
	 public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public char getLetra() {
		return letra;
	}
	public void setLetra(char letra) {
		this.letra = letra;
	}
	public Espectador getEspectador() {
		return espectador;
	}
	public void setEspectador(Espectador espectador) {
		this.espectador = espectador;
	}
	
	
	public Butaca(int numero, char letra) {
		super();
		this.numero = numero;
		this.letra = letra;
		this.espectador = null;
	}
}
