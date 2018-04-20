
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;

public class MyDocumentListener implements DocumentListener {

    private String msg;
    private InetAddress group;
    private MulticastSocket s;
    private DatagramPacket packet;
    private JTextArea temp;
    private Element elem;

    public MyDocumentListener(JTextArea txtCode) {
        temp = txtCode;
        try {
            group = InetAddress.getByName("228.5.6.7");
            s = new MulticastSocket(6789);
            //s.setLoopbackMode(true);
            s.joinGroup(group);
            //packet = new DatagramPacket(msg.getBytes(), msg.length(),group, 6789);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Errr");
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
        createBuffer(e);
        //JOptionPane.showMessageDialog(null, "add "+Thread.currentThread().getName());
        //JOptionPane.showMessageDialog(null, "scrivi");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        printInfo(e);
        createBuffer(e);
        //JOptionPane.showMessageDialog(null, "remo "+Thread.currentThread().getName());
        //JOptionPane.showMessageDialog(null, "togli");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void createBuffer(DocumentEvent e) {
        int offset = 0;
        int lenght = 0;
        if (!Thread.currentThread().getName().equals("CIAO")) {
            offset = e.getOffset();
            lenght = e.getLength();
            ByteBuffer buffer;
            if (lenght <= 125) {
                if (e.getType().equals(DocumentEvent.EventType.INSERT)) {
                    buffer = ByteBuffer.allocate((lenght * 2) + 6);
                    buffer.put(0, (byte) 0);
                    buffer.putInt(1, offset);
                    buffer.put(5, (byte) lenght);
                    try {
                        msg = temp.getText(offset, lenght);
                        buffer.position(6);
                        buffer.put(msg.getBytes(Charset.forName("UTF-16BE")));
                    } catch (BadLocationException ex) {
                        Logger.getLogger(MyDocumentListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    sendBuffer(buffer);
                } else if (e.getType().equals(DocumentEvent.EventType.REMOVE)) {
                    buffer = ByteBuffer.allocate(6);
                    buffer.put(0, (byte) 1);
                    buffer.putInt(1, offset);
                    buffer.put(5, (byte) lenght);
                    sendBuffer(buffer);
                }

            } else {
                int n = 0;
                while ((n + 125) < lenght) {
                    if (e.getType().equals(DocumentEvent.EventType.INSERT)) {
                        buffer = ByteBuffer.allocate(256);
                        buffer.put(0, (byte) 0);
                        buffer.putInt(1, offset);
                        buffer.put(5, (byte) 125);
                        try {
                            msg = temp.getText(offset, 125);
                            buffer.position(6);
                            buffer.put(msg.getBytes(Charset.forName("UTF-16BE")));
                        } catch (BadLocationException ex) {
                            Logger.getLogger(MyDocumentListener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        sendBuffer(buffer);
                        offset = offset + 125;
                    } else if (e.getType().equals(DocumentEvent.EventType.REMOVE)) {
                        buffer = ByteBuffer.allocate(6);
                        buffer.put(0, (byte) 1);
                        buffer.putInt(1, offset);
                        buffer.put(5, (byte) 125);
                        sendBuffer(buffer);
                    }
                    n = n + 125;
                }
                lenght = lenght - n;
                if (lenght > 0) {
                    if (e.getType().equals(DocumentEvent.EventType.INSERT)) {
                        buffer = ByteBuffer.allocate((lenght * 2) + 6);
                        buffer.put(0, (byte) 0);
                        buffer.putInt(1, offset);
                        buffer.put(5, (byte) lenght);
                        try {
                            msg = temp.getText(offset, lenght);
                            buffer.position(6);
                            buffer.put(msg.getBytes(Charset.forName("UTF-16BE")));
                        } catch (BadLocationException ex) {
                            Logger.getLogger(MyDocumentListener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        sendBuffer(buffer);
                    } else if (e.getType().equals(DocumentEvent.EventType.REMOVE)) {
                        buffer = ByteBuffer.allocate(6);
                        buffer.put(0, (byte) 1);
                        buffer.putInt(1, offset);
                        buffer.put(5, (byte) lenght);
                        sendBuffer(buffer);
                    }
                }
            }
        }
    }

    public void sendBuffer(ByteBuffer buffer) {
        try {
            packet = new DatagramPacket(buffer.array(), buffer.capacity(), group, 6789);
            msg = new String(buffer.array());
            System.out.println(Arrays.toString(buffer.array()));
            //System.out.println(buffer.getChar(6));
            //JOptionPane.showMessageDialog(null, msg);
            s.send(packet);
        } catch (IOException ex) {
            Logger.getLogger(MyDocumentListener.class.getName()).log(Level.SEVERE, null, ex);
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
