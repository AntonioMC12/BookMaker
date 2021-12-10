package es.iesfranciscodelosrios.BookMaker.model.IDO;

import java.util.List;

import es.iesfranciscodelosrios.BookMaker.model.DO.Character;
import es.iesfranciscodelosrios.BookMaker.model.DO.User;

public interface IBook {
	
	public void setId(Long id);
	public void setUser(User user);
	public void setTittle(String tittle);
	public void setSummary(String summary);
	public void setGenre(String genre);
	public void setCharacters(List<Character> character);

	
	public Long getId();
	public User getUser();
	public String getTittle();
	public String getSummary();
	public String getGenre();
	public List<Character> getCharacters();


}
