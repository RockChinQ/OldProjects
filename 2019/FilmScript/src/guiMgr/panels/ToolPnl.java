package guiMgr.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import boot.Main;
import guiMgr.FloatPanel;
import guiMgr.PnlColor;
import log.Log;

public class ToolPnl extends FloatPanel{
	PnlColor pc=new PnlColor();
	class tool extends JPanel{
		String name="工具";
		String descri="点击以选择";
		boolean chosen=false;
		public void setName(String name) {
			this.name=new String(name);
		}
		public void setDescribe(String d) {
			this.descri=new String(d);
		}
		public void setChosen(boolean chosen) {
			this.chosen=chosen;
		}
		public boolean isChosen() {
			return this.chosen;
		}
		public void paint(Graphics g) {
			if(chosen) {
				g.setColor(new Color(0,148,210));//(17,68,115)
			}else {
				g.setColor(pc.getColor(pc.toolPnl));
			}
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
	}
	
	class selector extends tool{
		public selector() {
			this.setSize(20, 20);
			this.setName("选择工具");
			this.setDescribe("点选或框选素材");
		}
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(200,200,200));
			g.drawLine(6, 3,6, 14);
			g.drawLine(6, 3, 13, 10);
			g.drawLine(6, 14, 13, 10);  //mid-point:(9.5,14.5)
			g.drawLine(9, 12, 11,16);
			g.drawLine(10, 11, 12,15);
			g.drawLine(11, 16, 12, 15);
			//fill
			g.drawLine(7,4, 7,13);
			g.drawLine(8,5, 8,12);
			g.drawLine(9,6, 9,11);
			g.drawLine(10,7, 10,10);
			g.drawLine(11,8, 11,9);
			g.drawLine(12,9, 12,10);
			g.drawLine(11,9, 11,10);
			g.drawLine(10, 12, 11,15);
		}
	}
	class scissors extends tool{
		public scissors() {
			this.setSize(20, 20);
			this.setName("剪刀工具");
			this.setDescribe("切割素材或项目");
		}
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(200,200,200));
			g.drawLine(3, 8, 13, 10);
			g.drawLine(7, 4,9, 13);
			g.drawOval(13, 9, 4, 4);
			g.drawOval(8, 13, 4, 4);
		}
	}
	class upward extends tool{
		public upward() {
			this.setSize(20, 20);
			this.setName("上升");
			this.setDescribe("点击以将所选项上移到上一轨道");
		}
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(200,200,200));
			g.fillRect(3, 9, 2,9);
			g.fillRect(15, 9, 2,9);
			g.drawLine(9, 7, 6, 11);
			g.drawLine(9, 6, 6, 10);
			g.drawLine(9, 7, 12, 11);
			g.drawLine(9, 6, 12, 10);
		}
	}
	class downward extends tool{
		public downward() {
			this.setSize(20, 20);
			this.setName("下降");
			this.setDescribe("点击以将所选项下移到下一轨道");
		}
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(200,200,200));
			g.fillRect(3, 7, 2,9);
			g.fillRect(15, 7, 2,9);
			
			int btm=25;
			g.drawLine(9, btm-9, 6, btm-13);
			g.drawLine(9, btm-8, 6, btm-12);
			g.drawLine(9, btm-9, 12, btm-13);
			g.drawLine(9, btm-8, 12, btm-12);
		}
	}
	class div extends JLabel{
		public div() {
			this.setSize(2, 20);
			
		}
		public void paint(Graphics g) {
			g.setColor(new Color(200,200,200));
			g.fillRect(0, 2, 1,18);
		}
	}
	class addvideo extends tool{
		public addvideo() {
			this.setSize(20,20);
			this.setName("添加影像片段");
			this.setDescribe("在轨道上拖拽以绘制新的影像片段");
		}
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(200,200,200));
			//g.drawRect(0, 0, 19, 29);
			
			g.drawRect(2, 4, 14, 12);
			g.fillRect(4, 8, 3, 5);
			g.fillRect(8, 8, 3, 5);
			g.fillRect(12, 8, 3, 5);

			g.fillRect(4, 6, 1, 1);
			g.fillRect(6, 6, 1, 1);
			g.fillRect(8, 6, 1, 1);
			g.fillRect(10, 6, 1, 1);
			g.fillRect(12, 6, 1, 1);
			g.fillRect(14, 6, 1, 1);
			
			int h=14;
			g.fillRect(4, h, 1, 1);
			g.fillRect(6, h, 1, 1);
			g.fillRect(8, h, 1, 1);
			g.fillRect(10, h, 1, 1);
			g.fillRect(12, h, 1, 1);
			g.fillRect(14, h, 1, 1);
		}
	}
	class addaudio extends tool{
		public addaudio() {
			this.setSize(20, 20);
			this.setName("添加音频片段");
			this.setDescribe("在轨道上拖拽以绘制新的音频片段");
		}
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(200,200,200));
			
			g.drawRect(2, 4, 14, 12);
			g.drawLine(2, 10, 16, 10);
			g.drawLine(5, 8, 5, 12);
			g.drawLine(9, 6, 9, 14);
			g.drawLine(12, 7, 12, 13);
			
		}
	}
	class addva extends tool{
		public addva() {
			this.setSize(20, 20);
			this.setName("添加视频片段");
			this.setDescribe("在视频和音频轨道上添加同步的影像和音频片段");
		}
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(200,200,200));
			g.drawRect(2, 4, 14, 5);
			g.drawRect(2, 11, 14, 5);

			g.fillRect(4, 6, 3, 2);
			g.fillRect(8, 6, 3, 2);
			g.fillRect(12, 6, 3, 2);
			
			g.drawLine(5, 13, 5, 14);
			g.drawLine(9, 12, 9, 15);
			g.drawLine(12, 13, 12, 14);
		}
	}
	
	public selector sl=new selector();
	public scissors sc=new scissors();
	public upward uw=new upward();
	public downward dw=new downward();
	public div div=new div();
	public addvideo adv=new addvideo();
	public addaudio ada=new addaudio();
	public addva ava=new addva();
	
	public toolMouse tm=new toolMouse();
	
	public tool toolSelected=sl;
	public ToolPnl() {
		this.setLayout(null);
		
		sl.setLocation(5,18);
		this.add(sl);
		sc.setLocation(30, 18);
		this.add(sc);
		uw.setLocation(55, 18);
		this.add(uw);
		dw.setLocation(80, 18);
		this.add(dw);
		div.setLocation(102, 18);
		this.add(div);
		adv.setLocation(105, 18);
		this.add(adv);
		ada.setLocation(130, 18);
		this.add(ada);
		ava.setLocation(155, 18);
		this.add(ava);
		
		this.setTitlex("工具栏");
		sl.setChosen(true);
		
		sl.addMouseListener(tm);
		sc.addMouseListener(tm);
		uw.addMouseListener(tm);
		dw.addMouseListener(tm);
		adv.addMouseListener(tm);
		ada.addMouseListener(tm);
		ava.addMouseListener(tm);
	}
	public void loadCfg() {
		try {
			this.setLocation(Integer.parseInt(Main.cfg.get("toolPnl.x").toString())
					,Integer.parseInt(Main.cfg.get("toolPnl.y").toString()));
		}catch(Exception e) {
			Log.record("Read cfg failed.");
			e.printStackTrace();
		}
	}
	public void select(tool tl) {
		this.toolSelected.chosen=false;
		this.toolSelected=tl;
		this.toolSelected.chosen=true;
	}
}
