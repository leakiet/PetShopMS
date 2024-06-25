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

    public Products(int proId, String proName, String proSKU, String proCategory, int proCateId, String proImage,
            String proDescription, int proQuantity, float proPrice, String proDate) {
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

    public Products(int proId, String proName, String proSKU, String proCategory, int proCateId,
            String proDescription, int proQuantity, float proPrice, String proDate) {
        this.proId = proId;
        this.proName = proName;
        this.proSKU = proSKU;
        this.proCategory = proCategory;
        this.proCateId = proCateId;
        // this.proImage = proImage;
        this.proDescription = proDescription;
        this.proQuantity = proQuantity;
        this.proPrice = proPrice;
        this.proDate = proDate;
    }

    public Products(int proId, String proName, String proSKU, String proCategory, String proImage,
            String proDescription, int proQuantity, float proPrice) {
        this.proId = proId;
        this.proName = proName;
        this.proSKU = proSKU;
        this.proCategory = proCategory;
        this.proImage = proImage;
        this.proDescription = proDescription;
        this.proQuantity = proQuantity;
        this.proPrice = proPrice;
        // this.proDate = proDate;
    }

    public Products(int proId, String proName, String proSKU, String proCategory, String proImage,
            String proDescription, int proQuantity, float proPrice, String proDate) {
        this.proId = proId;
        this.proName = proName;
        this.proSKU = proSKU;
        this.proCategory = proCategory;
        this.proImage = proImage;
        this.proDescription = proDescription;
        this.proQuantity = proQuantity;
        this.proPrice = proPrice;
        this.proDate = proDate;
    }

    public Products(String proName, String proSKU, String proCategory, String proImage,
            String proDescription, int proQuantity, float proPrice, String proDate) {
        this.proName = proName;
        this.proSKU = proSKU;
        this.proCategory = proCategory;
        this.proImage = proImage;
        this.proDescription = proDescription;
        this.proQuantity = proQuantity;
        this.proPrice = proPrice;
        this.proDate = proDate;
    }

    public Products() {

    }

    /**
     * @return int return the proId
     */
    public int getProId() {
        return proId;
    }

    /**
     * @param proId the proId to set
     */
    public void setProId(int proId) {
        this.proId = proId;
    }

    /**
     * @return String return the proName
     */
    public String getProName() {
        return proName;
    }

    /**
     * @param proName the proName to set
     */
    public void setProName(String proName) {
        this.proName = proName;
    }

    /**
     * @return String return the proSKU
     */
    public String getProSKU() {
        return proSKU;
    }

    /**
     * @param proSKU the proSKU to set
     */
    public void setProSKU(String proSKU) {
        this.proSKU = proSKU;
    }

    /**
     * @return String return the proCategory
     */
    public String getProCategory() {
        return proCategory;
    }

    /**
     * @param proCategory the proCategory to set
     */
    public void setProCategory(String proCategory) {
        this.proCategory = proCategory;
    }

    /**
     * @return String return the proImage
     */
    public String getProImage() {
        return proImage;
    }

    /**
     * @param proImage the proImage to set
     */
    public void setProImage(String proImage) {
        this.proImage = proImage;
    }

    /**
     * @return String return the proDescription
     */
    public String getProDescription() {
        return proDescription;
    }

    /**
     * @param proDescription the proDescription to set
     */
    public void setProDescription(String proDescription) {
        this.proDescription = proDescription;
    }

    /**
     * @return int return the proQuantity
     */
    public int getProQuantity() {
        return proQuantity;
    }

    /**
     * @param proQuantity the proQuantity to set
     */
    public void setProQuantity(int proQuantity) {
        this.proQuantity = proQuantity;
    }

    /**
     * @return float return the proPrice
     */
    public float getProPrice() {
        return proPrice;
    }

    /**
     * @param proPrice the proPrice to set
     */
    public void setProPrice(float proPrice) {
        this.proPrice = proPrice;
    }

    /**
     * @return String return the proDate
     */
    public String getProDate() {
        return proDate;
    }

    /**
     * @param proDate the proDate to set
     */
    public void setProDate(String proDate) {
        this.proDate = proDate;
    }

    /**
     * @return int return the proCateId
     */
    public int getProCateId() {
        return proCateId;
    }

    /**
     * @param proCateId the proCateId to set
     */
    public void setProCateId(int proCateId) {
        this.proCateId = proCateId;
    }

}
