package view_pack;

import javafx.beans.property.SimpleStringProperty;

public class Phone {
	private SimpleStringProperty smartPhone;
	private SimpleStringProperty image; //문자열로 속성을 정하겠다는 의미
	
	Phone(String smartPhone, String image) {
		this.smartPhone=new SimpleStringProperty(smartPhone);//문자열(smartPhone)을 받아서 SimpleStringProperty의 객체를 만듬
		this.image=new SimpleStringProperty(image);
	}
	
	public void setSmartPhone(String smartPhone) {
		this.smartPhone.set(smartPhone);
	}
	public String getSmartPhone() {
		return this.smartPhone.get();
	}
	public SimpleStringProperty smartPhoneProperty() {
		return this.smartPhone;
	}
	
	public void setImage(String image) {
		this.image.set(image);
	}
	public String getImage() {
		return this.image.get();
	}
	public SimpleStringProperty imageProperty() {
		return this.image;
	}
	//이전에 하는 방법과 다른 이유는 속성인 property를 가져와야 하기 때문이다. 문자열이 가지고있는 속성값을 활용하기 위해
}
