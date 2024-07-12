package javaproject;

import Database.EmployeeInfoDAO;
import Database.ProductsDAO;
import Models.InvoiceItem;
import Models.Products;
import Models.ProductsCategory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainFormController implements Initializable {

    @FXML
    private AnchorPane formMenu;
    @FXML
    private AnchorPane formProducts;
    @FXML
    private AnchorPane formSearchProducts;
    @FXML
    private AnchorPane formBill;
    @FXML
    private TableView<InvoiceItem> tvInvoice;
    @FXML
    private AnchorPane formProductsList;
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

    @FXML
    private TableColumn<InvoiceItem, String> tcNameInvoice;
    @FXML
    private TableColumn<InvoiceItem, Float> tcPriceInvoice;
    @FXML
    private TableColumn<InvoiceItem, Integer> tcQuantityInvoice;
    @FXML
    private Label ma_account;
    @FXML
    private Label ma_staffId;
    @FXML
    private VBox ma_contactForm;
    @FXML
    private Label ma_fullname;
    @FXML
    private Label ma_phone;
    @FXML
    private Label ma_email;
    @FXML
    private Label ma_address;
    @FXML
    private VBox ma_updateContactForm;
    @FXML
    private TextField ma_updateFullname;
    @FXML
    private TextField ma_updatePhone;
    @FXML
    private TextField ma_updateEmail;
    @FXML
    private TextArea ma_updateAddress;
    @FXML
    private TextField productNameTextField;
    @FXML
    private TextField producSKUTextField;
    @FXML
    private TextField productDescriptionTextField;
    @FXML
    private ComboBox<String> productCategoryComboBox;
    @FXML
    private Button addImageButton;
    @FXML
    private TextField productPriceTextField;
    @FXML
    private TextField productQuantityTextField;
    @FXML
    private Button addProductButton;
    @FXML
    private Button updateProductButton;
    @FXML
    private Button deleteProductButton;
    @FXML
    private Button clearBtn;
    @FXML
    private ComboBox<String> filterProductCategoryComboBox;
    @FXML
    private TextField searchProductTextField;
    @FXML
    private Button searchProductButton;
    @FXML
    private ImageView productImageView;
    @FXML
    private TableColumn<Products, Integer> productIdColumn;
    @FXML
    private TableColumn<Products, String> productSkuColumn;
    @FXML
    private TableColumn<Products, String> productNameColumn;
    @FXML
    private TableColumn<Products, String> productCategoryColumn;
    @FXML
    private TableColumn<Products, ImageView> productImageColumn;
    @FXML
    private TableColumn<Products, String> productDescriptionColumn;
    @FXML
    private TableColumn<Products, Integer> productQuantityColumn;
    @FXML
    private TableColumn<Products, Float> productPriceColumn;
    @FXML
    private TableColumn<Products, String> productDateColumn;
    @FXML
    private TableView<Products> productTableView;

    EmployeeInfoDAO empDAO = new EmployeeInfoDAO();
    ProductsDAO dao = new ProductsDAO();
    ObservableList<InvoiceItem> invoiceItemList = FXCollections.observableArrayList();

    ObservableList<Products> productsList = FXCollections.observableArrayList(dao.ListProductDB());
    ObservableList<ProductsCategory> productCategory = FXCollections.observableArrayList(dao.ListCategoryDB());

    ObservableList<String> category = FXCollections.observableArrayList(
            productCategory.stream().map(ProductsCategory::getCateName).toArray(String[]::new));

    ObservableList<String> seachCategories = FXCollections.observableArrayList(
            Stream.concat(
                    productCategory.stream().map(ProductsCategory::getCateName),
                    Stream.of("All")).toArray(String[]::new));

    FilteredList<Products> filteredProducts = new FilteredList<>(productsList, p -> true);

    Products proSelected;
    int indexSelected;
    String selectedCategory;
    String selectedNameCategory;
    String targetDir = "src/images/";
    String seachCategorySelected = "All";
    String imagePath;

    @FXML
    private Label ma_joinDate;
    @FXML
    private Label ma_gender;
    @FXML
    private TextField ma_updateDOB;
    @FXML
    private TextField ma_updateGender;
    @FXML
    private Label ma_DOB;
    @FXML
    private ImageView ma_picture;
    @FXML
    private Button ma_btnchangePW;
    @FXML
    private VBox ma_ChangePwForm;
    @FXML
    private PasswordField ma_tfCurrentPW;
    @FXML
    private PasswordField ma_tfNewPW;
    @FXML
    private PasswordField ma_tfConfirmPW;
    @FXML
    private ComboBox<String> pl_category;
    @FXML
    private TextField pl_tfSearch;

    public void SetUserData() {
        menuFullName.setText(EmployeeData.fullname);
        ma_fullname.setText(EmployeeData.fullname);
        ma_account.setText(EmployeeData.username);
        ma_staffId.setText(String.valueOf(EmployeeData.id));
        ma_joinDate.setText(EmployeeData.joinDate);
        ma_DOB.setText(EmployeeData.DOB);
        ma_gender.setText(EmployeeData.gender);
        ma_phone.setText(EmployeeData.phone);
        ma_address.setText(EmployeeData.address);
        ma_email.setText(EmployeeData.email);
        String path = "file:/D:/Java2/javaproject/" + EmployeeData.picture;
        var image = new Image(path);
        menuPicture.setImage(image);
        ma_picture.setImage(image);
    }

    public void DisplayProductList() {
        ObservableList<Products> pList = filteredProducts;

        int column = 0;
        int row = 1;

        try {
            gridPane.getChildren().clear();
            for (int i = 0; i < pList.size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                URL url = new File("src/javaproject/productCard.fxml").toURI().toURL();
                loader.setLocation(url);

                if (loader.getLocation() == null) {
                    System.out.println("FXML file not found");
                }

                AnchorPane pane = loader.load();
                ProductCardController controller = loader.getController();
                controller.setData(pList.get(i));
                controller.setMainController(this);

                if (column == 4) {
                    column = 0;
                    ++row;
                }

                gridPane.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(15));

            }
        } catch (IOException e) {
            System.out.println("Error Display Product: " + e.getMessage());
        }
    }

    public void addInvoiceItem(InvoiceItem item) {
        for (int index = 0; index < invoiceItemList.size(); index++) {
            InvoiceItem ii = invoiceItemList.get(index);
            if (ii.getName().equals(item.getName())) {
                ii.setQuantity(ii.getQuantity() + item.getQuantity());
                invoiceItemList.set(index, ii);
                return;
            }
        }
        invoiceItemList.add(item);
    }

    private void InvoiceTable() {
        tcNameInvoice.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tcPriceInvoice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        tcQuantityInvoice.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        tvInvoice.setItems(invoiceItemList);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productsList.addListener((ListChangeListener<Products>) change -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved() || change.wasUpdated()) {
                    DisplayProductList();
                }
            }
        });
        pl_category.valueProperty().addListener((obs, oldValue, newValue) -> handleCategoryFilter());

        pl_tfSearch.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                pl_handleSearch();
            }
        });

        DisplayProductList();
        SetUserData();
        InvoiceTable();
        handleOnDragImage();
        DisplayProducts();
        ComboBoxCategory();
    }

    public void ResetField() {
        productNameTextField.setText("");
        producSKUTextField.setText("");
        productDescriptionTextField.setText("");
        productPriceTextField.setText("");
        productQuantityTextField.setText("");
        productImageView.setImage(null);
        // productImageView.setImage(new
        // Image(getClass().getResourceAsStream("/Images/imagedefault1.jpg")));
        addImageButton.setText("Choose Image");
        productCategoryComboBox.setValue(null);
        ;
    }

    public void ComboBoxCategory() {
        pl_category.setItems(seachCategories);
        pl_category.valueProperty().addListener((observable, oldValue, newValue) -> {
            seachCategorySelected = newValue;
        });

        productCategoryComboBox.setItems(category);
        productCategoryComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            try {
                selectedCategory = String.valueOf(category.indexOf(newValue) + 1);
//                System.out.println(selectedCategory);
            } catch (Exception e) {
                selectedCategory = "0";
            }
            selectedNameCategory = newValue;
            // System.out.println(selectedCategory);
        });

        filterProductCategoryComboBox.setItems(seachCategories);
        filterProductCategoryComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            seachCategorySelected = newValue;
        });
    }

    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    public void DisplayProducts() {
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("proId"));
        productSkuColumn.setCellValueFactory(new PropertyValueFactory<>("proSKU"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("proName"));
        productCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("proCategory"));

        productImageColumn.setCellValueFactory(cellData -> {
            ImageView imageView = new ImageView();
            String imgPath = cellData.getValue().getProImage();
            File file = new File(imgPath);
            if (file.exists()) {
                imageView.setImage(new Image(file.toURI().toString()));
            }
            imageView.setFitWidth(50); // Cài đặt chiều rộng của hình ảnh
            imageView.setFitHeight(50); // Cài đặt chiều cao của hình ảnh
            return new SimpleObjectProperty<>(imageView);
        });

        productDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("proDescription"));
        productQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("proQuantity"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("proPrice"));
        productDateColumn.setCellValueFactory(new PropertyValueFactory<>("proDate"));
        productTableView.setItems(filteredProducts);
    }

    private void handleOnDragImage() {
        addImageButton.setOnDragOver(event -> {
            if (event.getGestureSource() != addImageButton && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });
        addImageButton.setOnDragDropped(event -> {
            javafx.scene.input.Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                File file = db.getFiles().get(0);
                String fileName = file.getName().toLowerCase();
                if (!fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg") && !fileName.endsWith(".png")
                        && !fileName.endsWith(".webp") && !fileName.endsWith(".webp") && !fileName.endsWith(".jfif")) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Invalid Image");
                    alert.setContentText("Please select a valid image file (jpg, jpeg, png, webp, jfif).");
                    alert.showAndWait();
                    return;
                }
                Image image = new Image(file.toURI().toString());
                imagePath = file.getAbsolutePath();
                productImageView.setImage(image);
                success = true;
                addImageButton.setText(file.getName());
            }
            event.setDropCompleted(success);
            event.consume();
        });

    }

    @FXML
    private void switchMenuProducts(MouseEvent event) throws IOException {
        formProducts.setVisible(true);
        formProductsList.setVisible(true);
        formGrooming.setVisible(false);
        formInventory.setVisible(false);
        formCustomers.setVisible(false);
        formInvoices.setVisible(false);
        formMyAccount.setVisible(false);
    }

    @FXML
    private void switchMenuGrooming(MouseEvent event) {
        formProducts.setVisible(true);
        formProductsList.setVisible(false);
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

    @FXML
    private void handleSwitchUpdate(ActionEvent event) {
        ma_updateContactForm.setVisible(true);
        ma_contactForm.setVisible(false);
        ma_updateFullname.setText(ma_fullname.getText());
        ma_updateDOB.setText(ma_DOB.getText());
        ma_updateGender.setText(ma_gender.getText());
        ma_updatePhone.setText(ma_phone.getText());
        ma_updateEmail.setText(ma_email.getText());
        ma_updateAddress.setText(ma_address.getText());
    }

    @FXML
    private void handleBackToInfomation(ActionEvent event) {
        ma_updateContactForm.setVisible(false);
        ma_contactForm.setVisible(true);
    }

    @FXML
    private void ma_openChangePW(ActionEvent event) {
        ma_ChangePwForm.setVisible(true);
    }

    @FXML
    private void ma_closeChangePW(ActionEvent event) {
        ma_ChangePwForm.setVisible(false);
    }

    @FXML
    private void ma_handleUpdatePW(ActionEvent event) throws IOException {
        Alert alert;
        if (ma_account.getText().isEmpty() || ma_tfCurrentPW.getText().isEmpty() || ma_tfNewPW.getText().isEmpty() || ma_tfConfirmPW.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all the fields");
            alert.showAndWait();
        } else {
            String username = ma_account.getText();
            String password = ma_tfCurrentPW.getText();
            String newPassword = ma_tfNewPW.getText();
            String confirmPassword = ma_tfConfirmPW.getText();
            boolean error = false;

            if (!newPassword.equals(confirmPassword)) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Password and Confirm Password do not match");
                alert.showAndWait();
                error = true;
            }

            if (!error) {
                boolean changePW = empDAO.changePassword(username, password, newPassword);
                if (changePW) {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Success Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Password reset successfully. Please Login again !!");
                    alert.showAndWait();

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
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to change password. Please check your inputs.");
                    alert.showAndWait();
                }
            }
        }
    }

    @FXML
    private void ma_updatePicture(MouseEvent event) {
    }

    @FXML
    private void handleUpdateContact(ActionEvent event) {
        Alert alert;
        if (ma_account.getText().isEmpty() || ma_updateFullname.getText().isEmpty() || ma_updateDOB.getText().isEmpty() || ma_updateGender.getText().isEmpty() || ma_updatePhone.getText().isEmpty() || ma_updateEmail.getText().isEmpty() || ma_updateAddress.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all the fields");
            alert.showAndWait();
        } else {
            String username = ma_account.getText();
            String fullname = ma_updateFullname.getText();
            String dob = ma_updateDOB.getText();
            String gender = ma_updateGender.getText();
            String phone = ma_updatePhone.getText();
            String email = ma_updateEmail.getText();
            String address = ma_updateAddress.getText();

            boolean error = false;

            if (!phone.matches("0[0-9]{9}")) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Phone number must be 10 digits long and start with 0.");
                alert.showAndWait();
                error = true;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid email format.");
                alert.showAndWait();
                error = true;
            }

            boolean updateSuccess = empDAO.updateEmployeeInfo(username, fullname, dob, gender, phone, email, address);
            if (updateSuccess) {
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Information updated successfully.");
                alert.showAndWait();

                ma_fullname.setText(fullname);
                ma_DOB.setText(dob);
                ma_gender.setText(gender);
                ma_phone.setText(phone);
                ma_address.setText(address);
                ma_email.setText(email);

                ma_updateContactForm.setVisible(false);
                ma_contactForm.setVisible(true);

            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Failed to update information. Please check your inputs.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void handleAddImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpg", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JFIF", "*.jfif"));
        new FileChooser.ExtensionFilter("WEBP", "*.webp", "*.webp");

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            String fileName = selectedFile.getName().toLowerCase();
            if (!fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg") && !fileName.endsWith(".png")
                    && !fileName.endsWith(".webp") && !fileName.endsWith(".webp") && !fileName.endsWith(".jfif")) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Invalid Image");
                alert.setContentText("Please select a valid image file (jpg, jpeg, png, webp, jfif).");
                alert.showAndWait();
                return;
            }
            imagePath = selectedFile.getAbsolutePath();
            addImageButton.setText(selectedFile.getName());
            productImageView.setImage(new Image(imagePath));
        }
    }

    @FXML
    private void handleDropImage(DragEvent event) {
    }

    @FXML
    private void handleAddButton(ActionEvent event) {
        try {
            String productName = productNameTextField.getText();
            if (productName.isEmpty()) {
                throw new IllegalArgumentException("Product name cannot be empty!");
            }
            String pCategory = selectedCategory;
            int proCate = Integer.parseInt(selectedCategory);
            String productNameCate = selectedNameCategory;
            System.out.println(pCategory);
            if (pCategory == null) {
                throw new IllegalArgumentException("Please select a category!");
            }
            String productDescription = productDescriptionTextField.getText();
            if (productDescription.isEmpty()) {
                throw new IllegalArgumentException("Product description cannot be empty!");
            }

            Path sourcePath = Paths.get(imagePath);
            String fileName = sourcePath.getFileName().toString();
            if (fileName == null) {
                throw new IllegalArgumentException("Please select an image!");
            }

            String fileNameNoExtension = fileName.substring(0, fileName.lastIndexOf('.'));
            String randomStringImage = generateRandomString(5);
            String proImg = targetDir + fileNameNoExtension + randomStringImage + "."
                    + fileName.substring(fileName.lastIndexOf('.') + 1);
            System.out.println(proImg);
            // String proImg = targetDir + fileName;

            float productPrice = Float.parseFloat(productPriceTextField.getText());
            if (productPrice <= 0) {
                throw new IllegalArgumentException("Product price must be greater than zero!");
            }
            LocalDate productDate = LocalDate.now();
            String date = String.valueOf(productDate);

            String productSku = producSKUTextField.getText();

            if (productSku.isEmpty()) {
                throw new IllegalArgumentException("Product SKU cannot be empty!");
            }
            boolean found = false;
            for (Products p : productsList) {
                if (p.getProSKU().equals(productSku)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                throw new IllegalArgumentException("Product SKU already exists!");
            }

            int proQuantity = Integer.parseInt(productQuantityTextField.getText());
            if (proQuantity <= 0) {
                throw new IllegalArgumentException("Product quantity must be greater than zero!");
            }
            Alert alert = new Alert(AlertType.CONFIRMATION,
                    "Are you sure you want to add this product " + "'" + productName + "'" + " ?", ButtonType.YES,
                    ButtonType.CANCEL);
            alert.showAndWait();
            alert.setTitle("Confirm Add Product");

            if (alert.getResult() == ButtonType.YES) {
                Products pro = new Products(productName, productSku, productNameCate, proCate, proImg,
                        productDescription, proQuantity, productPrice, date);
                dao.AddProductDB(pro, imagePath, randomStringImage);

                productsList.add(pro);
                ResetField();
            }

        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid input");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleUpdateButton(ActionEvent event) {
        if (proSelected == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No product selected");
            alert.setContentText("Please select a product to update.");
            alert.showAndWait();
            return;
        }
        try {
            String productName = productNameTextField.getText();
            if (productName.isEmpty()) {
                throw new IllegalArgumentException("Product name cannot be empty!");
            }

            String proSKU = producSKUTextField.getText();
            if (proSKU.isEmpty()) {
                throw new IllegalArgumentException("Product SKU cannot be empty!");
            }
            boolean found = false;
            for (Products p : productsList) {
                if (p.getProSKU().equals(proSKU) && !p.getProSKU().equals(proSelected.getProSKU())) {
                    found = true;
                    break;
                }
            }
            if (found) {
                throw new IllegalArgumentException("Product SKU already exists!");
            }

            int proCate = Integer.parseInt(selectedCategory);

            String proDescription = productDescriptionTextField.getText();
            if (proDescription.isEmpty()) {
                throw new IllegalArgumentException("Product description cannot be empty!");
            }
            int productQuantity = Integer.parseInt(productQuantityTextField.getText());

            float productPrice = Float.parseFloat(productPriceTextField.getText());

            Path sourcePath = Paths.get(imagePath);
            String fileName = sourcePath.getFileName().toString();
            if (fileName == null) {
                throw new IllegalArgumentException("Please select an image!");
            }

            // String proImg = targetDir + fileName;
            LocalDate productDate = LocalDate.now();
            String date = String.valueOf(productDate);

            if (imagePath == proSelected.getProImage()) {
                String proImg = targetDir + fileName;
                Alert alert = new Alert(AlertType.CONFIRMATION,
                        "Are you sure you want to update this product " + "'" + productName + "'" + " ?",
                        ButtonType.YES,
                        ButtonType.CANCEL);
                alert.showAndWait();
                alert.setTitle("Confirm Update Product");

                if (alert.getResult() == ButtonType.YES) {
                    Products proEdit = new Products(proSelected.getProId(),
                            productName.equals(proSelected.getProName()) ? proSelected.getProName() : productName,
                            proSKU.equals(proSelected.getProSKU()) ? proSelected.getProSKU() : proSKU,
                            selectedNameCategory.equals(proSelected.getProCategory()) ? proSelected.getProCategory()
                            : selectedNameCategory,
                            proCate == proSelected.getProCateId() ? proSelected.getProCateId() : proCate,
                            proImg.equals(proSelected.getProImage()) ? proSelected.getProImage() : proImg,
                            proDescription.equals(proSelected.getProDescription()) ? proSelected.getProDescription()
                            : proDescription,
                            productQuantity == proSelected.getProQuantity() ? proSelected.getProQuantity()
                            : productQuantity,
                            productPrice == proSelected.getProPrice() ? proSelected.getProPrice() : productPrice,
                            date.equals(proSelected.getProDate()) ? proSelected.getProDate() : date);
                    dao.UpdateProductDB(proEdit, imagePath, null, proSelected.getProImage());
                    productsList.set(indexSelected, proEdit);
                    ResetField();
                }
            } else {
                String fileNameNoExtension = fileName.substring(0, fileName.lastIndexOf('.'));
                String randomStringImage = generateRandomString(5);
                String proImg = targetDir + fileNameNoExtension + randomStringImage + "."
                        + fileName.substring(fileName.lastIndexOf('.') + 1);

                Alert alert = new Alert(AlertType.CONFIRMATION,
                        "Are you sure you want to update this product " + "'" + productName + "'" + " ?",
                        ButtonType.YES,
                        ButtonType.CANCEL);
                alert.showAndWait();
                alert.setTitle("Confirm Update Product");

                if (alert.getResult() == ButtonType.YES) {
                    Products proEdit = new Products(proSelected.getProId(),
                            productName.equals(proSelected.getProName()) ? proSelected.getProName() : productName,
                            proSKU.equals(proSelected.getProSKU()) ? proSelected.getProSKU() : proSKU,
                            selectedNameCategory.equals(proSelected.getProCategory()) ? proSelected.getProCategory()
                            : selectedNameCategory,
                            proCate == proSelected.getProCateId() ? proSelected.getProCateId() : proCate,
                            proImg.equals(proSelected.getProImage()) ? proSelected.getProImage() : proImg,
                            proDescription.equals(proSelected.getProDescription()) ? proSelected.getProDescription()
                            : proDescription,
                            productQuantity == proSelected.getProQuantity() ? proSelected.getProQuantity()
                            : productQuantity,
                            productPrice == proSelected.getProPrice() ? proSelected.getProPrice() : productPrice,
                            date.equals(proSelected.getProDate()) ? proSelected.getProDate() : date);
                    dao.UpdateProductDB(proEdit, imagePath, randomStringImage, proSelected.getProImage());
                    productsList.set(indexSelected, proEdit);
                    ResetField();
                }
            }

        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid input");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeleteButton(ActionEvent event) {
        if (proSelected == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No product selected");
            alert.setContentText("Please select a product to delete.");
            alert.showAndWait();
            return;
        }
        String productName = proSelected.getProName();
        Alert alert = new Alert(AlertType.CONFIRMATION,
                "Are you sure you want to delete this product " + "'" + productName + "'" + " ?", ButtonType.YES,
                ButtonType.CANCEL);
        alert.showAndWait();
        alert.setTitle("Confirm Delete Product");

        if (alert.getResult() == ButtonType.YES) {
            int id = proSelected.getProId();
            dao.DeleteDB(id);
            productsList.remove(indexSelected);
        }
    }

    @FXML
    private void handleClearField(MouseEvent event) {
        ResetField();
    }

    @FXML
    private void handleSeachButton(ActionEvent event) {
        String searchValue = searchProductTextField.getText().toLowerCase();
        String selectedCategoryValue = filterProductCategoryComboBox.getValue() != null
                ? filterProductCategoryComboBox.getValue().toLowerCase()
                : null;

        // Reset filter value to synchronize with updated product list
        filteredProducts.setPredicate(p -> {
            if (selectedCategoryValue != null && selectedCategoryValue.equals("all")) {
                return p.getProName().toLowerCase().contains(searchValue);
            } else {
                return (selectedCategoryValue == null
                        || selectedCategoryValue.equals(p.getProCategory().toLowerCase()))
                        && (searchValue.isEmpty() || p.getProName().toLowerCase().contains(searchValue));
            }
        });
    }

    @FXML
    private void handleTableInventory(MouseEvent event) {
        try {
            proSelected = productTableView.getSelectionModel().getSelectedItem();
            indexSelected = productTableView.getSelectionModel().getSelectedIndex();
            productNameTextField.setText(proSelected.getProName());
            producSKUTextField.setText(proSelected.getProSKU());
            productDescriptionTextField.setText(proSelected.getProDescription());
            productCategoryComboBox.setValue(String.valueOf(proSelected.getProCategory()));
            // System.out.println(selectedCategory);
            imagePath = proSelected.getProImage();
            productQuantityTextField.setText(String.valueOf(proSelected.getProQuantity()));
            productPriceTextField.setText(String.valueOf(proSelected.getProPrice()));
            File file = new File(imagePath);
            if (file.exists()) {
                Image image = new Image(file.toURI().toString());
                productImageView.setImage(image);
                addImageButton.setText(file.getName());
            } else {
                System.out.println("File does not exist: " + imagePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Throwable cause = e.getCause();
            if (cause instanceof InvocationTargetException) {
                cause = cause.getCause();
            }
            System.out.println("Exception loading image: " + cause.getMessage());
        }
    }

    // Start Search Product List //
    public void setProductFilter(String name, String category) {
        filteredProducts.setPredicate(p -> {
            if (category != null && category.equals("all")) {
                return p.getProName().toLowerCase().contains(name);
            } else {
                return (category == null
                        || category.equals(p.getProCategory().toLowerCase()))
                        && (name.isEmpty() || p.getProName().toLowerCase().contains(name));
            }
        });
    }

    private void handleCategoryFilter() {
        String cate = pl_category.getValue() != null
                ? pl_category.getValue().toLowerCase()
                : null;
        setProductFilter("", cate);
        pl_tfSearch.setText("");
        DisplayProductList();
    }

    @FXML
    private void pl_handleSearch() {
        String name = pl_tfSearch.getText().toLowerCase();
        String cate = pl_category.getValue() != null
                ? pl_category.getValue().toLowerCase()
                : null;
        setProductFilter(name, cate);
        DisplayProductList();
    }
    
    //End Search Product List //
}
