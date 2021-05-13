package dto;

import java.util.Date;

public class WaterReadings {

	private int waterReadingsId;
	private int propertyId;
	private Date readingDate;
	private int unitsUsed;

	public WaterReadings() {
	}

	public WaterReadings(int waterReadingsId, int propertyId, Date readingDate, int unitsUsed) {
		this.waterReadingsId = waterReadingsId;
		this.propertyId = propertyId;
		this.readingDate = readingDate;
		this.unitsUsed = unitsUsed;
	}

	public int getWaterReadingsId() {
		return waterReadingsId;
	}

	public void setWaterReadingsId(int waterReadingsId) {
		this.waterReadingsId = waterReadingsId;
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
