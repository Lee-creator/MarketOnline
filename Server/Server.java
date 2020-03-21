package Server;
import java.io.*;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;



public class Server {
		boolean started =false;
		ServerSocket ss= null;
		List <Client> clients=new ArrayList<>();
		public static void main(String[] args) {
			new Server().start();
		}
		public void start() {
			try {
				ss=new ServerSocket(8888);
				started=true;
			}catch(BindException e){
				System.out.println("端口使用中。。。");
				System.out.println("请关闭相关端口并重启服务器！");
				System.exit(0);
			}catch(IOException e) {
				e.printStackTrace();
			}
			try {
				while(started) {
				Socket s=ss.accept();
				Client c=new Client(s);
				new Thread(c).start();
				clients.add(c);
				}
			}catch(IOException e){
				e.printStackTrace();
			}finally {
				try {
					ss.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
				
			}
		}

class Client implements Runnable{
	private Socket s;
	private DataInputStream dis=null;
	private DataOutputStream dos=null;
	private boolean bConnect =false;
	
	public Client(Socket s) {
		this.s=s;
		try{
			dis =new DataInputStream(s.getInputStream());
			dos =new DataOutputStream(s.getOutputStream());
			bConnect=true;
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void send(String str) {
		try {
			dos.writeUTF(str);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void run() {
		try {
			while(bConnect) {
				String str = dis.readUTF();
				for(int i=0;i<clients.size();i++) {
					Client c=clients.get(i);
					c.send(str);
			}
		}
	}catch(EOFException e){
		System.out.println("Client closed!");
	}catch(IOException e) {
		e.printStackTrace();	
	}finally {
		try {
			if(dis!=null)dis.close();
			if(dos !=null)dos.close();
			if(s!=null)s.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	}
}
}