package es.iesfranciscodelosrios.BookMaker.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUnit {

	private static EntityManagerFactory emf;
	private static final String PUN = "aplicacionMariaDB";

	public static EntityManagerFactory getInstance(String name) {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory(name);
		}
		return emf;
	}

	public static EntityManagerFactory getInstance() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory(PUN);
		}
		return emf;
	}

}
