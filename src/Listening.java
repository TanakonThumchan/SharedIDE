
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
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
    public static int port;
    public MulticastSocket s;
    private DefaultTableModel model;
    /**
     * @param txtCode Casela di testo
     */
    public Listening(JTextArea txtCode,JTable userList) {
        port = 3000;
        temp = txtCode;
        caret = (DefaultCaret) txtCode.getCaret();
        caret.setUpdatePolicy(DefaultCaret.UPDATE_WHEN_ON_EDT);
        model= (DefaultTableModel) userList.getModel();
        portCheck();
    }
    
    public Listening(JTextArea txtCode,int port,JTable userList){
        this.port = port;
        temp = txtCode;
        model= (DefaultTableModel) userList.getModel();
        caret = (DefaultCaret) txtCode.getCaret();
        caret.setUpdatePolicy(DefaultCaret.UPDATE_WHEN_ON_EDT);
    }

    /**
     * Stabilisce la connessione e riceve i pacchetti
     */
    @Override
    protected Void doInBackground() throws Exception {
        try {
            InetAddress group = InetAddress.getByName("228.5.6.7");
            s = new MulticastSocket(port);
            s.setLoopbackMode(true);
            s.joinGroup(group);
            while (true) {
                byte[] buf = new byte[256];
                DatagramPacket recv = new DatagramPacket(buf, buf.length);
                s.receive(recv);
                ByteBuffer bytebu = ByteBuffer.wrap(buf);
                publish(bytebu);
            }
        } catch (IOException ex) {
            //JOptionPane.showMessageDialog(null, "Errr");
            //Logger.getLogger(Listening.class.getName()).log(Level.SEVERE, null, ex);
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
            String name="";
            String ip="";
            /*tempo=message.replaceAll("\0","");
            offset=Integer.parseInt(tempo.substring(1,4));
            lenght=Integer.parseInt(tempo.substring(4,7));*/
            buffer = Arrays.copyOfRange(message.array(), 6, 6 + (lenght * 2));
            tempo = new String(buffer, Charset.forName("UTF-16BE"));
            Thread.currentThread().setName("CIAO");
            
            switch (message.get(0)) {
                case 0:
                    temp.insert(tempo, offset);
                    break;
                case 1:
                    temp.replaceRange("", offset, offset + lenght);
                    break;
                case 6:
                    name = new String(Arrays.copyOfRange(message.array(), 1, 255), Charset.forName("UTF-16BE"));
                    userLeave(name);
                    break;
                case 7:
                    name = new String(Arrays.copyOfRange(message.array(), 1, 255), Charset.forName("UTF-16BE"));
                    userAdd(name);
                    break;
                case 9:
                    responseCheck();
                    break;
                default:
                    break;
            }
            
            //JOptionPane.showMessageDialog(null, tempo);
            //pos = temp.getCaretPosition();
            //temp.setText(tempo);
            //temp.setCaretPosition(pos);

            Thread.currentThread().setName("Main");
        }
    }

    /**
     * Rimuove l'utente dalla tabella
     * @param name
     */
    public void userLeave(String name){
        for  (int i=0;i<model.getRowCount();i++){
            if (model.getValueAt(i, 0).equals(name))
            {
                model.removeRow(i);
            }
        }
    }
    
    /**
     * Aggiunte l'utente alla tabella
     * @param name
     */
    public void userAdd(String name){
        model.addRow(new Object[]{name});
    }
    
    /**
     * Invia un messaggio per verificare se il canale è libero
     */
    public void portCheck() {
        while (true) {
            try {
                InetAddress group = InetAddress.getByName("228.5.6.7");
                MulticastSocket s = new MulticastSocket(port);
                //s.setLoopbackMode(true);
                s.setSoTimeout(5000);
                s.joinGroup(group);
                ByteBuffer buffer = ByteBuffer.allocate(5);
                buffer.put((byte) 9);
                buffer.putInt(0);
                DatagramPacket packet = new DatagramPacket(buffer.array(), buffer.capacity(), group, port);
                s.send(packet);
                byte[] buf = new byte[256];
                DatagramPacket recv = new DatagramPacket(buf, buf.length);                
                s.receive(recv);
                buffer=ByteBuffer.wrap(buf);
                if (buffer.get(0)!=10){
                    System.out.println(port);
                    break;
                }
                s.leaveGroup(group);
                port++;
            } catch (SocketTimeoutException e) {
                /*System.out.println(e.getMessage());
                System.out.println("si");*/
                System.out.println(port);
                break;
            }
            catch(Exception e){
                port++;
                continue;
            }
        }
    }

    /**
     * Segnala che il canale è gia occupata
     */
    public void responseCheck() {
        try {
            System.out.println("resp");
            InetAddress group = InetAddress.getByName("228.5.6.7");
            MulticastSocket s = new MulticastSocket(port);
            ByteBuffer buffer = ByteBuffer.allocate(5);
            buffer.put((byte) 10);
            buffer.putInt(0);
            DatagramPacket packet = new DatagramPacket(buffer.array(), buffer.capacity(), group, port);
            s.send(packet);
        } catch (Exception e) {

        }
    }
}
