package javaproject;

import Database.EmployeeInfoDAO;
import Models.EmployeeInfo;
import Models.Products;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DisplayEmployee();
    }    
    
    public void DisplayEmployee(){
        ObservableList<EmployeeInfo> eList = empList;
        
        if (empList == null){
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
    
}
