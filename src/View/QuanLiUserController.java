/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.HienCuaSo;
import Model.HibernateUtilUser;
import Model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
    String gioiTinh = "";
    @FXML
    private TableView<User> tbvListUser;
    private ArrayList<User> arrUser = new ArrayList<User>();

    @FXML
    private void capNhat(ActionEvent event) {
        if (txtTenDangNhap.getText().trim().equals("")
                || txtDiaChi.getText().trim().equals("")
                || txtEmail.getText().trim().equals("")
                || txtHoTen.getText().trim().equals("")
                || txtMatKhau.getText().trim().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Phải nhập đầy đủ thông tin !");
            alert.showAndWait();
            return;
        }
        if (radGioiTinh1.isSelected()) {
            gioiTinh = "Nam";
        } else if (radGioiTinh2.isSelected()) {
            gioiTinh = "Nữ";
        }
        if (radQuyen1.isSelected()) {
            quyen = "admin";
        } else if (radQuyen2.isSelected()) {
            quyen = "khach";
        }
        if (checkEmail(txtEmail.getText().trim()) == false) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Email không hợp lệ !");
            alert.showAndWait();
            return;
        }
        SessionFactory factory = HibernateUtilUser.getSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setId(Integer.parseInt(lbId.getText()));
        user.setUsername(txtTenDangNhap.getText());
        user.setPassword(txtMatKhau.getText());
        user.setQuyen(quyen);
        user.setHoTen(txtHoTen.getText());
        user.setEmail(txtEmail.getText());
        user.setDiaChi(txtDiaChi.getText());
        user.setGioiTinh(gioiTinh);
        session.update(user);
        transaction.commit();
        Criteria criteria = session.createCriteria(User.class);
        List result = criteria.list();
        Iterator iterator = result.iterator();

        if (iterator.hasNext()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Cập nhật thành công !");
            alert.showAndWait();
            HienCuaSo h = new HienCuaSo();
            h.showWindow("/View/QuanLiUser.fxml");
            Stage currentStage = (Stage) btCapNhat.getScene().getWindow();
            currentStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Một lỗi đã xãy ra !");
            alert.showAndWait();
        }
        session.close();
    }

    @FXML
    private void xoa(ActionEvent event) {

        SessionFactory factory = HibernateUtilUser.getSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("id", Integer.parseInt(lbId.getText())));
        List result = criteria.list();
        Iterator iterator = result.iterator();
        User user = (User) iterator.next();
        session.delete(user);
        transaction.commit();
        result = criteria.list();
        iterator = result.iterator();
        if (!iterator.hasNext()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Xóa thành công !");
            alert.showAndWait();
            Stage currentStage = (Stage) btXoa.getScene().getWindow();
            currentStage.close();
            HienCuaSo h = new HienCuaSo();
            h.showWindow("/View/QuanLiUser.fxml");
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Một lỗi đã xãy ra !");
            alert.showAndWait();

        }
        session.close();
    }

    @FXML
    private void load() {
        try {
            tbvListUser.setOnMouseClicked(e -> {
                if (e.getClickCount() == 1) {
                    User u = tbvListUser.getSelectionModel().getSelectedItem();

                    String id = String.valueOf(u.getId());
                    String tenDangNhap = u.getUsername();
                    String hoTen = u.getHoTen();
                    String email = u.getEmail();
                    String diaChi = u.getDiaChi();
                    String matKhau = u.getPassword();
                    gioiTinh = u.getGioiTinh();
                    quyen = u.getQuyen();

                    txtTenDangNhap.setText(tenDangNhap);
                    txtHoTen.setText(hoTen);
                    txtEmail.setText(email);
                    txtDiaChi.setText(diaChi);
                    txtMatKhau.setText(matKhau);
                    lbId.setText(id);

                    if (gioiTinh.equals("Nam")) {
                        radGioiTinh1.setSelected(true);
                    } else {
                        radGioiTinh2.setSelected(true);
                    }

                    if (quyen.equals("admin")) {
                        radQuyen1.setSelected(true);
                    } else {
                        radQuyen2.setSelected(true);
                    }

                }
            });
        } catch (Exception rx) {
            // TODO: handle exception
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setContentText("Lỗi kết nối !");
            alert.showAndWait();
        }
    }

    private void loadData() {

        ToggleGroup groupGioiTinh = new ToggleGroup();
        radGioiTinh1.setToggleGroup(groupGioiTinh);
        radGioiTinh2.setToggleGroup(groupGioiTinh);

        ToggleGroup groupQuyen = new ToggleGroup();
        radQuyen1.setToggleGroup(groupQuyen);
        radQuyen2.setToggleGroup(groupQuyen);

        SessionFactory factory = HibernateUtilUser.getSessionFactory();
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        List result = criteria.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            User u = (User) iterator.next();
            arrUser.add(u);
        }
        TableColumn<User, String> id = new TableColumn<User, String>("Mã user");
        TableColumn<User, String> username = new TableColumn<User, String>("Tên đăng nhập");
        TableColumn<User, String> password = new TableColumn<User, String>("Mật khẩu");
        TableColumn<User, String> quyen = new TableColumn<User, String>("Quyền");
        TableColumn<User, String> hoTen = new TableColumn<User, String>("Họ tên");
        TableColumn<User, String> email = new TableColumn<User, String>("Email");
        TableColumn<User, String> diaChi = new TableColumn<User, String>("Địa chỉ");
        TableColumn<User, String> gioiTinh = new TableColumn<User, String>("Giới tính");

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        quyen.setCellValueFactory(new PropertyValueFactory<>("quyen"));
        hoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        diaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        gioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));

        ObservableList<User> list = getUserList();
        tbvListUser.setItems(list);
        tbvListUser.getColumns().addAll(id, username, password, quyen, hoTen, email, diaChi, gioiTinh);
        session.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        load();
    }

    private ObservableList<User> getUserList() {
        ObservableList<User> list = FXCollections.observableArrayList(arrUser);
        return list;
    }

    private Boolean checkEmail(String email) {
        String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern regex = Pattern.compile(emailPattern);
        Matcher matcher = regex.matcher(email);
        return matcher.find();
    }
    @FXML
    public void quayVe() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ManHinhChinh.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage) btCapNhat.getScene().getWindow();
        currentStage.close();
    }
}
