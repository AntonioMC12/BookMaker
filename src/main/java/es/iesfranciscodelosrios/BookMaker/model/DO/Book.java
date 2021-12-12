package es.iesfranciscodelosrios.BookMaker.model.DO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.iesfranciscodelosrios.BookMaker.model.IDO.IBook;

@Entity
@Table(name = "Book")
@NamedQueries({ 
	@NamedQuery(name = "findBookById", query = "SELECT b FROM Book b WHERE b.id =:id"), 
	@NamedQuery(name = "getAllBooks", query="SELECT b FROM Book b"),
	@NamedQuery(name = "gellAllBooksByUser", query = "SELECT b FROM Book b WHERE b.user =: user")
	})
public class Book implements IBook, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
	@Column(name = "id")
	private Long id;
	@Column(name = "tittle")
	private String tittle;
	@Column(name = "summary")
	private String summary;
	@Column(name = "genre")
	private String genre;

	// para 1:N siendo libro la tabla N
	// las manytoone son eager por defecto
	// para cambiar poner (fetch = )
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	
	// para 1:N siendo globalnotes la tabla 1
	// mappedBy apunta al campo java de la clase many
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<GlobalNote> globalNotes;
	
	// para 1:N siendo reminders la tabla 1
	// mappedBy apunta al campo java de la clase many
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reminder> reminders;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "character_id", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "character_id"))
	private List<Character> characters;

	public Book(String tittle, String summary, String genre, User user, List<Character> characters) {
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
	
	public Book(Long id,String tittle, String summary, String genre) {
		this.id = -1L;
		this.tittle = tittle;
		this.summary = summary;
		this.genre = genre;
	}
	
	public Book(String tittle, String summary, String genre) {
		this.id = -1L;
		this.tittle = tittle;
		this.summary = summary;
		this.genre = genre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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
	public User getUser() {
		return user;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public List<Character> getCharacters() {
		return characters;
	}

	@Override
	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}

	@Override
	public String toString() {
		return tittle;
	}
	
	

}
