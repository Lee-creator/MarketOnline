package Server;
import java.awt.*;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.*;

public class MyServer {
	
public static void main(String[] args) {
	boolean started =false;
	ServerSocket ss=null;
	Socket s=null;
	DataInputStream dis=null;
	try {
		ss=new ServerSocket(8889);
	}catch(BindException e1){
		System.out.println("端口使用中。。。");
		System.out.println("请关掉相关程序并重启服务器！");
		System.exit(0);
	}
	catch(IOException e) {
		e.printStackTrace();
	}
	try {
		started =true;
		boolean bConnected=false;
		while(started) {
			s=ss.accept();
System.out.println("a client connected!");
	 bConnected=true;
  		 dis=new DataInputStream(s.getInputStream());
  		while(bConnected) {
  		String str=dis.readUTF();
  		System.out.println(str);}
		}
	}
	catch (EOFException e1) {
		//e.printStackTrace();
		System.out.println("Client Closed!");
	}catch(IOException e) {
		e.printStackTrace();
	}finally {
		try {
			if(dis != null)dis.close();
			if(s != null)s.close();
		}catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
}
