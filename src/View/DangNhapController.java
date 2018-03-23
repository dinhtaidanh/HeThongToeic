package View;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Controller.HienCuaSo;
import Model.ConnectionUtils;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class DangNhapController implements Initializable {

    @FXML
    private Hyperlink hpQuenMatKhau;
    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnRegister;

    @FXML
    private void quenMatKhau(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("QuenMatKhau.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            Stage currentStage = (Stage) btnRegister.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
        }
    }

    @FXML
    private void login(ActionEvent event) {
        try {
//            Session session = HibernateUtil.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//            List users = session.createQuery("FROM User").list();
//            for (Iterator iterator = users.iterator(); iterator.hasNext();) {
//                User employee = (User) iterator.next();
//                System.out.println("Họ và tên: " + employee.getHoTen());
//                System.out.println("------");
//            }
//            transaction.commit();
//            session.close();
            Connection connection = ConnectionUtils.getMyConnection();
            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM user WHERE ten_dang_nhap='"
                    + txtUserName.getText().trim() + "'AND mat_khau='"
                    + txtPassword.getText().trim() + "' ";
            ResultSet rs = statement.executeQuery(sql);
            if (txtUserName.getText().trim().equals("") || txtPassword.getText().trim().equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setContentText("Chưa nhập Tên đăng nhập hoặc Mật khẩu !");
                alert.showAndWait();
            } else if (rs.next()) {
                String quyen = rs.getString("quyen");
                if (quyen.equals("admin")) {
                    HienCuaSo h = new HienCuaSo();
                    h.showWindow("/View/ManHinhChinh.fxml");
                    Stage currentStage = (Stage) btnRegister.getScene().getWindow();
                    currentStage.close();
                } else {
                    HienCuaSo h = new HienCuaSo();
                    h.showWindow("/View/ManHinhUser.fxml");
                    Stage currentStage = (Stage) btnRegister.getScene().getWindow();
                    currentStage.close();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setContentText("Sai tài khoản hoặc mật khẩu !");
                alert.showAndWait();
            }
        } catch (ClassNotFoundException | SQLException e) {
            // TODO: handle exception
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Lỗi kết nối !");
            alert.showAndWait();
        }
    }

    @FXML
    private void register(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("DangKi.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
            Stage currentStage = (Stage) btnRegister.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
