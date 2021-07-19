package wondernana.adressbook.data;

public class ContactData {
	private String firstname;
	private String lastname;
	private String mobile;
	private String group;

	public ContactData (String firstname, String lastname, String mobile, String group) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.mobile = mobile;
		this.group = group;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getMobile() {
		return mobile;
	}

	public String getGroup() {
		return group;
	}
}
