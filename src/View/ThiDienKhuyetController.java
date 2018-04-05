/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.HienCuaSo;
import Model.HibernateUtilDienKhuyet;
import Model.DienKhuyet;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * FXML Controller class
 *
 * @author Danh
 */
public class ThiDienKhuyetController implements Initializable  {
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
    private ArrayList<DienKhuyet> listDienKhuyet = new ArrayList<>();
    private static int cauHienTai = 0;
    private String[] listTraLoi;
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        rdA.setToggleGroup(radioGroup);
        rdB.setToggleGroup(radioGroup);
        rdC.setToggleGroup(radioGroup);
        rdD.setToggleGroup(radioGroup);
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
        for(int i=0;i<listTraLoi.length;i++){
            listTraLoi[i] = "";
        }
        fillData();      
        radioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
           @Override
           public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
               // Có lựa chọn
               if (radioGroup.getSelectedToggle() != null) {
                   RadioButton rd = (RadioButton) radioGroup.getSelectedToggle();
                   if(rd.getText().substring(0,1)!=null){
                   String traLoi = rd.getText().substring(0,1);  
                   listTraLoi[cauHienTai]=traLoi;
                   }
               }                         
           }
       });
                
        // TODO
    }   
    private void fillData(){
        
        lblCauSo.setText("Câu hỏi "+String.valueOf(cauHienTai+1)+":");
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
    private void setCurrent(){
        switch(listTraLoi[cauHienTai]){
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
        if(cauHienTai <= listDienKhuyet.size()-2){
        cauHienTai+=1;
        fillData();
        setCurrent();
        }
    }
    @FXML
    private void onTinhDiemDienKhuyet() throws IOException{
        int soCauDung = 0;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION.INFORMATION);
        for(int i=0;i<listTraLoi.length;i++){
            if(listTraLoi[i].equals(listDienKhuyet.get(i).getDapAnDung()))
                soCauDung+=1;
        }    
        alert.setTitle("Kết quả thi thử");
        alert.setHeaderText("Số câu đúng: " + soCauDung + "/" + listDienKhuyet.size());
        alert.showAndWait(); 
        HienCuaSo w = new HienCuaSo();
        w.showWindow("/View/KetQua.fxml");
    }
    @FXML
    public void quayVe() throws IOException {
        cauHienTai=0;      
        Stage currentStage = (Stage) lblCauSo.getScene().getWindow();
        currentStage.close();
    }
}
