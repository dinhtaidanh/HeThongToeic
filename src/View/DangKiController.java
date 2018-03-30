/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.HibernateUtilUser;
import Model.User;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
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
public class DangKiController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btDangKi;
    @FXML
    private TextField txtTenDangNhap;
    @FXML
    private TextField txtMatKhau;
    @FXML
    private TextField txtHoTen;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtDiaChi;
    @FXML
    private RadioButton rdNam;
    @FXML
    private RadioButton rdNu;
    ToggleGroup radioGroup;
    String gioiTinh = "";

    private Boolean checkUserName(String tenDangNhap) {
        SessionFactory factory = HibernateUtilUser.getSessionFactory();
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("username", tenDangNhap));
        List result = criteria.list();
        Iterator iterator = result.iterator();

        return iterator.hasNext();
    }

    private Boolean checkEmail(String email) {
        String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern regex = Pattern.compile(emailPattern);
        Matcher matcher = regex.matcher(email);
        return matcher.find();
    }

    @FXML
    private void register(ActionEvent event) throws IOException {
        if (txtTenDangNhap.getText().trim().equals("")
                || txtDiaChi.getText().trim().equals("")
                || txtEmail.getText().trim().equals("")
                || txtHoTen.getText().trim().equals("")
                || txtMatKhau.getText().trim().equals("")
                || rdNam.isSelected() == false && rdNu.isSelected() == false) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Phải nhập đầy đủ thông tin !");
            alert.showAndWait();
            return;
        }
        if (checkEmail(txtEmail.getText().trim()) == false) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Email không hợp lệ !");
            alert.showAndWait();
            return;
        }
        if (checkUserName(txtTenDangNhap.getText()) == false) {
            SessionFactory factory = HibernateUtilUser.getSessionFactory();
            Session session = factory.openSession();
            Transaction trans = session.beginTransaction();
            User user = new User();
            user.setUsername(txtTenDangNhap.getText().trim());
            user.setPassword(txtMatKhau.getText().trim());
            user.setQuyen("khach");
            user.setHoTen(txtHoTen.getText().trim());
            user.setDiaChi(txtDiaChi.getText().trim());
            user.setEmail(txtEmail.getText().trim());
            user.setGioiTinh(gioiTinh);
            session.save(user);
            trans.commit();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("username", txtTenDangNhap.getText().trim()));
            List result = criteria.list();

            Iterator iterator = result.iterator();
            if (iterator.hasNext()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setContentText("Tạo tài khoản thành công ! Hãy đăng nhập vào tài khoản của bạn !");
                alert.showAndWait();
                Parent root = FXMLLoader.load(getClass().getResource("DangNhap.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.show();
                Stage currentStage = (Stage) btDangKi.getScene().getWindow();
                currentStage.close();
                session.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setContentText("Lỗi thêm !");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Tên tài khoản đã tồn tại !");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        radioGroup = new ToggleGroup();
        rdNam.setToggleGroup(radioGroup);
        rdNu.setToggleGroup(radioGroup);
        radioGroup.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) -> {
            // Có lựa chọn
            if (radioGroup.getSelectedToggle() != null) {
                RadioButton rd = (RadioButton) radioGroup.getSelectedToggle();
                gioiTinh = rd.getText();
            }
        });
    }
    @FXML
    public void quayVe() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DangNhap.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage) btDangKi.getScene().getWindow();
        currentStage.close();
    }
}
