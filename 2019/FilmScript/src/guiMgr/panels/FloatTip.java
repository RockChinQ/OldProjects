package guiMgr.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import boot.Main;

public class FloatTip extends JPanel{
	Color bg=new Color(20,140,230);
	public static final Color MSG_CL=new Color(20,140,230),WN_CL=new Color(210,170,0),ER_CL=new Color(220,20,20);
	public static final int MESSAGE=0,WARNING=1,ERROR=2;
	int type=0;
	private Font font=new Font("свт╡",Font.BOLD,15);
	private String tip="";
	public int time=0;
	static final int DISPLAY_DELAY=3000;
	public void setTip(String tip) {
		this.tip=tip;
		this.resize();
	}
	public void displayTip(String tip,int type) {
		this.setTip(tip);
		this.setType(type);
		this.resize();
		this.repaint();
		this.time=this.DISPLAY_DELAY;
	}
	public void setType(int type) {
		//System.out.println(this.getLocation()+"=============================");
		if(type==0)
			bg=this.MSG_CL;
		else if(type==1)
			bg=this.WN_CL;
		else if(type==2)
			bg=this.ER_CL;
		else
			return;
		this.type=type;
	}
	public void setFont(Font font) {
		this.font=font;
	}
	public void paint(Graphics g) {
		//System.out.println("=============================");
		g.setColor(bg);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(new Color(240,240,240));
		g.setFont(font);
		g.drawString(tip, 10,18);
		//this.setVisible(true);
	}
	public FloatTip() {
		this.setLayout(null);
	}
	public void resize() {
		FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(font);
		int w=SwingUtilities.computeStringWidth(fm, tip);
		this.setSize(w+20, fm.getHeight()+10);
		if(time>125)
			this.setLocation(Main.gui.bgp.getWidth()/2-this.getWidth()/2,8);
		else {
			this.setLocation(Main.gui.bgp.getWidth()/2-this.getWidth()/2,(int) (time*.152-30));
			//System.out.println(time);
		}
	}
}
