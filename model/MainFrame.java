package model;

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

public class MainFrame extends JFrame implements ActionListener{
private JPanel s,z,x;
private JButton login,register;
private TextField user,pwd;
private JLabel u,p;
static String userName;

	public MainFrame() {
		setTitle("��¼ϵͳ");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		s = new JPanel();
		z = new JPanel();
		x = new JPanel();
		u=new JLabel("�˺�");
		p= new JLabel("����");
		s.add(u,BorderLayout.WEST);
		s.add(user,BorderLayout.CENTER);
		z.add(p,BorderLayout.WEST);
		z.add(pwd, BorderLayout.CENTER);
		login=new JButton("��¼");
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				int userId = Integer.parseInt(user.getText());
				userName=Constant.mapUser.get(userId);
				if (!Constant.mapPass.containsKey(userId)) {
					JOptionPane.showMessageDialog(c, "���˺���δע�ᣡ", "����", JOptionPane.ERROR_MESSAGE);
				} else if (!Constant.mapPass.get(userId).equals(pwd.getText())) {
					JOptionPane.showMessageDialog(c, "�˺������벻һ�£�", "����", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(c, "�û�"+userName+"��½�ɹ���");
					
					Thread t=new Thread(){
						public void run() {
							Client c=new Client();
							
						}
					};
					t.start();
				}
                 }catch(NumberFormatException ex){
                	 JOptionPane.showMessageDialog(c, "�����������˺ţ�", "����", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		register = new JButton("ע��");
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
		setSize(250, 120);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	public static void main(String[] args) {
		MainFrame a = new MainFrame();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		setVisible(false);
		FristPage fs = new FristPage();
	}
}
