
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * Thread in ascolto per ricevere le richieste di partecipazione dagli altri client<br>
 * L'utente può decidere se accettare la richiesta o meno.
 */
public class ListenRequest implements Runnable {

    JTextArea temp;

    /**
     * @param txtCode Casella di testo
     */
    public ListenRequest(JTextArea txtCode) {
        temp = txtCode;
    }

    /**
     * Riceve la richiesta di partecipazione e chiede all'utente se accettare la richiesta<br>
     * L'utente accetta la richista: invia il contenuto della casella di testo al client.<br>
     * L'utente rifiuta la richiesta: invia il messaggio di rifiuto.
     */
    @Override
    public void run() {
        byte[] buffer;
        Socket socket;
        DataInputStream in;
        String ip = "";
        String name = "";
        try {
            ServerSocket listener = new ServerSocket(9091);
            while (true) {
                buffer = new byte[256];
                socket = listener.accept();
                in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                in.read(buffer);
                ByteBuffer buf = ByteBuffer.wrap(buffer);
                if (buf.get(0) == 4) {
                    ip = new String(Arrays.copyOfRange(buf.array(), 1, 31), Charset.forName("UTF-16BE"));
                    name = new String(Arrays.copyOfRange(buf.array(), 31, 255), Charset.forName("UTF-16BE"));
                }
                int resul = JOptionPane.showConfirmDialog(null, name, "Richiesta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                Socket client = new Socket(ip, 9090);
                DataOutputStream out = new DataOutputStream(client.getOutputStream());
                if (resul == JOptionPane.YES_OPTION) {
                    System.out.println("si");
                    String text = temp.getText();
                    int lenght = text.length();
                    int offset = 0;
                    try {
                        if (lenght <= 125) {
                            buf = ByteBuffer.allocate((lenght * 2) + 6);
                            buf.put(0, (byte) 8);
                            buf.putInt(1, 0);
                            buf.put(5, (byte) lenght);
                            buf.position(6);
                            buf.put(text.getBytes(Charset.forName("UTF-16BE")));
                            out.write(buf.array());

                            out.close();
                            client.close();
                        } else {
                            while ((offset + 125) < lenght) {
                                buf = ByteBuffer.allocate(256);
                                buf.put(0, (byte) 8);
                                buf.putInt(1, 0);
                                buf.put(5, (byte) 125);
                                buf.put((text.substring(offset, offset + 125)).getBytes(Charset.forName("UTF-16BE")));
                                out.write(buf.array());
                                offset = offset + 125;
                            }
                            lenght = lenght - offset;
                            if (lenght > 0) {
                                buf = ByteBuffer.allocate((lenght * 2) + 6);
                                buf.put(0, (byte) 8);
                                buf.putInt(1, 0);
                                buf.put(5, (byte) lenght);
                                buf.position(6);
                                buf.put((text.substring(offset, offset + lenght)).getBytes(Charset.forName("UTF-16BE")));;
                                out.write(buf.array());

                                out.close();
                                client.close();
                            }
                        }

                    } catch (Exception e) {

                    }
                } else if (resul == JOptionPane.NO_OPTION) {
                    buf = ByteBuffer.allocate(6);
                    buf.put(0, (byte) 8);
                    buf.putInt(1, 0);
                    buf.put(5, (byte) 0);
                    out.write(buf.array());

                    out.close();
                    client.close();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ListenRequest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
