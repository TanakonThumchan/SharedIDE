
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import javax.swing.*;

/**
 * Prova di connessione multicast
 * @deprecated 
 */
public class Clientside {

    Socket socket = null;
    InetAddress group=null;
    MulticastSocket s=null;
    
    /**
     * Inizializza la connessione 
     * @deprecated 
     */
    Clientside(){
        try {
            socket = new Socket(InetAddress.getLocalHost(), 10000);
            InetAddress group = InetAddress.getByName("228.5.6.7");
            MulticastSocket s = new MulticastSocket(6789);
            s.joinGroup(group);
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Connessione Fallita", "Connessione", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Test dell'invio del testo 
     * @param text Testo da inviare
     * @deprecated 
     */
    public String writing(String text) throws IOException {
        String temp = "";
        String buffer;
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while ((buffer = in.readLine()) != null) {
            temp += buffer;
        }
        return temp;
    }
    
    /**
     * Test dell'invio del testo in multicast
     * @deprecated 
     */
    public String writemulti()
    {
        String msg = "Hello";
        DatagramPacket hi = new DatagramPacket(msg.getBytes(), msg.length(),group, 6789);
        byte[] buf = new byte[1000];
        DatagramPacket recv = new DatagramPacket(buf, buf.length);
        try {
            s.send(hi);            
            s.receive(recv);
        } catch (IOException ex) {
            JOptionPane.showConfirmDialog(null, "NO", "Connessione", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        }
        return buf.toString();
    }
}
    
    
