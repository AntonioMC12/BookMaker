package es.iesfranciscodelosrios.BookMaker.model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import es.iesfranciscodelosrios.BookMaker.model.DO.Chapter;
import es.iesfranciscodelosrios.BookMaker.model.DO.ChapterNote;
import es.iesfranciscodelosrios.BookMaker.model.IDAO.IChapterNoteDAO;
import es.iesfranciscodelosrios.BookMaker.utils.PersistenceUnit;

public class ChapterNoteDAO implements IChapterNoteDAO {
	public static EntityManager createEM() {
		return PersistenceUnit.getEM();
	}

	@Override
	public void save(ChapterNote a) throws DAOException {
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
	public void edit(ChapterNote a) throws DAOException {
		save(a);
	}

	@Override
	public void delete(ChapterNote a) throws DAOException {
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
	public List<ChapterNote> showAll() throws DAOException {
		List<ChapterNote> capitulos = new ArrayList<ChapterNote>();
		EntityManager em = createEM();
		try {
			em.getTransaction().begin();
			TypedQuery<ChapterNote> q = em.createNamedQuery("findAllChapterNotes", ChapterNote.class);
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

	@Override
	public ChapterNote show(Long id) throws DAOException {
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

	public List<ChapterNote> selectedByName(String name) throws DAOException {
		List<ChapterNote> capitulos = new ArrayList<ChapterNote>();
		EntityManager em = createEM();
		try {
			em.getTransaction().begin();
			TypedQuery<ChapterNote> q = em.createNamedQuery("findChapterNoteByName", ChapterNote.class);
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

	public List<ChapterNote> selectedByChapter(Chapter c) throws DAOException {
		List<ChapterNote> capitulos = new ArrayList<ChapterNote>();
		EntityManager em = createEM();
		try {
			em.getTransaction().begin();
			TypedQuery<ChapterNote> q = em.createNamedQuery("findChapterNoteByChapter", ChapterNote.class);
			q.setParameter("chapter", c);
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