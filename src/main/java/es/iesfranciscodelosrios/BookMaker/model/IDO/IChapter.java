package es.iesfranciscodelosrios.BookMaker.model.IDO;

import java.util.List;

public interface IChapter {

	public void setId(Long id);
	public void setAct(IAct act);
	public void setName(String name);
	public void setText(String text);
	public void setNotesChapter(List<IChapterNote> notesChapter);

	
	public Long getId();
	public IAct getAct();
	public String getName();
	public String getText();
	public List<IChapterNote> getNotesChapter();

}
