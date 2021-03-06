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

import es.iesfranciscodelosrios.BookMaker.model.IDO.IReminder;

@Entity
@Table(name = "Reminder")
@NamedQueries({ @NamedQuery(name = "findAllReminders", query = "SELECT re FROM Reminder re")
	})
public class Reminder implements IReminder, Serializable {
	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "text")
	private String text;
	@Column(name = "chapterIndex")
	private int chapterIndex;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "book_id")
	private Book book;
	/**
	 * @param id
	 * @param book
	 * @param name
	 * @param text
	 * @param chapterIndex
	 */
	protected Reminder(Long id, Book book, String name, String text, int chapterIndex) {
		super();
		this.id = id;
		this.book = book;
		this.name = name;
		this.text = text;
		this.chapterIndex = chapterIndex;
	}

	public Reminder(String name, String text, int chapterIndex, Book book) {
		super();
		this.name = name;
		this.text = text;
		this.chapterIndex = chapterIndex;
		this.book = book;
	}
	
	

	public Reminder() {
		super();
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getChapterIndex() {
		return chapterIndex;
	}

	public void setChapterIndex(int chapterIndex) {
		this.chapterIndex = chapterIndex;
	}

	@Override
	public int hashCode() {
		return Objects.hash(book, chapterIndex, id, name, text);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reminder other = (Reminder) obj;
		return Objects.equals(book, other.book) && chapterIndex == other.chapterIndex && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(text, other.text);
	}

	@Override
	public String toString() {
		return "Reminder [id=" + id + ", book=" + book + ", name=" + name + ", text=" + text + ", chapterIndex="
				+ chapterIndex + "]";
	}

}
