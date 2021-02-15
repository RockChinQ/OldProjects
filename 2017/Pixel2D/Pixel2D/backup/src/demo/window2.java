package demo;

import java.awt.Color;

import PictureEditor.gui;
import Pixel2D.Pixel2D;

public class window2 {
	gui g=new gui(500,500,500,500,false,"Pixelllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll",true,true,true);

	Pixel2D p2d=new Pixel2D(5,5,1000,1000,Color.GRAY,null,false,Color.black);
	window2(){
		p2d.drawString(50, 50, "Fuck Microsoft !", Color.BLACK, 1, "chars-7.txt");
		g.add(p2d);
		g.setVisible(true);
	}
}
