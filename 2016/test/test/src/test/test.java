package test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class test {

	public static void main(String[] args) throws Exception {
		FileOutputStream fos=new FileOutputStream("rock.txt");
		String s="rock";
		fos.write(s.getBytes());
		fos.close();
	}

}
