
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thumchan.13108
 */
public class ListenPublic implements Runnable {

    String name;

    public ListenPublic(String name) {
        this.name=name;
    }

    @Override
    public void run() {
        try {
            InetAddress group = InetAddress.getByName("228.5.6.7");
            MulticastSocket s = new MulticastSocket(6789);
            //s.setLoopbackMode(true);
            s.joinGroup(group);
            while (true) {
                byte[] buf = new byte[256];
                DatagramPacket recv = new DatagramPacket(buf, buf.length);
                s.receive(recv);
                ByteBuffer bytebu = ByteBuffer.wrap(buf);
                if (bytebu.get(0) == 2) {
                    String address;
                    byte[] buffer;
                    buffer = Arrays.copyOfRange(bytebu.array(), 1, 31);
                    address = new String(buffer, Charset.forName("UTF-16BE"));
                    InetAddress host = InetAddress.getByName(address);
                    try {
                        Socket client = new Socket(host, 9090);
                        DataOutputStream out = new DataOutputStream(client.getOutputStream());
                        bytebu = ByteBuffer.allocate(256);                        
                        InetAddress localHost = InetAddress.getLocalHost();
                        address = normalizzaIp(localHost.getHostAddress());
                        bytebu.put(0, (byte) 3);
                        bytebu.position(1);
                        for (int i = 0; i < 15; i++) {
                            bytebu.putChar(address.charAt(i));
                        }
                        for(int i=0;i<name.length();i++){
                            bytebu.putChar(name.charAt(i));
                        }
                        out.write(bytebu.array());
                        client.close();
                        out.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ListenPublic.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Errr");
            Logger.getLogger(ListenGlobal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String normalizzaIp(String bruttoIp) {
        String res = "";
        String[] arrOfStr;
        arrOfStr = bruttoIp.split("\\.");
        for (int j = 0; j < 4; j++) {
            switch (arrOfStr[j].length()) {
                case 1:
                    res += "00" + arrOfStr[j];
                    break;
                case 2:
                    res += "0" + arrOfStr[j];
                    break;
                case 3:
                    res += arrOfStr[j];
                    break;
                default:
                    res = "Invalid ip";
                    break;
            }
            res += ".";
        }
        String newstr = res.substring(0, 15);
        return newstr;
    }
}
