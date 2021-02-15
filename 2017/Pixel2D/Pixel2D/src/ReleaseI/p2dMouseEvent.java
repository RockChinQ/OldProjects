package ReleaseI;

import java.awt.Point;

public class p2dMouseEvent {
	public Object source;
	public Point p;
	public int clickAmount;
	public int button;
	p2dMouseEvent(Object source,Point p,int clickAmount,int button){
		this.source=source;
		this.p=p;
		this.clickAmount=clickAmount;
		this.button=button;
	}
	Object getSource(){
		return source;
	}
	Point getLocation() {
		return p;
	}
	int getX() {
		return p.x;
	}
	int getY() {
		return p.y;
	}
	int getClickCount() {
		return this.clickAmount;
	}
	int getButton() {
		return this.button;
	}
}
