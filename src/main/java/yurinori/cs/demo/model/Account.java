package yurinori.cs.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {

//	Id int identity primary key ,
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

//	UserName varchar(50) not null,
	@Column(name = "user_name", nullable = false, length = 50, unique = true)
	private String userName;

//	Password varchar(50) not null,
	@Column(name = "password", nullable = false, length = 50)
	private String password;

//	Role int default 2, /*1 admin 2 user*/
	@Column(name = "role")
	private int role = 2;

//	FirstName nvarchar(50) not null,
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

//	LastName nvarchar(50) not null,
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;

//	Email varchar(50) not null,
	@Column(name = "email", nullable = false, length = 50)
	private String email;

//	Describe nvarchar(500) ,
	@Column(name = "describe", nullable = false, length = 500)
	private String describe;

//	Avatar text ,
	@Column(name = "avatar")
	private String avatar;

	public Account() {
	}

	public Account(int id, String userName, String password, int role, String firstName, String lastName, String email,
			String describe, String avatar) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.describe = describe;
		this.avatar = avatar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", userName=" + userName + ", password=" + password + ", role=" + role
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", describe=" + describe
				+ ", avatar=" + avatar + "]";
	}
}
