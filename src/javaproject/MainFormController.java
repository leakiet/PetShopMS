package javaproject;

import Models.Products;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    @FXML
    private GridPane gridPane;
    @FXML
    private AnchorPane formMyAccount;
    @FXML
    private AnchorPane mainForm;

    List<Products> productList;

    public List<Products> data() {
        List<Products> list = new ArrayList<>();
        Products pro = new Products();
        pro.setProId(1);
        pro.setProName("Meow Cloth 1");
        pro.setProImg("D:\\Java2\\javaproject\\src\\accessories\\CatFood1.jpg");
        pro.setProPrice(200);
        list.add(pro);
        
        pro = new Products();
        pro.setProId(2);
        pro.setProName("Meow Cloth 2");
        pro.setProImg("D:\\Java2\\javaproject\\src\\accessories\\CatFood2.jpg");
        pro.setProPrice(300);
        list.add(pro);
        
        pro = new Products();
        pro.setProId(2);
        pro.setProName("Meow Cloth 2");
        pro.setProImg("D:\\Java2\\javaproject\\src\\accessories\\CatFood2.jpg");
        pro.setProPrice(300);
        list.add(pro);
        
        pro = new Products();
        pro.setProId(3);
        pro.setProName("Meow Cloth 2");
        pro.setProImg("D:\\Java2\\javaproject\\src\\accessories\\CatFood2.jpg");
        pro.setProPrice(300);
        list.add(pro);
        
        pro = new Products();
        pro.setProId(4);
        pro.setProName("Meow Cloth 2");
        pro.setProImg("D:\\Java2\\javaproject\\src\\accessories\\CatFood2.jpg");
        pro.setProPrice(300);
        list.add(pro);
        
        pro = new Products();
        pro.setProId(5);
        pro.setProName("Meow Cloth 2");
        pro.setProImg("D:\\Java2\\javaproject\\src\\accessories\\CatFood2.jpg");
        pro.setProPrice(300);
        list.add(pro);
        
        pro = new Products();
        pro.setProId(6);
        pro.setProName("Meow Cloth 2");
        pro.setProImg("D:\\Java2\\javaproject\\src\\accessories\\CatFood2.jpg");
        pro.setProPrice(300);
        list.add(pro);
        
        return list;
    }

    public void DisplayProduct() {
        productList = data();

        if (productList == null) {
            System.out.println("Product List is Null");
        }

        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < productList.size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                URL url = new File("src/javaproject/productCard.fxml").toURI().toURL();
                loader.setLocation(url);

                if (loader.getLocation() == null) {
                    System.out.println("FXML file not found");
                }

                AnchorPane pane = loader.load();
                ProductCardController controller = loader.getController();
                controller.setData(productList.get(i));

                if (column == 3) {
                    column = 0;
                    ++row;
                }

                gridPane.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(10));

            }
        } catch (IOException e) {
            System.out.println("Error Display Product: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DisplayProduct();
    }

    public void openProduct() throws MalformedURLException, IOException {
        URL url = new File("src/javaproject/productCard.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("card Product form");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void switchMenuProducts(MouseEvent event) throws IOException {
        formProducts.setVisible(true);
        formSearchProducts.setVisible(true);
        formProductsList.setVisible(true);
        formTitleGrooming.setVisible(false);
        formGrooming.setVisible(false);
        formInventory.setVisible(false);
        formCustomers.setVisible(false);
        formInvoices.setVisible(false);
        formMyAccount.setVisible(false);
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
        formMyAccount.setVisible(false);
    }

    @FXML
    private void switchMenuInventory(MouseEvent event) {
        formProducts.setVisible(false);
        formInventory.setVisible(true);
        formCustomers.setVisible(false);
        formInvoices.setVisible(false);
        formMyAccount.setVisible(false);
    }

    @FXML
    private void switchMenuCustomers(MouseEvent event) {
        formProducts.setVisible(false);
        formInventory.setVisible(false);
        formCustomers.setVisible(true);
        formInvoices.setVisible(false);
        formMyAccount.setVisible(false);
    }

    @FXML
    private void switchMenuInvoices(MouseEvent event) {
        formProducts.setVisible(false);
        formInventory.setVisible(false);
        formCustomers.setVisible(false);
        formInvoices.setVisible(true);
        formMyAccount.setVisible(false);
    }

    @FXML
    private void btnLogOut(ActionEvent event) throws MalformedURLException, IOException {
        URL url = new File("src/javaproject/LoginForm.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Pet Shop Management System");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        Stage loginStage = (Stage) mainForm.getScene().getWindow();
        loginStage.close();
    }

    @FXML
    private void switchMenuMyAccounts(MouseEvent event) {
        formProducts.setVisible(false);
        formInventory.setVisible(false);
        formCustomers.setVisible(false);
        formInvoices.setVisible(false);
        formMyAccount.setVisible(true);
    }

}
