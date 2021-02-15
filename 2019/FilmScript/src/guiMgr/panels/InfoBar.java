package guiMgr.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import boot.Main;
import guiMgr.PnlColor;
import log.Log;

public class InfoBar extends JPanel implements MouseListener {
	private double progress=1;
	private String tip="就绪",prgrText="初始化完成.";
	//private int mode=0;
	public InfoBar() {
		this.addMouseListener(this);
	}
	public void resize() {
		this.repaint();
	}
	public void setTip(String tip) {
		this.tip=new String(tip);
		this.repaint();
	}
	public void setProgressText(String progressText) {
		this.prgrText=new String(progressText);
		this.repaint();
	}
	public void setProgressRate(double rate) {
		this.progress=rate;
		this.repaint();
	}
//	public void setViewMode(int modeCode) {
//		this.mode=modeCode;
//	}
	public void paint(Graphics g) {
		g.setColor(this.getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(new Color(200,200,200));
		/*
		g.drawOval(8, 6, 4, 4);
		g.drawLine(8, 1, 11, 1);
		g.drawLine(8, 1, 8, 3);
		g.drawLine(8, 3, 6, 5);
		g.drawLine(6, 5, 4, 4);
		g.drawLine(4, 4, 3, 7);
		g.drawLine(3, 7, 5, 8);
		g.drawLine(5, 8, 7, 11);
		g.drawLine(7, 11, 6, 13);
		g.drawLine(6, 13, 10, 14);
		g.drawLine(10, 14, 10, 12);
		g.drawLine(10, 12, 14, 11);
		g.drawLine(14, 11, 15, 12);
		g.drawLine(15, 12, 17, 9);
		g.drawLine(17, 9, 15, 8);
		g.drawLine(15, 8, 14, 5);
		g.drawLine(13, 5, 13, 1);*/
		g.drawLine(9, 2, 9, 13);
		g.drawLine(10, 2, 10, 13);
		g.drawLine(4, 7, 15, 7);
		g.drawLine(4, 8, 15, 8);
		g.drawLine(5, 3, 13, 12);
		g.drawLine(6, 3, 14, 12);
		g.drawLine(14, 3, 5, 12);
		g.drawLine(13, 3, 4, 12);
		g.fillOval(6, 4, 7, 7);
		g.setColor(this.getBackground());
		g.fillOval(8, 6, 3, 3);
		

		g.setColor(new Color(200,200,200));
		g.drawString("帧:"+Main.gui.bgp.ep.playNow, 25, 12);
		
		g.drawLine(90, 0, 90, 20);
		g.drawString("素材:"+Main.gui.bgp.ep.elms.size()+"段", 93, 12);
		
		g.drawLine(21, 0, 21, 20);
		g.drawLine(165, 0, 165, 20);
		g.drawString(tip, 169, 12);
		
		g.drawRect(this.getWidth()-(this.getWidth()/3)-60, 2, this.getWidth()/6, 12);
		g.drawString(prgrText,this.getWidth()-(this.getWidth()/3)+this.getWidth()/6+8-60 
				, 12);
		g.fillRect(this.getWidth()-(this.getWidth()/3)-60, 2
				,(int) (this.getWidth()/6*this.progress), 12);
		g.setFont(new Font(null,Font.BOLD,12));
		g.setColor(new Color(0,100,190));
		g.drawString((int)(progress*100)+"%"
				, this.getWidth()-(this.getWidth()/3)+this.getWidth()/12-70, 13);
	
		//视图按钮
			//已选标记
		int sx=this.getWidth()-60,sy=2;
		g.setColor(PnlColor.getColor(this.getBackground(),0.6));
		if(Main.gui.bgp.ep.viewMode==Main.gui.bgp.ep.TIMELINE_VIEW) {
			g.fillRect(sx-5, sy-2, 24, 18);
		}else if(Main.gui.bgp.ep.viewMode==Main.gui.bgp.ep.GROUP_VIEW) {
			g.fillRect(sx+20, sy-2, 24, 18);
		}
			//主视图（时间轴视图）
		g.setColor(new Color(200,200,200));
		g.drawRect(sx, sy, 14, 13);
		g.fillRect(sx+1, sy+3, 9, 3);
		g.fillRect(sx+3, sy+8, 11, 3);
		g.drawLine(sx+7, sy, sx+7, sy+13);
			//分组视图
		g.drawRect(sx+25, sy, 14, 13);
		g.fillRect(sx+27, sy+2, 5, 4);
		g.fillRect(sx+33, sy+2, 5, 4);
		g.fillRect(sx+27, sy+8, 5, 4);
		g.fillRect(sx+33, sy+8, 5, 4);
		
		g.drawLine(0, 0, this.getWidth(), 0);
		
	}
	public boolean range(int n,int min,int max) {
		return (n>=min&&n<=max);
	}
	public void mouseClicked(MouseEvent arg0) {
		int cx=arg0.getX();
		if(arg0.getX()<=20) {
			Log.record("Open setting.");
		}else if(range(cx,this.getWidth()-65,this.getWidth()-40)) {
			Main.gui.bgp.ep.viewMode=Main.gui.bgp.ep.TIMELINE_VIEW;
			Log.record("Current view "+Main.gui.bgp.ep.viewMode);
			Main.gui.updateViewMode(); 
		}else if(range(cx,this.getWidth()-40,this.getWidth()-16)) {
			Main.gui.bgp.ep.viewMode=Main.gui.bgp.ep.GROUP_VIEW;
			Log.record("Current view "+Main.gui.bgp.ep.viewMode);
			Main.gui.updateViewMode();
		}
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
