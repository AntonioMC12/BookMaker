package es.iesfranciscodelosrios.BookMaker.model.IDO;

public interface IReminder {
	
	public void setId(Long id);
	public void setBook(IBook book);
	public void setName(String name);
	public void setText(String text);
	public void setChapterIndex(int chapterIndex);


	
	public Long getId();
	public IBook getBook();
	public String getName();
	public String getText();
	public int getChapterIndex();
}
