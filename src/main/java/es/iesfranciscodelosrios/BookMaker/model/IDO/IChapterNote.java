package es.iesfranciscodelosrios.BookMaker.model.IDO;

public interface IChapterNote {
	
	public void setId(Long id);
	public void setChapter(IChapter chapter);
	public void setName(String name);
	public void setContent(String content);


	
	public Long getId();
	public IChapter getChapter();
	public String getName();
	public String getContent();

}
