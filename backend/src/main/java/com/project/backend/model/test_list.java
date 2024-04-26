package com.project.backend.model;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;
public class test_list {
    DatabaseConnection database=null;
    List<Test> l=new ArrayList<>();
    public test_list()
    {
        this.database=DatabaseConnection.getConnection();

    }
    public List<Test> get_tests()
    {
    try
    {
    
    Statement statement=this.database.connection.createStatement();
    ResultSet resultSet = statement.executeQuery("Select TestID,Subject from test");
    Test t;
    while (resultSet.next()) {
        t=new Test(resultSet.getString("testID"),resultSet.getString("Subject"));
        l.add(t);
    }
    }
    catch (Exception e) {
        e.printStackTrace();
        }

    return l;
    

}
}
