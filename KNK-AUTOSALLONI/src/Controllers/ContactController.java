package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import DBConnection.DBHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ContactController implements Initializable {
	
	
	private Connection connection;
	private DBHandler handler;
	
	
    @FXML
    private TextArea txtmesazhi;

    @FXML
    private TextField txtemri;

    @FXML
    private TextField txtmbiemri;

    @FXML
    private TextField txtemail;

    @FXML
    private Button kthehu;

    @FXML
    private Button dergo;

    @FXML
    void dergoaction(ActionEvent event) throws SQLException {
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		boolean validimiimelles = txtemail.getText().matches(regex);
		
		if(txtemri.getText().trim().isEmpty() || txtmbiemri.getText().trim().isEmpty() || txtemail.getText().trim().isEmpty() || txtmesazhi.getText().trim().isEmpty())
		{
			SignUpController.ktheAlert("Ju lutem mbushni gjitha te dhenat");
			return;
		}else if(!validimiimelles) {
			txtemail.setStyle("-fx-border-color:red;");
			txtemail.setText("Invalid E-mail");
			return;
		}
		
    	String query = String.format("INSERT INTO kontakti(emri,mbiemri,email,mesazhi) VALUES ('%s','%s','%s','%s')",
    			txtemri.getText(), txtmbiemri.getText(), txtemail.getText(), txtmesazhi.getText());
    	connection = handler.getConnection();
        Statement st;
        try {
        st = connection.createStatement();
        st.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
        }    
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText("Kerkesa eshte derguar me sukses");
		alert.showAndWait();

    }

   	@FXML
    void kthehuaction(ActionEvent event) throws IOException {
    	kthehu.getScene().getWindow().hide();
        Stage home = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/HomePageMain.fxml"));
        Scene scene = new Scene(root);
        home.setScene(scene);
        home.show();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
         handler = new DBHandler();		
	}

	
}
