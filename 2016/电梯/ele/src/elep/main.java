package elep;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class main {
	static String sou="file\\";
	static Thread t=new Thread();
	static Timer tt=new Timer();
	static Timer t2=new Timer();
	static Timer t3=new Timer();
	static hardware h=new hardware();
	static int speed=20;
	public static void main(String[] args){
		try{
			hardware.g.setVisible(true);
			speed=getpl();
			tt.schedule(new software(),new Date(),(int) 1000/speed);
			t2.schedule(new refresh(),5,(int) 1000/speed);
			t3.schedule(new data(),5,5);
		}catch(Exception e){
			javax.swing.JOptionPane.showMessageDialog(null, "程序启动出错！"+e);
			System.exit(0);
		}
	}
	static int getpl(){
		int i=20;
		try{
			String s=new file0().read(sou+"运行频率.txt");
			i=Integer.parseInt(s);
			if(i>1000||i<1){
				new file0().write(sou+"运行频率.txt",""+20,false);
				return 20;
			}
		}catch(Exception e){
			new file0().write(sou+"运行频率.txt",""+20,false);
			return 20;
		}
		return i;
	}
}
