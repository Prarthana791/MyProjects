package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Apartment;
import Model.Rent;

public class AdminApartmentDAO {
	Connection1 conn = new Connection1();
	public Apartment create(Apartment a)
	{
		
		conn.getConnection();
		String query = "INSERT INTO pshah111_apartment(building_no,apt_no,rooms,rent,availability_status)"
				+ "values(?,?,?,?,'available');";
		try (PreparedStatement statement = conn.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS))
		{
			statement.setString(1,a.getBuilding_no());
			statement.setString(2,a.getApt_no());
			statement.setString(3,a.getRooms());
			statement.setString(4,a.getBase_rent());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next())
			{
				a.setApt_id(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			a=null;
			e.printStackTrace();
			System.out.println("Error creating apartment");
		}
		conn.closeConnection();
		return a;
	}
	public void Rent(Rent r)
	{
		conn.getConnection();
		String query = "INSERT INTO pshah111_rent(apt_id,lease_period,Utilities,total_rent) values "
				+ "(?,?,?,?);";
		try
		{
			PreparedStatement statement = conn.connection.prepareStatement(query);
			statement.setInt(1,r.getAptid());
			statement.setString(2, r.getLeaseperiod());
			statement.setDouble(3,r.getUtilities());
			statement.setDouble(4, r.getTotal());
			statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		conn.closeConnection();
	}
	public void Update(Apartment a)
	{
		conn.getConnection();
		String query="UPDATE pshah111_apartment set building_no=?,apt_no=?,rooms=?,availability_status=?,rent=? WHERE apt_id=?;"; 
		try {
			PreparedStatement statement = conn.connection.prepareStatement(query);
			statement.setString(1,a.getBuilding_no());
			statement.setString(2,a.getApt_no());
			statement.setString(3, a.getRooms());
			System.out.println("rooms in update DAO is"+a.getRooms());
			statement.setString(4,a.getAvailability_status());
			statement.setString(5,a.getBase_rent());
			statement.setInt(6, a.getApt_id());
			System.out.println("apt id in DAO is"+a.getApt_id());
			statement.executeUpdate();
		}
		catch(SQLException s)
		{
			s.printStackTrace();
		}
		
conn.closeConnection();
	}
	public void Delete(Apartment a)
	{
		conn.getConnection();
		String query ="DELETE from pshah111_rent where apt_id="+a.getApt_id();
		try {
			PreparedStatement statement = conn.connection.prepareStatement(query);
			statement.executeUpdate();
		}
		catch(SQLException s)
		{
			s.printStackTrace();
		}
		String query1 ="DELETE from pshah111_apartment where apt_id="+a.getApt_id();
		try {
			PreparedStatement statement1 = conn.connection.prepareStatement(query1);
			statement1.executeUpdate();
		}
		catch(SQLException s)
		{
			s.printStackTrace();
		}
	conn.closeConnection();
	}
	
}
