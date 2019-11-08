package fi.aalto.app.client;

import com.prosysopc.ua.stack.transport.security.SecurityMode;

import com.prosysopc.ua.client.UaClient;
import com.prosysopc.ua.client.Subscription;

public class AppClient {

	private String serverUri= "opc.tcp://localhost:52500/DemoServer";			
	private UaClient client;
	
	public AppClient() {
        this.client = new UaClient();
	}

	public UaClient getClient() {
		return this.client;
	}
	
	public void connect() {
		
		try {
		    this.client.setUri(this.serverUri); 
		    this.client.setSecurityMode(SecurityMode.NONE);
		    this.client.connect();
	        this.client.addSubscription(new Subscription());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		return;
	}
	
	public void disconnect() {
		
		try {
			// TODO remove subscription?
		    this.client.disconnect();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
		return;
	}
}
