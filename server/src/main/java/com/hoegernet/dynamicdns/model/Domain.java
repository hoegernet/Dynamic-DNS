package com.hoegernet.dynamicdns.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * Projekt: com.hoegernet.dynamicdns.server/ <br>
 * Type: Host
 * 
 * created: 01.09.2010
 * 
 * @author Thorsten Höger
 * 
 */
@XmlType(name = "Domain")
@XmlAccessorType(XmlAccessType.FIELD)
public class Domain {
	
	@XmlAttribute(name = "name")
	private String domain = "";
	
	@XmlValue
	private String description = "";
	

	/**
	 * 
	 */
	public Domain() {
		// Auto-generated constructor stub
	}
	
	/**
	 * @param domain
	 * @param description
	 */
	public Domain(final String domain, final String description) {
		this.domain = domain;
		this.description = description;
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
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return String.format("Host [domain=%s]", this.domain);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
		result = prime * result + ((this.domain == null) ? 0 : this.domain.hashCode());
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
		final Domain other = (Domain) obj;
		if (this.description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!this.description.equals(other.description)) {
			return false;
		}
		if (this.domain == null) {
			if (other.domain != null) {
				return false;
			}
		} else if (!this.domain.equals(other.domain)) {
			return false;
		}
		return true;
	}
	
}
