package media_pack;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MediaController implements Initializable {
	
	@FXML ImageView imageView;
	@FXML MediaView mediaView;
	@FXML Button btnPlay, btnStop, btnPause;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Media media = new Media(getClass().getResource("/media/audio.wav").toString());
		MediaPlayer player = new MediaPlayer(media);
		mediaView.setMediaPlayer(player);
		
		player.setOnReady(new Runnable() {

			@Override
			public void run() {//레디상태에서는 플레이만
				btnPlay.setDisable(false);//사용 못하게 하는 것을 거짓으로, 사용 할 수 있다는 의미
				btnStop.setDisable(true);//사용 못하게 하는 것을 참값으로
				btnPause.setDisable(true);
			}
			
		});//준비상태가 되었을때 처리할 내용을 구현할것, Runnable클래스를 구현
		
		player.setOnPlaying(new Runnable() {

			@Override
			public void run() {
				btnPlay.setDisable(true);
				btnStop.setDisable(false);
				btnPause.setDisable(false);
			} 
			
		});
		
		player.setOnPaused(new Runnable() {

			@Override
			public void run() {
				btnPlay.setDisable(false);
				btnStop.setDisable(false);
				btnPause.setDisable(true);
			}
			
		});
		
		player.setOnEndOfMedia(new Runnable() {

			@Override
			public void run() {
				btnPlay.setDisable(false);
				btnStop.setDisable(true);
				btnPause.setDisable(true);
			}
			
		});
		
		player.setOnStopped(new Runnable() {

			@Override
			public void run() {
				btnPlay.setDisable(false);
				btnStop.setDisable(true);
				btnPause.setDisable(true);
			}
			
		});
		
		btnPlay.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				player.play();
			}
			
		});
		btnStop.setOnAction(e -> player.stop()); //람다식 표현
		btnPause.setOnAction(e -> player.pause());
		
	}

}
