package Model;

import java.sql.Date;

public class CreateUser {
	private Integer user_id;
	private String user_name;
	private String password;
	private String email_id;
	private Double contact_no;
	
	public CreateUser()
	{}
	public CreateUser(Integer user_id,String user_name, String password, String email_id,Double contact_no)
	{
		this.user_id = user_id;
		this.user_name = user_name;
		this.password = password;
		this.email_id = email_id;
		this.contact_no = contact_no;
		
	}
	public Integer getId() {
		return user_id;
	}

	public void setId(Integer user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return user_name;
	}

	public void setName(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email_id;
	}

	public void setEmail(String email_id) {
		this.email_id = email_id;
	}
	public Double getContact() {
		return contact_no;
	}

	public void setContact(Double contact_no) {
		this.contact_no = contact_no;
	}
	
}


