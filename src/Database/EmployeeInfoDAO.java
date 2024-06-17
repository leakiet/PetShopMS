package Database;

import Models.EmployeeInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javaproject.LoginFormController;

public class EmployeeInfoDAO {

    static ConnectDB connect = new ConnectDB();
    static Connection cn = null;
    static Statement stm = null;
    static ResultSet rs = null;
    static PreparedStatement pStatement = null;

    private ArrayList<EmployeeInfo> empList;
    private Alert alert;

    public EmployeeInfoDAO() {
        empList = new ArrayList<>();
    }

    public boolean loginUser(String username, String password, String accountType) {
        String sql;
        if(accountType == "Staff"){
             sql = "SELECT * FROM tbEmployeeInfo WHERE empUser =?  and empPassword = ? ";
        } else {
             sql = "SELECT * FROM tbAdmin WHERE adUser =?  and adPassword = ? ";
        }
        
        try {
            cn = connect.GetConnectDB();
            pStatement = cn.prepareStatement(sql);
            pStatement.setString(1, username);
            pStatement.setString(2, password);
            rs = pStatement.executeQuery();
            if (rs.next()) {
                if (rs.getBoolean(11) == true) {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Login Successfully");
                    alert.showAndWait();
                    return true;
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Your account is not active. Please contact Admin");
                    alert.showAndWait();
                }
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Username or Password was Incorrect");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                rs.close();
                cn.close();
                pStatement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    public boolean createUser(String name, String username, String password, String question, String answer) {
        String sql = "SELECT * FROM tbEmployeeInfo WHERE empUser = ?";

        try {
            cn = connect.GetConnectDB();
            pStatement = cn.prepareStatement(sql);
            pStatement.setString(1, username);
            rs = pStatement.executeQuery();

            if (rs.next()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Username already exists");
                alert.showAndWait();
            } else {
                String insertSql = "INSERT INTO tbEmployeeInfo (empName, empUser, empPassword, empQuestion, empAnswer) VALUES (?, ?, ?, ?, ?)";
                pStatement = cn.prepareStatement(insertSql);
                pStatement.setString(1, name);
                pStatement.setString(2, username);
                pStatement.setString(3, password);
                pStatement.setString(4, question);
                pStatement.setString(5, answer);
                int rowsAffected = pStatement.executeUpdate();

                if (rowsAffected > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                rs.close();
                pStatement.close();
                cn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    public boolean resetPassword(String username, String question, String answer, String newPassword) {
        String checkSql = "SELECT * FROM tbEmployeeInfo WHERE empUser = ? AND empQuestion = ? AND empAnswer = ?";
        try {
            cn = connect.GetConnectDB();
            pStatement = cn.prepareStatement(checkSql);
            pStatement.setString(1, username);
            pStatement.setString(2, question);
            pStatement.setString(3, answer);
            rs = pStatement.executeQuery();

            if (rs.next()) {
                String updateSql = "UPDATE tbEmployeeInfo SET empPassword = ? WHERE empUser = ?";
                pStatement = cn.prepareStatement(updateSql);
                pStatement.setString(1, newPassword);
                pStatement.setString(2, username);
                int rowsAffected = pStatement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                rs.close();
                pStatement.close();
                cn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }
}
