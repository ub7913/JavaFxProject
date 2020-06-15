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
		
		ObservableList<Regist> RegistList = getRegistList();
		
		
		TableColumn<Regist, String> tcId = new TableColumn<>();
		tcId.setCellValueFactory(new PropertyValueFactory<Regist, String>("id"));
		tcId.setText("번호");
		
		tableView.getColumns().add(tcId);
		
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
		//tableView.setItems(SearchList());
		tableView.setItems(RegistList);
//		tableView.setItems(DeleteList());
		
		
//		TableColumn<Regist, String> tcId = new TableColumn<Regist, String>();
	//	tcId.setCellValueFactory(new Callback<CellDataFeatures<Regist, String>, ObservableValue<String>> {
			
	//	})
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
//		} else if (event.getSource() == btnSearch) {
//			SerachList();
		} else if (event.getSource() == btnDelete) {
//			DeleteList();
		}
	}
	
	public ObservableList<Regist> getRegistList() {
		ObservableList<Regist> list = FXCollections.observableArrayList();
		String sql = "select id, pet_name, pet_age, pet_birthdate, pet_gender, "
				+ "	  pet_breed, pet_weight, pet_nuet, host_name, host_phone, image, cur_date from Registration1";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
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
	
//	private ObservableList<Regist> list;
	
//	public ObservableList<Regist> SearchList() {
//		ObservableList<Regist> list = FXCollections.observableArrayList();
//		String sql = "select * from registration1 where pet_name = ?";
//		try {
//			PreparedStatement pstmt= conn.prepareStatement(sql);
//			pstmt.setString(1, txtName.getText());
//			pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
	
//	public ObservableList<Regist> DeleteList() {
//		String sql = "delete from registration1 where id=?";
//		ObservableList<Regist> list = FXCollections.observableArrayList();
//		try {
//			PreparedStatement pstmt= conn.prepareStatement(sql);
//			ResultSet rs = pstmt.executeQuery();
//			pstmt.setString(1, txtNum.getText());
//			pstmt.executeUpdate();
//			while(rs.next()) {
//				Regist regist = new Regist(rs.getInt("id"), rs.getString("pet_name"), rs.getInt("pet_age"),
//						   rs.getString("pet_birthdate"), rs.getString("pet_gender"), rs.getString("pet_breed"),
//						   rs.getInt("pet_weight"), rs.getString("pet_nuet"), rs.getString("host_name"), rs.getInt("host_phone"),
//						   rs.getString("image"), rs.getString("cur_date"));
//				list.add(regist);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
		
//	}
	
}
