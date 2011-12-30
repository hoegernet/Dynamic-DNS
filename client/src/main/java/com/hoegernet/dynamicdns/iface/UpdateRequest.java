package com.hoegernet.dynamicdns.iface;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * Projekt: com.hoegernet.dynamicdns/ <br>
 * Type: UpdateRequest
 * 
 * created: 25.08.2010
 * 
 * @author Thorsten Höger
 * 
 */
@XmlRootElement(name = "UpdateRequest", namespace = "http://www.hoegernet.com/ws/DynamicDNS")
@XmlAccessorType(XmlAccessType.FIELD)
public class UpdateRequest {
	
	@XmlAttribute(name = "Host", required = true)
	private String host;
	
	@XmlValue
	private String ip;
	

	/**
	 * 
	 */
	public UpdateRequest() {
		// Auto-generated constructor stub
	}
	
	/**
	 * @param host
	 */
	public UpdateRequest(final String host) {
		this.host = host;
		this.ip = null;
	}
	
	/**
	 * @param host
	 * @param ip
	 */
	public UpdateRequest(final String host, final String ip) {
		this.host = host;
		this.ip = ip;
	}
	
	/**
	 * @return the host
	 */
	public String getHost() {
		return this.host;
	}
	
	/**
	 * @param host the host to set
	 */
	public void setHost(final String host) {
		this.host = host;
	}
	
	/**
	 * @return the ip
	 */
	public String getIp() {
		return this.ip;
	}
	
	/**
	 * @param ip the ip to set
	 */
	public void setIp(final String ip) {
		this.ip = ip;
	}
	
}
