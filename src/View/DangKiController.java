/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.ConnectionUtils;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    String GioiTinh = "";

    private Boolean checkUserName(String tenDangNhap) {
        try {
            Connection connection = ConnectionUtils.getMyConnection();
            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM user WHERE ten_dang_nhap='" + txtTenDangNhap.getText().trim() + "' ";
            ResultSet rs = statement.executeQuery(sql);
            return rs.next();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO: handle exception
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Lỗi kết nối !");
            alert.showAndWait();
        }
        return null;
    }

    private Boolean checkEmail(String email) {
        String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern regex = Pattern.compile(emailPattern);
        Matcher matcher = regex.matcher(email);
        return matcher.find();
    }

    @FXML
    private void register(ActionEvent event) throws IOException {
        try {
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
                String quyen = "khach";
                Connection connection = ConnectionUtils.getMyConnection();
                Statement statement = connection.createStatement();
                String sql = "INSERT INTO user(ten_dang_nhap,mat_khau,quyen,ho_ten,email,dia_chi,gioi_tinh) VALUES ('" 
                        + txtTenDangNhap.getText() + "','" + txtMatKhau.getText() + "','" + quyen + "','" 
                        + txtHoTen.getText() + "','" + txtEmail.getText() + "','" + txtDiaChi.getText() 
                        + "','" + GioiTinh + "' )";
                int rowCount = 0;
                rowCount = statement.executeUpdate(sql);
                if (rowCount != 0) {
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
                    Stage stage1 = (Stage) btDangKi.getScene().getWindow();
                    stage1.close();

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
        } catch (ClassNotFoundException | SQLException e) {
            // TODO: handle exception
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Lỗi kết nối !");
            alert.showAndWait();
        }
    }

    @FXML
    private void load(ActionEvent event) {
        ToggleGroup group = new ToggleGroup();
        rdNam.setToggleGroup(group);
        rdNu.setToggleGroup(group);
        if (rdNam.isSelected()) {
            GioiTinh = "Nam";
        } else if (rdNu.isSelected()) {
            GioiTinh = "Nữ";
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
