package es.iesfranciscodelosrios.BookMaker.model.IDAO;

import java.util.List;

import es.iesfranciscodelosrios.BookMaker.model.DAO.DAOException;

/**
 * 
 * @author Antonio
 *
 * @param <T> Clase (Boook)
 * @param <K> Tipo del Id (Long)
 */
public interface IDAO<T, K> {
	
	/**
	 * Método que guarda un objeto en la base de datos
	 * 
	 * @param a
	 * @throws DAOException
	 */
	void save(T a) throws DAOException;

	/**
	 * Método edita un objeto existente en la base de datos
	 * @param a
	 * @throws DAOException
	 */
	void edit(T a) throws DAOException;

	/**
	 * Método que borra un objeto existente en la base de datos
	 * @param a
	 * @throws DAOException
	 */
	void delete(T a) throws DAOException;

	/**
	 * Método que muestra todos los objetos de una tabla
	 * @return
	 * @throws DAOException
	 */
	List<T> showAll() throws DAOException;
	
	/**
	 * Método que muestra un objeto por la id dada
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	T show(K id) throws DAOException;

}