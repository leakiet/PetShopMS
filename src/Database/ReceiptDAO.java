package Database;

import static Database.EmployeeInfoDAO.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Models.Receipt;

public class ReceiptDAO {

    private Connection cn;
    private PreparedStatement pStatement;
    private ResultSet rs;

    public boolean addReceipt(Receipt receipt) {
        String sqlCheck = "SELECT COUNT(*) FROM receipt WHERE rName = ? AND rPrice = ? AND rQuantity = ?";
        String sqlInsert = "INSERT INTO receipt (graTotal, rName, rPrice, rQuantity) VALUES (?,?,?,?)";

        try {
            cn = connect.GetConnectDB();
            pStatement = cn.prepareStatement(sqlCheck);
            pStatement.setString(1, receipt.getRName());
            pStatement.setFloat(2, receipt.getRPrice());
            pStatement.setInt(3, receipt.getRQuantity());
            rs = pStatement.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Receipt already exists, not adding.");
                return false;
            }

            pStatement = cn.prepareStatement(sqlInsert);
            pStatement.setFloat(1, receipt.getGraTotal());
            pStatement.setString(2, receipt.getRName());
            pStatement.setFloat(3, receipt.getRPrice());
            pStatement.setInt(4, receipt.getRQuantity());
            int rowsAffected = pStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Receipt added successfully");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pStatement != null) {
                    pStatement.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    public boolean deleteAllReceipts() {
        String sql = "DELETE FROM receipt";

        try {
            cn = connect.GetConnectDB();
            pStatement = cn.prepareStatement(sql);
            int rowsAffected = pStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("All receipts deleted successfully");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (pStatement != null) {
                    pStatement.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

}
