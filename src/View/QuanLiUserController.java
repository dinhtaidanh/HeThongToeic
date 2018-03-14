/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.HienCuaSo;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private Label lbId;
    String quyen = "";
    String gioitinh = "";
    @FXML
    private TableView<Acc> tbvListUser; 
    private ArrayList<Acc> arrAcc = new ArrayList<Acc>();
    @FXML
    private void capNhat(ActionEvent event) {
        try{
            if(txtTenDangNhap.getText().trim().equals("") || txtDiaChi.getText().trim().equals("")|| txtEmail.getText().trim().equals("")|| txtHoTen.getText().trim().equals("")|| txtMatKhau.getText().trim().equals("")){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setContentText("Phải nhập đầy đủ thông tin !");
                    alert.showAndWait();
                    return;
                }
            if(radGioiTinh1.isSelected()){
                gioitinh = "Nam";
            }
            else if(radGioiTinh2.isSelected()){
                gioitinh = "Nữ";
            }
            if(radQuyen1.isSelected()){
                quyen = "admin";
            }else if(radQuyen2.isSelected()){
                quyen = "khach";
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
                String sql = "UPDATE user SET ten_dang_nhap ='"+txtTenDangNhap.getText().trim()+"',mat_khau ='"+txtMatKhau.getText().trim()+"',quyen ='"+quyen+"',ho_ten='"+txtHoTen.getText().trim()+"',email = '"+txtEmail.getText().trim()+"',dia_chi = '"+txtDiaChi.getText().trim()+"',gioi_tinh='"+gioitinh+"'WHERE id='"+lbId.getText()+"'";
                int rowCount = 0;
                rowCount = statement.executeUpdate(sql);                
                if(rowCount != 0)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setContentText("Cập nhật thành công !");
                    alert.showAndWait(); 
                    HienCuaSo h = new HienCuaSo();
                    h.showWindow("/View/QuanLiUser.fxml");
                    Stage stage1 = (Stage) btCapNhat.getScene().getWindow();
                    stage1.close();

                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setContentText("Một lỗi đã xãy ra !");
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
    private void xoa(ActionEvent event) {
        try{            
                Connection connection = ConnectionUtils.getMyConnection();             
                Statement statement = connection.createStatement();
                String sql = "DELETE FROM user WHERE id = '"+lbId.getText()+"'";             
                int rowCount = 0;
                rowCount = statement.executeUpdate(sql);                
                if(rowCount != 0)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setContentText("Xóa thành công !");
                    alert.showAndWait();                  
                    HienCuaSo h = new HienCuaSo();
                    h.showWindow("/View/QuanLiUser.fxml");
                    Stage stage1 = (Stage) btXoa.getScene().getWindow();
                    stage1.close();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setContentText("Một lỗi đã xãy ra !");
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
    private void load() { 
        try{
            tbvListUser.setOnMouseClicked(e ->{
            if( e.getClickCount() == 1 ) {
            Acc p = tbvListUser.getSelectionModel().getSelectedItem();
            
            String id = p.getId();
            String tenDangNhap = p.getTenDangNhap();
            String hoTen = p.getHoTen();
            String email = p.getEmail();
            String diaChi = p.getDiaChi();
            String matKhau = p.getMatKhau();
            String gioiTinh = p.getGioiTinh();
            String quyen = p.getQuyen();
            
            txtTenDangNhap.setText(tenDangNhap);
            txtHoTen.setText(hoTen);
            txtEmail.setText(email);
            txtDiaChi.setText(diaChi);
            txtMatKhau.setText(matKhau);            
            lbId.setText(id);  
            
            if(gioiTinh.equals("Nam")){
                radGioiTinh1.setSelected(true);
            }
            else{radGioiTinh2.setSelected(true);}
            
            if(quyen.equals("admin")){
                radQuyen1.setSelected(true);
            }
            else{radQuyen2.setSelected(true);}
            
            }});             
        }catch (Exception rx) {
                // TODO: handle exception
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setContentText("Lỗi kết nối !");
                alert.showAndWait();
            }
    }
    private void loaddata(){
        try{       
                ToggleGroup group = new ToggleGroup();
                radGioiTinh1.setToggleGroup(group);
                radGioiTinh2.setToggleGroup(group);
                
                ToggleGroup group1 = new ToggleGroup();
                radQuyen1.setToggleGroup(group1);
                radQuyen2.setToggleGroup(group1);
                
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
                
                statement.close();
                
  
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
         loaddata();
         load();
    }
    private ObservableList<Acc> getUserList() {        
        ObservableList<Acc> list = FXCollections.observableArrayList(arrAcc);
        return list;
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
}
