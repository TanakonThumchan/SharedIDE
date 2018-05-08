
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author diù
 */
public class JoinDialog extends javax.swing.JDialog {

    private String msg;
    private Timer timer;
    private InetAddress group;
    private MulticastSocket s;
    private DatagramPacket packet;
    private ListenJoin thread;
    DefaultTableModel model;

    public JoinDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        timer = new Timer();
        thread = new ListenJoin(tblCanali);
        thread.execute();
        model = (DefaultTableModel) tblCanali.getModel();
        try {
            group = InetAddress.getByName("228.5.6.7");
            s = new MulticastSocket(6789);
            //s.setLoopbackMode(true);
            s.joinGroup(group);
            //packet = new DatagramPacket(msg.getBytes(), msg.length(),group, 6789);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Errr");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEntra = new javax.swing.JButton();
        btnAnnulla = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCanali = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNome = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnEntra.setText("Entra");
        btnEntra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntraActionPerformed(evt);
            }
        });

        btnAnnulla.setText("Annulla");
        btnAnnulla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnnullaActionPerformed(evt);
            }
        });

        tblCanali.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Indirizzo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblCanali);

        jScrollPane1.setViewportView(txtNome);

        jLabel1.setText("Nickname: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(179, Short.MAX_VALUE)
                        .addComponent(btnEntra)
                        .addGap(18, 18, 18)
                        .addComponent(btnAnnulla)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnnulla)
                    .addComponent(btnEntra))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnnullaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnullaActionPerformed
        thread.cancel(true);
        this.dispose();
    }//GEN-LAST:event_btnAnnullaActionPerformed

    private void btnEntraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntraActionPerformed
        String nome = txtNome.getText();
        if (!nome.equals("")) {
            int column = 1;
            int row = tblCanali.getSelectedRow();
            String value = "";
            String address = "";
            try {
                value = tblCanali.getModel().getValueAt(row, column).toString();
                Socket client = new Socket(value, 9091);
                DataOutputStream out = new DataOutputStream(client.getOutputStream());
                ByteBuffer bytebu = ByteBuffer.allocate(256);
                InetAddress localHost = InetAddress.getLocalHost();
                address = normalizzaIp(localHost.getHostAddress());
                bytebu.put(0, (byte) 4);
                bytebu.position(1);
                for (int i = 0; i < 15; i++) {
                    bytebu.putChar(address.charAt(i));
                }
                for (int i = 0; i < nome.length(); i++) {
                    bytebu.putChar(nome.charAt(i));
                }
                out.write(bytebu.array());
                client.close();
                out.close();
            } catch (Exception E) {
                JOptionPane.showConfirmDialog(null, "Seleziona un host", "", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            }
            System.out.println(value);
        } else {
            JOptionPane.showConfirmDialog(null, "Inserisci prima il nickname", "Nome assente", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEntraActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                aggiorna();
            }
        }, 0, 10000);
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        thread.cancel(true);
    }//GEN-LAST:event_formWindowClosed

    public void aggiorna() {
        ByteBuffer buffer = ByteBuffer.allocate(31);
        String address = "";
        model.setRowCount(0);
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            address = normalizzaIp(localHost.getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(JoinDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(address);
        buffer.put(0, (byte) 2);
        buffer.position(1);
        for (int i = 0; i < 15; i++) {
            buffer.putChar(address.charAt(i));
        }
        packet = new DatagramPacket(buffer.array(), buffer.capacity(), group, 6789);
        try {
            s.send(packet);
        } catch (IOException ex) {
            Logger.getLogger(JoinDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String normalizzaIp(String bruttoIp) {
        String res = "";
        String[] arrOfStr;
        arrOfStr = bruttoIp.split("\\.");
        for (int j = 0; j < 4; j++) {
            switch (arrOfStr[j].length()) {
                case 1:
                    res += "00" + arrOfStr[j];
                    break;
                case 2:
                    res += "0" + arrOfStr[j];
                    break;
                case 3:
                    res += arrOfStr[j];
                    break;
                default:
                    res = "Invalid ip";
                    break;
            }
            res += ".";
        }
        String newstr = res.substring(0, 15);
        return newstr;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnnulla;
    private javax.swing.JButton btnEntra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblCanali;
    private javax.swing.JTextPane txtNome;
    // End of variables declaration//GEN-END:variables
}
