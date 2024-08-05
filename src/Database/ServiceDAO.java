package Database;

import static Database.EmployeeInfoDAO.cn;
import static Database.EmployeeInfoDAO.pStatement;
import static Database.EmployeeInfoDAO.rs;
import Models.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Alert;

public class ServiceDAO {

    static ConnectDB connect = new ConnectDB();
    static Connection cn = null;
    static Statement stm = null;
    static ResultSet rs = null;
    static PreparedStatement pStm = null;
    private Alert alert;

    ArrayList<Service> serviceList = new ArrayList<>();

    public ArrayList<Service> ListService() {
        String sql = "SELECT * FROM tbServiceSchedule;";
        try {
            cn = connect.GetConnectDB();
            stm = cn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                String timeDate = String.valueOf(rs.getDate(12));
                Service service = new Service(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getFloat(6), rs.getString(7), rs.getString(8), rs.getFloat(9),rs.getFloat(10), rs.getString(11), timeDate);
                serviceList.add(service);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stm.close();
                cn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return serviceList;
    }

    public void AddService(Service ser) {
        String sql = "INSERT INTO tbServiceSchedule (serName, serCusName, serCusPhone, serPetName, serPetWeight, serScheduleDate, serScheduleTime, serPrice, maxWeight, serStatus) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            cn = connect.GetConnectDB();
            pStm = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pStm.setString(1, ser.getSerName());
            pStm.setString(2, ser.getSerCusName());
            pStm.setString(3, ser.getSerCusPhone());
            pStm.setString(4, ser.getSerPetName());
            pStm.setFloat(5, ser.getSerPetWeight());
            pStm.setString(6, ser.getSerScheduleDate());
            pStm.setString(7, ser.getSerScheduleTime());
            pStm.setFloat(8, ser.getSerPrice());
            pStm.setFloat(9, ser.getMaxWeight());
            pStm.setString(10, ser.getSerStatus());
            pStm.executeUpdate();

            ResultSet generatedKeys = pStm.getGeneratedKeys();
            if (generatedKeys.next()) {
                ser.setSerId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pStm.close();
                cn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void UpdateService(Service ser) {
        String sql = "UPDATE tbServiceSchedule SET serName = ?, serCusName = ?, serCusPhone = ?, serPetName = ?, serPetWeight = ?, serScheduleDate = ?, serScheduleTime = ?, serPrice = ?, maxWeight = ?  WHERE serId = ?";
        try {
            cn = connect.GetConnectDB();
            pStm = cn.prepareStatement(sql);
            pStm.setString(1, ser.getSerName());
            pStm.setString(2, ser.getSerCusName());
            pStm.setString(3, ser.getSerCusPhone());
            pStm.setString(4, ser.getSerPetName());
            pStm.setFloat(5, ser.getSerPetWeight());
            pStm.setString(6, ser.getSerScheduleDate());
            pStm.setString(7, ser.getSerScheduleTime());
            pStm.setFloat(8, ser.getSerPrice());
            pStm.setFloat(9, ser.getMaxWeight());
            pStm.setInt(10, ser.getSerId());
            pStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pStm != null) {
                    pStm.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void UpdateServiceStatus(int id, String newStatus){
        String sql = "UPDATE tbServiceSchedule SET serStatus = ?  WHERE serId = ?";
        try {
            cn = connect.GetConnectDB();
            pStm = cn.prepareStatement(sql);
            pStm.setString(1, newStatus);
            pStm.setInt(2, id);
            pStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pStm != null) {
                    pStm.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
