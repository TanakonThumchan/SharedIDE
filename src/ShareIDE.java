
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
        copia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copia();
            }
        });
        incolla = new JMenuItem("Incolla");
        incolla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incolla();
            }
        });
        commenta = new JMenuItem("Commenta");
        commenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commenta();
            }
        });
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
        menuApri = new javax.swing.JMenuItem();
        menuSalva = new javax.swing.JMenuItem();
        menuSalvaNome = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuCerca = new javax.swing.JMenuItem();
        menuSostituisci = new javax.swing.JMenuItem();
        menuCommenta = new javax.swing.JMenuItem();
        menuJavaDoc = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Share IDE");

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

        menuApri.setText("Apri");
        menuApri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuApriActionPerformed(evt);
            }
        });
        jMenu1.add(menuApri);

        menuSalva.setText("Salva");
        jMenu1.add(menuSalva);

        menuSalvaNome.setText("Salva con nome");
        menuSalvaNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSalvaNomeActionPerformed(evt);
            }
        });
        jMenu1.add(menuSalvaNome);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        menuCerca.setText("Cerca");
        menuCerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCercaActionPerformed(evt);
            }
        });
        jMenu2.add(menuCerca);

        menuSostituisci.setText("Sostituisci");
        menuSostituisci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSostituisciActionPerformed(evt);
            }
        });
        jMenu2.add(menuSostituisci);

        menuCommenta.setText("Commenta");
        menuCommenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCommentaActionPerformed(evt);
            }
        });
        jMenu2.add(menuCommenta);

        menuJavaDoc.setText("JavaDoc");
        jMenu2.add(menuJavaDoc);

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

    private void menuCercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCercaActionPerformed
        SearchDialog s = new SearchDialog(this,true,txtCode);
        s.setVisible(true);
    }//GEN-LAST:event_menuCercaActionPerformed

    private void menuCommentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCommentaActionPerformed
        commenta();
    }//GEN-LAST:event_menuCommentaActionPerformed

    private void menuSostituisciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSostituisciActionPerformed
        ReplaceDialog r= new ReplaceDialog(this,true,txtCode);
        r.setVisible(true);
    }//GEN-LAST:event_menuSostituisciActionPerformed
    
    private void btnCompilaMouseClicked(java.awt.event.MouseEvent evt) {                                        
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
    }
    
    private void menuSalvaNomeActionPerformed(java.awt.event.ActionEvent evt) {                                              
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
        
    }

    private void menuApriActionPerformed(java.awt.event.ActionEvent evt) {                                         
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
    }                                          
    
    public void copia()
    {
        txtCode.copy();
    }
    
    public void incolla()
    {
        txtCode.paste();
    }
    
    public void taglia()
    {
        txtCode.cut();
    }
    
    public void commenta()
    {
        int start=txtCode.getSelectionStart();
        int end=txtCode.getSelectionEnd();
        txtCode.insert("/*", start);
        txtCode.insert("*/", end+2);
    }
    
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
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane linenumber;
    private javax.swing.JMenuItem menuApri;
    private javax.swing.JMenuItem menuCerca;
    private javax.swing.JMenuItem menuCommenta;
    private javax.swing.JMenuItem menuJavaDoc;
    private javax.swing.JMenuItem menuSalva;
    private javax.swing.JMenuItem menuSalvaNome;
    private javax.swing.JMenuItem menuSostituisci;
    private javax.swing.JTextArea txtCode;
    private javax.swing.JTextArea txtReturn;
    // End of variables declaration//GEN-END:variables
}
