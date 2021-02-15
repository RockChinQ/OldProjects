package Pixel2D;

public class p2dMouseEvent {
	public Object source;
	p2dMouseEvent(Object source){
		this.source=source;
	}
	Object getSource(){
		return source;
	}
}
