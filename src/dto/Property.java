package dto;

import java.util.Date;

public class Property {

	private int prId;
	private int ownerId;
	private String complexName;
	private String streetAddress;
	private String suburb;
	private String areaCode;
	private String province;
	private Date purchaseDate;
	private int firstWaterReading;
	private int firstElecReading;
	private int status;

	public Property() {
	}

	public Property(int prId, int ownerId, String complexName, String streetAddress, String suburb, String areaCode,
			String province, Date purchaseDate, int firstWaterReading, int firstElecReading, int status) {
		this.prId = prId;
		this.ownerId = ownerId;
		this.complexName = complexName;
		this.streetAddress = streetAddress;
		this.suburb = suburb;
		this.areaCode = areaCode;
		this.province = province;
		this.purchaseDate = purchaseDate;
		this.firstWaterReading = firstWaterReading;
		this.firstElecReading = firstElecReading;
		this.status = status;
	}

	public int getPrId() {
		return prId;
	}

	public void setPrId(int prId) {
		this.prId = prId;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getComplexName() {
		return complexName;
	}

	public void setComplexName(String complexName) {
		this.complexName = complexName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getFirstWaterReading() {
		return firstWaterReading;
	}

	public void setFirstWaterReading(int firstWaterReading) {
		this.firstWaterReading = firstWaterReading;
	}

	public int getFirstElecReading() {
		return firstElecReading;
	}

	public void setFirstElecReading(int firstElecReading) {
		this.firstElecReading = firstElecReading;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
