package kgc_cli_p0;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class main {

	public static void main(String[] args) {
		try {
			Socket s=new Socket("127.0.0.1",9999);
			InputStream is=s.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			String str=br.readLine();
			if(str.equals("lose")){
				System.out.print("连接失败");
			}else if(str.equals("welcome")){
				System.out.println("连接成功");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
