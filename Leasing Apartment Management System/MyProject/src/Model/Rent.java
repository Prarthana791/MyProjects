package Model;

public class Rent {
	private Integer aptid;
	private String leaseperiod;
	private Double utilities;
	private Double total;


public Rent()
{
}


public Integer getAptid() {
	return aptid;
}


public void setAptid(Integer aptid) {
	this.aptid = aptid;
}


public String getLeaseperiod() {
	return leaseperiod;
}


public void setLeaseperiod(String leaseperiod) {
	this.leaseperiod = leaseperiod;
}


public Double getUtilities() {
	return utilities;
}


public void setUtilities(Double utilities) {
	this.utilities = utilities;
}


public Double getTotal() {
	return total;
}


public void setTotal(Double total) {
	this.total = total;
}

}
