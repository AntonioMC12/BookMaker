package es.iesfranciscodelosrios.BookMaker.model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import es.iesfranciscodelosrios.BookMaker.model.DO.Book;
import es.iesfranciscodelosrios.BookMaker.model.DO.GlobalNote;
import es.iesfranciscodelosrios.BookMaker.model.IDAO.IGlobalNoteDAO;
import es.iesfranciscodelosrios.BookMaker.utils.PersistenceUnit;
import es.iesfranciscodelosrios.BookMaker.utils.Utils;

public class GlobalNoteDAO implements IGlobalNoteDAO{
	public static EntityManager createEM() {
		return PersistenceUnit.getEM();
	}
	
	public static EntityTransaction beginSession() {
		EntityManager em=createEM();
		return em.getTransaction();
	}
	
	public void save(GlobalNote gn) throws DAOException { //Insert-update
		EntityManager em=createEM();
		
		try {
			em.getTransaction().begin();
			em.persist(gn);
			em.getTransaction().commit();			
		}catch(EntityExistsException e) {
			throw new DAOException("Error, la entidad ya existe");
		}catch(IllegalStateException e) {
			throw new DAOException("Error de estado, puede ser del begin o el commit", e);
		}catch(RollbackException e) {
			throw new DAOException("Error al hacer el commit de la transaccion. Deshaciendo cambios...", e);
		}catch(TransactionRequiredException e) {
			throw new DAOException("Error, no hay una transaccion empezada al hacer el persist", e);
		}catch(IllegalArgumentException e) {
			throw new DAOException("La instacia pasada por parametro no es una entidad o es null", e);
		}catch(Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void delete(GlobalNote gn) throws DAOException {
		EntityManager em=createEM();
		
		try {
			em.getTransaction().begin();
			em.remove(gn);
			em.getTransaction().commit();			
		}catch(EntityExistsException e) {
			throw new DAOException("Error, la entidad ya existe");
		}catch(IllegalStateException e) {
			throw new DAOException("Error de estado, puede ser del begin o el commit", e);
		}catch(RollbackException e) {
			throw new DAOException("Error al hacer el commit de la transaccion. Deshaciendo cambios...", e);
		}catch(TransactionRequiredException e) {
			throw new DAOException("Error, no hay una transaccion empezada al hacer el persist", e);
		}catch(IllegalArgumentException e) {
			throw new DAOException("La instacia pasada por parametro no es una entidad o es null", e);
		}catch(Exception e) {
			throw new DAOException(e);
		}
	}
	
	public GlobalNote show(Long id) throws DAOException {
		GlobalNote result=null;
		
		EntityManager em=createEM();
		
		try {
			em.getTransaction().begin();
			result=em.find(GlobalNote.class, id);
			em.getTransaction().commit();			
		}catch(EntityExistsException e) {
			throw new DAOException("Error, la entidad ya existe");
		}catch(IllegalStateException e) {
			throw new DAOException("Error de estado, puede ser del begin, el commit", e);
		}catch(RollbackException e) {
			throw new DAOException("Error al hacer el commit de la transaccion. Deshaciendo cambios...", e);
		}catch(TransactionRequiredException e) {
			throw new DAOException("Error, no hay una transaccion empezada al hacer el persist", e);
		}catch(IllegalArgumentException e) {
			throw new DAOException("La id pasada al find es invalida", e);
		}catch(Exception e) {
			throw new DAOException(e);
		}
		
		return result;
	}
	
	public List<GlobalNote> getAllBookNotes(Book book) throws DAOException {
		List<GlobalNote> bookNotes=new ArrayList<GlobalNote>();
		EntityManager em=createEM();
		
		try {
			em.getTransaction().begin();
			TypedQuery<GlobalNote> q=em.createNamedQuery("getAllGlobalNotes", GlobalNote.class);
			q.setParameter("book", book);
			bookNotes=q.getResultList();
			em.getTransaction().commit();			
		}catch(EntityExistsException e) {
			throw new DAOException("Error, la entidad ya existe");
		}catch(IllegalStateException e) {
			throw new DAOException("Error de estado, puede ser del begin, el commit o el resultList", e);
		}catch(RollbackException e) {
			throw new DAOException("Error al hacer el commit de la transaccion. Deshaciendo cambios...", e);
		}catch(TransactionRequiredException e) {
			throw new DAOException("Error, no hay una transaccion empezada al hacer el persist", e);
		}catch(IllegalArgumentException e) {
			throw new DAOException("La query es invalida o no se ha definido", e);
		}catch(Exception e) {
			throw new DAOException(e);
		}
		
		return bookNotes;
	}
	
	public List<GlobalNote> selectByName(String name) throws DAOException {
		List<GlobalNote> bookNotes=new ArrayList<GlobalNote>();
		EntityManager em=createEM();
		
		try {
			em.getTransaction().begin();
			TypedQuery<GlobalNote> q=em.createNamedQuery("findGlobalNoteByName", GlobalNote.class);
			q.setParameter("name", name);
			bookNotes=q.getResultList();
			em.getTransaction().commit();			
		}catch(EntityExistsException e) {
			throw new DAOException("Error, la entidad ya existe");
		}catch(IllegalStateException e) {
			throw new DAOException("Error de estado, puede ser del begin, el commit o el resultList", e);
		}catch(RollbackException e) {
			throw new DAOException("Error al hacer el commit de la transaccion. Deshaciendo cambios...", e);
		}catch(TransactionRequiredException e) {
			throw new DAOException("Error, no hay una transaccion empezada al hacer el persist", e);
		}catch(IllegalArgumentException e) {
			throw new DAOException("La query es invalida o no se ha definido", e);
		}catch(Exception e) {
			throw new DAOException(e);
		}
		
		return bookNotes;
	}

	@Override
	public void edit(GlobalNote a) throws DAOException {
		save(a);
	}

	/**
	 * No se usa
	 */
	@Override
	public List<GlobalNote> showAll() throws DAOException {
		return null;
	}
}
