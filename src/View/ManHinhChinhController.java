/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.HienCuaSo;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Danh
 */
public class ManHinhChinhController implements Initializable {
    @FXML
    private Button btnNguPhap = new Button();
    @FXML
    private Button btnDienKhuyet = new Button();
    @FXML
    private Button btnNghe = new Button();
    @FXML
    private Button btnQuanLyUser = new Button();
    @FXML
    private Button btnQuanLyNguPhap = new Button();
    @FXML
    private Button btnQuanLyDienKhuyet = new Button();
    @FXML
    private Button btnQuanLyNghePhoto = new Button();
    @FXML
    private void onLuyenNghePhoto(ActionEvent event) {     
        try{
        Parent root = FXMLLoader.load(getClass().getResource("LuyenNghe.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);  
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();    
        }catch (IOException e) {e.printStackTrace();}
        closeCurrentStage();
    }
    @FXML
    private void onLuyenNguPhap(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("ThiNguPhap.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);  
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();    
        }catch (IOException e) {e.printStackTrace();}
        closeCurrentStage();
    }
    @FXML
    private void onLuyenDienKhuyet(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource(""));
        Stage stage = new Stage();
        Scene scene = new Scene(root);  
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();    
        }catch (IOException e) {e.printStackTrace();}     
        closeCurrentStage();
    }
    @FXML
    private void onQuanLyNguPhap(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("QuanLyNguPhap.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();    
        }catch (IOException e) {e.printStackTrace();}
        closeCurrentStage();
    }
    @FXML
    private void onQuanLyDienKhuyet(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("QuanLyDienKhuyet.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);  
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();    
        }catch (IOException e) {e.printStackTrace();} 
        closeCurrentStage();
    }
    
    @FXML
    private void onQuanLyUser(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("QuanLiUser.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);  
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();    
        }catch (IOException e) {e.printStackTrace();} 
        closeCurrentStage();
    }
    @FXML
    private void onQuanLyNghePhoto(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("QuanLyNghePhoto.fxml"));
        Stage stage = new Stage();
        stage.setResizable(false);
        Scene scene = new Scene(root);  
        stage.setScene(scene);
        stage.show();    
        }catch (IOException e) {e.printStackTrace();}   
        closeCurrentStage();
    }
    private void closeCurrentStage(){
        Stage currentStage = (Stage) btnNguPhap.getScene().getWindow();
        currentStage.hide();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnDienKhuyet.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/CheckBox_32x32.png"))));
        btnNghe.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/AudioContent_32x32.png"))));
        btnNguPhap.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/CheckBox_32x32.png"))));
        btnQuanLyDienKhuyet.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/ManageDatasource_32x32.png"))));
        btnQuanLyNghePhoto.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/ManageDatasource_32x32.png"))));
        btnQuanLyNguPhap.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/ManageDatasource_32x32.png"))));
        btnQuanLyUser.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/UserGroup_32x32.png"))));
    }    
    
}
