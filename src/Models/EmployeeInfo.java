package Models;

public class EmployeeInfo {
    private int empId;
    private String empName;
    private String empUser;
    private String empPassword;
    private String empQuestion;
    private String empAnwser;
    private boolean empIsActive;

    public EmployeeInfo() {
    }

    public EmployeeInfo(int empId, String empName, String empUser, String empPassword, String empQuestion, String empAnwser, boolean empIsActive) {
        this.empId = empId;
        this.empName = empName;
        this.empUser = empUser;
        this.empPassword = empPassword;
        this.empQuestion = empQuestion;
        this.empAnwser = empAnwser;
        this.empIsActive = empIsActive;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpUser() {
        return empUser;
    }

    public void setEmpUser(String empUser) {
        this.empUser = empUser;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public String getEmpQuestion() {
        return empQuestion;
    }

    public void setEmpQuestion(String empQuestion) {
        this.empQuestion = empQuestion;
    }

    public String getEmpAnwser() {
        return empAnwser;
    }

    public void setEmpAnwser(String empAnwser) {
        this.empAnwser = empAnwser;
    }

    public boolean isEmpIsActive() {
        return empIsActive;
    }

    public void setEmpIsActive(boolean empIsActive) {
        this.empIsActive = empIsActive;
    }
    
    
}
