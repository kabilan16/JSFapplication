package com.jsf.crud.db.operations;
import java.sql.*;
import java.util.ArrayList;

import com.jsf.crud.StudentBean;
public class DatabaseOperation 
{
    public static Statement stmt;
    public static Connection conn;
    public static ResultSet rs;
    public static PreparedStatement ps;
    public static Connection getConnection()
    {  
        try
        {  
            Class.forName("com.mysql.jdbc.Driver");     
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kabilan","root","2019115044");  
        } 
        catch(Exception e1) 
        {  
            e1.printStackTrace();
        }  
        return conn;
    }
    
    public static String gpaval(int m1,int m2,int m3)
    {
        int total=m1+m2+m3;
        float avg;
        avg=(float) (total/3.0);
        if(avg>90)
            return "O";
        else if(avg>80 && avg<=89)
            return "A+";
        else if(avg>70 && avg<=79)
            return "A";
        else if(avg>60 && avg<=69)
            return "B+";
        else if(avg>50 && avg<=59)
            return "B";
        else if(avg>40 && avg<=49)
            return "C";
        else 
            return "F";
    }
    public static ArrayList readDetails() {
        ArrayList studentsList = new ArrayList();  

        String res;
        int m1,m2,m3;
        try 
        {
            stmt = getConnection().createStatement();    
            rs = stmt.executeQuery("select * from gradecalculator");    
            while(rs.next()) 
            {  
                StudentBean stuObj = new StudentBean(); 
                stuObj.setId(rs.getInt("student_id"));  
                stuObj.setName(rs.getString("name"));  
                stuObj.setEmail(rs.getString("email"));  
                stuObj.setPassword(rs.getString("s_password"));  
                stuObj.setGender(rs.getString("gender"));  
                stuObj.setDepartment(rs.getString("department"));
                stuObj.setSemester(Integer.parseInt(rs.getString("semester")));
                m1=Integer.parseInt(rs.getString("mark1"));
                m2=Integer.parseInt(rs.getString("mark2"));
                m3=Integer.parseInt(rs.getString("mark3"));
                stuObj.setMark1(m1);
                stuObj.setMark2(m2);
                stuObj.setMark3(m3);
                stuObj.setGpa(rs.getString("gpa"));
                studentsList.add(stuObj);  
            }   
            conn.close();
        } catch(Exception e2) {
            e2.printStackTrace();
        } 
        return studentsList;
    }
 
    public static String storeDetails(StudentBean newStudentObj) {
        int saveResult = 0,m1,m2,m3;
        String res;
        String navigationResult = "";
        try {      
            ps = getConnection().prepareStatement("insert into gradecalculator (name, email, s_password, gender, department, semester,mark1,mark2,mark3,gpa) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");         
            ps.setString(1, newStudentObj.getName());
            ps.setString(2, newStudentObj.getEmail());
            ps.setString(3, newStudentObj.getPassword());
            ps.setString(4, newStudentObj.getGender());
            ps.setString(5, newStudentObj.getDepartment());
            ps.setInt(6, newStudentObj.getSemester());
            ps.setInt(7, newStudentObj.getMark1());
            ps.setInt(8, newStudentObj.getMark2());
            ps.setInt(9, newStudentObj.getMark3());
            m1=newStudentObj.getMark1();
            m2=newStudentObj.getMark2();
            m3=newStudentObj.getMark3();
            res=gpaval(m1,m2,m3);
            ps.setString(10,res);
            saveResult = ps.executeUpdate();
            conn.close();
        } catch(Exception e3) {
            e3.printStackTrace();
        }
        if(saveResult !=0) {
            navigationResult = "studentDetails.xhtml?faces-redirect=true";
        } else {
            navigationResult = "createStudent.xhtml?faces-redirect=true";
        }
        return navigationResult;
    }
  
    public static String deleteRecords(int studentId){
        System.out.println("deleteStudentRecordInDB() : Student Id: " + studentId);
        try {
            ps = getConnection().prepareStatement("delete from gradecalculator where student_id = "+studentId);  
            ps.executeUpdate();  
            conn.close();
        } catch(Exception e4){
            e4.printStackTrace();
        }
        return "/studentDetails.xhtml?faces-redirect=true";
    }
}