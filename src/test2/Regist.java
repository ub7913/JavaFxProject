package test2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Regist {
	private SimpleIntegerProperty id;
	private SimpleStringProperty name;
	private SimpleIntegerProperty age;
	private SimpleStringProperty birth;
	private SimpleStringProperty gender;
	private SimpleStringProperty breed;
	private SimpleIntegerProperty weight;
	private SimpleStringProperty nuet;
	private SimpleStringProperty host;
	private SimpleIntegerProperty phone;
	private SimpleStringProperty image;
	
	public Regist(Integer id, String name, Integer age, String birth, String gender,
				  String breed, Integer weight, String nuet, String host, Integer phone, String image) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.age = new SimpleIntegerProperty(age);
		this.birth = new SimpleStringProperty(birth);
		this.gender = new SimpleStringProperty(gender);
		this.breed = new SimpleStringProperty(breed);
		this.weight = new SimpleIntegerProperty(weight);
		this.nuet = new SimpleStringProperty(nuet);
		this.host = new SimpleStringProperty(host);
		this.phone = new SimpleIntegerProperty(phone);
		this.image = new SimpleStringProperty(image);
	}
	
	public void setId(Integer id) {
		this.id.set(id);
	}
	
	public Integer getId() {
		return this.id.get();
	}
	
	public SimpleIntegerProperty idProperty() {
		return this.id;
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
	
	public void setAge(Integer age) {
		this.age.set(age);
	}
	
	public Integer getAge() {
		return this.age.get();
	}
	
	public SimpleIntegerProperty ageProperty() {
		return this.age;
	}
	
	public void setBirth(String birth) {
		this.birth.set(birth);
	}
	
	public String getBirth() {
		return this.birth.get();
	}
	
	public SimpleStringProperty birthProperty() {
		return this.birth;
	}
	
	public void setGender(String gender) {
		this.gender.set(gender);
	}
	
	public String getGender() {
		return this.gender.get();
	}
	
	public SimpleStringProperty genderProperty() {
		return this.gender;
	}
	
	public void setBreed(String breed) {
		this.breed.set(breed);
	}
	
	public String getBreed() {
		return this.breed.get();
	}
	
	public SimpleStringProperty breedProperty() {
		return this.breed;
	}
	
	public void setWeight(Integer weight) {
		this.weight.set(weight);
	}
	
	public Integer getWeight() {
		return this.weight.get();
	}
	
	public SimpleIntegerProperty weightProperty() {
		return this.weight;
	}
	
	public void setNuet(String nuet) {
		this.nuet.set(nuet);
	}
	
	public String getNuet() {
		return this.nuet.get();
	}
	
	public SimpleStringProperty nuetProperty() {
		return this.nuet;
	}
	
	public void setHost(String host) {
		this.host.set(host);
	}
	
	public String getHost() {
		return this.host.get();
	}
	
	public SimpleStringProperty hostProperty() {
		return this.host;
	}
	
	public void setPhone(Integer phone) {
		this.phone.set(phone);
	}
	
	public Integer getPhone() {
		return this.phone.get();
	}
	
	public SimpleIntegerProperty phoneProperty() {
		return this.phone;
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
	
}


