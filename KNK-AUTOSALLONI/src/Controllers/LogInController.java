package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DBConnection.DBHandler;


public class LogInController implements Initializable {

    @FXML
    private TextField lblusername;

    @FXML
    private Button loginbtn;

    @FXML
    private Button signupbutton;

    @FXML
    private PasswordField lblpassword;

    @FXML
    private Label errorlabel;

    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;
    private static LogInController instance;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        handler = new DBHandler();
    }

    public LogInController()
    {
        instance = this;
    }

    public static LogInController getInstance()
    {
        return instance;
    }


    public String username()
    {
        return lblusername.getText();
    }

    @FXML
    public void loginAction(ActionEvent actionEvent) throws Exception {

        String email = lblusername.getText().trim();
        String password = lblpassword.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Type Username and Password");
            alert.showAndWait();
        }


        connection = handler.getConnection();
        String q1 = "SELECT * FROM users where emri=?";

        try {
            pst = connection.prepareStatement(q1);
            pst.setString(1, lblusername.getText());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                if (SignUpController.check(lblpassword.getText(), rs.getString("fjalkalimi"))) {
                    // admin - admin123
                    if(rs.getString("roli").equals("a")){
                        loginbtn.getScene().getWindow().hide();
                        Stage loginbtn1 = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("../FXML/AdminPage.fxml"));
                        Scene scene1 = new Scene(root);
//                        scene1.getStylesheets().add(getClass().getResource("../FXML/button.css").toExternalForm());
                        loginbtn1.setScene(scene1);
                        loginbtn1.show();
                        loginbtn1.setResizable(true);
                        return;
                    }
                    loginbtn.getScene().getWindow().hide();
                    Stage home = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("../FXML/HomePageMain.fxml"));
                    Scene scene = new Scene(root);
                    home.setScene(scene);
                    home.show();
                } else {
                    errorlabel.setTextFill(Color.RED);
                    errorlabel.setText("Enter correct Username/Password");
                }
            } else {
                errorlabel.setTextFill(Color.RED);
                errorlabel.setText("Enter correct Username/Password");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void signup(ActionEvent actionEvent) throws Exception {
        loginbtn.getScene().getWindow().hide();

        Stage signupbutton = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/SignUpMain.fxml"));
        Scene scene = new Scene(root);
        signupbutton.setScene(scene);
        signupbutton.show();
        signupbutton.setResizable(false);
    }


}
