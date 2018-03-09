/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Cau;
import Model.ListCauHoi;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Danh
 */
public class QuanLyNguPhapController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Cau> tbvListCauHoi = new TableView<Cau>();
    private ListCauHoi dsCauHoi = new ListCauHoi("nguphap",0,10);
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TableColumn<Cau, String> STT = new TableColumn<Cau, String>("STT");
        TableColumn<Cau, String> CauHoi = new TableColumn<Cau, String>("Câu hỏi");
        TableColumn<Cau, String> dA = new TableColumn<Cau, String>("A");
        TableColumn<Cau, String> dB = new TableColumn<Cau, String>("B");
        TableColumn<Cau, String> dC = new TableColumn<Cau, String>("C");
        TableColumn<Cau, String> dD = new TableColumn<Cau, String>("D");
        TableColumn<Cau, String> DapAnDung = new TableColumn<Cau, String>("Đáp án đúng");
            
        CauHoi.setMaxWidth(200);
        STT.setCellValueFactory(new PropertyValueFactory<>("STT"));
        CauHoi.setCellValueFactory(new PropertyValueFactory<>("CauHoi"));
        dA.setCellValueFactory(new PropertyValueFactory<>("DapAn1"));
        dB.setCellValueFactory(new PropertyValueFactory<>("DapAn2"));
        dC.setCellValueFactory(new PropertyValueFactory<>("DapAn3"));
        dD.setCellValueFactory(new PropertyValueFactory<>("DapAn4"));
        DapAnDung.setCellValueFactory(new PropertyValueFactory<>("DapAnDung"));
        
        ObservableList<Cau> list = getUserList();
        tbvListCauHoi.setItems(list);
        tbvListCauHoi.getColumns().addAll(STT,CauHoi,dA,dB,dC,dD,DapAnDung);
        
        
    }    
    private ObservableList<Cau> getUserList() {        
        ObservableList<Cau> list = FXCollections.observableArrayList(dsCauHoi.getDsCau());
        return list;
    }
}