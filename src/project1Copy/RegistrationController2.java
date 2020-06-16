package project1Copy;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class RegistrationController2 implements Initializable {
	
	@FXML TextField txtName, txtWeight, txtAge, txtBreed, txtPhone, txtHost;
	@FXML ComboBox<String> comboGender, comboNuet;
	@FXML DatePicker dateBirth;
	@FXML Button btnReg, btnPrev, btnImage;
	@FXML ImageView imgImage;
	
	Connection conn;
	String path=null;//handleBtnImageAction메소드의 결과값을 담기위한
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url,"hr","hr");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//입력된 값들을 DB에 담을 수 있도록 등록버튼에 이벤트달기
	public void handleBtnRegAction(ActionEvent e) {
		
//		btnImage.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent arg0) {
//				FileChooser fileChooser = new FileChooser();
//				fileChooser.setTitle("Open Resource File");
//				fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
//				File file = fileChooser.showOpenDialog(null);
//				String path = file.getPath();
//				System.out.println(path);
//			}
//			
//		});
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String sql = "INSERT INTO registration1(id,pet_name,"
					+ "pet_age,pet_birthdate,pet_gender,pet_breed,"
					+ "pet_weight,pet_nuet,host_name,host_phone,image,cur_date) "
					+ "VALUES(pet_id_seq.nextval,?,?,?,?,?,?,?,?,?,?,sysdate)";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, txtName.getText());
			pstmt.setString(2, txtAge.getText());
			pstmt.setString(3, dateBirth.getValue().format(formatter));
			pstmt.setString(4, comboGender.getValue());
			pstmt.setString(5, txtBreed.getText());
			pstmt.setString(6, txtWeight.getText());
			pstmt.setString(7, comboNuet.getValue());
			pstmt.setString(8, txtHost.getText());
			pstmt.setString(9, txtPhone.getText());
			pstmt.setString(10, path);
			
			
			int r = pstmt.executeUpdate();
			System.out.println(r+"건 입력됨");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		//등록버튼 누르면 입력된 값들 clearing하기
		txtName.clear();
		txtAge.clear();
		dateBirth.setValue(null);
		comboGender.setValue(null);
		txtBreed.clear();
		txtWeight.clear();
		comboNuet.setValue(null);
		txtHost.clear();
		txtPhone.clear();
		imgImage.setImage(null);
	}
	
	//이전레이아웃으로 돌아가도록 이전 버튼에 이벤트달기
	public void handleBtnPrevAction(ActionEvent e) {
		Stage stage = (Stage) btnPrev.getScene().getWindow();
		stage.close();
		
		try {
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("RegistrationChart.fxml")));
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	//사진등록 버튼을 클릭하면 사진의 경로를 가져 오도록하는 이벤트
	public void handleBtnImageAction(ActionEvent e) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		File file = fileChooser.showOpenDialog(null);
		path = file.getPath();
		System.out.println(path);
		imgImage.setImage(new Image("file:///"+path));
	}
	
}
