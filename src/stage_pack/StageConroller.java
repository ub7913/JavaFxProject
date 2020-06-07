package stage_pack;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageConroller implements Initializable {
	@FXML TableView<Student> tableView;
	@FXML Button btnAdd, btnChart;
	
	ObservableList<Student> scores;
	
//	Stage primaryStage; //메인클래스에 호출 하여 쓰기 위해
//	
//	void setPrimaryStage(Stage primaryStage) {
//		this.primaryStage = primaryStage;
//	} //메인클래스에 호출 하여 쓰기 위해
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		scores = FXCollections.observableArrayList();
		
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				buttonAddAction(arg0);
			}
			
		});
		btnChart.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				buttonChartAction(arg0);
			}
			
		});
		
		//테이블 뷰의 칼럼과 이름, 과목들을 매칭 시켜 주는 작업
		TableColumn<Student, ?> tcName = tableView.getColumns().get(0);
		tcName.setCellValueFactory(new PropertyValueFactory("name")); //PropertyValueFactory:student클래스에 있는 property들을 가져옴
		TableColumn<Student, ?> tcKorean = tableView.getColumns().get(1);
		tcKorean.setCellValueFactory(new PropertyValueFactory("korean"));
		TableColumn<Student, ?> tcMath = tableView.getColumns().get(2);
		tcMath.setCellValueFactory(new PropertyValueFactory("math"));
		TableColumn<Student, ?> tcEnglish = tableView.getColumns().get(3);
		tcEnglish.setCellValueFactory(new PropertyValueFactory("english"));
		
		tableView.setItems(scores);
		
	}
	
	public void buttonAddAction(ActionEvent ae) {
		Stage addStage = new Stage(StageStyle.UTILITY);
		addStage.initModality(Modality.WINDOW_MODAL);
		addStage.initOwner(btnAdd.getScene().getWindow());//addStage에 주 윈도우 설정
		
		try { //씬을 불러와야 함
			Parent parent = FXMLLoader.load(getClass().getResource("AddForm.fxml")); //컨트롤들을 담을 수 있는 최상위의 컨테이너 Parent타입을 지정
			Scene scene = new Scene(parent);
			addStage.setScene(scene);
			addStage.setResizable(false);
			addStage.show();		
			
			Button btnFormAdd = (Button) parent.lookup("#btnFormAdd");//parent의 하위 클래스에서 아이디값을 가져 오게 하는 것
			btnFormAdd.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					TextField txtName = (TextField) parent.lookup("#txtName");
					TextField txtKorean = (TextField) parent.lookup("#txtKorean");
					TextField txtMath = (TextField) parent.lookup("#txtMath");
					TextField txtEnglish = (TextField) parent.lookup("#txtEnglish");
					Student student = new Student(txtName.getText(),
							Integer.parseInt(txtKorean.getText()), //문자열을 숫자열로 바꿔줌
							Integer.parseInt(txtMath.getText()), 
							Integer.parseInt(txtEnglish.getText())
					);
					scores.add(student);
					addStage.close();
				}
				
			});
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	public void buttonChartAction(ActionEvent ae) {
		Stage chartStage = new Stage(StageStyle.UTILITY);
		chartStage.initModality(Modality.WINDOW_MODAL);
		chartStage.initOwner(btnChart.getScene().getWindow());//primaryStage
		
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("ScoreChart.fxml"));
			BarChart barChart = (BarChart) parent.lookup("#barChart");
			
			XYChart.Series<String, Integer> seriesKorean = new XYChart.Series<String, Integer>();
			ObservableList<XYChart.Data<String, Integer>> datasKorean = FXCollections.observableArrayList();
			for (int i=0; i<scores.size(); i++) {
				datasKorean.add(new XYChart.Data(scores.get(i).getName(),scores.get(i).getKorean()));	
				//이름,국어점수/ 차트에 시리즈를 담고 싶은데 시리즈의 데이터들은 스코어에 담겨 있기 때문에 반복문을 통해서 담아준다.
			}
			seriesKorean.setData(datasKorean);
			
			XYChart.Series<String, Integer> seriesMath = new XYChart.Series<String, Integer>();
			ObservableList<XYChart.Data<String, Integer>> datasMath = FXCollections.observableArrayList();
			for (int i=0; i<scores.size(); i++) {
				datasMath.add(new XYChart.Data(scores.get(i).getName(),scores.get(i).getMath()));	
				//이름,수학점수/ 차트에 시리즈를 담고 싶은데 시리즈의 데이터들은 스코어에 담겨 있기 때문에 반복문을 통해서 담아준다.
			}
			seriesMath.setData(datasMath);
			
			XYChart.Series<String, Integer> seriesEnglish = new XYChart.Series<String, Integer>();
			ObservableList<XYChart.Data<String, Integer>> datasEnglish = FXCollections.observableArrayList();
			for (int i=0; i<scores.size(); i++) {
				datasEnglish.add(new XYChart.Data(scores.get(i).getName(),scores.get(i).getEnglish()));	
				//이름,수학점수/ 차트에 시리즈를 담고 싶은데 시리즈의 데이터들은 스코어에 담겨 있기 때문에 반복문을 통해서 담아준다.
			}
			seriesEnglish.setData(datasEnglish);
			
			barChart.setData(FXCollections.observableArrayList(seriesKorean,seriesMath,seriesEnglish));
			
			seriesKorean.setName("국어");
			seriesMath.setName("수학");
			seriesEnglish.setName("영어");
			
			Scene scene = new Scene(parent);
			chartStage.setScene(scene);
			chartStage.show();
			chartStage.setResizable(false);
			
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
