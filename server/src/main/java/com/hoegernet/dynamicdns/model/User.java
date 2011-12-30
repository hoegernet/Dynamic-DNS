package com.hoegernet.dynamicdns.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Projekt: com.hoegernet.dynamicdns.server/ <br>
 * Type: User
 * 
 * created: 01.09.2010
 * 
 * @author Thorsten Höger
 * 
 */
@XmlType(name = "User")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
	
	@XmlElement(name = "Username")
	private String username;
	
	@XmlElement(name = "Password")
	private String password;
	

	/**
	 * 
	 */
	public User() {
		// Auto-generated constructor stub
	}
	
	/**
	 * @param username
	 * @param password
	 */
	public User(final String username, final String password) {
		this.username = username;
		this.password = password;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(final String username) {
		this.username = username;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(final String password) {
		this.password = password;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
		result = prime * result + ((this.username == null) ? 0 : this.username.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final User other = (User) obj;
		if (this.password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!this.password.equals(other.password)) {
			return false;
		}
		if (this.username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!this.username.equals(other.username)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("User [username=%s]", this.username);
	}
	
}
