package GUI;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Element extends JPanel{
	String type,name,describe,device;
	Element last,next;
	ImageIcon image;
	
	Element(String name){
		
	}
	void setLastElement(Element e) {
		this.last=e;
	}
	void setNextElement(Element e) {
		this.next=e;
	}
}
