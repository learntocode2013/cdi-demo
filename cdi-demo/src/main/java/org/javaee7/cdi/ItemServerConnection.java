package org.javaee7.cdi;

import org.javaee7.cdi.constraints.URL;

public class ItemServerConnection {
	@URL(protocol = "https", host = "www.cisco.com")
	private String itemUrl ;

	public void setFtpServer(String ftpServer) {
		this.ftpServer = ftpServer;
	}

	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}

	@URL(protocol = "ftp", port = 21)
	private String ftpServer ;

	public ItemServerConnection(String ftpServer, String itemUrl) {
		this.ftpServer = ftpServer;
		this.itemUrl = itemUrl;
	}


}
