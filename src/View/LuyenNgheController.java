/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.HienCuaSo;
import Model.ConnectionUtils;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LuyenNgheController implements Initializable {

    @FXML
    private Label idCauHoi;
    @FXML
    private Button btPhat;
    @FXML
    private MediaView audio;
    @FXML
    private ImageView imgPhat;
    @FXML
    private Label lbThongBao;
    @FXML
    private RadioButton rad1;
    @FXML
    private RadioButton rad2;
    @FXML
    private RadioButton rad3;
    @FXML
    private RadioButton rad4;
    @FXML
    private ImageView photo;
    @FXML
    private Button btBatDau;
    @FXML
    private Button btKiemTra;
    @FXML
    private Button btTiepTuc;
    @FXML
    private ImageView hello;
    @FXML
    private MediaPlayer player;
    @FXML
    private AnchorPane pane;
    String dapan = "";
    int id = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void phat(ActionEvent event) {
        if(btPhat.getText().equals("Phát")){
            lbThongBao.setVisible(false);
            player.play();
            imgPhat.setVisible(true);
            btPhat.setText("Dừng");
        }else{
            lbThongBao.setVisible(true);
            player.pause();
            imgPhat.setVisible(false);
            btPhat.setText("Phát");
        }
    }
    @FXML
    private void tieptuc(ActionEvent event) {  
        id +=1;
        imgPhat.setVisible(false);
        rad1.setSelected(false);
        rad2.setSelected(false);
        rad3.setSelected(false);
        rad4.setSelected(false);
        btPhat.setText("Phát");
        lbThongBao.setVisible(true);
        idCauHoi.setText( String.valueOf(id));
        try{
                player.pause();
                Connection connection = ConnectionUtils.getMyConnection();             
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM luyennghe WHERE Id='"+id+"' ";                    
                ResultSet rs = statement.executeQuery(sql);
                if(rs.next())
                {
                    String linkaudio = rs.getString("LinkAudio");
                    String linkphoto = rs.getString("LinkPhoto");
                    File file1 = new File(linkaudio);
                    Media media = new Media(file1.toURI().toString());
                    player = new MediaPlayer(media);
                    player.pause();
                    audio = new MediaView(player);
                    File file = new File(linkphoto);
                    Image img = new Image(file.toURI().toString());
                    photo = new ImageView(img);
                    photo.setX(300);
                    photo.setY(150);
                    photo.setFitHeight(190);
                    photo.setFitWidth(280);
                    pane.getChildren().addAll(audio,photo);
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setContentText("Hết câu hỏi !");
                    alert.showAndWait();
                }
            }
            catch (ClassNotFoundException | SQLException e) {
                // TODO: handle exception
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setContentText("Lỗi kết nối !");
                alert.showAndWait();
            }
    }

    @FXML
    private void batdau(ActionEvent event) { 
        lbThongBao.setVisible(true);
        hello.setVisible(false);
        btBatDau.setVisible(false);
        btPhat.setVisible(true);
        audio.setVisible(true);
        imgPhat.setVisible(false);
        lbThongBao.setVisible(true);
        rad1.setVisible(true);
        rad2.setVisible(true);
        rad3.setVisible(true);
        rad4.setVisible(true);
        photo.setVisible(true);
        btKiemTra.setVisible(true);
        btTiepTuc.setVisible(true);        
        ToggleGroup group = new ToggleGroup();
        rad1.setToggleGroup(group);
        rad2.setToggleGroup(group);
        rad3.setToggleGroup(group);
        rad4.setToggleGroup(group); 
        id = 1; 
        idCauHoi.setText( String.valueOf(id));
        try{
                Connection connection = ConnectionUtils.getMyConnection();             
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM luyennghe WHERE Id='"+id+"' ";                    
                ResultSet rs = statement.executeQuery(sql);
                if(rs.next())
                {
                    String linkaudio = rs.getString("LinkAudio");
                    String linkphoto = rs.getString("LinkPhoto");
                    File file1 = new File(linkaudio);
                    Media media = new Media(file1.toURI().toString());
                    player = new MediaPlayer(media);
                    player.pause();
                    audio = new MediaView(player);
                    File file = new File(linkphoto);
                    Image img = new Image(file.toURI().toString());
                    photo = new ImageView(img);
                    photo.setX(300);
                    photo.setY(150);
                    photo.setFitHeight(190);
                    photo.setFitWidth(280);
                    pane.getChildren().addAll(audio,photo);
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setContentText("Một lỗi đã xảy ra !");
                    alert.showAndWait();
                }
            }
            catch (ClassNotFoundException | SQLException e) {
                // TODO: handle exception
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setContentText("Lỗi kết nối !");
                alert.showAndWait();
            }
    }

    @FXML
    private void kiemtra(ActionEvent event) {
        player.pause();
        if(rad1.isSelected()){
            dapan = "A";
        }else if(rad2.isSelected()){
            dapan = "B";
        }else if(rad3.isSelected()){
            dapan = "C";
        }else{
            dapan = "D";
        }
        try{
                Connection connection = ConnectionUtils.getMyConnection();             
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM luyennghe WHERE Id='"+id+"'AND DapAn='"+ dapan+"' ";                    
                ResultSet rs = statement.executeQuery(sql);
                if(rs.next())
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setContentText("Đúng !");
                    alert.showAndWait();

                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setContentText("Sai !");
                    alert.showAndWait();
                }
            }
            catch (ClassNotFoundException | SQLException e) {
                // TODO: handle exception
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setContentText("Lỗi kết nối !");
                alert.showAndWait();
            }
    }
    
}
