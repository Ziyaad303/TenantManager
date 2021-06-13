package utils;

import java.util.List;

public class BillingDetails {

	private List<BillingDetailsLine> billingDetails;

	public List<BillingDetailsLine> getBillingDetails() {
		return billingDetails;
	}

	public void setBillingDetails(List<BillingDetailsLine> billingDetails) {
		this.billingDetails = billingDetails;
		
		BillingDetailsLine line = new BillingDetailsLine();
//		line.setDate(readingsDbHandler.retrieveOldWaterDetails());
	}

}
