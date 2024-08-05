package javaproject;

import Database.CustomersDAO;
import Database.EmployeeInfoDAO;
import Database.InvoiceDAO;
import Database.InvoiceProSerDAO;
import Models.Customers;
import Models.EmployeeInfo;
import Models.Invoice;
import Models.InvoiceProducts;
import Models.InvoiceServices;
import Models.Products;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AdminFormController implements Initializable {

    @FXML
    private VBox empListLayout;

    EmployeeInfoDAO empDAO = new EmployeeInfoDAO();
    ObservableList<EmployeeInfo> empList = FXCollections.observableArrayList(empDAO.ListEmployee());
    FilteredList<EmployeeInfo> filteredEmployee = new FilteredList<>(empList, p -> true);

    @FXML
    private AnchorPane formMenu;
    @FXML
    private AnchorPane formReport;
    @FXML
    private AnchorPane formDashBoard;
    @FXML
    private VBox formEmployee;
    @FXML
    private BorderPane AdminForm;
    @FXML
    private Label label;
    @FXML
    private VBox card1;
    @FXML
    private Label txtTitleCard1;
    @FXML
    private Label txtCard1;
    @FXML
    private VBox card2;
    @FXML
    private Label txtTitleCard2;
    @FXML
    private Label txtCard2;
    @FXML
    private VBox card3;
    @FXML
    private Label txtTitleCard3;
    @FXML
    private Label txtCard3;
    @FXML
    private VBox card4;
    @FXML
    private Label txtTitleCard4;
    @FXML
    private Label txtCard4;
    @FXML
    private ComboBox<String> comboboxYearDB;
    @FXML
    private LineChart DBLineChart;
    @FXML
    private PieChart DBpieChart;

    InvoiceDAO invDAO = new InvoiceDAO();
    InvoiceProSerDAO invProSerDAO = new InvoiceProSerDAO();
    CustomersDAO cusDAO = new CustomersDAO();

    ObservableList<Customers> customersList = FXCollections.observableArrayList(cusDAO.ListCustomerDB());
    FilteredList<Customers> filteredCustomers = new FilteredList<>(customersList, p -> true);
    Map<String, Integer> monthlyCustomerCounts;

    DateTimeFormatter formatterDateDashboard = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    ObservableList<Invoice> invoicesList = FXCollections.observableArrayList(invDAO.data());
    FilteredList<Invoice> filteredInvoice = new FilteredList<>(invoicesList, p -> true);

    ObservableList<InvoiceProducts> invoicesProductList = FXCollections.observableArrayList(invProSerDAO.dataProduct());
    FilteredList<InvoiceProducts> filteredInvoiceProduct = new FilteredList<>(invoicesProductList, p -> true);

    ObservableList<InvoiceServices> invoicesServiceList = FXCollections.observableArrayList(invProSerDAO.dataService());
    FilteredList<InvoiceServices> filteredInvoiceService = new FilteredList<>(invoicesServiceList, p -> true);

    private String selectedYear = String.valueOf(LocalDate.now().getYear());

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DisplayEmployee();
        startDashBoard();
        comboboxYear();
        selectComboBoxYear();
        comboboxYearDB.setOnAction(e -> {
            filterYearDashboard();
            if (card1.getStyle() == "-fx-background-color: #d15662;") {
                lineChartCustomer();
                pieChartCustomers();
            } else if (card2.getStyle() == "-fx-background-color: #d15662;") {
                lineChartInvoice();
                pieChartInvoice();
            } else if (card3.getStyle() == "-fx-background-color: #d15662;") {
                lineChartRevenue();
                pieChartRevenue();
            } else if (card4.getStyle() == "-fx-background-color: #d15662;") {
                lineChartSales();
                pieChartSales();
            }
            setTextCard();
        });
    }

    public void DisplayEmployee() {
        ObservableList<EmployeeInfo> eList = empList;

        if (empList == null) {
            System.out.println("EmptyList");
        }

        try {
            for (int i = 0; i < eList.size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                URL url = new File("src/javaproject/EmployeeCard.fxml").toURI().toURL();
                loader.setLocation(url);

                if (loader.getLocation() == null) {
                    System.out.println("FXML file not found");
                }

                HBox hBox = loader.load();
                EmployeeCardController controller = loader.getController();
                controller.setData(eList.get(i));

                empListLayout.getChildren().add(hBox);

            }
        } catch (IOException e) {
            System.out.println("Error Display Product: " + e.getMessage());
        }
    }

    @FXML
    private void admin_searchName(ActionEvent event) {
    }

    @FXML
    private void admin_searchAccount(ActionEvent event) {
    }

    @FXML
    private void admin_searchStaffID(ActionEvent event) {
    }

    @FXML
    private void switchDashBoard(MouseEvent event) {
        formDashBoard.setVisible(true);
        formEmployee.setVisible(false);
        formReport.setVisible(false);
    }

    @FXML
    private void switchEmployee(MouseEvent event) {
        formDashBoard.setVisible(false);
        formEmployee.setVisible(true);
        formReport.setVisible(false);
    }

    @FXML
    private void switchReports(MouseEvent event) {
        formDashBoard.setVisible(false);
        formEmployee.setVisible(false);
        formReport.setVisible(true);
    }

    @FXML
    private void handleLogOut(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Logout");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to log out?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            URL url = new File("src/javaproject/LoginForm.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Pet Shop Management System");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            Stage currentStage = (Stage) AdminForm.getScene().getWindow();
            currentStage.close();
        }
    }

    public void filterYearDashboard() {

        filteredCustomers.setPredicate(customer -> {
            LocalDate accountDate = LocalDate.parse(customer.getCusDateAccount(), formatterDateDashboard);
            return accountDate.getYear() == Integer.parseInt(selectedYear);
        });

        filteredInvoice.setPredicate(invoice -> {
            LocalDate localDate = LocalDate.parse(invoice.getInvoicesDate(), formatterDateDashboard);
            return localDate.getYear() == Integer.parseInt(selectedYear);
        });

        filteredInvoiceProduct.setPredicate(invoice -> {
            LocalDate localDate = LocalDate.parse(invoice.getInvoiceDate(), formatterDateDashboard);
            return localDate.getYear() == Integer.parseInt(selectedYear);
        });

        filteredInvoiceService.setPredicate(invoice -> {
            LocalDate localDate = LocalDate.parse(invoice.getInvoiceDate(), formatterDateDashboard);
            return localDate.getYear() == Integer.parseInt(selectedYear);
        });
    }

    public void startDashBoard() {
        card1.setStyle("-fx-background-color: #d15662;");
        txtCard1.setTextFill(Color.WHITE);
        txtTitleCard1.setTextFill(Color.WHITE);
        setTextCard();
        pieChartCustomers();
        lineChartCustomer();
    }

    public void setTextCard() {
        float total = 0;
        int Quantity = 0;
        txtCard1.setText(String.valueOf(filteredCustomers.size()));
        txtCard2.setText(String.valueOf(filteredInvoice.size()));
        for (Invoice invoice : filteredInvoice) {
            total += invoice.getTotal();
        }
        txtCard3.setText(String.valueOf(total + "$"));
        for (InvoiceProducts invoice : filteredInvoiceProduct) {
            Quantity += invoice.getQuantity();
        }

        for (InvoiceServices invoice : filteredInvoiceService) {
            Quantity += invoice.getQuantity();
        }
        txtCard4.setText(String.valueOf(Quantity));
    }

    public void selectComboBoxYear() {
        comboboxYearDB.valueProperty().addListener((observable, oldValue, newValue) -> {
            selectedYear = (String) newValue;
        });
    }

    public void comboboxYear() {
        int currentYear = LocalDate.now().getYear();
        List<String> years = IntStream.rangeClosed(2022, currentYear)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());
        comboboxYearDB.getItems().setAll(years);
    }

    // dashBoard customers <============================================x
    public void pieChartCustomers() {
        if (selectedYear == null) {
            selectedYear = String.valueOf(LocalDate.now().getYear());
        }
        DBpieChart.getData().clear();

        // Lọc danh sách khách hàng theo năm đã chọn
        int lowSpend = 0, mediumSpend = 0, highSpend = 0;
        for (Customers customer : filteredCustomers) {
            if (customer.getCusTotalPaid() < 50) {
                lowSpend++;
            } else if (customer.getCusTotalPaid() <= 100) {
                mediumSpend++;
            } else {
                highSpend++;
            }
        }

        if (lowSpend == 0 && mediumSpend == 0 && highSpend == 0) {
            return;
        }

        int totalSpend = lowSpend + mediumSpend + highSpend;

        double lowPercentage = ((double) lowSpend / totalSpend) * 100;
        double mediumPercentage = ((double) mediumSpend / totalSpend) * 100;
        double highPercentage = ((double) highSpend / totalSpend) * 100;

        String lowPercentageStr = String.format("%.2f%%", lowPercentage);
        String mediumPercentageStr = String.format("%.2f%%", mediumPercentage);
        String highPercentageStr = String.format("%.2f%%", highPercentage);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("(<50) low spent seize: " + lowPercentageStr, lowSpend),
                new PieChart.Data("(50-100) Medium Spend seize: " + mediumPercentageStr, mediumSpend),
                new PieChart.Data("(>100) High Spend seize: " + highPercentageStr, highSpend));
        DBpieChart.setPrefSize(800, 600);
        DBpieChart.setData(pieChartData);
    }

    public void lineChartCustomer() {
        if (selectedYear == null) {
            selectedYear = String.valueOf(LocalDate.now().getYear());
        }
        DBLineChart.getData().clear();
        Map<String, Integer> monthlyCustomerCounts = new HashMap<>();

        String[] monthNames = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
        for (String monthName : monthNames) {
            monthlyCustomerCounts.put(monthName, 0);
        }

        for (Customers customer : filteredCustomers) {
            LocalDate accountDate = LocalDate.parse(customer.getCusDateAccount(), formatterDateDashboard);
            String monthYear = monthNames[accountDate.getMonthValue() - 1];
            monthlyCustomerCounts.put(monthYear, monthlyCustomerCounts.getOrDefault(monthYear, 0) + 1);
        }

        DBLineChart.setTitle("Customers");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Customers");

        for (String monthName : monthNames) {
            series.getData().add(new XYChart.Data<>(monthName, monthlyCustomerCounts.get(monthName)));
        }

        DBLineChart.getData().add(series);
    }

    // dashBoard invoice <============================================
    public void pieChartInvoice() {
        if (selectedYear == null) {
            selectedYear = String.valueOf(LocalDate.now().getYear());
        }
        DBpieChart.getData().clear();

        Map<String, Float> employeeSales = new HashMap<>();

        float totalSales = 0;

        for (Invoice invoice : filteredInvoice) {
            totalSales += invoice.getTotal();
            String employeeName = invoice.getEmployeeName();
            employeeSales.put(employeeName, employeeSales.getOrDefault(employeeName, 0f) + invoice.getTotal());
        }

        Map<String, Float> employeeSalesPercentage = new HashMap<>();

        for (Map.Entry<String, Float> entry : employeeSales.entrySet()) {
            String employeeName = entry.getKey();
            float sales = entry.getValue();
            float percentage = (sales / totalSales) * 100;
            employeeSalesPercentage.put(employeeName, percentage);
        }

        PieChart.Data[] pieChartData = employeeSalesPercentage.entrySet().stream()
                .map(entry -> new PieChart.Data(
                entry.getKey() + " seize: " + String.format("%.2f", entry.getValue()) + "%",
                entry.getValue()))
                .toArray(PieChart.Data[]::new);

        DBpieChart.setPrefSize(800, 600);
        DBpieChart.setData(FXCollections.observableArrayList(pieChartData));
    }

    public void lineChartInvoice() {
        if (selectedYear == null) {
            selectedYear = String.valueOf(LocalDate.now().getYear());
        }
        DBLineChart.getData().clear();
        Map<String, Integer> monthlyCustomerCounts = new HashMap<>();

        String[] monthNames = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
        for (String monthName : monthNames) {
            monthlyCustomerCounts.put(monthName, 0);
        }

        for (Invoice inv : filteredInvoice) {
            LocalDate localDate = LocalDate.parse(inv.getInvoicesDate(), formatterDateDashboard);
            String monthYear = monthNames[localDate.getMonthValue() - 1];
            monthlyCustomerCounts.put(monthYear, monthlyCustomerCounts.getOrDefault(monthYear, 0) + 1);
        }

        DBLineChart.setTitle("Invoices");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Invoices");

        for (String monthName : monthNames) {
            series.getData().add(new XYChart.Data<>(monthName, monthlyCustomerCounts.get(monthName)));
        }
        DBLineChart.getData().add(series);
    }

    // dashBoard Revenue <============================================
    public void lineChartRevenue() {
        if (selectedYear == null) {
            selectedYear = String.valueOf(LocalDate.now().getYear());
        }
        DBLineChart.getData().clear();
        Map<String, Float> monthlyRevenueCounts = new HashMap<>();

        String[] monthNames = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
        for (String monthName : monthNames) {
            monthlyRevenueCounts.put(monthName, 0f);
        }

        for (Invoice inv : filteredInvoice) {
            LocalDate localDate = LocalDate.parse(inv.getInvoicesDate(), formatterDateDashboard);
            String monthYear = monthNames[localDate.getMonthValue() - 1];
            monthlyRevenueCounts.put(monthYear, monthlyRevenueCounts.getOrDefault(monthYear, 0f) + inv.getTotal());
        }

        DBLineChart.setTitle("Revenue");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Revenue");

        for (String monthName : monthNames) {
            series.getData().add(new XYChart.Data<>(monthName, monthlyRevenueCounts.get(monthName)));
        }

        DBLineChart.getData().add(series);
    }

    public void pieChartRevenue() {
        if (selectedYear == null) {
            selectedYear = String.valueOf(LocalDate.now().getYear());
        }
        DBpieChart.getData().clear();
        Map<String, Float> quarterSales = new HashMap<>();

        float totalSales = 0;

        for (Invoice invoice : filteredInvoice) {
            LocalDate localDate = LocalDate.parse(invoice.getInvoicesDate(), formatterDateDashboard);
            int quarter = (localDate.getMonthValue() - 1) / 3 + 1;
            String quarterYear = "quarter" + quarter + " in year " + localDate.getYear();
            totalSales += invoice.getTotal();
            quarterSales.put(quarterYear, quarterSales.getOrDefault(quarterYear, 0f) + invoice.getTotal());
        }

        Map<String, Float> employeeSalesPercentage = new HashMap<>();

        for (Map.Entry<String, Float> entry : quarterSales.entrySet()) {
            String quarterYear = entry.getKey();
            float sales = entry.getValue();
            float percentage = (sales / totalSales) * 100;
            employeeSalesPercentage.put(quarterYear, percentage);
        }

        PieChart.Data[] pieChartData = employeeSalesPercentage.entrySet().stream()
                .map(entry -> new PieChart.Data(
                entry.getKey().replace("quarter", "quarter ") + " seize: "
                + String.format("%.2f", entry.getValue()) + "%",
                entry.getValue()))
                .toArray(PieChart.Data[]::new);

        DBpieChart.setPrefSize(800, 600);
        DBpieChart.setData(FXCollections.observableArrayList(pieChartData));
    }

    // dashBoard sales <============================================
    public void lineChartSales() {
        if (selectedYear == null) {
            selectedYear = String.valueOf(LocalDate.now().getYear());
        }
        DBLineChart.getData().clear();
        Map<String, Float> monthlySaleCounts = new HashMap<>();

        String[] monthNames = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
        for (String monthName : monthNames) {
            monthlySaleCounts.put(monthName, 0f);
        }

        for (InvoiceProducts invProduct : filteredInvoiceProduct) {
            LocalDate localDate = LocalDate.parse(invProduct.getInvoiceDate(), formatterDateDashboard);
            String monthYear = monthNames[localDate.getMonthValue() - 1];
            monthlySaleCounts.put(monthYear,
                    monthlySaleCounts.getOrDefault(monthYear, 0f) + invProduct.getQuantity());
        }

        for (InvoiceServices invService : filteredInvoiceService) {
            LocalDate localDate = LocalDate.parse(invService.getInvoiceDate(), formatterDateDashboard);
            String monthYear = monthNames[localDate.getMonthValue() - 1];
            monthlySaleCounts.put(monthYear,
                    monthlySaleCounts.getOrDefault(monthYear, 0f) + invService.getQuantity());
        }

        DBLineChart.setTitle("Sales");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Sales");

        for (String monthName : monthNames) {
            series.getData().add(new XYChart.Data<>(monthName,
                    monthlySaleCounts.get(monthName)));
        }

        DBLineChart.getData().add(series);
    }

    public void pieChartSales() {
        if (selectedYear == null) {
            selectedYear = String.valueOf(LocalDate.now().getYear());
        }
        DBpieChart.getData().clear();
        float totalSales = 0;

        float productSales = 0;
        float serviceSales = 0;

        for (InvoiceProducts invProduct : filteredInvoiceProduct) {
            productSales += invProduct.getQuantity();
        }

        for (InvoiceServices invService : filteredInvoiceService) {
            serviceSales += invService.getQuantity();
        }

        totalSales = productSales + serviceSales;

        Map<String, Float> SalesPercentage = new HashMap<>();

        float productPercentage = (productSales / totalSales) * 100;
        SalesPercentage.put("Product", productPercentage);

        float servicePercentage = (serviceSales / totalSales) * 100;
        SalesPercentage.put("Service", servicePercentage);

        PieChart.Data[] pieChartData = SalesPercentage.entrySet().stream()
                .map(entry -> new PieChart.Data(
                entry.getKey() + " seize: " + String.format("%.2f", entry.getValue()) + "%",
                entry.getValue()))
                .toArray(PieChart.Data[]::new);

        DBpieChart.setPrefSize(800, 600);
        DBpieChart.setData(FXCollections.observableArrayList(pieChartData));
    }

    @FXML
    private void handleCard1(MouseEvent event) {
        pieChartCustomers();
        lineChartCustomer();
        setCardStyle(card1, txtCard1, txtTitleCard1);
        card1.setStyle("-fx-background-color: #d15662;");
        txtCard1.setTextFill(Color.WHITE);
        txtTitleCard1.setTextFill(Color.WHITE);
    }

    @FXML
    private void handleCard2(MouseEvent event) {
        pieChartInvoice();
        lineChartInvoice();
        setCardStyle(card2, txtCard2, txtTitleCard2);
        card2.setStyle("-fx-background-color: #d15662;");
        txtCard2.setTextFill(Color.WHITE);
        txtTitleCard2.setTextFill(Color.WHITE);
    }

    @FXML
    private void handleCard3(MouseEvent event) {
        lineChartRevenue();
        pieChartRevenue();
        setCardStyle(card3, txtCard3, txtTitleCard3);
        card3.setStyle("-fx-background-color: #d15662;");
        txtCard3.setTextFill(Color.WHITE);
        txtTitleCard3.setTextFill(Color.WHITE);
    }

    @FXML
    private void handleCard4(MouseEvent event) {
        lineChartSales();
        pieChartSales();
        setCardStyle(card4, txtCard4, txtTitleCard4);
        card4.setStyle("-fx-background-color: #d15662;");
        txtCard4.setTextFill(Color.WHITE);
        txtTitleCard4.setTextFill(Color.WHITE);
    }

    private void setCardStyle(VBox card, Label txt, Label txtTitle) {
        card.setStyle("-fx-background-color: #d15662;");
        txt.setTextFill(Color.WHITE);
        txtTitle.setTextFill(Color.WHITE);
        card1.setStyle("-fx-background-color: #FFF;");
        card2.setStyle("-fx-background-color: #FFF;");
        card3.setStyle("-fx-background-color: #FFF;");
        card4.setStyle("-fx-background-color: #FFF;");

        txtCard1.setTextFill(Color.BLACK);
        txtTitleCard1.setTextFill(Color.BLACK);
        txtCard2.setTextFill(Color.BLACK);
        txtTitleCard2.setTextFill(Color.BLACK);
        txtCard3.setTextFill(Color.BLACK);
        txtTitleCard3.setTextFill(Color.BLACK);
        txtCard4.setTextFill(Color.BLACK);
        txtTitleCard4.setTextFill(Color.BLACK);
    }

}
