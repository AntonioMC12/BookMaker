package es.iesfranciscodelosrios.BookMaker.model.IDO;

import es.iesfranciscodelosrios.BookMaker.model.DO.Book;

public interface IReminder {
	
	public void setId(Long id);
	public void setBook(Book book);
	public void setName(String name);
	public void setText(String text);
	public void setChapterIndex(int chapterIndex);


	
	public Long getId();
	public Book getBook();
	public String getName();
	public String getText();
	public int getChapterIndex();
}
