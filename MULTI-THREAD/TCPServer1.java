//Multi-threaded TCP Server
import java.net.*;
import java.io.*;

public class TCPServer1
{
	public static void main(String[] args) throws Exception
	{
		ServerSocket server = new ServerSocket(4440);
		System.out.println("Server is waiting ....");
		while(true)
		{

			Socket conn = server.accept();
			Thread tasks = new ServerThread(conn);
			tasks.start();
		}

		//System.out.println("Hello World!");
	}
}

class ServerThread extends Thread
{
	Socket myConn;
	static int clientCount = 0;
	ServerThread(Socket cn) {this.myConn = cn; clientCount = clientCount+1;}
	public void run()
	{
		try
		{
			DataInputStream in = new DataInputStream(myConn.getInputStream());
			DataOutputStream out = new DataOutputStream(myConn.getOutputStream());
			System.out.println("Client " +clientCount+ ": " + in.readUTF().toString());
			out.writeUTF("this is server");


			String s;
			System.out.println("Server waiting for message from the client");
			boolean quit = false;
				do {
					String msg = "";
					s = in.readLine();
					 int len = s.length();
					 if (s.equals("exit")) {
					  quit = true;
						}
					 for (int i = 0; i < len; i++) {
					 msg = msg + s.charAt(i);
					 out.write((byte) s.charAt(i));
												   }
 
					 System.out.println("From client " +clientCount+" message ="+ msg);
						out.write(13);
					 out.write(10);
				   out.flush();
					 } while (!quit);
				out.close();
				//soc.close()
			
		}
		catch (IOException ioe) 
		{ System.out.println("ioe");}
		finally {
				try
				{
					myConn.close();
				}
				catch (IOException ioe)
				{ System.out.println("ioe");}
			
			}
	}
}

