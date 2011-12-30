package com.hoegernet.dynamicdns.connect;

import org.xbill.DNS.ARecord;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Name;
import org.xbill.DNS.Record;
import org.xbill.DNS.Resolver;
import org.xbill.DNS.SimpleResolver;
import org.xbill.DNS.Type;
import org.xbill.DNS.Update;

/**
 * Projekt: com.hoegernet.dynamicdns/ <br>
 * Type: DNSUpdater
 * 
 * created: 25.08.2010
 * 
 * @author Thorsten Höger
 * 
 */
public class DNSUpdater {
	
	/**
	 * @param domain
	 * @param ip
	 */
	public static void update(final String domain, final String ip) {
		try {
			System.out.println("Host: " + domain);
			System.out.println("IP: " + ip);
			
			final Name zone = Name.fromString("cp.hoegergroup.de.");
			final Name host = Name.fromString(domain, zone);
			final Update update = new Update(zone);
			update.replace(host, Type.A, 3600, ip);
			
			final Resolver res = new SimpleResolver("localhost");
			res.setTCP(true);
			res.send(update);
		} catch (final Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * @param host
	 * @return IP address
	 */
	public static String getIP(final String host) {
		try {
			final Lookup lookup = new Lookup(host + ".cp.hoegergroup.de", Type.A);
			lookup.setResolver(new SimpleResolver("83.169.45.65"));
			lookup.run();
			if (lookup.getResult() == Lookup.SUCCESSFUL) {
				final Record[] records = lookup.getAnswers();
				for (int i = 0; i < records.length; i++) {
					final ARecord a = (ARecord) records[i];
					System.out.println("Host " + a.getAddress());
				}
			} else {
				System.out.println("Error");
			}
		} catch (final Exception ex) {
			ex.printStackTrace();
		}
		return "0.0.0.0";
	}
}
