package COUNTLINE;
import java.io.File;
import java.io.FileInputStream;
public class Main {
	static long chars=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//FileRW.write("", "", false);
		String path=new File("").getAbsolutePath();
		System.out.println(path);
		int count=(count(new File(path+"\\src"))-46);
		System.out.println("----------Summary-----------\nLine Count:"+count);
		System.out.println("Symbol Count:"+(chars-1348));
		System.out.println("Average:"+(chars/count)+" sym/line");
	}
	static int count(File dir) {
		System.out.println("Counting:"+dir.getPath()+" ...");
		int count=0;
		int elec=dir.listFiles().length;
		File[] lf=dir.listFiles();
		for(int i=0;i<elec;i++) {
			if(lf[i].isFile()) {
				String temp=read(lf[i].getAbsolutePath());
				chars+=temp.length();
				System.out.println("      File "+lf[i].getName()+" "+temp.length());
				count+=temp.split("\n").length;
			}else if(lf[i].isDirectory()) {
				count+=count(lf[i]);
			}
		}
		return count;
	}
	public static String read(String s){
		File f=new File(s);
		try {
			FileInputStream fis=new FileInputStream(f);
			byte[] data=new byte[(int)f.length()];
			fis.read(data);
			fis.close();
			return new String(new String(data));
		} catch (Exception e) {
		}
		return "";
	}
}
