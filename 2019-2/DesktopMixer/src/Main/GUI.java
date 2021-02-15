package Main;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;

import Classes.*;
public class GUI {
	public GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
	public Rectangle rec=ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
	public int sw=(int)rec.getWidth();
	public int sh=(int)rec.getHeight();
	
	MyFrame mf;
	EditArea bg;
	
	GUI(){
		mf=new MyFrame(sw,sh,0,0,false,"DesktopPaper",true,true,true);
		mf.setLayout(null);
		mf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		bg=new EditArea(sw,sh);
		bg.setLocation(0, 0);
		bg.loadAll();
		mf.add(bg);
		
		mf.setVisible(true);
	}
}
