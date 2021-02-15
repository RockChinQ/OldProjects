package tp0;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class file0 {
	void write(String s0,String s1,boolean b){
		try {
			FileWriter fw=new FileWriter(s0,b);
			System.out.println(s0+" "+s1);
			fw.write(s1);
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	String read(String s){
		File f=new File(s);
		try {
			FileInputStream fis=new FileInputStream(f);
			byte[] data=new byte[(int)f.length()];
			fis.read(data);
			fis.close();
			return new String(new String(data));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
