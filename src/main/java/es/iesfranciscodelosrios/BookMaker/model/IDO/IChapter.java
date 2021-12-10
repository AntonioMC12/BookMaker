package es.iesfranciscodelosrios.BookMaker.model.IDO;

import java.util.List;

import es.iesfranciscodelosrios.BookMaker.model.DO.Act;
import es.iesfranciscodelosrios.BookMaker.model.DO.ChapterNote;

public interface IChapter {

	public void setId(Long id);
	public void setAct(Act act);
	public void setName(String name);
	public void setText(String text);
	public void setNotesChapter(List<ChapterNote> notesChapter);

	
	public Long getId();
	public Act getAct();
	public String getName();
	public String getText();
	public List<ChapterNote> getNotesChapter();

}
