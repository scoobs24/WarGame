import java.awt.event.*;
import java.awt.*;
/**
 * Java Button
 */
public class Deal implements ActionListener{
    CardTable ct;
    public Deal (CardTable ct) {
        this.ct = ct;
    }
    public void actionPerformed (ActionEvent e) {
        Object src = e.getSource();
        ct.handleButtonPress(src);
    }
}