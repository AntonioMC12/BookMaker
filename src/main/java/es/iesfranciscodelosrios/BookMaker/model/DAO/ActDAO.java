package es.iesfranciscodelosrios.BookMaker.model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import es.iesfranciscodelosrios.BookMaker.model.DO.Act;
import es.iesfranciscodelosrios.BookMaker.model.IDAO.IActDAO;
import es.iesfranciscodelosrios.BookMaker.utils.PersistenceUnit;

public class ActDAO implements IActDAO {

	public static EntityManager createEM() {
		return PersistenceUnit.getEM();
	}

	@Override
	public void save(Act ac) throws DAOException {
		try {
			EntityManager em = createEM();
			em.getTransaction().begin();
			em.persist(ac);
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
	public void edit(Act ac) throws DAOException {
		save(ac);
	}

	@Override
	public void delete(Act ac) throws DAOException {
		try {
			EntityManager em = createEM();
			em.getTransaction().begin();
			em.remove(ac);
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
	public List<Act> showAll() throws DAOException {
		List<Act> acts = new ArrayList<>();
		try {
			EntityManager em = createEM();
			em.getTransaction().begin();
			TypedQuery<Act> q = em.createNamedQuery("findAllActs", Act.class);
			acts = q.getResultList();
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
		return acts;
	}

	@Override
	public Act show(Long id) throws DAOException {
		Act result = null;
		try {
			EntityManager em = createEM();
			em.getTransaction().begin();
			result = em.find(Act.class, id);
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
