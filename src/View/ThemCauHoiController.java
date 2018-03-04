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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Danh
 */
public class ThemCauHoiController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private void onThemNguPhap(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("ThemNguPhap.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);       
        stage.setScene(scene);
        stage.show();    
        }catch (IOException e) {e.printStackTrace();}     
    }
    @FXML
    private void onThemDienKhuyet(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("ThemDienKhuyet.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);       
        stage.setScene(scene);
        stage.show();    
        }catch (IOException e) {e.printStackTrace();}     
    }
    @FXML
    private void onThemNghePhoto(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("ThemNguPhap.fxml"));
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
