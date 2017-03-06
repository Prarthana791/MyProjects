package DAO;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
public class Connection1 {
	//Connection object
			public Connection connection;
			//Database connection parameters
		    private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
		    private String username = "fpuser";
		    private String password = "510";

public void getConnection()
{
	//Get a connection
	try {
        connection = (Connection) DriverManager.getConnection(url, username, password);
    } catch(SQLException e) {
        System.out.println("Error creating connection to database: " + e);
        System.exit(-1);
    }
}
public void closeConnection()
{
	try {
          ((java.sql.Connection) connection).close();
        connection = null;
    } catch(SQLException e) {
        System.out.println("Error closing connection: " + e);
    }
}
	
}
