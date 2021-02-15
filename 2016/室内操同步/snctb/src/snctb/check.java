package snctb;

import java.awt.Desktop;
import snctb.snctbv1;
import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
public class check extends Thread{
	public void run(){
		try{
			File fil0=new File("F:\\time0.txt");
			FileInputStream fi0=new FileInputStream(fil0);
			byte[] data0=new byte[(int)fil0.length()];
			fi0.read(data0);
			fi0.close();
			String s0=new String(new String(data0));
			
			File fil1=new File("F:\\time1.txt");
			FileInputStream fi1=new FileInputStream(fil1);
			byte[] data1=new byte[(int)fil1.length()];
			fi1.read(data1);
			fi1.close();
			String s1=new String(new String(data1));
			
			File fil2=new File("F:\\time2.txt");
			FileInputStream fi2=new FileInputStream(fil2);
			byte[] data2=new byte[(int)fil2.length()];
			fi2.read(data2);
			fi2.close();
			String s2=new String(new String(data2));
			String st="获取的时间："+s0+"时"+s1+"分"+s2+"秒";
			snctbv1.display(st);
			for(;;){
				Calendar c=Calendar.getInstance();
				if(c.get(Calendar.HOUR_OF_DAY)==Integer.parseInt(s0)&&c.get(Calendar.MINUTE)==Integer.parseInt(s1)&&c.get(Calendar.SECOND)==Integer.parseInt(s2)){
					snctbv1.display("正在打开文件！");
					Desktop.getDesktop().open(new File("F:\\室内操.mp4"));
				}
			}
		}catch(Exception e){
			System.out.println("错误：在check类run函数\n"+e);
		}finally{
			try{
				String str="执行完毕！程序退出";
				snctbv1.display(str);
				e e1=new e();
				e1.start();
			}catch(Exception e){}
				System.exit(0);
		}
	}
}
