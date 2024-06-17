package javaproject;

import Models.Products;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ProductCardController implements Initializable {

    @FXML
    private AnchorPane productCardForm;
    @FXML
    private ImageView proImg;
    @FXML
    private Label proName;
    @FXML
    private Label proPrice;
    @FXML
    private Spinner<?> proSpinner;
    @FXML
    private Button btnAddtoInvoice;
    
    private Products products;
    private Image image;
    
    public void setData(Products products){
        this.products =products;
        
        proName.setText(products.getProName());
        proPrice.setText(String.valueOf(products.getProPrice()));
        image = new Image(products.getProImg(), 300, 300, false, true);
        proImg.setImage(image);
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
