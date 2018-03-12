/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Acc;
import Model.Cau;
import Model.ConnectionUtils;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class QuanLiUserController implements Initializable {

    /**
     * Initializes the controller class.
     */   
    @FXML
    private TextField txtTenDangNhap;
    @FXML
    private TextField txtHoTen;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtDiaChi;
    @FXML
    private RadioButton radGioiTinh1;
    @FXML
    private RadioButton radGioiTinh2;
    @FXML
    private RadioButton radQuyen1;
    @FXML
    private RadioButton radQuyen2;
    @FXML
    private TextField txtMatKhau;
    @FXML
    private Button btCapNhat;
    @FXML
    private Button btXoa;
    @FXML
    private TableView<Acc> tbvListUser; 
    private ArrayList<Acc> arrAcc = new ArrayList<Acc>();
    @FXML
    private void capNhat(ActionEvent event) {
        
    }
    @FXML
    private void xoa(ActionEvent event) {
        
    }  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try{
                Connection connection = ConnectionUtils.getMyConnection();             
                Statement statement = connection.createStatement();
                String sql = "SELECT * FROM user ";                    
                ResultSet rs = statement.executeQuery(sql);
                while(rs.next())
                {
                    Acc acc = new Acc();
                    acc.setId(rs.getString("id"));
                    acc.setTenDangNhap(rs.getString("ten_dang_nhap"));
                    acc.setMatKhau(rs.getString("mat_khau"));
                    acc.setHoTen(rs.getString("ho_ten"));
                    acc.setDiaChi(rs.getString("dia_chi"));
                    acc.setGioiTinh(rs.getString("gioi_tinh"));
                    acc.setEmail(rs.getString("email"));
                    acc.setQuyen(rs.getString("quyen"));
                    arrAcc.add(acc);
                }   
            }
            catch (ClassNotFoundException | SQLException e) {
                // TODO: handle exception
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setContentText("Lỗi kết nối !");
                alert.showAndWait();
            }
         TableColumn<Acc, String> id = new TableColumn<Acc, String>("Mã user");
         TableColumn<Acc, String> tenDangNhap = new TableColumn<Acc, String>("Tên đăng nhập");
         TableColumn<Acc, String> matKhau = new TableColumn<Acc, String>("Mật khẩu");
         TableColumn<Acc, String> quyen = new TableColumn<Acc, String>("Quyền");
         TableColumn<Acc, String> hoTen = new TableColumn<Acc, String>("Họ tên");
         TableColumn<Acc, String> email = new TableColumn<Acc, String>("Email");
         TableColumn<Acc, String> diaChi = new TableColumn<Acc, String>("Địa chỉ");
         TableColumn<Acc, String> gioiTinh = new TableColumn<Acc, String>("Giới tính");
         
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tenDangNhap.setCellValueFactory(new PropertyValueFactory<>("tenDangNhap"));
        matKhau.setCellValueFactory(new PropertyValueFactory<>("matKhau"));
        quyen.setCellValueFactory(new PropertyValueFactory<>("quyen"));
        hoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        diaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        gioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        
        ObservableList<Acc> list = getUserList();
        tbvListUser.setItems(list);
        tbvListUser.getColumns().addAll(id,tenDangNhap,matKhau,quyen,hoTen,email,diaChi,gioiTinh);
    }
    private ObservableList<Acc> getUserList() {        
        ObservableList<Acc> list = FXCollections.observableArrayList(arrAcc);
        return list;
    }
}
