package es.iesfranciscodelosrios.BookMaker.model.DO;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import es.iesfranciscodelosrios.BookMaker.model.IDO.IAct;

@Entity
@Table(name = "Act")
@NamedQueries({ @NamedQuery(name = "findAllActs", query = "SELECT ac FROM Act ac"),
		@NamedQuery(name = "findActByName", query = "SELECT ac FROM Act ac WHERE ac.name=:name"),
		@NamedQuery(name = "findActById", query = "SELECT ac FROM Act ac WHERE ac.id=:id") })

public class Act implements IAct, Serializable {
	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "book_id")
	private Book book;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;

	/**
	 * @param id
	 * @param book
	 * @param name
	 * @param description
	 */
	protected Act(Long id, Book book, String name, String description) {
		super();
		this.id = id;
		this.book = book;
		this.name = name;
		this.description = description;
	}

	protected Act() {
		super();
	}

	/**
	 * @param name
	 * @param description
	 */
	protected Act(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(book, description, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Act other = (Act) obj;
		return Objects.equals(book, other.book) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Act [id=" + id + ", book=" + book + ", name=" + name + ", description=" + description + "]";
	}

}
