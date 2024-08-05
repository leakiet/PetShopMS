package Database;

import Models.Invoice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javaproject.EmployeeData;

public class InvoiceDAO {
    static ConnectDB connect = new ConnectDB();
    static Connection cn = null;
    static Statement stm = null;
    static ResultSet rs = null;
    static PreparedStatement pStm = null;
    private Alert alert;

    private ArrayList<Invoice> invoiceLists = new ArrayList<>();
    private ArrayList<Invoice> invoiceList = new ArrayList<>();

    public ArrayList<Invoice> data() {
        String sql = "SELECT InvoicesID, CustomerID, InvoicesDate, tbInvoices.staffId, tbEmployeeInfo.empName , Total FROM tbInvoices join tbEmployeeInfo on tbInvoices.staffId  = tbEmployeeInfo.empId";
        try {
            cn = connect.GetConnectDB();
            stm = cn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoicesID(rs.getInt("InvoicesID"));
                invoice.setCustomerID(rs.getInt("CustomerID"));
                // date
                invoice.setInvoicesDate(String.valueOf(rs.getDate("InvoicesDate")));
                invoice.setEmployeeName(rs.getString("empName"));
                invoice.setEmployeeID(rs.getInt("staffId"));
                invoice.setTotal(rs.getFloat("Total"));
                invoiceList.add(invoice);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                rs.close();
                cn.close();
                stm.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return invoiceList;
    }
    
    public ArrayList<Invoice> ListSaleReport() {
        String sql = "SELECT * FROM tbInvoices Where EmployeeID = ?";
        try {
            cn = connect.GetConnectDB();
            pStm = cn.prepareStatement(sql);
            pStm.setInt(1, EmployeeData.id);
            rs = pStm.executeQuery();
            
            while (rs.next()) {
                String timeDate = String.valueOf(rs.getDate(4));
                Invoice invoice = new Invoice(rs.getInt(1), timeDate, rs.getFloat(5));
                invoiceList.add(invoice);
            }
        } catch (SQLException e) {
        } finally {
            try {
                pStm.close();
                cn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return invoiceLists;
    }
}
