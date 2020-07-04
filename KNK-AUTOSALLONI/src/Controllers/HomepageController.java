package Controllers;

import DBConnection.DBHandler;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class HomepageController implements Initializable {
	

    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;

    @FXML
    private AnchorPane HomeAnchorpane;

    @FXML
    private Label wlclbl;

    @FXML
    private MenuButton menubuttoni;

    @FXML
    private Button BMW;

    @FXML
    private Button MERCEDES;

    @FXML
    private Button AUDI;

    @FXML
    private Button VW;

    @FXML
    private AnchorPane BMWAnchorPane;

    @FXML
    private AnchorPane MERCEDESAnchorPane;

    @FXML
    private AnchorPane AudiAnchorPane;

    @FXML
    private AnchorPane VWAnchorPane;

    @FXML
    private TableView<Cars> tvCars;

    @FXML
    private TableColumn<Cars, Integer> colId;

    @FXML
    private TableColumn<Cars, String> colBrand;

    @FXML
    private TableColumn<Cars, String> colModel;

    @FXML
    private TableColumn<Cars, String> colEngine;

    @FXML
    private TableColumn<Cars, String> colType;

    @FXML
    private TableColumn<Cars, String> colColor;

    @FXML
    private TableColumn<Cars, String> colYear;

    @FXML
    private TableColumn<Cars, Integer> colDoors;

    @FXML
    private TableColumn<Cars, String> colFuel;

    @FXML
    private TableColumn<Cars, String> colGear;

    @FXML
    private TableColumn<Cars, String> colPrice;

    @FXML
    private TableView<Cars> tvCars1;

    @FXML
    private TableColumn<Cars, Integer> colId1;

    @FXML
    private TableColumn<Cars, String> colBrand1;

    @FXML
    private TableColumn<Cars, String> colModel1;

    @FXML
    private TableColumn<Cars, String> colEngine1;

    @FXML
    private TableColumn<Cars, String> colType1;

    @FXML
    private TableColumn<Cars, String> colColor1;

    @FXML
    private TableColumn<Cars, String> colYear1;

    @FXML
    private TableColumn<Cars, Integer> colDoors1;

    @FXML
    private TableColumn<Cars, String> colFuel1;

    @FXML
    private TableColumn<Cars, String> colGear1;

    @FXML
    private TableColumn<Cars, String> colPrice1;


    @FXML
    private TableView<Cars> tvCars11;

    @FXML
    private TableColumn<Cars, Integer> colId11;

    @FXML
    private TableColumn<Cars, String> colBrand11;

    @FXML
    private TableColumn<Cars, String> colModel11;

    @FXML
    private TableColumn<Cars, String> colEngine11;

    @FXML
    private TableColumn<Cars, String> colType11;

    @FXML
    private TableColumn<Cars, String> colColor11;

    @FXML
    private TableColumn<Cars, String> colYear11;

    @FXML
    private TableColumn<Cars, Integer> colDoors11;

    @FXML
    private TableColumn<Cars, String> colFuel11;

    @FXML
    private TableColumn<Cars, String> colGear11;

    @FXML
    private TableColumn<Cars, String> colPrice11;

    @FXML
    private TableView<Cars> tvCars111;

    @FXML
    private TableColumn<Cars, Integer> colId111;

    @FXML
    private TableColumn<Cars, String> colBrand111;

    @FXML
    private TableColumn<Cars, String> colModel111;

    @FXML
    private TableColumn<Cars, String> colEngine111;

    @FXML
    private TableColumn<Cars, String> colType111;

    @FXML
    private TableColumn<Cars, String> colColor111;

    @FXML
    private TableColumn<Cars, String> colYear111;

    @FXML
    private TableColumn<Cars, Integer> colDoors111;

    @FXML
    private TableColumn<Cars, String> colFuel111;

    @FXML
    private TableColumn<Cars, String> colGear111;

    @FXML
    private TableColumn<Cars, String> colPrice111;

    @FXML
    private MenuItem exit;

    @FXML
    private MenuItem logout;

    @FXML
    private Button homebtn;

    @FXML
    private Button aboutbtn;

    @FXML
    private Button conbtn;

    @FXML
    private Label wlclbl1;

    @FXML
    private Menu lgbtn;

    @FXML
    private MenuItem englbtn;

    @FXML
    private MenuItem albtn;
    
    @FXML
    private Menu help;

    @FXML
    private MenuItem helpbtn;
    
    private ResourceBundle bundle;
    private Locale locale;

    @Override
    public void initialize(URL location, ResourceBundle resources){

        exit.setMnemonicParsing(true);
        exit.setAccelerator(new KeyCodeCombination(KeyCode.X,
                KeyCombination.CONTROL_DOWN));

        exit.setOnAction(event1 -> Platform.exit());

        logout.setMnemonicParsing(true);
        logout.setAccelerator(new KeyCodeCombination(KeyCode.L,
                KeyCombination.CONTROL_DOWN));

        logout.setOnAction(event1 -> {
            try {
                logoutmethod(event1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        helpbtn.setMnemonicParsing(true);
        helpbtn.setAccelerator(new KeyCodeCombination(KeyCode.H,
                KeyCombination.CONTROL_DOWN));

        helpbtn.setOnAction(event1 -> {
            try {
                helpAction(event1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        englbtn.setMnemonicParsing(true);
        englbtn.setAccelerator(new KeyCodeCombination(KeyCode.E,
                KeyCombination.CONTROL_DOWN));

        englbtn.setOnAction(event1 -> {
            try {
                englishAction(event1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        albtn.setMnemonicParsing(true);
        albtn.setAccelerator(new KeyCodeCombination(KeyCode.A,
                KeyCombination.CONTROL_DOWN));

        albtn.setOnAction(event1 -> {
            try {
                albanianAction(event1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        selectAnchor(true,false,false,false,false);


        setUsername(LogInController.getInstance().username());
        
    }
    
    
    public void homeAction(ActionEvent actionEvent) {
        selectAnchor(true,false,false,false,false);
    }

    @FXML
    public void AboutAction(ActionEvent actionEvent) throws Exception {
        loadFxml("../FXML/AboutPage.fxml","About");
    }

   
    public void ContactUsAction(ActionEvent actionEvent) throws IOException {
    	conbtn.getScene().getWindow().hide();
    	loadFxml("../FXML/ContactUs.fxml","Contact Us");
    }


    @FXML
    void exitmethod(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void logoutmethod(ActionEvent event) throws Exception {
        menubuttoni.getScene().getWindow().hide();
        loadFxml("../FXML/LogInMain.fxml","Log in");
        }


    public void setUsername(String user) {
        this.wlclbl.setTextFill(Color.BLUE);
        this.wlclbl.setText(user);
    }

    @FXML
    void AudiAction(ActionEvent event) {
        handler = new DBHandler();
        try {
            showCars("SELECT * FROM veturat WHERE brendi='Audi'", tvCars11, colId11, colBrand11, colModel11, colEngine11, colType11, colColor11, colYear11, colDoors11, colFuel11, colGear11, colPrice11);
        } catch (Exception e) {
            e.printStackTrace();
        }
        selectAnchor(false,false,false,true,false);
    }

    private void showCars(String s, TableView<Cars> tvCars, TableColumn<Cars, Integer> colId, TableColumn<Cars, String> colBrand, TableColumn<Cars, String> colModel, TableColumn<Cars, String> colEngine, TableColumn<Cars, String> colType, TableColumn<Cars, String> colColor, TableColumn<Cars, String> colYear, TableColumn<Cars, Integer> colDoors, TableColumn<Cars, String> colFuel, TableColumn<Cars, String> colGear, TableColumn<Cars, String> colPrice) throws Exception {
        ObservableList<Cars> list = getCarsList(s);
        colId.setCellValueFactory(new PropertyValueFactory<Cars, Integer>("Id"));
        colBrand.setCellValueFactory(new PropertyValueFactory<Cars, String>("Brand"));
        colModel.setCellValueFactory(new PropertyValueFactory<Cars, String>("Modeli"));
        colEngine.setCellValueFactory(new PropertyValueFactory<Cars, String>("Engine"));
        colType.setCellValueFactory(new PropertyValueFactory<Cars, String>("Type"));
        colColor.setCellValueFactory(new PropertyValueFactory<Cars, String>("Color"));
        colYear.setCellValueFactory(new PropertyValueFactory<Cars, String>("Year"));
        colDoors.setCellValueFactory(new PropertyValueFactory<Cars, Integer>("Doors"));
        colFuel.setCellValueFactory(new PropertyValueFactory<Cars, String>("Fuel"));
        colGear.setCellValueFactory(new PropertyValueFactory<Cars, String>("Gear"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Cars, String>("Price"));

        tvCars.setItems(list);
    }


    @FXML
    void MercedesAction(ActionEvent event) {

        handler = new DBHandler();
        try {
            showCars("SELECT * FROM veturat WHERE brendi='Mercedes-Benz'", tvCars1, colId1, colBrand1, colModel1, colEngine1, colType1, colColor1, colYear1, colDoors1, colFuel1, colGear1, colPrice1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        selectAnchor(false,false,true,false,false);
        
    }
    

    @FXML
    void VWAction(ActionEvent event) {

    	handler = new DBHandler();
        try {
            showCars("SELECT * FROM veturat WHERE brendi='Volkswagen'", tvCars111, colId111, colBrand111, colModel111, colEngine111, colType111, colColor111, colYear111, colDoors111, colFuel111, colGear111, colPrice111);
        } catch (Exception e) {
            e.printStackTrace();
        }
        selectAnchor(false,false,false,false,true);
            }

    @FXML
    void bmwAction(ActionEvent event) {

        handler = new DBHandler();

        try {
            showCars("SELECT * FROM veturat WHERE brendi='BMW'", tvCars, colId, colBrand, colModel, colEngine, colType, colColor, colYear, colDoors, colFuel, colGear, colPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }

        selectAnchor(false,true,false,false,false);
    }

    public void selectAnchor(boolean Home,boolean BMW,boolean MERCEDES,boolean Audi ,boolean VW) {

    	HomeAnchorpane.setVisible(Home);
    	BMWAnchorPane.setVisible(BMW);
    	MERCEDESAnchorPane.setVisible(MERCEDES);
    	AudiAnchorPane.setVisible(Audi);
    	VWAnchorPane.setVisible(VW);
    }

    public ObservableList<Cars> getCarsList(String query) throws SQLException {
        ObservableList<Cars> carList = FXCollections.observableArrayList();
        connection = handler.getConnection();
        try {
            pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Cars cars;
            while (rs.next()) {
                cars = new Cars(rs.getInt("vId"), rs.getString("brendi"), rs.getString("modeli"), rs.getString("motori"), rs.getString("tipi"), rs.getString("ngjyra"), rs.getString("vitiProdhimit"), rs.getInt("numriDyerve"), rs.getString("karburanti"), rs.getString("transmetuesi"), rs.getString("cmimi"));
                carList.add(cars);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carList;
    }


    @FXML
    void englishAction(ActionEvent event) {
    loadlang("Eng");
    }

    @FXML
    void albanianAction(ActionEvent event) {
        loadlang("Alb");
    }

    
    @FXML
    void helpAction(ActionEvent event) throws IOException {
    	File f = new File( "src\\help\\" + helpbtn.getText() + ".txt" );
        Desktop.getDesktop().open(f);
    }
    
    
    private void loadlang(String lang){
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("Bundle.Lang",locale);
        homebtn.setText(bundle.getString("homebtn"));
        aboutbtn.setText(bundle.getString("aboutbtn"));
        conbtn.setText(bundle.getString("conbtn"));
        wlclbl1.setText(bundle.getString("wlclbl1"));
        englbtn.setText(bundle.getString("englbtn"));
        albtn.setText(bundle.getString("albtn"));
        lgbtn.setText(bundle.getString("lgbtn"));
        exit.setText(bundle.getString("exit"));
        logout.setText(bundle.getString("logout"));
        help.setText(bundle.getString("help"));
        helpbtn.setText(bundle.getString("help"));    
        
    }
    
    public void loadFxml(String path,String titulli) throws IOException {
    	Stage about = new Stage();
        about.setTitle(titulli);
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene(root);
        about.setScene(scene);
        about.show();
    }


}
