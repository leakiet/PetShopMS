package Models;

public class ProductsCategory {
    private int cateId;
    private String cateName;

    public ProductsCategory(int cateId, String cateName) {
        this.cateId = cateId;
        this.cateName = cateName;
    }

    /**
     * @return int return the cateId
     */
    public int getCateId() {
        return cateId;
    }

    /**
     * @param cateId the cateId to set
     */
    public void setCateId(final int cateId) {
        this.cateId = cateId;
    }

    /**
     * @return String return the cateName
     */
    public String getCateName() {
        return cateName;
    }

    /**
     * @param cateName the cateName to set
     */
    public void setCateName(final String cateName) {
        this.cateName = cateName;
    }

    @Override
    public String toString() {
        return cateName;
    }

}
