package es.iesfranciscodelosrios.BookMaker.model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import es.iesfranciscodelosrios.BookMaker.model.DO.ChapterNote;
import es.iesfranciscodelosrios.BookMaker.model.IDAO.IChapterNoteDAO;
import es.iesfranciscodelosrios.BookMaker.model.IDO.IChapterNote;
import es.iesfranciscodelosrios.BookMaker.utils.PersistenceUnit;

public class ChapterNoteDAO implements IChapterNoteDAO {
	public static EntityManager createEM() {
		EntityManagerFactory emf = PersistenceUnit.getInstance();
		return emf.createEntityManager();
	}

	@Override
	public void save(IChapterNote a) throws DAOException {
		EntityManager em = createEM();
		try {
			em.getTransaction().begin();
			em.persist(a);
			em.getTransaction().commit();
		} catch (EntityExistsException e) {
			throw new DAOException("Error, la nota de capítulo ya existe");
		} catch (IllegalStateException e) {
			throw new DAOException("Error, transacción en curso y no puede comenzar otra", e);
		} catch (RollbackException e) {
			throw new DAOException("Error en la transacción. Deshaciendo Cambios", e);
		} catch (Exception e) {
			throw new DAOException("Ha ocurrido un error.", e);
		}
	}

	@Override
	public void edit(IChapterNote a) throws DAOException {
		ChapterNote aux = new ChapterNote();
		EntityManager em = createEM();
		try {
			aux = em.find(ChapterNote.class, a);
			em.getTransaction().begin();
			em.merge(aux);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new DAOException("Error, transacción en curso y no puede comenzar otra", e);
		} catch (RollbackException e) {
			throw new DAOException("Error en la transacción. Deshaciendo Cambios", e);
		} catch (Exception e) {
			throw new DAOException("Ha ocurrido un error.", e);
		}
	}

	@Override
	public void delete(IChapterNote a) throws DAOException {
		EntityManager em = createEM();
		try {
			em.getTransaction().begin();
			em.remove(a);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new DAOException("Error, transacción en curso y no puede comenzar otra", e);
		} catch (RollbackException e) {
			throw new DAOException("Error en la transacción. Deshaciendo Cambios", e);
		} catch (Exception e) {
			throw new DAOException("Ha ocurrido un error.", e);
		}
	}

	@Override
	public List<IChapterNote> showAll() throws DAOException {
		/*
		 * List<ChapterNote> capitulos=new ArrayList<ChapterNote>(); EntityManager em
		 * =createEM(); em.getTransaction().begin(); TypedQuery<ChapterNote>
		 * q=em.createNamedQuery("findAll", ChapterNote.class);
		 * capitulos=q.getResultList(); em.getTransaction().commit(); return capitulos;
		 */
		return null;
	}

	@Override
	public IChapterNote show(Long id) throws DAOException {
		ChapterNote result = null;
		EntityManager em = createEM();
		try {
			em.getTransaction().begin();
			result = em.find(ChapterNote.class, id);
		} catch (IllegalStateException e) {
			throw new DAOException("Error, transacción en curso y no puede comenzar otra", e);
		} catch (RollbackException e) {
			throw new DAOException("Error en la transacción. Deshaciendo Cambios", e);
		} catch (Exception e) {
			throw new DAOException("Ha ocurrido un error.", e);
		}
		return result;
	}

	// Seria el show all
	public static List<ChapterNote> getAll() throws DAOException {
		List<ChapterNote> capitulos = new ArrayList<ChapterNote>();
		EntityManager em = createEM();
		try {
			em.getTransaction().begin();
			TypedQuery<ChapterNote> q = em.createNamedQuery("findAll", ChapterNote.class);
			capitulos = q.getResultList();
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new DAOException("Error, transacción en curso y no puede comenzar otra", e);
		} catch (RollbackException e) {
			throw new DAOException("Error en la transacción. Deshaciendo Cambios", e);
		} catch (Exception e) {
			throw new DAOException("Ha ocurrido un error.", e);
		}
		return capitulos;
	}

	public static List<ChapterNote> selectedByName(String name) throws DAOException {
		List<ChapterNote> capitulos = new ArrayList<ChapterNote>();
		EntityManager em = createEM();
		try {
			em.getTransaction().begin();
			TypedQuery<ChapterNote> q = em.createNamedQuery("findByName", ChapterNote.class);
			q.setParameter("name", name);
			capitulos = q.getResultList();
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new DAOException("Error, transacción en curso y no puede comenzar otra", e);
		} catch (RollbackException e) {
			throw new DAOException("Error en la transacción. Deshaciendo Cambios", e);
		} catch (Exception e) {
			throw new DAOException("Ha ocurrido un error.", e);
		}
		return capitulos;
	}

}