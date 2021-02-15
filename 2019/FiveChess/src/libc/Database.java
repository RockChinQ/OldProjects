package libc;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class Database {
	class Method{
		String file;
		Dimension size;
		short[] code;
		Point put;
		char[][] autospawn;
		int flag=0;
		public static final int RESET=0,SET=1,DEL=2;
		public Method(String file) throws Exception {
			this.file=new String(file);
			load();
		}
		public void load() throws Exception{
			String fileStr[]=FileRW.read(file)
					.replaceAll("\r\n", "\n").split("\n");//�ļ���ȡ�ָ�
			System.out.println("loading..."+file);
			//���г���
			String sizestr[]=fileStr[0].split(",");
			//���д���
			String codestr[]=fileStr[1].split(",");
			//�������ӵ�
			String putstr[]=fileStr[2].split(",");
			//�����Զ�����
			String spawnstr[]=fileStr[3].split(",");
			
			size=new Dimension(str2int(sizestr[0]),str2int(sizestr[1]));
			code=new short[(int) (size.getHeight()*size.getWidth())];
			for(int i=0;i<size.getHeight()*size.getWidth();i++) {
				code[i]=(short) str2int(codestr[i]);
			}
			put=new Point(str2int(putstr[0]),str2int(putstr[1]));
			int len=spawnstr.length;
			autospawn=new char[len][2];
			for(int i=0;i<len;i++) {
				autospawn[i][0]=spawnstr[i].toCharArray()[0];
				autospawn[i][1]=spawnstr[i].toCharArray()[1];
			}
		}
	}
	FileFilter ff=new FileFilter() {
		public boolean accept(File arg0) {
			if(arg0.isFile()&&arg0.getName().endsWith("fmt"))
				return true;
			return false;
		}
	};
	ArrayList<Method> lib=new ArrayList<Method>();
	public void loadAllMethods() {//��������methods
		String dirstr=new FileRW().read("lib\\index.ini");
		String[] dirs=dirstr.split("\n");
		int len=dirs.length;
		for(int i=0;i<len;i++) {
			//System.out.println(dirs[i]);
			loadDir("lib\\"+dirs[i]);
		}
		System.out.println("�������...��"+lib.size()+"��");
	}
	public void autoSpawn() {
		
	}
	public void libc() {
		
	}
	
	public static int str2int(String str) {
		return Integer.parseInt(str);
	}
	public static boolean str2bool(String str) {
		return Boolean.parseBoolean(str);
	}
	public static boolean intStr2bool(String str) {
		return str2int(str)==0?false:true;
	}
	public void loadDir(String path) {
		File dir=new File(path+"\\");
		System.out.println("Dir:"+dir.getAbsolutePath()+" "+dir.isDirectory());
		File files[]=dir.listFiles();
		//System.out.println(files.length);
		int len=files.length;
		for(int i=0;i<len;i++) {
			try {
				lib.add(new Method(files[i].getAbsolutePath()));
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("#��������,������:"+files[i].getAbsolutePath());
			}
		}
	}
}
