package View;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Controller.HienCuaSo;
import Model.ConnectionUtils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;

/**
 *
 * @author ASUS
 */
public class DangNhapController implements Initializable {
    
    @FXML
    private TextField txttendangnhap; 
    @FXML
    private PasswordField txtmatkhau; 
    @FXML
    private Button btdangnhap;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        try{
                Connection connection = ConnectionUtils.getMyConnection();             
                Statement statement = connection.createStatement();

                String sql = "SELECT * FROM user WHERE ten_dang_nhap='"+txttendangnhap.getText().trim()+"'AND mat_khau='"+ txtmatkhau.getText().trim()+"' ";                    
                ResultSet rs = statement.executeQuery(sql);
                if(txttendangnhap.getText().trim().equals("") || txtmatkhau.getText().trim().equals("")){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setContentText("Chưa nhập Tên đăng nhập hoặc Mật khẩu !");
                    alert.showAndWait();
                }
                else if(rs.next())
                {
                    String quyen = rs.getString("quyen");
                    if (quyen.equals("admin")){
                        HienCuaSo h = new HienCuaSo();
                        h.showWindow("/View/ManHinhChinh.fxml");
                    }
                    else{
                        /*code mở form màn hình chính cho người dùng ở đây*/
                    }

                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setContentText("Sai tài khoản hoặc mật khẩu !");
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
