package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Models.Customers;
import Models.Products;
import Models.ProductsCategory;

public class CustomersDAO {

    private ConnectDB connect = new ConnectDB();
    private Connection cn = null;
    private Statement stm = null;
    private ResultSet rs = null;
    private PreparedStatement pStm = null;

    private ArrayList<Customers> listCustomers = new ArrayList<>();

    public ArrayList<Customers> ListCustomerDB() {
        String sql = "SELECT * FROM tbCustomer;";
        try {
            cn = connect.GetConnectDB();
            stm = cn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Customers cus = new Customers(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getFloat(7), rs.getFloat(8), rs.getString(9));
                this.listCustomers.add(cus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCustomers;
    }

    public Customers AddCustomerDB(Customers cus) {
        String sql = "INSERT INTO tbCustomer(cusName,cusPhone,cusDOB,cusAddress,cusGender,cusTotalPaid,cusPoint) VALUES(?,?,?,?,?,?,?)";
        try {
            cn = connect.GetConnectDB();
            pStm = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pStm.setString(1, cus.getCusName());
            pStm.setString(2, cus.getCusPhone());
            pStm.setString(3, cus.getCusDob());
            pStm.setString(4, cus.getCusAddress());
            pStm.setString(5, cus.getCusGender());
            pStm.setFloat(6, cus.getCusTotalPaid());
            pStm.setFloat(7, cus.getCusPoint());
            pStm.executeUpdate();

            ResultSet generatedKeys = pStm.getGeneratedKeys();
            if (generatedKeys.next()) {
                cus.setCusId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cus;
    }

    public Customers UpdateCustomerDB(Customers cus) {
        String sql = "UPDATE tbCustomer SET cusName=?,cusPhone=?,cusDOB=?,cusAddress=?,cusGender=? WHERE cusId=?";
        try {
            cn = connect.GetConnectDB();
            pStm = cn.prepareStatement(sql);
            pStm.setString(1, cus.getCusName());
            pStm.setString(2, cus.getCusPhone());
            pStm.setString(3, cus.getCusDob());
            pStm.setString(4, cus.getCusAddress());
            pStm.setString(5, cus.getCusGender());
            pStm.setInt(6, cus.getCusId());
            pStm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cus;
    }

    public void DeleteCustomerDB(int id) {
        String sql = "DELETE FROM tbCustomer WHERE cusId = ?";
        try {
            cn = connect.GetConnectDB();
            pStm = cn.prepareStatement(sql);
            pStm.setInt(1, id);
            pStm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Customers CheckCustomer(String name, String phone) {
        Customers customer = null;
        String sql = "SELECT * FROM tbCustomer WHERE cusPhone = ?";

        try {
            cn = connect.GetConnectDB();
            pStm = cn.prepareStatement(sql);
            pStm.setString(1, phone);
            rs = pStm.executeQuery();

            if (rs.next()) {
                // Customer exists
                customer = new Customers(
                        rs.getInt("cusId"),
                        rs.getString("cusName"),
                        rs.getString("cusPhone"),
                        rs.getString("cusDOB"),
                        rs.getString("cusAddress"),
                        rs.getString("cusGender"),
                        rs.getFloat("cusTotalPaid"),
                        rs.getFloat("cusPoint"),
                        rs.getString(9)
                );
            } else {
                // Customer does not exist, create a new one with default values
                customer = new Customers();
                customer.setCusName(name.isEmpty() ? "guest" : name);
                customer.setCusPhone(phone);
                customer.setCusDob(""); // Default or empty date
                customer.setCusAddress(""); // Default or empty address
                customer.setCusGender(""); // Default or empty gender
                customer.setCusTotalPaid(0);
                customer.setCusPoint(0);
                customer = AddCustomerDB(customer); // Save the new customer to the database
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customer;
    }

}
