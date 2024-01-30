// package exp1;

class User {
	private String name;
	private String password;
	private String email;
	private String phone;
	private Address address;

	public User(String name, String password, String email, String phone, Address address) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public Address getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", email=" + email + ", phone=" + phone + ", address="
				+ address + "]";
	}
}

class Address {
	private String country;
	private String province;
	private String city;
	private String street;
	private String zipCode;

	public Address(String country, String province, String city, String street, String zipCode) {
		this.country = country;
		this.province = province;
		this.city = city;
		this.street = street;
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getZipCode() {
		return zipCode;
	}

	@Override
	public String toString() {
		return "Address [country=" + country + ", province=" + province + ", city=" + city + ", street=" + street
				+ ", zipCode=" + zipCode + "]";
	}
}

class Admin extends User {
	private boolean isAdmin;

	public Admin(String name, String password, String email, String phone, Address address, boolean isAdmin) {
		super(name, password, email, phone, address);
		this.isAdmin = isAdmin;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return super.toString() + ", isAdmin=" + isAdmin;
	}
}

class Customer extends User {
	private String cardNumber;

	public Customer(String name, String password, String email, String phone, Address address, String cardNumber) {
		super(name, password, email, phone, address);
		this.cardNumber = cardNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	@Override
	public String toString() {
		return super.toString() + ", cardNumber=" + cardNumber;
	}
}