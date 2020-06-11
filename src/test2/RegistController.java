package test2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegistController implements Initializable {
	@FXML Button btnAdd, btnDelete, btnSearch;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	
	
	public void handleBtn(MouseEvent event) {
		if (event.getSource() == btnAdd) {
			try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			stage.close();
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("PetRegistration.fxml")));
			stage.setScene(scene);
			stage.show();
			} catch(IOException ex) {
				System.err.println(ex.getMessage());
			} 
		}
	}
}
