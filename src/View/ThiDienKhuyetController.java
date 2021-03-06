/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.HienCuaSo;
import Model.HibernateUtilDienKhuyet;
import Model.DienKhuyet;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * FXML Controller class
 *
 * @author Danh
 */
public class ThiDienKhuyetController implements Initializable {

    @FXML
    private Label lblCauSo;
    @FXML
    private ToggleGroup radioGroup = new ToggleGroup();
    @FXML
    private RadioButton rdA;
    @FXML
    private RadioButton rdB;
    @FXML
    private RadioButton rdC;
    @FXML
    private RadioButton rdD;
    @FXML
    private TextArea txtCauHoi;
    @FXML
    private Button btnTinhDiem;
    MediaPlayer mediaPlayer;
    private ArrayList<DienKhuyet> listDienKhuyet = new ArrayList<>();
    private static int cauHienTai = 0;
    private String[] listTraLoi;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rdA.setToggleGroup(radioGroup);
        rdB.setToggleGroup(radioGroup);
        rdC.setToggleGroup(radioGroup);
        rdD.setToggleGroup(radioGroup);
        btnTinhDiem.setVisible(false);
        SessionFactory factory = HibernateUtilDienKhuyet.getSessionFactory();
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(DienKhuyet.class);
        List result = criteria.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            DienKhuyet dienKhuyet = (DienKhuyet) iterator.next();
            listDienKhuyet.add(dienKhuyet);
        }
        listTraLoi = new String[listDienKhuyet.size()];
        for (int i = 0; i < listTraLoi.length; i++) {
            listTraLoi[i] = "";
        }
        fillData();
        radioGroup.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) -> {
            // Có lựa chọn
            if (radioGroup.getSelectedToggle() != null) {
                RadioButton rd = (RadioButton) radioGroup.getSelectedToggle();
                if (rd.getText().substring(0, 1) != null) {
                    String traLoi = rd.getText().substring(0, 1);
                    listTraLoi[cauHienTai] = traLoi;
                }
            }
        });

        // TODO
    }

    private void fillData() {

        lblCauSo.setText("Câu hỏi " + String.valueOf(cauHienTai + 1) + ":");
        txtCauHoi.setText(listDienKhuyet.get(cauHienTai).getCauHoi());
        rdA.setText("A. " + listDienKhuyet.get(cauHienTai).getDapAn1());
        rdB.setText("B. " + listDienKhuyet.get(cauHienTai).getDapAn2());
        rdC.setText("C. " + listDienKhuyet.get(cauHienTai).getDapAn3());
        rdD.setText("D. " + listDienKhuyet.get(cauHienTai).getDapAn4());
        rdA.setSelected(false);
        rdB.setSelected(false);
        rdC.setSelected(false);
        rdD.setSelected(false);
    }

    private void setCurrent() {
        switch (listTraLoi[cauHienTai]) {
            case "A":
                rdA.setSelected(true);
                break;
            case "B":
                rdB.setSelected(true);
                break;
            case "C":
                rdC.setSelected(true);
                break;
            case "D":
                rdD.setSelected(true);
                break;
        }
    }

    @FXML
    private void onPreviousQuestion() {
        if (cauHienTai > 0) {
            cauHienTai -= 1;
            fillData();
            setCurrent();
        }
    }

    @FXML
    private void onNextQuestion() {
        if (cauHienTai == listDienKhuyet.size() - 1) {
            btnTinhDiem.setVisible(true);
        }
        if (cauHienTai <= listDienKhuyet.size() - 2 && radioGroup.getSelectedToggle() != null) {
            cauHienTai += 1;
            fillData();
            setCurrent();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lưu ý");
            alert.setHeaderText("Vui lòng chọn đáp án!");
            alert.setContentText("Bạn chưa chọn đáp án hoặc số câu hỏi đã hết!");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Toeic.png")));
            alert.show();
        }
    }

    @FXML
    private void onTinhDiemDienKhuyet() throws IOException {
        int score = 0;
        for (int i = 0; i < listTraLoi.length; i++) {
            if (listTraLoi[i].equals(listDienKhuyet.get(i).getDapAnDung())) {
                score++;
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Kết quả");
        alert.setHeaderText("Kết quả thi của bạn");
        String thongbao = "Bạn đã hoàn thành bài thi với số điểm là: " + score;
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Toeic.png")));
        alert.setContentText(thongbao);
        alert.setOnShown(e -> {
            File file = new File("C:/Users/Danh/Desktop/HeThongToeic/src/media/congratulation.mp3");
            Media media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        });
        alert.showAndWait();
    }

    @FXML
    public void quayVe() throws IOException {
        cauHienTai = 0;
        Stage currentStage = (Stage) lblCauSo.getScene().getWindow();
        currentStage.close();
    }
}
