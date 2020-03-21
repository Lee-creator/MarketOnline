package Client;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ClientChat extends JFrame {
	boolean bConnect =false;
	Socket s=null;
	DataOutputStream dos=null;
	DataInputStream dis=null;
	JPanel a;
	TextField  tf=new TextField(30);
	TextArea ta =new TextArea();
	Thread tRecv=new Thread(new RecvThread());
	JButton tb;
	public ClientChat() {
		a=new JPanel();
		setBounds(300, 300, 500, 400);
		add(ta,BorderLayout.CENTER);
		tb=new JButton("发送");
		tb.addActionListener(new TFListener());
		a.add(tf,BorderLayout.CENTER);
		a.add(tb,BorderLayout.EAST);
		add(a,BorderLayout.SOUTH);
		pack();
		/*this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				disconnect();
				setVisible(false);
			}
		} );*/
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		connect();
		tRecv.start();
	}
	public void connect() {

		try {
			bConnect=true;
			s=new Socket("127.0.0.1",8888);
			dos=new DataOutputStream(s.getOutputStream());
			dis=new DataInputStream(s.getInputStream());
			//System.out.println("Connect!");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void  disconnect() { 
		try {
			bConnect=false;
			tRecv.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			try {
				dos.close();
				dis.close();
				s.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}

public static void main(String[] args) {
	new ClientChat();
}

private class TFListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		String str=tf.getText();
		if (str == "" || str == null) {
			JOptionPane.showMessageDialog(tf, "无法发送空消息");
		}else {
		tf.setText("");
		try {
			dos.writeUTF(str);
			dos.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
			
		}
	}}
}
	private class RecvThread implements Runnable{

		@Override
		public void run() {
			try {
			while(bConnect) {
				String str=dis.readUTF();
				
				ta.setText(ta.getText()+"\n"+str);
			}}catch(SocketException e) {
				
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	

}