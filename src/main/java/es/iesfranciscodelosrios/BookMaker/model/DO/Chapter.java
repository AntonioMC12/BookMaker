package es.iesfranciscodelosrios.BookMaker.model.DO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.iesfranciscodelosrios.BookMaker.model.IDO.IChapter;



@Entity
@Table(name="Chapter")
@NamedQueries({
	@NamedQuery(name="findAllChapters", query="SELECT c FROM Chapter c")
, @NamedQuery(name="findChapterByName", query="SELECT c FROM Chapter c WHERE c.name=:name")
})
public class Chapter implements IChapter, Serializable {
	public static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="title")
	private String name;
	@Column(name="text")
	private String text;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn (name="act_id")
	private Act act;
	
	@OneToMany(mappedBy="chapter", cascade=CascadeType.REMOVE, orphanRemoval = true)
	private List<ChapterNote> notesChapter;
	
	
	
	public Chapter(Long id, String name, String text, Act act, List<ChapterNote> notesChapter) {
		super();
		this.id = id;
		this.name = name;
		this.text = text;
		this.act = act;
		this.notesChapter = notesChapter;
	}

	public Chapter() {
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

	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Act getAct() {
		return act;
	}

	@Override
	public void setAct(Act act) {
		this.act = act;
	}

	@Override
	public List<ChapterNote> getNotesChapter() {
		return notesChapter;
	}

	@Override
	public void setNotesChapter(List<ChapterNote> notesChapter) {
		this.notesChapter = notesChapter;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		Chapter other = (Chapter) obj;
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
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Chapter [id=" + id + ", name=" + name + ", text=" + text + ", act=" + act + ", notesChapter="
				+ notesChapter + "]";
	}

	
	

}
