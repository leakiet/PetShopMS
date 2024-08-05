package Models;

public class Invoice {
    private int InvoicesID;
    private int CustomerID;
    private int EmployeeID;
    private String EmployeeName;
    private String InvoicesDate;
    private float Total;

    public Invoice() {
    }

    public Invoice(int InvoicesID, String InvoicesDate, float Total) {
        this.InvoicesID = InvoicesID;
        this.InvoicesDate = InvoicesDate;
        this.Total = Total;
    }
    
    

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String EmployeeName) {
        this.EmployeeName = EmployeeName;
    }

    
    public int getInvoicesID() {
        return InvoicesID;
    }

    public void setInvoicesID(int InvoicesID) {
        this.InvoicesID = InvoicesID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public String getInvoicesDate() {
        return InvoicesDate;
    }

    public void setInvoicesDate(String InvoicesDate) {
        this.InvoicesDate = InvoicesDate;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float Total) {
        this.Total = Total;
    }
    
    
}
