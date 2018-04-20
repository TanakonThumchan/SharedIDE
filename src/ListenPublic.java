
import java.io.IOException;
import java.io.PrintWriter;
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
    
    public ListenPublic(){
        
    }
    
    @Override
    public void run() {
        try
        {
            InetAddress group = InetAddress.getByName("228.5.6.7");
            MulticastSocket s = new MulticastSocket(6789);
            //s.setLoopbackMode(true);
            s.joinGroup(group);        
            while(true){
                byte[] buf = new byte[256];
                DatagramPacket recv = new DatagramPacket(buf, buf.length);
                s.receive(recv);
                ByteBuffer bytebu= ByteBuffer.wrap(buf);
                if (bytebu.get(0)==3)
                {
                    String address;
                    byte[] buffer;
                    buffer= Arrays.copyOfRange(bytebu.array(), 1, 30);
                    address=new String(buffer,Charset.forName("UTF-16BE"));
                    InetAddress host = InetAddress.getByName(address);
                    Socket client = new Socket(host, 9090);
                    PrintWriter out= new PrintWriter(client.getOutputStream());
                    //out.write();
                    s.close();
                    out.close();
                }
            }
        }      
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Errr");
            Logger.getLogger(ListenGlobal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
