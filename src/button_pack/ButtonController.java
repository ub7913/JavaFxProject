package button_pack;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ButtonController implements Initializable {
	
	@FXML CheckBox chk1;
	@FXML CheckBox chk2;
	@FXML ImageView imageView;
	@FXML ToggleGroup group;
	@FXML RadioButton rad1;
	@FXML RadioButton rad2;
	@FXML RadioButton rad3;
	@FXML ImageView imageView2;
	@FXML Button exitbtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> arg0, Toggle oldVal, Toggle newVal) {
				System.out.println("/images/"+newVal.getUserData().toString()+".png");//control에 지정 해놓은 userData가 getUserData()로 newVal의 속성을 표시한다.(BubbleChart, BarChart..)
				imageView2.setImage(new Image("/images/"+newVal.getUserData().toString()+".png"));//getUserData()필요한 이유는 경로의 파일을 가져 오기 위해 쓰는 것임!
			}
			
		});
		
		chk2.setOnAction((e) -> handleCheckAction(e));// 아래 코드를 람다식으로 표현
		
		chk1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				handleCheckAction(event); //ButtonControl.fxml의 체크 박스들의 onAction을 없애고 이렇게 코드를 짜줄 수 있다.
			}
			
		});
		exitbtn.setOnAction((e) -> Platform.exit());
	}
	
	public void handleCheckAction(ActionEvent event) {
		String imageName = "";
		if (chk1.isSelected() && chk2.isSelected()) { //두개값이 참인 경우를 먼저 조건으로 걸어줘야지 이 조건의 결과값을 가져 올수 있다.
			imageName = "geek-glasses-hair.gif";
		} else if(chk1.isSelected()) {
			imageName = "geek-glasses.gif";
		} else if(chk2.isSelected()) {
			imageName = "geek-hair.gif";
		} else {
			imageName = "geek.gif";
		}
		imageView.setImage(new Image("/images/" + imageName));
	}
	
		
	
//	public void handleCheckAction(ActionEvent event) {
//		if (chk1.isSelected() && chk2.isSelected()) {
//			imageView.setImage(new Image(getClass().getResource("../images/geek-glasses-hair.gif").toString()));
//		} else if (chk1.isSelected()) {
//			imageView.setImage(new Image(getClass().getResource("../images/geek-glasses.gif").toString()));
//		} else if (chk2.isSelected()) {
//			imageView.setImage(new Image(getClass().getResource("../images/geek-hair.gif").toString()));
//		} else {
//			imageView.setImage(new Image(getClass().getResource("../images/geek.gif").toString()));
//		}
//	}
}
