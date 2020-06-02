package input_pack;

public class Board {
	private String title;
	private String password;
	private String publicity;
	private String exitDate;
	private String content;
	
	public Board(String title, String password, String publicity, String exitDate, String content) {
		super();
		this.title = title;
		this.password = password;
		this.publicity = publicity;
		this.exitDate = exitDate;
		this.content = content;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPublicity() {
		return publicity;
	}
	public void setPublicity(String publicity) {
		this.publicity = publicity;
	}
	public String getExitDate() {
		return exitDate;
	}
	public void setExitDate(String exitDate) {
		this.exitDate = exitDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
