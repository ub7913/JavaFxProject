package view_pack;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class BoardController implements Initializable {
	Connection conn;
	
	@FXML TableView<Board> tableView;
	@FXML TextField title;
	@FXML ComboBox publicity;
	@FXML TextField exitDate;
	@FXML TextArea content;
	@FXML Button a,b,c;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//소스를 읽어 와야 하기때문에 예외쳐리 할것 드라이브가 있으면 넘어가고 없으면 클래스 낫파운드 캣치할것
			conn=DriverManager.getConnection(url,"hr","hr");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		ObservableList<Board> boardList = getBoardList(); //FXCollections.observableArrayList();
		
		//boardList.add(new Board("test","1234","공개","2020/06/04","내용ㅇ...."));
		
		TableColumn<Board, String> tcTitle = new TableColumn<Board, String>();//새로운 칼럼을 정의해서 해서 연결
		tcTitle.setCellValueFactory(new Callback<CellDataFeatures<Board, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Board, String> param) {
				return param.getValue().titleProperty();
			}
			
		});
		tcTitle.setText("제목");
		
		//exitDate
		TableColumn<Board, String> tcExitDate = new TableColumn<>();
		tcExitDate.setCellValueFactory(new PropertyValueFactory<Board, String>("exitDate"));//위에서 쓰는것이랑 같은것임
		tcExitDate.setText("종료일자");
				
		tableView.getColumns().add(tcTitle);
		tableView.getColumns().add(tcExitDate);
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Board>() {

			@Override
			public void changed(ObservableValue<? extends Board> observable, Board oldVal, Board newVal) {
				if (newVal == null) {
					return;
				}
				
				title.setText(newVal.getTitle());
				publicity.setValue(newVal.getPublicity());
				exitDate.setText(newVal.getExitDate());
				content.setText(newVal.getContent());
			}
			
		});
		
		a.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Integer curretIndex = tableView.getSelectionModel().getSelectedIndex();
				tableView.getSelectionModel().select(curretIndex-1);
				System.out.println(curretIndex);
			}
			
		});
		
		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Integer currentIndex = tableView.getSelectionModel().getSelectedIndex();
				tableView.getSelectionModel().select(currentIndex+1);
			}
			
		});
		
		c.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String sql = "update board set content =? where title=?";
				
				try {
					PreparedStatement pstmt= conn.prepareStatement(sql);
					pstmt.setString(1, content.getText());
					pstmt.setString(2, title.getText());
					pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				tableView.setItems(getBoardList());
			}
		});
		
		tableView.setItems(boardList);
	} // end of Initialize()
	
	public ObservableList<Board> getBoardList() {
		ObservableList<Board> list = FXCollections.observableArrayList();
		String sql = "select title, publicity, exit_date, content from board";
		try {
			PreparedStatement pstmt= conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board(rs.getString("title"), null, rs.getString("publicity"), 
										rs.getString("exit_date"), rs.getString("content"));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	} // end of getBoardList()
	
//	public void modifyBoard(Board brd) {
//		String sql = "update board set content =? where title=?;";
//		
//		try {
//			PreparedStatement pstmt= conn.prepareStatement(sql);
//			pstmt.setString(1, brd.getContent());
//			pstmt.setString(2, "title");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

}

// 문제 : 이전 다음의 기능넣기, 수정버튼은 제목을 기준으로 내용을 수정 할 수 있도록 ,힌트 : 리스트뷰
