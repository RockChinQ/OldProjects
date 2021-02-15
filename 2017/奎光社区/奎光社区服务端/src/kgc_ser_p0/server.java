package kgc_ser_p0;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server extends Thread{
	public void run(){
		try {
			ServerSocket ss=new ServerSocket(18703);
			Socket socket=ss.accept();
			boolean b=false;
			for(int a=0;a<25;a++){
				if(!main.use[a]){
					main.use[a]=true;
					main.soc[a]=socket;
					b=true;
					new sc().send("welcome", socket);
					break;
				}
			}
			if(!b){
				OutputStream os=socket.getOutputStream();
				PrintStream ps=new PrintStream(os);
				ps.println("lose");
				socket.close();
			}
		} catch (Exception e) {
			new notes().save(e.toString());
		}
	}
}
