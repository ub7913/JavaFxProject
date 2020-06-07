package stage_pack;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Root.fxml
//AddForm.fxml
//ScoreChart.fxml

public class StageMain extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Root.fxml")); //정적메소드를 사용해서 바로 가져 오는 방법
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
//		FXMLLoader loader = new FXMLLoader(); //인스턴스를 하나 만들어서 로드를 호출
//		Parent root = loader.load(getClass().getResource("Root.fxml"));
//		
//		//Controller에 stage값을 넘겨준다.
//		StageController cont = loader.getController();//컨트롤러를 가져오는 메소드
//		cont.setPrimaryStage(stage);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
