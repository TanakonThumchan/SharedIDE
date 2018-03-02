
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author diù
 */
public class ListenGlobal implements Runnable {
    JTextArea temp;
    public ListenGlobal(JTextArea txtCode)
    {
        temp=txtCode;
    }
    @Override
    public void run() {
        try
        {
            Thread.currentThread().setName("CIAO");
            Thread.sleep(5000);
            //JOptionPane.showMessageDialog(null, Thread.currentThread().getName());
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Thread.currentThread().setName("CIAO");
                    temp.append("Blah"+System.lineSeparator());
                    Thread.currentThread().setName("Main");
                }
            });;
            Thread.sleep(1000);
            JOptionPane.showMessageDialog(null,"Runnig");
            String msg= "Ciao";
            InetAddress group = InetAddress.getByName("228.5.6.7");
            MulticastSocket s = new MulticastSocket(6789);
            s.joinGroup(group);
            DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(),group, 6789);
            s.send(packet);
            
            byte[] buf = new byte[1000];
            DatagramPacket recv = new DatagramPacket(buf, buf.length);
            s.receive(recv);
            msg=new String(buf);
            JOptionPane.showMessageDialog(null,"NN1"+msg);
            s.receive(recv);
            msg=new String(buf);
            JOptionPane.showMessageDialog(null,"NN2"+msg);
            s.leaveGroup(group);
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Errr");
            Logger.getLogger(ListenGlobal.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Errr");
            //Logger.getLogger(ListenGlobal.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}