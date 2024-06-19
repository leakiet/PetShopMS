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
    private ImageView proImg;
    @FXML
    private Label proName;
    @FXML
    private Label proPrice;
    @FXML
    private Label proId;

    public void setData(Products products) {
        try {
            String imagePath = products.getProImg();
            Image image = new Image(getClass().getResource(imagePath).toExternalForm());
            proImg.setImage(image);
        } catch (IllegalArgumentException e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
        proName.setText(products.getProName());
        proPrice.setText(String.valueOf(products.getProPrice()));
        proId.setText(String.valueOf(products.getProId()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
