
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

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
    public static boolean salva;
    public static boolean change;
    public static String file;
    private Listening collabora;
    private MyDocumentListener textChange;
    private Thread lisPub;
    private Thread lisReguest;
    private ListenPublic lisPubRn;
    private ListenRequest lisReqRn;

    /**
     * Costruttore: inizializza i componenti principale dell'applicazione
     */
    public SharedIDE() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
        pnlUser.setVisible(false);
        /*Thread listenG=new Thread(new ListenGlobal(txtCode));
        listenG.start();*/
 /*test = new Listening(txtCode);
        test.execute();*/
        tln = new TextLineNumber(txtCode);
        linenumber.setRowHeaderView(tln);
        addChangeListener();
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
        pnlUser = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUserList = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuNew = new javax.swing.JMenuItem();
        menuApri = new javax.swing.JMenuItem();
        menuApriOnline = new javax.swing.JMenuItem();
        menuSalva = new javax.swing.JMenuItem();
        menuSalvaNome = new javax.swing.JMenuItem();
        menuSalvaOnline = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuCerca = new javax.swing.JMenuItem();
        menuSostituisci = new javax.swing.JMenuItem();
        menuCommenta = new javax.swing.JMenuItem();
        menuJavaDoc = new javax.swing.JMenuItem();
        User = new javax.swing.JMenu();
        menuLogIn = new javax.swing.JMenuItem();
        menuLogOut = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SharedIDE");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
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
        linenumber.setViewportView(txtCode);

        lblNome.setText("  ");

        tblUserList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User"
            }
        ));
        jScrollPane1.setViewportView(tblUserList);
        tblUserList.getAccessibleContext().setAccessibleParent(this);

        javax.swing.GroupLayout pnlUserLayout = new javax.swing.GroupLayout(pnlUser);
        pnlUser.setLayout(pnlUserLayout);
        pnlUserLayout.setHorizontalGroup(
            pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
        );
        pnlUserLayout.setVerticalGroup(
            pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUserLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenu1.setText("File");

        menuNew.setText("Nuovo");
        menuNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNewActionPerformed(evt);
            }
        });
        jMenu1.add(menuNew);

        menuApri.setText("Apri");
        menuApri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuApriActionPerformed(evt);
            }
        });
        jMenu1.add(menuApri);

        menuApriOnline.setText("Apri online");
        menuApriOnline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuApriOnlineActionPerformed(evt);
            }
        });
        jMenu1.add(menuApriOnline);

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

        menuSalvaOnline.setText("Salva online");
        menuSalvaOnline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSalvaOnlineActionPerformed(evt);
            }
        });
        jMenu1.add(menuSalvaOnline);

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

        User.setText("User");

        menuLogIn.setText("Log In");
        menuLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLogInActionPerformed(evt);
            }
        });
        User.add(menuLogIn);

        menuLogOut.setText("Log Out");
        menuLogOut.setEnabled(false);
        menuLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLogOutActionPerformed(evt);
            }
        });
        User.add(menuLogOut);

        jMenuBar1.add(User);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
                    .addComponent(linenumber))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(btnCompila, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnJoin, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(88, 88, 88))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCompila, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnJoin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlUser, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 223, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(linenumber)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
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
        SearchDialog s = new SearchDialog(this, false, txtCode);
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
        ReplaceDialog r = new ReplaceDialog(this, false, txtCode);
        r.setVisible(true);
    }//GEN-LAST:event_menuSostituisciActionPerformed

    /**
     * Avvia una finestra con la lista delle collaborazioni attive
     *
     * @param evt evento click dell'opzione Join
     * @see JoinDialog
     */
    private void btnJoinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJoinActionPerformed
        if (click == false) {
            DefaultTableModel model = (DefaultTableModel) tblUserList.getModel();
            if (JoinDialog.accepted == false) {
                JoinDialog j = new JoinDialog(this, true, txtCode, tblUserList);
                j.setVisible(true);
                if (!j.isVisible()) {
                    if (JoinDialog.accepted == true) {
                        model.addRow(new Object[]{JoinDialog.nome});
                        pnlUser.setVisible(true);
                        btnJoin.setText("Disconnect");
                        collabora = new Listening(txtCode, ListenJoin.port, tblUserList);
                        collabora.execute();
                        textChange = new MyDocumentListener(txtCode, ListenJoin.port);
                        txtCode.getDocument().addDocumentListener(textChange);
                        textChange.userJoin(JoinDialog.nome);
                    }
                }
            } else {
                model.setRowCount(0);
                pnlUser.setVisible(false);
                collabora.s.close();
                collabora.cancel(true);
                textChange.userLeave(JoinDialog.nome);
                txtCode.getDocument().removeDocumentListener(textChange);
                btnJoin.setText("Join");
                JoinDialog.accepted = false;
            }
        }
    }//GEN-LAST:event_btnJoinActionPerformed

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
     * Carica il file salvato online
     *
     * @param evt
     */
    private void menuSalvaOnlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSalvaOnlineActionPerformed
        if (UserLogIn.log == true) {
            salvaOnline();
        } else {
            JOptionPane.showConfirmDialog(null, "Eseguire prima l'autenticazione", "Autenticazione", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            autentication();
            salvaOnline();
        }
    }//GEN-LAST:event_menuSalvaOnlineActionPerformed

    /**
     * Scarica il file dalla piattaforma online e apre il contenuto
     *
     * @param evt
     */
    private void menuApriOnlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuApriOnlineActionPerformed
        if (UserLogIn.log == true) {
            OpenOnline dialogOpen = new OpenOnline(this, true, txtCode);
            dialogOpen.setVisible(true);
        } else {
            JOptionPane.showConfirmDialog(null, "Eseguire prima l'autenticazione", "Autenticazione", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            autentication();
            if (UserLogIn.log == true) {
                OpenOnline dialogOpen = new OpenOnline(this, true, txtCode);
                dialogOpen.setVisible(true);
            }
        }
    }//GEN-LAST:event_menuApriOnlineActionPerformed

    /**
     * Svuota la casella di testo per iniziare un file nuovo
     *
     * @param evt
     */
    private void menuNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNewActionPerformed
        txtCode.setText("");
        salva = false;
        change=false;
        file = "";
    }//GEN-LAST:event_menuNewActionPerformed

    /**
     * Apre la finestra di log in
     *
     * @param evt
     */
    private void menuLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLogInActionPerformed
        autentication();
    }//GEN-LAST:event_menuLogInActionPerformed

    private void menuLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLogOutActionPerformed
        UserLogIn.log = false;
        UserLogIn.user = "";
        menuLogIn.setEnabled(true);
        menuLogOut.setEnabled(false);
    }//GEN-LAST:event_menuLogOutActionPerformed

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
        DefaultTableModel model = (DefaultTableModel) tblUserList.getModel();
        if (JoinDialog.accepted == false) {
            if (click == false) {
                if (UserLogIn.log == false) {
                    int resul = JOptionPane.showConfirmDialog(null, "Avviare la collaborazione offline?", "Log in non eseguita", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (resul == JOptionPane.NO_OPTION) {
                        autentication();
                        if (UserLogIn.log == true) {
                            if (salva != false) {
                                salva();
                            } else {
                                salvaNome();
                            }
                        }
                    } else if (resul == JOptionPane.YES_OPTION) {
                        if (salva != false) {
                            salva();
                        } else {
                            salvaNome();
                        }
                    }
                }
                if (salva == true) {
                    String nome = (String) JOptionPane.showInputDialog(this, "Inserisci il nome della collaborazione", "Nome della collaborazione", JOptionPane.PLAIN_MESSAGE, null, null, null);
                    if (!nome.equals("")) {
                        if (UserLogIn.log == false) {
                            String username="";
                            JOptionPane.showConfirmDialog(null, "Collaborazione avviata in modalità offline", "Log in non eseguita", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                            do{
                                username= (String) JOptionPane.showInputDialog(this, "Inserisci il nome dell'utente", "Nome dell'utente", JOptionPane.PLAIN_MESSAGE, null, null, null);
                            }while(username==null || username.equals(""));
                            model.addRow(new Object[]{username});
                        } else {
                            model.addRow(new Object[]{UserLogIn.user});
                            salvaOnline();
                        }
                        pnlUser.setVisible(true);
                        click = true;
                        btnStart.setBackground(Color.GREEN);
                        lblNome.setText(nome);

                        lisPubRn = new ListenPublic(nome);
                        lisReqRn = new ListenRequest(txtCode, tblUserList);

                        lisPub = new Thread(lisPubRn);
                        lisReguest = new Thread(lisReqRn);

                        collabora = new Listening(txtCode, tblUserList);
                        collabora.execute();
                        lisPub.start();
                        lisReguest.start();
                        textChange = new MyDocumentListener(txtCode, collabora.port);
                        txtCode.getDocument().addDocumentListener(textChange);
                    }
                }
            } else {
                model.setRowCount(0);
                pnlUser.setVisible(false);
                collabora.s.close();
                collabora.cancel(true);
                lisPubRn.s.close();
                try {
                    lisReqRn.listener.close();
                } catch (IOException ex) {
                    Logger.getLogger(SharedIDE.class.getName()).log(Level.SEVERE, null, ex);
                }
                txtCode.getDocument().removeDocumentListener(textChange);
                lblNome.setText(null);
                click = false;
                btnStart.setBackground(null);
            }
        }
    }//GEN-LAST:event_btnStartActionPerformed

    /**
     * Azione prima della chiusura del programma
     *
     * @param evt
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (change == true) {
            int resul = JOptionPane.showConfirmDialog(null, "Salvare prima di uscire?", "Salvataggio", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resul == JOptionPane.YES_OPTION) {
                if (salva == true) {
                    salva();
                } else {
                    salvaNome();
                }
            }
        }
        if (JoinDialog.accepted == true) {
            textChange.userLeave(JoinDialog.nome);
        }
    }//GEN-LAST:event_formWindowClosing

    public void addChangeListener() {
        txtCode.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                change = true;
                txtReturn.setText("");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                change = true;
                txtReturn.setText("");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                change = true;
                txtReturn.setText("");
            }
        });
    }

    /**
     * Salva il file, compila il codice e restituisce il messaggio del
     * compilatore
     *
     * @param evt evento click dell'opzione Compila
     */
    private void btnCompilaMouseClicked(java.awt.event.MouseEvent evt) {
        if (salva == true) {
            txtReturn.setText("");
            String buffer = new String();
            String temp = "";
            try (PrintWriter out = new PrintWriter(file)) {
                buffer = txtCode.getText();
                out.print(buffer);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SharedIDE.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                buffer = new String();
                String command = "cmd /c javac " + file;
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
                if (exitVal == 0) {
                    txtReturn.setText("BUILD SUCCESSFUL");
                }
            } catch (IOException ex) {
                Logger.getLogger(SharedIDE.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(SharedIDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            salvaNome();
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
                salva = true;
                change = false;
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
        try {
            out = new BufferedWriter(new FileWriter(fileToSave));
            out.write(txtCode.getText());
            out.close();
            salva = true;
            change = false;
        } catch (IOException ex) {
            JOptionPane.showConfirmDialog(null, "Salvataggio del file fallito", "Errore di salvataggio", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        }

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
                change = false;
            } catch (IOException ex) {
                JOptionPane.showConfirmDialog(null, "Salvataggio del file fallito", "Errore di salvataggio", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Carica il file salvato sulla piattaforma online
     */
    public void salvaOnline() {
        String server = "ftp.thumchant.altervista.org";
        int port = 21;
        String user = "thumchant";
        String pass = "fidpivufdi60";

        if (salva != false) {
            salva();
        } else {
            salvaNome();
        }
        if (!file.equals("")) {
            try {
                System.setProperty("http.agent", "Chrome");
                String url = "http://thumchant.altervista.org/ProgettoEsame/UserPutFile.php";
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                File uploadFile = new File(file);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                String urlParameters = "username=" + UserLogIn.user + "&filename=" + uploadFile.getName() + "&lastChange=" + sdf.format(uploadFile.lastModified());
                System.out.println(urlParameters);

                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                if (!response.equals("NO")) {
                    FTPClient ftpClient = new FTPClient();
                    try {

                        ftpClient.connect(server, port);
                        ftpClient.login(user, pass);
                        ftpClient.enterLocalPassiveMode();

                        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                        // APPROACH #1: uploads first file using an InputStream
                        String remoteFile = "/ProgettoEsame/File/" + uploadFile.getName();
                        InputStream inputStream = new FileInputStream(uploadFile);

                        System.out.println("Start uploading first file");
                        boolean done = ftpClient.storeFile(remoteFile, inputStream);
                        inputStream.close();
                        if (done) {
                            JOptionPane.showConfirmDialog(null, "Salvataggio online completato", "Salvataggio online", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (IOException ex) {
                        System.out.println("Error: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showConfirmDialog(null, "L'utente non ha i permessi per salvare questo file online", "Errore di salvataggio", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                }
            } catch (MalformedURLException ex) {
                Logger.getLogger(SharedIDE.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SharedIDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Verifica le credenziali dell'utente
     */
    public void autentication() {
        UserLogIn dialog = new UserLogIn(this, true);
        dialog.setVisible(true);
        if (!dialog.isVisible()) {
            if (UserLogIn.log == true) {
                menuLogIn.setEnabled(false);
                menuLogOut.setEnabled(true);
                JOptionPane.showConfirmDialog(null, "Autenticazione riuscita", "Log in eseguita", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
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
    private javax.swing.JMenu User;
    private javax.swing.JButton btnCompila;
    private javax.swing.JButton btnJoin;
    private javax.swing.JButton btnStart;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblNome;
    private javax.swing.JScrollPane linenumber;
    private javax.swing.JMenuItem menuApri;
    private javax.swing.JMenuItem menuApriOnline;
    private javax.swing.JMenuItem menuCerca;
    private javax.swing.JMenuItem menuCommenta;
    private javax.swing.JMenuItem menuJavaDoc;
    private javax.swing.JMenuItem menuLogIn;
    private javax.swing.JMenuItem menuLogOut;
    private javax.swing.JMenuItem menuNew;
    private javax.swing.JMenuItem menuSalva;
    private javax.swing.JMenuItem menuSalvaNome;
    private javax.swing.JMenuItem menuSalvaOnline;
    private javax.swing.JMenuItem menuSostituisci;
    private javax.swing.JPanel pnlUser;
    private javax.swing.JTable tblUserList;
    private javax.swing.JTextArea txtCode;
    private javax.swing.JTextArea txtReturn;
    // End of variables declaration//GEN-END:variables
}
