package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DBConnection.DBHandler;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class SignUpController implements Initializable {

    @FXML
    private TextField signupemri;

    @FXML
    private TextField signupimella;

    @FXML
    private Button singupbtn1;

    @FXML
    private Button loginbtn1;

    @FXML
    private PasswordField signuppw;

    @FXML
    private TextField signupmbiemri;

    @FXML
    private RadioButton signupmale;

    @FXML
    private RadioButton signupfemale;

    @FXML
    private TextField signuplokacioni;

    @FXML
    private Label emailerror;

    @FXML
    private Label pwerror;

    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;

    static final String pa = System.getProperty("user.home") + "\\" + "Desktop" + "\\";
    static final int iterations = 20*1000;
    static final int saltLen = 32;
    static final int desiredKeyLen = 256;

    public SignUpController() throws Exception {
    }


    public static String hash(String password, byte[] salt) throws Exception {
        if (password == null || password.length() == 0)
            throw new IllegalArgumentException("Empty passwords are not supported.");
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey key = f.generateSecret(new PBEKeySpec(
                password.toCharArray(), salt, iterations, desiredKeyLen));
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public static String getSaltedHash(String password) throws Exception {
        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
        return Base64.getEncoder().encodeToString(salt) + "$" + hash(password, salt);
    }


    public static boolean check(String password, String stored) throws Exception{
        String[] saltAndHash = stored.split("\\$");
        String hashOfInput = hash(password, Base64.getDecoder().decode(saltAndHash[0]));
        return hashOfInput.equals(saltAndHash[1]);
    }

    public static String check1(String password, String stored) throws Exception{
        String[] saltAndHash = stored.split("\\$");
        String hashOfInput = hash(password, Base64.getDecoder().decode(saltAndHash[0]));
        return hashOfInput;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        handler = new DBHandler();

    }

    @FXML
    public void login(ActionEvent actionEvent) throws Exception{
        shfaqLogin();
    }


    @FXML
    public void signupaction(ActionEvent actionEvent) throws Exception{
        String insert = "INSERT INTO users(roli,emri,mbiemri,lokacioni,email,fjalkalimi,gjinia)"
                + "VALUES (?,?,?,?,?,?,?)";

        connection = handler.getConnection();

        String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        boolean validimiimelles = signupimella.getText().matches(regex);

        Pattern fileExtnPtrn = Pattern.compile("^(?=\\D*\\d)(?=[^a-z]*[a-z])[\\w~@#$%^&*+=`|{}:;!.?\\\"()\\[\\]-]{6,}$");
        Matcher mtch = fileExtnPtrn.matcher(signuppw.getText());


        if(signupemri.getText().trim().isEmpty() || signupmbiemri.getText().trim().isEmpty() || signuplokacioni.getText().trim().isEmpty() || signupimella.getText().trim().isEmpty() || signuppw.getText().trim().isEmpty() || getGender().trim().isEmpty())
        {
            ktheAlert("Ju lutem mbushni gjitha te dhenat");
            return;
        }else if(!validimiimelles) {
            emailerror.setTextFill(Color.RED);
            emailerror.setText("Invalid E-mail");
            return;
        }else if (!mtch.matches()) {
            pwerror.setTextFill(Color.RED);
            pwerror.setText("Password must contain at least 6ch and 1nr");
            return;
        }else {
            shfaqLogin();
        }

        try{
            pst = connection.prepareStatement(insert);
        }catch (SQLException e){
            e.printStackTrace();
        }

        try{
            pst.setString(1,"u");
        pst.setString(2,signupemri.getText());
        pst.setString(3,signupmbiemri.getText());
        pst.setString(4,signuplokacioni.getText());
        pst.setString(5,signupimella.getText());
        pst.setString(6,getSaltedHash(signuppw.getText()));
        pst.setString(7,getGender());

        pst.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void shfaqLogin() throws java.io.IOException {
        loginbtn1.getScene().getWindow().hide();
        Stage loginbtn1 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/LogInMain.fxml"));
        Scene scene = new Scene(root);
        loginbtn1.setScene(scene);
        loginbtn1.show();
        loginbtn1.setResizable(false);
    }

    public String getGender(){
        String gen = "";
        if (signupmale.isSelected()){
            gen = "m";
        }else{
            gen = "f";
        }

        return gen;
    }

    public Alert ktheAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
        return alert;
    }


}
