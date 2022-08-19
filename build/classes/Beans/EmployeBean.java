package Beans;

import java.sql.Date;


public class EmployeBean {

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getCnicNO() {
        return cnicNO;
    }

    public void setCnicNO(String cnicNO) {
        this.cnicNO = cnicNO;
    }

    public String getMobilleNO() {
        return mobilleNO;
    }

    public void setMobilleNO(String mobilleNO) {
        this.mobilleNO = mobilleNO;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeType() {
        return employeType;
    }

    public void setEmployeType(String employeType) {
        this.employeType = employeType;
    }

   
    private int empId;
    private String cnicNO;
    private String mobilleNO;
    private String email;
    private String allowted_counter_no;

    public String getAllowted_counter_no() {
        return allowted_counter_no;
    }

    public void setAllowted_counter_no(String allowted_counter_no) {
        this.allowted_counter_no = allowted_counter_no;
    }
    private String employeType;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString(){
        return cnicNO;
    }
}
