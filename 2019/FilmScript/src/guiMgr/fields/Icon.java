package guiMgr.fields;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Icon extends JPanel {
	String iconData="";
	public Color one=new Color(230,230,230),zero;
	public void paint(Graphics g) {
		g.setColor(zero);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		int len=iconData.length();
		char[] ic=iconData.toCharArray();
		int index=0;
		label:for(int i=0;i<this.getHeight();i++) {
			l2:for(int j=0;j<this.getWidth();j++) {
				if(ic[index]=='2') {
					index++;
					break label;
				}else if(ic[index]=='3') {
					index++;
					break l2;
				}
				//System.out.println(ic[index]);
				g.setColor(ic[index]=='0'?zero:one);
				g.drawLine(j, i, j, i);
				index++;
			}
		}
	}
	public Icon() {
		
	}
	public Icon(String data) {
		this.iconData=data;
		zero=new Color(43,43,43);
	}
	public void setBackground(Color bg) {
		zero=bg;
		super.setBackground(bg);
		this.repaint();
	}
	public void set(String code) {
		this.iconData=code;
	}
	public void setColor(Color front) {
		this.one=front;
	}
}
