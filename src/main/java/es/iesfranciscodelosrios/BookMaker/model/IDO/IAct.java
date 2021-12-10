package es.iesfranciscodelosrios.BookMaker.model.IDO;

import es.iesfranciscodelosrios.BookMaker.model.DO.Book;

public interface IAct {

	public void setId(Long id);
	public void setBook(Book book);
	public void setName(String name);
	public void setDescription(String description);

	
	public Long getId();
	public Book getBook();
	public String getName();
	public String getDescription();
}
