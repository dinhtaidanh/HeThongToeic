/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.HibernateUtilLuyenNghe;
import Model.LuyenNghe;
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
import javafx.stage.Stage;
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
public class QuanLyNghePhotoController implements Initializable {

   
    @FXML
    private TableView<LuyenNghe> tbvListCauHoi = new TableView<>();
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
    private LuyenNghe cauDuocChon;
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
    private ObservableList<LuyenNghe> getQuestionList() {  ;
        SessionFactory factory = HibernateUtilLuyenNghe.getSessionFactory();
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(LuyenNghe.class);
        //criteria.add(Restrictions.eq("username", tenDangNhap));
        List result = criteria.list(); 
        ArrayList<LuyenNghe> listLuyenNghe = new ArrayList<>();
        Iterator iterator = result.iterator();
        while(iterator.hasNext()){
            LuyenNghe luyenNghe = (LuyenNghe) iterator.next();
            listLuyenNghe.add(luyenNghe);
        }
        ObservableList<LuyenNghe> list = FXCollections.observableArrayList(listLuyenNghe);
        session.close();  
        return list;
    }
    @FXML
    private void onAddQuestion(){
        showStage("ThemCauHoiPhoto.fxml");
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
            SessionFactory factory = HibernateUtilLuyenNghe.getSessionFactory();
            Session session = factory.openSession();
            Transaction trans = session.beginTransaction();
            Criteria criteria = session.createCriteria(LuyenNghe.class);
            criteria.add(Restrictions.eq("id", cauDuocChon.getId()));
            List rs = criteria.list(); 
            Iterator iterator = rs.iterator();
            LuyenNghe luyenNghe = (LuyenNghe) iterator.next();
            session.delete(luyenNghe);
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
        GridPane p = new GridPane();
        p.setPadding(new Insets(20));
        p.setVgap(20);      
        TextField txtLinkAudio = new TextField(cauDuocChon.getLinkAudio());
        TextField txtLinkPhoto = new TextField(cauDuocChon.getLinkPhoto());     
        ComboBox cbDapAnLuyenNgheDung = new ComboBox();
        cbDapAnLuyenNgheDung.getItems().addAll("A", "B", "C","D");
        cbDapAnLuyenNgheDung.setValue(cauDuocChon.getDapAn());
        cbDapAnLuyenNgheDung.promptTextProperty().set(cauDuocChon.getDapAn());
        Button btnLuu = new Button("Lưu lại");
        btnLuu.setOnAction(event -> {
            SessionFactory factory = HibernateUtilLuyenNghe.getSessionFactory();
            Session session = factory.openSession();
            Transaction trans = session.beginTransaction();
            LuyenNghe luyenNghe = new LuyenNghe();
            luyenNghe.setId(cauDuocChon.getId());
            
            luyenNghe.setLinkAudio(txtLinkAudio.getText());
            luyenNghe.setLinkPhoto(txtLinkPhoto.getText());
            luyenNghe.setDapAn(cbDapAnLuyenNgheDung.getSelectionModel().getSelectedItem().toString());
            session.update(luyenNghe);
            trans.commit();
            session.close();
            
        });
        p.add(new Label("Link audio: "),0,0);
        p.add(new Label("Link photo: "),0,1);
        p.add(new Label("Đáp án đúng: "),0,2);
        
        p.add(txtLinkAudio,1,0);
        p.add(txtLinkPhoto,1,1);
        p.add(cbDapAnLuyenNgheDung,1,2);       
        p.add(btnLuu, 1, 3);
        Scene scene = new Scene(p,1000,500);
        s.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Toeic.png")));
        s.setTitle("Chỉnh sửa câu hỏi nghe photo");
        s.setScene(scene);
        s.show();
            
    }
    private void showStage(String fxmlPath){
        try{
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = new Stage();
        Scene scene = new Scene(root);  
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
        TableColumn<LuyenNghe, String> id = new TableColumn<LuyenNghe, String>("Mã câu");
        TableColumn<LuyenNghe, String> linkAudio = new TableColumn<LuyenNghe, String>("Link Audio");
        TableColumn<LuyenNghe, String> linkPhoto = new TableColumn<LuyenNghe, String>("Link Photo");
        TableColumn<LuyenNghe, String> dapAnDung = new TableColumn<LuyenNghe, String>("Đáp án đúng");
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        linkAudio.setCellValueFactory(new PropertyValueFactory<>("linkAudio"));
        linkPhoto.setCellValueFactory(new PropertyValueFactory<>("linkPhoto"));
        dapAnDung.setCellValueFactory(new PropertyValueFactory<>("dapAn"));       
        
        ObservableList<LuyenNghe> list = getQuestionList();
        tbvListCauHoi.setItems(list);
        tbvListCauHoi.getColumns().addAll(id,linkAudio,linkPhoto,dapAnDung);
        
    }
    @FXML
    private void onReturn(){       
        closeCurrentStage(); 
        showStage("/View/ManHinhChinh.fxml");
    }
    @FXML
    private void onRefreshList(){
        closeCurrentStage();
        showStage("QuanLyNghePhoto.fxml");
    }
}
