package es.iesfranciscodelosrios.BookMaker.model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;


import es.iesfranciscodelosrios.BookMaker.model.DO.Chapter;
import es.iesfranciscodelosrios.BookMaker.model.IDAO.IChapterDAO;
import es.iesfranciscodelosrios.BookMaker.utils.PersistenceUnit;



public class ChapterDAO implements IChapterDAO {
	public static EntityManager createEM() {
		EntityManagerFactory emf = PersistenceUnit.getInstance();
		return emf.createEntityManager();
	}

	@Override
	public void save(Chapter a) throws DAOException {
		EntityManager em=createEM();
		try {
			em.getTransaction().begin();
			em.persist(a);
			em.getTransaction().commit();
		} catch (EntityExistsException e) {
			throw new DAOException ("Error, el capítulo ya existe");
		} catch (IllegalStateException e) {
			throw new DAOException ("Error, transacción en curso y no puede comenzar otra", e);
		} catch (RollbackException e) {
			throw new DAOException ("Error en la transacción. Deshaciendo Cambios", e);
		} catch(Exception e){
			throw new DAOException ("Ha ocurrido un error.", e);
		}
	}

	@Override
	public void edit(Chapter a) throws DAOException {
		save(a);
	}

	@Override
	public void delete(Chapter a) throws DAOException {
		EntityManager em =createEM();
		try {
			em.getTransaction().begin();
			em.remove(a);
			em.getTransaction().commit();	
		}  catch (IllegalStateException e) {
			throw new DAOException ("Error, transacción en curso y no puede comenzar otra", e);
		} catch (RollbackException e) {
			throw new DAOException ("Error en la transacción. Deshaciendo Cambios", e);
		} catch(Exception e){
			throw new DAOException ("Ha ocurrido un error.", e);
		}
	}

	@Override
	public List<Chapter> showAll() throws DAOException {
		List<Chapter> capitulos=new ArrayList<Chapter>();
		EntityManager em =createEM();
		try {
			em.getTransaction().begin();
			TypedQuery<Chapter> q=em.createNamedQuery("findAllChapters", Chapter.class);
			capitulos=q.getResultList();
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new DAOException ("Error, transacción en curso y no puede comenzar otra", e);
		} catch (RollbackException e) {
			throw new DAOException ("Error en la transacción. Deshaciendo Cambios", e);
		} catch(Exception e){
			throw new DAOException ("Ha ocurrido un error.", e);
		}

		return capitulos;
	}

	@Override
	public Chapter show(Long id) throws DAOException {
		Chapter result=null;
		EntityManager em=createEM();
		try {
			em.getTransaction().begin();
			result=em.find(Chapter.class, id);
		} catch (IllegalStateException e) {
			throw new DAOException ("Error, transacción en curso y no puede comenzar otra", e);
		} catch (RollbackException e) {
			throw new DAOException ("Error en la transacción. Deshaciendo Cambios", e);
		} catch(Exception e){
			throw new DAOException ("Ha ocurrido un error.", e);
		}
		return result;
	}
	
	public static List<Chapter> selectedByName(String name) throws DAOException{
		List<Chapter> capitulos = new ArrayList<Chapter>();
		EntityManager em = createEM();
		try {
			em.getTransaction().begin();
			TypedQuery<Chapter> q=em.createNamedQuery("findChapterByName", Chapter.class);
			q.setParameter("name", name);
			capitulos = q.getResultList();
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new DAOException ("Error, transacción en curso y no puede comenzar otra", e);
		} catch (RollbackException e) {
			throw new DAOException ("Error en la transacción. Deshaciendo Cambios", e);
		} catch(Exception e){
			throw new DAOException ("Ha ocurrido un error.", e);
		}

		return capitulos;
	}

}