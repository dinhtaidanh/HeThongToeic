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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Danh
 */
public class ThemDienKhuyetController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private TextArea txtCauHoiDienKhuyet;
    @FXML
    private TextField txtDapAnDienKhuyetA;
    @FXML
    private TextField txtDapAnDienKhuyetB;
    @FXML
    private TextField txtDapAnDienKhuyetC;
    @FXML
    private TextField txtDapAnDienKhuyetD;
    @FXML
    private TextField txtDapAnDienKhuyetDung;
    @FXML
    private void onThemCauHoiDienKhuyet(ActionEvent event)throws ClassNotFoundException,
          SQLException{
        // Lấy ra kết nối tới cơ sở dữ liệu.
        
        Connection connection = ConnectionUtils.getMyConnection();
        Statement statement = connection.createStatement();
        String CauHoi = "'"+txtCauHoiDienKhuyet.getText()+"'";
        String DapAn1 = "'"+txtDapAnDienKhuyetA.getText()+"'";
        String DapAn2 = "'"+txtDapAnDienKhuyetB.getText()+"'";
        String DapAn3 = "'"+txtDapAnDienKhuyetC.getText()+"'";
        String DapAn4 = "'"+txtDapAnDienKhuyetD.getText()+"'";
        int DapAnDung = -1;
        switch(txtDapAnDienKhuyetDung.getText()){
            case "A" : DapAnDung = 0;
            break;
            case "B": DapAnDung = 1;
            break;
            case "C": DapAnDung = 2;
            break;
            case "D": DapAnDung = 3;
        }
        
        
        String sql = "INSERT INTO dienkhuyet(CauHoi, DapAn1, DapAn2, DapAn3, DapAn4,DapAnDung)"
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
