package Boot;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Classes.FileRW;

public class CanvasPanel extends JPanel{
	ArrayList<Element> eles;
	ArrayList<String> label=new ArrayList<String>();
	CanvasPanel(){
		eles=new ArrayList<Element>();
		//this.setBackground(null);
		this.addMouseListener(new _canvasMouse());
		this.addMouseMotionListener(new _canvasMouse());
		this.setLayout(null);
	}
	public void paint(Graphics g) {
		//透明
		int tgw=20;
		Color dark=new Color(204,204,204),light=Color.WHITE;
		for(int i=0;i<=this.getHeight()/tgw;i++) {
			for(int j=0;j<=this.getWidth()/tgw;j++) {
				g.setColor((j+i%2)%2==0?light:dark);
				g.fillRect(j*tgw, i*tgw, tgw, tgw);
			}
		}
		int width=Main.g.ccp.ewidth,height=Main.g.ccp.eheight;
		for(int i=0;i<Main.g.cvp.eles.size();i++) {
			Element e=Main.g.cvp.eles.get(i);
			g.setColor(new Color(127,127,127,200));
			g.fillRect(e.location.x+5, e.location.y+5, width, height);
			if(e.type==Element.VIDEO) {
				g.setColor(new Color(255,127,39));
				g.fillRect(e.location.x, e.location.y, width, height);
				g.drawImage(Toolkit.getDefaultToolkit()
						.getImage("Files\\Image\\vimage.png"),e.location.x+1
						, e.location.y+1,null, this);
			}else if(e.type==Element.AUDIO) {
				g.setColor(new Color(34,177,76));
				g.fillRect(e.location.x, e.location.y, width, height);
				g.drawImage(Toolkit.getDefaultToolkit()
						.getImage("Files\\Image\\aimage.png"),e.location.x+1
						, e.location.y+1,null, this);
			}else if(e.type==Element.TRANSITION) {
				g.setColor(new Color(127,127,127));
				g.fillRect(e.location.x, e.location.y, width, height);
				g.drawImage(Toolkit.getDefaultToolkit()
						.getImage("Files\\Image\\timage.png"),e.location.x+1
						, e.location.y+1,null, this);
			}else if(e.type==Element.INFO) {
				g.setColor(new Color(0,162,232));
				g.fillRect(e.location.x, e.location.y, width, height);
				g.drawImage(Toolkit.getDefaultToolkit()
						.getImage("Files\\Image\\iimage.png"),e.location.x+1
						, e.location.y+1,null, this);
			}
			g.setColor(Color.white);
			if(Main.g.ccp.zoom==1) {
				g.setFont(new Font("",Font.BOLD,13));
				FontMetrics fm=g.getFontMetrics(g.getFont());
				/*
				if(fm.stringWidth(e.name)+24>Main.g.ccp.ewidth) {
					g.fillRect(e.location.x, e.location.y
							, fm.stringWidth(e.name)+24, Main.g.ccp.eheight);
				}*/
				g.drawString(e.name,e.location.x+22,e.location.y+Main.g.ccp.eheight/2+4);
			}else if(Main.g.ccp.zoom==2) {       //2
				g.setFont(new Font("",Font.BOLD,14));
				FontMetrics fm=g.getFontMetrics(g.getFont());
				g.drawString(e.name,e.location.x+22,e.location.y+14);
				g.setFont(new Font("",Font.PLAIN,13));
				int x=22,y=fm.getHeight()+18;
				try {
					if(e.describe.equals("")) {
						throw new NullPointerException();
					}
					char ct[]=e.describe.toCharArray();
					for(int j=0;j<e.describe.length();j++) {
						g.drawString(ct[j]+"",e.location.x+x,e.location.y+y);
						x+=fm.stringWidth(ct[j]+"")-2;
						if(x>=Main.g.ccp.ewidth-fm.stringWidth(ct[j]+"")) {
							y+=fm.getHeight();
							x=22;
						}
					}
				}catch(NullPointerException ex) {
					g.drawString("无描述.",e.location.x+x,e.location.y+y);
				}
			}else if(Main.g.ccp.zoom==3) {
				g.setFont(new Font("",Font.BOLD,14));
				FontMetrics fm=g.getFontMetrics(g.getFont());
				g.drawString(e.name,e.location.x+22,e.location.y+14);
				g.setFont(new Font("",Font.PLAIN,13));
				int x=22,y=fm.getHeight()+18;
				try {
					if(e.describe.equals("")) {
						throw new NullPointerException();
					}
					char ct[]=e.describe.toCharArray();
					for(int j=0;j<e.describe.length();j++) {
						g.drawString(ct[j]+"",e.location.x+x,e.location.y+y);
						x+=fm.stringWidth(ct[j]+"")-2;
						if(x>=190-fm.stringWidth(ct[j]+"")) {
							y+=fm.getHeight();
							x=22;
						}
					}
				}catch(NullPointerException ex) {
					g.setFont(new Font("",Font.ITALIC,15));
					g.drawString("无描述.",e.location.x+x,e.location.y+y);
				}
				if(e.image.size()==0) {
					g.setFont(new Font("",Font.ITALIC,15));
					g.drawString("无缩略图.",e.location.x+242,e.location.y+60);
				}
			}
		}
		if(Main.g.etp.e!=null) {
			Element e=Main.g.etp.e;
			g.setColor(new Color(26,56,200));
			g.drawRect(e.location.x-3, e.location.y-3, Main.g.ccp.ewidth+6, Main.g.ccp.eheight+6);
		}
		Main.g.tbp.resize();
		Main.g.tbp.repaint();
		//Main.g.mf.repaint();
	}
	public void reset() {
		
		this.eles=new ArrayList<Element>();
		eles.add(new Element(Element.INFO,0
				,"项目信息","",new Point(150,200)));
		_canvasMouse.vnum=_canvasMouse.anum=_canvasMouse.tnum=0;
		Main.fileName=new String();
		Main.g.mf.setTitle("脚本编辑 "+Main.ver);
		Main.g.mf.repaint();
	}
	public void loadFile(String path) {
		Main.g.ccp.setZoom(1);
		this.eles=new ArrayList<Element>();
		String firsts[]=new FileRW().read(path).split(":::");
		String nums[]=firsts[0].split(";");
		_canvasMouse.vnum=Integer.parseInt(nums[0]);
		_canvasMouse.anum=Integer.parseInt(nums[1]);
		_canvasMouse.tnum=Integer.parseInt(nums[2]);
		String fs[]=firsts[1].split(";e;");
		for(int i=0;i<fs.length;i++) {
			String es[]=fs[i].split(";:;");
			Element e=new Element(Integer.parseInt(es[4])
					,Integer.parseInt(es[3]),es[0],es[2]
							,new Point(Integer.parseInt(es[6].split(",")[0])
									,Integer.parseInt(es[6].split(",")[1])));
			e.setDescribe(es[1]);
			e.setMarker(Boolean.parseBoolean(es[5]));
			String imgs[]=es[7].split(":");
			for(int j=1;j<imgs.length;j++) {
				e.image.add(new ImageIcon(path+(path.endsWith("\\")?"":"\\")+imgs[j]));
			}
			Main.g.cvp.eles.add(e);
		}
		Main.fileName=new String(path);
		Main.g.mf.setTitle(path);
		Main.g.mf.repaint();
	}
}
