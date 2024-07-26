package Models;

public class Receipt {
    private float graTotal;
    private String rName;
    private float rPrice;
    private int rQuantity;

    public Receipt(float graTotal, String rName, float rPrice, int rQuantity) {
        this.graTotal = graTotal;
        this.rName = rName;
        this.rPrice = rPrice;
        this.rQuantity = rQuantity;
    }

    public float getGraTotal() {
        return graTotal;
    }

    public String getRName() {
        return rName;
    }

    public float getRPrice() {
        return rPrice;
    }

    public int getRQuantity() {
        return rQuantity;
    }
}
