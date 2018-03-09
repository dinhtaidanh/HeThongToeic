/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ThemCauHoiPhotoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtAudio;
    @FXML
    private TextField txtPhoto;
    @FXML
    private Button btAudio;
    @FXML
    private Button btPhoto;
    @FXML
    private RadioButton rad1;
    @FXML
    private RadioButton rad2;
    @FXML
    private RadioButton rad3;
    @FXML
    private RadioButton rad4;
    String dapan = "";
    @FXML
    private void audio(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("MP3 Files", "*.mp3"));
        fileChooser.getExtensionFilters().add(new ExtensionFilter("AIFF Files", "*.aiff"));
        fileChooser.getExtensionFilters().add(new ExtensionFilter("WAV Files", "*.wav"));
        fileChooser.getExtensionFilters().add(new ExtensionFilter("MPEG-4 Files", "*.mpeg-4"));
        File selectedFile = fileChooser.showOpenDialog(null);
        txtAudio.setText(selectedFile.getPath());
    }
    @FXML
    private void photo(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("JPG Files", "*.jpg"));
        fileChooser.getExtensionFilters().add(new ExtensionFilter("GIF Files", "*.gif"));
        fileChooser.getExtensionFilters().add(new ExtensionFilter("PNG Files", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(null);
        txtPhoto.setText(selectedFile.getPath());
    }
    @FXML
    private void them(ActionEvent event) {
         try{
                Connection connection = ConnectionUtils.getMyConnection();             
                Statement statement = connection.createStatement();
                String sql = "INSERT INTO luyennghe(DapAn,LinkAudio,LinkPhoto) VALUES ('"+dapan+"','"+ txtAudio.getText()+"','"+ txtPhoto.getText()+"' )";                    
                int rowCount = 0;
                rowCount = statement.executeUpdate(sql);
                if(rowCount != 0)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setContentText("Thêm thành công !");
                    alert.showAndWait();

                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setContentText("Lỗi thêm câu hỏi!");
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
    private void load(ActionEvent event) {
        ToggleGroup group = new ToggleGroup();
        rad1.setToggleGroup(group);
        rad2.setToggleGroup(group);
        rad3.setToggleGroup(group);
        rad4.setToggleGroup(group); 
        if(rad1.isSelected()){
            dapan = "A";
        }else if(rad2.isSelected()){
            dapan = "B";
        }else if(rad3.isSelected()){
            dapan = "C";
        }else{
            dapan = "D";
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
