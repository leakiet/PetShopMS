package javaproject;

import Models.Products;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class MainFormController implements Initializable {

    @FXML
    private AnchorPane formMenu;
    @FXML
    private AnchorPane formProducts;
    @FXML
    private AnchorPane formSearchProducts;
    @FXML
    private TextField inputSearch;
    @FXML
    private AnchorPane formBill;
    @FXML
    private TableView<?> tvInvoice;
    @FXML
    private AnchorPane formProductsList;
    @FXML
    private AnchorPane formTitleGrooming;
    @FXML
    private AnchorPane formGrooming;
    @FXML
    private TableView<?> tvGroomingSchedule;
    @FXML
    private AnchorPane formAddGrooming;
    @FXML
    private ImageView menuPicture;
    @FXML
    private Label menuFullName;
    @FXML
    private AnchorPane formInventory;
    @FXML
    private AnchorPane formCustomers;
    @FXML
    private AnchorPane formInvoices;
    
    private Image image;
    
    private ObservableList<Products> productCardList;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switchMenuProducts(MouseEvent event) {
        formProducts.setVisible(true);
        formSearchProducts.setVisible(true);
        formProductsList.setVisible(true);
        formTitleGrooming.setVisible(false);
        formGrooming.setVisible(false);
        formInventory.setVisible(false);
        formCustomers.setVisible(false);
        formInvoices.setVisible(false);
    }

    @FXML
    private void switchMenuGrooming(MouseEvent event) {
        formProducts.setVisible(true);
        formSearchProducts.setVisible(false);
        formProductsList.setVisible(false);
        formTitleGrooming.setVisible(true);
        formGrooming.setVisible(true);
        formInventory.setVisible(false);
        formCustomers.setVisible(false);
        formInvoices.setVisible(false);
  
    }

    @FXML
    private void switchMenuInventory(MouseEvent event) {
        formProducts.setVisible(false);
        formInventory.setVisible(true);
        formCustomers.setVisible(false);
        formInvoices.setVisible(false);
    }

    @FXML
    private void switchMenuCustomers(MouseEvent event) {
        formProducts.setVisible(false);
        formInventory.setVisible(false);
        formCustomers.setVisible(true);
        formInvoices.setVisible(false);
    }

    @FXML
    private void switchMenuInvoices(MouseEvent event) {
        formProducts.setVisible(false);
        formInventory.setVisible(false);
        formCustomers.setVisible(false);
        formInvoices.setVisible(true);
    }

    @FXML
    private void btnLogOut(ActionEvent event) {
    }

    @FXML
    private void switchMenuMyAccounts(ContextMenuEvent event) {
    }
    
    public ObservableList<Products> menuGetData(){
        
        
        return productCardList;
    }
    
}
