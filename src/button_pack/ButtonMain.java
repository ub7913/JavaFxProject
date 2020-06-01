package button_pack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Control:ButtonControl.fxml
//Controller:ButtonController.java
public class ButtonMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		/*VBox*/Parent root = FXMLLoader.load(getClass().getResource("ButtonControl.fxml"));//fxml의 타입은 BoarderPane인데 여기는 VBox로 받았기 때문에 에러가 난다. 그래서 , Parent클래스를 써야 아무 타입의 컨테이너를 받을 수 있다.
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
