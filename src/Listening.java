
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.text.DefaultCaret;


public class Listening extends SwingWorker<Void, ByteBuffer>{
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
                ByteBuffer bytebu= ByteBuffer.wrap(buf);
                publish(bytebu);
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
    protected void process(List<ByteBuffer> messages) {
        for (ByteBuffer message : messages) {
            String tempo=new String();
            byte[] buffer=new byte[256];
            int offset=message.getInt(1);
            byte lenght=message.get(5);
            
            /*tempo=message.replaceAll("\0","");
            offset=Integer.parseInt(tempo.substring(1,4));
            lenght=Integer.parseInt(tempo.substring(4,7));*/
            buffer= Arrays.copyOfRange(message.array(), 6, 6+(lenght*2));
            tempo=new String(buffer,Charset.forName("UTF-16BE"));
            Thread.currentThread().setName("CIAO");            
            if (message.get(0)==0)
            {
                temp.insert(tempo,offset);
            }
            else if (message.get(0)==1)
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
