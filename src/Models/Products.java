package Models;

public class Products {
    private int proId;
    private String proName;
    private String proImg;
    private float proPrice;

    public Products() {
    }

    
    public Products(int proId, String proName, String proImg, float proPrice) {
        this.proId = proId;
        this.proName = proName;
        this.proImg = proImg;
        this.proPrice = proPrice;
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

    public String getProImg() {
        return proImg;
    }

    public void setProImg(String proImg) {
        this.proImg = proImg;
    }

    public float getProPrice() {
        return proPrice;
    }

    public void setProPrice(float proPrice) {
        this.proPrice = proPrice;
    }
    
}
