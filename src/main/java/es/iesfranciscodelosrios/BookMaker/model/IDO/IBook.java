package es.iesfranciscodelosrios.BookMaker.model.IDO;

import java.util.List;

public interface IBook {
	
	public void setId(Long id);
	public void setUser(IUser user);
	public void setTittle(String tittle);
	public void setSummary(String summary);
	public void setGenre(String genre);
	public void setCharacters(List<ICharacter> character);

	
	public Long getId();
	public IUser getUser();
	public String getTittle();
	public String getSummary();
	public String getGenre();
	public List<ICharacter> getCharacters();


}
