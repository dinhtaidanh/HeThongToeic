/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.HibernateUtilLuyenNghe;
import Model.LuyenNghe;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LuyenNgheController implements Initializable {

    @FXML
    Button btnPlay;
    @FXML
    Button btnNextQuestion;
    @FXML
    Button btnTinhDiem;
    @FXML
    Button btnPreviousQuestion;
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
    private MediaView mdvAudio = new MediaView();
    @FXML
    private MediaPlayer mediaPlayer;
    @FXML
    ImageView imvPhoto = new ImageView();;
    @FXML
    ToggleGroup radioGroup = new ToggleGroup();
    private ArrayList<LuyenNghe> listLuyenNghe = new ArrayList<>();
    private static int cauHienTai = 0;
    private static String[] listTraLoi;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO    
        btnPreviousQuestion.setVisible(false);
        btnPlay.setText("PLAY");
        btnTinhDiem.setVisible(false);
        txtCauSo.setText("Question " + String.valueOf(cauHienTai+1) + ":");
        rdA.setToggleGroup(radioGroup);
        rdB.setToggleGroup(radioGroup);
        rdC.setToggleGroup(radioGroup);
        rdD.setToggleGroup(radioGroup);
        radioGroup.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) -> {
            // Có lựa chọn
            if (radioGroup.getSelectedToggle() != null) {
                RadioButton rd = (RadioButton) radioGroup.getSelectedToggle();
                if(rd.getText().substring(0,1)!=null){
                    String traLoi = rd.getText();
                    listTraLoi[cauHienTai]= traLoi;
                }
            }
        });
        
        SessionFactory factory = HibernateUtilLuyenNghe.getSessionFactory();
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(LuyenNghe.class);
        List result = criteria.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            LuyenNghe luyenNghe = (LuyenNghe) iterator.next();
            listLuyenNghe.add(luyenNghe);
        }
        listTraLoi = new String[listLuyenNghe.size()];
        for(int i = 0;i<listTraLoi.length;i++){
            listTraLoi[i]="";
        }
        loadMedia();
    }

    private void loadMedia() {
        
        String photoPath = listLuyenNghe.get(cauHienTai).getLinkPhoto();
        String audioPath = listLuyenNghe.get(cauHienTai).getLinkAudio();
        File fileAudio = new File(audioPath);
        Media media = new Media(fileAudio.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.pause();
        mdvAudio.setMediaPlayer(mediaPlayer); 
        File filePhoto = new File(photoPath);
        Image img = new Image(filePhoto.toURI().toString());
        imvPhoto.setImage(img);    
    }
    private void getCurrent(){
        rdA.setSelected(false);
        rdB.setSelected(false);
        rdC.setSelected(false);
        rdD.setSelected(false);
        btnPlay.setText("PLAY");
        mediaPlayer.stop();
        txtCauSo.setText("Question "+String.valueOf(cauHienTai+1)+":");
        switch(listTraLoi[cauHienTai]){
            case "A":rdA.setSelected(true);
            break;
            case "B":rdB.setSelected(true);
            break;
            case "C":rdC.setSelected(true);
            break;
            case "D":rdD.setSelected(true);
            break;
        }
    }
    @FXML
    private void playAudio(ActionEvent event) {
        if (btnPlay.getText().equals("PLAY")) {
            mediaPlayer.play();
            btnPlay.setText("PAUSE");
        } else if(btnPlay.getText().equals("PAUSE")){
            mediaPlayer.pause();
            btnPlay.setText("PLAY");
        }
    }

    @FXML
    private void nextQuestion() {    
        if(cauHienTai == listLuyenNghe.size()-1)
            btnTinhDiem.setVisible(true);
        if(cauHienTai < listLuyenNghe.size()-1 && radioGroup.getSelectedToggle() != null){
            btnPreviousQuestion.setVisible(true);
            cauHienTai++;
            getCurrent();
            loadMedia();
        }  
    } 
    @FXML
    private void previousQuestion(){
        if(cauHienTai > 0 && radioGroup.getSelectedToggle() != null){
            cauHienTai--;
            getCurrent();
            loadMedia();
        }  
    }
    @FXML
    private void tinhDiem() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        String thongbao = "Bạn đã hoàn thành bài thi với số điểm là: " ;
        alert.setContentText(thongbao);
        alert.showAndWait();
    }
    @FXML
    public void quayVe() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ManHinhChinh.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage) btnTinhDiem.getScene().getWindow();
        currentStage.close();
    }
}
