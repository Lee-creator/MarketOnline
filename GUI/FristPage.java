package main;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.*;

@SuppressWarnings("serial")
public class FristPage extends JFrame {
	Container con = getContentPane();
	JPanel jp2,jp3,jp5;
	JSplitPane jp1;
	JScrollPane jp4;
	public FristPage() {
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp5 = new JPanel();
		jp5.setBorder(BorderFactory.createTitledBorder("好友列表"));//添加标题边框
		jp4 = new JScrollPane(jp5);//添加jp5面板
		jp4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//设置滚动条
		jp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jp4,new JLabel("未设置"));//分割
		jp1.setDividerSize(10);//分割条大小
		jp1.setDividerLocation(200);//分割条位置
		setBounds(350, 100, 700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setmenu();
		con.add(jp1);
	}
	void setmenu() {
		JMenuBar m = new JMenuBar();//创建菜单条
		/*创建一级菜单*/
		JMenu m1 = new JMenu("File");
		JMenu m2 = new JMenu("Edit");
		JMenu m3 = new JMenu("Settings");
		JMenu m4 = new JMenu("Help");
		m.add(m1);
		m.add(m2);
		m.add(m3);
		m.add(m4);
		setJMenuBar(m);
	}

}
