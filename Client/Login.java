package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import model.Constant;

public class Login extends JFrame implements ActionListener{
private JPanel s,z,x;
private JButton login,register;
private TextField user,pwd;
private JLabel u,p;
static String userName;

	public Login() {
		setTitle("µ«¬ºœµÕ≥");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		s = new JPanel();
		z = new JPanel();
		x = new JPanel();
		u=new JLabel("’À∫≈");
		p= new JLabel("√‹¬Î");
		user=new TextField(20);
		pwd=new TextField(20);
		pwd.setEchoChar('*');
		s.add(u,BorderLayout.WEST);
		s.add(user,BorderLayout.CENTER);
		z.add(p,BorderLayout.WEST);
		z.add(pwd, BorderLayout.CENTER);
		login=new JButton("µ«¬º");
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				int userId = Integer.parseInt(user.getText());
				userName=Constant.mapUser.get(userId);
				if (!Constant.mapPass.containsKey(userId)) {
					JOptionPane.showMessageDialog(c, "∏√’À∫≈…–Œ¥◊¢≤·£°", "¥ÌŒÛ", JOptionPane.ERROR_MESSAGE);
				} else if (!Constant.mapPass.get(userId).equals(pwd.getText())) {
					JOptionPane.showMessageDialog(c, "’À∫≈”Î√‹¬Î≤ª“ª÷¬£°", "¥ÌŒÛ", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(c, "”√ªß"+userName+"µ«¬Ω≥…π¶£°");
					
					Thread t=new Thread(){
						public void run() {
							FirstPage c=new FirstPage();
							
						}
					};
					t.start();
				}
                 }catch(NumberFormatException ex){
                	 JOptionPane.showMessageDialog(c, "«Î ‰»Î∫œ¿Ìµƒ’À∫≈£°", "¥ÌŒÛ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		register = new JButton("◊¢≤·");
		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Register r = new Register();

			}
		});
		x.add(login);
		x.add(register);

		c.add(s, BorderLayout.NORTH);
		c.add(z, BorderLayout.CENTER);
		c.add(x, BorderLayout.SOUTH);
		setSize(400,300);
		setLocationRelativeTo(null);
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	public static void main(String[] args) {
		Login a = new Login();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

	/*public void actionPerformed(ActionEvent e) {
		setVisible(false);
		FirstPage fs = new FirstPage();
	}
}
*/