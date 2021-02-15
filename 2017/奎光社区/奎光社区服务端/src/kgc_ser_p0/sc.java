package kgc_ser_p0;

import java.net.Socket;
import java.io.*;

public class sc {
	void send(String message,Socket soc){
		OutputStream os;
		try {
			os = soc.getOutputStream();
			PrintStream ps=new PrintStream(os);
			ps.println(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	String receive(Socket s){
		try{
			InputStream is=s.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			return br.readLine();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
