package dto;

public class ElectricityReading {

	private int electricityReadingsId;
	private int propertyId;
	private int tenantId;
	private String readingDate;
	private int unitsUsed;
	private int sequence;
	
	public ElectricityReading() {
	}

	public ElectricityReading(int electricityReadingsId, int propertyId, int tenantId, String readingDate, int unitsUsed, int sequence) {
		this.electricityReadingsId = electricityReadingsId;
		this.propertyId = propertyId;
		this.tenantId = tenantId;
		this.readingDate = readingDate;
		this.unitsUsed = unitsUsed;
		this.sequence = sequence;
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
