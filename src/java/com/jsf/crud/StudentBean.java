package com.jsf.crud;
 
import java.util.ArrayList;
 
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped; 

import com.jsf.crud.db.operations.DatabaseOperation;
 
@ManagedBean @RequestScoped
public class StudentBean {
 
    private int id;  
    private String name;
    public String gpa;
    private String email;  
    private String password;  
    private String gender;  
    private String department;
    private int semester;
    private int mark1;
    private int mark2;
    private int mark3;
 
    public ArrayList studentArrayList;
 
    public int getId() {
        return id;  
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getGender() {
        return gender;
    }
 
    public void setGender(String gender) {
        this.gender = gender;
    }
 
    public String getDepartment() {
        return department;
    }
    
    public void setGpa(String gpa) {
        this.gpa=gpa;
    }
    
    public String getGpa() {
        return gpa;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }  
    
    public int getSemester() {
        return semester;  
    }
 
    public void setSemester(int semester) {
        this.semester = semester;
    }
    
    public int getMark1() {
        return mark1;  
    }
 
    public void setMark1(int mark1) {
        this.mark1 = mark1;
    }
    
    public int getMark2() {
        return mark2;  
    }
 
    public void setMark2(int mark2) {
        this.mark2 = mark2;
    }
    
    public int getMark3() {
        return mark3;  
    }
 
    public void setMark3(int mark3) {
        this.mark3 = mark3;
    }
    
    @PostConstruct
    public void init() {
        studentArrayList = DatabaseOperation.readDetails();
    }
 
    public ArrayList studentsList() {
        return studentArrayList;
    }
     
    public String storeDetails(StudentBean newStudentObj) {
        return DatabaseOperation.storeDetails(newStudentObj);
    }
  
    public String deleteRecords(int studentId) {
        return DatabaseOperation.deleteRecords(studentId);
    }
}