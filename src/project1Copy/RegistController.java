package project1Copy;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegistController implements Initializable {
	@FXML Button btnAdd, btnDelete, btnSearch;
	@FXML TableView<Regist> tableView;
	@FXML TextField txtNum, txtName, txtBreed, txtAge, txtWeight, dateBirth, txtSearch, txtHost, txtPhone, dateRegist;
	@FXML ComboBox<String> comboGender, comboNuet;
	@FXML ImageView imgImage;
	
	ObservableList<Regist> registList;
	Connection conn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(url,"hr","hr");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		registList = getRegistList(null);
		
		TableColumn<Regist, ?> tcNum = tableView.getColumns().get(0);
		tcNum.setCellValueFactory(new PropertyValueFactory("id"));
		TableColumn<Regist, ?> tcName = tableView.getColumns().get(1);
		tcName.setCellValueFactory(new PropertyValueFactory("name"));
		TableColumn<Regist, ?> tcHost = tableView.getColumns().get(2);
		tcHost.setCellValueFactory(new PropertyValueFactory("host"));
		TableColumn<Regist, ?> tcRd = tableView.getColumns().get(3);
		tcRd.setCellValueFactory(new PropertyValueFactory("registDay"));
//		TableColumn<Regist, String> tcId = new TableColumn<>();
//		tcId.setCellValueFactory(new PropertyValueFactory<Regist, String>("id"));
//		tcId.setText("번호");
//		
//		tableView.getColumns().add(tcId); 신빌더로 컬럼 만들지 않고 컬럼 연결
		
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Regist>() {
			
			@Override
			public void changed(ObservableValue<? extends Regist> arg0, Regist oldVal, Regist newVal) {
				if (newVal == null) {
					return;
				}
				txtNum.setText(String.valueOf(newVal.getId()));
				txtName.setText(newVal.getName());
				txtBreed.setText(newVal.getBreed());
				txtAge.setText(String.valueOf(newVal.getAge()));
				txtWeight.setText(String.valueOf(newVal.getWeight()));
				txtHost.setText(newVal.getHost());
				txtPhone.setText(String.valueOf(newVal.getPhone()));
				comboGender.setValue(newVal.getGender());
				comboNuet.setValue(newVal.getNuet());
				dateBirth.setText(newVal.getBirth());
				dateRegist.setText(newVal.getRegistDay());
				imgImage.setImage(new Image("file:///"+newVal.getImage()));
			}
			
		});
		tableView.setItems(registList);

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
		} else if (event.getSource() == btnSearch) {
			searchList();
		} else if (event.getSource() == btnDelete) {
			deleteList();
		}
	}
	
	public ObservableList<Regist> getRegistList(String name) {
		System.out.println("실행"+txtSearch.getText());
		ObservableList<Regist> list = FXCollections.observableArrayList();
		String sql = "select id, pet_name, pet_age, pet_birthdate, pet_gender, "
				+ "	  pet_breed, pet_weight, pet_nuet, host_name, host_phone, image, cur_date from Registration1 where pet_name like ?||'%'";// "?||'%'" ?는 아래 검색때 필요한 매개변수를 담는것 ||(연결연산자) '%'null값을 받을 수 있도록(첫화면에 리스트를 뿌릴수 있도록) 
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, txtSearch.getText());//이 메소드에서 매개변수 선언 없이 검색하기위한 코드
			//name = txtSearch.getText(); 이미 searchList() 안의 getRegistList메소드에 txtSearch.getText()를 매개변수를 넣어서 필요없음
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Regist regist = new Regist(rs.getInt("id"), rs.getString("pet_name"), rs.getInt("pet_age"),
										   rs.getString("pet_birthdate"), rs.getString("pet_gender"), rs.getString("pet_breed"),
										   rs.getInt("pet_weight"), rs.getString("pet_nuet"), rs.getString("host_name"), rs.getInt("host_phone"),
										   rs.getString("image"), rs.getString("cur_date"));
				list.add(regist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public void searchList() {
		
		registList = getRegistList(txtSearch.getText());//("g") 같이 매개변수 안에 아무 문자나 쓰려면 위에서  name = txtSearch.getText(); 선언해주면 된다. 
		tableView.setItems(registList);
	}
	
	public void deleteList() {
		
		String sql = "delete from registration1 where id=?";
		try {
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, txtNum.getText());
			pstmt.executeUpdate();
			
//			int i=0;
//			for(Regist r : registList) {
//				if(r.getId() == Integer.parseInt(txtNum.getText())) {
//					registList.remove(i);
//					System.out.println("삭제");
//					break;
//				}
//				++i;
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tableView.setItems(getRegistList(null));
		
		txtNum.clear();
		txtName.clear();
		txtBreed.clear();
		txtAge.clear();
		txtWeight.clear();
		txtHost.clear();
		txtPhone.clear();
		comboGender.setValue(null);
		comboNuet.setValue(null);
		dateBirth.setText(null);
		dateRegist.setText(null);
		imgImage.setImage(null);
	}
	
}
