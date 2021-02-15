package cp;

import java.io.File;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s=javax.swing.JOptionPane.showInputDialog("路径");
		FileScan(s);
		javax.swing.JOptionPane.showMessageDialog(null, "完成！");
		System.exit(0);
	}
	static void FileScan(String path){
		File[] fl=new File(path).listFiles();  //文件列表
		int i=fl.length;
		System.out.println(i);
		String dow=new file0().read(path+"\\1.txt");
		for(int a=0;a<i;a++){
			if(fl[a].isFile()){
				String hz=fl[a].getName().split("\\.")[1];
				if(hz.equals("jpg")){
					System.out.println(hz);
					fl[a].renameTo(new File(path+"\\"+dow+"("+a+")"+".jpg"));
				}
			}
		}
	}

}
