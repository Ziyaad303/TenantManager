package dto;

import java.util.Date;

public class ElectricityReadings {

	private int electricityReadingsId;
	private int propertyId;
	private Date readingDate;
	private int unitsUsed;
	
	public ElectricityReadings() {
	}

	public ElectricityReadings(int electricityReadingsId, int propertyId, Date readingDate, int unitsUsed) {
		this.electricityReadingsId = electricityReadingsId;
		this.propertyId = propertyId;
		this.readingDate = readingDate;
		this.unitsUsed = unitsUsed;
	}

	public int getElectricityReadingsId() {
		return electricityReadingsId;
	}

	public void setElectricityReadingsId(int electricityReadingsId) {
		this.electricityReadingsId = electricityReadingsId;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public Date getReadingDate() {
		return readingDate;
	}

	public void setReadingDate(Date readingDate) {
		this.readingDate = readingDate;
	}

	public int getUnitsUsed() {
		return unitsUsed;
	}

	public void setUnitsUsed(int unitsUsed) {
		this.unitsUsed = unitsUsed;
	}
}
