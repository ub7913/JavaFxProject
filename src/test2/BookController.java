package test2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BookController implements Initializable {
	@FXML TableView<Book> tableView;
	@FXML Button btnAdd, btnChart;
	@FXML TextField txtName;
	@FXML TextField txtName2;
	@FXML TextField txtTitle;
	@FXML TextField txtTitle2;
	@FXML TextField txtCode;
	@FXML TextField txtPrice;
	@FXML TextField txtPrice2;
	@FXML TextField txtSell;
	@FXML TextField txtSell2;
	
	ObservableList<Book> sales;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Book i = new Book(111, "aaa","aaa",11,111);
		Book e = new Book(112, "bbb","bbb",22,159);
		
		sales = FXCollections.observableArrayList();
		sales.add(i);
		sales.add(e);
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
		

		TableColumn<Book, ?> tcCode = tableView.getColumns().get(0);
		tcCode.setCellValueFactory(new PropertyValueFactory("code")); //PropertyValueFactory:student클래스에 있는 property들을 가져옴
		TableColumn<Book, ?> tcTitle = tableView.getColumns().get(1);
		tcTitle.setCellValueFactory(new PropertyValueFactory("title"));
		TableColumn<Book, ?> tcName = tableView.getColumns().get(2);
		tcName.setCellValueFactory(new PropertyValueFactory("name"));
		TableColumn<Book, ?> tcPrice = tableView.getColumns().get(3);
		tcPrice.setCellValueFactory(new PropertyValueFactory("price"));
		TableColumn<Book, ?> tcSell = tableView.getColumns().get(4);
		tcSell.setCellValueFactory(new PropertyValueFactory("sell"));
		
		tableView.setItems(sales);
		
		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					updateBook();
				}
			}
			
		});
	}
	
	public void buttonAddAction(ActionEvent ae) {
		Stage addStage = new Stage(StageStyle.UTILITY);
		addStage.initModality(Modality.WINDOW_MODAL);
		addStage.initOwner(btnAdd.getScene().getWindow());
		
		try { 
			Parent parent = FXMLLoader.load(getClass().getResource("AddBook.fxml")); 
			Scene scene = new Scene(parent);
			addStage.setScene(scene);
			addStage.setResizable(false);
			addStage.show();		
			
			Button btnFormAdd = (Button) parent.lookup("#btnFormAdd");
			Button btnFormCancel = (Button) parent.lookup("#btnFormCancel");
			btnFormAdd.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					TextField txtCode = (TextField) parent.lookup("#txtCode");
					TextField txtTitle = (TextField) parent.lookup("#txtTitle");
					TextField txtName = (TextField) parent.lookup("#txtName");
					TextField txtPrice = (TextField) parent.lookup("#txtPrice");
					TextField txtSell = (TextField) parent.lookup("#txtSell");
					Book book = new Book(Integer.parseInt(txtCode.getText()), 
							txtTitle.getText(),
							txtName.getText(),
							Integer.parseInt(txtPrice.getText()),
							Integer.parseInt(txtSell.getText())
					);
					sales.add(book);
					addStage.close();
				}
				
			});
			
			btnFormCancel.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
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
			Parent parent = FXMLLoader.load(getClass().getResource("BookChart.fxml"));
			BarChart barChart = (BarChart) parent.lookup("#barChart");
			
			XYChart.Series<String, Integer> seriesPrice = new XYChart.Series<String, Integer>();
			ObservableList<XYChart.Data<String, Integer>> datasPrice = FXCollections.observableArrayList();
			for (int i=0; i<sales.size(); i++) {
				datasPrice.add(new XYChart.Data(sales.get(i).getName(),sales.get(i).getPrice()));	
				
			}
			seriesPrice.setData(datasPrice);
			
			XYChart.Series<String, Integer> seriesSell = new XYChart.Series<String, Integer>();
			ObservableList<XYChart.Data<String, Integer>> datasSell = FXCollections.observableArrayList();
			for (int i=0; i<sales.size(); i++) {
				datasSell.add(new XYChart.Data(sales.get(i).getName(),sales.get(i).getSell()));	
				
			}
			seriesSell.setData(datasSell);
			
			
			barChart.setData(FXCollections.observableArrayList(seriesPrice,seriesSell));
			
			seriesPrice.setName("금액");
			seriesSell.setName("판매량");
			
			Button btnFormClose = (Button) parent.lookup("#btnClose");
			btnFormClose.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					chartStage.close();
				}
				
			});
			
			Scene scene = new Scene(parent);
			chartStage.setScene(scene);
			chartStage.show();
			chartStage.setResizable(false);
			
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public void updateBook() {
		Stage BookStage = new Stage(StageStyle.UTILITY);
		BookStage.initModality(Modality.WINDOW_MODAL);
		BookStage.initOwner(tableView.getScene().getWindow());
		
		
		try {
			Parent parent;
			parent = FXMLLoader.load(getClass().getResource("UpdateBook.fxml"));
			Scene scene = new Scene(parent);
			
			Button btnFormUpdate = (Button) parent.lookup("#btnFormUpdate");//parent의 하위 클래스에서 아이디값을 가져 오게 하는 것
			btnFormUpdate.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					TextField txtName2 = (TextField) parent.lookup("#txtName2");
					TextField txtTitle2 = (TextField) parent.lookup("#txtTitle2");
					TextField txtPrice2 = (TextField) parent.lookup("#txtPrice2");
					TextField txtSell2 = (TextField) parent.lookup("#txtSell2");
					tableView.getSelectionModel().selectedItemProperty().getValue().setName(txtName2.getText());
					tableView.getSelectionModel().selectedItemProperty().getValue().setTitle(txtTitle2.getText());
					tableView.getSelectionModel().selectedItemProperty().getValue().setPrice(Integer.parseInt(txtPrice2.getText()));
					tableView.getSelectionModel().selectedItemProperty().getValue().setSell(Integer.parseInt(txtSell2.getText()));
				}
				
			});
			
			Button btnFormCancel = (Button) parent.lookup("#btnFormCancel");
			btnFormCancel.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					BookStage.close();
				}
				
			});
			
			BookStage.setScene(scene);
			BookStage.setResizable(false);
			BookStage.show();	
		} catch (IOException e) {
			e.printStackTrace();
		} 
			
	}
}
