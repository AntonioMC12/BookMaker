package es.iesfranciscodelosrios.BookMaker.model.IDO;

public interface IUser {
	
	public void setId(Long id);
	public void setName(String name);
	public void setPassword(String password);
	public void setMail(String mail);
	
	public Long getId();
	public String getName();
	public String getPassword();
	public String getMail();

}
