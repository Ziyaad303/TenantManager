package dto;

public class Account {

	private int accountId;
	private String bank;
	private String accountHolder;
	private String accountType;
	private String accountNumber;
	private String branchCode;

	public Account() {
	}

	public Account(int accountId, String bank, String accountHolder, String accountType, String accountNumber,
			String branchCode) {
		this.accountId = accountId;
		this.bank = bank;
		this.accountHolder = accountHolder;
		this.accountType = accountType;
		this.accountNumber = accountNumber;
		this.branchCode = branchCode;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

}
