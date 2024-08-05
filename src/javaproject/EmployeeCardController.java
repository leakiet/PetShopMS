package javaproject;

import Database.EmployeeInfoDAO;
import Models.EmployeeInfo;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

public class EmployeeCardController implements Initializable {

    @FXML
    private Label admin_empId;
    @FXML
    private Label admin_empUser;
    @FXML
    private Label admin_empName;
    @FXML
    private Label admin_empGender;
    @FXML
    private Label admin_empPhone;
    @FXML
    private Label admin_empEmail;
    @FXML
    private Label admin_empJoinDate;
    @FXML
    private Label admin_empStatus;
    @FXML
    private ImageView admin_empPicture;

    EmployeeInfoDAO empDAO = new EmployeeInfoDAO();
    @FXML
    private TextField update_Fullname;
    @FXML
    private ComboBox<String> update_Gender;
    @FXML
    private TextField update_Phone;
    @FXML
    private TextField update_Email;

    private final String[] genders = {"Male", "Female", "Other"};

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        genderList();
        addEnterKeyHandler(update_Fullname, this::updateFullname);
        addEnterKeyHandler(update_Gender, this::updateGender);
        addEnterKeyHandler(update_Phone, this::updatePhone);
        addEnterKeyHandler(update_Email, this::updateEmail);
    }

    public void genderList() {
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(genders));

        ObservableList listData = FXCollections.observableArrayList(list);
        update_Gender.setItems(listData);
    }

    public void setData(EmployeeInfo employee) {
        String path = "file:/D:/Java2/javaproject/" + employee.getEmpPicture().replace("\\", "/");
        var image = new Image(path, 60, 60, false, true);
        admin_empPicture.setImage(image);
        admin_empId.setText(String.valueOf(employee.getEmpId()));
        admin_empUser.setText(employee.getEmpUser());
        admin_empName.setText(employee.getEmpName());
        admin_empGender.setText(employee.getEmpGender());
        admin_empPhone.setText(employee.getEmpPhone());
        admin_empEmail.setText(employee.getEmpEmail());
        admin_empJoinDate.setText(employee.getEmpJoinDate());
        admin_empStatus.setText(employee.getEmpIsActive() ? "Active" : "Inactive");
    }

    @FXML
    private void handleUpdateStatus(ActionEvent event) {
        int id = Integer.parseInt(admin_empId.getText());
        String currentStatus = admin_empStatus.getText();
        String newStatus = "";

        if (currentStatus.equals("Inactive")) {
            newStatus = "Active";
        } else if (currentStatus.equals("Active")) {
            newStatus = "Inactive";
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Status Change");
        alert.setHeaderText("Confirm Change Status");
        alert.setContentText("Are you sure you want to change the status to " + newStatus + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            empDAO.updateStatus(id, newStatus);
            admin_empStatus.setText(newStatus);

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Status Updated");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Status updated successfully to " + newStatus);
            successAlert.showAndWait();
        } else {
            alert.close();
        }

    }

    @FXML
    private void handleResetPW(ActionEvent event) {
        int id = Integer.parseInt(admin_empId.getText());
        String newPassword = generatePW(6);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Password Reset");
        alert.setHeaderText("Confirm Reset Password");
        alert.setContentText("Are you sure you want to reset the password?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            empDAO.resetPassword(id, newPassword);

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Password Reset");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Password reset successfully. New Password: " + newPassword);
            successAlert.showAndWait();
        } else {
            alert.close();
        }
    }

    private String generatePW(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }

    private void addEnterKeyHandler(TextField textField, Runnable updateAction) {
        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                updateAction.run();
            }
        });
    }

    private void addEnterKeyHandler(ComboBox<String> comboBox, Runnable updateAction) {
        comboBox.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                updateAction.run();
            }
        });
    }

    private void updateFullname() {
        String newFullname = update_Fullname.getText();
        int id = Integer.parseInt(admin_empId.getText());
        empDAO.updateFullname(id, newFullname);
        admin_empName.setText(newFullname);
        admin_empName.setVisible(true);
        update_Fullname.setVisible(false);

        showAlert("Full Name Updated", "Full name updated successfully.");
    }

    private void updateGender() {
        String newGender = update_Gender.getValue();
        int id = Integer.parseInt(admin_empId.getText());
        empDAO.updateGender(id, newGender);
        admin_empGender.setText(newGender);
        admin_empGender.setVisible(true);
        update_Gender.setVisible(false);

        showAlert("Gender Updated", "Gender updated successfully.");
    }

    private void updatePhone() {
        String newPhone = update_Phone.getText();
        int id = Integer.parseInt(admin_empId.getText());
        empDAO.updatePhone(id, newPhone);
        admin_empPhone.setText(newPhone);
        admin_empPhone.setVisible(true);
        update_Phone.setVisible(false);

        showAlert("Phone Updated", "Phone updated successfully.");
    }

    private void updateEmail() {
        String newEmail = update_Email.getText();
        int id = Integer.parseInt(admin_empId.getText());
        empDAO.updateEmail(id, newEmail);
        admin_empEmail.setText(newEmail);
        admin_empEmail.setVisible(true);
        update_Email.setVisible(false);

        showAlert("Email Updated", "Email updated successfully.");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void switchUpdateFullname(MouseEvent event) {
        admin_empName.setVisible(false);
        update_Fullname.setVisible(true);
        update_Fullname.setText(admin_empName.getText());
    }

    @FXML
    private void switchUpdateGender(MouseEvent event) {
        admin_empGender.setVisible(false);
        update_Gender.setVisible(true);
        update_Gender.setValue(admin_empGender.getText());
    }

    @FXML
    private void switchUpdatePhone(MouseEvent event) {
        admin_empPhone.setVisible(false);
        update_Phone.setVisible(true);
        update_Phone.setText(admin_empPhone.getText());
    }

    @FXML
    private void switchUpdateEmail(MouseEvent event) {
        admin_empEmail.setVisible(false);
        update_Email.setVisible(true);
        update_Email.setText(admin_empEmail.getText());
    }

    @FXML
    private void HandleViewDetails(ActionEvent event) {
    }

}
