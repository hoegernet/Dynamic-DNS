package com.hoegernet.dynamicdns.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Projekt: com.hoegernet.dynamicdns.server/ <br>
 * Type: Permission
 * 
 * created: 01.09.2010
 * 
 * @author Thorsten Höger
 * 
 */
@XmlType(name = "Permission")
@XmlAccessorType(XmlAccessType.FIELD)
public class Permission {
	
	@XmlAttribute(name = "user")
	private String username = "";
	
	@XmlAttribute(name = "domain")
	private String domain = "";
	

	/**
	 * 
	 */
	public Permission() {
		// Auto-generated constructor stub
	}
	
	/**
	 * @param username
	 * @param domain
	 */
	public Permission(final String username, final String domain) {
		this.username = username;
		this.domain = domain;
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
	 * @return the domain
	 */
	public String getDomain() {
		return this.domain;
	}
	
	/**
	 * @param domain the domain to set
	 */
	public void setDomain(final String domain) {
		this.domain = domain;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.domain == null) ? 0 : this.domain.hashCode());
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
		final Permission other = (Permission) obj;
		if (this.domain == null) {
			if (other.domain != null) {
				return false;
			}
		} else if (!this.domain.equals(other.domain)) {
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
		return String.format("Permission [domain=%s, username=%s]", this.domain, this.username);
	}
	
	/**
	 * @return key
	 */
	public String getKey() {
		return String.format("%s@%s", this.username, this.domain);
	}
}
