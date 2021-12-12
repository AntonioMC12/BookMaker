package es.iesfranciscodelosrios.BookMaker.model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import es.iesfranciscodelosrios.BookMaker.model.DO.Book;
import es.iesfranciscodelosrios.BookMaker.model.DO.GlobalNote;
import es.iesfranciscodelosrios.BookMaker.model.DO.User;
import es.iesfranciscodelosrios.BookMaker.model.IDAO.IBookDAO;
import es.iesfranciscodelosrios.BookMaker.utils.PersistenceUnit;

public class BookDAO implements IBookDAO {

	public static EntityManager createEM() {
		return PersistenceUnit.getEM();
	}

	@Override
	public void save(Book a) throws DAOException {
		try {
			EntityManager em = createEM();
			em.getTransaction().begin();
			em.persist(a);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new DAOException("Signals that a method has been invoked at an illegal orinappropriate time.", e);
		} catch (EntityExistsException e) {
			throw new DAOException("The entity already exists. ", e);
		} catch (IllegalArgumentException e) {
			throw new DAOException("A method has been passed an illegal orinappropriate argument.", e);
		} catch (TransactionRequiredException e) {
			throw new DAOException("Transaction is required but is notactive.", e);
		} catch (Exception e) {
			throw new DAOException("An error has ocurred", e);
		}

	}

	@Override
	public void edit(Book a) throws DAOException {
		save(a);
	}

	@Override
	public void delete(Book a) throws DAOException {
		try {
			EntityManager em = createEM();
			em.getTransaction().begin();
			em.remove(a);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new DAOException("Signals that a method has been invoked at an illegal orinappropriate time.", e);
		} catch (EntityExistsException e) {
			throw new DAOException("The entity already exists. ", e);
		} catch (IllegalArgumentException e) {
			throw new DAOException("A method has been passed an illegal orinappropriate argument.", e);
		} catch (TransactionRequiredException e) {
			throw new DAOException("Transaction is required but is notactive.", e);
		} catch (Exception e) {
			throw new DAOException("An error has ocurred", e);
		}

	}

	@Override
	public List<Book> showAll() throws DAOException {
		List<Book> books = new ArrayList<>();
		try {
			EntityManager em = createEM();
			books = em.createQuery("getAllBooks", Book.class).getResultList();
		} catch (IllegalStateException e) {
			throw new DAOException("Signals that a method has been invoked at an illegal orinappropriate time.", e);
		} catch (EntityExistsException e) {
			throw new DAOException("The entity already exists. ", e);
		} catch (IllegalArgumentException e) {
			throw new DAOException("A method has been passed an illegal orinappropriate argument.", e);
		} catch (TransactionRequiredException e) {
			throw new DAOException("Transaction is required but is notactive.", e);
		} catch (Exception e) {
			throw new DAOException("An error has ocurred", e);
		}
		return books;
	}

	@Override
	public Book show(Long id) throws DAOException {
		Book result = null;
		try {
			EntityManager em = createEM();
			em.getTransaction().begin();
			result = em.find(Book.class, id);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new DAOException("Signals that a method has been invoked at an illegal orinappropriate time.", e);
		} catch (EntityExistsException e) {
			throw new DAOException("The entity already exists. ", e);
		} catch (IllegalArgumentException e) {
			throw new DAOException("A method has been passed an illegal orinappropriate argument.", e);
		} catch (TransactionRequiredException e) {
			throw new DAOException("Transaction is required but is notactive.", e);
		} catch (Exception e) {
			throw new DAOException("An error has ocurred", e);
		}
		return result;
	}

	public List<Book> showAllByUser(User user) throws DAOException {
		List<Book> books = new ArrayList<Book>();
		try {
			EntityManager em = createEM();
			TypedQuery<Book> q=em.createNamedQuery("gellAllBooksByUser", Book.class);
			q.setParameter("user", user);
			books=q.getResultList();
		} catch (IllegalStateException e) {
			throw new DAOException("Signals that a method has been invoked at an illegal orinappropriate time.", e);
		} catch (EntityExistsException e) {
			throw new DAOException("The entity already exists. ", e);
		} catch (IllegalArgumentException e) {
			throw new DAOException("A method has been passed an illegal orinappropriate argument.", e);
		} catch (TransactionRequiredException e) {
			throw new DAOException("Transaction is required but is notactive.", e);
		} catch (Exception e) {
			throw new DAOException("An error has ocurred", e);
		}

		return books;
	}
	
	public List<Book> showAllByTittle(String tittle) throws DAOException{
		List<Book> books = new ArrayList<Book>();
		try {
			EntityManager em = createEM();
			TypedQuery<Book> q=em.createNamedQuery("gellAllBooksByTittle", Book.class);
			q.setParameter("tittle", tittle);
			books=q.getResultList();
		} catch (IllegalStateException e) {
			throw new DAOException("Signals that a method has been invoked at an illegal orinappropriate time.", e);
		} catch (EntityExistsException e) {
			throw new DAOException("The entity already exists. ", e);
		} catch (IllegalArgumentException e) {
			throw new DAOException("A method has been passed an illegal orinappropriate argument.", e);
		} catch (TransactionRequiredException e) {
			throw new DAOException("Transaction is required but is notactive.", e);
		} catch (Exception e) {
			throw new DAOException("An error has ocurred", e);
		}

		return books;
	}

}
