package Controllers;

import DBConnection.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListaShitjeveController implements Initializable {
    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;


    @FXML
    private TableView<Sales> tvShitjet;

    @FXML
    private TableColumn<Sales,Integer> colsid;

    @FXML
    private TableColumn<Sales, String> colbrand;

    @FXML
    private TableColumn<Sales, String> colmodel;

    @FXML
    private TableColumn<Sales, String> colprice;

    @FXML
    private TableColumn<Sales, String> coldate;

    @FXML
    private TableColumn<Sales, String> coltime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handler = new DBHandler();
        try {
            showSales();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public ObservableList<Sales> getSalesList() throws SQLException {
        ObservableList<Sales> salesList = FXCollections.observableArrayList();
        connection = handler.getConnection();
        String query = "SELECT * FROM listashitjeve";

        try {
            pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            Sales sales;
            while (rs.next()) {
                sales = new Sales(rs.getInt("sID"),rs.getString("brendi"),rs.getString("modeli"),rs.getString("cmimi"),rs.getString("dataShitjes"),rs.getString("kohaShitjes"));
                salesList.add(sales);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salesList;
    }


    public void showSales() throws Exception {
        ObservableList<Sales> list = getSalesList();
        colsid.setCellValueFactory(new PropertyValueFactory<Sales, Integer>("SID"));
        colbrand.setCellValueFactory(new PropertyValueFactory<Sales, String>("Brand"));
        colmodel.setCellValueFactory(new PropertyValueFactory<Sales, String>("Model"));
        colprice.setCellValueFactory(new PropertyValueFactory<Sales, String>("Price"));
        coldate.setCellValueFactory(new PropertyValueFactory<Sales, String>("Date"));
        coltime.setCellValueFactory(new PropertyValueFactory<Sales, String>("Time"));

        tvShitjet.setItems(list);
    }
}
