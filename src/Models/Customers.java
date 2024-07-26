package Models;

import java.util.Date;

public class Customers {

    private int cusId;
    private String cusName;
    private String cusPhone;
    private String cusDob;
    private String cusAddress;
    private String cusGender;
    private float cusTotalPaid;
    private float cusPoint;

    public Customers() {
    }

    public Customers(int cusId, String cusName, String cusPhone, String cusDob, String cusAddress,
            String cusGender, float cusTotalPaid, float cusPoint) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.cusPhone = cusPhone;
        this.cusDob = cusDob;
        this.cusAddress = cusAddress;
        this.cusGender = cusGender;
        this.cusTotalPaid = cusTotalPaid;
        this.cusPoint = cusPoint;
    }

    public Customers(String cusName, String cusPhone, String cusDob, String cusAddress,
            String cusGender, float cusTotalPaid, float cusPoint) {
        this.cusName = cusName;
        this.cusPhone = cusPhone;
        this.cusDob = cusDob;
        this.cusAddress = cusAddress;
        this.cusGender = cusGender;
        this.cusTotalPaid = cusTotalPaid;
        this.cusPoint = cusPoint;
    }

    public Customers(int cusId, String cusName, String cusPhone, String cusDob, String cusAddress,
            String cusGender) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.cusPhone = cusPhone;
        this.cusDob = cusDob;
        this.cusAddress = cusAddress;
        this.cusGender = cusGender;
    }

    /**
     * @return int return the cusId
     */
    public int getCusId() {
        return cusId;
    }

    /**
     * @param cusId the cusId to set
     */
    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    /**
     * @return String return the cusName
     */
    public String getCusName() {
        return cusName;
    }

    /**
     * @param cusName the cusName to set
     */
    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    /**
     * @return String return the cusPhone
     */
    public String getCusPhone() {
        return cusPhone;
    }

    /**
     * @param cusPhone the cusPhone to set
     */
    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    /**
     * @return String return the cusDob
     */
    public String getCusDob() {
        return cusDob;
    }

    /**
     * @param cusDob the cusDob to set
     */
    public void setCusDob(String cusDob) {
        this.cusDob = cusDob;
    }

    /**
     * @return String return the cusAddress
     */
    public String getCusAddress() {
        return cusAddress;
    }

    /**
     * @param cusAddress the cusAddress to set
     */
    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    /**
     * @return String return the cusGender
     */
    public String getCusGender() {
        return cusGender;
    }

    /**
     * @param cusGender the cusGender to set
     */
    public void setCusGender(String cusGender) {
        this.cusGender = cusGender;
    }

    /**
     * @return float return the cusTotalPaid
     */
    public float getCusTotalPaid() {
        return cusTotalPaid;
    }

    /**
     * @param cusTotalPaid the cusTotalPaid to set
     */
    public void setCusTotalPaid(final float cusTotalPaid) {
        this.cusTotalPaid = cusTotalPaid;
    }

    /**
     * @return float return the cusPoint
     */
    public float getCusPoint() {
        return cusPoint;
    }

    /**
     * @param cusPoint the cusPoint to set
     */
    public void setCusPoint(final float cusPoint) {
        this.cusPoint = cusPoint;
    }

}
