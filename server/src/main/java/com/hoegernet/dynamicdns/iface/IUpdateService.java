package com.hoegernet.dynamicdns.iface;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * Projekt: com.hoegernet.dynamicdns/ <br>
 * Type: IUpdateService
 * 
 * created: 25.08.2010
 * 
 * @author Thorsten Höger
 * 
 */
@WebService(targetNamespace = "http://www.hoegernet.com/ws/DynamicDNS", name = "UpdateService", serviceName = "UpdateService", portName = "UpdateService")
@SOAPBinding(style = Style.RPC)
public interface IUpdateService {
	
	/**
	 * @param user
	 * @param pwd
	 * @param req
	 */
	@WebMethod(operationName = "update")
	void update(@WebParam(header = true, name = "user") final String user, @WebParam(header = true, name = "pwd") final String pwd, @WebParam(header = false, name = "request") final UpdateRequest req);
	
	/**
	 * @param user
	 * @param pwd
	 * @param req
	 */
	@WebMethod(operationName = "updateMultiple")
	void update(@WebParam(header = true, name = "user") final String user, @WebParam(header = true, name = "pwd") final String pwd, @WebParam(header = false, name = "request") final UpdateRequest[] req);
	
	/**
	 * @return caller IP
	 */
	@WebMethod(operationName = "getClientIP")
	@WebResult(name = "clientIP")
	String getClientIP();
	
}