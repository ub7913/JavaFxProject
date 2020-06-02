package input_pack;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class InputController implements Initializable {

	@FXML
	private TextField txtTitle;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private ComboBox<String> comboPublic;
	@FXML
	private DatePicker dateExit;
	@FXML
	private TextArea txtContent;
	@FXML
	Button btnReg;
	@FXML
	Button btnCancel;
	
	Connection conn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		String url ="jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url,"hr","hr");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleBtnRegAction(ActionEvent e) {
		if(txtTitle.getText() == null || txtTitle.getText().equals("")) {
			System.out.println("제목을 입력하세요");
		} else if(txtPassword.getText() == null || txtPassword.getText().equals("")) {
			System.out.println("비밀번호를 입력하세요");
		} else if(comboPublic.getValue() == null || comboPublic.getValue().equals("")) {
			System.out.println("공개여부를 입력하세요");
		} else if(dateExit.getValue() == null || dateExit.getValue().equals("")) {
			System.out.println("종료일을 입력하세요");
		} else if(txtContent.getText() == null || txtContent.getText().equals("")) {
			System.out.println("내용을 입력하세요");
		} else { //디비입력
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			String sql = "insert into board(title,password,publicity,exit_date,content)"
				+ "values(?,?,?,?,?)";
			try {//예외처리 필요
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, txtTitle.getText());
				pstmt.setString(2, txtPassword.getText());
				pstmt.setString(3, comboPublic.getValue());
				pstmt.setString(4, dateExit.getValue().format(formatter));
				pstmt.setString(5, txtContent.getText());
				
				int r = pstmt.executeUpdate();
				System.out.println(r+" 건 입력됨");
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			//각 필드 초기화
			txtTitle.setText(null);
			txtPassword.setText(null);
			comboPublic.setValue("공개");
			dateExit.setValue(null);
			txtContent.setText(null);
		}
	}

	public void handleBtnCancelAction(ActionEvent e) {
		Platform.exit();
	}

}
