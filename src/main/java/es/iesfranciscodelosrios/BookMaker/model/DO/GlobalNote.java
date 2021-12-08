package es.iesfranciscodelosrios.BookMaker.model.DO;

import java.io.Serializable;

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
import es.iesfranciscodelosrios.BookMaker.model.IDO.IGlobalNote;

@Entity
@Table(name="GlobalNote")
public class GlobalNote implements IGlobalNote, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="content")
	private String content;
	
	@ManyToOne(fetch = FetchType.EAGER) //Se trae el libro al traerse la GlobalNote
	@JoinColumn(name="book_id") //Define el nombre de la columna que es la clave foranea
	private IBook book;

	public GlobalNote() {
		
	}
		
	public GlobalNote(Long id, IBook book, String name, String content) {
		super();
		this.id = id;
		this.book = book;
		this.name = name;
		this.content = content;
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
	 * @return the book
	 */
	public IBook getBook() {
		return book;
	}

	/**
	 * @param book the book to set
	 */
	public void setBook(IBook book) {
		this.book = book;
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
	 * @return the content
	 */
	@Override
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	@Override
	public void setContent(String content) {
		this.content = content;
	}

}
