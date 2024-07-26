package Models;

public class ProductsCategory {
    private int cateId;
    private String cateName;

    public ProductsCategory(int cateId, String cateName) {
        this.cateId = cateId;
        this.cateName = cateName;
    }

    public ProductsCategory(String cateName) {
        this.cateName = cateName;
    }
 
    public int getCateId() {
        return cateId;
    }

    public void setCateId(final int cateId) {
        this.cateId = cateId;
    }

  
    public String getCateName() {
        return cateName;
    }

    public void setCateName(final String cateName) {
        this.cateName = cateName;
    }

    @Override
    public String toString() {
        return cateName;
    }

}
