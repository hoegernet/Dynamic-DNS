package com.hoegernet.dynamicdns.server;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import com.hoegernet.dynamicdns.access.DataAccess;
import com.hoegernet.dynamicdns.connect.DNSUpdater;
import com.hoegernet.dynamicdns.iface.IUpdateService;
import com.hoegernet.dynamicdns.iface.UpdateRequest;

/**
 * Projekt: com.hoegernet.dynamicdns/ <br>
 * Type: UpdateService
 * 
 * created: 25.08.2010
 * 
 * @author Thorsten Höger
 * 
 */
@WebService(targetNamespace = "http://www.hoegernet.com/ws/DynamicDNS", name = "UpdateService", serviceName = "UpdateService", portName = "UpdateService", endpointInterface = "com.hoegernet.dynamicdns.iface.IUpdateService")
public class UpdateService implements IUpdateService {

	@Resource
	private WebServiceContext webServiceContext;

	@Override
	public void update(final String user, final String pwd, final UpdateRequest req) {
		if (DataAccess.getInstance().checkUser(user, pwd) && DataAccess.getInstance().checkPermission(user, req.getHost())) {
			String ip = req.getIp();
			if ((ip == null) || ip.isEmpty()) {
				ip = this.getClientIP();
			}
			DNSUpdater.update(req.getHost(), ip);
		}
	}

	@Override
	public void update(final String user, final String pwd, final UpdateRequest[] req) {
		if (DataAccess.getInstance().checkUser(user, pwd)) {
			final String callerIP = this.getClientIP();
			for (final UpdateRequest updateRequest : req) {
				if (DataAccess.getInstance().checkPermission(user, updateRequest.getHost())) {
					String ip = updateRequest.getIp();
					if (ip == null) {
						ip = callerIP;
					}
					DNSUpdater.update(updateRequest.getHost(), ip);
				}
			}
		}
	}

	@Override
	@SuppressWarnings("restriction")
	public String getClientIP() {
		final MessageContext messageContext = this.webServiceContext.getMessageContext();
		final com.sun.net.httpserver.HttpExchange httpExchange = (com.sun.net.httpserver.HttpExchange)messageContext.get("com.sun.xml.internal.ws.http.exchange");
		return httpExchange.getRemoteAddress().getAddress().getHostAddress();
	}

}
