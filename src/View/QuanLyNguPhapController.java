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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Danh
 */
public class QuanLyNguPhapController implements Initializable {

   
    @FXML
    private TableView<Cau> tbvListCauHoi = new TableView<Cau>();
    @FXML
    private Button btnThemCauHoi = new Button();
    @FXML
    private Button btnSuaCauHoi = new Button();
    @FXML
    private Button btnXoaCauHoi = new Button();
    
    private ListCauHoi dsCauHoi = new ListCauHoi();
    private Cau cauDuocChon;
    private int rowCount = 0;
    public Cau getCauDuocChon() {
        return cauDuocChon;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO      
        loadListCauHoi();
        if(tbvListCauHoi.getSelectionModel().isEmpty()){
            btnSuaCauHoi.setDisable(true);
            btnXoaCauHoi.setDisable(true);
        }
        tbvListCauHoi.getFocusModel().focusedCellProperty().addListener((ObservableValue<? extends TablePosition> observable, TablePosition oldPos, TablePosition pos) -> {
            btnSuaCauHoi.setDisable(false);
            btnXoaCauHoi.setDisable(false);
        });
    }    
    @SuppressWarnings("empty-statement")
    private ObservableList<Cau> getQuestionList() {  ;
        dsCauHoi.readTable("nguphap");
        ObservableList<Cau> list = FXCollections.observableArrayList(dsCauHoi.getDsCau());
        return list;
    }
    @FXML
    private void onAddQuestion(){
        showStage("ThemNguPhap.fxml");
    }
    @FXML
    private void onDeleteQuestion(){
        cauDuocChon = tbvListCauHoi.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xóa câu hỏi");
        alert.setHeaderText("Xóa câu hỏi được chọn");
        alert.setContentText("Bạn có chắc chắn?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try{
            Connection connection = ConnectionUtils.getMyConnection();
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM nguphap WHERE Id="+cauDuocChon.getId();
            statement.executeUpdate(sql);
            tbvListCauHoi.getItems().remove(cauDuocChon);
            }catch(ClassNotFoundException | SQLException ex){
                
            }
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }
    @FXML
    private void onModifyQuestion(){
        //Text text = new Text(tbvListCauHoi.getSelectionModel().getSelectedItem().getCauHoi());
        cauDuocChon = tbvListCauHoi.getSelectionModel().getSelectedItem();
        Stage s = new Stage();
        GridPane p = new GridPane();
        p.setPadding(new Insets(20));
        p.setVgap(20);
        HTMLEditor htmlEditor = new HTMLEditor();
        htmlEditor.setHtmlText(cauDuocChon.getCauHoi());
        htmlEditor.setMaxHeight(200);
        TextField txtDapAn1 = new TextField(cauDuocChon.getDapAn1());
        TextField txtDapAn2 = new TextField(cauDuocChon.getDapAn2());
        TextField txtDapAn3 = new TextField(cauDuocChon.getDapAn3());
        TextField txtDapAn4 = new TextField(cauDuocChon.getDapAn4());     
        ComboBox cbDapAnNguPhapDung = new ComboBox();
        cbDapAnNguPhapDung.getItems().addAll("A", "B", "C","D");
        cbDapAnNguPhapDung.setValue(cauDuocChon.getDapAnDung());
        cbDapAnNguPhapDung.promptTextProperty().set(cauDuocChon.getDapAnDung());
        Button btnLuu = new Button("Lưu lại");
        btnLuu.setOnAction(event -> {
            try{              
                Connection connection = ConnectionUtils.getMyConnection();
                Statement statement = connection.createStatement();
                String sql = "UPDATE nguphap SET CauHoi = ?, DapAn1 = ?, DapAn2 = ?"+
                        ", DapAn3 = ?, DapAn4 = ?, DapAnDung = ? WHERE Id = ?";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1,htmlEditor.getHtmlText());
                    ps.setString(2, txtDapAn1.getText());
                    ps.setString(3, txtDapAn2.getText());
                    ps.setString(4, txtDapAn3.getText());
                    ps.setString(5, txtDapAn4.getText());
                    ps.setString(6, cbDapAnNguPhapDung.getSelectionModel().getSelectedItem().toString());
                    ps.setInt(7, cauDuocChon.getId());
                    rowCount = ps.executeUpdate();
                }
                if(rowCount != 0){
                     Alert a = new Alert(Alert.AlertType.INFORMATION);
                     a.setContentText("Sửa câu hỏi thành công!");
                     a.show();                   
                 }
                else{
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Sửa câu hỏi thất bại!");
                    a.show();
                }
                }catch(ClassNotFoundException|SQLException e){
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Lỗi cú pháp SQL!!!");
                    a.show();
                }    
        });
        p.add(new Label("Câu hỏi: "),0,0);
        p.add(new Label("Đáp án A: "),0,1);
        p.add(new Label("Đáp án B: "),0,2);
        p.add(new Label("Đáp án C: "),0,3);
        p.add(new Label("Đáp án D: "),0,4);
        p.add(new Label("Đáp án đúng: "),0,5);
        p.add(htmlEditor,1,0);
        p.add(txtDapAn1,1,1);
        p.add(txtDapAn2,1,2);
        p.add(txtDapAn3,1,3);
        p.add(txtDapAn4,1,4);
        p.add(cbDapAnNguPhapDung,1,5);
        p.add(btnLuu, 1, 6);
        Scene scene = new Scene(p,1000,500);
        s.setScene(scene);
        s.show();
            
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
    private void closeCurrentStage(){
        Stage currentStage = (Stage) tbvListCauHoi.getScene().getWindow();
        currentStage.close();
    }
    private void loadListCauHoi(){       
        TableColumn<Cau, String> Id = new TableColumn<>("Mã câu");
        TableColumn<Cau, String> CauHoi = new TableColumn<Cau, String>("Câu hỏi");
        TableColumn<Cau, String> dA = new TableColumn<Cau, String>("A");
        TableColumn<Cau, String> dB = new TableColumn<Cau, String>("B");
        TableColumn<Cau, String> dC = new TableColumn<Cau, String>("C");
        TableColumn<Cau, String> dD = new TableColumn<Cau, String>("D");
        TableColumn<Cau, String> DapAnDung = new TableColumn<Cau, String>("Đáp án đúng");
            
        CauHoi.setMaxWidth(200);
        Id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        CauHoi.setCellValueFactory(new PropertyValueFactory<>("CauHoi"));
        dA.setCellValueFactory(new PropertyValueFactory<>("DapAn1"));
        dB.setCellValueFactory(new PropertyValueFactory<>("DapAn2"));
        dC.setCellValueFactory(new PropertyValueFactory<>("DapAn3"));
        dD.setCellValueFactory(new PropertyValueFactory<>("DapAn4"));
        DapAnDung.setCellValueFactory(new PropertyValueFactory<>("DapAnDung"));
        
        ObservableList<Cau> list = getQuestionList();
        tbvListCauHoi.setItems(list);
        tbvListCauHoi.getColumns().addAll(Id,CauHoi,dA,dB,dC,dD,DapAnDung);
        
    }
    @FXML
    private void onReturn(){
        closeCurrentStage();       
    }
    @FXML
    private void onRefreshList(){
        closeCurrentStage();
        showStage("QuanLyNguPhap.fxml");
    }
}
