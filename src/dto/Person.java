package dto;

public class Person {

	private int peId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String email;
	private String phone;
	private int acId;
	private int status;

	public Person() {
	}

	public Person(int peId, String firstName, String lastName, String idNumber, String email, String phone, int acId,
			int status) {
		this.peId = peId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.email = email;
		this.phone = phone;
		this.acId = acId;
		this.status = status;
	}

	public int getPeId() {
		return peId;
	}

	public void setPeId(int peId) {
		this.peId = peId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAcId() {
		return acId;
	}

	public void setAcId(int acId) {
		this.acId = acId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
