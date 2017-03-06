package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import Model.Admin;
import Model.CreateUser;
import Model.Tenant;

public class UserDAO {
	Connection1 conn = new Connection1();
	 public static Tenant profile = new Tenant();
	 public static Admin admin = new Admin();
	//public static int i=0;

	// Connection object
		public Tenant create(Tenant user) {

		conn.getConnection();
		// Get a connection
		
		String query = "INSERT INTO pshah111_tenant(user_name,password,emailid,contact_no,passport_no,status) VALUES (?,?,?,?,null,'visitor');";
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = conn.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			statement.setString(1,user.getName());
			statement.setString(2,user.getPassword());
			statement.setString(3,user.getEmail());
			statement.setDouble(4,user.getContact());

			// Execute the insert
			statement.executeUpdate();
			// To get the primary key (id) of the newly inserted record
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				// Set the id field of the database to the model
				 user.setId(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			user = null;
			System.out.println("Error Creating User: " + e);
		}
		conn.closeConnection();
		// Close the connection to the database
		
		return user;
	}

	public int login(CreateUser tenant) {
		int i = 0;
		int j =0;

		conn.getConnection();
		try {
			Statement statement = conn.connection.createStatement();
			String query = "SELECT user_id,user_name,password,emailid,contact_no,passport_no FROM pshah111_tenant;";
			ResultSet resultset = statement.executeQuery(query);
			while (resultset.next()) {
				if ((Integer.parseInt(resultset.getString(1)) == tenant.getId()
						&& (resultset.getString(3)).equals(tenant.getPassword()))) {
					i = 1;
					System.out.println(i);
					profile.setId(tenant.getId());
					System.out.println("tenNT NU ID"+profile.getId());
					profile.setName(resultset.getString(2));
					System.out.println("tenant nu name"+profile.getName());
					profile.setEmail(resultset.getString(4));
					//profile.setPassword(tenant.getPassword());
					profile.setContact(Double.parseDouble(resultset.getString(5)));
					profile.setPassport(resultset.getString(6));
					System.out.println("after profile setid"+i);
					break;
					
					//System.out.println("DAO i is"+i);

				} else
				{
					i = 0;
					System.out.println("tenant ma chu");
				}
				
			}
			System.out.println("if ni pehla"+i);
			if(i==0){
				Statement statement1 = conn.connection.createStatement();
				String query1 = "SELECT user_ID,user_name,password,emailid,contact_no,joining_dt FROM pshah111_admin;";
				ResultSet resultset1 = statement1.executeQuery(query1);
				while (resultset1.next()) {
					if ((Integer.parseInt(resultset1.getString(1)) == tenant.getId()
							&& (resultset1.getString(3)).equals(tenant.getPassword()))) {
						i = 2;
						System.out.println("admin no pwd"+tenant.getPassword());
						admin.setId(tenant.getId());
						admin.setName(resultset1.getString(2));
						admin.setEmail(resultset1.getString(4));
						admin.setContact(Double.parseDouble(resultset1.getString(5)));
						System.out.println("contact is"+resultset1.getString(5));
						//admin.setDate(SimpleDateFormat.format(resultset.getString(6)));
						break;
						
						//System.out.println("DAO i is"+i);


				
			}
		
			else
			{
				i=0;
				System.out.println("admin ma chu");
			}}}
		}
			catch (SQLException e) {
			tenant = null;
			System.out.println("Error showing tenat page: " + e);
		}

		conn.closeConnection();
		return i;
		
	}
	public Tenant Update(Tenant tenant)
	{
		conn.getConnection();
		String query="UPDATE pshah111_tenant set user_name=?,emailid=?,contact_no=?,passport_no=? WHERE user_id = "+profile.getId()+";"; 
		try {
			PreparedStatement statement = conn.connection.prepareStatement(query);
			System.out.println("update query id is"+profile.getId());
			System.out.println("update query name is"+tenant.getName());
			statement.setString(1,tenant.getName());
			statement.setString(2,tenant.getEmail());
			statement.setDouble(3,tenant.getContact());
			statement.setString(4,tenant.getPassport());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tenant;
	}
	public Admin UpdateAdmin(Admin a)
	{
		conn.getConnection();
		String query="UPDATE pshah111_admin set user_name=?,emailid=?,contact_no=? WHERE user_ID = "+admin.getId()+";"; 
		try {
			PreparedStatement statement = conn.connection.prepareStatement(query);
			System.out.println("update query id is"+profile.getId());
			System.out.println("update query name is"+a.getName());
			statement.setString(1,a.getName());
			statement.setString(2,a.getEmail());
			statement.setDouble(3,a.getContact());
						statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

}
