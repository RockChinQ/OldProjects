package modpe0;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class file {
	void dow(String a,String b) throws Exception{
		FileOutputStream fi=new FileOutputStream(a);
		fi.write(b.getBytes());
		fi.close();
	}
	void del(String s){
		File file=new File(s);
		if(file.exists()){
			file.delete();
		}
	}
	String read(String s) throws Exception{
		File f=new File(s);
		FileInputStream fis=new FileInputStream(f);
		byte[] data=new byte[(int)f.length()];
		fis.read(data);
		fis.close();
		String s1=new String(new String(data));
		return s1;
	}
}
