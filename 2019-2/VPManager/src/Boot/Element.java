package Boot;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Element {
	String name,describe,label;
	int num,type;
	boolean marker;
	public ArrayList<ImageIcon> image=new ArrayList<ImageIcon>();
	
	Point location=new Point();
	Element last,next;
	static final int VIDEO=1,AUDIO=2,TRANSITION=3,INFO=4;
	Element(int type,int num,String name,String label,Point location){
		this.type=type;
		this.num=num;
		this.name=new String(name);
		this.label=new String(label);
		this.location=location;
	}
	public void setDescribe(String describe) {
		this.describe=describe;
	}
	public void setMarker(boolean marker) {
		this.marker=marker;
	}
}
