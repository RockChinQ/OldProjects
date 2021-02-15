package guiMgr;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import boot.Main;

public class Workflow extends JPanel implements MouseListener{
	public static int now=0;
	public static final int SCRIPT=0,DETAIL=1,TASK=2;
	static final int height=35;
	static final Font font=new Font("",Font.PLAIN,12),chosen=new Font("",Font.BOLD,12);
	static final int bw=60,bh=30,space=30;
	Color bg=new Color(45,45,45);
	static final Color fcl=new Color(200,200,200),cho=new Color(80,170,250);
	
	script sc=new script();
	detail dt=new detail();
	task ts=new task();
	public Workflow() {
		//System.out.println(111);
		this.setLayout(null);

		sc.setLocation(5, 0);
		sc.setSize(bw, bh);
		this.add(sc);
		dt.setLocation(bw+space+5, 0);
		dt.setSize(bw, bh);
		this.add(dt);
		ts.setLocation(bw*2+space*2+5, 0);
		ts.setSize(bw, bh);
		this.add(ts);
		
		sc.addMouseListener(this);
		dt.addMouseListener(this);
		ts.addMouseListener(this);
	}
	
	public void changeWorkflow(int wf) {
		this.now=wf;
		
		if(now==SCRIPT) {
			Main.gui.sbp.resize();
			Main.gui.bgp.setVisible(false);
			Main.gui.sbp.setVisible(true);
		}else if(now==DETAIL) {
			Main.gui.bgp.resize();
			Main.gui.sbp.setVisible(false);
			Main.gui.bgp.setVisible(true);

			Main.gui.bgp.ep.setFocusable(true);
			Main.gui.bgp.ep.requestFocus();
		}else if(now==TASK) {
			Main.gui.sbp.setVisible(false);
			Main.gui.bgp.setVisible(false);
		}
		this.repaint();
	}
	public void resize() {
		bg=this.getBackground();
		this.setSize(Main.gui.mwd.getWidth(), 30);
	}
	public int getWorkflow() {
		return now;
	}
	
	class script extends JPanel{
		public script() {
			this.setSize(bw, bh);
		}
		public void paint(Graphics g) {
			g.setColor(bg);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(now==SCRIPT?cho:fcl);
			g.setFont(font);
			g.drawString("分镜", 10, 20);
		}
	}
	class detail extends JPanel{
		public detail() {
			this.setSize(bw, bh);
		}
		public void paint(Graphics g) {
			//System.out.println(now);
			g.setColor(bg);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(now==DETAIL?cho:fcl);
			g.setFont(font);
			g.drawString("时间线", 5, 20);
		}
	}
	class task extends JPanel{
		public task() {
			this.setSize(bw, bh);
		}
		public void paint(Graphics g) {
			//System.out.println(now);
			g.setColor(bg);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(now==TASK?cho:fcl);
			g.setFont(font);
			g.drawString("任务", 10, 20);
		}
	}
	public void mouseClicked(MouseEvent arg0) {
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
		if(arg0.getSource()==sc) {
			this.changeWorkflow(SCRIPT);
		}else if(arg0.getSource()==dt) {
			this.changeWorkflow(DETAIL);
		}else if(arg0.getSource()==ts) {
			this.changeWorkflow(TASK);
		}
	}
	public void mouseReleased(MouseEvent arg0) {
	}
}
