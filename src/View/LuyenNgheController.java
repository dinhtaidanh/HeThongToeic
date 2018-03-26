/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.ConnectionUtils;
import Model.HibernateUtilLuyenNghe;
import Model.HibernateUtilUser;
import Model.LuyenNghe;
import Model.User;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
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
    ToggleGroup group = new ToggleGroup();
    private ArrayList<LuyenNghe> listLuyenNghe = new ArrayList<>();
    private static int cauHienTai = 0;
    String dapAn = "";
    int diem = 0;
    int id = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO     
        btnPlay.setText("PLAY");
        txtCauSo.setText("Question " + String.valueOf(cauHienTai+1) + ":");
        rdA.setToggleGroup(group);
        rdB.setToggleGroup(group);
        rdC.setToggleGroup(group);
        rdD.setToggleGroup(group);
        SessionFactory factory = HibernateUtilLuyenNghe.getSessionFactory();
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(LuyenNghe.class);
        List result = criteria.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            LuyenNghe luyenNghe = (LuyenNghe) iterator.next();
            listLuyenNghe.add(luyenNghe);
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
        if(cauHienTai < listLuyenNghe.size()-1){
            if (rdA.isSelected()) {
                dapAn = "A";
            } else if (rdB.isSelected()) {
                dapAn = "B";
            } else if (rdC.isSelected()) {
                dapAn = "C";
            } else if (rdD.isSelected()) {
                dapAn = "D";
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Lưu ý!!");
                alert.setContentText("Chưa chọn đáp án!!");
                alert.showAndWait();
            }
            if(group.getSelectedToggle()!=null){
            cauHienTai++;
            btnPlay.setText("PLAY");
            rdA.setSelected(false);
            rdB.setSelected(false);
            rdC.setSelected(false);
            rdD.setSelected(false);
            txtCauSo.setText("Question " + String.valueOf(cauHienTai+1) + ":");
            loadMedia();  
            }
        } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Lưu ý!!");
                alert.setContentText("Hết câu hỏi!!");
                alert.showAndWait();
        }
    }       
    @FXML
    private void tinhDiem() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        String thongbao = "Bạn đã hoàn thành bài thi với số điểm là:" + diem;
        alert.setContentText(thongbao);
        alert.showAndWait();
    }
}
