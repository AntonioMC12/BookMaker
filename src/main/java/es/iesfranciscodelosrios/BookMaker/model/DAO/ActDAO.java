package es.iesfranciscodelosrios.BookMaker.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import es.iesfranciscodelosrios.BookMaker.model.DO.Act;
import es.iesfranciscodelosrios.BookMaker.model.IDAO.IActDAO;
import es.iesfranciscodelosrios.BookMaker.utils.PersistenceUnit;

public class ActDAO implements IActDAO {

	public static EntityManager createEM() {
		EntityManagerFactory emf = PersistenceUnit.getInstance();
		return emf.createEntityManager();
	}

	@Override
	public void save(Act ac) throws DAOException {
		EntityManager em = createEM();
		em.getTransaction().begin();
		em.persist(ac);
		em.getTransaction().commit();
	}

	@Override
	public void edit(Act ac) throws DAOException {
		save(ac);
	}

	@Override
	public void delete(Act ac) throws DAOException {
		EntityManager em = createEM();
		em.getTransaction().begin();
		em.remove(ac);
		em.getTransaction().commit();

	}

	@Override
	public List<Act> showAll() throws DAOException {
		EntityManager em = createEM();
		return em.createQuery("SELECT ac FROM Act ac", Act.class).getResultList();
	}

	@Override
	public Act show(Long id) throws DAOException {
		Act result = null;
		EntityManager em = createEM();
		em.getTransaction().begin();
		result = em.find(Act.class, id);
		em.getTransaction().commit();
		return result;
	}
}
