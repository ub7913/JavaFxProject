package fxml;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AppMain extends Application { //main class Root.fxml을 호출 해 윈도우 창을 만드는 역할

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox root = new HBox();// HBox는 옆으로 컨트롤을 배치시키는 컨테이너 박스이다
		root.setPadding(new Insets(10)); // 컨트롤과 컨테이너와의 간격(여백)을 설정 Insets(10,10,10,10)아래위좌우를 각각 값을 줄 수 있다
		root.setSpacing(10);// 컨트롤간의 수평간격
		root.setPrefSize(700, 300);

		TextField textField = new TextField();
		textField.setPrefWidth(200);
		textField.setPrefHeight(100);

		Button button = new Button();
		button.setText("확인");
		button.setPrefSize(100, 100);
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				textField.setText("확인을 눌렀습니다");
			}
		});

		Button button1 = new Button();
		button1.setText("취소");
		button1.setPrefSize(100, 100);
		button1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				textField.setText(null);
			}
		});

		ObservableList list = root.getChildren();
		list.add(textField);
		list.add(button);
		list.add(button1);

		Scene scene = new Scene(root);

		primaryStage.setTitle("AppMain");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				System.out.println(event);
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}
