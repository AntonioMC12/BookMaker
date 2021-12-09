package es.iesfranciscodelosrios.BookMaker.model.DO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import es.iesfranciscodelosrios.BookMaker.model.IDO.IBook;
import es.iesfranciscodelosrios.BookMaker.model.IDO.ICharacter;

@Entity
@Table(name = "Character")
public class Character implements ICharacter, Serializable {

	static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
	@Column(name = "id")
	private Long id;
	private String name;
	private String description;
	private String rol;

	@ManyToMany(mappedBy = "book")
	private List<IBook> books;

	public Character(String name, String description, String rol, List<IBook> books) {
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
	public List<IBook> getBooks() {
		return books;
	}

	/**
	 * @param books the books to set
	 */
	public void setBooks(List<IBook> books) {
		this.books = books;
	}

}
