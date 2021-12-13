package es.iesfranciscodelosrios.BookMaker.model.DO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import es.iesfranciscodelosrios.BookMaker.model.IDO.ICharacter;

@Entity
@Table(name = "Character")
@NamedQueries({
	@NamedQuery(name="getCharacterByName", query="SELECT ch FROM Character ch WHERE ch.name=:name"),
	@NamedQuery(name="getAllCharacters", query="SELECT ch FROM Character ch")
})
public class Character implements ICharacter, Serializable {

	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "rol")
	private String rol;

	@ManyToMany(targetEntity=Book.class, mappedBy="characters")
	private List<Book> books;
	
	

	public Character(Long id, String name, String description, String rol, List<Book> books) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.rol = rol;
		this.books = books;
	}

	public Character(String name, String description, String rol, List<Book> books) {
		this.id = -1L;
		this.name = name;
		this.description = description;
		this.rol = rol;
		this.books = books;
	}
	
	public Character() {
		this.id = -1L;
		this.name = "";
		this.description = "";
		this.rol = "";
		this.books = null;
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
		Character other = (Character) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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
	 * @return the description
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the rol
	 */
	@Override
	public String getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	@Override
	public void setRol(String rol) {
		this.rol = rol;
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

	@Override
	public String toString() {
		return name;
	}

	
}
