package CAE;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

class write {
	write(){
		;
	}
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
}
