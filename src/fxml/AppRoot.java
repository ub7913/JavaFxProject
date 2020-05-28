package fxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AppRoot extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		/*HBox*/Parent root = FXMLLoader.load(getClass().getResource("Root.fxml")); //FXML을 불러오는 코드, getClass() : 현재 실행되는 클래스에  getResource("Root.fxml") : Root.fxml 파일의 리소스(파일,디렉토리 등)를 가져오겠다는 의미, 
		        //타입을 맞출수 있는상위클래스												//만약 다른 위치에 있으면  ../control/root.fxml 등등 이렇게 써서 상대 경로를 찾아 오면 된다.
		Scene scene = new Scene(root);//Scene은 매개값이 없는 생성자는 없기에 매개값이 무조건 들어가줘야 한다. 컨테이너(HBox, VBox 등)
		
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("FXML sample");//윈도우 창에 이름을 주는 역할
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
