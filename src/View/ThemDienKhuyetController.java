/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.HibernateUtilDienKhuyet;
import Model.DienKhuyet;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author Danh
 */
public class ThemDienKhuyetController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbDapAnDienKhuyetDung.getItems().removeAll(cbDapAnDienKhuyetDung.getItems());
        cbDapAnDienKhuyetDung.getItems().addAll("A", "B", "C", "D");
    }  
    @FXML
    private Button btnThem;
    @FXML
    private TextField txtCauHoiDienKhuyet;
    @FXML
    private TextField txtDapAnDienKhuyetA;
    @FXML
    private TextField txtDapAnDienKhuyetB;
    @FXML
    private TextField txtDapAnDienKhuyetC;
    @FXML
    private TextField txtDapAnDienKhuyetD;
    @FXML
    private ComboBox cbDapAnDienKhuyetDung;
    @FXML
    private void onThemCauHoiDienKhuyet(ActionEvent event){
          SessionFactory factory = HibernateUtilDienKhuyet.getSessionFactory();
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        DienKhuyet dienKhuyet = new DienKhuyet();
        dienKhuyet.setCauHoi(txtCauHoiDienKhuyet.getText().trim());
        dienKhuyet.setDapAn1(txtDapAnDienKhuyetA.getText().trim());
        dienKhuyet.setDapAn2(txtDapAnDienKhuyetB.getText().trim());
        dienKhuyet.setDapAn3(txtDapAnDienKhuyetC.getText().trim());
        dienKhuyet.setDapAn4(txtDapAnDienKhuyetD.getText().trim());
        dienKhuyet.setDapAnDung(cbDapAnDienKhuyetDung.getSelectionModel().getSelectedItem().toString());
        session.save(dienKhuyet);
        trans.commit();
        Criteria criteria = session.createCriteria(DienKhuyet.class);
        criteria.add(Restrictions.eq("cauHoi", txtCauHoiDienKhuyet.getText().trim()));
        List result = criteria.list();
        Iterator iterator = result.iterator();
        if (iterator.hasNext()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Thêm câu hỏi thành công");
            a.show();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Thêm câu hỏi thất bại");
            a.show();
        }
        Stage currentStage = (Stage) btnThem.getScene().getWindow();
        currentStage.close();
    }
}
