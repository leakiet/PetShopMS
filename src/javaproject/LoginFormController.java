package javaproject;

import Database.EmployeeInfoDAO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginFormController implements Initializable {

    private Alert alert;

    @FXML
    private AnchorPane formLogin;

    @FXML
    private TextField lgUsername;

    @FXML
    private TextField lgPassword;

    @FXML
    private Hyperlink linkForgetPassword;

    @FXML
    private AnchorPane formCreateAccount;

    @FXML
    private ComboBox<String> rpQuestion;

    @FXML
    private ComboBox<String> suQuestion;

    @FXML
    private AnchorPane formResetPassword;

    @FXML
    private AnchorPane leftSide;

    @FXML
    private ImageView iconLogin;

    @FXML
    private Button btnCreateAccount;

    @FXML
    private Button btnHadAccount;

    @FXML
    private ImageView iconForgetPassword;

    @FXML
    private ImageView iconCreateAccount;

    @FXML
    private TextField suAnswer;

    @FXML
    private TextField suPassword;

    @FXML
    private TextField suPwConfirm;

    @FXML
    private TextField suName;

    @FXML
    private TextField suUsername;

    @FXML
    private TextField rpUsername;

    @FXML
    private TextField rpAnswer;

    @FXML
    private TextField rpPassword;

    @FXML
    private TextField rpConfirmPw;

    @FXML
    private TextField lgPwShown;

    @FXML
    private ComboBox<String> lgAccountType;

    @FXML
    private CheckBox lgShowPw;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accountList();
    }

    private final String[] questions = {"Your Favourite Food?",
        "Your Favourite Color?",
        "Your High School Name?",
        "Write Something?"
    };

    private final String[] accounts = {"Admin", "Staff"};

    @FXML
    public void ocShowPw(ActionEvent event) {
        if (lgShowPw.isSelected()) {
            lgPwShown.setText(lgPassword.getText());
            lgPassword.setVisible(false);
            lgPwShown.setVisible(true);
        } else {
            lgPassword.setText(lgPwShown.getText());
            lgPassword.setVisible(true);
            lgPwShown.setVisible(false);
        }
    }

    public void accountList() {
        List<String> aList = new ArrayList<>();
        aList.addAll(Arrays.asList(accounts));

        ObservableList listData = FXCollections.observableArrayList(aList);
        lgAccountType.setItems(listData);
    }

    public void questionList() {
        List<String> qList = new ArrayList<>();
        qList.addAll(Arrays.asList(questions));

        ObservableList listData = FXCollections.observableArrayList(qList);
        rpQuestion.setItems(listData);
        suQuestion.setItems(listData);
    }

    @FXML
    public void switchForgetPassword() {
        formCreateAccount.setVisible(false);
        formLogin.setVisible(false);
        formResetPassword.setVisible(true);
        btnCreateAccount.setVisible(true);
        btnHadAccount.setVisible(false);
        iconLogin.setVisible(false);
        iconForgetPassword.setVisible(true);
        iconCreateAccount.setVisible(false);
        questionList();
    }

    @FXML
    public void switchLogin() {
        formCreateAccount.setVisible(false);
        formLogin.setVisible(true);
        formResetPassword.setVisible(false);
        btnCreateAccount.setVisible(true);
        btnHadAccount.setVisible(false);
        iconLogin.setVisible(true);
        iconForgetPassword.setVisible(false);
        iconCreateAccount.setVisible(false);
    }

    @FXML
    public void switchCreateAccount() {
        formCreateAccount.setVisible(true);
        formLogin.setVisible(false);
        formResetPassword.setVisible(false);
        btnCreateAccount.setVisible(false);
        btnHadAccount.setVisible(true);
        iconLogin.setVisible(false);
        iconForgetPassword.setVisible(false);
        iconCreateAccount.setVisible(true);
        questionList();
    }

    EmployeeInfoDAO empDAO = new EmployeeInfoDAO();

    @FXML
    void ocLogin(ActionEvent event) throws IOException {
        if (lgUsername.getText().isEmpty() || lgPassword.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        } else {
            if (lgAccountType.getValue() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select Account Type");
                alert.showAndWait();
            } else {

                if (!lgPwShown.isVisible()) {
                    if (!lgPwShown.getText().equals(lgPassword.getText())) {
                        lgPwShown.setText(lgPassword.getText());
                    }
                } else {
                    if (!lgPwShown.getText().equals(lgPassword.getText())) {
                        lgPassword.setText(lgPwShown.getText());
                    }
                }

                String username = lgUsername.getText();
                String password = lgPassword.getText();
                String accountType = lgAccountType.getValue();
                boolean loggedIn = empDAO.loginUser(username, password, accountType);

                if (loggedIn) {
                    if (accountType == "Admin") {
                        switchToAdminForm();
                    } else {
                        switchToMainForm();
                    }
                }
            }
        }
    }

    public void switchToMainForm() throws IOException {
        URL url = new File("src/javaproject/MainForm.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Pet Shop Management System - Staff Portal");
        stage.setScene(scene);
        stage.show();

        Stage loginStage = (Stage) formLogin.getScene().getWindow();
        loginStage.close();
    }

    public void switchToAdminForm() throws IOException {
        URL url = new File("src/javaproject/AdminForm.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Pet Shop Management System - Admin Portal");
        stage.setScene(scene);
        stage.show();

        Stage loginStage = (Stage) formLogin.getScene().getWindow();
        loginStage.close();
    }

    @FXML
    void ocSignUp(ActionEvent event) {
        if (suName.getText().isEmpty() || suUsername.getText().isEmpty() || suPassword.getText().isEmpty() || suPwConfirm.getText().isEmpty() || suAnswer.getText().isEmpty() || suQuestion.getValue() == null) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all the fields");
            alert.showAndWait();
        } else {
            String name = suName.getText();
            String username = suUsername.getText();
            String password = suPassword.getText();
            String confirmPassword = suPwConfirm.getText();
            String answer = suAnswer.getText();
            String question = suQuestion.getValue();
            boolean error = false;

            if (name.matches(".*\\d+.*")) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Name cannot contain numbers");
                alert.showAndWait();
                error = true;
            }

            if (!username.matches("^[a-zA-Z0-9_]+$") || username.length() < 6) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Username can only contain letters, numbers, and underscores, and must be at least 6 characters long");
                alert.showAndWait();
                error = true;
            }

            if (password.length() < 6) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Password must be at least 6 characters long");
                alert.showAndWait();
                error = true;
            }

            if (!password.equals(confirmPassword)) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Password and Confirm Password do not match");
                alert.showAndWait();
                error = true;
            }

            if (!error) {
                boolean userCreated = empDAO.createUser(name, username, password, question, answer);
                if (userCreated) {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Success Message");
                    alert.setHeaderText(null);
                    alert.setContentText("User created successfully");
                    alert.showAndWait();
                    switchLogin();
                    suName.clear();
                    suUsername.clear();
                    suPassword.clear();
                    suPwConfirm.clear();
                    suAnswer.clear();
                    suQuestion.getItems().clear();
                }
            }
        }
    }

    @FXML
    void ocResetPassword(ActionEvent event) {
        if (rpUsername.getText().isEmpty() || rpQuestion.getValue() == null || rpAnswer.getText().isEmpty() || rpPassword.getText().isEmpty() || rpConfirmPw.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all the fields");
            alert.showAndWait();
        } else {
            String username = rpUsername.getText();
            String question = rpQuestion.getValue();
            String answer = rpAnswer.getText();
            String newPassword = rpPassword.getText();
            String confirmPassword = rpConfirmPw.getText();
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
                boolean passwordReset = empDAO.resetPassword(username, question, answer, newPassword);
                if (passwordReset) {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Success Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Password reset successfully");
                    alert.showAndWait();
                    switchLogin();
                    rpUsername.clear();
                    rpQuestion.getSelectionModel().clearSelection();
                    rpAnswer.clear();
                    rpPassword.clear();
                    rpConfirmPw.clear();
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to reset password. Please check your inputs.");
                    alert.showAndWait();
                }
            }
        }
    }

}
