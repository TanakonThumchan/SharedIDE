
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 * Thread in ascolto per ricevere le richieste di partecipazione dagli altri
 * client<br>
 * L'utente può decidere se accettare la richiesta o meno.
 */
public class ListenRequest implements Runnable {

    private JTextArea temp;
    public Socket socket;
    public ServerSocket listener;
    DefaultTableModel model;

    /**
     * @param txtCode Casella di testo
     * @param userList Lista degli utenti in collaborazione
     */
    public ListenRequest(JTextArea txtCode,JTable userList) {
        Thread.currentThread().setName("ListenRequest");
        temp = txtCode;
        model= (DefaultTableModel) userList.getModel();
    }

    /**
     * Riceve la richiesta di partecipazione e chiede all'utente se accettare la
     * richiesta<br>
     * L'utente accetta la richista: invia il contenuto della casella di testo
     * al client.<br>
     * L'utente rifiuta la richiesta: invia il messaggio di rifiuto.
     */
    @Override
    public void run() {
        byte[] buffer;
        DataInputStream in;
        String ip = "";
        String name = "";
        try {
            listener = new ServerSocket(9091);
            while (true) {
                buffer = new byte[256];
                socket = listener.accept();
                in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                in.read(buffer);
                ByteBuffer buf = ByteBuffer.wrap(buffer);
                if (buf.get(0) == 4) {
                    //ip = new String(Arrays.copyOfRange(buf.array(), 1, 31), Charset.forName("UTF-16BE"));
                    ip = socket.getInetAddress().toString().replace("/", "");
                    name = new String(Arrays.copyOfRange(buf.array(), 31, 255), Charset.forName("UTF-16BE"));
                }
                int resul = JOptionPane.showConfirmDialog(null, name, "Richiesta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                Socket client = new Socket(ip, 9090);
                DataOutputStream out = new DataOutputStream(client.getOutputStream());
                if (resul == JOptionPane.YES_OPTION) {
                    if (UserLogIn.log == true) {
                        try {
                            System.setProperty("http.agent", "Chrome");
                            String url = "http://thumchant.altervista.org/ProgettoEsame/UserAddToCollaboration.php";
                            URL obj = new URL(url);
                            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                            con.setRequestMethod("POST");
                            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                            
                            File uploadFile = new File(SharedIDE.file);
                            String urlParameters = "username="+UserLogIn.user+"&userAdd="+name+"&fileName="+uploadFile.getName();
                            JOptionPane.showConfirmDialog(null, urlParameters, "Richiesta", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE);
                            con.setDoOutput(true);
                            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                            wr.writeBytes(urlParameters);
                            wr.flush();
                            wr.close();

                            BufferedReader serverRespose = new BufferedReader(new InputStreamReader(con.getInputStream()));
                            String inputLine;
                            StringBuilder response = new StringBuilder();

                            while ((inputLine = serverRespose.readLine()) != null) {
                                response.append(inputLine);
                            }
                            JOptionPane.showConfirmDialog(null, response.toString(), "Richiesta", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE);
                            in.close();
                        } catch (IOException ex) {
                            System.out.println("Error: " + ex.getMessage());
                            ex.printStackTrace();
                        }
                    }
                    System.out.println("Utente accettato");
                    //model.addRow(new Object[]{name,ip});
                    String text = temp.getText();
                    int lenght = text.length();
                    int offset = 0;
                    try {
                        InetAddress localHost = InetAddress.getLocalHost();
                        String address = ListenPublic.normalizzaIp(localHost.getHostAddress());
                        buf = ByteBuffer.allocate(256);
                        buf.put(0, (byte) 5);
                        buf.position(1);
                        /*for (int i = 0; i < 15; i++) {
                            buf.putChar(address.charAt(i));
                        }*/
                        buf.putInt(Listening.port);
                        out.write(buf.array());

                        if (lenght <= 125) {
                            buf = ByteBuffer.allocate((lenght * 2) + 6);
                            buf.put(0, (byte) 8);
                            buf.putInt(1, 0);
                            buf.put(5, (byte) lenght);
                            buf.position(6);
                            buf.put(text.getBytes(Charset.forName("UTF-16BE")));
                            out.write(buf.array());
                            
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
                            }
                        }
                        for (int i=0;i<model.getRowCount();i++){
                            buf = ByteBuffer.allocate(256);
                            buf.put(0, (byte) 7);
                            buf.position(1);
                            buf.put((model.getValueAt(i, 0).toString()).getBytes(Charset.forName("UTF-16BE")));
                            out.write(buf.array()); 
                        }
                        buf = ByteBuffer.allocate(256);
                        buf.put(0, (byte) 0);
                        out.write(buf.array());
                        out.close();
                        client.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (resul == JOptionPane.NO_OPTION) {
                    buf = ByteBuffer.allocate(5);
                    buf.put(0, (byte) 5);
                    buf.putInt(1, 0);
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
