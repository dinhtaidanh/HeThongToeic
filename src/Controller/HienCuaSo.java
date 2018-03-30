/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Danh
 */
public class HienCuaSo{
    public void showWindow(String path){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);            
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HienCuaSo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
