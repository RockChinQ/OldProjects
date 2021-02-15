package guiMgr.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import boot.Main;
import guiMgr.FloatPanel;
import guiMgr.PnlColor;
import log.Log;

public class TrackOper extends FloatPanel implements MouseListener{
	
	class timeCode extends JPanel{
		Font text=new Font("",Font.BOLD,20);
		public timeCode() {
			this.setSize(122, 20);
			this.setBackground(PnlColor.getColor(this.getBackground(), 1.2));
		}
		public void paint(Graphics g) {
			g.setColor(Main.gui.bgp.pc.getColor(Main.gui.bgp.pc.trackOper));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			TimeLine.timeData td=Main.gui.bgp.ep.tmln.td;
			td.set(Main.gui.bgp.ep.playNow);
			g.setColor(new Color(200,200,200));
			g.setFont(text);
			
			//String.format("%02d",)
			g.drawString(String.format("%02d",td.hour)+":"+String.format("%02d",td.minu)+
					":"+String.format("%02d",td.seco)+":"+String.format("%02d",td.fram)
					, 10, 15);
		}
	}
	
	class TrackButton extends JPanel{
		public String name="TrackButton";
		public String descri="click to operate.";
	}
	class enhigh extends TrackButton{
		public enhigh() {
			this.descri="增加轨道高度";
			this.setSize(30, 30);
		}
		public void paint(Graphics g) {
			g.setColor(new Color(200,200,200));
			g.setFont(new Font("",Font.PLAIN,25));
			//g.drawString("+", 7, 20);
			g.drawLine(12, 6, 12, 18);
			g.drawLine(13, 6, 13, 18);
			g.drawLine(7, 12, 19, 12);
			g.drawLine(7, 13, 19, 13);
		}
	}
	class enlow extends TrackButton{
		public enlow() {
			this.descri="减少轨道高度";
			this.setSize(30, 30);
		}
		public void paint(Graphics g) {
			g.setColor(new Color(200,200,200));
			g.setFont(new Font("",Font.PLAIN,25));
			//g.drawString("-", 7, 18);
			//g.drawString("-", 12, 18);
			g.drawLine(7, 12, 18, 12);
			g.drawLine(7, 13, 18, 13);
		}
	}
	class up extends TrackButton{
		public up() {
			this.descri="时间轴上移";
			this.setSize(30, 30);
		}
		public void paint(Graphics g) {
			g.setColor(new Color(200,200,200));
			g.setFont(new Font("",Font.PLAIN,20));
			//g.drawString("↓", 5, 18);
			g.drawLine(12, 5, 12, 18);
			g.drawLine(11, 5, 11, 18);
			g.drawLine(11, 18, 6, 13);
			g.drawLine(11, 17, 6, 12);
			g.drawLine(12, 18, 17, 13);
			g.drawLine(12, 17, 17, 12);
		}
	}
	class down extends TrackButton{
		public down() {
			this.descri="时间轴下移";
			this.setSize(30, 30);
		}
		public void paint(Graphics g) {
			g.setColor(new Color(200,200,200));
			g.setFont(new Font("",Font.PLAIN,20));
			//g.drawString("↑", 5, 18);
			g.drawLine(10, 5, 10, 18);
			g.drawLine(9, 5, 9, 18);
			g.drawLine(9, 5, 4, 10);
			g.drawLine(10, 5, 5, 10);
			g.drawLine(10, 5, 15, 10);
			g.drawLine(10, 4, 15, 9);
		}
	}
	class label extends TrackButton{
		public label() {
			this.descri="在当前位置增加/删除标记";
			this.setSize(30,30);
		}
		public void paint(Graphics g) {
			g.setColor(new Color(200,200,200));
			g.fillRect(7, 6, 7,8);
			g.drawLine(7, 13,9, 17);
			g.drawLine(13, 13,9, 17);
			g.drawLine(8, 13, 13, 13);
			g.drawLine(9, 14, 12, 14);
			g.drawLine(9, 15, 11, 15);
		}
	}
	class lastFrame extends TrackButton{
		public lastFrame() {
			this.descri="上一帧";
			this.setSize(30, 30);
		}
		public void paint(Graphics g) {
			g.setColor(new Color(200,200,200));
			g.drawLine(12, 8, 12, 16);
			for(int i=0;i<5;i++) {
				g.drawLine(i+4, 12-i, i+4, 12+i);
			}
		}
	}
	class play extends TrackButton{
		public play() {
			this.descri="播放";
			this.setSize(30,30);
		}
		public void paint(Graphics g) {
			g.setColor(new Color(200,200,200));
			for(int i=0;i<6;i++) {
				g.drawLine(i+7, 12-(5-i), i+7, 12+(5-i));
			}
		}
	}
	class nextFrame extends TrackButton{
		public nextFrame() {
			this.descri="下一帧";
			this.setSize(30, 30);
		}
		public void paint(Graphics g) {
			g.setColor(new Color(200,200,200));
			g.drawLine(3, 8,3, 16);
			for(int i=0;i<5;i++) {
				g.drawLine(i+7, 12-(4-i), i+7, 12+(4-i));
			}
		}
	}
	timeCode tc=new timeCode();
	
	enlow el=new enlow();
	enhigh eh=new enhigh();
	up up=new up();
	down down=new down();
	label label=new label();
	lastFrame lf=new lastFrame();
	play play=new play();
	nextFrame nf=new nextFrame();
	public TrackOper() {
		this.setLayout(null);
		
		tc.setLocation(0, 15);
		this.add(tc);
		int dx=120;
		eh.setLocation(5+dx, 12);
		this.add(eh);
		el.setLocation(35+dx, 12);
		this.add(el);
		up.setLocation(65+dx, 12);
		this.add(up);
		down.setLocation(95+dx, 12);
		this.add(down);
		label.setLocation(125+dx, 12);
		this.add(label);
		lf.setLocation(155+dx, 12);
		this.add(lf);
		play.setLocation(185+dx, 12);
		this.add(play);
		nf.setLocation(215+dx, 12);
		this.add(nf);
		
		eh.addMouseListener(this);
		el.addMouseListener(this);
		up.addMouseListener(this);
		down.addMouseListener(this);
		label.addMouseListener(this);
		lf.addMouseListener(this);
		nf.addMouseListener(this);
		play.addMouseListener(this);
		
		this.setTitlex("轨道控制");
	}
	public void loadCfg() {
		try {
			this.setLocation(Integer.parseInt(Main.cfg.get("trackOper.x").toString())
					,Integer.parseInt(Main.cfg.get("trackOper.y").toString()));
		} catch (Exception e) {
			Log.record("Read cfg failed.");
			e.printStackTrace();
		}
	}
	public void mouseClicked(MouseEvent arg0) {
		super.mouseClicked(arg0);
	}
	public void mouseEntered(MouseEvent arg0) {
		super.mouseEntered(arg0);
		if(arg0.getSource().getClass().getSuperclass().equals(new TrackButton().getClass())) {
			Main.gui.bgp.ib.setTip(((TrackButton)arg0.getSource()).descri);
		}
	}
	public void mouseExited(MouseEvent arg0) {
		super.mouseExited(arg0);
		Main.gui.bgp.ib.setTip("就绪");
	}
	public void mousePressed(MouseEvent arg0) {
		super.mousePressed(arg0);
		if(arg0.getSource()==up) {
			Main.gui.bgp.ep.add+=Main.gui.bgp.ep.eleHeight;
		}else if(arg0.getSource()==down) {
			Main.gui.bgp.ep.add-=Main.gui.bgp.ep.eleHeight;
		}else if(arg0.getSource()==eh&&Main.gui.bgp.ep.eleHeight<50) {
			Main.gui.bgp.ep.eleHeight+=5;
		}else if(arg0.getSource()==el&&Main.gui.bgp.ep.eleHeight>5) {
			Main.gui.bgp.ep.eleHeight-=5;
		}else if(arg0.getSource()==label) {
			//Main.gui.bgp.ep.addLabel(Main.gui.bgp.ep.playNow);
			Main.gui.bgp.ep.addLabel(Main.gui.bgp.ep.playNow,"Label-"+
					(Main.gui.bgp.ep.labelIndex+1),null);
		}else if(arg0.getSource()==lf) {
			if(Main.gui.bgp.ep.playNow>0)
				Main.gui.bgp.ep.playNow--;
		}else if(arg0.getSource()==nf) {
			Main.gui.bgp.ep.playNow++;
		}
		Main.gui.bgp.ep.updateElement();
	}
	public void mouseReleased(MouseEvent arg0) {}
}

