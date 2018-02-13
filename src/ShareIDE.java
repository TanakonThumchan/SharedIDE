
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ShareIDE extends javax.swing.JFrame {

    Clientside client;

    public ShareIDE() {
        initComponents();
        txtCode.getDocument().addDocumentListener(new MyDocumentListener());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtCode = new javax.swing.JTextArea();
        btnDebug = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Share IDE");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        txtCode.setColumns(20);
        txtCode.setRows(5);
        txtCode.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtCodeCaretUpdate(evt);
            }
        });
        jScrollPane1.setViewportView(txtCode);

        btnDebug.setText("Debug");
        btnDebug.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDebugMouseClicked(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem4.setText("Nuovo");
        jMenu1.add(jMenuItem4);

        jMenuItem1.setText("Apri");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Salva");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Salva con nome");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnDebug, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDebug, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    //evento textbox per la scrittura 
    private void txtCodeCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCodeCaretUpdate
        String buffer;
        buffer=txtCode.getText();
        //JOptionPane.showMessageDialog(null, buffer);
    }//GEN-LAST:event_txtCodeCaretUpdate

    private void btnDebugMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDebugMouseClicked
        String buffer=new String();
        String temp = "";
        try(  PrintWriter out = new PrintWriter( "filename.java" )  )
            {
                buffer=txtCode.getText();
                out.println( buffer );
            } catch (FileNotFoundException ex) {
            Logger.getLogger(ShareIDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        try {
            buffer= new String();
            String command = "cmd /c javac Filename.java";
            Process child = Runtime.getRuntime().exec(command);
//            OutputStream out = child.getOutputStream();
            InputStream in = child.getInputStream();
            BufferedReader readd = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            while ((buffer = readd.readLine()) != null) {
                temp += buffer; // lettura risposta del cmd
            }
            InputStream stderr = child.getErrorStream(); // Ottengo lo stream dell'errore
            BufferedReader br = new BufferedReader(new InputStreamReader(stderr));
            buffer= new String();
            System.out.println("<ERROR>");
            while ( (buffer = br.readLine()) != null)
                System.out.println(buffer);
            System.out.println("</ERROR>");
            int exitVal = child.waitFor(); // Se exitVal=0 tutto ok se è diverso da 0 c'è qualche errore
            System.out.println("Process exitValue: " + exitVal);
            JOptionPane.showMessageDialog(null, temp);
        } catch (IOException ex) {
            Logger.getLogger(ShareIDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ShareIDE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDebugMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        client=new Clientside();
        JOptionPane.showMessageDialog(null, "Apertura");

    }//GEN-LAST:event_formWindowOpened

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(null);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ShareIDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShareIDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShareIDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShareIDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }     
   
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShareIDE().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDebug;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtCode;
    // End of variables declaration//GEN-END:variables
}
