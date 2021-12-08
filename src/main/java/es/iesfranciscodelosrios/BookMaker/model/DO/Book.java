package es.iesfranciscodelosrios.BookMaker.model.DO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.iesfranciscodelosrios.BookMaker.model.IDO.IBook;
import es.iesfranciscodelosrios.BookMaker.model.IDO.ICharacter;
import es.iesfranciscodelosrios.BookMaker.model.IDO.IUser;

@Entity
@Table(name = "Book")
public class Book implements IBook,Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
	@Column(name = "id")
	private Long id;
	private String tittle;
	private String summary;
	private String genre;
	
	//para 1:N siendo libro la tabla N
	//las manytoone son eager por defecto
	//para cambiar poner (fetch = )
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private IUser user;
	
	private List<ICharacter> characters;
	
	

	public Book(String tittle, String summary, String genre, IUser user, List<ICharacter> characters) {
		this.id = -1L;
		this.tittle = tittle;
		this.summary = summary;
		this.genre = genre;
		this.user = user;
		this.characters = characters;
	}
	
	public Book() {
		this.id = -1L;
		this.tittle = "";
		this.summary = "";
		this.genre = "";
		this.user = null;
		this.characters = null;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getTittle() {
		return tittle;
	}

	@Override
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	@Override
	public String getSummary() {
		return summary;
	}

	@Override
	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public String getGenre() {
		return genre;
	}

	@Override
	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public IUser getUser() {
		return user;
	}

	@Override
	public void setUser(IUser user) {
		this.user = user;
	}

	@Override
	public List<ICharacter> getCharacters() {
		return characters;
	}

	@Override
	public void setCharacters(List<ICharacter> characters) {
		this.characters = characters;
	}



	

}
