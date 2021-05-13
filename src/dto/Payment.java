package dto;

import java.util.Date;

public class Payment {

	private int paymentId;
	private int tenantId;
	private Date paymentDate;
	private double amount;

	public Payment() {

	}
	
	public Payment(int paymentId, int tenantId, Date paymentDate, double amount) {
		this.paymentId = paymentId;
		this.tenantId = tenantId;
		this.paymentDate = paymentDate;
		this.amount = amount;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getTenantId() {
		return tenantId;
	}

	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
