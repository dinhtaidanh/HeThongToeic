/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.HibernateUtilLuyenNghe;
import Model.LuyenNghe;
import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

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
    private RadioButton rdA;
    @FXML
    private RadioButton rdB;
    @FXML
    private RadioButton rdC;
    @FXML
    private RadioButton rdD;
    @FXML
    private ToggleGroup radioGroup;
    FileChooser fileChooserPhoto = new FileChooser();
    FileChooser fileChooserMedia = new FileChooser(); 
    private static String dapAn = "";
    @FXML
    private void audio(ActionEvent event) throws NullPointerException {              
        File selectedFile = fileChooserMedia.showOpenDialog(null);
        txtAudio.setText(selectedFile.getAbsolutePath().replace("\\", "/"));
    }
    @FXML
    private void photo(ActionEvent event) throws NullPointerException{       
        File selectedFile = fileChooserPhoto.showOpenDialog(null);
        txtPhoto.setText(selectedFile.getAbsolutePath().replace("\\", "/"));
    }
    @FXML
    private void them(ActionEvent event) {
         SessionFactory factory = HibernateUtilLuyenNghe.getSessionFactory();
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        LuyenNghe luyenNghe = new LuyenNghe();
        luyenNghe.setLinkAudio(txtAudio.getText().trim());
        luyenNghe.setLinkPhoto(txtPhoto.getText().trim());        
        luyenNghe.setDapAn(dapAn);
        session.save(luyenNghe);
        trans.commit();
        Criteria criteria = session.createCriteria(LuyenNghe.class);
        criteria.add(Restrictions.eq("linkAudio", txtAudio.getText().trim()));
        List result = criteria.list();
        Iterator iterator = result.iterator();
        if (iterator.hasNext()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Thêm câu hỏi thành công");
            a.show();
            Stage currentStage = (Stage) btAudio.getScene().getWindow();
            currentStage.close();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Thêm câu hỏi thất bại");
            a.show();
        }
        Stage currentStage = (Stage) btAudio.getScene().getWindow();
        currentStage.close();
    }  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        radioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
           @Override
           public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
               // Có lựa chọn
               if (radioGroup.getSelectedToggle() != null) {
                   RadioButton rd = (RadioButton) radioGroup.getSelectedToggle();
                   if(rd.getText()!=null){
                       dapAn = rd.getText();
                   }
               }                         
           }
       });
        File file = new File("C:\\Users\\Danh\\Desktop\\HeThongToeic\\src\\media");
        fileChooserPhoto.setInitialDirectory(file);
        fileChooserPhoto.getExtensionFilters().add(new ExtensionFilter("JPG Files", "*.jpg"));
        fileChooserPhoto.getExtensionFilters().add(new ExtensionFilter("GIF Files", "*.gif"));
        fileChooserPhoto.getExtensionFilters().add(new ExtensionFilter("PNG Files", "*.png"));
        
        fileChooserMedia.setInitialDirectory(file);
        fileChooserMedia.getExtensionFilters().add(new ExtensionFilter("MP3 Files", "*.mp3"));
        fileChooserMedia.getExtensionFilters().add(new ExtensionFilter("AIFF Files", "*.aiff"));
        fileChooserMedia.getExtensionFilters().add(new ExtensionFilter("WAV Files", "*.wav"));
        fileChooserMedia.getExtensionFilters().add(new ExtensionFilter("MPEG-4 Files", "*.mpeg-4"));
    }    
    
}
