/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Cau;
import Model.ConnectionUtils;
import Model.ListCauHoi;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Danh
 */
public class QuanLyNguPhapController implements Initializable {

   
    @FXML
    private TableView<Cau> tbvListCauHoi = new TableView<Cau>();
    private ListCauHoi dsCauHoi = new ListCauHoi();
    private Cau cauDuocChon;
    public Cau getCauDuocChon() {
        return cauDuocChon;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dsCauHoi.readTable("nguphap");
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
    @FXML
    private void onAddQuestion(){
        showStage("ThemNguPhap.fxml");
    }
    @FXML
    private void onDeleteQuestion(){
        
    }
    @FXML
    private void onModifyQuestion(){
        //Text text = new Text(tbvListCauHoi.getSelectionModel().getSelectedItem().getCauHoi());
        cauDuocChon = tbvListCauHoi.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SuaCauHoi.fxml"));
        SuaCauHoiController controller = loader.getController();
        Parent root = loader.load();
        controller.setCauDuocChon(cauDuocChon);
        Stage stage = new Stage();
        Scene scene = new Scene(root);  
        stage.setScene(scene);
        stage.show();    
        }catch(IOException e){
            
        }
            
    }
    private void Connect(){
        try{
        Connection connection = ConnectionUtils.getMyConnection();
        Statement statement = connection.createStatement();
        }catch(ClassNotFoundException|SQLException e){
            
        }
    }
    private void showStage(String fxmlPath){
        try{
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = new Stage();
        Scene scene = new Scene(root);  
        stage.setScene(scene);
        stage.show();    
        }catch (IOException e) {
        }
    }
}
