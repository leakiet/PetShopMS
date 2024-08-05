package Models;

public class ServiceList {
    private String serviceListName ;
    private float serviceListPrice ;
    private float weight;

    public ServiceList(String serviceListName, float serviceListPrice, float weight) {
        this.serviceListName = serviceListName;
        this.serviceListPrice = serviceListPrice;
        this.weight = weight;
    }

    
    public ServiceList() {
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    
    public String getServiceListName() {
        return serviceListName;
    }

    public void setServiceListName(String serviceListName) {
        this.serviceListName = serviceListName;
    }

    public float getServiceListPrice() {
        return serviceListPrice;
    }

    public void setServiceListPrice(float serviceListPrice) {
        this.serviceListPrice = serviceListPrice;
    }
    
    
}
