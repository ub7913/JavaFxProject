package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RootController implements Initializable { //이벤트를 만들어 주기 위한 클래스
	@FXML
	TextField textField; //UI에서 fx:id로 지정했던 것을 여기서 만들어 줘야 한다.
	@FXML Button btnOk;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		textField.setText("초기화합니다");
		btnOk.setOnAction(new EventHandler<ActionEvent>() {//버튼의 이벤트를 구현 해주는 익명객체 만들어 주기

			@Override
			public void handle(ActionEvent arg0) {
				Platform.exit();
			}
			
		});
	}

}
