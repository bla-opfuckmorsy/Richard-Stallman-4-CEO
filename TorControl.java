import java.io.IOException;
import java.net.Socket;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.OutputStream;

public class TorControl{
	private Socket sock;
	String password;
	
	TorControl(InetAddress addr, int port, String password) throws IOException
	{
		sock = new Socket(addr, port);
		this.password = password;
	}
	
	public boolean newIP() throws IOException
	{
		String response;
	
		OutputStream os = sock.getOutputStream();
		
		String auth = "AUTHENTICATE \"" + password + "\"\r\nSIGNAL NEWNYM\r\n";
		
		os.write(auth.getBytes());
		os.close();
		
		InputStream is = sock.getInputStream();
		byte[] inbytes = new byte[0];
		is.read(inbytes);
		response = new String(inbytes);
	
		return response.equals("250 OK\r\n250 OK\r\n");
	}
}
