
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author thumchan.13108
 */
public class ListenJoin extends SwingWorker<Void, Socket> {

    String msg;
    JTable temp = new JTable();
    DefaultTableModel model;
    JTextArea code;

    public ListenJoin(JTable tblCanali, JTextArea txtCode) {        
        temp = tblCanali;
        code = txtCode;
        model = (DefaultTableModel) temp.getModel();
    }

    @Override
    protected Void doInBackground() throws Exception {
        try {
            ServerSocket listener = new ServerSocket(9090);
            try {
                while (true) {
                    Socket socket = listener.accept();
                    publish(socket);
                }
            } finally {
                listener.close();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Errr");
            Logger.getLogger(ListenGlobal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

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
                } else if (buf.get(0) == 8) {
                    do {
                        lenght = buf.get(5);
                        offset = buf.getInt(1);
                        if (lenght >0) {
                            Thread.currentThread().setName("CIAO");
                            msg = new String(Arrays.copyOfRange(buf.array(), 6, 6 + (lenght * 2)), Charset.forName("UTF-16BE"));
                            code.insert(msg, offset);
                            in.read(buffer);
                            buf = ByteBuffer.wrap(buffer);
                            Thread.currentThread().setName("Main");
                        }
                    } while (buf.get(0) == 8 && lenght == 125);
                }
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ListenJoin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
