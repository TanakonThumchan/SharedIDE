
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


public class Listening extends SwingWorker<Void, String>{
    private JTextArea temp;
    private String msg;
    public Listening(JTextArea txtCode){
        temp=txtCode;
    }
    
    @Override
    protected Void doInBackground() throws Exception {
        try
        {
            boolean go=true;
            InetAddress group = InetAddress.getByName("228.5.6.7");
            MulticastSocket s = new MulticastSocket(6789);
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
            Thread.currentThread().setName("CIAO");
            temp.append(message);
            Thread.currentThread().setName("Main");
        }
    }
}
