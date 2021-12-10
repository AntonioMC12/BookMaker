package es.iesfranciscodelosrios.BookMaker.model.IDO;

import java.util.List;

import es.iesfranciscodelosrios.BookMaker.model.DO.Book;

public interface ICharacter {
	
	public void setId(Long id);
	public void setName(String name);
	public void setDescription(String description);
	public void setRol(String rol);
	public void setBooks(List<Book> books);

	
	public Long getId();
	public String getName();
	public String getDescription();
	public String getRol();
	public List<Book> getBooks();

}
