
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
import javax.swing.text.Document;
import javax.swing.text.Element;

public class MyDocumentListener implements DocumentListener {
    private String msg;
    private InetAddress group;
    private MulticastSocket s;
    private DatagramPacket packet;
    private JTextArea temp;
    private Element elem;
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
       printInfo(e);
       /*JOptionPane.showMessageDialog(null, "add "+Thread.currentThread().getName());
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
        }*/
         sendText();
        //JOptionPane.showMessageDialog(null, "add "+Thread.currentThread().getName());
        //JOptionPane.showMessageDialog(null, "scrivi");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        printInfo(e);
        sendText();
        //JOptionPane.showMessageDialog(null, "remo "+Thread.currentThread().getName());
        //JOptionPane.showMessageDialog(null, "togli");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void sendText(){
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
    }
    public void printInfo(DocumentEvent documentEvent) {
    System.out.println("Offset: " + documentEvent.getOffset());
    System.out.println("Length: " + documentEvent.getLength());
    DocumentEvent.EventType type = documentEvent.getType();
    String typeString = null;
    if (type.equals(DocumentEvent.EventType.CHANGE)) {
      typeString = "Change";
    } else if (type.equals(DocumentEvent.EventType.INSERT)) {
      typeString = "Insert";
    } else if (type.equals(DocumentEvent.EventType.REMOVE)) {
      typeString = "Remove";
    }
    System.out.println("Type  : " + typeString);
    Document documentSource = documentEvent.getDocument();
    Element rootElement = documentSource.getDefaultRootElement();
    DocumentEvent.ElementChange change = documentEvent.getChange(rootElement);
    System.out.println("Change: " + change);
  }
}
