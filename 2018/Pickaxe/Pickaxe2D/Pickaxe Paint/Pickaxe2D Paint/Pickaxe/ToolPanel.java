package Pickaxe;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
public class ToolPanel extends JPanel{
	String fp=Boot.filePath;
	MyButton tPoint,tLine,tShape,tSelect,tGetColor,tClear,tFill,tText,tMouse,tEnlarge;
	MyButton aButton[];
	ImageIcon point=new ImageIcon(fp+"tpoint.png"),line=new ImageIcon(fp+"tline.png")
			,rect=new ImageIcon(fp+"trect.png"),circle=new ImageIcon(fp+"tcircle.png")
			,poly=new ImageIcon(fp+"tpoly.png"),sRect=new ImageIcon(fp+"srect.png")
			,sMagic=new ImageIcon(fp+"smagic.png"),sPoly=new ImageIcon(fp+"spoly.png")
			,sCircle=new ImageIcon(fp+"scircle.png"),getColor=new ImageIcon(fp+"getcolor.png")
			,clear=new ImageIcon(fp+"clear.png"),fill=new ImageIcon(fp+"fill.png")
			,text=new ImageIcon(fp+"text.png"),mouse=new ImageIcon(fp+"mouse.png")
			,zoom=new ImageIcon(fp+"enlarge.png");
	ImageIcon sshape=rect,sselect=sRect;
	MyButton selectTool=tPoint;
	StringBuffer toolNow=new StringBuffer("mouse");
	Color bg=new Color(25,25,25);
	Font bFont=new Font("",Font.PLAIN,15);
	Color textc=new Color(5,204,226);
	
	ToolPanel() {
		//面板参数
		this.setBackground(bg);
		this.setLayout(null);
		this.setSize(80, 500);
		//控件
		tPoint=new MyButton(point);
		tLine=new MyButton(line);
		tShape=new MyButton(rect);
		tSelect=new MyButton(sRect);
		tGetColor=new MyButton(getColor);
		tClear=new MyButton(clear);
		tFill=new MyButton(fill);
		tText=new MyButton(text);
		tMouse=new MyButton(mouse);
		tEnlarge=new MyButton(zoom);
		int startH=20,w=32,h=32,x=5;
		tPoint.setSizex(w, h).setLocationx(x, startH).setFontx(bFont).setBackgroundx(Color.black).addActionListener(new _toolAction());
		tLine.setSizex(w,h).setLocationx(x, startH+33).setFontx(bFont).setBackgroundx(Color.black).addActionListener(new _toolAction());
		tShape.setSizex(w, h).setLocationx(x,startH+33*2).setFontx(bFont).setBackgroundx(Color.black).addActionListener(new _toolAction());
		tSelect.setSizex(w,h).setLocationx(x,startH+33*3).setFontx(bFont).setBackgroundx(Color.black).addActionListener(new _toolAction());
		tGetColor.setSizex(w,h).setLocationx(x,startH+33*4).setFontx(bFont).setBackgroundx(Color.black).addActionListener(new _toolAction());
		tClear.setSizex(w,h).setLocationx(x,startH+33*5).setFontx(bFont).setBackgroundx(Color.black).addActionListener(new _toolAction());
		tFill.setSizex(w,h).setLocationx(x,startH+33*6).setFontx(bFont).setBackgroundx(Color.black).addActionListener(new _toolAction());
		tText.setSizex(w,h).setLocationx(x,startH+33*7).setFontx(bFont).setBackgroundx(Color.black).addActionListener(new _toolAction());
		tMouse.setSizex(w,h).setLocationx(x,startH+33*9).setFontx(bFont).setBackgroundx(Color.black).addActionListener(new _toolAction());
		tEnlarge.setSizex(w,h).setLocationx(x,startH+33*8).setFontx(bFont).setBackgroundx(Color.black).addActionListener(new _toolAction());
		this.add(tPoint);
		this.add(tLine);
		this.add(tShape);
		this.add(tSelect);
		this.add(tGetColor);
		this.add(tClear);
		this.add(tFill);
		this.add(tText);
		this.add(tMouse);
		this.add(tEnlarge);
		//副按钮
		aButton=new MyButton[10];
		for(int i=0;i<10;i++) {
			aButton[i]=new MyButton();
			aButton[i].setSizex(w, h).setLocationx(x+w+2, startH+(w+1)*i).setVisible(false);
			aButton[i].addActionListener(new _toolAction());
			this.add(aButton[i]);
		}
		this.setSelectedTool(tMouse);
	}
	void setAllUnselected() {
		tPoint.setBackground(Color.black);
		tLine.setBackground(Color.black);
		tGetColor.setBackground(Color.black);
		tClear.setBackground(Color.black);
		tFill.setBackground(Color.black);
		tText.setBackground(Color.black);
		tShape.setBackground(Color.black);
		tSelect.setBackground(Color.black);
		tMouse.setBackground(Color.BLACK);
		tEnlarge.setBackground(Color.black);
	}
	void setShapeSelected(ImageIcon sshape) {
		this.sshape=sshape;
		this.tShape.setIcon(sshape);
	}
	void setSelectSelected(ImageIcon sselect) {
		this.sselect=sselect;
		this.tSelect.setIcon(sselect);
	}
	void setSelectedTool(MyButton mb) {
		setAllUnselected();
		/*
		String s[]=((ImageIcon)mb.getIcon()).toString().split("[.]");
		mb.setIcon(new ImageIcon(s[0]+"1.png"));
		*/
		mb.setBackground(textc);
		this.selectTool=mb;
	}
	void updateToolNow() {
		ImageIcon it=(ImageIcon) selectTool.getIcon();
		if(it.equals(this.point)) {
			this.toolNow=new StringBuffer("point");
		}else if(it.equals(this.line)) {
			this.toolNow=new StringBuffer("line");
		}else if(it.equals(this.rect)) {
			this.toolNow=new StringBuffer("rect");
		}else if(it.equals(this.circle)) {
			this.toolNow=new StringBuffer("circle");
		}else if(it.equals(this.poly)) {
			this.toolNow=new StringBuffer("poly");
		}else if(it.equals(this.sRect)) {
			this.toolNow=new StringBuffer("srect");
		}else if(it.equals(this.sCircle)) {
			this.toolNow=new StringBuffer("scircle");
		}else if(it.equals(this.sPoly)) {
			this.toolNow=new StringBuffer("spoly");
		}else if(it.equals(this.sMagic)) {
			this.toolNow=new StringBuffer("smagic");
		}else if(it.equals(this.getColor)) {
			this.toolNow=new StringBuffer("getcolor");
		}else if(it.equals(this.clear)) {
			this.toolNow=new StringBuffer("clear");
		}else if(it.equals(this.fill)) {
			this.toolNow=new StringBuffer("fill");
		}else if(it.equals(this.text)) {
			this.toolNow=new StringBuffer("text");
		}else if(it.equals(this.zoom)) {
			this.toolNow=new StringBuffer("zoom");
		}else if(it.equals(this.mouse)) {
			this.toolNow=new StringBuffer("mouse");
		}
		System.out.println(toolNow);
	}
	void setAButtonVisible(boolean v) {
		for(int i=0;i<10;i++) {
			aButton[i].setVisible(v);
		}
	}
}
