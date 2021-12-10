package es.iesfranciscodelosrios.BookMaker.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Utils {
	private static final String PUN="aplicacionMariaDB";
	private static EntityManagerFactory emf;
	
	public static EntityManagerFactory getInstance(String name) {
		if(emf==null) {
			emf=Persistence.createEntityManagerFactory(name);
		}
		return emf;
	}
	
	public static EntityManagerFactory getInstance() {
		if(emf==null) {
			emf=Persistence.createEntityManagerFactory(PUN);
		}
		return emf;
	}
}
