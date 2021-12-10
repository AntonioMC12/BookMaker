package es.iesfranciscodelosrios.BookMaker.model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;


import es.iesfranciscodelosrios.BookMaker.model.DO.Chapter;
import es.iesfranciscodelosrios.BookMaker.model.DO.ChapterNote;
import es.iesfranciscodelosrios.BookMaker.model.IDAO.IChapterDAO;
import es.iesfranciscodelosrios.BookMaker.model.IDO.IChapter;



public class ChapterDAO implements IChapterDAO {
	public static EntityManager createEM() {
		EntityManagerFactory emf=PersistenceUnit.getInstance();
		return emf.createEntityManager();
	}

	@Override
	public void save(IChapter a) throws DAOException {
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
		}
	}

	@Override
	public void edit(IChapter a) throws DAOException {
		Chapter aux = new Chapter();
		EntityManager em = createEM();
		try {
			aux = em.find(Chapter.class, a);
			em.getTransaction().begin();
			em.merge(aux);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new DAOException ("Error, transacción en curso y no puede comenzar otra", e);
		} catch (RollbackException e) {
			throw new DAOException ("Error en la transacción. Deshaciendo Cambios", e);
		}

	}

	@Override
	public void delete(IChapter a) throws DAOException {
		EntityManager em =createEM();
		try {
			em.getTransaction().begin();
			em.remove(a);
			em.getTransaction().commit();	
		}  catch (IllegalStateException e) {
			throw new DAOException ("Error, transacción en curso y no puede comenzar otra", e);
		} catch (RollbackException e) {
			throw new DAOException ("Error en la transacción. Deshaciendo Cambios", e);
		}
	}

	@Override
	public List<IChapter> showAll() throws DAOException {
		/**List<IChapter> capitulos=new ArrayList<IChapter>();
		EntityManager em =createEM();
		em.getTransaction().begin();
		TypedQuery<Chapter> q=em.createNamedQuery("findAll", Chapter.class);
		capitulos=q.getResultList();
		em.getTransaction().commit();
		return capitulos;*/
		return null;
	}

	@Override
	public IChapter show(Long id) throws DAOException {
		Chapter result=null;
		EntityManager em=createEM();
		try {
			em.getTransaction().begin();
			result=em.find(Chapter.class, id);
		} catch (IllegalStateException e) {
			throw new DAOException ("Error, transacción en curso y no puede comenzar otra", e);
		} catch (RollbackException e) {
			throw new DAOException ("Error en la transacción. Deshaciendo Cambios", e);
		}
		return result;
	}
	
	//seria el showall
	public static List<Chapter> getAll() throws DAOException{
		List<Chapter> capitulos=new ArrayList<Chapter>();
		EntityManager em =createEM();
		try {
			em.getTransaction().begin();
			TypedQuery<Chapter> q=em.createNamedQuery("findAll", Chapter.class);
			capitulos=q.getResultList();
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new DAOException ("Error, transacción en curso y no puede comenzar otra", e);
		} catch (RollbackException e) {
			throw new DAOException ("Error en la transacción. Deshaciendo Cambios", e);
		}

		return capitulos;
	}
	
	
	public static void addChapterNote(Chapter c, ChapterNote cn) throws DAOException {
		EntityManager em = createEM();
		try {
			em.getTransaction().begin();
			Chapter aux=em.merge(c);
			cn.setChapter(aux);
			aux.getNotesChapter().add(cn);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new DAOException ("Error, transacción en curso y no puede comenzar otra", e);
		} catch (RollbackException e) {
			throw new DAOException ("Error en la transacción. Deshaciendo Cambios", e);
		}

	}
	
	public static List<Chapter> selectedByName(String name) throws DAOException{
		List<Chapter> capitulos = new ArrayList<Chapter>();
		EntityManager em = createEM();
		try {
			em.getTransaction().begin();
			TypedQuery<Chapter> q=em.createNamedQuery("findByName", Chapter.class);
			q.setParameter("name", name);
			capitulos = q.getResultList();
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new DAOException ("Error, transacción en curso y no puede comenzar otra", e);
		} catch (RollbackException e) {
			throw new DAOException ("Error en la transacción. Deshaciendo Cambios", e);
		}

		return capitulos;
	}
	


}