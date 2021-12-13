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

import es.iesfranciscodelosrios.BookMaker.model.IDO.IChapterNote;

@Entity
@Table(name="Chapter_Note")
@NamedQueries({
	@NamedQuery(name="findAllChapterNotes", query="Select cn FROM ChapterNote cn")
	, @NamedQuery(name="findChapterNoteByName", query="SELECT cn FROM ChapterNote cn WHERE cn.name=:name"),
	@NamedQuery(name="findChapterNoteByChapter", query="SELECT cn FROM ChapterNote cn WHERE chapter=:chapter")
})
public class ChapterNote implements IChapterNote, Serializable {
	public static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="name")
	private String name;
	@Column(name="content")
	private String content;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn (name="chapter_id")
	private Chapter chapter;
	
	
	public ChapterNote(Long id, String name, String content) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
	}

	public ChapterNote(String name, String content, Chapter chapter) {
		super();
		this.name = name;
		this.content = content;
		this.chapter = chapter;
	}

	public ChapterNote() {
		super();
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
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public void setContent(String content) {
		this.content = content;
	}
		

	@Override
	public Chapter getChapter() {
		return chapter;
	}

	@Override
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ChapterNote other = (ChapterNote) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ChapterNote [id=" + id + ", name=" + name + ", content=" + content + "]";
	}


}
