package es.iesfranciscodelosrios.BookMaker.model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TransactionRequiredException;

import es.iesfranciscodelosrios.BookMaker.model.IDAO.ICharacterDAO;
import es.iesfranciscodelosrios.BookMaker.utils.PersistenceUnit;

public class CharacterDAO implements ICharacterDAO {

	public static EntityManager createEM() {
		EntityManagerFactory emf = PersistenceUnit.getInstance();
		return emf.createEntityManager();
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
		save(a);

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
			characters = em.createQuery("SELECT a FROM Character a", Character.class).getResultList();
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

}
