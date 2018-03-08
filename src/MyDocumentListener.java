
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MyDocumentListener implements DocumentListener {
    private String msg;
    private InetAddress group;
    private MulticastSocket s;
    private DatagramPacket packet;
    private JTextArea temp;
    public MyDocumentListener(JTextArea txtCode)
    {
        temp=txtCode;
        try {
            group = InetAddress.getByName("228.5.6.7");
            s = new MulticastSocket(6789);
            //s.setLoopbackMode(true);
            s.joinGroup(group);
            //packet = new DatagramPacket(msg.getBytes(), msg.length(),group, 6789);
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Errr");
        }
    }
    @Override
    public void changedUpdate(DocumentEvent e) {
        //JOptionPane.showMessageDialog(null, ""+Thread.currentThread().getName());
        //Plain text components do not fire these events
        //JOptionPane.showMessageDialog(null, "cambio");
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        JOptionPane.showMessageDialog(null, "add "+Thread.currentThread().getName());
        if (!Thread.currentThread().getName().equals("CIAO"))
        {
            try {
                msg = temp.getText();
                packet = new DatagramPacket(msg.getBytes(), msg.length(),group, 6789); 
                msg=new String(packet.getData());
                //JOptionPane.showMessageDialog(null, msg);
                s.send(packet);
            } catch (IOException ex) {
                Logger.getLogger(MyDocumentListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        //JOptionPane.showMessageDialog(null, "add "+Thread.currentThread().getName());
        //JOptionPane.showMessageDialog(null, "scrivi");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        //JOptionPane.showMessageDialog(null, "remo "+Thread.currentThread().getName());
        //JOptionPane.showMessageDialog(null, "togli");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
