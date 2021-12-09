package es.iesfranciscodelosrios.BookMaker.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import es.iesfranciscodelosrios.BookMaker.model.DO.Book;
import es.iesfranciscodelosrios.BookMaker.model.IDAO.IBookDAO;
import es.iesfranciscodelosrios.BookMaker.utils.PersistenceUnit;

public class BookDAO implements IBookDAO {
	
	public static EntityManager createEM() {
		EntityManagerFactory emf = PersistenceUnit.getInstance();
		return emf.createEntityManager();
	}

	@Override
	public void save(Book a) throws DAOException {
		EntityManager em = createEM();
		em.getTransaction().begin();
		em.persist(a);
		em.getTransaction().commit();
		
	}

	@Override
	public void edit(Book a) throws DAOException {
		save(a);
	}

	@Override
	public void delete(Book a) throws DAOException {
		EntityManager em = createEM();
		em.getTransaction().begin();
		em.remove(a);
		em.getTransaction().commit();
		
	}

	@Override
	public List<Book> showAll() throws DAOException {
		EntityManager em = createEM();
		return em.createQuery("SELECT a FROM Book a", Book.class).getResultList();
	}

	@Override
	public Book show(Long id) throws DAOException {
		Book result = null;
		EntityManager em = createEM();
		em.getTransaction().begin();
		result = em.find(Book.class, id);
		em.getTransaction().commit();
		return result;
	}

}
