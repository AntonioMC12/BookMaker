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

import es.iesfranciscodelosrios.BookMaker.model.IDO.IBook;
import es.iesfranciscodelosrios.BookMaker.model.IDO.IReminder;

@Entity
@Table(name = "Reminder")
@NamedQueries({ @NamedQuery(name = "findAll", query = "SELECT * FROM Reminder"),
	@NamedQuery(name = "findByName", query = "SELECT re FROM Reminder re WHERE re.name=:name"),
	@NamedQuery(name = "findById", query = "SELECT re FROM Reminder re WHERE re.id=:id") })
public class Reminder implements IReminder, Serializable {
	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "book_id")
	private IBook book;
	@Column(name = "name")
	private String name;
	@Column(name = "text")
	private String text;
	@Column(name = "chapterIndex")
	private int chapterIndex;

	/**
	 * @param id
	 * @param book
	 * @param name
	 * @param text
	 * @param chapterIndex
	 */
	protected Reminder(Long id, IBook book, String name, String text, int chapterIndex) {
		super();
		this.id = id;
		this.book = book;
		this.name = name;
		this.text = text;
		this.chapterIndex = chapterIndex;
	}

	protected Reminder() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public IBook getBook() {
		return book;
	}

	public void setBook(IBook book) {
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
