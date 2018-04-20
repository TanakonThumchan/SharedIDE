
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

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
    JList temp = new JList();

    public ListenJoin(JList lstCanali) {
        temp = lstCanali;
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
            try {
                DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                in.read(buffer);
            } catch (IOException ex) {
                Logger.getLogger(ListenJoin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
