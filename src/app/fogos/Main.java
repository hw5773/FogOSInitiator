package app.fogos;

import FlexID.FlexID;
import FlexID.FlexIDFactory;
import FlexID.InterfaceType;
import FlexID.Locator;
import FogOSSocket.FlexIDSession;

public class Main {

    public static void main(String[] args) {
	    FlexIDFactory factory = new FlexIDFactory();
	    String addr = "127.0.0.1";
	    String test = "Hello";
	    int port = 5556;
	    Locator loc = new Locator(InterfaceType.ETH, addr, port);
        byte[] peerID = {
                (byte) 0xd0, (byte) 0x93, (byte) 0xb9, (byte) 0xf6, (byte) 0xff, (byte) 0x83, (byte) 0xac, (byte) 0x89,
                (byte) 0xd9, (byte) 0x54, (byte) 0xb3, (byte) 0x03, (byte) 0x5d, (byte) 0x1f, (byte) 0x06, (byte) 0x47,
                (byte) 0xc1, (byte) 0x43, (byte) 0xb6, (byte) 0xed
        };

        FlexID id = factory.generateDeviceID();
        FlexID peer = new FlexID(peerID);
        peer.setLocator(loc);

        FlexIDSession flexIDSession = new FlexIDSession(id, peer);
        System.out.println("To be sent");
        System.out.println(byteArrayToHex(test.getBytes(), test.length()));

        flexIDSession.send(test.getBytes());
    }

    static String byteArrayToHex(byte[] a, int len) {
        byte[] tmp = new byte[len];
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        System.arraycopy(a, 0, tmp, 0, len);

        for (final byte b: tmp) {
            sb.append(String.format("0x%02x, ", b & 0xff));
            idx++;
            if (idx % 8 == 0)
                sb.append("\n");
        }
        return sb.toString();
    }
}
