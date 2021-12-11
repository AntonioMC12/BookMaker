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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import es.iesfranciscodelosrios.BookMaker.model.IDO.IGlobalNote;

@Entity
@Table(name="GlobalNote")
@NamedQueries({
	@NamedQuery(name="findGlobalNoteByName", query="SELECT gn FROM GlobalNote gn WHERE gn.name =: name"),
	//@NamedQuery(name="getAllGlobalNotes", query="SELECT gn FROM GlobalNote gn ")
})
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
	private Book book;

	public GlobalNote() {
		this(-1L, "", "", new Book());
	}
		
	public GlobalNote(String name, String content, Book book) {
		super();
		this.name = name;
		this.content = content;
		this.book = book;
	}

	public GlobalNote(Long id, String name, String content,  Book book) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.book = book;
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
	public Book getBook() {
		return this.book;
	}

	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
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
