package Models;

public class Products {
    private int proId;
    private String proName;
    private String proSKU;
    private String proCategory;
    private int proCateId;
    private String proImage;
    private String proDescription;
    private int proQuantity;
    private float proPrice;
    private String proDate;

    public Products() {
    }

    public Products(int proId, String proName, String proSKU, String proCategory, int proCateId, String proImage, String proDescription, int proQuantity, float proPrice, String proDate) {
        this.proId = proId;
        this.proName = proName;
        this.proSKU = proSKU;
        this.proCategory = proCategory;
        this.proCateId = proCateId;
        this.proImage = proImage;
        this.proDescription = proDescription;
        this.proQuantity = proQuantity;
        this.proPrice = proPrice;
        this.proDate = proDate;
    }

    
    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProSKU() {
        return proSKU;
    }

    public void setProSKU(String proSKU) {
        this.proSKU = proSKU;
    }

    public String getProCategory() {
        return proCategory;
    }

    public void setProCategory(String proCategory) {
        this.proCategory = proCategory;
    }

    public int getProCateId() {
        return proCateId;
    }

    public void setProCateId(int proCateId) {
        this.proCateId = proCateId;
    }

    public String getProImage() {
        return proImage;
    }

    public void setProImage(String proImage) {
        this.proImage = proImage;
    }

    public String getProDescription() {
        return proDescription;
    }

    public void setProDescription(String proDescription) {
        this.proDescription = proDescription;
    }

    public int getProQuantity() {
        return proQuantity;
    }

    public void setProQuantity(int proQuantity) {
        this.proQuantity = proQuantity;
    }

    public float getProPrice() {
        return proPrice;
    }

    public void setProPrice(float proPrice) {
        this.proPrice = proPrice;
    }

    public String getProDate() {
        return proDate;
    }

    public void setProDate(String proDate) {
        this.proDate = proDate;
    }
    
    
}