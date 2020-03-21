package Client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.*;
import Server.jdbc;
@SuppressWarnings("serial")
public class FirstPage extends JFrame {
	Container con = getContentPane();
	JPanel jp2,jp3,jp5,jp6;
	JSplitPane jp1;
	JScrollPane jp4,jsp;
	JTabbedPane jtp;
	public FirstPage() {
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp5 = new JPanel();
		jp6 = new JPanel();
		jp6.setLayout(new GridLayout(50,1,4,30));
		jsp = new JScrollPane(jp6);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		try {
			jdbc.fill();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		addGoods();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setmenu();
		con.add(jsp);
		setBounds(350, 100, 700, 500);
	}
	void setmenu() {
		JMenuBar m = new JMenuBar();//创建菜单条
		JButton m1 = new JButton("搜索");
		JButton m2 = new JButton("发布");
		JButton m4 = new JButton("Help");
		m.add(m1);
		m.add(m2);
		m.add(m4);
		
		m1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new Search();
			}
		});
		m2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new Publish();
			}
		});
		m4.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(con, "自己百度！");
			}
		});
		
		setJMenuBar(m);
	}

	void addGoods() {
		for(int i = 0;i < 10;i++) {
		JPanel p = new JPanel();
		JPanel p_1 = new JPanel();
		p.setLayout(new GridLayout(1,3,4,4));
		p_1.setLayout(new GridLayout(2,1,4,4));
		JButton jb1 = new JButton("购买");
		Comment jb2 = new Comment("评论");
		JButton jb3 = new JButton("聊天");
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new BuyWindow();
				
			}
		});
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jb2.new Show();
				
			}
		});
		jb3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientChat c = new ClientChat();
			}
		});
		p_1.add(jb1);
		p_1.add(jb2);
		JTextArea jl = new JTextArea("ID："+jdbc.form[0][i]+"\n"+"userID: "+jdbc.form[1][i]+"\n"+"name: "+jdbc.form[2][i]+"\n"+"price: "+jdbc.form[3][i]+"\n"+"description: "+jdbc.form[4][i]+"\n");
		jl.setBackground(Color.lightGray);
		p.add(jl);
		p.add(jb3);
		p.add(p_1);
		p.setBorder(BorderFactory.createLineBorder(Color.red));
		jp6.add(p);
		}
	}
public static void main(String[] args) {
	FirstPage f = new FirstPage();
	
}
class Comment extends JButton{
	private String []com =new String [100];
	public Comment(String str) {
		super (str);
	}
	class Show extends JFrame{
		TextField tf=new TextField(20);
		TextArea ta=new  TextArea();
		JPanel jp=new JPanel();
		JButton jb=new JButton("发送");
		public Show() {
			jp.setLayout(new GridLayout(1,2));
			jb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String input=tf.getText();
					for(int front =0;;front++) {
						if(com[front]==null) {
							com[front]=input;
							break;
						}
					}
						
					ta.setText(ta.getText()+"\n"+input);
					tf.setText("");
				}
			});
			jp.add(tf);jp.add(jb);
			set();
			add(ta,BorderLayout.NORTH);
			add(jp,BorderLayout.SOUTH);
			pack();
			setVisible(true);
		}
		void set() {
			for(int i=0;com[i]!=null;i++) {
				ta.setText(ta.getText()+com[i]+"\n");
			}
		}
	}
}
class Search extends JFrame{
	private TextField txf;
	private JButton go;
	private JButton quit;
	private JLabel c;
	JPanel  b=new JPanel();
	JPanel  a=new JPanel();
	public Search() {
		c=new JLabel("商品id ：");
		setBounds(500,400,300,100);
		txf=new TextField(20);
		go=new JButton ("GO!");
		quit=new JButton ("exit");
		a.setLayout(new GridLayout(1,2));
		a.add(c);
		a.add(txf);
		b.add(go);
		b.add(quit);
		add(a,BorderLayout.CENTER);
		add(b,BorderLayout.SOUTH);
		go.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int a=Integer.parseInt(txf.getText());
				new SearchOver(a);
			}
		});
	quit.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);	
		}
	});
		setVisible(true);
	
	}
	  
}
class Publish extends JFrame{
	JPanel z,x;
	JLabel j1,j2,j3,j4,j5;
	TextField tx1,tx2,tx3,tx4,tx5;
	JButton publish,exit;
	public Publish() {
		j1=new JLabel("id");
		j2=new JLabel("userID");
		j3=new JLabel ("name");
		j4=new JLabel("price");
		j5=new JLabel("description");
		z=new JPanel();
		z.setLayout(new GridLayout(2,5));
		tx1=new TextField();
		
		tx2=new TextField();
		tx3=new TextField();
		tx4=new TextField();
		tx5=new TextField();
		z.add(j1);z.add(j2);z.add(j3);z.add(j4);z.add(j5);z.add(tx1);z.add(tx2);z.add(tx3);z.add(tx4);z.add(tx5);
		publish=new JButton("Publish");
		exit=new JButton("Exit");
		x=new JPanel();
		x.add(publish);x.add(exit);
		publish.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
			int a=Integer.parseInt(tx1.getText());
			int b =Integer.parseInt(tx2.getText());
			String c=tx3.getText();
			double d=Double.parseDouble(tx4.getText());
			String e=tx5.getText();
				boolean f=false;
				for(int i=0;i<5;i++) {
					if(jdbc.form[0][i].equals(a)) {
						JOptionPane.showMessageDialog(con, "ID重复，请重新选择后输入！");
						f=true;break;
					}
				}
				if(!f) {
					try {
						jdbc.addGoods(a, b, c, d, e);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(con, "商品发布成功！");
				}
			}
			
		});
		add(z,BorderLayout.CENTER);add(x,BorderLayout.SOUTH);
		pack();
		setVisible(true);
		setBounds(400,400,600,150);
	}
}
class BuyWindow extends JFrame{
	JLabel a;TextField b;
	JPanel j1,j2;
	JButton b1,b2;
	public BuyWindow() {
		setTitle("确认购买");
		a=new JLabel("请确认，您购买商品的id是：");
		b=new TextField();
		setVisible(true);
		setBounds(800,400,300,200);
		b1=new JButton("确认");b2=new JButton("取消");
		j1=new JPanel();
		j2=new JPanel();
		j1.setLayout(new GridLayout(1,2));
		j1.add(a);
		j1.add(b);
		j2.setLayout(new GridLayout(1,2));
		j2.add(b1);j2.add(b2);
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int a=Integer.parseInt(b.getText());
				try {
					jdbc.deleteGoods(a);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(con, "商品已购买！");
			}
		});
		add(j1,BorderLayout.NORTH);
		add(j2,BorderLayout.SOUTH);
		pack();
	}
}
class SearchOver extends JFrame{
public SearchOver(int a) {
	String str=a+"";
	for(int i=0;i<5;i++) {
		if(jdbc.form[0][i].equals(str)) {
			JPanel p = new JPanel();
			JPanel p_1 = new JPanel();
			p.setLayout(new GridLayout(1,3,4,4));
			p_1.setLayout(new GridLayout(2,1,4,4));
			JButton jb1 = new JButton("购买");
			JButton jb2 = new JButton("评论");
			JButton jb3 = new JButton("聊天");
			jb1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new BuyWindow();
					
				}
			});
			jb2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					
				}
			});
			jb3.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ClientChat c = new ClientChat();
				}
			});
			p_1.add(jb1);
			p_1.add(jb2);
			JTextArea jl = new JTextArea("ID："+jdbc.form[0][i]+"\n"+"userID: "+jdbc.form[1][i]+"\n"+"name: "+jdbc.form[2][i]+"\n"+"price: "+jdbc.form[3][i]+"\n"+"description: "+jdbc.form[4][i]+"\n");
			jl.setBackground(Color.lightGray);
			p.add(jl);
			p.add(jb3);
			p.add(p_1);
			p.setBorder(BorderFactory.createLineBorder(Color.red));
			add(p);
			setBounds(800, 500, 400, 200);
			setVisible(true);
}
			
		}
	}
}
}

