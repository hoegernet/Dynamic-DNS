package com.hoegernet.dynamicdns;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.hoegernet.dynamicdns.iface.IUpdateService;
import com.hoegernet.dynamicdns.iface.UpdateRequest;
import com.hoegernet.platform.core.AbstractStarter;
import com.hoegernet.platform.core.config.Config;
import com.hoegernet.platform.core.config.ConsoleIO;

/**
 * Projekt: com.hoegernet.dynamicdns.server/ <br>
 * Type: Server
 * 
 * created: 25.08.2010
 * 
 * @author Thorsten Höger
 * 
 */
public class Client extends AbstractStarter {

	// private static final String CLIENT_CONF = "/etc/ddns-client.conf";
	private static final String CLIENT_CONF = "ddns-client.conf";

	private IUpdateService updateService;

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		new Client().run(args);
	}

	@Override
	protected void doConfig(final String[] arg0) {
		final Config cfg = new Config(Client.CLIENT_CONF);

		System.out.println("Clientkonfiguration");
		System.out.println("-------------------");

		cfg.readPropertyUpdate("Username", "login.user");
		cfg.readPropertyUpdate("Password", "login.pwd");

		cfg.readPropertyUpdate("Interval", "interval");

		String host = "";
		int count = 0;

		while (((host = ConsoleIO.readOption("Host:", cfg.getProperty("host." + count))) != null) && !host.isEmpty()) {
			cfg.setProperty("host." + count, host);
			count++;
		}

		cfg.setIntProperty("host.count", count);

		cfg.save();

	}

	@Override
	protected String getServiceName() {
		return "ddns-client";
	}

	@Override
	protected void doShutdown(final String[] arg0) {
		//
	}

	@Override
	protected void doStart(final String[] arg0) {
		try {
			final Service service = Service.create(new URL("http://83.169.45.65:44444/UpdateService?wsdl"), new QName("http://www.hoegernet.com/ws/DynamicDNS", "UpdateService"));
			this.updateService = service.getPort(IUpdateService.class);

		} catch (final Exception ex) {
			ex.printStackTrace();
		}

		final Config cfg = new Config(Client.CLIENT_CONF);
		String ip = "";

		while (this.isStarted()) {
			try {
				final String remoteIP = this.updateService.getClientIP();
				if (!ip.equals(remoteIP)) {
					final int count = cfg.getIntProperty("host.count");

					final UpdateRequest[] req = new UpdateRequest[count];

					for (int i = 0; i < req.length; i++) {
						req[i] = new UpdateRequest(cfg.getProperty("host." + i), remoteIP);
					}

					this.updateService.update(cfg.getProperty("login.user"), cfg.getProperty("login.pwd"), req);
					ip = remoteIP;
				} else {
					System.out.println("No update!");
				}

				Thread.sleep(cfg.getIntProperty("interval") * 1000);
			} catch (final Exception ex) {
				ex.printStackTrace();
			}
		}

	}

}
