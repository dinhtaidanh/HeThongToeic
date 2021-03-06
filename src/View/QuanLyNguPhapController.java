/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.HibernateUtilNguPhap;
import Model.NguPhap;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * FXML Controller class
 *
 * @author Danh
 */
public class QuanLyNguPhapController implements Initializable {

   
    @FXML
    private TableView<NguPhap> tbvListCauHoi = new TableView<>();
    @FXML
    private Button btnThemCauHoi = new Button();
    @FXML
    private Button btnSuaCauHoi = new Button();
    @FXML
    private Button btnXoaCauHoi = new Button();
    @FXML 
    private Button btnRefreshList = new Button();
    @FXML 
    private Button btnReturn = new Button();
    private NguPhap cauDuocChon;
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
        btnThemCauHoi.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/Add_32x32.png"))));
        btnRefreshList.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/Refresh_32x32.png"))));
        btnSuaCauHoi.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/Edit_32x32.png"))));
        btnXoaCauHoi.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/Delete_32x32.png"))));
        btnReturn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/Images/Backward_32x32.png"))));
    }    
    private ObservableList<NguPhap> getQuestionList() {  ;
        SessionFactory factory = HibernateUtilNguPhap.getSessionFactory();
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(NguPhap.class);
        //criteria.add(Restrictions.eq("username", tenDangNhap));
        List result = criteria.list(); 
        ArrayList<NguPhap> listNguPhap = new ArrayList<>();
        Iterator iterator = result.iterator();
        while(iterator.hasNext()){
            NguPhap nguPhap = (NguPhap) iterator.next();
            listNguPhap.add(nguPhap);
        }
        ObservableList<NguPhap> list = FXCollections.observableArrayList(listNguPhap);
        session.close();  
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
            SessionFactory factory = HibernateUtilNguPhap.getSessionFactory();
            Session session = factory.openSession();
            Transaction trans = session.beginTransaction();
            Criteria criteria = session.createCriteria(NguPhap.class);
            criteria.add(Restrictions.eq("id", cauDuocChon.getId()));
            List rs = criteria.list(); 
            Iterator iterator = rs.iterator();
            NguPhap nguPhap = (NguPhap) iterator.next();
            session.delete(nguPhap);
            trans.commit();
            session.close();           
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }
    @FXML
    private void onModifyQuestion(){
        //Text text = new Text(tbvListCauHoi.getSelectionModel().getSelectedItem().getCauHoi());
        cauDuocChon = tbvListCauHoi.getSelectionModel().getSelectedItem();
        Stage s = new Stage();
        s.initModality(Modality.APPLICATION_MODAL);
        s.setResizable(false);
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
            SessionFactory factory = HibernateUtilNguPhap.getSessionFactory();
            Session session = factory.openSession();
            Transaction trans = session.beginTransaction();
            NguPhap nguPhap = new NguPhap();
            nguPhap.setId(cauDuocChon.getId());
            nguPhap.setCauHoi(htmlEditor.getHtmlText());
            nguPhap.setDapAn1(txtDapAn1.getText());
            nguPhap.setDapAn2(txtDapAn2.getText());
            nguPhap.setDapAn3(txtDapAn3.getText());
            nguPhap.setDapAn4(txtDapAn4.getText());
            nguPhap.setDapAnDung(cbDapAnNguPhapDung.getSelectionModel().getSelectedItem().toString());
            session.update(nguPhap);
            trans.commit();
            session.close();
            Stage currentStage = (Stage) btnLuu.getScene().getWindow();
            currentStage.close();
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
        Scene scene = new Scene(p,950,500);
        s.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Toeic.png")));
        s.setTitle("Chỉnh sửa câu hỏi ngữ pháp");
        s.setScene(scene);
        s.show();
            
    }
    private void showStage(String fxmlPath){
        try{
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
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
        TableColumn<NguPhap, String> id = new TableColumn<NguPhap, String>("Mã câu");
        TableColumn<NguPhap, String> cauHoi = new TableColumn<NguPhap, String>("Câu hỏi");
        TableColumn<NguPhap, String> dapAnA = new TableColumn<NguPhap, String>("A");
        TableColumn<NguPhap, String> dapAnB = new TableColumn<NguPhap, String>("B");
        TableColumn<NguPhap, String> dapAnC = new TableColumn<NguPhap, String>("C");
        TableColumn<NguPhap, String> dapAnD = new TableColumn<NguPhap, String>("D");
        TableColumn<NguPhap, String> dapAnDung = new TableColumn<NguPhap, String>("Đáp án đúng");
            
        cauHoi.setMaxWidth(200);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cauHoi.setCellValueFactory(new PropertyValueFactory<>("cauHoi"));
        dapAnA.setCellValueFactory(new PropertyValueFactory<>("dapAn1"));
        dapAnB.setCellValueFactory(new PropertyValueFactory<>("dapAn2"));
        dapAnC.setCellValueFactory(new PropertyValueFactory<>("dapAn3"));
        dapAnD.setCellValueFactory(new PropertyValueFactory<>("dapAn4"));
        dapAnDung.setCellValueFactory(new PropertyValueFactory<>("dapAnDung"));
        
        ObservableList<NguPhap> list = getQuestionList();
        tbvListCauHoi.setItems(list);
        tbvListCauHoi.getColumns().addAll(id,cauHoi,dapAnA,dapAnB,dapAnC,dapAnD,dapAnDung);
        
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
