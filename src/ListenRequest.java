
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thumchan.13108
 */
public class ListenRequest implements Runnable{

    @Override
    public void run() {
        byte[] buffer;
        Socket socket;
        DataInputStream in;
        try {
            ServerSocket listener = new ServerSocket(9090);
            while (true){
                buffer = new byte[256];
                socket = listener.accept();
                in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                in.read(buffer);
            }
        } catch (IOException ex) {
            Logger.getLogger(ListenRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
