package es.iesfranciscodelosrios.BookMaker.model.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import es.iesfranciscodelosrios.BookMaker.model.IDAO.ICharacterDAO;
import es.iesfranciscodelosrios.BookMaker.utils.PersistenceUnit;

public class CharacterDAO implements ICharacterDAO{
	
	public static EntityManager createEM() {
		EntityManagerFactory emf = PersistenceUnit.getInstance();
		return emf.createEntityManager();
	}

	@Override
	public void save(Character a) throws DAOException {
		EntityManager em = createEM();
		em.getTransaction().begin();
		em.persist(a);
		em.getTransaction().commit();		
	}

	@Override
	public void edit(Character a) throws DAOException {
		save(a);
		
	}

	@Override
	public void delete(Character a) throws DAOException {
		EntityManager em = createEM();
		em.getTransaction().begin();
		em.remove(a);
		em.getTransaction().commit();		
	}

	@Override
	public List<Character> showAll() throws DAOException {
		EntityManager em = createEM();
		return em.createQuery("SELECT a FROM Character a", Character.class).getResultList();
	}

	@Override
	public Character show(Long id) throws DAOException {
		Character result = null;
		EntityManager em = createEM();
		em.getTransaction().begin();
		result = em.find(Character.class, id);
		em.getTransaction().commit();
		return result;
	}

}
