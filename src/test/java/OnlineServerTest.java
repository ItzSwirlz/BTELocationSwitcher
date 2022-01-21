import java.net.InetAddress;

import org.junit.Test;

public class OnlineServerTest {
    public String[] ips = {"buildtheearth.net",
                           "bte-germany.de",
                           "hub.andromedacraft.com",
                           "207.244.229.33:25565",
                           "nordicbalticbte.net",
                           "midwestbte.net",
                           "play.btene.com",
                           "mc.alps-bte.com",
                           "btecanada.net",
                           "BuildTheEarth.ru",
                           "207.244.229.33:25567",
                           "64.139.238.70:25565",
                           "btejp.net",
                           "bteconosur.com",
                           "play.btehkmu.com",
                           "play.bteisrael.online",
                           "btetw.duckarmada.com",
                           "teams.buildtheearth.net:25503",
                           "bte.thesmyler.fr"};

    @Test
    public void test() {
    	System.out.println("Hello!");
        for(String i: ips) {
            try {
                InetAddress address = InetAddress.getByName(i);
                boolean reachable = address.isReachable(10000);
                if (reachable == true) {
                	System.out.println(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}