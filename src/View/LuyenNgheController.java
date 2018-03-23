/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LuyenNgheController implements Initializable {

    @FXML
    Text txtCauSo;
    @FXML
    RadioButton rdA;
    @FXML
    RadioButton rdB;
    @FXML
    RadioButton rdC;
    @FXML
    RadioButton rdD;
    @FXML
    ImageView imvPhoto;
    ToggleGroup group = new ToggleGroup();
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rdA.setToggleGroup(group);
        rdB.setToggleGroup(group);
        rdC.setToggleGroup(group);
        rdD.setToggleGroup(group);
    }

    @FXML
    private void playAudio(ActionEvent event) {
       
    }
    @FXML
    private void nextQuestion(){
        
    }
    
    @FXML
    private void tinhDiem(){
        
    }
}
