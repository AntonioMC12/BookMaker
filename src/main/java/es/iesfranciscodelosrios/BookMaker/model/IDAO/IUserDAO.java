package es.iesfranciscodelosrios.BookMaker.model.IDAO;

import java.util.List;

import es.iesfranciscodelosrios.BookMaker.model.DAO.DAOException;
import es.iesfranciscodelosrios.BookMaker.model.DO.User;

public interface IUserDAO extends IDAO<User, Long>{
	public List<User> selectByName(String name) throws DAOException;
}
