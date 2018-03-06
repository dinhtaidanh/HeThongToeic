/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.ConnectionUtils;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

/**
 * FXML Controller class
 *
 * @author Danh
 */
public class ThemNguPhapController implements Initializable {

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO    
    }   
    @FXML
    private TextField txtDapAnNguPhapA;
    @FXML
    private TextField txtDapAnNguPhapB;
    @FXML
    private TextField txtDapAnNguPhapC;
    @FXML
    private TextField txtDapAnNguPhapD;
    @FXML
    private TextField txtDapAnNguPhapDung;
    @FXML
    private HTMLEditor  htmlEditor;
    @FXML
    private void onThemCauHoiNguPhap(ActionEvent event)throws ClassNotFoundException,
          SQLException{
        // Lấy ra kết nối tới cơ sở dữ liệu.
        
        Connection connection = ConnectionUtils.getMyConnection();
        Statement statement = connection.createStatement();
        String CauHoi = "'"+htmlEditor.getHtmlText()+"'";
        String DapAn1 = "'"+txtDapAnNguPhapA.getText()+"'";
        String DapAn2 = "'"+txtDapAnNguPhapB.getText()+"'";
        String DapAn3 = "'"+txtDapAnNguPhapC.getText()+"'";
        String DapAn4 = "'"+txtDapAnNguPhapD.getText()+"'";
        int DapAnDung = Integer.parseInt(txtDapAnNguPhapDung.getText());
        
        String sql = "INSERT INTO nguphap(CauHoi, DapAn1, DapAn2, DapAn3, DapAn4,DapAnDung)"
                + "VALUES ("+CauHoi
                + ", " + DapAn1
                + ", " + DapAn2
                + ", " + DapAn3
                + ", " + DapAn4
                + ", " + DapAnDung + ")";
 
      // Thực thi câu lệnh.
      // executeUpdate(String) sử dụng cho các loại lệnh Insert,Update,Delete.
      int rowCount = statement.executeUpdate(sql);
 
      // In ra số dòng được trèn vào bởi câu lệnh trên.
      System.out.println("Row Count affected = " + rowCount);
    }
}
