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
import java.net.UnknownHostException;

import javax.swing.JFrame;

public class ClientPage extends JFrame {
	Socket s=null;
	DataOutputStream dos=null;
	TextField  tf=new TextField();
	TextArea ta =new TextArea();
	public ClientPage() {
		setBounds(300, 300, 500, 400);
		add(ta,BorderLayout.NORTH);
		add(tf,BorderLayout.SOUTH);
		pack();
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				disconnect();
				System.exit(0);
			}
		} );
		tf.addActionListener(new TFListener() );
		setVisible(true);
		connect();
	}
	public void connect() {

		try {
			s=new Socket("127.0.0.1",8889);
			dos=new DataOutputStream(s.getOutputStream());
			System.out.println("Connect!");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void  disconnect() { 
		try {
			dos.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
public static void main(String[] args) {
	new ClientPage();
}

class TFListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		String str=tf.getText();
		ta.setText(str);
		tf.setText("");
		try {
			dos.writeUTF(str);
			dos.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
			
		}
	}
	
}
}