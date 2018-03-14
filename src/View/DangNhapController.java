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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class DangNhapController implements Initializable {
    
    @FXML
    private Hyperlink QuenMatKhau;
    @FXML
    private TextField txtTenDangNhap; 
    @FXML
    private PasswordField txtMatKhau; 
    @FXML
    private Button btnDangNhap; 
    @FXML 
    private AnchorPane pane = new AnchorPane();
    @FXML
    private void quenMatKhau(ActionEvent event) {
        try{
            
        Parent root = FXMLLoader.load(getClass().getResource("QuenMatKhau.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);  
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage) btnDangNhap.getScene().getWindow();
        currentStage.close();
        }catch (IOException e) {e.printStackTrace();}
    }
    @FXML
    private void onDangNhap(ActionEvent event) {
        try{
                Connection connection = ConnectionUtils.getMyConnection();             
                Statement statement = connection.createStatement();

                String sql = "SELECT * FROM user WHERE ten_dang_nhap='"
                        +txtTenDangNhap.getText().trim()+"'AND mat_khau='"+
                        txtMatKhau.getText().trim()+"' ";                    
                ResultSet rs = statement.executeQuery(sql);
                if(txtTenDangNhap.getText().trim().equals("") || txtMatKhau.getText().trim().equals("")){
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
                        Stage stage = (Stage) btnDangNhap.getScene().getWindow();
                        stage.close();
                    }
                    else{
                        HienCuaSo h = new HienCuaSo();
                        h.showWindow("/View/ManHinhUser.fxml");
                        Stage stage = (Stage) btnDangNhap.getScene().getWindow();
                        stage.close();
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
    @FXML
    private void onDangKy(ActionEvent event) {
        try{
            
        Parent root = FXMLLoader.load(getClass().getResource("DangKi.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);  
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();  
        Stage currentStage = (Stage) btnDangNhap.getScene().getWindow();
        currentStage.close();
        }catch (IOException e) {e.printStackTrace();}
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
//        currentStage.setTitle("Đăng nhập");
    }    
    
}
