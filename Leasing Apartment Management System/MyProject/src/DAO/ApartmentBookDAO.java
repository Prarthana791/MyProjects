package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import Model.Lease;

public class ApartmentBookDAO {
	Integer aid;
	public void bookApartment(Lease l)
	{
		System.out.println("bldg no in DAO for book is"+l.getBldgNo());
		System.out.println("apt no in DAO for book is"+l.getAptNo());
		Connection1 conn= new Connection1();
		conn.getConnection();
		//Statement st;
		try {
			Statement st = conn.connection.createStatement();
		 
		String query ="SELECT apt_id from pshah111_apartment where building_no='"+l.getBldgNo()+"' and apt_no='"+l.getAptNo()+"';";
		ResultSet rs = st.executeQuery(query);
		while(rs.next())
		{
			System.out.println("apt_id in DAO for lease is "+rs.getString(1));
			aid=Integer.parseInt(rs.getString(1));
		}
				}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			String query1="INSERT into pshah111_lease(user_id,apt_id,admin_id,movein_date,lease_period,booking_date,lease_status)"
					+ "values(?,?,null,?,?,?,'pending')";
			PreparedStatement statement1 = conn.connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
			statement1.setInt(1,UserDAO.profile.getId());
			statement1.setInt(2,aid);
			statement1.setDate(3,java.sql.Date.valueOf(l.getMoveInDate()));
			statement1.setString(4,l.getLeasePeriod());
			Calendar calendar = Calendar.getInstance();
		    java.sql.Date currdt = new java.sql.Date(calendar.getTime().getTime());
			statement1.setDate(5,currdt);
			statement1.executeUpdate();
			ResultSet rs1= statement1.getGeneratedKeys();
			if(rs1.next())
			{
				l.setLeaseId(rs1.getInt(1));
			}

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			String query2="UPDATE pshah111_tenant set passport_no = '"+l.getPassport()+"'where user_id="+UserDAO.profile.getId()+";";
			PreparedStatement statement = conn.connection.prepareStatement(query2);
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			
		}
		try
		{
		String query3="UPDATE pshah111_apartment set availability_status = 'pending approval' where apt_id="+aid+";";	
		PreparedStatement statement = conn.connection.prepareStatement(query3);
		statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		conn.closeConnection();
	}

}
