
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

/**
 * Thread in ascolto per ricevere le modifiche dagli altri client e aggiorna la
 * casella di testo
 *
 * @see SwingWorker
 */
public class Listening extends SwingWorker<Void, ByteBuffer> {

    private JTextArea temp;
    private int pos;
    private DefaultCaret caret;
    public int port;
    /**
     * @param txtCode Casela di testo
     */
    public Listening(JTextArea txtCode) {
        port=3000;
        temp = txtCode;
        caret = (DefaultCaret) txtCode.getCaret();
        caret.setUpdatePolicy(DefaultCaret.UPDATE_WHEN_ON_EDT);
        portCheck();
    }

    /**
     * Stabilisce la connessione e riceve i pacchetti
     */
    @Override
    protected Void doInBackground() throws Exception {
        try {
            InetAddress group = InetAddress.getByName("228.5.6.7");
            MulticastSocket s = new MulticastSocket(port);
            //s.setLoopbackMode(true);
            s.joinGroup(group);
            while (true) {
                byte[] buf = new byte[256];
                DatagramPacket recv = new DatagramPacket(buf, buf.length);
                s.receive(recv);
                ByteBuffer bytebu = ByteBuffer.wrap(buf);
                publish(bytebu);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Errr");
            Logger.getLogger(Listening.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * In base al tipo di messaggio ricevuto la funzione può aggiungere o
     * togliere testo dal TextArea
     *
     * @param messages Messaggio ricevuto
     */
    @Override
    protected void process(List<ByteBuffer> messages) {
        for (ByteBuffer message : messages) {
            String tempo = new String();
            byte[] buffer = new byte[256];
            int offset = message.getInt(1);
            byte lenght = message.get(5);

            /*tempo=message.replaceAll("\0","");
            offset=Integer.parseInt(tempo.substring(1,4));
            lenght=Integer.parseInt(tempo.substring(4,7));*/
            buffer = Arrays.copyOfRange(message.array(), 6, 6 + (lenght * 2));
            tempo = new String(buffer, Charset.forName("UTF-16BE"));
            Thread.currentThread().setName("CIAO");
            if (message.get(0) == 0) {
                temp.insert(tempo, offset);
            } else if (message.get(0) == 1) {
                temp.replaceRange("", offset, offset + lenght);
            }
            //JOptionPane.showMessageDialog(null, tempo);
            //pos = temp.getCaretPosition();
            //temp.setText(tempo);
            //temp.setCaretPosition(pos);

            Thread.currentThread().setName("Main");
        }
    }

    /**
     * Invia un messaggio per verificare se il canale è libero
     */
    public void portCheck() {
        while(true){
            try {
                InetAddress group = InetAddress.getByName("228.5.6.7");
                MulticastSocket s = new MulticastSocket(port);
                //s.setLoopbackMode(true);
                ByteBuffer buffer = ByteBuffer.allocate(5);
                buffer.put((byte)9);
                buffer.putInt(0);
                DatagramPacket packet = new DatagramPacket(buffer.array(), buffer.capacity(), group, port);
                s.send(packet);
                byte[] buf = new byte[256];
                DatagramPacket recv = new DatagramPacket(buf, buf.length);
                s.setSoTimeout(200);
                s.receive(recv);
                s.joinGroup(group);
                port++;
            } catch (Exception e) {
                /*System.out.println(e.getMessage());
                System.out.println("si");*/
                break;
            }
        }
    }
}
