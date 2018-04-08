
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ShareIDE extends javax.swing.JFrame {

    private Clientside client;
    private Listening test;
    private TextLineNumber tln;
    private JPopupMenu popup;
    private JMenuItem copia; private JMenuItem incolla; private JMenuItem commenta;
    public ShareIDE() {
        initComponents();
        Thread.currentThread().setName("Main");
        txtCode.getDocument().addDocumentListener(new MyDocumentListener(txtCode));
        popup = new JPopupMenu("Popup Menu");
        copia = new JMenuItem("Copia");
        incolla = new JMenuItem("Incolla");
        commenta = new JMenuItem("Commenta");
        popup.add(copia); popup.add(incolla); popup.add(commenta);
        txtCode.setComponentPopupMenu(popup);
        /*Thread listenG=new Thread(new ListenGlobal(txtCode));
        listenG.start();*/
        test = new Listening(txtCode);
        test.execute();
        tln = new TextLineNumber(txtCode);
        linenumber.setRowHeaderView( tln );
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCompila = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtReturn = new javax.swing.JTextArea();
        bntStart = new javax.swing.JButton();
        btnJoin = new javax.swing.JButton();
        linenumber = new javax.swing.JScrollPane();
        txtCode = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Share IDE");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnCompila.setText("Compila");
        btnCompila.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCompilaMouseClicked(evt);
            }
        });

        txtReturn.setEditable(false);
        txtReturn.setColumns(20);
        txtReturn.setRows(5);
        jScrollPane4.setViewportView(txtReturn);

        bntStart.setText("Inizia");

        btnJoin.setText("Join");

        txtCode.setColumns(20);
        txtCode.setRows(5);
        txtCode.setTabSize(4);
        linenumber.setViewportView(txtCode);

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

        jMenuItem5.setText("Cerca");
        jMenu2.add(jMenuItem5);

        jMenuItem6.setText("Sostituisci");
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("Commenta");
        jMenu2.add(jMenuItem7);

        jMenuItem8.setText("JavaDoc");
        jMenu2.add(jMenuItem8);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                    .addComponent(linenumber))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCompila, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                    .addComponent(bntStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnJoin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(linenumber)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCompila, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(bntStart, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnJoin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(266, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnCompilaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCompilaMouseClicked
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
            while ( (buffer = br.readLine()) != null)
            {
                System.out.println(buffer);
                txtReturn.append(buffer+System.lineSeparator());
            }
            int exitVal = child.waitFor(); // Se exitVal=0 tutto ok se è diverso da 0 c'è qualche errore
            System.out.println("Process exitValue: " + exitVal);
            JOptionPane.showMessageDialog(null, temp);
        } catch (IOException ex) {
            Logger.getLogger(ShareIDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ShareIDE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCompilaMouseClicked
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        //client=new Clientside();
        //JOptionPane.showMessageDialog(null, "Apertura");

    }//GEN-LAST:event_formWindowOpened

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleziona la dictory per il salvataggio");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setFileFilter(new FileNameExtensionFilter("File java","java"));
        int resul =fileChooser.showSaveDialog(null);
        if (resul==fileChooser.APPROVE_OPTION)
        {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getName().endsWith(".java")) 
            {
                fileToSave = new File(fileToSave.toString() + ".java"); 
            }
            BufferedWriter out; 
            try {
                out = new BufferedWriter(new FileWriter(fileToSave));
                out.write(txtCode.getText());
                out.close();
            } catch (IOException ex) {
                JOptionPane.showConfirmDialog(null, "Salvataggio del file fallito", "Errore di salvataggio", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            }            
        }
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleziona il file da aprire");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("File java","java"));
        int resul =fileChooser.showOpenDialog(null);
        if (resul==fileChooser.APPROVE_OPTION)
        {
            File fileToOpen = fileChooser.getSelectedFile();
            try
            {
                BufferedReader reader = new BufferedReader(new FileReader(fileToOpen));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    txtCode.append(line+System.lineSeparator());
                }
            }
            catch(IOException e)
            {
                JOptionPane.showConfirmDialog(null, "Apertura del file fallita", "Errore di apertura", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            }
        }
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
    private javax.swing.JButton bntStart;
    private javax.swing.JButton btnCompila;
    private javax.swing.JButton btnJoin;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane linenumber;
    private javax.swing.JTextArea txtCode;
    private javax.swing.JTextArea txtReturn;
    // End of variables declaration//GEN-END:variables
}
