package es.iesfranciscodelosrios.BookMaker.model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import es.iesfranciscodelosrios.BookMaker.model.IDAO.ICharacterDAO;
import es.iesfranciscodelosrios.BookMaker.utils.PersistenceUnit;
import es.iesfranciscodelosrios.BookMaker.model.DO.Book;
import es.iesfranciscodelosrios.BookMaker.model.DO.Character;

public class CharacterDAO implements ICharacterDAO {

	public static EntityManager createEM() {
		return PersistenceUnit.getEM();
	}

	@Override
	public void save(Character a) throws DAOException {
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
	public void edit(Character a) throws DAOException {
		try {
			EntityManager em = createEM();
			em.getTransaction().begin();
			em.merge(a);
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
	public void delete(Character a) throws DAOException {
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
	public List<Character> showAll() throws DAOException {
		List<Character> characters = new ArrayList<>();
		try {
			EntityManager em = createEM();
			em.getTransaction().begin();
			TypedQuery<Character> q=em.createNamedQuery("getAllCharacters", Character.class);
			characters=q.getResultList();
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
		return characters;
	}

	@Override
	public Character show(Long id) throws DAOException {
		Character result = null;
		try {
			EntityManager em = createEM();
			em.getTransaction().begin();
			result = em.find(Character.class, id);
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
	
	public List<Character> selectByName(String name) throws DAOException {
		List<Character> chapters = new ArrayList<Character>();
		EntityManager em = createEM();

		try {
			em.getTransaction().begin();
			TypedQuery<Character> q = em.createNamedQuery("getCharacterByName", Character.class);
			q.setParameter("name", name);
			chapters = q.getResultList();
			em.getTransaction().commit();
		} catch (EntityExistsException e) {
			throw new DAOException("The entity already exists.");
		} catch (IllegalStateException e) {
			throw new DAOException("Signals that a method has been invoked at an illegal orinappropriate time.", e);
		} catch (RollbackException e) {
			throw new DAOException("Error during commit. Undoing changes...", e);
		} catch (TransactionRequiredException e) {
			throw new DAOException("Transaction is required but is notactive.", e);
		} catch (IllegalArgumentException e) {
			throw new DAOException("Undefined or unvalid query", e);
		} catch (Exception e) {
			throw new DAOException(e);
		}

		return chapters;
	}
	
	public List<Character> selectByBook(Book book) throws DAOException {
		List<Character> chapters = new ArrayList<Character>();
		EntityManager em = createEM();

		try {
			em.getTransaction().begin();
			TypedQuery<Character> q = em.createNamedQuery("getAllCharactersByBook", Character.class);
			q.setParameter("book", book);
			chapters = q.getResultList();
			em.getTransaction().commit();
		} catch (EntityExistsException e) {
			throw new DAOException("The entity already exists.");
		} catch (IllegalStateException e) {
			throw new DAOException("Signals that a method has been invoked at an illegal orinappropriate time.", e);
		} catch (RollbackException e) {
			throw new DAOException("Error during commit. Undoing changes...", e);
		} catch (TransactionRequiredException e) {
			throw new DAOException("Transaction is required but is notactive.", e);
		} catch (IllegalArgumentException e) {
			throw new DAOException("Undefined or unvalid query", e);
		} catch (Exception e) {
			throw new DAOException(e);
		}

		return chapters;
	}
}
