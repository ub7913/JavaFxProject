package fxml;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox root = new HBox();//HBox는 옆으로 컨트롤을 배치시키는 컨테이너 박스이다
		root.setPadding(new Insets(10)); //컨트롤과 컨테이너와의 간격(여백)을 설정 Insets(10,10,10,10)아래위좌우를 각각 값을 줄 수 있다
		root.setSpacing(10);//컨트롤간의 수평간격
		
		TextField textField = new TextField();
		textField.setPrefWidth(200);
		
		Button button = new Button();
		button.setText("확인");
		
		ObservableList list = root.getChildren();
		list.add(textField);
		list.add(button);
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("AppMain");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
