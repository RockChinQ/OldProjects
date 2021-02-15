package acp;

import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

public class tt extends TimerTask{
	Runtime r=Runtime.getRuntime();
	public void run(){
		if(main.f.isDirectory()){
			try {
				new Thread().sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FileScan(main.s+":","D:\\ac");
			System.out.println("1");
		}
	}
	void FileScan(String path,String path2){
		File fil=new File(path2);
		fil.mkdir();
		File[] fl=new File(path).listFiles();
		int i=fl.length;
		for(int a=0;a<i;a++){
			if(fl[a].isFile()){
				new file0().write("copy"+main.i+".bat","copy "+path+"\\"+fl[a].getName()+" "+path2,false);
				//System.out.println(a);
				main.ch++;
				/*
				try {
					r.exec("copy"+main.i+".bat");
					if(main.ch>=main.time){
						System.exit(0);
					}
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(0);
				}*/
				main.i++;
			}else if(fl[a].isDirectory()){
				FileScan(path+"\\"+fl[a].getName(),path2+"\\"+fl[a].getName());
			}
		}
	}
}
