package Database;

import Models.EmployeeInfo;
import Models.Products;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

public class ProductsDAO {
    static ConnectDB connect = new ConnectDB();
    static Connection cn = null;
    static Statement stm = null;
    static ResultSet rs = null;
    static PreparedStatement pStatement = null;
    
    public ArrayList<Products> data(){
        ArrayList<Products> productList = new ArrayList<>();
        String sql = "select proId, proName,proImage,proPrice from tbProductInfo";
        try {
            cn = connect.GetConnectDB();
            stm = cn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Products product = new Products();
                product.setProId(rs.getInt("proId"));
                product.setProName(rs.getString("proName"));
                product.setProImage(rs.getString("proImage"));
                product.setProPrice(rs.getFloat("proPrice"));
                productList.add(product);
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
        return productList;
    }
    
}
