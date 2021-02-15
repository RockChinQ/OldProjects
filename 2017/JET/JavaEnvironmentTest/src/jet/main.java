package jet;

import java.io.FileWriter;

public class main {

	public static void main(String[] args) {
		write("test.txt", "successful", false);
		System.exit(0);
	}
	static void write(String s0,String s1,boolean b){
		try {
			FileWriter fw=new FileWriter(s0,b);
			fw.write(s1);
			fw.close();
		} catch (Exception e) {
		}
	}

}
