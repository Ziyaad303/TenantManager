package dto;

public class WaterReading {

	private int waterReadingsId;
	private int propertyId;
	private int tenantId;
	private String readingDate;
	private int unitsUsed;
	private int sequence;

	public WaterReading() {
	}

	public WaterReading(int waterReadingsId, int propertyId, int tenantId, String readingDate, int unitsUsed, int sequence) {
		this.waterReadingsId = waterReadingsId;
		this.propertyId = propertyId;
		this.tenantId = tenantId;
		this.readingDate = readingDate;
		this.unitsUsed = unitsUsed;
		this.sequence = sequence;
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

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public String getReadingDate() {
		return readingDate;
	}

	public void setReadingDate(String readingDate) {
		this.readingDate = readingDate;
	}

	public int getUnitsUsed() {
		return unitsUsed;
	}

	public void setUnitsUsed(int unitsUsed) {
		this.unitsUsed = unitsUsed;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

}
