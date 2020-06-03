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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
			messageDialog("제목을 입력하세요");
		} else if(txtPassword.getText() == null || txtPassword.getText().equals("")) {
			messageDialog("비밀번호를 입력하세요");
		} else if(comboPublic.getValue() == null || comboPublic.getValue().equals("")) {
			messageDialog("공개여부를 입력하세요");
		} else if(dateExit.getValue() == null || dateExit.getValue().equals("")) {
			messagePopup("종료일을 입력하세요");
		} else if(txtContent.getText() == null || txtContent.getText().equals("")) {
			messagePopup("내용을 입력하세요");
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
		}//end of if..
	}//end of handleBtnRegAction()

	public void handleBtnCancelAction(ActionEvent e) {
		Platform.exit();
	}//end of handleBtnCancelAction
	
	public void messageDialog(String message) {//팝업윈도우 만들기
		
		Stage customStage = new Stage(StageStyle.UTILITY); // api를 보니 열거형 타입임
		customStage.initModality(Modality.WINDOW_MODAL);//모달 속성에 대해 정해줌
		customStage.initOwner(btnReg.getScene().getWindow());//주인 윈도우가 필요하다
		customStage.setTitle("확인");
		
		AnchorPane ap = new AnchorPane();
		ap.setPrefSize(400, 150);
		
		ImageView imageView = new ImageView();
		imageView.setImage(new Image("/icons/dialog-info.png"));//생성자에 이미지 경로를 적어줘야 생성됨
		imageView.setFitHeight(50);
		imageView.setPreserveRatio(true);//이미지의 원래 비율을 유지한채로 가져옴
		imageView.setLayoutX(15);//위치배치
		imageView.setLayoutY(15);//위치배치
		
		Button button = new Button("확인");
		button.setLayoutX(336);//위치배치
		button.setLayoutY(104);//위치배치
		button.setOnAction(e -> customStage.close());
		
		Label label = new Label(message);
		label.setLayoutX(87);//위치배치
		label.setLayoutY(33);//위치배치
		label.setPrefHeight(15);
		label.setPrefWidth(290);
		
		ap.getChildren().add(imageView);
		ap.getChildren().add(button);
		ap.getChildren().add(label);
		
		Scene scene = new Scene(ap);
		customStage.setScene(scene);
		customStage.show();
		
	}
	
	public void messagePopup(String message) {//팝업창을 만들기 위한 메소드, stage와 scene이 없는 팝업창
		
		//팝업창의 컨테이너
		HBox hbox = new HBox();
		hbox.setStyle("-fx-background-color: black; -fx-background-radius: 20; ");
		hbox.setAlignment(Pos.CENTER);
		
		//컨테이너에 담길 컨트롤 이미지뷰
		ImageView imageView = new ImageView();
		imageView.setImage(new Image("/icons/dialog-info.png"));
		imageView.setFitHeight(30);
		imageView.setFitWidth(30);
		
		//컨테이너에 담길 라벨
		Label label = new Label();
		HBox.setMargin(label, new Insets(0,5,0,5));
		label.setText(message);
		label.setStyle("-fx-text-fill: white; ");
		
		//컨트롤들을 컨테이너에 담음
		hbox.getChildren().add(imageView);//이미지뷰를 컨테이너에 담았다
		hbox.getChildren().add(label);//라벨을 컨테이너에 담았다.
		
		//팝업생성. 컨테이너에 담아서 호출
		Popup popup = new Popup();
		popup.getContent().add(hbox);//팝업안에 hbox컨테이너를 담음 //모달방식 새로운 창이 뜨면 뒤의 창을 사용 못하는 것 반대로 모달리스방식은 팝업창이 떠도 뒤의 창을 사용 할 수 있는방식
		popup.setAutoHide(true);//언제 닫는지 지정. 다른곳 클릭하면 사라짐
		popup.show(btnReg.getScene().getWindow());//btnReg컨트롤이 소속되어있는 것을 가져 온다
		
	}//end of popup
	
}//end of class
