package Database;

import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Models.Products;
import Models.ProductsCategory;

public class ProductsDAO {
    private ConnectDB connect = new ConnectDB();
    private Connection cn = null;
    private Statement stm = null;
    private ResultSet rs = null;
    private PreparedStatement pStm = null;

    private ArrayList<Products> products = new ArrayList<>();

    private ArrayList<ProductsCategory> category = new ArrayList<>();

    public ArrayList<ProductsCategory> ListCategoryDB() {
        String sql = "SELECT * FROM tbProductCategory;";
        try {
            cn = connect.GetConnectDB();
            stm = cn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                ProductsCategory category = new ProductsCategory(rs.getInt(1),
                        rs.getString(2));
                this.category.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    public ArrayList<Products> ListProductDB() {
        String sql = "SELECT tbProductInfo.proId, tbProductInfo.proName, tbProductInfo.proSKU, tbProductCategory.cateName, tbProductInfo.proImage, tbProductInfo.proDescription, tbProductInfo.proQuantity, tbProductInfo.proPrice, tbProductInfo.proDate FROM tbProductInfo JOIN tbProductCategory ON tbProductInfo.proCategory = tbProductCategory.cateID;";
        try {
            cn = connect.GetConnectDB();
            stm = cn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                // LocalDateTime localDateTime = rs.getTimestamp("proDate").toLocalDateTime();
                // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                // String timeDate = localDateTime.format(formatter);
                String timeDate = String.valueOf(rs.getDate(9));
                Products product = new Products(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getFloat(8), timeDate);
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public Products AddProductDB(Products pro, String imagePath) {
        String sql = "INSERT INTO tbProductInfo (proName, proSKU, proCategory ,proImage, proDescription, proQuantity, proPrice) VALUES (?, ?, ?, ? , ?, ?, ?)";
        String targetDir = "src/images/";
        try {
            cn = connect.GetConnectDB();
            pStm = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pStm.setString(1, pro.getProName());
            pStm.setString(2, pro.getProSKU());
            pStm.setString(3, pro.getProCategory());

            Path sourcePath = Paths.get(imagePath);
            String fileName = sourcePath.getFileName().toString();
            Path targetPath = Paths.get(targetDir + fileName);

            Path oldPath = Paths.get(pro.getProImage());
            if (Files.exists(oldPath) && !oldPath.equals(targetPath)) {
                Files.delete(oldPath);
            }

            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            pro.setProImage(targetPath.toString());
            pStm.setString(4, pro.getProImage());
            pStm.setString(5, pro.getProDescription());
            pStm.setInt(6, pro.getProQuantity());
            pStm.setFloat(7, pro.getProPrice());
            pStm.executeUpdate();

            ResultSet generatedKeys = pStm.getGeneratedKeys();
            if (generatedKeys.next()) {
                pro.setProId(generatedKeys.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pStm != null)
                    pStm.close();
                if (cn != null)
                    cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pro;
    }

    public void UpdateProductDB(Products pro, String imagePath) {
        String sql = "update tbProductInfo set proName = ?, proSKU = ?,proCategory = ?, proImage = ?, proDescription = ?, proQuantity = ?, proPrice = ? where proId = ?";
        String targetDir = "src/images/";
        try {
            cn = connect.GetConnectDB();
            pStm = cn.prepareStatement(sql);
            pStm.setString(1, pro.getProName());
            pStm.setString(2, pro.getProSKU());
            pStm.setInt(3, pro.getProCateId());
            if (!imagePath.isEmpty()) {
                Path sourcePath = Paths.get(imagePath);
                String fileName = sourcePath.getFileName().toString();
                Path targetPath = Paths.get(targetDir + fileName);

                Path oldPath = Paths.get(pro.getProImage());
                if (Files.exists(oldPath) && !oldPath.equals(targetPath)) {
                    Files.delete(oldPath);
                }

                Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                pro.setProImage(targetPath.toString());
            }
            pStm.setString(4, pro.getProImage());
            pStm.setString(5, pro.getProDescription());
            pStm.setInt(6, pro.getProQuantity());
            pStm.setFloat(7, pro.getProPrice());
            pStm.setInt(8, pro.getProId());

            // copy image to folder image only if imagePath is not empty

            pStm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pStm != null)
                    pStm.close();
                if (cn != null)
                    cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // do ảnh bị update khi không chỉnh sửa nếu không thay đổi mới update
    }

    public void DeleteDB(int id) {
        String sql = "delete from tbProductInfo where proId = ?";

        try {
            cn = connect.GetConnectDB();
            pStm = cn.prepareStatement(sql);
            pStm.setInt(1, id);
            pStm.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                cn.close();
                pStm.close();
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }
}