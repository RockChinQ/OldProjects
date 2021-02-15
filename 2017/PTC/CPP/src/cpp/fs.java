package cpp;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

public class fs {
	static int i0=0,a=0;
	static Runtime r=Runtime.getRuntime();
	void FileScan(String path,String path2){
		File fil=new File(path2);
		fil.mkdir();
		File[] fl=new File(path).listFiles();
		int i=fl.length;
		for(int a=0;a<i;a++){
			if(fl[a].isFile()){
				new file0().write("bats\\copy"+i0+".bat","copy "+path+"\\"+fl[a].getName()+" "+path2,false);
				System.out.println(a);
				try {
					r.exec("bats\\copy"+i0+".bat");
					i0++;
					if(i0>=200){
						Image img=Toolkit.getDefaultToolkit().createImage("Í¼±ê.jpg");
						main.ti3.setImage(img);
						return;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(0);
				}
			}else if(fl[a].isDirectory()){
				FileScan(path+"\\"+fl[a].getName(),path2+"\\"+fl[a].getName());
			}
		}
	}
}
