package javaproject;

import Database.ConnectDB;
import Database.CustomersDAO;
import Database.EmployeeInfoDAO;
import Database.ProductsDAO;
import Database.ReceiptDAO;
import Models.Customers;
import Models.InvoiceItem;
import Models.Products;
import Models.ProductsCategory;
import Models.Receipt;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.application.Platform;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
//import javafx.scene.control.Cell;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperCompileManager;


public class MainFormController implements Initializable {

    @FXML
    private AnchorPane formMenu, formProductsList, formGrooming, formProducts, formSearchProducts, formBill;
    @FXML
    private TableView<InvoiceItem> tvInvoice;
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
    CustomersDAO cusDAO = new CustomersDAO();

    ObservableList<InvoiceItem> invoiceItemList = FXCollections.observableArrayList();

    ObservableList<Products> productsList = FXCollections.observableArrayList(dao.ListProductDB());
    FilteredList<Products> filteredProducts = new FilteredList<>(productsList, p -> true);
    ObservableList<ProductsCategory> productCategory = FXCollections.observableArrayList(dao.ListCategoryDB());

    ObservableList<String> category = FXCollections.observableArrayList();
    ObservableList<String> searchCategories = FXCollections.observableArrayList();

    ObservableList<Customers> customersList = FXCollections.observableArrayList(cusDAO.ListCustomerDB());
    FilteredList<Customers> filteredCustomers = new FilteredList<>(customersList, p -> true);

    ObservableList<String> genderCustomer = FXCollections.observableArrayList("Male", "Female", "Other");
    private String genderCustomerSelected;

    Customers cusSelected;
    private int indexSelectedCustomer;
    Products proSelected;
    private int indexSelected;
    private String selectedCategory;
    private String selectedNameCategory;
    private String targetDir = "src/images/";
    private String empPicDir = "src/accessories/";
    private String seachCategorySelected = "All";
    private String imagePath;
    private boolean isUpdateCombobox = true;
    private float promotionDiscount = 0;
    private final String[] genders = {"Male", "Female", "Other"};

    @FXML
    private Label ma_joinDate;
    @FXML
    private Label ma_gender;
    @FXML
    private TextField ma_updateDOB;
    @FXML
    private ComboBox<String> ma_updateGender;
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
    @FXML
    private TableView<Customers> CustomerTableView;
    @FXML
    private TableColumn<Customers, Integer> customerIdColumn;
    @FXML
    private TableColumn<Customers, String> customerNameColumn;
    @FXML
    private TableColumn<Customers, String> customerPhoneColumn;
    @FXML
    private TableColumn<Customers, String> customerDobColumn;
    @FXML
    private TableColumn<Customers, String> customerAddressColumn;
    @FXML
    private TableColumn<Customers, String> customerGenderColumn;
    @FXML
    private TableColumn<Customers, String> customerTotalpaidColumn;
    @FXML
    private TableColumn<Customers, String> customerPointColumn;
    @FXML
    private TextField txtCustomerName;
    @FXML
    private TextField txtCustomerPhone;
    @FXML
    private DatePicker dateCustomerDob;
    @FXML
    private TextField txtCustomerAddress;
    @FXML
    private ComboBox<String> cbCustomerGender;
    @FXML
    private Button customerAddBtn;
    @FXML
    private Button customerUpdateBtn;
    @FXML
    private Button customerDeleteBtn;
    @FXML
    private Button customerClearBtn;
    @FXML
    private TextField txtSearchCusPhone;
    @FXML
    private Button searchCustomer;
    @FXML
    private TextField txtSearchCusName;
    @FXML
    private TextField invoice_Total;
    @FXML
    private TextField invoice_proCode;
    @FXML
    private TextField invoice_graTotal;
    @FXML
    private TextField invoice_Amount;
    @FXML
    private TextField invoice_Change;

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
                updateInvoiceTotal();
                return;
            }
        }
        invoiceItemList.add(item);
        updateInvoiceTotal();
    }

    public void updateInvoiceTotal() {
        float total = 0;
        ReceiptDAO receiptDAO = new ReceiptDAO(); // Tạo đối tượng ReceiptDAO một lần

        for (InvoiceItem item : invoiceItemList) {
            float subTotal = item.getPrice() * item.getQuantity();
            item.setSubTotal(subTotal);
            total += subTotal;
        }
        float grandTotal = total * (1 - promotionDiscount);
        for (InvoiceItem item : invoiceItemList) {
            String rName = item.getName();
            float rPrice = item.getPrice();
            int rQuantity = item.getQuantity();

            Receipt receipt = new Receipt(grandTotal, rName, rPrice, rQuantity);

            boolean isInserted = receiptDAO.addReceipt(receipt);

            if (isInserted) {
                System.out.println("graTotal đã được lưu vào cơ sở dữ liệu thành công.");
            } else {
                System.out.println("Không thể lưu graTotal vào cơ sở dữ liệu.");
            }
        }

        invoice_Total.setText(String.valueOf(total));
        invoice_graTotal.setText(String.valueOf(grandTotal));

        try {
            float amountPaid = Float.parseFloat(invoice_Amount.getText());
            float change = amountPaid - grandTotal;
            invoice_Change.setText(String.format("$%.2f", change));
        } catch (NumberFormatException e) {
            invoice_Change.setText("$0.00");
        }
    }

    private void applyPromotionCode(String code) {
        // Bạn có thể tùy chỉnh logic kiểm tra mã khuyến mãi ở đây
        if (code.equalsIgnoreCase("PROMO10")) {
            promotionDiscount = 0.10f; // Giảm giá 10%
        } else if (code.equalsIgnoreCase("PROMO20")) {
            promotionDiscount = 0.20f; // Giảm giá 20%
        } else {
            promotionDiscount = 0; // Không giảm giá nếu mã không hợp lệ
        }
    }

    private void InvoiceTable() {
        tcNameInvoice.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tcPriceInvoice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        tcQuantityInvoice.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        tvInvoice.setItems(invoiceItemList);
    }

    @FXML
    private void handleGenerateReceipt(ActionEvent event) throws SQLException {
        try {
            // Load the JasperReport
            String reportPath = "D:\\Java2\\javaproject\\src\\Report\\report1.jrxml";
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);

            // Parameters for the report
            Map<String, Object> parameters = new HashMap<>();
            // Add any required parameters here
            ConnectDB connectDB = new ConnectDB();
            java.sql.Connection cn = connectDB.GetConnectDB();
            String query = "SELECT * FROM receipt"; // Thay 'receipt' bằng tên bảng của bạn
            java.sql.PreparedStatement pStatement = cn.prepareStatement(query);
            java.sql.ResultSet resultSet = pStatement.executeQuery();
            // Fill the report with data
            JRResultSetDataSource jrResultSetDataSource = new JRResultSetDataSource(resultSet);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrResultSetDataSource);

            // View the report
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while generating the receipt.");
            alert.showAndWait();
        }

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText(null);
        alert.setContentText("Print Receipt Successfully");
        if (alert.showAndWait().get() == ButtonType.OK) {
            // Xóa dữ liệu sau khi in biên lai
            ReceiptDAO receiptDAO = new ReceiptDAO();
            boolean isDeleted = receiptDAO.deleteAllReceipts();

            if (isDeleted) {
                System.out.println("All receipts deleted from database successfully.");
            } else {
                System.out.println("Failed to delete receipts from database.");
            }

            invoiceItemList.clear();
            clearFields();
            updateInvoiceTotal();
        }
    }
    
    private void clearFields() {
        invoice_proCode.setText("");
        invoice_Amount.setText("");
        invoice_Change.setText("");
        invoice_Total.setText("$0.00");
        invoice_graTotal.setText("$0.00");
    }

    public void optionCate() {
        Runnable updateLists = () -> {
            if (isUpdateCombobox) {
                category.clear();
                searchCategories.clear();
                List<String> categoryNames = productCategory.stream()
                        .map(ProductsCategory::getCateName)
                        .collect(Collectors.toList());
                List<String> allCategories = new ArrayList<>(categoryNames);
                allCategories.add("All");

                Platform.runLater(() -> {
                    category.addAll(categoryNames);
                    searchCategories.addAll(allCategories);
                });

                isUpdateCombobox = false; // Đánh dấu là đã cập nhật
            }
        };

        productCategory.addListener((ListChangeListener<ProductsCategory>) change -> {
            isUpdateCombobox = true; // Đánh dấu có sự thay đổi mới
            updateLists.run();
        });
        // Gọi updateLists ban đầu để cập nhật combobox
        updateLists.run();
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

        invoiceItemList.addListener((ListChangeListener<InvoiceItem>) change -> {
            updateInvoiceTotal();
        });

        invoice_proCode.textProperty().addListener((observable, oldValue, newValue) -> {
            applyPromotionCode(newValue);
            updateInvoiceTotal();
        });

        invoice_Amount.textProperty().addListener((observable, oldValue, newValue) -> {
            updateInvoiceTotal();
        });

        dateCustomerDob.setValue(LocalDate.now());
        optionCate();
        DisplayProductList();
        SetUserData();
        InvoiceTable();
        handleOnDragImage();
        DisplayProducts();
        ComboBoxCategory();
        DisplayCustomers();
        ComboboxCustomerGender();
        genderList();
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

        filterProductCategoryComboBox.setItems(searchCategories);
        filterProductCategoryComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            seachCategorySelected = newValue;
        });

        pl_category.setItems(searchCategories);
        pl_category.valueProperty().addListener((observable, oldValue, newValue) -> {
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
        ma_updateGender.setValue(ma_gender.getText());
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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a Picture");
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter(
                "Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.webp"
        );
        fileChooser.getExtensionFilters().add(imageFilter);

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            if (file.length() > 1 * 1024 * 1024) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("File size should be less than 1MB.");
                alert.showAndWait();
                return; // Exit the method if file size exceeds 1MB
            }
            try {
                String originalFileName = file.getName();
                String newFileName = System.currentTimeMillis() + "_" + originalFileName;
                File fileDir = new File("src/accessories");
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                File destFile = new File(fileDir, newFileName);
                Files.copy(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                String imgPath = "src/accessories/" + newFileName;
                String username = ma_account.getText();
                boolean isUpdated = new EmployeeInfoDAO().updateEmpPicture(username, imgPath);

                Alert alert;
                if (isUpdated) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Picture updated successfully.");
                    alert.showAndWait();

                    String path = "file:/D:/Java2/javaproject/" + imgPath;
                    var image = new Image(path);
                    menuPicture.setImage(image);
                    ma_picture.setImage(image);

                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to update picture.");
                    alert.showAndWait();
                }

            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Failed to save the picture file.");
                alert.showAndWait();
            }
        }
    }

    public void genderList() {
        List<String> aList = new ArrayList<>();
        aList.addAll(Arrays.asList(genders));

        ObservableList listData = FXCollections.observableArrayList(aList);
        ma_updateGender.setItems(listData);
    }

    @FXML
    private void handleUpdateContact(ActionEvent event) {
        Alert alert;
        if (ma_account.getText().isEmpty() || ma_updateFullname.getText().isEmpty() || ma_updateDOB.getText().isEmpty() || ma_updateGender.getValue().isEmpty() || ma_updatePhone.getText().isEmpty() || ma_updateEmail.getText().isEmpty() || ma_updateAddress.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all the fields");
            alert.showAndWait();
        } else {
            String username = ma_account.getText();
            String fullname = ma_updateFullname.getText();
            String dob = ma_updateDOB.getText();
            String gender = ma_updateGender.getValue();
            String phone = ma_updatePhone.getText();
            String email = ma_updateEmail.getText();
            String address = ma_updateAddress.getText();

            boolean error = false;

            if (!dob.matches("\\d{4}/\\d{2}/\\d{2}")) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Date of Birth must be in the format yyyy/mm/dd.");
                alert.showAndWait();
                error = true;
            } else {
                // Kiểm tra tuổi phải lớn hơn 18
                int birthYear = Integer.parseInt(dob.substring(0, 4));
                int currentYear = java.time.Year.now().getValue();
                if (currentYear - birthYear < 18) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("You are not enogh Age");
                    alert.showAndWait();
                    error = true;
                }
            }

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

            if (error) {
                return;
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

            if (proSelected == null || indexSelected == -1) {
                throw new IllegalArgumentException("No product selected");
            }

            if (proSelected.getProName() == null) {
                throw new IllegalArgumentException("No product selected");
            }
            productNameTextField.setText(proSelected.getProName());
            if (proSelected.getProSKU() == null) {
                throw new IllegalArgumentException("No product selected");
            }
            producSKUTextField.setText(proSelected.getProSKU());
            if (proSelected.getProDescription() == null) {
                throw new IllegalArgumentException("No product selected");
            }
            productDescriptionTextField.setText(proSelected.getProDescription());
            if (proSelected.getProCategory() == null) {
                throw new IllegalArgumentException("No product selected");
            }
            productCategoryComboBox.setValue(String.valueOf(proSelected.getProCategory()));
            // System.out.println(selectedCategory);
            imagePath = proSelected.getProImage();
            if (imagePath == null) {
                throw new IllegalArgumentException("No image selected");
            }

            if (proSelected.getProQuantity() == -1) {
                throw new IllegalArgumentException("No product selected");
            }
            productQuantityTextField.setText(String.valueOf(proSelected.getProQuantity()));

            if (proSelected.getProPrice() == -1) {
                throw new IllegalArgumentException("No product selected");
            }
            productPriceTextField.setText(String.valueOf(proSelected.getProPrice()));
            File file = new File(imagePath);
            if (file.exists()) {
                Image image = new Image(file.toURI().toString());
                productImageView.setImage(image);
                addImageButton.setText(file.getName());
            } else {
                System.out.println("File does not exist: " + imagePath);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("[Warning]: " + e.getMessage());
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
    @FXML
    private void handleAddCategory(MouseEvent event) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Add Category");
        dialog.setHeaderText("Add New Category");
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        Label nameLabel = new Label("Name Category:");
        grid.add(nameLabel, 0, 0);

        TextField textFieldName = new TextField();
        grid.add(textFieldName, 1, 0);

        Text messageError = new Text();
        messageError.setFill(Color.RED);
        grid.add(messageError, 1, 3);

        Button addBtn = new Button();
        addBtn.setText("Add");
        grid.add(addBtn, 0, 4);

        Button updateBtn = new Button();
        updateBtn.setText("Update");
        grid.add(updateBtn, 1, 4);

        // addButtonType.setOnAction(event1 -> handleAddCategoryButton(dialog));
        TableView<ProductsCategory> tableCategory = new TableView<>();
        TableColumn<ProductsCategory, Integer> cateIdColumn = new TableColumn<>("ID");
        TableColumn<ProductsCategory, String> cateNameColumn = new TableColumn<>("Name");

        cateIdColumn.setPrefWidth(80);
        cateNameColumn.setPrefWidth(180);
        AtomicInteger indexcate = new AtomicInteger(0);
        AtomicInteger cateid = new AtomicInteger(0);

        tableCategory.setOnMouseClicked(event1 -> {
            if (event1.getButton() == MouseButton.PRIMARY) {
                ProductsCategory selectedItemcategory = tableCategory.getSelectionModel().getSelectedItem();
                if (selectedItemcategory != null) {
                    cateid.set(selectedItemcategory.getCateId());
                    int indexcategory = tableCategory.getSelectionModel().getSelectedIndex();
                    indexcate.set(indexcategory);
                    textFieldName.setText(selectedItemcategory.getCateName());
                }
            }
        });

        dialog.getDialogPane().setContent(new VBox(8));

        addBtn.setOnAction(ev -> {
            try {
                String cateName = textFieldName.getText();
                if (cateName.isEmpty()) {
                    throw new IllegalArgumentException("Please input name category");
                }
                ProductsCategory cate = new ProductsCategory(cateName);
                Alert alert = new Alert(AlertType.CONFIRMATION,
                        "Are you sure you want to add this category " + "'" + cateName + "'" + " ?",
                        ButtonType.YES,
                        ButtonType.CANCEL);
                alert.setTitle("Confirm Add Category");

                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    productCategory.add(cate);
                    dao.AddCategoryDB(cate);
                    textFieldName.setText("");
                    messageError.setText("");
                    dialog.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                messageError.setText(e.getMessage() + "!");
            }
        });

        // Định nghĩa hành động cho nút "Update"
        updateBtn.setOnAction(ev -> {
            try {
                String cateName = textFieldName.getText();
                if (cateName.isEmpty()) {
                    throw new IllegalArgumentException("Please select category");
                }
                if (cateid.get() == -1) {
                    throw new IllegalAccessException("Please select category");
                }
                Alert alert = new Alert(AlertType.CONFIRMATION,
                        "Are you sure you want to update this product " + "'" + cateName + "'" + "?",
                        ButtonType.YES,
                        ButtonType.CANCEL);
                alert.setTitle("Confirm Update Product");

                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    ProductsCategory cate = new ProductsCategory(cateid.get(), cateName);
                    productCategory.set(indexcate.get(), cate);
                    dao.UpdateCategoryDB(cate);
                    textFieldName.setText("");
                    messageError.setText("");
                    cateid.set(-1);
                    dialog.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                messageError.setText(e.getMessage() + "!");
            }
        });

        cateIdColumn.setCellValueFactory(new PropertyValueFactory<>("CateId"));
        cateNameColumn.setCellValueFactory(new PropertyValueFactory<>("CateName"));
        tableCategory.getColumns().addAll(cateIdColumn, cateNameColumn);
        tableCategory.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            for (ProductsCategory cate : productCategory) {
                tableCategory.getItems().add(cate);
            }
        } catch (Exception e) {
            System.out.println("Error getting category data: " + e.getMessage());
        }

        dialog.getDialogPane().setContent(grid);
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(nameLabel, textFieldName, tableCategory);
        grid.add(vbox, 0, 2, 3, 1);

        dialog.showAndWait();

    }

    public void DisplayCustomers() {
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("cusPhone"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("cusAddress"));
        customerGenderColumn.setCellValueFactory(new PropertyValueFactory<>("cusGender"));
        customerDobColumn.setCellValueFactory(new PropertyValueFactory<>("cusDob"));
        customerTotalpaidColumn.setCellValueFactory(new PropertyValueFactory<>("cusTotalPaid"));
        customerPointColumn.setCellValueFactory(new PropertyValueFactory<>("cusPoint"));
        CustomerTableView.setItems(filteredCustomers);
    }

    @FXML
    private void handleTableCustomer(MouseEvent event) {
        try {
            cusSelected = CustomerTableView.getSelectionModel().getSelectedItem();
            indexSelectedCustomer = CustomerTableView.getSelectionModel().getSelectedIndex();
            if (cusSelected == null || indexSelectedCustomer == -1) {
                throw new IllegalArgumentException("No customer selected");
            }

            if (cusSelected.getCusName().length() == 0) {
                throw new IllegalArgumentException("No customer selected");
            }
            txtCustomerName.setText(cusSelected.getCusName());
            if (cusSelected.getCusGender().length() == 0) {
                throw new IllegalArgumentException("No customer selected");
            }
            cbCustomerGender.setValue(String.valueOf(cusSelected.getCusGender()));

            if (cusSelected.getCusPhone().length() == 0) {
                throw new IllegalArgumentException("No customer selected");
            }
            txtCustomerPhone.setText(cusSelected.getCusPhone());
            if (cusSelected.getCusAddress().length() == 0) {
                throw new IllegalArgumentException("No customer selected");
            }
            txtCustomerAddress.setText(cusSelected.getCusAddress());
            LocalDate dob = LocalDate.parse(cusSelected.getCusDob(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            dateCustomerDob.setValue(dob);
        } catch (Exception e) {
            e.printStackTrace();
            Throwable cause = e.getCause();
            if (cause instanceof InvocationTargetException) {
                cause = cause.getCause();
            }
        }
    }

    public void resetFieldCustomer() {
        txtCustomerName.clear();
        genderCustomerSelected = "";
        cbCustomerGender.setValue(null);
        txtCustomerPhone.clear();
        txtCustomerAddress.clear();
        dateCustomerDob.setValue(null);
        dateCustomerDob.setValue(LocalDate.now());
    }

    @FXML
    private void handleAddCustomer(MouseEvent event) {
        try {
            String customerName = txtCustomerName.getText();
            if (customerName.isEmpty()) {
                throw new IllegalArgumentException("Customer name cannot be empty!");
            }
            String genderCus = genderCustomerSelected;
            if (genderCus == "") {
                throw new IllegalArgumentException("Please select a gender!");
            }
            String customerPhone = txtCustomerPhone.getText();
            if (customerPhone.isEmpty()) {
                throw new IllegalArgumentException("Customer phone cannot be empty!");
            }

            String customerAddress = txtCustomerAddress.getText();
            if (customerAddress.isEmpty()) {
                throw new IllegalArgumentException("Customer address cannot be empty!");
            }

            String dob = dateCustomerDob.getEditor().getText();
            if (!dob.matches("^(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/[0-9]{4}$")) {
                throw new IllegalArgumentException("Invalid date of birth format! Correct format is mm/dd/yyyy");
            }
            LocalDate customerDob = LocalDate.parse(dob, DateTimeFormatter.ofPattern("M/d/yyyy"));

            Alert alert = new Alert(AlertType.CONFIRMATION,
                    "Are you sure you want to add this customer " + "'" + customerName + "'" + " ?", ButtonType.YES,
                    ButtonType.CANCEL);
            alert.showAndWait();
            alert.setTitle("Confirm Add Customer");

            if (alert.getResult() == ButtonType.YES) {
                Customers cus = new Customers(customerName, customerPhone, customerDob.toString(), customerAddress,
                        genderCus, 0f, 0f);
                customersList.add(cus);
                cusDAO.AddCustomerDB(cus);
                resetFieldCustomer();
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
    private void handleUpdateCustomer(MouseEvent event) {
        try {
            String customerName = txtCustomerName.getText();
            if (customerName.isEmpty()) {
                throw new IllegalArgumentException("Customer name cannot be empty!");
            }
            String genderCust = genderCustomerSelected;
            if (genderCust == null) {
                throw new IllegalArgumentException("Please select a gender!");
            }
            String customerPhone = txtCustomerPhone.getText();
            if (customerPhone.isEmpty()) {
                throw new IllegalArgumentException("Customer phone cannot be empty!");
            }

            String customerAddress = txtCustomerAddress.getText();
            if (customerAddress.isEmpty()) {
                throw new IllegalArgumentException("Customer address cannot be empty!");
            }

            String dob = dateCustomerDob.getEditor().getText();
            if (!dob.matches("^(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/[0-9]{4}$")) {
                throw new IllegalArgumentException("Invalid date of birth format! Correct format is mm/dd/yyyy");
            }
            LocalDate customerDob = LocalDate.parse(dob, DateTimeFormatter.ofPattern("M/d/yyyy"));
            Alert alert = new Alert(AlertType.CONFIRMATION,
                    "Are you sure you want to add this customer " + "'" + customerName + "'" + " ?", ButtonType.YES,
                    ButtonType.CANCEL);
            alert.showAndWait();
            alert.setTitle("Confirm Add Customer");

            if (alert.getResult() == ButtonType.YES) {
                Customers cus = new Customers(cusSelected.getCusId(), customerName, customerPhone,
                        customerDob.toString(), customerAddress,
                        genderCust);
                customersList.set(indexSelectedCustomer, cus);
                cusDAO.UpdateCustomerDB(cus);
                resetFieldCustomer();
            }
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid input");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeleteCustomer(MouseEvent event) {
        if (cusSelected == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No customer selected");
            alert.setContentText("Please select a customer to delete.");
            alert.showAndWait();
            return;
        }
        String cusName = cusSelected.getCusName();
        Alert alert = new Alert(AlertType.CONFIRMATION,
                "Are you sure you want to delete this customer " + "'" + cusName + "'" + " ?", ButtonType.YES,
                ButtonType.CANCEL);
        alert.showAndWait();
        alert.setTitle("Confirm Delete customer");

        if (alert.getResult() == ButtonType.YES) {
            int id = cusSelected.getCusId();
            cusDAO.DeleteCustomerDB(id);
            customersList.remove(indexSelectedCustomer);
        }
    }

    @FXML
    private void handleClearFieldCustomer(MouseEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION,
                "Are you sure you want to clear this customer?", ButtonType.YES,
                ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            resetFieldCustomer();
        }
    }

    @FXML
    private void handleSeachCustomer(MouseEvent event) {
        String searchName = txtSearchCusName.getText().toLowerCase();
        String searchPhone = txtSearchCusPhone.getText().toLowerCase();
        filteredCustomers.setPredicate(c -> (searchName.isEmpty() || c.getCusName().toLowerCase().contains(searchName))
                && (searchPhone.isEmpty() || c.getCusPhone().toLowerCase().contains(searchPhone)));
    }

    public void ComboboxCustomerGender() {
        cbCustomerGender.setItems(genderCustomer);
        cbCustomerGender.valueProperty().addListener((observable, oldValue, newValue) -> {
            genderCustomerSelected = newValue;
        });
    }

    @FXML
    private void handlePrintProducts(ActionEvent event) {
    }

}
