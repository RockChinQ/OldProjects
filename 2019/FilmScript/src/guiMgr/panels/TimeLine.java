package guiMgr.panels;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import boot.Main;
import guiMgr.PnlColor;
import guiMgr.panels.EditingPnl.Label;

public class TimeLine extends JPanel{
	public class timeData{
		public int day=0,hour=0,minu=0,seco=0,fram=0;
		public timeData() {
			
		}
		public timeData(long frame) {//由传入的帧数计算长度
			set(frame);
		}
		public void set(long frame) {
			double fps=Main.cfd.fps;
			this.fram=(int) (frame%fps);//设置帧数
			this.seco=(int) (((frame-this.fram)/fps)%60);
			this.minu=(int) ((((frame-this.fram)/fps-this.seco)/60)%60);
			this.hour=(int) (((((frame-this.fram)/fps-this.seco)/60-this.minu)/60)%24);
		}
	}
	
	timeData td=new timeData();
	public void TimeLine() {
	}
	public void paint(Graphics g) {
		EditingPnl ep=Main.gui.bgp.ep;
		g.setColor(PnlColor.getColor(ep.getBackground(), 1.2));
		g.fillRect(0, 0, this.getWidth(),this.getHeight());
		g.setColor(new Color(200,200,200));
		g.drawLine(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2);
//		int lastSecond=-60;
//		for(int i=ep.tmlnStart;i<(int)(this.getWidth()/ep.zoom)+ep.tmlnStart;i++) {
//			//System.out.println(i+" "+(i%30==0));
//			if(i%3==0)
//				g.drawLine((int)((i-ep.tmlnStart)*ep.zoom), 12, (int)((i-ep.tmlnStart)*ep.zoom), 15);
//			//System.out.println((int)((i)*zoom)+" "+(int)((i-30)*zoom));
//			if(i%30==0&&(int)((i-ep.tmlnStart)*ep.zoom)-lastSecond>=60) {
//				g.drawLine((int)((i-ep.tmlnStart)*ep.zoom), 0, (int)((i-ep.tmlnStart)*ep.zoom), 15);
//				g.drawString(i/30+" \"",(int)((i-ep.tmlnStart)*ep.zoom), 9);
//				lastSecond=(int)((i-ep.tmlnStart)*ep.zoom);
//			}
//		}
//		for(int i=0;i<ep.getWidth();i+=100) {
//			g.drawLine(i, 15, i, 25);
//			g.drawString((int)(i/ep.zoom)+ep.tmlnStart+"", i, 30);
//		}
		//System.out.println(Main.gui.bgp.ep.zoom);
		//绘制帧标记
			//计算标签间隔
		double space=0;
		double zoom=Main.gui.bgp.ep.zoom;
		if(zoom>=31){
			space=1;
		}else if(zoom<=17&&zoom>=7) {//16-8
			space=5;
		}else if(zoom<=5&&zoom>=1.9) {//4-2
			space=20;
		}else if(zoom<=1.1&&zoom>=0.5) {//1-0.5
			space=100;
		}else if(zoom<=.26&&zoom>=.124){//0.25-0.125
			space=500;
		}else {
			space=1000;
		}
		//System.out.println(space);
		int frameCount=EditingPnl.getFrameByPixel(this.getWidth())-Main.gui.bgp.ep.tmlnStart;
		for(int i=0;i<frameCount;i++) {
			if((i+Main.gui.bgp.ep.tmlnStart)%space==0) {
				g.drawString((i+Main.gui.bgp.ep.tmlnStart)+"", (int)Math.round(Main.gui.bgp.ep.zoom*i)-3, this.getHeight()/2+24);
				g.drawLine((int)Math.round(Main.gui.bgp.ep.zoom*i), this.getHeight()/2
						, (int) Math.round(Main.gui.bgp.ep.zoom*i), this.getHeight()/2+14);
			}else {
				g.drawLine((int)Math.round(Main.gui.bgp.ep.zoom*i), this.getHeight()/2
						, (int) Math.round(Main.gui.bgp.ep.zoom*i), this.getHeight()/2+8);
			}
		}
		//绘制自然时间标记
			//计算标签位置
		space=0;
		if(zoom>=15) {//32 16
			space=Main.cfd.fps/2;
		}else if(zoom<=9&&zoom>=7) {//8
			space=Main.cfd.fps;
		}else if(zoom<=5&&zoom>=1.9) {//4 2
			space=Main.cfd.fps*2;
		}else if(zoom<=1.1&&zoom>=0.4) {//1 .5
			space=Main.cfd.fps*5;
		}else if(zoom<=.26&&zoom>=.24) {//.25
			space=Main.cfd.fps*10;
		}else if(zoom<=.126&&zoom>=.124) {//.125
			space=Main.cfd.fps*20;
		}else {//.0625
			space=Main.cfd.fps*60;
		}
		//System.out.println(Main.gui.bgp.ep.tmlnStart+" "+EditingPnl.getFrameByPixel(this.getWidth())+" sp"+space);
		for(int i=Main.gui.bgp.ep.tmlnStart;i<EditingPnl.getFrameByPixel(this.getWidth());i++) {
			//System.out.println(i);
			if(i%(int)Math.round(space)==0) {
				//System.out.println(i+" "+space);
				td.set(i);
				g.drawLine((int) (zoom*(i-Main.gui.bgp.ep.tmlnStart))
						, 10,(int) (zoom*(i-Main.gui.bgp.ep.tmlnStart)) , this.getHeight()/2);
				g.drawString(td.hour+":"+td.minu+":"+td.seco+":"+td.fram
						,(int) (zoom*(i-Main.gui.bgp.ep.tmlnStart))-20, 8);
			}else if(i%(int)Math.round(space/10)==0) {
				g.drawLine((int) (zoom*(i-Main.gui.bgp.ep.tmlnStart))
						, 19,(int) (zoom*(i-Main.gui.bgp.ep.tmlnStart)) , this.getHeight()/2);
			}
		}
		
		//绘制标记
		for(int i=0;i<ep.projLbs.size();i++) {
			Label tl=ep.projLbs.get(i);
			if(tl.x>=ep.tmlnStart&&tl.x<=(int)(ep.tmln.getWidth()/ep.zoom)+ep.tmlnStart) {
				g.setColor(tl.cl);
				g.fillRect((int)((tl.x-ep.tmlnStart)*ep.zoom)-3, 10, 7, 6);
				g.drawLine((int)((tl.x-ep.tmlnStart)*ep.zoom), 10, (int)((tl.x-ep.tmlnStart)*ep.zoom), this.getHeight()-8);
			}
		}
	}
}
