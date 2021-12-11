package es.iesfranciscodelosrios.BookMaker;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.iesfranciscodelosrios.BookMaker.model.DAO.DAOException;
import es.iesfranciscodelosrios.BookMaker.model.DAO.UserDAO;
import es.iesfranciscodelosrios.BookMaker.model.DO.Book;
import es.iesfranciscodelosrios.BookMaker.model.DO.User;
import es.iesfranciscodelosrios.BookMaker.model.DO.Character;

public class mainTest {
	
	private static EntityManager em;
	private static EntityManagerFactory emf;

	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("aplicacionH2");
		em = emf.createEntityManager();

		em.getTransaction().begin();
		///////////////////////////////////////////////////////////////////////////
		//LOS CAMBIOS SON EFECTIVOS
		User user1 = new User("Antonio", "1234", "prueba@prueba.com");
		List<Character> characters = new ArrayList<Character>();
		Book book1 = new Book("Quijote", "Un jambo que va to puesteo", "Fantasía", user1, null);
		List<Book> books = new ArrayList<Book>();
		Character character1 = new Character("Sancho","Señor con panza","Notas",books);
		characters.add(character1);
		
		book1.setCharacters(characters);
		books.add(book1);
		user1.setBooks(books);
		
		try {
			new UserDAO().save(user1);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		//em.persist(user1);
		
		User aux = em.find(User.class, 1L);
		System.out.println(user1);
		System.out.println(aux);
		System.out.println(book1);
		
		
		
		//FINAL DE LOS CAMBIOS		
		///////////////////////////////////////////////////////////////////////////
		em.getTransaction().commit();
		
	}

}
