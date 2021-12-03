package es.iesfranciscodelosrios.BookMaker.model.IDO;

public interface IChapterNote {
	
	public void setId(Long id);
	public void setChapter(ICharacter chapter);
	public void setName(String name);
	public void setContent(String content);


	
	public Long getId();
	public ICharacter getChapter();
	public String getName();
	public String getContent();

}
