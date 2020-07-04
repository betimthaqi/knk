package Controllers;

import DBConnection.DBHandler;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

import Controllers.HomepageController;

public class AdminController implements Initializable {


    private static DBHandler handler;
    private static Connection connection;
    private PreparedStatement pst;

    HomepageController home = new HomepageController();

    @FXML
    private MenuItem help;

    @FXML
    private Button btndelete;

    @FXML
    private Button btninsert;

    @FXML
    private TextField tfbrand;

    @FXML
    private TextField tfmodel;

    @FXML
    private TextField tfengine;

    //    @FXML
    //    private MenuButton mbtype;

    @FXML
    private TextField tfcolor;

    @FXML
    private TextField tfyear;

    @FXML
    private RadioButton rb3;

    @FXML
    private RadioButton rb5;

    @FXML
    private RadioButton rddiesel;

    @FXML
    private RadioButton rdbenzin;

    @FXML
    private RadioButton rdelectric;

    @FXML
    private RadioButton rdmanual;

    @FXML
    private RadioButton rdautomatic;

    @FXML
    private TextField tfprice;

    @FXML
    private MenuButton menubuttoni;

    @FXML
    private MenuItem exit;

    @FXML
    private MenuItem logout;

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
    private TextField lbty;

    @FXML
    private TextField tfID;

    @FXML
    private MenuItem btnlistashitjeve;


    @FXML
    private Menu albl;

    @FXML
    private Menu lgbl;
    @FXML
    private Menu slbl;

    @FXML
    private MenuItem lblang;

    @FXML
    private MenuItem lblshq;

    @FXML
    private MenuItem about;


    @FXML
    private Label blbl;


    @FXML
    private Label tlbl;

    @FXML
    private Label clbl;


    @FXML
    private Label ylbl;

    @FXML
    private Label mlbl;

    @FXML
    private Label elbl;

    @FXML
    private Label dlbl;
    @FXML
    private Label glbl;
    @FXML
    private Label flbl;
    @FXML
    private Label plbl;


    @Override

    public void initialize(URL location, ResourceBundle resources) {
        handler = new DBHandler();

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

        lblang.setMnemonicParsing(true);
        lblang.setAccelerator(new KeyCodeCombination(KeyCode.E,
                KeyCombination.CONTROL_DOWN));

        lblang.setOnAction(event1 -> {
            try {
                englishAction(event1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        lblshq.setMnemonicParsing(true);
        lblshq.setAccelerator(new KeyCodeCombination(KeyCode.A,
                KeyCombination.CONTROL_DOWN));

        lblshq.setOnAction(event1 -> {
            try {
                albanianAction(event1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        help.setMnemonicParsing(true);
        help.setAccelerator(new KeyCodeCombination(KeyCode.H,
                KeyCombination.CONTROL_DOWN));

        help.setOnAction(event1 -> {
            try {
                helpAction(event1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        about.setMnemonicParsing(true);
        about.setAccelerator(new KeyCodeCombination(KeyCode.O,
                KeyCombination.CONTROL_DOWN));

        about.setOnAction(event1 -> {
            try {
                aboutAction(event1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        btnlistashitjeve.setMnemonicParsing(true);
        btnlistashitjeve.setAccelerator(new KeyCodeCombination(KeyCode.S,
                KeyCombination.CONTROL_DOWN));

        btnlistashitjeve.setOnAction(event1 -> {
            try {
                listaShitjeveAction(event1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        try {
            showCars("SELECT * FROM veturat");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void exitmethod(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void aboutAction(ActionEvent event) throws Exception {
        home.loadFxml("../FXML/AboutPage.fxml", "About");
    }

    @FXML
    void logoutmethod(ActionEvent event) throws Exception {
        menubuttoni.getScene().getWindow().hide();
        home.loadFxml("../FXML/LogInMain.fxml", "Sign In");
    }


    @FXML
    void handleButtonAction(ActionEvent event) throws Exception {
        if (event.getSource() == btninsert) {
            insertRecords();
        } else deleteRecords();
    }


    @FXML
    void helpAction(ActionEvent event) throws IOException {
        File f = new File("src\\help\\" + help.getText() + ".txt");
        Desktop.getDesktop().open(f);
    }

    @FXML
    void listaShitjeveAction(ActionEvent event) throws Exception {
        home.loadFxml("../FXML/ListaShitjeve.fxml", "Lista e Shitjeve");
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

    public void showCars(String query) throws Exception {
        ObservableList<Cars> list = getCarsList(query);
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

    public void insertRecords() throws Exception {
        String query = String.format("INSERT INTO veturat(brendi,modeli,motori,tipi,ngjyra,vitiProdhimit,numriDyerve,karburanti,transmetuesi,cmimi) VALUES ('%s','%s','%s','%s','%s','%s',%d,'%s','%s','%s')", tfbrand.getText(), tfmodel.getText(), tfengine.getText(), lbty.getText(), tfcolor.getText(), tfyear.getText(), getDoors(), getFuel(), getGear(), tfprice.getText());
        executeQuery(query);
        showCars("SELECT * FROM veturat");
    }

    public void deleteRecords() throws Exception {
        String query = "DELETE FROM veturat WHERE vId =" + tfID.getText();
        executeQuery(query);
        showCars("SELECT * FROM veturat");
    }

    public static void executeQuery(String query) throws SQLException {
        connection = handler.getConnection();
        Statement st;
        try {
            st = connection.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getDoors() {
        int door;
        if (rb3.isSelected()) {
            door = 3;
        } else {
            door = 5;
        }

        return door;
    }

    public String getFuel() {
        String fuel = "";
        if (rdbenzin.isSelected()) {
            fuel = "Benzine";
        } else if (rdbenzin.isSelected()) {
            fuel = "Nafte";
        } else fuel = "Elektronike";

        return fuel;
    }

    public String getGear() {
        String gear = "";
        if (rdautomatic.isSelected()) {
            gear = "Automatik";
        } else gear = "Manual";

        return gear;
    }

    public void handleMouseAction(MouseEvent mouseEvent) {
        Cars selectedItem = tvCars.getSelectionModel().getSelectedItem();

        tfID.setText("" + selectedItem.getId());
        tfbrand.setText(selectedItem.getBrand());
        tfmodel.setText(selectedItem.getModeli());
        tfengine.setText(selectedItem.getEngine());
        lbty.setText(selectedItem.getType());
        tfcolor.setText(selectedItem.getColor());
        tfyear.setText(selectedItem.getYear());
        //
        if (selectedItem.getDoors() == 3) {
            rb3.setSelected(true);
            rb5.setSelected(false);
        } else {
            rb3.setSelected(false);
            rb5.setSelected(true);
        }

        if (selectedItem.getFuel().equals("Nafte")) {
            rddiesel.setSelected(true);
            rdbenzin.setSelected(false);
            rdelectric.setSelected(false);
        } else if (selectedItem.getFuel().equals("Benzine")) {
            rdbenzin.setSelected(true);
            rddiesel.setSelected(false);
            rdelectric.setSelected(false);
        } else {
            rdelectric.setSelected(true);
            rddiesel.setSelected(false);
            rdbenzin.setSelected(false);

        }

        if (selectedItem.getGear().equals("Manual")) {
            rdmanual.setSelected(true);
            rdautomatic.setSelected(false);
        } else {
            rdautomatic.setSelected(true);
            rdmanual.setSelected(false);
        }

        tfprice.setText(selectedItem.getPrice());
    }


    private ResourceBundle bundle;
    private Locale locale;

    @FXML
    void englishAction(ActionEvent event) {
        loadlang("Eng");
    }

    @FXML
    void albanianAction(ActionEvent event) {
        loadlang("Alb");
    }

    private void loadlang(String lang) {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("Bundle.Lang", locale);
        blbl.setText(bundle.getString("blbl"));
        mlbl.setText(bundle.getString("mlbl"));
        elbl.setText(bundle.getString("elbl"));
        tlbl.setText(bundle.getString("tlbl"));
        clbl.setText(bundle.getString("clbl"));
        ylbl.setText(bundle.getString("ylbl"));
        dlbl.setText(bundle.getString("dlbl"));
        flbl.setText(bundle.getString("flbl"));
        glbl.setText(bundle.getString("glbl"));
        plbl.setText(bundle.getString("plbl"));
        btndelete.setText(bundle.getString("btndelete"));
        btninsert.setText(bundle.getString("btninsert"));
        lgbl.setText(bundle.getString("lgbl"));
        lblang.setText(bundle.getString("lblang"));
        lblshq.setText(bundle.getString("lblshq"));
        slbl.setText(bundle.getString("slbl"));
        btnlistashitjeve.setText(bundle.getString("btnlistashitjeve"));
        albl.setText(bundle.getString("albl"));
        about.setText(bundle.getString("about"));
        exit.setText(bundle.getString("exit"));
        logout.setText(bundle.getString("logout"));
        help.setText(bundle.getString("help"));

    }

}
