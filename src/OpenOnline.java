
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 */
public class OpenOnline extends javax.swing.JDialog {

    private DefaultTableModel model;
    private JTextArea txtCode;

    /**
     * Creates new form OpenOnline
     */
    public OpenOnline(java.awt.Frame parent, boolean modal, JTextArea txtCode) {
        super(parent, modal);
        initComponents();
        model = (DefaultTableModel) tblFile.getModel();
        this.txtCode = txtCode;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblFile = new javax.swing.JTable();
        btnApri = new javax.swing.JButton();
        btnAnnulla = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("File Salvati Online");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tblFile.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome file", "Ultima modifica"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblFile);
        if (tblFile.getColumnModel().getColumnCount() > 0) {
            tblFile.getColumnModel().getColumn(0).setResizable(false);
            tblFile.getColumnModel().getColumn(1).setResizable(false);
        }

        btnApri.setText("Apri");
        btnApri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApriActionPerformed(evt);
            }
        });

        btnAnnulla.setText("Annulla");
        btnAnnulla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnnullaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 209, Short.MAX_VALUE)
                        .addComponent(btnApri)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnnulla)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnApri)
                    .addComponent(btnAnnulla))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Scarica il file con dalla piattaforma online e apre il suo contenuto
     *
     * @param evt
     */
    private void btnApriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApriActionPerformed
        int column = 0;
        String fileName = "";
        int row = tblFile.getSelectedRow();
        if (row >= 0) {
            fileName = tblFile.getModel().getValueAt(row, column).toString();
        }
        String server = "ftp.thumchant.altervista.org";
        int port = 21;
        String user = "thumchant";
        String pass = "fidpivufdi60";
        if (!fileName.equals("")) {
            FTPClient ftpClient = new FTPClient();
            try {

                ftpClient.connect(server, port);
                ftpClient.login(user, pass);
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Seleziona la dictory per il salvataggio");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                fileChooser.setFileFilter(new FileNameExtensionFilter("File java", "java"));
                fileChooser.setSelectedFile(new File(fileName));
                int resul = fileChooser.showSaveDialog(null);
                if (resul == fileChooser.APPROVE_OPTION) {
                    String remoteFile = "/ProgettoEsame/File/"+fileName;
                    //String remoteFile = "/ProgettoEsame/File/Listening.java";
                    File downloadFile = fileChooser.getSelectedFile();
                    OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
                    boolean success = ftpClient.retrieveFile(remoteFile, outputStream);
                    outputStream.close();

                    if (success) {
                        SharedIDE.salva=true;                        
                        SharedIDE.file=downloadFile.getAbsolutePath();
                        System.out.println("File #1 has been downloaded successfully.");
                        try {
                            BufferedReader reader = new BufferedReader(new FileReader(downloadFile));
                            String line = null;
                            txtCode.setText(null);
                            while ((line = reader.readLine()) != null) {
                                txtCode.append(line + System.lineSeparator());
                            }
                            SharedIDE.change=false;
                            this.dispose();
                        } catch (IOException e) {
                            JOptionPane.showConfirmDialog(null, "Apertura del file fallita", "Errore di apertura", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        System.out.println("Can't download file");
                    }
                }

            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnApriActionPerformed

    private void btnAnnullaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnullaActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnAnnullaActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            System.setProperty("http.agent", "Chrome");
            String url = "http://thumchant.altervista.org/ProgettoEsame/UserGetFileList.php";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String urlParameters = "username=" + UserLogIn.user + "&mode=pc";

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

            //JSONObject jOb = new JSONObject(response.toString());
            JSONArray jas = new JSONArray(response.toString());
            System.out.println(jas.toString());
            for (int i = 0; i < jas.length(); i++) {
                JSONObject rec = jas.getJSONObject(i);
                String fileName = rec.getString("nome");
                String dataModifica = rec.getString("data");
                model.addRow(new Object[]{fileName, dataModifica});
            }
            //System.out.println(jOb.getString("nome"));
            //print result
            System.out.println(response.toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(SharedIDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SharedIDE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnnulla;
    private javax.swing.JButton btnApri;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblFile;
    // End of variables declaration//GEN-END:variables
}
