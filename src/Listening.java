
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.text.DefaultCaret;


public class Listening extends SwingWorker<Void, String>{
    private JTextArea temp;
    private String msg;
    private int pos;
    private DefaultCaret caret;
    public Listening(JTextArea txtCode){
        temp=txtCode;
        caret = (DefaultCaret)txtCode.getCaret();
        caret.setUpdatePolicy(DefaultCaret.UPDATE_WHEN_ON_EDT);
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        try
        {
            boolean go=true;
            InetAddress group = InetAddress.getByName("228.5.6.7");
            MulticastSocket s = new MulticastSocket(6789);
            //s.setLoopbackMode(true);
            s.joinGroup(group);        
            while(go==true){
                byte[] buf = new byte[256];
                DatagramPacket recv = new DatagramPacket(buf, buf.length);
                s.receive(recv);
                msg=new String(recv.getData());
                publish(msg);
            }
            s.leaveGroup(group);
        }      
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Errr");
            Logger.getLogger(ListenGlobal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @Override
    protected void process(List<String> messages) {
        for (String message : messages) {
            String tempo=new String();
            int offset;
            int lenght;
            tempo=message.replaceAll("\0","");
            offset=Integer.parseInt(tempo.substring(1,4));
            lenght=Integer.parseInt(tempo.substring(4,7));
            Thread.currentThread().setName("CIAO");            
            if (tempo.startsWith("0"))
            {
                temp.insert(tempo.substring(7),offset);
            }
            else if (tempo.startsWith("1"))
            {
                temp.replaceRange("", offset, offset+lenght);
            }
            //JOptionPane.showMessageDialog(null, tempo);
            //pos = temp.getCaretPosition();
            //temp.setText(tempo);
            //temp.setCaretPosition(pos);
            
             
            Thread.currentThread().setName("Main");
        }
    }
}
