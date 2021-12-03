package es.iesfranciscodelosrios.BookMaker.model.DO;

public class Reminder {

	private int id;
	private int book_id;
	private int chapterIndex;
	private String name;
	private String text;

	/**
	 * @param chapterIndex
	 * @param name
	 * @param text
	 */
	public Reminder(int chapterIndex, String name, String text) {
		this.id = -1;
		this.book_id = -1;
		this.chapterIndex = chapterIndex;
		this.name = name;
		this.text = text;
	}

	/**
	 * @param name
	 * @param text
	 */
	public Reminder(String name, String text) {
		this.name = name;
		this.text = text;
	}

	/**
	 * @param name
	 */
	public Reminder(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public int getChapterIndex() {
		return chapterIndex;
	}

	public void setChapterIndex(int chapterIndex) {
		this.chapterIndex = chapterIndex;
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

	@Override
	public String toString() {
		return "Reminder [id=" + id + ", book_id=" + book_id + ", chapterIndex=" + chapterIndex + ", name=" + name
				+ ", text=" + text + "]";
	}

}
