package es.iesfranciscodelosrios.BookMaker.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import es.iesfranciscodelosrios.BookMaker.model.DO.Reminder;
import es.iesfranciscodelosrios.BookMaker.model.IDAO.IReminderDAO;
import es.iesfranciscodelosrios.BookMaker.utils.PersistenceUnit;

public class ReminderDAO implements IReminderDAO{
	
	public static EntityManager createEM() {
		EntityManagerFactory emf = PersistenceUnit.getInstance();
		return emf.createEntityManager();
	}

	@Override
	public void save(Reminder re) throws DAOException {
		EntityManager em = createEM();
		em.getTransaction().begin();
		em.persist(re);
		em.getTransaction().commit();
	}

	@Override
	public void edit(Reminder re) throws DAOException {
		save(re);
		
	}

	@Override
	public void delete(Reminder re) throws DAOException {
		EntityManager em = createEM();
		em.getTransaction().begin();
		em.remove(re);
		em.getTransaction().commit();
	}

	@Override
	public List<Reminder> showAll() throws DAOException {
		EntityManager em = createEM();
		return em.createQuery("SELECT re FROM Reminder re", Reminder.class).getResultList();
	}

	@Override
	public Reminder show(Long id) throws DAOException {
		Reminder result = null;
		EntityManager em = createEM();
		em.getTransaction().begin();
		result = em.find(Reminder.class, id);
		em.getTransaction().commit();
		return result;
	}
	
}
