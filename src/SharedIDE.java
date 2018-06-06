
import java.awt.Color;
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
import javax.swing.text.Document;

/**
 * Finestra pricipale del programma
 */
public class SharedIDE extends javax.swing.JFrame {

    private Clientside client;
    private Listening test;
    private TextLineNumber tln;
    private JPopupMenu popup;
    private boolean click;
    private JMenuItem copia;
    private JMenuItem incolla;
    private JMenuItem commenta;
    private boolean salva;
    private String file;
    private Listening collabora;
    private MyDocumentListener textChange;
    private Thread lisPub;
    private Thread lisReguest;
    
    /**
     * Costruttore: inizializza i componenti principale dell'applicazione
     */
    public SharedIDE() {
        initComponents();
        Thread.currentThread().setName("Main");
        salva = false;
        file = "";
        //txtCode.getDocument().addDocumentListener(new MyDocumentListener(txtCode));
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
        popup.add(copia);
        popup.add(incolla);
        popup.add(commenta);
        txtCode.setComponentPopupMenu(popup);
        /*Thread listenG=new Thread(new ListenGlobal(txtCode));
        listenG.start();*/
 /*test = new Listening(txtCode);
        test.execute();*/
        tln = new TextLineNumber(txtCode);
        linenumber.setRowHeaderView(tln);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCompila = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtReturn = new javax.swing.JTextArea();
        btnStart = new javax.swing.JButton();
        btnJoin = new javax.swing.JButton();
        linenumber = new javax.swing.JScrollPane();
        txtCode = new javax.swing.JTextArea();
        lblNome = new javax.swing.JLabel();
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

        btnStart.setText("Inizia");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnJoin.setText("Join");
        btnJoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJoinActionPerformed(evt);
            }
        });

        txtCode.setColumns(20);
        txtCode.setRows(5);
        txtCode.setTabSize(4);
        linenumber.setViewportView(txtCode);

        lblNome.setToolTipText("");

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
        menuSalva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSalvaActionPerformed(evt);
            }
        });
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
                    .addComponent(btnStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnJoin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(linenumber, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCompila, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnJoin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Avvia una finestra con le opzioni di ricerca del testo
     *
     * @param evt evento click dell'opzione Cerca
     * @see SearchDialog
     */
    private void menuCercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCercaActionPerformed
        SearchDialog s = new SearchDialog(this, true, txtCode);
        s.setVisible(true);
    }//GEN-LAST:event_menuCercaActionPerformed

    /**
     * Richiama il metodo 'commenta()' che trasforma il testo selezionato in
     * commento Java
     *
     * @param evt evento click dell'opzione Commenta
     */
    private void menuCommentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCommentaActionPerformed
        commenta();
    }//GEN-LAST:event_menuCommentaActionPerformed

    /**
     * Avvia una finestra con le opzioni di ricerca e sostituzione del testo
     *
     * @param evt evento click dell'opzione Sostituisci
     * @see ReplaceDialog
     */
    private void menuSostituisciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSostituisciActionPerformed
        ReplaceDialog r = new ReplaceDialog(this, true, txtCode);
        r.setVisible(true);
    }//GEN-LAST:event_menuSostituisciActionPerformed

    /**
     * Avvia una finestra con la lista delle collaborazioni attive
     *
     * @param evt evento click dell'opzione Join
     * @see JoinDialog
     */
    private void btnJoinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJoinActionPerformed
        if (JoinDialog.accepted == false) {
            JoinDialog j = new JoinDialog(this, true, txtCode);
            j.setVisible(true);
            if (!j.isVisible()) {
                if (JoinDialog.accepted == true) {
                    btnJoin.setText("Disconnect");
                    collabora = new Listening(txtCode,ListenJoin.port);
                    collabora.execute();
                    textChange= new MyDocumentListener(txtCode,ListenJoin.port);
                    txtCode.getDocument().addDocumentListener(textChange);
                }
            }
        }
        else{
            collabora.s.close();
            collabora.cancel(true);
            txtCode.getDocument().removeDocumentListener(textChange);
            btnJoin.setText("Join");
        }
    }//GEN-LAST:event_btnJoinActionPerformed

    /**
     * Crea la collaborazione e resta in ascolto per le richieste di
     * partecipazione degli altri host. Viene chiesto l'utente di inserire il
     * nome della collaborazione.<br>
     * Se la collaborazione è gia attiva allora viene disattivata.
     *
     * @param evt evento click dell'opzione Start
     * @see ListenPublic
     * @see ListenRequest
     */
    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        if (click == false) {
            String nome = (String) JOptionPane.showInputDialog(this, "Inserisci il nome", "Nome", JOptionPane.PLAIN_MESSAGE, null, null, null);
            if (nome != null && !nome.equals("")) {
                click = true;
                btnStart.setBackground(Color.GREEN);
                lblNome.setText(nome);
                lisPub = new Thread(new ListenPublic(nome));
                lisReguest = new Thread(new ListenRequest(txtCode));
                collabora = new Listening(txtCode);
                collabora.execute();
                lisPub.start();
                lisReguest.start();
                textChange=new MyDocumentListener(txtCode,collabora.port);
                txtCode.getDocument().addDocumentListener(textChange);
            }
        } else {
            collabora.s.close();
            collabora.cancel(true);
            txtCode.getDocument().removeDocumentListener(textChange);
            lblNome.setText(null);
            click = false;
            btnStart.setBackground(null);
        }
    }//GEN-LAST:event_btnStartActionPerformed

    /**
     * Salva il file aperto<br>
     * Se è la prima volta apre la finestra salva con nome
     *
     * @param evt
     */
    private void menuSalvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSalvaActionPerformed
        if (salva != false) {
            salva();
        } else {
            salvaNome();
        }
    }//GEN-LAST:event_menuSalvaActionPerformed

    /**
     * Salva il file, compila il codice e restituisce il messaggio del
     * compilatore
     *
     * @param evt evento click dell'opzione Compila
     */
    private void btnCompilaMouseClicked(java.awt.event.MouseEvent evt) {
        String buffer = new String();
        String temp = "";
        try (PrintWriter out = new PrintWriter("filename.java")) {
            buffer = txtCode.getText();
            out.println(buffer);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SharedIDE.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            buffer = new String();
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
            buffer = new String();
            while ((buffer = br.readLine()) != null) {
                System.out.println(buffer);
                txtReturn.append(buffer + System.lineSeparator());
            }
            int exitVal = child.waitFor(); // Se exitVal=0 tutto ok se è diverso da 0 c'è qualche errore
            System.out.println("Process exitValue: " + exitVal);
            JOptionPane.showMessageDialog(null, temp);
        } catch (IOException ex) {
            Logger.getLogger(SharedIDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(SharedIDE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Salva il file con il nome scelto dall'utente nella directory scelta
     *
     * @param evt evento click dell'opzione Salva Con Nome
     */
    private void menuSalvaNomeActionPerformed(java.awt.event.ActionEvent evt) {
        salvaNome();
    }

    /**
     * Mostra il contenuto del file scelto
     *
     * @param evt evento click dell'opzione Apri
     * @see JFileChooser
     */
    private void menuApriActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleziona il file da aprire");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("File java", "java"));
        int resul = fileChooser.showOpenDialog(null);
        if (resul == fileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileToOpen));
                String line = null;
                txtCode.setText(null);
                while ((line = reader.readLine()) != null) {
                    txtCode.append(line + System.lineSeparator());
                }
                file = fileToOpen.getAbsolutePath();
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Apertura del file fallita", "Errore di apertura", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Copia il testo selezionato
     */
    public void copia() {
        txtCode.copy();
    }

    /**
     * Incolla il testo copiato nella posizione del puntatore
     */
    public void incolla() {
        txtCode.paste();
    }

    /**
     * Copia il testo selezionato e lo cancella
     */
    public void taglia() {
        txtCode.cut();
    }

    /**
     * Trasforma il testo selezionato in commento Java
     */
    public void commenta() {
        int start = txtCode.getSelectionStart();
        int end = txtCode.getSelectionEnd();
        txtCode.insert("/*", start);
        txtCode.insert("*/", end + 2);
    }

    /**
     * Salva il file
     */
    public void salva() {
        BufferedWriter out;
        File fileToSave = new File(file);

    }

    /**
     * Salva il file con il nome scelto dall'utente nella directory scelta
     *
     * @see JFileChooser
     */
    public void salvaNome() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleziona la dictory per il salvataggio");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setFileFilter(new FileNameExtensionFilter("File java", "java"));
        int resul = fileChooser.showSaveDialog(null);
        if (resul == fileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getName().endsWith(".java")) {
                fileToSave = new File(fileToSave.toString() + ".java");
            }
            BufferedWriter out;
            try {
                out = new BufferedWriter(new FileWriter(fileToSave));
                out.write(txtCode.getText());
                out.close();
                salva = true;
                file = fileToSave.getAbsolutePath();
            } catch (IOException ex) {
                JOptionPane.showConfirmDialog(null, "Salvataggio del file fallito", "Errore di salvataggio", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            }
        }
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
            java.util.logging.Logger.getLogger(SharedIDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SharedIDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SharedIDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SharedIDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SharedIDE().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCompila;
    private javax.swing.JButton btnJoin;
    private javax.swing.JButton btnStart;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblNome;
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
