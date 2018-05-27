
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.*;

/**
 * Thread in ascolto per ricevere i messaggi dagli altri client e aggiunge il testo ricevuto alla casella di testo
 * @deprecated 
 */
public class ListenGlobal implements Runnable {
    private JTextArea temp;
    private String msg;
    /**
     * @deprecated 
     * @param txtCode Casella di testo
     */
    public ListenGlobal(JTextArea txtCode)
    {
        temp=txtCode;
    }
    
    /**
     * @deprecated 
     */
    @Override
    public void run() {
        try
        {
            boolean go=true;
            /*Thread.currentThread().setName("CIAO");
            Thread.sleep(5000);
            //JOptionPane.showMessageDialog(null, Thread.currentThread().getName());
            
            Thread.sleep(1000);*/
            JOptionPane.showMessageDialog(null,"Runnig");
            InetAddress group = InetAddress.getByName("228.5.6.7");
            MulticastSocket s = new MulticastSocket(6789);
            //s.setLoopbackMode(true);
            s.joinGroup(group);
            /*DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(),group, 6789);
            s.send(packet);*/
            while (go==true){
            byte[] buf = new byte[256];
            DatagramPacket recv = new DatagramPacket(buf, buf.length);
            s.receive(recv);
            msg=new String(recv.getData());/*
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    Thread.currentThread().setName("CIAO");
                    temp.append(msg);
                    //JOptionPane.showMessageDialog(null, "added "+msg);
                    Thread.currentThread().setName("Main");
                }
            });*/
            update();
            }
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
    
    /**
     * @deprecated 
     */
    protected void update()
    {
        SwingWorker worker;
        worker = new SwingWorker<Boolean, Integer>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                Thread.currentThread().setName("CIAO");
                temp.append(msg);
                return true;
            }

            @Override
            protected void done() {
                // Finish sequence
            }
        };
        worker.execute();
    }
    
}