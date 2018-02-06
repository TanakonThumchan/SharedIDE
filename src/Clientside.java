
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author diù
 */
public class Clientside {

    Socket socket = null;
    Clientside(){
        try {
            socket = new Socket(InetAddress.getLocalHost(), 10000);
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Connessione Fallita", "Connessione", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        }
    }
    public String writing(String text) throws IOException {
        String temp = "";
        String buffer;
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while ((buffer = in.readLine()) != null) {
            temp += buffer;
        }
        return temp;
    }
}
