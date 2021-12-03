package es.iesfranciscodelosrios.BookMaker.model.IDO;

public interface ICharacter {
	
	public void setId(Long id);
	public void setName(String name);
	public void setDescription(String description);
	public void setRol(String rol);

	
	public Long getId();
	public String getName();
	public String getDescription();
	public String getRol();

}
