package guiMgr.script;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import boot.Main;
import guiMgr.script.SceneTable.scene.seat;

public class thumbCanvas extends JPanel implements MouseListener, MouseMotionListener{
	public static final int PENCIL=0,ERASER=1,CAMERA=2;
	public static final Color bg=new Color(40,40,40),seatcl=new Color(255,128,0);
	public int tool=0;
	public Color cl=new Color(0,200,255);
	public Color pix[][]=new Color[75][115];
	public Point lsPoint=new Point(-1,-1);
	public thumbCanvas(int w,int h) {
		this.setSize(w, h);
		//this.reset(w, h);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	public void reset(int w,int h) {
		Main.gui.sbp.sa.scnow.thumb=new Color[h/2][w/2];
		for(int i=0;i<h/2;i++) {
			for(int j=0;j<w/2;j++) {
				Main.gui.sbp.sa.scnow.thumb[i][j]=new Color(bg.getRGB());
				pix[i][j]=Main.gui.sbp.sa.scnow.thumb[i][j];
			}
		}
		this.repaint();
	}
	public void paint(Graphics g) {
				//System.out.println(this.getWidth()/2);
		for(int y=0;y<this.getHeight()/2;y++) {
			for(int x=0;x<this.getWidth()/2;x++) {
				g.setColor(pix[y][x]);
				g.fillRect(2*x, 2*y, 2, 2);
			}
		}
		if(Main.gui.sbp.sa.scnow!=null) {
			for(seat s:Main.gui.sbp.sa.scnow.seats) {
				g.setColor(s.cl);
				g.fillRect(s.lo.x-3, s.lo.y-2, 11, 9);
				g.drawLine(s.lo.x+8,s.lo.y+2,s.lo.x+14,s.lo.y-2);
				g.drawLine(s.lo.x+8,s.lo.y+2,s.lo.x+14,s.lo.y+6);
				g.drawLine(s.lo.x+14,s.lo.y-2,s.lo.x+14,s.lo.y+6);
				g.drawLine(s.lo.x+13,s.lo.y-1,s.lo.x+13,s.lo.y+5);
				g.drawLine(s.lo.x+12,s.lo.y,s.lo.x+12,s.lo.y+4);
				g.drawLine(s.lo.x+11,s.lo.y+1,s.lo.x+11,s.lo.y+3);
			}
		}
	}
	public void mouseDragged(MouseEvent arg0) {
		if(tool==PENCIL) {
			try {
				pix[arg0.getY()/2][arg0.getX()/2]=cl;
				//System.out.println("pencil");
				//与上次的拖拽经过点连接
				if(lsPoint.x!=-1) {
					//System.out.println("now:"+arg0.getX()/2+","+arg0.getY()/2+" ls:"+lsPoint);
					line(arg0.getX()/2,arg0.getY()/2,lsPoint.x,lsPoint.y);
				}
				this.lsPoint.setLocation(arg0.getX()/2,arg0.getY()/2);
			}catch(Exception e) {}
		}else if(tool==ERASER) {
			eraser(arg0.getX(),arg0.getY());
		}
		this.repaint();
	}
	public void mouseMoved(MouseEvent arg0) {
	}
	public void mouseClicked(MouseEvent arg0) {
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
		//System.out.println(tool);
		Main.gui.sbp.cb.setVisible(null);
		//System.out.println(arg0.getX()+" "+arg0.getY());
		lsPoint.setLocation(-1,-1);
		if(tool==PENCIL) {
			try {
				pix[arg0.getY()/2][arg0.getX()/2]=cl;
				this.lsPoint.setLocation(arg0.getX()/2,arg0.getY()/2);
			}catch(Exception e) {}
		}else if(tool==ERASER) {
			eraser(arg0.getX(),arg0.getY());
		}else if(tool==CAMERA) {
			int seuid=getCameraSeat(arg0.getX(),arg0.getY());
			if(seuid==-1) {
				Main.gui.sbp.sa.scnow.seats.add(new seat(arg0.getPoint(),"Cam"+(Main.gui.sbp.sa.scnow.seatuidindex+1),Main.gui.sbp.sa.scnow));
			}else {
				Main.gui.sbp.sa.scnow.removeSeat(seuid);
			}
			Main.gui.sbp.sh.updateInfo();
			//thu.repaint();
		}
	}
	public void mouseReleased(MouseEvent arg0) {
		this.repaint();
	}
	public void eraser(int x,int y) {
		for(int i=y/2-1;i<=y/2+2;i++) {
			for(int j=x/2-1;j<x/2+2;j++) {
				try {
					pix[i][j]=bg;
				}catch(Exception e) {
					//e.printStackTrace();
				}
			}
		}
	}
	public void line(int x0,int y0,int x1,int y1) {
		//System.out.println("line");
		int dx=x1-x0,dyssb=y1-y0;
		//System.out.println("dx:"+dx+" dy:"+dyssb);
		if(Math.abs(dx)>Math.abs(dyssb)) {//长大于高
			//System.out.println("0");
			int unit=dx/Math.abs(dx);
			double step=(double)dyssb/(double)dx;
			for(int x=x0;Math.abs(x-x1)>0;x+=unit) {
				pix[(int) (y0+step*(x-x0))][x]=cl;
			}
		}else if(Math.abs(dyssb)>=Math.abs(dx)) {
			//System.out.println("1");
			int unit=dyssb/Math.abs(dyssb);
			double step=(double)dx/(double)dyssb;
			//System.out.println("step:"+step);
			for(int y=y0;Math.abs(y-y1)>0;y+=unit) {
				pix[y][(int) (x0+step*(y-y0))]=cl;
			}
		}
		//System.out.println("============");
	}
	public int getCameraSeat(int x,int y) {
		for(seat se:Main.gui.sbp.sa.scnow.seats) {
			int x0=se.lo.x,y0=se.lo.y;
			if(x>=x0-2&&x<=x0+15&&y>=y0-2&&y<=y0+8) {
				return se.uid;
			}
		}
		return -1;
	}
	
}
