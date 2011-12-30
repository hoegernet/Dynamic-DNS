package com.hoegernet.dynamicdns.access;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import com.hoegernet.dynamicdns.model.Domain;
import com.hoegernet.dynamicdns.model.Permission;
import com.hoegernet.dynamicdns.model.User;

/**
 * Projekt: com.hoegernet.dynamicdns.server/ <br>
 * Type: DataAccess
 * 
 * created: 01.09.2010
 * 
 * @author Thorsten Höger
 * 
 */
public class DataAccess {
	
	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		DataAccess.getInstance().generateSchema();
	}
	

	/**
	 * instance
	 */
	private static DataAccess instance = new DataAccess();
	

	/**
	 * get singelton instance
	 * 
	 * @return instance
	 */
	public static DataAccess getInstance() {
		return DataAccess.instance;
	}
	

	private final HashMap<String, User> userMap = new HashMap<String, User>();
	
	private final HashMap<String, Domain> hostMap = new HashMap<String, Domain>();
	
	private final HashSet<String> permissionSet = new HashSet<String>();
	

	/**
	 * private constructor
	 */
	private DataAccess() {
		// private constructor stub
	}
	
	/**
	 * load data from config
	 */
	public void loadData() {
		try {
			final JAXBContext ctx = JAXBContext.newInstance(Config.class);
			final Unmarshaller unmarshaller = ctx.createUnmarshaller();
			final Config cfg = (Config) unmarshaller.unmarshal(new File("ddns.xml"));
			
			System.out.println("Loading users:");
			for (final User user : cfg.users) {
				System.out.println("  " + user);
				this.userMap.put(user.getUsername(), user);
			}
			
			System.out.println("Loading domains:");
			for (final Domain dom : cfg.domains) {
				System.out.println("  " + dom);
				this.hostMap.put(dom.getDomain(), dom);
			}
			
			System.out.println("Loading permissions:");
			for (final Permission perm : cfg.permissions) {
				System.out.println("  " + perm);
				this.permissionSet.add(perm.getKey());
			}
			
		} catch (final JAXBException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * generate schema file
	 */
	public void generateSchema() {
		try {
			final JAXBContext ctx = JAXBContext.newInstance(Config.class);
			ctx.generateSchema(new SchemaOutputResolver() {
				
				@Override
				public Result createOutput(final String namespaceUri, final String suggestedFileName) throws IOException {
					return new StreamResult(new File(suggestedFileName));
				}
			});
		} catch (final JAXBException ex) {
			ex.printStackTrace();
		} catch (final IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * @param user
	 * @param pwd
	 * @return check
	 */
	public boolean checkUser(final String user, final String pwd) {
		final User u = this.userMap.get(user);
		if ((u != null) && u.getUsername().equals(user) && u.getPassword().equals(pwd)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param user
	 * @param domain
	 * @return check
	 */
	public boolean checkPermission(final String user, final String domain) {
		return (user != null) && (domain != null) && this.permissionSet.contains(user + "@" + domain);
	}
}
