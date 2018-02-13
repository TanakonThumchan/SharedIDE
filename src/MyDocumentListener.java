
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MyDocumentListener implements DocumentListener {
    @Override
    public void changedUpdate(DocumentEvent e) {
        //Plain text components do not fire these events
        //JOptionPane.showMessageDialog(null, "cambio");
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        //JOptionPane.showMessageDialog(null, "scrivi");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        //JOptionPane.showMessageDialog(null, "togli");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
