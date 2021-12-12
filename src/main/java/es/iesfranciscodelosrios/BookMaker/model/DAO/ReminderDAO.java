package es.iesfranciscodelosrios.BookMaker.model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import es.iesfranciscodelosrios.BookMaker.model.DO.Reminder;
import es.iesfranciscodelosrios.BookMaker.model.IDAO.IReminderDAO;
import es.iesfranciscodelosrios.BookMaker.utils.PersistenceUnit;

public class ReminderDAO implements IReminderDAO {
	
	public static EntityManager createEM() {
		/*
		if(em==null) {
			EntityManagerFactory emf = PersistenceUnit.getInstance();
			em=emf.createEntityManager();
			return em;			
		}else {
			return em;
		}*/
		return PersistenceUnit.getEM();
	}

	@Override
	public void save(Reminder re) throws DAOException {
		try {
			EntityManager em = createEM();
			em.getTransaction().begin();
			em.persist(re);
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
	public void edit(Reminder re) throws DAOException {
		save(re);
	}

	@Override
	public void delete(Reminder re) throws DAOException {
		EntityManager em = createEM();
		try {
			em.getTransaction().begin();
			em.merge(re);
			em.remove(re);
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
	public List<Reminder> showAll() throws DAOException {
		List<Reminder> reminders = new ArrayList<>();
		try {
			EntityManager em = createEM();
			em.getTransaction().begin();
			TypedQuery<Reminder> q = em.createNamedQuery("findAllReminders", Reminder.class);
			reminders = q.getResultList();
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
		return reminders;
	}

	@Override
	public Reminder show(Long id) throws DAOException {
		Reminder result = null;
		try {
			EntityManager em = createEM();
			//em.getTransaction().begin();
			result = em.find(Reminder.class, id);
			//em.getTransaction().commit();
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
