package javaproject;

import Models.InvoiceItem;
import Models.Products;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ProductCardController implements Initializable {

    @FXML
    private Label proName;
    @FXML
    private Label proPrice;
    @FXML
    private Label proId;
    @FXML
    private ImageView proImage;

    private Image image;
    private SpinnerValueFactory<Integer> spin;

    @FXML
    private Spinner<Integer> pcSpinner;
    @FXML
    private Button btnAddInvoice;

    private MainFormController mainController;

    public void setData(Products products) {
        String path = "file:/D:/Java2/javaproject/" + products.getProImage().replace("\\", "/");
        image = new Image(path, 250, 250, false, true);
        proImage.setImage(image);
        proName.setText(products.getProName());
        proPrice.setText(String.valueOf(products.getProPrice()));
        proId.setText(String.valueOf(products.getProId()));
    }

    public void setQuantity() {
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        pcSpinner.setValueFactory(spin);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setQuantity();
        btnAddInvoice.setOnAction(e -> handleAddToInvoice());
    }

    @FXML
    private void handleAddToInvoice() {
        String name = proName.getText();
        float price = Float.parseFloat(proPrice.getText());
        int quantity = pcSpinner.getValue();

        if (quantity > 0 && mainController != null) {
            InvoiceItem item = new InvoiceItem(name, price, quantity);
            mainController.addInvoiceItem(item);
        }
    }
    
    public void setMainController(MainFormController mainController) {
        this.mainController = mainController;
    }
}
