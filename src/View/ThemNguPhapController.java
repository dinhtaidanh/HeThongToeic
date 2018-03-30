/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.HibernateUtilNguPhap;
import Model.NguPhap;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
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
public class ThemNguPhapController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO     
        cbDapAnNguPhapDung.getItems().removeAll(cbDapAnNguPhapDung.getItems());
        cbDapAnNguPhapDung.getItems().addAll("A", "B", "C", "D");
    }
    @FXML
    private TextField txtDapAnNguPhapA;
    @FXML
    private TextField txtDapAnNguPhapB;
    @FXML
    private TextField txtDapAnNguPhapC;
    @FXML
    private TextField txtDapAnNguPhapD;
    @FXML
    private HTMLEditor htmlEditor;
    @FXML
    private ComboBox cbDapAnNguPhapDung;

    @FXML
    private void onThemCauHoiNguPhap(ActionEvent event) {
        SessionFactory factory = HibernateUtilNguPhap.getSessionFactory();
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        NguPhap nguPhap = new NguPhap();
        nguPhap.setCauHoi(htmlEditor.getHtmlText().trim());
        nguPhap.setDapAn1(txtDapAnNguPhapA.getText().trim());
        nguPhap.setDapAn2(txtDapAnNguPhapB.getText().trim());
        nguPhap.setDapAn3(txtDapAnNguPhapC.getText().trim());
        nguPhap.setDapAn4(txtDapAnNguPhapD.getText().trim());
        nguPhap.setDapAnDung(cbDapAnNguPhapDung.getSelectionModel().getSelectedItem().toString());
        session.save(nguPhap);
        trans.commit();
        Criteria criteria = session.createCriteria(NguPhap.class);
        criteria.add(Restrictions.eq("cauHoi", htmlEditor.getHtmlText().trim()));
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
    }
}
