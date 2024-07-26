package javaproject;

import Database.EmployeeInfoDAO;
import Models.EmployeeInfo;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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

}
