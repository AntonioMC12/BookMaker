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

import es.iesfranciscodelosrios.BookMaker.model.DO.User;
import es.iesfranciscodelosrios.BookMaker.model.IDAO.IUserDAO;
import es.iesfranciscodelosrios.BookMaker.utils.Utils;

public class UserDAO implements IUserDAO{
	public static EntityManager createEM() {
		EntityManagerFactory emf=Utils.getInstance();
		return emf.createEntityManager();
	}
	
	public static EntityTransaction beginSession() {
		EntityManager em=createEM();
		return em.getTransaction();
	}
	
	public void save(User u) throws DAOException { //Insert-update
		EntityManager em=createEM();
		
		try {
			em.getTransaction().begin();
			em.persist(u);
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
	
	public void delete(User u) throws DAOException {
		EntityManager em=createEM();
		
		try {
			em.getTransaction().begin();
			em.remove(u);
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
	
	public User show(Long id) throws DAOException {
		User result=null;
		
		EntityManager em=createEM();
		
		try {
			em.getTransaction().begin();
			result=em.find(User.class, id);
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
	
	public List<User> showAll() throws DAOException {
		List<User> users=new ArrayList<User>();
		EntityManager em=createEM();
		
		try {
			em.getTransaction().begin();
			TypedQuery<User> q=em.createNamedQuery("getAllUser", User.class);
			users=q.getResultList();
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
		
		return users;
	}
	
	public List<User> selectByName(String name) throws DAOException {
		List<User> users=new ArrayList<User>();
		EntityManager em=createEM();
		
		try {
			em.getTransaction().begin();
			TypedQuery<User> q=em.createNamedQuery("getUserByName", User.class);
			q.setParameter("name", name);
			users=q.getResultList();
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
		
		return users;
	}

	@Override
	public void edit(User a) throws DAOException {
		save(a);
	}
}
