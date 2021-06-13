package utils;

public class BillingDetailsLine {

	private String date;
	private String reference;
	private String description;
	private double debit;
	private double credit;
	private double balance;

	public BillingDetailsLine() {
		this.date = "";
		this.reference = "";
		this.description = "";
	}

	public BillingDetailsLine(String date, String reference, String description, double debit, double credit, double balance) {
		this.date = date;
		this.reference = reference;
		this.description = description;
		this.debit = debit;
		this.credit = credit;
		this.balance = balance;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getDebit() {
		return debit;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
