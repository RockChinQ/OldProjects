package Pixel2D;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;

public class PixelComponent {
	public String text;
	public int width,height;
	public Point location=new Point();
	public Color background=new Color(0,162,232);
	public PixelFont font=new PixelFont();
	public Color fontcl=Color.BLACK;
	public boolean enable=true;
	public PixelIcon icon;
	public PixelMouseListener mouseListener;
	public boolean showBorderLine=false;
	public void addMouseListener(PixelMouseListener ml){
		this.mouseListener=ml;
	}
	public void boederLine(boolean showBorderLine){
		this.showBorderLine=showBorderLine;
	}
	public void setText(String text){
		this.text=new String(text);
	}
	public void setSize(int w,int h){
		this.width=w;
		this.height=h;
	}
	public void setWidth(int width){
		this.width=width;
	}
	public void setHeight(int height){
		this.height=height;
	}
	public void setLocation(Point p){
		this.location=p;
	}
	public void setLocation(int x,int y){
		this.location=new Point(x,y);
	}
	public void setEnable(boolean enable){
		this.enable=enable;
	}
	public void setFontColor(Color fc){
		this.fontcl=fc;
	}
	public boolean isEnable(){
		return enable;
	}
	public Point getLocation(){
		return location;
	}
	public String getText(){
		return text;
	}
	public String getDisplayableText(){
		StringBuffer strb=new StringBuffer("");
		int charAmount=this.width/(int)Math.sqrt(read(this.font.getCharset()).split("#;")[0].split(":")[1].length());
		char[] ch=this.text.toCharArray();
		int len=ch.length;
		for(int a=0;a<charAmount&&a<len;a++){
			strb.append(ch[a]);
		}
		return strb.toString();
	}
	public String read(String s){
		File f=new File(s);
		try {
			FileInputStream fis=new FileInputStream(f);
			byte[] data=new byte[(int)f.length()];
			fis.read(data);
			fis.close();
			return new String(new String(data));
		} catch (Exception e) {
		}
		return "";
	}
}
