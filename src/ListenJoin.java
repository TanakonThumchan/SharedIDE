
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 * Thread in ascolto per ricevere risposte dagli altri client <br>
 * In base al tipo di messaggio ricevuto può eseguire delle operazioni
 */
public class ListenJoin extends SwingWorker<Void, Socket> {

    String msg;
    JTable temp = new JTable();
    DefaultTableModel model;
    DefaultTableModel userList;
    JTextArea code;
    public Socket socket;
    public ServerSocket listener;
    JDialog parent;
    public static int port;

    /**
     * Inizializza alcuni componenti grafici
     *
     * @param tblCanali Tabella contenente la lista delle collaborazione
     * disponibile
     * @param txtCode Casella di testo per scrivere il codice
     * @param parent finestra di selezione elenco delle collaborazione
     * @param userList Lista degli utenti
     */
    public ListenJoin(JTable tblCanali, JTextArea txtCode, JDialog parent, JTable userList) {
        this.parent = parent;
        this.userList = (DefaultTableModel) userList.getModel();
        temp = tblCanali;
        code = txtCode;
        model = (DefaultTableModel) temp.getModel();
    }

    /**
     * Resta in ascolto per ricevere il collegamentto socket dagli altri client
     */
    @Override
    protected Void doInBackground() throws Exception {
        try {
            listener = new ServerSocket(9090);

            while (true) {

                socket = new Socket();
                socket = listener.accept();
                publish(socket);
            }

        } catch (SocketException ex) {
            System.out.println(ex);
            return null;
        }
    }

    /**
     * Riceve il collegamento socket e in base in base al tipo di messaggio
     * ricevuto può aggionare la lista delle collaborazione disponibile o
     * ricevere il testo iniziale dagli altri client quando si è appena
     * collegato alla collaborazione
     *
     * @param socks Collegameto socket
     */
    @Override
    protected void process(List<Socket> socks) {
        for (Socket socket : socks) {
            byte[] buffer = new byte[256];
            String ip;
            String name;
            int offset;
            byte lenght = 0;
            String msg;
            try {
                DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                in.read(buffer);
                ByteBuffer buf = ByteBuffer.wrap(buffer);
                if (buf.get(0) == 3) {
                    ip = new String(Arrays.copyOfRange(buf.array(), 1, 31), Charset.forName("UTF-16BE"));
                    name = new String(Arrays.copyOfRange(buf.array(), 31, 255), Charset.forName("UTF-16BE"));
                    //name = new String(Arrays.copyOfRange(buf.array(), 1, 255), Charset.forName("UTF-16BE"));
                    //model.addRow(new Object[]{name, ip});
                    model.addRow(new Object[]{name, socket.getInetAddress().toString().replace("/", "")});
                } else if (buf.get(0) == 5) {
                    JoinDialog.accepted = true;
                    port = buf.getInt(1);
                    in.read(buffer);
                    buf = ByteBuffer.wrap(buffer);
                    if (buf.get(0) == 8 && port > 0) {
                        code.setText("");
                        do {
                            lenght = buf.get(5);
                            offset = buf.getInt(1);
                            if (lenght > 0) {
                                Thread.currentThread().setName("CIAO");
                                msg = new String(Arrays.copyOfRange(buf.array(), 6, 6 + (lenght * 2)), Charset.forName("UTF-16BE"));
                                code.insert(msg, offset);
                                in.read(buffer);
                                buf = ByteBuffer.wrap(buffer);
                                Thread.currentThread().setName("Main");
                            }
                        } while (buf.get(0) == 8 && lenght == 125);
                        Thread.sleep(100);
                        /*in.read(buffer);
                        buf = ByteBuffer.wrap(buffer);*/
                        while (buf.get(0) == 7) {
                            name = new String(Arrays.copyOfRange(buf.array(), 1, 255), Charset.forName("UTF-16BE"));
                            userList.addRow(new Object[]{name});
                            in.read(buffer);
                            buf = ByteBuffer.wrap(buffer);
                        }
                        parent.dispose();
                    } else {
                        JOptionPane.showConfirmDialog(null, "Richiesta rifiutata", "Rifiutata", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                        JoinDialog.accepted = false;
                        parent.dispose();
                    }
                }
                socket.close();
            } catch (IOException ex) {
                System.out.println(ex + " ListenJoin");
                Logger.getLogger(ListenJoin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ListenJoin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
