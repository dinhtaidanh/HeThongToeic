/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Danh
 */
public class ManHinhChinhController implements Initializable {
    
    @FXML
    private void onThemCauHoi(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("ThemCauHoi.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);  
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();    
        }catch (IOException e) {e.printStackTrace();}
    }
    @FXML
    private void onLuyenNghePhoto(ActionEvent event) {
         try{
        Parent root = FXMLLoader.load(getClass().getResource("LuyenNghe.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);  
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();    
        }catch (IOException e) {e.printStackTrace();}
    }
    @FXML
    private void onLuyenNguPhap(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("ThiNguPhap.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);  
        stage.setScene(scene);
        stage.show();    
        }catch (IOException e) {e.printStackTrace();}
    }
    @FXML
    private void onLuyenDienKhuyet(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource(""));
        Stage stage = new Stage();
        Scene scene = new Scene(root);  
        stage.setScene(scene);
        stage.show();    
        }catch (IOException e) {e.printStackTrace();}     
    }
    @FXML
    private void onQuanLyNguPhap(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("QuanLyNguPhap.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);  
        stage.setScene(scene);
        stage.show();    
        }catch (IOException e) {e.printStackTrace();}     
    }
    @FXML
    private void onQuanLyUser(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("QuanLiUser.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);  
        stage.setScene(scene);
        stage.show();    
        }catch (IOException e) {e.printStackTrace();}     
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
