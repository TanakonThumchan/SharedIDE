
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
    JTextArea code;
    public Socket socket;
    public ServerSocket listener;
    JDialog parent;
    /**
     * Inizializza alcuni componenti grafici
     *
     * @param tblCanali Tabella contenente la lista delle collaborazione
     * disponibile
     * @param txtCode Casella di testo per scrivere il codice
     * @param parent finestra di selezione elenco delle collaborazione
     */
    public ListenJoin(JTable tblCanali, JTextArea txtCode,JDialog parent) {
        this.parent=parent;
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
                    model.addRow(new Object[]{name, ip});
                } else if (buf.get(0) == 5) {

                } else if (buf.get(0) == 8) {
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
                    parent.dispose();
                }
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ListenJoin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
