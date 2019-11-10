package fi.aalto.app.server;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.prosysopc.ua.stack.builtintypes.ByteString;
import com.prosysopc.ua.stack.builtintypes.DateTime;
import com.prosysopc.ua.stack.builtintypes.LocalizedText;
import com.prosysopc.ua.stack.cert.PkiDirectoryCertificateStore;
import com.prosysopc.ua.stack.cert.DefaultCertificateValidator;
import com.prosysopc.ua.stack.core.ApplicationDescription;
import com.prosysopc.ua.stack.core.MessageSecurityMode;
import com.prosysopc.ua.stack.transport.security.KeyPair;
import com.prosysopc.ua.stack.transport.security.SecurityMode;
import com.prosysopc.ua.stack.transport.security.SecurityPolicy;
import com.prosysopc.ua.stack.utils.EndpointUtil;
import com.prosysopc.ua.types.opcua.server.BaseEventTypeNode;
import com.prosysopc.ua.ApplicationIdentity;
import com.prosysopc.ua.UserTokenPolicies;
import com.prosysopc.ua.UaApplication.Protocol;
import com.prosysopc.ua.server.EventManager;
import com.prosysopc.ua.server.UaServer;

import fi.aalto.app.server.AppNodeManager;
import fi.aalto.app.client.AppClient;

public class AppServer {

	protected static String APP_NAME = "AppServer";
	protected static int TCP_PORT = 52520;
	protected static boolean enableSessionDiagnostics = false;
	protected AppNodeManager appNodeManager;
	protected UaServer server;
	private AppClient appClient; 

	public static void main(String[] args) {

		AppServer appServer = new AppServer();
		appServer.initialize(TCP_PORT, APP_NAME);
		appServer.createAddressSpace();
		appServer.run(enableSessionDiagnostics);
	}
	
	public AppServer() {
        this.appClient = new AppClient();
	}
	
	public AppClient getAppClient() {
        return this.appClient;
	}

	protected void initialize(int port, String applicationName) {

		try {
		    server = new UaServer();
		    
		    ApplicationDescription appDescription = new ApplicationDescription();
		    appDescription.setApplicationName(new LocalizedText(applicationName, Locale.ENGLISH));
		    appDescription.setApplicationUri("urn:IlkkaSeilonen:" + applicationName);
			appDescription.setProductUri("urn:prosysopc.com:" + applicationName);
		    server.setPort(Protocol.OpcTcp, port);
		    server.setServerName(applicationName);
		    server.setBindAddresses(EndpointUtil.getInetAddresses());

		    final PkiDirectoryCertificateStore certificateStore = new PkiDirectoryCertificateStore();
		    final DefaultCertificateValidator validator = new DefaultCertificateValidator(certificateStore);
			server.setCertificateValidator(validator);
		    File privatePath = new File(certificateStore.getBaseDir(), "private");
			KeyPair issuerCertificate = ApplicationIdentity.loadOrCreateIssuerCertificate(
					"ApplicationCA", privatePath, "application", 3650, false);
			int[] keySizes = null;
			final ApplicationIdentity identity = ApplicationIdentity
					.loadOrCreateCertificate(appDescription, "App Organisation",
					"application", privatePath, null, keySizes, true);
			//String hostName = ApplicationIdentity.getActualHostName();
		    server.setApplicationIdentity(identity);

		    Set<SecurityPolicy> supportedSecurityPolicies = new HashSet<SecurityPolicy>();
		    supportedSecurityPolicies.add(SecurityPolicy.NONE);
		    Set<MessageSecurityMode> supportedMessageSecurityModes = new HashSet<MessageSecurityMode>();
		    supportedMessageSecurityModes.add(MessageSecurityMode.None);
		    server.getSecurityModes()
	        .addAll(SecurityMode.combinations(supportedMessageSecurityModes, supportedSecurityPolicies));

		    server.addUserTokenPolicy(UserTokenPolicies.ANONYMOUS);
			server.init();
			server.getSessionManager().setMaxSessionCount(500);
			server.getSessionManager().setMaxSessionTimeout(3600000);
			server.getSubscriptionManager().setMaxSubscriptionCount(500);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}

	}

	protected void createAddressSpace() {
		try {
			this.appClient.connect();
			appNodeManager = new AppNodeManager(server, AppNodeManager.NAMESPACE);
			appNodeManager.createAddressSpace(appClient.getClient());
			appNodeManager.getIoManager().addListeners(new AppIoManagerListener(appClient.getClient()));
			appNodeManager.addListener(new AppNodeManagerListener(appClient.getClient(), this.server));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
	}
	
	protected void run(boolean enableSessionDiagnostics) {
		try {
			server.start();
			if (enableSessionDiagnostics)
				server.getNodeManagerRoot().getServerData().getServerDiagnosticsNode().setEnabled(true);

			waitForEnter();

			System.out.println("Closing down...");
			this.appClient.disconnect();
			server.shutdown(5, new LocalizedText("Closed by user", Locale.ENGLISH));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
	}

	protected void waitForEnter() {
		try {
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("AppServer: Press enter to exit");
            String s = br.readLine();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
   }
	
	

}
