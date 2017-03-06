package DAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Apartment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UpdateApartmentDAO {
	Connection1 conn = new Connection1();
	ObservableList<Apartment> apt= FXCollections.observableArrayList();
			//FXCollections.observableArrayList();
	public ObservableList<Apartment> viewApt()
	{
		conn.getConnection();
		try {
			Statement st = conn.connection.createStatement();
			String query="select * from pshah111_apartment;";
			ResultSet rs = st.executeQuery(query);
			int i=0;
			while(rs.next())
			{
				Apartment a= new Apartment();
				a.setApt_id(Integer.parseInt(rs.getString(5)));
				System.out.println("apt id is"+rs.getString(1));
				a.setBuilding_no(rs.getString(1));
				a.setApt_no(rs.getString(2));
				a.setRooms(rs.getString(3));
				a.setBase_rent(rs.getString(6));
				a.setAvailability_status(rs.getString(4));
				System.out.println("apt is"+a);
				apt.add(i,a);
				i++;
				//add(a.getAptId(),a);
				System.out.println("apt in dao is"+apt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.closeConnection();
		return apt;
		
	}

}
