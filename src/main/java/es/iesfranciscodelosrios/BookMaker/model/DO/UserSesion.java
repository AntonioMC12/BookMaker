package es.iesfranciscodelosrios.BookMaker.model.DO;

public class UserSesion {
	
	private User user;
	private final static UserSesion INSTANCE = new UserSesion();

	public UserSesion() {
		  }

	public static UserSesion getInstance() {
		return INSTANCE;
	}

	public void setUser(User u) {
		this.user = u;
	}

	public User getUser() {
		return this.user;

	}

}
