package inicio;

import java.security.MessageDigest;

public class TestCripto {

	public static void main(String[] args) {
		// 1era opcion con metodos static
		String palabra="Indra";
		
		try {
			encriptar(palabra);
			System.out.println("encriptando .....");
			System.out.println(encriptar(palabra));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 2da opcion sin metodos static
		TestCripto test=new TestCripto();
		
		
		try {
			System.out.println("encriptando .....");
			System.out.println(test.encriptar1(palabra));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String miHash(String clear) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");// message digest

		byte[] b = md.digest(clear.getBytes());
		int size = b.length;
		StringBuffer h = new StringBuffer(size);
		for (int i = 0; i < size; i++) {
			int u = b[i] & 255; // unsigned conversion
			if (u < 16) {
				h.append("0" + Integer.toHexString(u));
			} else {
				h.append(Integer.toHexString(u));
			}
		}
		return h.toString();
	}
	
	public static String encriptar(String palabra) throws Exception {
		String cripto="";		
	
		try {
			cripto=miHash(palabra);
		} catch (Exception e) {
			throw new Error("Error al encriptar la palabra");
		}
				
		return cripto;
	}
	
	// bloque NO ESTATICO
	private  String miHash1(String clear) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");// message digest

		byte[] b = md.digest(clear.getBytes());
		int size = b.length;
		StringBuffer h = new StringBuffer(size);
		for (int i = 0; i < size; i++) {
			int u = b[i] & 255; // unsigned conversion
			if (u < 16) {
				h.append("0" + Integer.toHexString(u));
			} else {
				h.append(Integer.toHexString(u));
			}
		}
		return h.toString();
	}
	
	public  String encriptar1(String palabra) throws Exception {
		String cripto="";		
	
		try {
			cripto=miHash1(palabra);
		} catch (Exception e) {
			throw new Error("Error al encriptar la palabra");
		}
				
		return cripto;
	}
}