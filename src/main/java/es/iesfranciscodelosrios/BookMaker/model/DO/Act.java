package es.iesfranciscodelosrios.BookMaker.model.DO;

public class Act {

	private int id;
	private int book_id;
	private String title;
	private String description;

	/**
	 * @param title
	 * @param description
	 */
	public Act(String title, String description) {
		this.id = -1;
		this.book_id = -1;
		this.title = title;
		this.description = description;
	}

	/**
	 * @param title
	 */
	public Act(String title) {
		super();
		this.title = title;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Act [id=" + id + ", book_id=" + book_id + ", title=" + title + ", description=" + description + "]";
	}

}
