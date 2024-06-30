package Models;

public class EmployeeInfo {
    private int empId;
    private String empName;  
    private String empGender;
    private String empDOB;
    private String empUser;
    private String empPassword;
    private String empQuestion;
    private String empAnwser;
    private String empPicture;
    private String empJoinDate;
    private boolean empIsActive;

    public EmployeeInfo() {
    }

    public EmployeeInfo(int empId, String empName, String empGender, String empDOB, String empUser, String empPassword, String empQuestion, String empAnwser, String empPicture, String empJoinDate) {
        this.empId = empId;
        this.empName = empName;
        this.empGender = empGender;
        this.empDOB = empDOB;
        this.empUser = empUser;
        this.empPassword = empPassword;
        this.empQuestion = empQuestion;
        this.empAnwser = empAnwser;
        this.empPicture = empPicture;
        this.empJoinDate = empJoinDate;
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

    public String getEmpGender() {
        return empGender;
    }

    public void setEmpGender(String empGender) {
        this.empGender = empGender;
    }

    public String getEmpDOB() {
        return empDOB;
    }

    public void setEmpDOB(String empDOB) {
        this.empDOB = empDOB;
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

    public String getEmpPicture() {
        return empPicture;
    }

    public void setEmpPicture(String empPicture) {
        this.empPicture = empPicture;
    }

    public String getEmpJoinDate() {
        return empJoinDate;
    }

    public void setEmpJoinDate(String empJoinDate) {
        this.empJoinDate = empJoinDate;
    }

    public boolean isEmpIsActive() {
        return empIsActive;
    }

    public void setEmpIsActive(boolean empIsActive) {
        this.empIsActive = empIsActive;
    }

   
}
