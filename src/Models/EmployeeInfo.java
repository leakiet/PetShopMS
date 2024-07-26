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
    private String empPhone;
    private String empAddress;
    private String empEmail;

    public EmployeeInfo() {
    }

    public EmployeeInfo(int empId, String empName, String empGender, String empDOB, String empUser, String empJoinDate, 
            boolean empIsActive, String empPhone, String empEmail, String empAddress,String empPicture ) {
        this.empId = empId;
        this.empName = empName;
        this.empGender = empGender;
        this.empDOB = empDOB;
        this.empUser = empUser;
        this.empJoinDate = empJoinDate;
        this.empIsActive = empIsActive;
        this.empPhone = empPhone;
        this.empAddress = empAddress;
        this.empEmail = empEmail;
        this.empPicture = empPicture;
    }
    
    
    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
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

    public boolean getEmpIsActive() {
        return empIsActive;
    }

    public void setEmpIsActive(boolean empIsActive) {
        this.empIsActive = empIsActive;
    }

}
