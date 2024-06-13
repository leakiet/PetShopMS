package javaproject;

import Database.EmployeeInfoDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private final String[] questions = {"Your Favourite Food?",
        "Your Favourite Color?",
        "Your High School Name?",
        "Write Something?"
    };

    public void questionList() {
        ArrayList<String> qList = new ArrayList<>();

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
    void ocLogin(ActionEvent event) {
        if (lgUsername.getText().isEmpty() || lgPassword.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        } else {
            String username = lgUsername.getText();
            String password = lgPassword.getText();
            empDAO.loginUser(username, password);
        }
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
