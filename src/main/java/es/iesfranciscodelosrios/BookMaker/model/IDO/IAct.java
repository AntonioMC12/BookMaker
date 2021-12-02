package es.iesfranciscodelosrios.BookMaker.model.IDO;

public interface IAct {

	public void setId(Long id);
	public void setBook(IBook book);
	public void setName(String name);
	public void setDescription(String description);

	
	public Long getId();
	public IBook getBook();
	public String getName();
	public String getDescription();
}
