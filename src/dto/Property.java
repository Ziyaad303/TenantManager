package dto;

import java.util.Date;
import java.util.List;

public class Property {

	private int propertyId;
	private int personId;
	private String complexName;
	private String streetAddress;
	private String suburb;
	private String areaCode;
	private String province;
	private Date purchaseDate;
	private int firstWaterReading;
	private int firstElecReading;
	private int status;
	private List<WaterReadings> waterReadings;
	private List<ElectricityReadings> electricityReadings;

	public Property() {
	}

	public Property(int propertyId, int personId, String complexName, String streetAddress, String suburb,
			String areaCode, String province, Date purchaseDate, int firstWaterReading, int firstElecReading,
			int status, List<WaterReadings> waterReadings, List<ElectricityReadings> electricityReadings) {
		this.propertyId = propertyId;
		this.personId = personId;
		this.complexName = complexName;
		this.streetAddress = streetAddress;
		this.suburb = suburb;
		this.areaCode = areaCode;
		this.province = province;
		this.purchaseDate = purchaseDate;
		this.firstWaterReading = firstWaterReading;
		this.firstElecReading = firstElecReading;
		this.status = status;
		this.waterReadings = waterReadings;
		this.electricityReadings = electricityReadings;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
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

	public List<WaterReadings> getWaterReadings() {
		return waterReadings;
	}

	public void setWaterReadings(List<WaterReadings> waterReadings) {
		this.waterReadings = waterReadings;
	}

	public List<ElectricityReadings> getElectricityReadings() {
		return electricityReadings;
	}

	public void setElectricityReadings(List<ElectricityReadings> electricityReadings) {
		this.electricityReadings = electricityReadings;
	}

}
