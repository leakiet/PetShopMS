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
    private Label proName;
    @FXML
    private Label proPrice;
    @FXML
    private Label proId;
    @FXML
    private ImageView proImage;
    private Image image;

    public void setData(Products products) {          
                String path = "file:/" + products.getProImg().replace("\\", "/");
                image = new Image(path, 200, 150, false, true);
                proImage.setImage(image);
                proName.setText(products.getProName());
                proPrice.setText(String.valueOf(products.getProPrice()));
                proId.setText(String.valueOf(products.getProId()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
