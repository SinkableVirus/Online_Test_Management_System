package com.project.backend.model;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;


public class leaderboard {
    DatabaseConnection database=null;
    List <Scoredetails> l=new ArrayList<>();
    public leaderboard()
    {
        this.database=DatabaseConnection.getConnection();

    }
    public List<Scoredetails> jointable(String TestID)
    {
        try
        {
        Statement statement=this.database.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select s.name,s.SRN,MarksSecured from student s JOIN student_test t on t.SRN=s.SRN where t.TestID='"+TestID+"' order by MarksSecured DESC");
        Scoredetails s;
        while (resultSet.next())
        {
            s=new Scoredetails();
            s.setName(resultSet.getString("name"));
            s.setSRN(resultSet.getString("SRN"));
            s.setScore(resultSet.getInt("MarksSecured"));
            l.add(s);

        }
        }
        catch (Exception e) {
        e.printStackTrace();
        }
        return l;
    } 
}
