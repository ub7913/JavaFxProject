package com.yedam.control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.yedam.common.ConnectionUtil;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author oXCToo
 */
public class LoginController implements Initializable {

	@FXML
	private Label lblErrors;

	@FXML
	private TextField txtUsername;

	@FXML
	private TextField txtPassword;

	@FXML
	private Button btnSignin;
	@FXML
	private Button btnSignup;

	/// --
	Connection con = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public LoginController() {
		con = ConnectionUtil.conDB();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		if (con == null) {
			lblErrors.setTextFill(Color.TOMATO);
			lblErrors.setText("Server Error : Check");
		} else {
			lblErrors.setTextFill(Color.GREEN);
			lblErrors.setText("Server is up : Good to go");
		}
	}

	@FXML
	public void handleButtonAction(MouseEvent event) { //로그인 화면의 모든 버튼에 걸어줄 메소드

		System.out.println(event.getSource());

		if (event.getSource() == btnSignin) { //btnSignin의 이름을 가진 버튼을 눌렀을때 그 버튼의 소스를 가져와 이벤트 하겠다
			// login here
			if (logIn().equals("Success")) { //logIn()의 메소드가 성공적으로 이뤄 졌을때 "Success"를 리턴하는데 이것이 참이라면 아래 구문을 실행
				try {

					// add you loading or delays - ;-)
					Node node = (Node) event.getSource();//btnSignin의 소스를 가져와 node에 담는다.
					Stage stage = (Stage) node.getScene().getWindow();//담겨있는 소스의 윈도우와 신을 stage에 담는다
					// stage.setMaximized(true);
					stage.close();//stage를 닫는다
					Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/OnBoard.fxml")));
					stage.setScene(scene);
					stage.show();

				} catch (IOException ex) {
					System.err.println(ex.getMessage());
				}

			}

		} else if (event.getSource() == btnSignup) {
			System.out.println("signup");
			if (signUp().equals("Success")) {
				setLblError(Color.DARKRED, "new User Created.");

			} else {
				setLblError(Color.DARKRED, "error.");

			}
		}
	}

	private String signUp() {
		String status = "Success";
		String email = txtUsername.getText();
		String password = txtPassword.getText();
		if (email.isEmpty() || password.isEmpty()) {
			setLblError(Color.TOMATO, "Empty credentials");
			status = "Error";
		} else {
			// query
			String sql = "insert into admins values(?, ?)";
			try {
				preparedStatement = con.prepareStatement(sql);
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, password);
				int r = preparedStatement.executeUpdate();
				if (r == 0) {
					setLblError(Color.TOMATO, "DB error");
					status = "Error";
				} else {
					setLblError(Color.GREEN, "admin user created.");
				}
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
				status = "Exception";
			}
		}

		return status;
	}

	// we gonna use string to check for status
	private String logIn() {
		String status = "Success";
		String email = txtUsername.getText();
		String password = txtPassword.getText();
		if (email.isEmpty() || password.isEmpty()) {
			setLblError(Color.TOMATO, "Empty credentials");
			status = "Error";
		} else {
			// query
			String sql = "SELECT * FROM admins Where email = ? and password = ?";
			try {
				preparedStatement = con.prepareStatement(sql);
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, password);
				resultSet = preparedStatement.executeQuery();
				if (!resultSet.next()) {
					setLblError(Color.TOMATO, "Enter Correct Email/Password");
					status = "Error";
				} else {
					setLblError(Color.GREEN, "Login Successful..Redirecting..");
				}
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
				status = "Exception";
			}
		}

		return status;
	}

	private void setLblError(Color color, String text) {
		lblErrors.setTextFill(color);
		lblErrors.setText(text);
		System.out.println(text);
	}
}
