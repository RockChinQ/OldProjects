package guiMgr.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import boot.Main;
import guiMgr.FloatPanel;
import guiMgr.PnlColor;
import guiMgr.colorBoardTarget;
import guiMgr.panels.LabelCheck.labelEntry.clblock;
import log.Log;

public class LabelCheck extends FloatPanel implements MouseWheelListener {
	Font font=new Font("",Font.BOLD,14),tmf=new Font("",Font.PLAIN,15);
	Color tmc=new Color(230,230,230);//time color
	Color tcl=new Color(250,250,250);//title color
	int labelStart=0;
	public clblock lsClk=null;
	colorBoardTarget cbt=c->
	{((LabelCheck.labelEntry)Main.gui.bgp.lc.lsClk.getParent()).pointer.cl=c;
	Main.gui.bgp.lc.updateLabel();};
	public class labelEntry extends JPanel implements MouseListener, MouseWheelListener{
		long tc;
		String name;
		Color cl;
		public EditingPnl.Label pointer;
		time tm;
		title tl;
		clblock cb;
		public class time extends JPanel{
			String natureTime;
			public time () {
				this.setSize(90, 30);
			}
			public void paint(Graphics g) {
				set(tc);
				g.setColor(tmc);
				g.setFont(tmf);
				g.drawString(natureTime, 5, 16);
			}
		}
		public class title extends JPanel{
			public title() {
				this.setSize(70, 30);
			}
			public void paint(Graphics g) {
				g.setFont(font);
				g.setColor(tcl);
				g.drawString(name+"", 8, 10);
			}
		}
		public class clblock extends JPanel{
			public clblock() {
				this.setSize(20, 20);
			}
			public void paint(Graphics g) {
				g.setColor(cl);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
			}
		}
		
		public labelEntry(long timeCode,String name,Color cl,EditingPnl.Label pointer) {
			this.tc=timeCode;
			this.name=name;
			this.cl=cl;
			this.pointer=pointer;
			this.setLayout(null);
			this.setSize(200,30);
			
			tm=new time();
			tm.setLocation(0, 5);
			this.add(tm);
			
			tl=new title();
			tl.setLocation(90, 10);
			this.add(tl);
			
			cb=new clblock();
			cb.setLocation(170, 6);
			cb.addMouseListener(this);
			cb.addMouseWheelListener(this);
			this.add(cb);
		}
		
		public void set(long frame) {
			double fps=Main.cfd.fps;
			int fram=(int) (frame%fps);//设置帧数
			int seco=(int) (((frame-fram)/fps)%60);
			int minu=(int) ((((frame-fram)/fps-seco)/60)%60);
			int hour=(int) (((((frame-fram)/fps-seco)/60-minu)/60)%24);
			this.tm.natureTime=String.format("%02d",hour)+":"+String.format("%02d",minu)
							+":"+String.format("%02d",seco)+":"+String.format("%02d",fram);
		}

		public void mouseClicked(MouseEvent arg0) {
			if(!Main.gui.bgp.cb.isVisible()) {
				//Main.gui.bgp.cb.setVisible(true);
				Main.gui.bgp.cb.setVisible(cbt);
				Main.gui.bgp.cb.setLocation(this.getX()+220+Main.gui.bgp.lc.getX()
				, this.getY()+Main.gui.bgp.lc.getY()+20);
				lsClk=(labelEntry.clblock)arg0.getSource();
				Main.gui.bgp.cb.uid=((labelEntry)((labelEntry.clblock)arg0.getSource()).getParent()).pointer.uid;
			}else if(((labelEntry)((labelEntry.clblock)arg0.getSource()).getParent()).pointer.uid==Main.gui.bgp.cb.uid){
				Main.gui.bgp.cb.setVisible(null);
			}else {
				Main.gui.bgp.cb.setVisible(cbt);
				Main.gui.bgp.cb.setLocation(this.getX()+220+Main.gui.bgp.lc.getX()
				, this.getY()+Main.gui.bgp.lc.getY()+20);
				lsClk=(labelEntry.clblock)arg0.getSource();
				Main.gui.bgp.cb.uid=((labelEntry)((labelEntry.clblock)arg0.getSource()).getParent()).pointer.uid;
			}
		}
		public void mouseEntered(MouseEvent arg0) {
		}
		public void mouseExited(MouseEvent arg0) {
		}
		public void mousePressed(MouseEvent arg0) {
		}
		public void mouseReleased(MouseEvent arg0) {
		}
		public void mouseWheelMoved(MouseWheelEvent e) {
			this.pointer.clptr=this.pointer.clptr+e.getWheelRotation();
			Main.gui.bgp.cb.setVisible(null);
			if(this.pointer.clptr<12)
				this.pointer.clptr+=12;
			//System.out.println(this.pointer.clptr+" "+e.getWheelRotation());
			this.pointer.cl=Main.gui.bgp.cb.psc[Math.abs(this.pointer.clptr%12)];
			updateLabel();
		}
	}
	
	
	
	JPanel lbp;
	JLabel range;
	public LabelCheck() {
		this.setLayout(null);
		this.setSize(210, 180);
		
		range=new JLabel("显示:0-0");
		range.setSize(130, 15);
		range.setLocation(7, this.getHeight()-18);
		range.setForeground(tcl);
		this.add(range);
		
		lbp=new JPanel();
		lbp.setSize(200, 150);
		lbp.setLocation(5, 12);
		lbp.setLayout(null);
		lbp.addMouseWheelListener(this);
		this.add(lbp);
		this.setTitlex("标签管理");
		
	}
	public void updateLabel() {
		//System.out.println(this.labelStart);
		lbp.removeAll();
		System.gc();
		lbp.setBackground(Main.gui.bgp.pc.getColor(Main.gui.bgp.pc.labelCheck));
		//当前显示
		this.range.setText("显示:"+(this.labelStart+1)+"-"+
				(Main.gui.bgp.ep.projLbs.size()<this.labelStart+4
				?Main.gui.bgp.ep.projLbs.size():this.labelStart+4));
		for(int i=this.labelStart;i<Main.gui.bgp.ep.projLbs.size();i++) {
			labelEntry lc=new labelEntry(Main.gui.bgp.ep.projLbs.get(i).x
					,Main.gui.bgp.ep.projLbs.get(i).text,Main.gui.bgp.ep.projLbs.get(i).cl,Main.gui.bgp.ep.projLbs.get(i));
			lc.setBackground(PnlColor.getColor(this.getBackground(), 1.5));
			lc.setLocation(0,(lc.getHeight()+3)*(i-this.labelStart)+5);
			
			lbp.add(lc);
		}
		Main.gui.mwd.repaint();
	}
	public void loadCfg() {
		try {
			this.setLocation(Integer.parseInt(Main.cfg.get("labelCheck.x").toString())
					,Integer.parseInt(Main.cfg.get("labelCheck.y").toString()));
		}catch(Exception e) {
			Log.record("Read cfg failed.");
			e.printStackTrace();
		}
	}
	
	
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		//System.out.println(this.labelStart);
		if(arg0.getWheelRotation()==1) {//向下
			this.labelStart+=this.labelStart<Main.gui.bgp.ep.projLbs.size()-4?1:0;
			this.updateLabel();
		}else if(arg0.getWheelRotation()==-1) {
			this.labelStart-=this.labelStart==0?0:1;
			this.updateLabel();
		}
	}
}
