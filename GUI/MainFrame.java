package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame implements ActionListener{

	
		// TODO 自动生成的方法存根
	public MainFrame() {
		setTitle("登录系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();//获取主面板
		c.setLayout(new GridLayout(5,1));//布局格式：5行1列
		JPanel jp = new JPanel();//多面板
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		JPanel jp4 = new JPanel();
		jp4.setBorder(new EmptyBorder(0,5,5,5));//设置边界距离
		jp4.setLayout(new GridLayout(1,2,10,0));//按钮面板布局
		/*输入框*/
		JLabel name = new JLabel("姓名：");
		jp.add(name);
		JTextField xm = new JTextField(20);
		jp.add(xm);
		JLabel stuNO = new JLabel("账号：");
		jp1.add(stuNO);
		JTextField xh = new JTextField(20);
		jp1.add(xh);
		JLabel password = new JLabel("密码：");
		jp2.add(password);
		JPasswordField mm = new JPasswordField(20);
		jp2.add(mm);
		/* 复选框*/
		JCheckBox jc = new JCheckBox("记住密码");
		jp3.add(jc);
		JCheckBox jc1 = new JCheckBox("自动登录");
		jp3.add(jc1);
		FocusListener f1 = new TFLisener(xm);//聚焦事件监听
		FocusListener f2 = new TFLisener(xh);
		FocusListener f3 = new TFLisener(mm);
		xm.addFocusListener(f1);
		xh.addFocusListener(f2);
		mm.addFocusListener(f3);
		
		// JPanel jp1 = new JPanel();
		JButton b1 = new JButton("登录");//按钮
		JButton b2 = new JButton("注册");
		b1.addActionListener(this);
		jp4.add(b1);
		jp4.add(b2);
		c.add(jp);
		c.add(jp1);
		c.add(jp2);
		c.add(jp3);
		c.add(jp4);
		
	}
	public static void main(String[] args) {
		MainFrame f = new MainFrame();
		f.setBounds(450, 200, 350, 250);
		f.setVisible(true);
}



class TFLisener implements FocusListener {
	JTextField jt;

	public TFLisener(JTextField j) {
		jt = j;
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO 自动生成的方法存根
		jt.setBackground(Color.yellow);
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO 自动生成的方法存根
		jt.setBackground(Color.WHITE);
	}

}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		setVisible(false);
		FristPage fs = new FristPage();
	}
}
    