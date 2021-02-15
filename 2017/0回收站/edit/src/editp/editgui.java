package editp;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

public class editgui {
	public static GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
	public static Rectangle rec=ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
	public static int xl=(int)rec.getWidth();
	public static int yl=(int)rec.getHeight();
	String fs=main.sou+"openfile";
	String[] s=new file0().read(new file0().read(fs)).split("&");
	static gui g;
	editgui(){
		try{
		g=new gui(Integer.parseInt(s[0])*60+100,Integer.parseInt(s[1])*60+100,0,0,true,"SeatEdit",false,true,true);
		}catch(Exception e){
			javax.swing.JOptionPane.showConfirmDialog(null, "Error:"+e);
			System.exit(-1);
		}
	}
}
