package Model;

public class Tenant extends CreateUser {
	private String passport;
	private String status;
	public Tenant()
	{
		
	}
	
	/*public Tenant(Integer user_id,String user_name, String password, String email_id,Double contact_no, String passport, String status)
	{
		/*super(user_id,user_name,password,email_id,contact_no);
		this.passport = passport;
		this.status = status;*/		
	
	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



}
