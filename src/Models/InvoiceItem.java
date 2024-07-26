package Models;

public class InvoiceItem {
    private String name;
    private float price;
    private int quantity;
    private int id;
    private float subTotal;
    
    public InvoiceItem(String name, float price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public InvoiceItem(String name, float price, int quantity, float subTotal) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    
    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}