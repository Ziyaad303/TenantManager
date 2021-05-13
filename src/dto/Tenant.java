package dto;

import java.util.Date;

public class Tenant {

	private int tenantId;
	private int personId;
	private int propertyId;
	private Date startDate;
	private int leasePeriod;
	private String documents;

	public Tenant() {
		
	}

	public Tenant(int tenantId, int personId, int propertyId, Date startDate, int leasePeriod, String documents) {
		this.tenantId = tenantId;
		this.personId = personId;
		this.propertyId = propertyId;
		this.startDate = startDate;
		this.leasePeriod = leasePeriod;
		this.documents = documents;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getLeasePeriod() {
		return leasePeriod;
	}

	public void setLeasePeriod(int leasePeriod) {
		this.leasePeriod = leasePeriod;
	}

	public String getDocuments() {
		return documents;
	}

	public void setDocuments(String documents) {
		this.documents = documents;
	}

}
