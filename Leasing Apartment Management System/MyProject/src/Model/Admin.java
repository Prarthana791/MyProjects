package Model;

import java.sql.Date;

public class Admin extends CreateUser {
	Date joining_dt;
	/*Admin(Integer user_id,String user_name, String password, String email_id,Double contact_no,Date joining_dt)
	{
	super(user_id,user_name,password,email_id,contact_no);
	this.joining_dt = joining_dt;
	}*/
	public Date getDate() {
		return joining_dt;
	}

	public void setDate(Date joining_dt) {
		this.joining_dt = joining_dt;
	}

}
