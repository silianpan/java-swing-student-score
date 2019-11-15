package appstu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class JF_login extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 7920980929782158580L;
    private JLabel label;
    private JPanel panel;
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel jPanel1 = new JPanel();
    GridLayout gridLayout1 = new GridLayout();
    JLabel jLabel1 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JLabel jLabel2 = new JLabel();
    JPasswordField jPasswordField1 = new JPasswordField();
    JPanel jPanel2 = new JPanel();
    FlowLayout flowLayout1 = new FlowLayout();
    JButton jBlogin = new JButton();
    JButton jBexit = new JButton();
    JLabel jLabel3 = new JLabel();
    Border border1 = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.white, new Color(148, 145, 140));
    
    public JF_login() {
        try {
            jbInit();
            getContentPane().add(getPanel(), BorderLayout.NORTH);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        
        getContentPane().setLayout(borderLayout1);
        flowLayout1.setAlignment(FlowLayout.RIGHT);
        jLabel3.setForeground(Color.red);
        
        this.setResizable(false);
        this.setTitle("系统用户登录");
        Font _MenuItemFont = new Font("宋体", 0, 12);
        jTextField1.addKeyListener(new JF_login_jTextField1_keyAdapter(this));
        jPanel1.setBorder(border1);
        jPasswordField1.addKeyListener(new JF_login_jPasswordField1_keyAdapter(this));
        jBexit.addActionListener(new JF_login_jBexit_actionAdapter(this));
        jBlogin.addActionListener(new JF_login_jBlogin_actionAdapter(this));
        this.getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
        jTextField1.setText("");
        jLabel2.setText("密  码：");
        jLabel2.setFont(_MenuItemFont);
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jPasswordField1.setText("");
        jPanel2.setLayout(flowLayout1);
        jBlogin.setText("登录");
        jBlogin.setFont(_MenuItemFont);
        jBexit.setText("退出");
        jBexit.setFont(_MenuItemFont);
        jPanel1.add(jLabel1);
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel1.add(jTextField1);
        jPanel1.add(jLabel2);
        jPanel1.add(jPasswordField1);
        this.getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);
        jPanel2.add(jLabel3);
        jPanel2.add(jBlogin);
        jPanel2.add(jBexit);
        jLabel1.setText("用户名：");
        jLabel1.setFont(_MenuItemFont);
        gridLayout1.setColumns(2);
        gridLayout1.setHgap(2);
        gridLayout1.setVgap(5);
        gridLayout1.setRows(2);
        jPanel1.setLayout(gridLayout1);
        this.setSize(360, 200);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        
    }
    
    public void jTextField1_keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
            String sqlSelect = null;
            Vector vdata = null;
            sqlSelect = "select username from tb_user where userid = '" + jTextField1.getText().trim() + "'";
            appstu.util.RetrieveObject retrieve = new appstu.util.RetrieveObject();
            vdata = retrieve.getObjectRow(sqlSelect);
            System.out.println("vdata " + vdata.size());
            if (vdata.size() > 0) {
                jPasswordField1.requestFocus();
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "输入的用户ID不存在，请重新输入!!!", "系统提示", javax.swing.JOptionPane.ERROR_MESSAGE);
                jTextField1.requestFocus();
            }
            
        }
    }
    
    public void jPasswordField1_keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            ActionEvent login = new ActionEvent(jBlogin, 0, null);
            jBlogin_actionPerformed(login);
            
        }
    }
    
    public void jBexit_actionPerformed(ActionEvent e) {
        int result = javax.swing.JOptionPane.showOptionDialog(null, "是否退出系统登录?", "系统提示", javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE, null, new String[] { "是", "否" }, "否");
        if (result == javax.swing.JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        
    }
    
    public void jBlogin_actionPerformed(ActionEvent e) {
        if (jTextField1.getText().trim().length() == 0 || jPasswordField1.getPassword().length == 0) {
            javax.swing.JOptionPane.showMessageDialog(null, "用户密码不允许为空", "系统提示", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        String pass = null;
        pass = String.valueOf(jPasswordField1.getPassword());
        String sqlSelect = null;
        sqlSelect = "select count(*) from tb_user where userid = '" + jTextField1.getText().trim() + "' and pass = '" + pass + "'";
        Vector vdata = null;
        appstu.util.RetrieveObject retrieve = new appstu.util.RetrieveObject();
        vdata = retrieve.getObjectRow(sqlSelect);
        System.out.println(vdata.size() + " : " + vdata.get(0));
        
        if (Integer.parseInt(String.valueOf(vdata.get(0))) > 0) {
            AppMain frame = new AppMain();
            this.setVisible(false);
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "输入的口令不正确,请重新输入!!!", "系统提示", javax.swing.JOptionPane.ERROR_MESSAGE);
            jTextField1.setText(null);
            jPasswordField1.setText(null);
            jTextField1.requestFocus();
            return;
        }
        
    }
    
    protected JPanel getPanel() {
        if (panel == null) {
            panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.add(getLabel());
            
        }
        return panel;
    }
    
    protected JLabel getLabel() {
        if (label == null) {
            ImageIcon icon = new ImageIcon(this.getClass().getResource("/wsy/login.jpg"));
            label = new JLabel(icon);
            label.setPreferredSize(new Dimension(360, 80));
            label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        }
        return label;
    }
}

class JF_login_jBlogin_actionAdapter implements ActionListener {
    private JF_login adaptee;
    
    JF_login_jBlogin_actionAdapter(JF_login adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jBlogin_actionPerformed(e);
    }
}

class JF_login_jPasswordField1_keyAdapter extends KeyAdapter {
    private JF_login adaptee;
    
    JF_login_jPasswordField1_keyAdapter(JF_login adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        adaptee.jPasswordField1_keyPressed(e);
    }
}

class JF_login_jBexit_actionAdapter implements ActionListener {
    private JF_login adaptee;
    
    JF_login_jBexit_actionAdapter(JF_login adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        adaptee.jBexit_actionPerformed(e);
    }
}

class JF_login_jTextField1_keyAdapter extends KeyAdapter {
    private JF_login adaptee;
    
    JF_login_jTextField1_keyAdapter(JF_login adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        adaptee.jTextField1_keyPressed(keyEvent);
    }
}
