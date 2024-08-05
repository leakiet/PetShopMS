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
    private String cusDateAccount;

    public Customers() {
    }

    public Customers(int cusId, String cusName, String cusPhone, String cusDob, String cusAddress,
            String cusGender, float cusTotalPaid, float cusPoint, String cusDateAccount) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.cusPhone = cusPhone;
        this.cusDob = cusDob;
        this.cusAddress = cusAddress;
        this.cusGender = cusGender;
        this.cusTotalPaid = cusTotalPaid;
        this.cusPoint = cusPoint;
        this.cusDateAccount = cusDateAccount;
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

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCusDob() {
        return cusDob;
    }

    public void setCusDob(String cusDob) {
        this.cusDob = cusDob;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getCusGender() {
        return cusGender;
    }

    public void setCusGender(String cusGender) {
        this.cusGender = cusGender;
    }

    public float getCusTotalPaid() {
        return cusTotalPaid;
    }

    public void setCusTotalPaid(final float cusTotalPaid) {
        this.cusTotalPaid = cusTotalPaid;
    }

    public float getCusPoint() {
        return cusPoint;
    }
    
    public void setCusPoint(final float cusPoint) {
        this.cusPoint = cusPoint;
    }

    public String getCusDateAccount() {
        return cusDateAccount;
    }

    public void setCusDateAccount(String cusDateAccount) {
        this.cusDateAccount = cusDateAccount;
    }

    
}
