package Models;

public class InvoiceProducts {
    private int invoicesId;
    private int productId;
    private int quantity;
    private float price;
    private String invoiceDate;

    /**
     * @return int return the invoicesId
     */
    public int getInvoicesId() {
        return invoicesId;
    }

    /**
     * @param invoicesId the invoicesId to set
     */
    public void setInvoicesId(int invoicesId) {
        this.invoicesId = invoicesId;
    }

    /**
     * @return int return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @return int return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return float return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return String return the invoiceDate
     */
    public String getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * @param invoiceDate the invoiceDate to set
     */
    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

}
