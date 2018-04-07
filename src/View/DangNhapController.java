package View;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Model.MaHoaPass;
import Model.User;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.Iterator;
import java.util.List;
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
import javafx.stage.StageStyle;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ASUS
 */
public class DangNhapController implements Initializable {

    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnRegister;

    @FXML
    private void login(ActionEvent event) throws UnsupportedEncodingException {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        config.addAnnotatedClass(User.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
        SessionFactory factory = config.buildSessionFactory(builder.build());

        Session session = factory.openSession();
        //Transaction trans = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("username", txtUserName.getText().trim()));
        criteria.add(Restrictions.eq("password", MaHoaPass.maHoa(txtPassword.getText().trim())));    
        List result = criteria.list();      
        Iterator iterator = result.iterator();
        if(iterator.hasNext()){
        User u = (User)iterator.next();
        if(u.getQuyen().equals("admin")){
            try{
            Parent root = FXMLLoader.load(getClass().getResource("ManHinhChinh.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);  
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            Stage currentStage = (Stage) btnRegister.getScene().getWindow();
            currentStage.close();
            }catch (IOException e) {}
        }
        else if(u.getQuyen().equals("khach")){
            try{
            Parent root = FXMLLoader.load(getClass().getResource("ManHinhUser.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);  
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            Stage currentStage = (Stage) btnRegister.getScene().getWindow();
            currentStage.close();
            }catch (IOException e) {}
        }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi đăng nhập");
            alert.setHeaderText("Không thể đăng nhập");
            alert.setContentText("Sai tài khoản hoặc mật khẩu");
            alert.showAndWait();
        }
        session.close();
    }

    @FXML
    private void register(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("DangKi.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setResizable(false);
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
