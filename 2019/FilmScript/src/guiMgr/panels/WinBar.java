package guiMgr.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import boot.Main;
import guiMgr.FloatPanel;

public class WinBar extends JPanel {
	public class winLabel extends JPanel{
		public FloatPanel fp;
		public winLabel(FloatPanel fp) {
			this.fp=fp;
			
			this.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e) {
					if(fp.isEnabled()) {
						fp.setVisible(!fp.isVisible());
						repaint();
					}
				}
				public void mouseEntered(MouseEvent e) {
				}
				public void mouseExited(MouseEvent e) {
				}
				public void mousePressed(MouseEvent e) {
				}
				public void mouseReleased(MouseEvent e) {
				}
			});
		}
		public void paint(Graphics g) {
			g.setColor(fp.getBackground());
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(fp.isVisible()?(new Color(20,140,250)):(new Color(180,180,180)));
			g.drawRect(0, 0, this.getWidth()-2, this.getHeight()-2);
			g.setColor(new Color(240,240,240));
			g.drawString(fp.tl.title, 3, 12);
			if(!fp.isEnabled()) {
				this.setEnabled(false);
				g.setColor(new Color(100,100,100,140));
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
			}
		}
	}
	
	public class icon extends JPanel{
		public void paint(Graphics g) {
			g.setColor(getBackground());
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(new Color(240,240,240));
			g.drawRect(5, 3, 12, 10);
			g.fillRect(5, 4, 12, 2);
		}
	}
	icon ic=new icon();
	public WinBar(){
		this.setLayout(null);
		
		ic.setSize(25, 20);
		ic.setLocation(4, 0);
		this.add(ic);
	}
	public void resize() {
		ic.setBackground(getBackground());
		this.repaint();
	}
	int index=0;
	public void addLabel(FloatPanel fp) {
		winLabel wl=new winLabel(fp);
		wl.setSize(70, 20);
		wl.setLocation((index++)*(70+2)+30, 0);
		this.add(wl);
		this.repaint();
	}
}
