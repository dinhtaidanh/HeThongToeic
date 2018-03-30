/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.HienCuaSo;
import Model.Cau;
import Model.ListCauHoi;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Danh
 */
public class ThiNguPhapController implements Initializable  {
    @FXML
    private Label lblCauSo = new Label();
    @FXML
    private ToggleGroup radioGroup = new ToggleGroup();
    @FXML
    private RadioButton rdA = new RadioButton();
    @FXML
    private RadioButton rdB = new RadioButton();
    @FXML
    private RadioButton rdC = new RadioButton();
    @FXML
    private RadioButton rdD = new RadioButton();
    @FXML
    private WebView cauHoi = new WebView();
    private static int cauHienTai = 0;
    private ListCauHoi dsCauHoi = new ListCauHoi();
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        rdA.setToggleGroup(radioGroup);
        rdB.setToggleGroup(radioGroup);
        rdC.setToggleGroup(radioGroup);
        rdD.setToggleGroup(radioGroup);
        dsCauHoi.readTable("nguphap");
        fillData();      
        radioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
           @Override
           public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
               // Có lựa chọn
               if (radioGroup.getSelectedToggle() != null) {
                   RadioButton rd = (RadioButton) radioGroup.getSelectedToggle();
                   if(rd.getText().substring(0,1)!=null){
                   String traLoi = rd.getText().substring(0,1);  
                   dsCauHoi.getDsCau().get(cauHienTai).setTraLoi(traLoi);
                   }
               }                         
           }
       });
                
        // TODO
    }   
    private void fillData(){
        
        lblCauSo.setText("Câu hỏi "+String.valueOf(cauHienTai+1));
        WebEngine webEngine = cauHoi.getEngine();
        webEngine.loadContent(dsCauHoi.getDsCau().get(cauHienTai).getCauHoi(), "text/html");
        rdA.setText("A. " + dsCauHoi.getDsCau().get(cauHienTai).getDapAn1());
        rdB.setText("B. " + dsCauHoi.getDsCau().get(cauHienTai).getDapAn2());
        rdC.setText("C. " + dsCauHoi.getDsCau().get(cauHienTai).getDapAn3());
        rdD.setText("D. " + dsCauHoi.getDsCau().get(cauHienTai).getDapAn4());  
        rdA.setSelected(false);
        rdB.setSelected(false);
        rdC.setSelected(false);
        rdD.setSelected(false);
    }
    private boolean checkStringArr(String word, String[] arr){
        for(String i : arr){
            if(i.equals(word))
                return true;
        }
        return false;
    }
    private void setCurrent(){
        switch(dsCauHoi.getDsCau().get(cauHienTai).getTraLoi()){
                case "A": rdA.setSelected(true);
                        break;
                case "B": rdB.setSelected(true);
                        break;
                case "C": rdC.setSelected(true);
                        break;
                case "D": rdD.setSelected(true);
                        break;
            }
    }
    @FXML
    private void onPreviousQuestion(){
        if(cauHienTai>0){
        cauHienTai-=1;
        fillData();
        setCurrent();
        }
    }
    @FXML
    private void onNextQuestion(){
        if(cauHienTai <= dsCauHoi.getDsCau().size()-2){
        cauHienTai+=1;
        fillData();
        setCurrent();
        }
    }
    @FXML
    private void onTinhDiemNguPhap(){
        int soCauDung = 0;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION.INFORMATION);
        for(Cau i : dsCauHoi.getDsCau()){
            if(i.getDapAnDung().equals(i.getTraLoi()))
                soCauDung+=1;
        }    
        alert.setTitle("Kết quả thi thử");
        alert.setHeaderText("Số câu đúng: " + soCauDung + "/" + dsCauHoi.getDsCau().size());
        alert.setContentText("so phan tu: " + dsCauHoi.getDsCau().size() + "vi tri: " + cauHienTai);
        alert.showAndWait(); 
        HienCuaSo w = new HienCuaSo();
        w.showWindow("/View/KetQua.fxml");
    }
    @FXML
    public void quayVe() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ManHinhChinh.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage) lblCauSo.getScene().getWindow();
        currentStage.close();
    }
}
