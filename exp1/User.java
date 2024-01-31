// package exp1;

enum CustomerType {
	ORDINARY, VIP
}

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

	public void setAddress(Address address) {
		this.address = address;
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

class Customer extends User {
	private String cardNumber;
	private CustomerType type;

	public Customer(String name, String password, String email, String phone, Address address, String cardNumber) {
		super(name, password, email, phone, address);
		this.cardNumber = cardNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public CustomerType getType() {
		return type;
	}

	@Override
	public String toString() {
		return super.toString() + ", cardNumber=" + cardNumber;
	}
}