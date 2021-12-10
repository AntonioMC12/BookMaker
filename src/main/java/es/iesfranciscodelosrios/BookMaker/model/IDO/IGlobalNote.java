package es.iesfranciscodelosrios.BookMaker.model.IDO;

import es.iesfranciscodelosrios.BookMaker.model.DO.Book;

public interface IGlobalNote {
	
	public void setId(Long id);
	public void setBook(Book book);
	public void setName(String name);
	public void setContent(String content);


	
	public Long getId();
	public Book getBook();
	public String getName();
	public String getContent();

}
