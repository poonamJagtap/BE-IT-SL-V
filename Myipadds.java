
import java.net.InetAddress;

public class Myipadds {
   public static void main(String[] args) throws Exception {
      InetAddress addr = InetAddress.getLocalHost();
      System.out.println("Local HostAddress: "+addr.getHostAddress());
      String hostname = addr.getHostName();
      System.out.println("Local host name: "+hostname);
   }

}
/*
 OUTPUT
 * Local HostAddress: 172.16.231.230
   Local host name: IT15
 */

