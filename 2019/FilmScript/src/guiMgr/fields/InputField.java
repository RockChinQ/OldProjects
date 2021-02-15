package guiMgr.fields;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import guiMgr.PnlColor;

public class InputField extends JPanel{
	public JLabel text=new JLabel("undefined");
	public JTextField input=new JTextField();
	public static final Color fcl=new Color(240,240,240);
	public static final Color bgf=new Color(60,60,60);
	public InputField(String name,int width,int height,double sep) {
		init(name,width,height,sep);
	}
	public InputField(String name,int width,int height,int sepx) {
		init(name,width,height,(double)sepx/(double)width);
	}
	public void init(String name,int width,int height,double sep) {
		this.setLayout(null);
		this.setSize(width, height);
		
		text.setText(name);
		text.setSize((int) (width*sep), height);
		text.setLocation(0, 0);
		text.setForeground(fcl);
		this.add(text);
		
		input.setSize(width-(int) (width*sep), height-4);
		input.setLocation((int) (width*sep), 2);
		input.setForeground(fcl);
		input.setBackground(bgf);
		input.setCaretColor(fcl);
		input.setBorder(null);
		this.add(input);
	}
	public String getValue() {
		return input.getText();
	}
	public void setValue(String s) {
		this.input.setText(s);
	}
	public void updateCom() {
		text.setBackground(getBackground());
		input.setBackground(PnlColor.getColor(getBackground(), 0.7));
	}
	
}
