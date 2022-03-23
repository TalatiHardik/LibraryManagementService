package com.lib.authorization.model;

import java.sql.Date;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.core.Transient;

@Entity
@Transient
public class User {
	
	
	
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getSq() {
		return sq;
	}
	public void setSq(int sq) {
		this.sq = sq;
	}
	public String getSqa() {
		return sqa;
	}
	public void setSqa(String sqa) {
		this.sqa = sqa;
	}	
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}
	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}
	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}
	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}
	public boolean isEnabled() {
		return isEnabled;
	}
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}




	@Id
	@GeneratedValue
	private long userid;
	private String fname;
	private String lname;
	private String email;
	private String pwd;
	private String gender;
	private Date dob;
	private String phone;
	private String city;
	private int sq;
	private String sqa;
	@Column(columnDefinition = "varchar(255)  default 'ROLE_USER'")
	private String roles;	
	private boolean isAccountNonExpired;	
	private boolean isAccountNonLocked;	
	private boolean isCredentialsNonExpired;	
	private boolean isEnabled;
	
	@Column(columnDefinition = "LONGBLOB" )
	private byte[] image;
	
	
	
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public User() {
		super();
	}
	public User(long userid, String fname, String lname, String email, String pwd, String gender, Date dob,
			String phone, String city, int sq, String sqa, String roles, boolean isAccountNonExpired,
			boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled, byte[] image) {
		super();
		this.userid = userid;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.pwd = pwd;
		this.gender = gender;
		this.dob = dob;
		this.phone = phone;
		this.city = city;
		this.sq = sq;
		this.sqa = sqa;
		this.roles = roles;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
		this.image = image;
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", pwd=" + pwd
				+ ", gender=" + gender + ", dob=" + dob + ", phone=" + phone + ", city=" + city + ", sq=" + sq
				+ ", sqa=" + sqa + ", roles=" + roles + ", isAccountNonExpired=" + isAccountNonExpired
				+ ", isAccountNonLocked=" + isAccountNonLocked + ", isCredentialsNonExpired=" + isCredentialsNonExpired
				+ ", isEnabled=" + isEnabled + ", image=" + Arrays.toString(image) + "]";
	}
	
	
	
	
	
	
	
	

}
