package Model;

import java.util.ArrayList;


public class Apartment implements Calculate {
	private Integer apt_id;
	private String building_no;
	private String apt_no;
	private String rooms;
	private String availability_status;
	private String base_rent;
	ArrayList<Rent> r = new ArrayList<Rent>();
public Apartment()
{
	
}
public Double calculateRent(Double rent,Double utilities)
{
	return rent+utilities;
}

public Integer getApt_id() {
		return apt_id;
	}


	public void setApt_id(Integer apt_id) {
		this.apt_id = apt_id;
	}


	public String getBuilding_no() {
		return building_no;
	}


	public void setBuilding_no(String building_no) {
		this.building_no = building_no;
	}


	public String getApt_no() {
		return apt_no;
	}


	public void setApt_no(String apt_no) {
		this.apt_no = apt_no;
	}


	public String getRooms() {
		return rooms;
	}


	public void setRooms(String rooms) {
		this.rooms = rooms;
	}


	public String getAvailability_status() {
		return availability_status;
	}


	public void setAvailability_status(String availability_status) {
		this.availability_status = availability_status;
	}


	public String getBase_rent() {
		return base_rent;
	}


	public void setBase_rent(String base_rent) {
		this.base_rent = base_rent;
	}


}