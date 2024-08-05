package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Models.InvoiceProducts;
import Models.InvoiceServices;

public class InvoiceProSerDAO {
    private ConnectDB connect = new ConnectDB();
    private Connection cn = null;
    private Statement stm = null;
    private ResultSet rs = null;
    private PreparedStatement pStm = null;

    private ArrayList<InvoiceProducts> invoiceProductList = new ArrayList<>();
    private ArrayList<InvoiceServices> invoiceServiceList = new ArrayList<>();

    public ArrayList<InvoiceProducts> dataProduct() {
        String sql = "SELECT * FROM InvoiceProduct;";
        try {
            cn = connect.GetConnectDB();
            stm = cn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                InvoiceProducts invoiceProduct = new InvoiceProducts();
                invoiceProduct.setInvoicesId(rs.getInt("InvoicesID"));
                invoiceProduct.setProductId(rs.getInt("ProductID"));
                invoiceProduct.setQuantity(rs.getInt("Quantity"));
                invoiceProduct.setInvoiceDate(String.valueOf(rs.getDate("invoiceDate")));
                invoiceProduct.setPrice(rs.getFloat("Price"));
                invoiceProductList.add(invoiceProduct);
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
        return invoiceProductList;
    }

    public ArrayList<InvoiceServices> dataService() {
        String sql = "SELECT * FROM InvoiceService;";
        try {
            cn = connect.GetConnectDB();
            stm = cn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                InvoiceServices invoiceService = new InvoiceServices();
                invoiceService.setInvoicesId(rs.getInt("invoicesId"));
                invoiceService.setServiceId(rs.getInt("serviceId"));
                invoiceService.setQuantity(rs.getInt("quantity"));
                invoiceService.setInvoiceDate(String.valueOf(rs.getDate("invoiceDate")));
                invoiceService.setPrice(rs.getFloat("Price"));
                invoiceServiceList.add(invoiceService);
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
        return invoiceServiceList;
    }
}
