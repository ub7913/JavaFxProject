package view_pack;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewController implements Initializable {
	@FXML ListView<String> listView;
	@FXML TableView<Phone> tableView;
	@FXML ImageView imageView;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// 초기화 지정.
		ObservableList<String> list = FXCollections.observableArrayList("GalaxyS1","GalaxyS2","GalaxyS3","GalaxyS4");
		list.add("GalaxyS5");
		list.add("GalaxyS6");
		list.add("GalaxyS7"); //이렇게 추가 해도 되고 위와 같이 바로 매개값으로 넣어도 된다
		listView.setItems(list); 
		
		listView.getSelectionModel().selectedIndexProperty()//선택된 값을 읽어 오는 메소드,api에 가면 인덱스와 아이템을 가져와 읽는 방식 두가지가 있다.selectedIndexProperty():selectedIndex의 속성을 가져온다. 
		.addListener(new ChangeListener<Number>() {//selectedIndexProperty()바뀌는 속성값을 듣고있다가 가져온다.

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldVal, Number newVal) {
				tableView.getSelectionModel().select(newVal.intValue());//Number 클래스에 intValue()라는 메소드가 있어 인트타입으로 바꿔준다.intValue():소수점을 정수로 바꿔주는 메소드
				tableView.scrollTo(newVal.intValue());//해당하는 위치로 스크롤이 내려간다.
			}
		});//선택된 값을 읽어 오는 메소드,api에 가면 인덱스와 아이템을 가져와 읽는 방식 두가지가 있다.selectedIndex의 
		
		tableView.setItems(FXCollections.observableArrayList( //위와 다르게 한번에 넣을예정
				new Phone("GalaxyS1", "phone01.png"),
				new Phone("GalaxyS2", "phone02.png"),
				new Phone("GalaxyS3", "phone03.png"),
				new Phone("GalaxyS4", "phone04.png"),
				new Phone("GalaxyS5", "phone05.png"),
				new Phone("GalaxyS6", "phone06.png"),
				new Phone("GalaxyS7", "phone07.png")
				));
		TableColumn<Phone, ?> tcSmartPhone = tableView.getColumns().get(0); //폰의 이름이 나오게 매칭 시켜주기,getColumns().get(0):첫번째 칼럼에 연결
		tcSmartPhone.setCellValueFactory(new PropertyValueFactory("smartPhone"));//속성값을 가져와서 쓰겠다. Phone클래스에 대한 smartPhone 필드의 속성을 가져와 쓰겠다는 의미
		//tableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("smartPhone")); //해석하자면 smartPhone의 속성을 첫번째 칼럼의 셀들에 하나씩 담겠다는 의미(위의코드와 동일)
		
		TableColumn<Phone, ?> tcImage=tableView.getColumns().get(1);//getColumns().get(1):두번째 칼럼에 연결
		tcImage.setCellValueFactory(new PropertyValueFactory("image"));//image의 속성값을 칼럼에 두번째 칼럼에 연결함
		
		tableView.getSelectionModel().selectedItemProperty()//ItemProperty:한건에 대한 속성을 리턴 해줌
		.addListener(new ChangeListener<Phone>() {

			@Override
			public void changed(ObservableValue<? extends Phone> arg0, Phone oldVal, Phone newVal) {//이번에는 위와 다르게 인덱스 값이 아닌 속성 값을 가져 와야 한다.
				imageView.setImage(new Image("/images/"+newVal.getImage()));//경로를 위해서 위에서 또 선언 해줘야 한다.
			}
			
		});
		
		//imageView.setImage(new Image("/images/phone01.png"));
	}
	
	public void handleBtnOkAction(){
		
	}
	
	public void handleBtnCancelAction() {
		Platform.exit();
	}
	
}
