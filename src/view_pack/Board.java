package view_pack;

import javafx.beans.property.SimpleStringProperty;

public class Board {
	private SimpleStringProperty title;
	private SimpleStringProperty password;
	private SimpleStringProperty publicity;
	private SimpleStringProperty exitDate;
	private SimpleStringProperty content; //생성자 겟셋메소드와 property메소드 만들기
	
	Board(String title, String password, String publicity, String exitDate, String content) {
		this.title=new SimpleStringProperty(title);
		this.title=new SimpleStringProperty(password);
		this.title=new SimpleStringProperty(publicity);
		this.title=new SimpleStringProperty(exitDate);
		this.title=new SimpleStringProperty(content);
	}
	
	public void setTitle(String title) {
		this.title.set(title);
	}
	
	public String getTitle() {
		return this.title.get();
	}
	
	public SimpleStringProperty titleProperty() {
		return this.title;
	}
	
	public void setPassword(String password) {
		this.password.set(password);
	}
	
	public String getPassword() {
		return this.password.get();
	}
	
	public SimpleStringProperty passwordProperty() {
		return this.password;
	}
	
	public void setPublicity(String publicity) {
		this.publicity.set(publicity);
	}
	
	public String getPublicity() {
		return this.publicity.get();
	}
	
	public SimpleStringProperty publicityProperty() {
		return this.publicity;
	}
	
	public void setExitDate(String exitDate) {
		this.exitDate.set(exitDate);
	}
	
	public String getExitDate() {
		return this.exitDate.get();
	}
	
	public SimpleStringProperty exitDateProperty() {
		return this.exitDate;
	}
	
	public void setContent(String content) {
		this.content.set(content);
	}
	
	public String getContent() {
		return this.content.get();
	}
	
	public SimpleStringProperty contentProperty() {
		return this.content;
	}
}
