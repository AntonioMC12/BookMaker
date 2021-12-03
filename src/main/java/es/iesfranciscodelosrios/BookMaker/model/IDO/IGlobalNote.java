package es.iesfranciscodelosrios.BookMaker.model.IDO;

public interface IGlobalNote {
	
	public void setId(Long id);
	public void setBook(IBook book);
	public void setName(String name);
	public void setContent(String content);


	
	public Long getId();
	public IBook getBook();
	public String getName();
	public String getContent();

}
