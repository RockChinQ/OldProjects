package RenjuMain;

import java.awt.Color;

import ReleaseI.Pixel2D;
import ReleaseI.PixelImage;

public class GUI {
	MyFrame mf;
	Pixel2D p2d;
	GUI(String style){
		/*Pixel2D panel的生成*/
		p2d=new Pixel2D(6,6,85,72,new Color(6,206,182),
				Color.BLACK,false,Color.WHITE);
		p2d.addPixelMouseListener(new PixelMouseEvent());
		/*绘制*/
		p2d.drawPixelImage(0, 1, Color.white, new PixelImage("styles\\"+style+"\\chessPanel.p2d"));
		
		mf=new MyFrame(490,465,300,100,true,"RenjuAI "+Data.ver,true,true,true);
		mf.setLayout(null);
		
		p2d.setLocation(0, 0);
		mf.add(p2d);
	}
}
