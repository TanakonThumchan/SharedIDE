
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thumchan.13108
 */
public class ReplaceDialog extends javax.swing.JDialog {

    /**
     * Creates new form ReplaceDialog
     */
    JTextArea txtCode;
    int index; int start;
    Highlighter highlighter;
    public ReplaceDialog(java.awt.Frame parent, boolean modal, JTextArea txtCode) {
        super(parent, modal);
        initComponents();
        this.txtCode = txtCode;
        highlighter = txtCode.getHighlighter();
        index=0;start=0;
        txtCerca.getDocument().addDocumentListener(new DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    index=0;start=0;
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    index=0;start=0;
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    index=0;start=0;                    
                }
            }        
        );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        txtSostituisci1 = new javax.swing.JTextField();
        btnSostituisci = new javax.swing.JButton();
        btnSostituisciAll = new javax.swing.JButton();
        btnAnnulla = new javax.swing.JButton();
        btnCerca = new javax.swing.JButton();
        txtCerca = new javax.swing.JTextField();
        txtSostituisci = new javax.swing.JTextField();

        jButton3.setText("jButton3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnSostituisci.setText("Sosituisci");
        btnSostituisci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSostituisciActionPerformed(evt);
            }
        });

        btnSostituisciAll.setText("Sostituisci tutto");
        btnSostituisciAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSostituisciAllActionPerformed(evt);
            }
        });

        btnAnnulla.setText("Annulla");
        btnAnnulla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnnullaActionPerformed(evt);
            }
        });

        btnCerca.setText("Cerca");
        btnCerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCercaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCerca, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCerca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSostituisci)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSostituisciAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnnulla)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtSostituisci))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCerca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSostituisci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSostituisci)
                    .addComponent(btnSostituisciAll)
                    .addComponent(btnAnnulla)
                    .addComponent(btnCerca))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCercaActionPerformed
        String search = txtCerca.getText();
        highlighter.removeAllHighlights();
        if (!search.equals(""))
        {                    
            DefaultHighlighter.DefaultHighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);                 
            Pattern pattern = Pattern.compile( Pattern.quote(search) );
            Matcher matcher = pattern.matcher(txtCode.getText());                    
            if (matcher.find(index)){
                try {
                    highlighter.addHighlight(matcher.start(), matcher.end(), painter);
                    txtCode.setCaretPosition(matcher.start());
                    index=matcher.end();start=matcher.start();
                } catch (BadLocationException ex) {
                    Logger.getLogger(SearchDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else 
            {
                index=0;
                if (matcher.find(index)){
                    try {
                        highlighter.addHighlight(matcher.start(), matcher.end(), painter);
                        txtCode.setCaretPosition(matcher.start());
                        index=matcher.end();start=matcher.start();
                    } catch (BadLocationException ex) {
                        Logger.getLogger(SearchDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnCercaActionPerformed

    private void btnSostituisciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSostituisciActionPerformed
        String sostituisci = txtSostituisci.getText();
        String search = txtCerca.getText();
        if (!search.equals(""))
        {
            Pattern pattern = Pattern.compile( Pattern.quote(search) );
            Matcher matcher = pattern.matcher(txtCode.getText());
            if (index==0)
            {
                if (matcher.find(index))
                {
                txtCode.replaceRange(sostituisci, matcher.start(), matcher.end());
                txtCode.setCaretPosition(matcher.start());
                }
            }
            else if (index>0)
            {
                if (matcher.find(start))
                {
                    txtCode.replaceRange(sostituisci, matcher.start(), matcher.end());
                    txtCode.setCaretPosition(matcher.start());
                }
            }
        }
    }//GEN-LAST:event_btnSostituisciActionPerformed

    private void btnSostituisciAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSostituisciAllActionPerformed
        String sostituisci = txtSostituisci.getText();
        String search = txtCerca.getText();
        if (!search.equals(""))
        {
            Pattern pattern = Pattern.compile( Pattern.quote(search) );
            Matcher matcher = pattern.matcher(txtCode.getText());
            while(matcher.find()) {               
               txtCode.replaceRange(sostituisci, matcher.start(), matcher.end());
            }
        }
    }//GEN-LAST:event_btnSostituisciAllActionPerformed

    private void btnAnnullaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnullaActionPerformed
        highlighter.removeAllHighlights();
        this.dispose();
    }//GEN-LAST:event_btnAnnullaActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        highlighter.removeAllHighlights();
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnnulla;
    private javax.swing.JButton btnCerca;
    private javax.swing.JButton btnSostituisci;
    private javax.swing.JButton btnSostituisciAll;
    private javax.swing.JButton jButton3;
    private javax.swing.JTextField txtCerca;
    private javax.swing.JTextField txtSostituisci;
    private javax.swing.JTextField txtSostituisci1;
    // End of variables declaration//GEN-END:variables
}
