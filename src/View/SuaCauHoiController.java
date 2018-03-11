/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Cau;
import Model.ConnectionUtils;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

/**
 * FXML Controller class
 *
 * @author Danh
 */
public class SuaCauHoiController implements Initializable {
    private Cau cauDuocChon = new Cau();
    public Cau getCauDuocChon() {
        return cauDuocChon;
    }
    public void setCauDuocChon(Cau cauDuocChon) {
        this.cauDuocChon = cauDuocChon;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO     
        cbDapAnNguPhapDung.getItems().removeAll(cbDapAnNguPhapDung.getItems());
        cbDapAnNguPhapDung.getItems().addAll("A", "B", "C","D");
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
    private HTMLEditor  htmlEditor;
    @FXML
    private ComboBox cbDapAnNguPhapDung;
    @FXML
    private void onSaveChange(ActionEvent event)throws ClassNotFoundException,SQLException{       
        Connection connection = ConnectionUtils.getMyConnection();
        Statement statement = connection.createStatement();
        String CauHoi = "'"+htmlEditor.getHtmlText()+"'";
        String DapAn1 = "'"+txtDapAnNguPhapA.getText()+"'";
        String DapAn2 = "'"+txtDapAnNguPhapB.getText()+"'";
        String DapAn3 = "'"+txtDapAnNguPhapC.getText()+"'";
        String DapAn4 = "'"+txtDapAnNguPhapD.getText()+"'";
        String DapAnDung = "'"+cbDapAnNguPhapDung.getSelectionModel().getSelectedItem().toString()+"'";
        
        String sql = "UPDATE nguphap SET CauHoi= '" + CauHoi + "', "
                + "DapAn1= '" + DapAn1 + "', "
                + "DapAn2= '" + DapAn2 + "', "
                + "DapAn3= '" + DapAn3 + "', "
                + "DapAn4= '" + DapAn4 + "', "
                + "DapAnDung= '" + DapAnDung +"' "  
                + "WHERE Id = " + cauDuocChon.getId();
                      
 
      // Thực thi câu lệnh.
      // executeUpdate(String) sử dụng cho các loại lệnh Insert,Update,Delete.
      System.out.println(DapAnDung+" " +htmlEditor.getHtmlText());
      int rowCount=0;
      rowCount = statement.executeUpdate(sql);
 
      // In ra số dòng được trèn vào bởi câu lệnh trên.
      if(rowCount!=0){
          Alert a = new Alert(Alert.AlertType.INFORMATION);
          a.setContentText("Sửa câu hỏi thành công");
          a.show();
      }else{
          Alert a = new Alert(Alert.AlertType.ERROR);
          a.setContentText("Sửa câu hỏi thất bại");
          a.show();
      }
    }
}
