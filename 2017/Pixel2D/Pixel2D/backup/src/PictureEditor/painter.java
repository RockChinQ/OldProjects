package PictureEditor;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JColorChooser;

public class painter {
	final static int MODE_POINT=1,MODE_LINE=2,MODE_CIRCLE=3,MODE_RECT=4,MODE_TEXT=5,MODE_GET=6,MODE_DEL=7,MODE_TRANS=8;
	int mode=1;
	Color cl=Color.green;//=JColorChooser.showDialog(main.w.g, "Ñ¡È¡ÑÕÉ«", null)
	Color bg=Color.white;
	Point point1=new Point(-1,-1);
}
