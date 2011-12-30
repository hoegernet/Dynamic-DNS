package com.hoegernet.dynamicdns;

import javax.xml.ws.Endpoint;

import com.hoegernet.dynamicdns.access.DataAccess;
import com.hoegernet.dynamicdns.server.UpdateService;
import com.hoegernet.platform.core.AbstractStarter;

/**
 * Projekt: com.hoegernet.dynamicdns.server/ <br>
 * Type: Server
 * 
 * created: 25.08.2010
 * 
 * @author Thorsten Höger
 * 
 */
public class Server extends AbstractStarter {

	private Endpoint endpoint = null;

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		new Server().run(args);
	}

	@Override
	protected String getServiceName() {
		return "ddns-server";
	}

	@Override
	protected void doConfig(final String[] arg0) {
		//
	}

	@Override
	protected void doShutdown(final String[] arg0) {
		if (this.endpoint.isPublished()) {
			this.endpoint.stop();
		}
	}

	@Override
	protected void doStart(final String[] arg0) {
		DataAccess.getInstance().loadData();

		this.endpoint = Endpoint.publish("http://0.0.0.0:44444/UpdateService", new UpdateService());

		System.out.println("Service Published!");
	}

}
