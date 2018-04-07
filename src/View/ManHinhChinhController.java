/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.INDEFINITE;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Danh
 */
public class ManHinhChinhController implements Initializable {

    @FXML
    private Button btnNguPhap;
    @FXML
    private Button btnDienKhuyet;
    @FXML
    private Button btnNghe;
    @FXML
    private Button btnQuanLyUser;
    @FXML
    private Button btnQuanLyNguPhap;
    @FXML
    private Button btnQuanLyDienKhuyet;
    @FXML
    private Button btnVolume;
    @FXML
    private Button btnQuanLyNghePhoto;
    @FXML
    private ImageView imgVolume;
    private MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        File file = new File("C:/Users/Danh/Desktop/HeThongToeic/src/media/Nhac_Nen.mp3");
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(INDEFINITE);
        mediaPlayer.play();
        btnDienKhuyet.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/CheckBox_32x32.png"))));
        btnNghe.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/AudioContent_32x32.png"))));
        btnNguPhap.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/CheckBox_32x32.png"))));
        btnQuanLyDienKhuyet.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/ManageDatasource_32x32.png"))));
        btnQuanLyNghePhoto.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/ManageDatasource_32x32.png"))));
        btnQuanLyNguPhap.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/ManageDatasource_32x32.png"))));
        btnQuanLyUser.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/UserGroup_32x32.png"))));
    }

    @FXML
    private void volumeOnOff(ActionEvent event) {
        if (btnVolume.getText().equals("Off")) {
            mediaPlayer.pause();
            imgVolume.setImage(new Image(getClass().getResourceAsStream("/Images/on.png")));
            btnVolume.setText("On");
        } else {
            mediaPlayer.play();
            imgVolume.setImage(new Image(getClass().getResourceAsStream("/Images/off.png")));
            btnVolume.setText("Off");
        }
    }

    @FXML
    private void onLuyenNghePhoto(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LuyenNghe.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            mediaPlayer.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeCurrentStage();
    }

    @FXML
    private void onLuyenNguPhap(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ThiNguPhap.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeCurrentStage();
    }

    @FXML
    private void onLuyenDienKhuyet(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ThiDienKhuyet.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeCurrentStage();
    }

    @FXML
    private void onQuanLyNguPhap(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("QuanLyNguPhap.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
        }
        closeCurrentStage();
    }

    @FXML
    private void onQuanLyDienKhuyet(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("QuanLyDienKhuyet.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeCurrentStage();
    }

    @FXML
    private void onQuanLyUser(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("QuanLiUser.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeCurrentStage();
    }

    @FXML
    private void onQuanLyNghePhoto(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("QuanLyNghePhoto.fxml"));
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeCurrentStage();
    }

    @FXML
    private void onLogOut(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DangNhap.fxml"));
            Stage stage = new Stage();
            stage.setResizable(false);
            Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Toeic.png")));
            stage.setScene(scene);
            stage.show();
            mediaPlayer.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage currentStage = (Stage) btnNguPhap.getScene().getWindow();
        currentStage.close();
    }

    private void closeCurrentStage() {
        Stage currentStage = (Stage) btnNguPhap.getScene().getWindow();
        currentStage.toBack();
    }

}
