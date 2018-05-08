
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
    JTextArea temp;
    public ListenRequest(JTextArea txtCode){
        temp=txtCode;
    }
    @Override
    public void run() {
        byte[] buffer;
        Socket socket;
        DataInputStream in;String ip="";String name="";
        try {
            ServerSocket listener = new ServerSocket(9091);
            while (true){
                buffer = new byte[256];
                socket = listener.accept();
                in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                in.read(buffer);
                ByteBuffer buf = ByteBuffer.wrap(buffer);
                if (buf.get(0) == 4) {
                    ip = new String(Arrays.copyOfRange(buf.array(), 1, 31), Charset.forName("UTF-16BE"));
                    name = new String(Arrays.copyOfRange(buf.array(), 31, 255), Charset.forName("UTF-16BE"));
                }
                int resul=JOptionPane.showConfirmDialog(null, name, "Richiesta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (resul==JOptionPane.YES_OPTION){
                    System.out.println("si");
                    String text=temp.getText();
                    int lenght=text.length();
                    int offset=0;int n = 0;
                    try{
                        Socket client = new Socket(ip, 9090);
                        DataOutputStream out = new DataOutputStream(client.getOutputStream());
                        buf = ByteBuffer.allocate(256);
                        while((n + 125) < lenght){
                            //xcvxcv
                        }
                        
                    }
                    catch(Exception e){
                        
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ListenRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
