package es.iesfranciscodelosrios.BookMaker;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.iesfranciscodelosrios.BookMaker.model.DAO.ActDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.BookDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.ChapterDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.ChapterNoteDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.DAOException;
import es.iesfranciscodelosrios.BookMaker.model.DAO.GlobalNoteDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.ReminderDAO;
import es.iesfranciscodelosrios.BookMaker.model.DAO.UserDAO;
import es.iesfranciscodelosrios.BookMaker.model.DO.Act;
import es.iesfranciscodelosrios.BookMaker.model.DO.Book;
import es.iesfranciscodelosrios.BookMaker.model.DO.Chapter;
import es.iesfranciscodelosrios.BookMaker.model.DO.ChapterNote;
import es.iesfranciscodelosrios.BookMaker.model.DO.User;
import es.iesfranciscodelosrios.BookMaker.utils.PersistenceUnit;
import es.iesfranciscodelosrios.BookMaker.model.DO.Character;
import es.iesfranciscodelosrios.BookMaker.model.DO.GlobalNote;
import es.iesfranciscodelosrios.BookMaker.model.DO.Reminder;

public class mainTest {
	
	private static EntityManager em;
	private static EntityManagerFactory emf;

	public static void main(String[] args) {
		emf = PersistenceUnit.getInstance("aplicacionH2");
		em = PersistenceUnit.getEM();

		/*
		//em.getTransaction().begin();
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
		
		Act a=new Act(book1, "Molinos", "Don Quijote se da de hostias con un molino"); 
		
		ActDAO adao=new ActDAO();
		
		try {
			adao.save(a);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GlobalNote gn=new GlobalNote("Nota 1", "Don Quijote es retrasado", book1);
		
		GlobalNoteDAO gndao=new GlobalNoteDAO();
		
		try {
			gndao.save(gn);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Chapter c=new Chapter("Capitulo 1", "Primer capitulo del Quijote", "Terminado", a, new ArrayList<ChapterNote>());
		
		ChapterDAO cdao=new ChapterDAO();
		
		try {
			cdao.save(c);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ChapterNote cn=new ChapterNote("Nota del capitulo 1", "En esta capitulo Sancho Panza se emborracha", c);
		
		ChapterNoteDAO cndao=new ChapterNoteDAO();
		
		try {
			cndao.save(cn);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Reminder r=new Reminder("Recordatorio", "Termina lo que empieces", 1, book1);
		
		ReminderDAO rdao=new ReminderDAO();
		
		try {
			rdao.save(r);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//FINAL DE LOS CAMBIOS		
		///////////////////////////////////////////////////////////////////////////
		//em.getTransaction().commit();
		
		Reminder ax=new Reminder();
		
		try {
			//Tenemos 2 opciones:
			//En los lugares donde vayamos a tener que editar datos vamos a tener que poner transactions
			//o llamar al DAO de turno y hacer un save del objeto que hayamos editado
			
			em.getTransaction().begin();
			ax=rdao.show(r.getId());
			//em.persist(ax); //Ya viene persistido
			ax.setName("Recorda");
			em.getTransaction().commit();
			System.out.println(ax);
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
		
		
		//em.getTransaction().begin();
		
			try {
				//ax=em.merge(ax);
				//em.remove(ax);
				rdao.delete(ax);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		BookDAO bdao=new BookDAO();
		
		//book1.setId(2L); //No te deja cambiar la id de un objecto que ya exista en la base de datos
		
		book1.setTittle("El Seños de los Anillos");
		
		try {
			bdao.save(book1);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			List<GlobalNote> allGlobalNotes=new ArrayList<GlobalNote>(gndao.getAllBookNotes(book1));
			System.out.println("\n");
			for (GlobalNote globalNote : allGlobalNotes) {
				System.out.println(globalNote);
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("//////////////////////////////////////////////");
		
		try {
			System.out.println(new BookDAO().showAllByUser(user1));
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//em.getTransaction().commit();
		
		ActDAO adao=new ActDAO();
		
		try {
			System.out.println(adao.showAll());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		ChapterNoteDAO cndao=new ChapterNoteDAO();
		
		Chapter c=new Chapter();
		c.setId(1L);
		
		try {
			System.out.println(cndao.selectedByChapter(c));
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
