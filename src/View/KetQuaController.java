
package View;

import Model.Cau;
import Model.ConnectionUtils;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

/**
 * FXML Controller class
 *
 * @author Danh
 */
public class KetQuaController  extends ThiNguPhapController implements Initializable{

    @FXML
    private Label lblCauHoi;
    @FXML
    private RadioButton rdA, rdB, rdC, rdD;
    @FXML
    private Button btnSau;
    @FXML
    private Button btnTruoc;
    @FXML
    private Button btnKetThuc;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    @FXML
    private void onPrevious(){
        
    }
    @FXML
    private void onNext(){
        
    }
    @FXML
    private void onEnd(){
        
    }
}
