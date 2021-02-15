package guiMgr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import guiMgr.FloatPanel.title;
import guiMgr.fields.Icon;

public class FixedPanel extends JScrollPane {

	public class title extends JLabel{
		public String title="Fixed Panel";
		Color bg=Color.GRAY,point=new Color(200,200,200);
		Color line=new Color(180,180,180);
		Font font=new Font("",Font.PLAIN,14);
		int lineLen=100;
		public title(String title) {
			this.title=title;
		}
		public title() {
			;
		}
		public void setTitle(String title) {
			this.title=new String(title);
		}
		public void paint(Graphics g) {
			g.setColor(point);
			
			g.setFont(font);
			FontMetrics fm = g.getFontMetrics();
			int w=fm.stringWidth(title);
			g.drawString(title, 30, 16);
			g.setColor(line);
			g.fillRect(0, 23,lineLen, 3);
		}
	}
	
	public title tl;
	public JPanel jp=new JPanel();
	public Icon ico=new Icon("2");
	public FixedPanel() {
		
		jp.setLayout(null);
		this.add(jp);
		this.setSize(500, 200);
		this.setBorder(null);
		
		ico.setSize(12, 12);
		ico.setLocation(13, 5);
		ico.setVisible(false);
		super.add(ico);
		
		tl=new title();
		tl.setSize(this.getWidth(), 30);
		tl.setLocation(0, 0);
		super.add(tl);
		
		
	}
	public void setBackgroudx(Color bg) {
		this.tl.bg=bg;
		this.setBackground(bg);
	}
	public void resize() {
		FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(tl.font);
		int w=SwingUtilities.computeStringWidth(fm, tl.title);
		tl.lineLen=w+60;
		tl.setSize(this.getWidth(), 25);
		this.repaint();
	}
	public void setTitlex(String t) {
		this.tl.title=t;
	}
	public void setSize(int w,int h) {
		super.setSize(w, h);
	}
	public Component add(Component comp) {
		return super.add(comp);
	}
	public void setIcon(String code) {
		this.ico.set(code);
		if(!"2".equals(code))
			ico.setVisible(true);
	}
}
