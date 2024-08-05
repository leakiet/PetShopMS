package Models;

public class Service {

    private int serId;
    private String serName;
    private String serCusName;
    private String serCusPhone;
    private String serPetName;
    private float serPetWeight;
    private String serScheduleDate;
    private String serScheduleTime;
    private float serPrice;
    private float maxWeight;
    private String serStatus;
    private String serDate;

    public Service() {
    }

    public Service(int serId, String serName, String serCusName, String serCusPhone, String serPetName, float serPetWeight, String serScheduleDate, String serScheduleTime, float serPrice, float maxWeight, String serStatus, String serDate) {
        this.serId = serId;
        this.serName = serName;
        this.serCusName = serCusName;
        this.serCusPhone = serCusPhone;
        this.serPetName = serPetName;
        this.serPetWeight = serPetWeight;
        this.serScheduleDate = serScheduleDate;
        this.serScheduleTime = serScheduleTime;
        this.serPrice = serPrice;
        this.maxWeight = maxWeight;
        this.serStatus = serStatus;
        this.serDate = serDate;
    }
    
    public Service(String serName, String serCusName, String serCusPhone, String serPetName, float serPetWeight, String serScheduleDate, String serScheduleTime, float serPrice, float maxWeight, String serStatus, String serDate) {
        this.serName = serName;
        this.serCusName = serCusName;
        this.serCusPhone = serCusPhone;
        this.serPetName = serPetName;
        this.serPetWeight = serPetWeight;
        this.serScheduleDate = serScheduleDate;
        this.serScheduleTime = serScheduleTime;
        this.serPrice = serPrice;
        this.maxWeight = maxWeight;
        this.serStatus = serStatus;
        this.serDate = serDate;
    }
    
    public Service(String serName, String serCusName, String serCusPhone, String serPetName, float serPetWeight, String serScheduleDate, String serScheduleTime, float serPrice, float maxWeight, String serStatus) {
        this.serName = serName;
        this.serCusName = serCusName;
        this.serCusPhone = serCusPhone;
        this.serPetName = serPetName;
        this.serPetWeight = serPetWeight;
        this.serScheduleDate = serScheduleDate;
        this.serScheduleTime = serScheduleTime;
        this.serPrice = serPrice;
        this.maxWeight = maxWeight;
        this.serStatus = serStatus;
    }
    
    public Service(int serId,String serName, String serCusName, String serCusPhone, String serPetName, float serPetWeight, String serScheduleDate, String serScheduleTime, float serPrice, float maxWeight, String serStatus) {
        this.serId = serId;
        this.serName = serName;
        this.serCusName = serCusName;
        this.serCusPhone = serCusPhone;
        this.serPetName = serPetName;
        this.serPetWeight = serPetWeight;
        this.serScheduleDate = serScheduleDate;
        this.serScheduleTime = serScheduleTime;
        this.serPrice = serPrice;
        this.maxWeight = maxWeight;
        this.serStatus = serStatus;
    }

    public float getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
    }

    
    public int getSerId() {
        return serId;
    }

    public void setSerId(int serId) {
        this.serId = serId;
    }

    public String getSerName() {
        return serName;
    }

    public void setSerName(String serName) {
        this.serName = serName;
    }

    public String getSerCusName() {
        return serCusName;
    }

    public void setSerCusName(String serCusName) {
        this.serCusName = serCusName;
    }

    public String getSerCusPhone() {
        return serCusPhone;
    }

    public void setSerCusPhone(String serCusPhone) {
        this.serCusPhone = serCusPhone;
    }

    public String getSerPetName() {
        return serPetName;
    }

    public void setSerPetName(String serPetName) {
        this.serPetName = serPetName;
    }

    public float getSerPetWeight() {
        return serPetWeight;
    }

    public void setSerPetWeight(float serPetWeight) {
        this.serPetWeight = serPetWeight;
    }

    public String getSerScheduleDate() {
        return serScheduleDate;
    }

    public void setSerScheduleDate(String serScheduleDate) {
        this.serScheduleDate = serScheduleDate;
    }

    public String getSerScheduleTime() {
        return serScheduleTime;
    }

    public void setSerScheduleTime(String serScheduleTime) {
        this.serScheduleTime = serScheduleTime;
    }

    public float getSerPrice() {
        return serPrice;
    }

    public void setSerPrice(float serPrice) {
        this.serPrice = serPrice;
    }

    public String getSerStatus() {
        return serStatus;
    }

    public void setSerStatus(String serStatus) {
        this.serStatus = serStatus;
    }

    public String getSerDate() {
        return serDate;
    }

    public void setSerDate(String serDate) {
        this.serDate = serDate;
    }
    
    
}
