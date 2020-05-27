package Basic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AppMain extends Application { // 오류나면 라이브러리 바꿔주기, 라이브러리 바꾸고 재정의 해주기

	public AppMain() { //기본 생성자 호출
		System.out.println(Thread.currentThread().getName()+" : AppMain() 생성자 실행");
	}
	
	@Override
	public void init() throws Exception {
		System.out.println(Thread.currentThread().getName()+" : init() 메소드 실행");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println(Thread.currentThread().getName()+" : start() 메소드 실행");
		VBox root = new VBox();//컨테이너 박스 만듬, 제일 외곽에 있는 것 최상위 컨터에너 root, vbox는 컨트롤을 수직으로 배치함
		root.setPrefWidth(350);//컨테이너 박스 가로 길이
		root.setPrefHeight(150);//컨테이너 박스 세로 길이
		root.setAlignment(Pos.CENTER);//컨트롤을 가운데로 정렬하게 함
		root.setSpacing(20);//컨트롤과 컨트롤 사이의 간격을 줌
		
		Label label = new Label(); //임포트 해주기 javafx.scene.control
		label.setText("Hello, JavaFX");
		label.setFont(new Font(50));//폰트 클래스도 임포트 해주기, 매개값이 없는 디폴트 생성자 Font()가 없기 때문에 안에 매개값을 넣어 줘야 한다. 라벨의 글씨가 커짐
		
		Button button = new Button();//임포트 해주기
		button.setText("확인");
		button.setOnAction(event -> Platform.exit());//EventHandler는 functional인터페이스라서 람다식으로 쓸수 있음
//		button.setOnAction(new EventHandler<ActionEvent>() { //익명구현객체를 만들어서 이벤트를 발생 시킴
//			@Override
//			public void handle(ActionEvent event) {
//				Platform.exit(); //확인 버튼을 눌렀을때 화면이 닫히는 이벤트
//			} 
//		});
		
		root.getChildren().add(label);//컨트롤들을 담을 수 있게 하는 메소드
		root.getChildren().add(button);
		
		Scene scene = new Scene(root);//import해주기, 이 scene에 컨터이너를 담을 예정임
		primaryStage.setScene(scene);//스테이지 안에 신을 담음
		primaryStage.show();//윈도우를 띄우겠다는의미, 실행하면 윈도우 창이 하나 뜸 이것을 스테이지라고 한다.
	}
	
	@Override
	public void stop() throws Exception { //윈도우창을 닫으니 메소드가 실행되면서 결과가 출력됨
		System.out.println(Thread.currentThread().getName()+" : stop() 메소드 실행");
	}

	public static void main(String[] args) {
		Application.launch(args); //Application클래스가 가지고 있는 런치를 실행 시키니 위와 같이 실행된다.
	}
	
}
