
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class MouseListener extends MouseAdapter {
    public void MousePressed(MouseEvent e){
        if(e.isPopupTrigger())
        {
            JOptionPane.showMessageDialog(null, "ciao");
        }
            
    }

    public void MouseReleased(MouseEvent e){
        if(e.isPopupTrigger())
        {
            JOptionPane.showMessageDialog(null, "ciao");
        }
            
    }

    public void MouseClicked(MouseEvent e){
            JOptionPane.showMessageDialog(null, "ciao");
    }
}
