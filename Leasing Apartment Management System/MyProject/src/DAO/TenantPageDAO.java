package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.CreateUser;
import Model.Tenant;
//import DAO.Connection;

public class TenantPageDAO {
	private Connection connection;
	//Database connection parameters
    private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
    private String username = "fpuser";
    private String password = "510";
   
/*public Tenant display(Tenant t)
{
	
	//Tenant t = new Tenant();
//Get a connection
		try {
            connection = DriverManager.getConnection(url, username, password);
        } catch(SQLException e) {
            System.out.println("Error creating connection to database: " + e);
            System.exit(-1);
        }
		try
		{
	        Statement statement = connection.createStatement();
	        String query="SELECT * FROM pshah111_tenant WHERE user_id = "+t.getId();

	    	ResultSet resultset = statement.executeQuery(query);
	    	
	    //	profile.setId(Integer.parseInt(resultset.getString(1)));
	    	while(resultset.next())
	    	{
	    		profile.setId(Integer.parseInt(resultset.getString(1)));
	    		profile.setName(resultset.getString(2));
	    		profile.setPassword(resultset.getString(3));
	    		profile.setEmail(resultset.getString(4));
	    		profile.setContact(Double.parseDouble(resultset.getString(5)));
	    		//profile.setPassport(resultset.getString(2));
	    	}
	    	
	    	//profile.setPassword(resultset.getString(3));
	    	//profile.setEmail(resultset.getString(4));
	    	//profile.setContact(Double.parseDouble(resultset.getString(5)));
		}
		catch(SQLException e)
		{
			System.out.println("error in fetching data");
		}
		try {
            connection.close();
            connection = null;
        } catch(SQLException e) {
            System.out.println("Error closing connection: " + e);
        }
		return profile;
}*/}
