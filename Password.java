package ejerciciosRepasoSemanaSanta;
import java.util.ArrayList;
import java.util.Scanner;
public class Password {
	private String contraseña;
	static Scanner sc=new Scanner(System.in);
	static ArrayList<String> tablaDeContraseñas = new ArrayList<String>();	
	
	
	public Password(String palabra) {
		while(!esFuerte(palabra)) {
			tablaDeContraseñas.add(palabra);
			System.out.println("Escriba de nuevo la contraseña cumpliendo las normas de seguridad");
			palabra=sc.next();
		}
		this.contraseña=palabra;
	}
	public Password() {
		this.contraseña=generarPasword();
	}

	private String generarPasword() {
		String pass;
		do {
			pass="";
			for(int i=0;i<generaEnteroAleatorio(10,15);i++) {
				pass+=(char)generaEnteroAleatorio(33, 122);
			}
			tablaDeContraseñas.add(pass);
		}while (!esFuerte(pass));
		return pass;
	}
	
	public static boolean esFuerte(String palabra) {
		int[]comprobaciones=new int[4];
		if(palabra.length()<8) {
			System.out.println("La longitud debe ser igual o mayor a 8 caracteres");
			return false;
		}
		else {
			for(int i=0;i<palabra.length();i++) {
				comprobaciones[0]=(Character.isUpperCase(palabra.charAt(i)))?comprobaciones[0]+1:comprobaciones[0];
				comprobaciones[1]=(Character.isDigit(palabra.charAt(i)))?comprobaciones[1]+1:comprobaciones[1];
				comprobaciones[2]=(Character.isLowerCase(palabra.charAt(i)))?comprobaciones[2]+1:comprobaciones[2];
				comprobaciones[3]=(!Character.isAlphabetic(palabra.charAt(i)))?comprobaciones[3]+1:comprobaciones[3];
			}
		for (int i : comprobaciones) {
			if(i==0) {
				return false;
			}
		}
		}
		return true;
	}
	
	public static int generaEnteroAleatorio(int minimo, int maximo)
	{
		return (int)Math.floor(Math.random()* (minimo-(maximo+1))+(maximo+1));
	}
}