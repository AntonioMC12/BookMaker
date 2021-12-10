package es.iesfranciscodelosrios.BookMaker.model.IDAO;

import java.util.List;

import es.iesfranciscodelosrios.BookMaker.model.DAO.DAOException;
import es.iesfranciscodelosrios.BookMaker.model.DO.GlobalNote;

public interface IGlobalNoteDAO extends IDAO<GlobalNote, Long>{
	public List<GlobalNote> selectByName(String name) throws DAOException;
}
