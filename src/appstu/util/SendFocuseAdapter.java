package appstu.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class SendFocuseAdapter extends KeyAdapter {
    private JFrame adaptee;
    private JTextField jTextField = null;
    private JComboBox jComboBox = null;
    
    public SendFocuseAdapter(JTextField ss) {
        this.jTextField = ss;
    }
    
    public SendFocuseAdapter(JComboBox ss) {
        this.jComboBox = ss;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (jTextField instanceof JTextField) {
                this.jTextField.requestFocus();
            }
            if (jComboBox instanceof JComboBox) {
                this.jComboBox.requestFocus();
            }
        }
    }
}
