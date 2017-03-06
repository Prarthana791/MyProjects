package Model;

import java.sql.Date;
import java.time.LocalDate;

public class Lease {
	private String leasePeriod;
	private Integer userId;
	private Integer aptId;
	private String bldgNo;
	private String aptNo;
	private String passport;
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getBldgNo() {
		return bldgNo;
	}
	public void setBldgNo(String bldgNo) {
		this.bldgNo = bldgNo;
	}
	public String getAptNo() {
		return aptNo;
	}
	public void setAptNo(String aptNo) {
		this.aptNo = aptNo;
	}
	private Integer adminId;
	private LocalDate moveInDate;
	private Integer leaseId;
	private Date bookDt;
	private String leaseStatus;
	public String getLeasePeriod() {
		return leasePeriod;
	}
	public void setLeasePeriod(String leasePeriod) {
		this.leasePeriod = leasePeriod;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getAptId() {
		return aptId;
	}
	public void setAptId(Integer aptId) {
		this.aptId = aptId;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public LocalDate getMoveInDate() {
		return moveInDate;
	}
	public void setMoveInDate(LocalDate moveInDate) {
		this.moveInDate = moveInDate;
	}
	public Integer getLeaseId() {
		return leaseId;
	}
	public void setLeaseId(Integer leaseId) {
		this.leaseId = leaseId;
	}
	public Date getBookDt() {
		return bookDt;
	}
	public void setBookDt(Date bookDt) {
		this.bookDt = bookDt;
	}
	public String getLeaseStatus() {
		return leaseStatus;
	}
	public void setLeaseStatus(String leaseStatus) {
		this.leaseStatus = leaseStatus;
	}

}
