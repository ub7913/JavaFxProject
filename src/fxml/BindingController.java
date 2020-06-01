package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

public class BindingController implements Initializable {
	
	@FXML TextArea txtArea1 ;
	@FXML TextArea txtArea2 ;
	@FXML Label label;
	@FXML Slider slider;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		slider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number oldVal, Number newVal) { //oldVal는 원래값, newVal는 뒤에 바뀐값, ObservableValue 변화되는 속성을 감시하는 역할
				System.out.println(newVal);
				label.setFont(new Font(newVal.doubleValue()));
			}
			
		});;//속성값이 변할때마다 addListener가 듣고 있다가 이벤트를 처리 하겠다는 의미
		
	//	txtArea2.textProperty().bind(txtArea1.textProperty());//txtArea2의 속성을 txtArea1속성에 묶어 주겠다는 의미
	//	txtArea2.textProperty().bindBidirectional(txtArea1.textProperty());//첫번째 방법: 두 속성이 서로 묶여 지는것. 어느 곳에든지 입력하면 한쪽이 따라 입력됨
		Bindings.bindBidirectional(txtArea2.textProperty(), txtArea1.textProperty());//두번째방법
		Bindings.unbindBidirectional(txtArea2.textProperty(), txtArea1.textProperty());//바인딩 된 거 해제
	}
}
