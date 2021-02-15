package LightComponent;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JButton;

public class LButton extends JButton {
	LButton(){
		super();
	}
	LButton(String text){
		super(text);
	}
	LButton(Icon icon){
		super(icon);
	}
	LButton(String text,Icon icon){
		super(text,icon);
	}
	public LButton addTo(Component com) {
		com.add(this);
	}
	public LButton setSizex(int w,int h) {
		super.setSize(w, h);
		return this;
	}
	public LButton setLocationx(int x,int y) {
		super.setLocation(x, y);
		return this;
	}
	
}
