package Server;

import java.awt.*;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.*;

public class Server {
	ServerSocket ss = null;
	public void start() {
		try {
			ss = new ServerSocket(8889);
		} catch (BindException e1) {
			System.out.println("端口使用中。。。");
			System.out.println("请关掉相关程序并重启服务器！");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			while (true) {
			Socket s = ss.accept();
				Client c = new Client(s);
				System.out.println("a client connected!");
				new Thread(c).start();
				
			}
		} catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				ss.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
	
		new Server().start();
		
	}

	class Client implements Runnable {
		private Socket s;
		private DataInputStream dis = null;
		boolean bConnected = false;

		public Client(Socket s) {
			this.s = s;
			bConnected = true;
			try {
				dis = new DataInputStream(s.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public void run() {
			while (bConnected) {
				try {
					String str = null;
					str = dis.readUTF();
					System.out.println(str);
				} catch (EOFException e1) {
					// e.printStackTrace();
					System.out.println("Client Closed!");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (dis != null)
							dis.close();
						if (s != null)
							s.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

			}
		}

	}

}