package test2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {
	private SimpleIntegerProperty code;
	private SimpleStringProperty title;
	private SimpleStringProperty name;
	private SimpleIntegerProperty price;
	private SimpleIntegerProperty sell;
	
	public Book(Integer code, String title, String name, int price, int sell) {
		super();
		this.code = new SimpleIntegerProperty(code);
		this.title=new SimpleStringProperty(title);
		this.name=new SimpleStringProperty(name);
		this.price=new SimpleIntegerProperty(price);
		this.sell=new SimpleIntegerProperty(sell);
	}
	
	public void setCode(Integer code) {
		this.code.set(code);
	}
	
	public Integer getCode() {
		return this.code.get();
	}
	
	public SimpleIntegerProperty codeProperty() {
		return this.code;
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
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public String getName() {
		return this.name.get();
	}
	
	public SimpleStringProperty nameProperty() {
		return this.name;
	}
	
	public void setPrice(Integer price) {
		this.price.set(price);
	}
	
	public Integer getPrice() {
		return this.price.get();
	}
	
	public SimpleIntegerProperty priceProperty() {
		return this.price;
	}
	
	public void setSell(Integer sell) {
		this.sell.set(sell);
	}
	
	public Integer getSell() {
		return this.sell.get();
	}
	
	public SimpleIntegerProperty sellProperty() {
		return this.sell;
	}
}
