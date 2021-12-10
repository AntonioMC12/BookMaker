package es.iesfranciscodelosrios.BookMaker.model.IDO;

import es.iesfranciscodelosrios.BookMaker.model.DO.Chapter;

public interface IChapterNote {
	
	public void setId(Long id);
	public void setChapter(Chapter chapter);
	public void setName(String name);
	public void setContent(String content);


	
	public Long getId();
	public Chapter getChapter();
	public String getName();
	public String getContent();

}
