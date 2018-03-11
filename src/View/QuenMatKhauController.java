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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class QuenMatKhauController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtTenDangNhap;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtMatKhau;
    @FXML
    private Button btCapNhat;
    @FXML
    private void capNhat(ActionEvent event) throws IOException {
         try{
            if(txtTenDangNhap.getText().trim().equals("") || txtEmail.getText().trim().equals("")||  txtMatKhau.getText().trim().equals("")){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setContentText("Phải nhập đầy đủ thông tin !");
                    alert.showAndWait();
                    return;
                }
            if(checkEmail(txtEmail.getText().trim())== false){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setContentText("Email không hợp lệ !");
                alert.showAndWait();
                return;
            }
                Connection connection = ConnectionUtils.getMyConnection();             
                Statement statement = connection.createStatement();
                String sql = "UPDATE user SET mat_khau ='"+txtMatKhau.getText().trim()+"'WHERE ten_dang_nhap='"+txtTenDangNhap.getText().trim()+"'AND email='"+ txtEmail.getText().trim()+"'";
                int rowCount = 0;
                rowCount = statement.executeUpdate(sql);                
                if(rowCount != 0)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setContentText("Thay đổi mật khẩu thành công !");
                    alert.showAndWait();
                    Parent root = FXMLLoader.load(getClass().getResource("DangNhap.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);  
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(scene);
                    stage.show();
                    Stage stage1 = (Stage) btCapNhat.getScene().getWindow();
                    stage1.close();

                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setContentText("Kiểm tra lại tên đăng nhập và Email !");
                    alert.showAndWait();
                }
            
       
            }
            catch (ClassNotFoundException | SQLException e) {
                // TODO: handle exception
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setContentText("Lỗi kết nối !");
                alert.showAndWait();
            }
    }
    private Boolean checkEmail(String email) {
        String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern regex = Pattern.compile(emailPattern);
        Matcher matcher = regex.matcher(email);
        if (matcher.find()) {
            return true;
        } else {           
            return false;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
