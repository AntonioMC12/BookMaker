package es.iesfranciscodelosrios.BookMaker.model.DO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.iesfranciscodelosrios.BookMaker.model.IDO.IUser;

@Entity
@Table(name = "User")
@NamedQueries({
	@NamedQuery(name="getUserByName", query="SELECT u FROM User u WHERE u.name=:name"),
	@NamedQuery(name="getAllUsers", query="SELECT u FROM User u"),
	@NamedQuery(name="getUserByMailAndPass", query="SELECT u FROM User u WHERE u.mail=:mail AND u.password=:password")
})
public class User implements IUser, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "password")
	private String password;
	@Column(name = "mail")
	private String mail;

	// para 1:N siendo autor la tabla 1
	// mappedBy apunta al campo java de la clase many
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Book> books;
	
	public User() {
		this(-1L, "", "", "", new ArrayList<Book>());
	}
	
	public User(String name, String password, String mail, List<Book> books) {
		super();
		this.name = name;
		this.password = password;
		this.mail = mail;
		this.books = books;
	}

	public User(Long id, String name, String password, String mail, List<Book> books) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.mail = mail;
		this.books = books;
	}
	
	public User(String name, String password, String mail) {
		super();
		this.name = name;
		this.password = password;
		this.mail = mail;
	}
	
	public User(String password, String mail) {
		super();
		this.password = password;
		this.mail = mail;
	}

	/**
	 * @return the id
	 */
	@Override
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the mail
	 */
	@Override
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	@Override
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the books
	 */
	public List<Book> getBooks() {
		return books;
	}

	/**
	 * @param books the books to set
	 */
	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
