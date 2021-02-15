package Boot;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Classes.MyLabel;

public class CVControlPanel extends JPanel{
	MyLabel drag,zoombar,zoomblock;
	int zoom=0;
	int ewidth=60,eheight=20;
	Point press=new Point(),pl=new Point();
	CVControlPanel(){
		this.setBackground(new Color(190,190,190));
		this.setLayout(null);
		this.setSize(440, 80);
		
		drag=new MyLabel(new ImageIcon("Files\\Image\\drag.png")).setSizex(50, 10)
				.setLocationx(4, 0);
		drag.addMouseListener(new _dragMouse1());
		drag.addMouseMotionListener(new _dragMouse1());
		this.add(drag);
		zoomblock=new MyLabel(new ImageIcon("Files\\Image\\zoomblock.png"))
				.setSizex(15,32).setLocationx(20,35);
		zoomblock.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent arg0) {
				int dx=(int) (arg0.getX()-press.getX());
				zoomblock.setLocation(dx+zoomblock.getX(), 35);
			}
			public void mouseMoved(MouseEvent arg0) {
			}
			
		});
		zoomblock.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {
				Main.g.mf.setCursor(Cursor.HAND_CURSOR);
			}
			public void mouseExited(MouseEvent e) {
				Main.g.mf.setCursor(Cursor.DEFAULT_CURSOR);
			}
			public void mousePressed(MouseEvent e) {
				press.setLocation(e.getX(), 35);
			}
			public void mouseReleased(MouseEvent e) {
				setZoomFromX(zoomblock.getX());
			}
			
		});
		this.add(zoomblock);
		zoombar=new MyLabel(new ImageIcon("Files\\Image\\zoombar.png"))
				.setSizex(this.getWidth(),this.getHeight()).setLocationx(0, 0);
		zoombar.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				setZoomFromX(arg0.getX());
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		this.add(zoombar);
		

		zoomblock.setLocation(145,35);
		this.zoom=1;
		ewidth=(int) Math.round(60.0*2.63);
		eheight=(int) Math.round(20.0*1.67);
	}
	void setZoomFromX(int x) {
		if(x<=85) {    //0
			setZoom(0);
		}else if(x>85&&x<=205) {
			setZoom(1);
		}else if(x>205&&x<=335) {
			setZoom(2);
		}else {
			setZoom(3);
		}
	}
	void setZoom(int zoom) {
		int tw=ewidth,th=eheight;
		zoomblock.setLocation(zoom*120+25,35);
		this.zoom=zoom;
		if(zoom==0) {
			ewidth=60;
			eheight=20;
		}else if(zoom==1) {
			ewidth=(int) Math.round(60.0*2.63);
			eheight=(int) Math.round(20.0*1.67);
		}else if(zoom==2) {
			ewidth=(int) Math.round(60.0*2.63*1.2);
			eheight=(int) Math.round(20.0*1.67*2.5);
		}else if(zoom==3) {
			ewidth=(int) Math.round(60.0*2.63*1.2*1.97);
			eheight=(int) Math.round(20.0*1.67*2.5*1.28);
		}
		double sw=(double)ewidth/(double)tw,sh=(double)eheight/(double)th;
		Point c=new Point(Main.g.cvp.getWidth()/2,Main.g.cvp.getHeight()/2);
		for(int i=0;i<Main.g.cvp.eles.size();i++) {
			Point l=Main.g.cvp.eles.get(i).location;
			Main.g.cvp.eles.get(i).location.setLocation(c.x+((double)l.x-(double)c.x)*sw
					, c.y+((double)l.y-(double)c.y)*sh);
		}

		Main.g.cvp.repaint();
		Main.g.mf.repaint();
		Main.g.mf.setVisible(true);
		Main.g.etp.save();
		Main.g.tlp.chooseTool(Main.g.tlp.pointer);
		Main.g.tlp.pointer.requestFocus();
	}
}
