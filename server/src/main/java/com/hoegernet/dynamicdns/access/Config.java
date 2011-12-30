package com.hoegernet.dynamicdns.access;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import com.hoegernet.dynamicdns.model.Domain;
import com.hoegernet.dynamicdns.model.Permission;
import com.hoegernet.dynamicdns.model.User;

/**
 * Projekt: com.hoegernet.dynamicdns.server/ <br>
 * Type: Config
 * 
 * created: 01.09.2010
 * 
 * @author Thorsten Höger
 * 
 */
@XmlType(name = "Configuration")
@XmlRootElement(name = "Configuration")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlSeeAlso( {User.class, Domain.class, Permission.class})
public class Config {
	
	/**
	 * 
	 */
	@XmlElementWrapper(name = "Users")
	@XmlElements(@XmlElement(name = "User"))
	public final ArrayList<User> users = new ArrayList<User>();
	
	/**
	 * 
	 */
	@XmlElementWrapper(name = "Domain")
	@XmlElements(@XmlElement(name = "Domain"))
	public final ArrayList<Domain> domains = new ArrayList<Domain>();
	
	/**
	 * 
	 */
	@XmlElementWrapper(name = "Permissions")
	@XmlElements(@XmlElement(name = "Permission"))
	public final ArrayList<Permission> permissions = new ArrayList<Permission>();
	
}
