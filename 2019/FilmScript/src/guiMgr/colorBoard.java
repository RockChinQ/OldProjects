package guiMgr;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import boot.Main;

public class colorBoard extends JPanel{
	public colorBoardTarget cbt;
	public class presetBtn extends JButton{
		public presetBtn() {
			this.setSize(13, 13);
		}
		public void paint(Graphics g) {
			g.setColor(getBackground());
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
	}
	public class colorBar extends JPanel{
		int x=10;
		public colorBar() {
			this.setSize(210, 45);
		}
		public void paint(Graphics g) {
			g.setColor(new Color(200,200,200));
			g.drawString("自定义:"+psc[11].getRed()+","+psc[11].getGreen()+","+psc[11].getBlue(), 0, 9);
			//r255 g0 b0>255
			for(int i=0;i<35;i++) {
				g.setColor(new Color(255,0,(int)(i*7.2857)));
				g.fillRect(i, 15, 1, 20);
			}
			//r255>0 g0 b255
			for(int i=0;i<35;i++) {
				g.setColor(new Color(255-((int)(i*7.2857)),0,255));
				g.fillRect(i+35,15, 1, 20);
			}
			//r0 g0>255 b255
			for(int i=0;i<35;i++) {
				g.setColor(new Color(0,(int)(i*7.2857),255));
				g.fillRect(i+70,15, 1, 20);
			}
			//r0 g255 b255>0
			for(int i=0;i<35;i++) {
				g.setColor(new Color(0,255,255-((int)(i*7.2857))));
				g.fillRect(i+105,15, 1, 20);
			}
			//r0>255 g255 b0
			for(int i=0;i<35;i++) {
				g.setColor(new Color((int)(i*7.2857),255,0));
				g.fillRect(i+140,15, 1, 20);
			}
			//r255 g255>0 b0
			for(int i=0;i<35;i++) {
				g.setColor(new Color(255,255-((int)(i*7.2857)),0));
				g.fillRect(i+175,15, 1, 20);
			}
			g.setColor(Color.white);
			g.drawLine(x, 13, x, 40);
			//g.drawLine(x-5, 41, x, 35);
			//g.drawLine(x+5, 41, x, 35);
			g.drawLine(x-5, 41, x+5, 41);
			g.drawLine(x-4, 40, x+4, 40);
			g.drawLine(x-3, 39, x+3, 39);
			g.drawLine(x-2, 38, x+2, 38);
			g.drawLine(x-1, 37, x+1, 37);
		}
		public Color getColor(int x) {
			this.x=x;
			if(x<36&&x>=0) {
				psc[11]=new Color(255,0,(int)(x*7.2857));
			}else if(x<71&&x>=36) {
				psc[11]=new Color(255-((int)((x-35)*7.2857)),0,255);
			}else if(x<106&&x>=71) {
				psc[11]=new Color(0,(int)((x-70)*7.2857),255);
			}else if(x<141&&x>=106) {
				psc[11]=new Color(0,255,255-((int)((x-105)*7.2857)) );
			}else if(x<176&&x>=141) {
				psc[11]=new Color((int)((x-140)*7.2857) ,255,0);
			}else if(x<211&&x>=176) {
				psc[11]=new Color(255,255-((int)((x-175)*7.2857)),0);
			}else if(x>=211){
				this.x=209;
				psc[11]=new Color(255,0,0);
			}else if(x<0) {
				this.x=0;
				psc[11]=new Color(255,0,0);
			}
			
			preset[11].setBackground(psc[11]);
			preset[11].repaint();
			this.repaint();
			return psc[11];
		}
	}
	
	presetBtn preset[];
	public Color psc[]=new Color[] {new Color(0,0,0),new Color(127,127,127)
			,new Color(136,0,21),new Color(237,28,36),new Color(255,127,39)
			,new Color(255,242,0),new Color(34,177,76),new Color(0,162,232)
			,new Color(63,72,204),new Color(136,73,164),new Color(255,255,255)
			,new Color(255,255,255)};
	public colorBar cb;
	public int uid=-1;
	public colorBoard() {
		this.setLayout(null);
		this.setSize(223, 75);
		
		preset=new presetBtn[12];
		//设置每个色块
		for(int i=0;i<12;i++) {
			preset[i]=new presetBtn();
			preset[i].setBackground(psc[i]);
			preset[i].setLocation(i*18+5, 8);
			preset[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//Main.gui.bgp.lc.lsClk..setBackground(((presetBtn)arg0.getSource()).getBackground());

					cbt.setColor(((presetBtn)arg0.getSource()).getBackground());
//					((LabelCheck.labelEntry)Main.gui.bgp.lc.lsClk.getParent()).pointer.cl=((presetBtn)arg0.getSource()).getBackground();
//					Main.gui.bgp.lc.updateLabel();
					//Main.gui.bgp.cb.setVisible(false);
					//Main.gui.bgp.lc.lsClk=null;
				}
			});
			this.add(preset[i]);
		}
		cb=new colorBar();
		cb.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent arg0) {

				cbt.setColor(cb.getColor(arg0.getX()));
//				((LabelCheck.labelEntry)Main.gui.bgp.lc.lsClk.getParent()).pointer.cl=cb.getColor(arg0.getX());
//				Main.gui.bgp.lc.updateLabel();
				//Main.gui.bgp.cb.setVisible(false);
				//Main.gui.bgp.lc.lsClk=null;
			}
			public void mouseMoved(MouseEvent arg0) {
			}
		});
		cb.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				cbt.setColor(cb.getColor(arg0.getX()));
//				((LabelCheck.labelEntry)Main.gui.bgp.lc.lsClk.getParent()).pointer.cl=cb.getColor(arg0.getX());
//				Main.gui.bgp.lc.updateLabel();
				//Main.gui.bgp.cb.setVisible(false);
				//Main.gui.bgp.lc.lsClk=null;
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		cb.setLocation(7, 30);
		this.add(cb);
		
	}
	public void setVisible(colorBoardTarget cbt) {
		this.cbt=cbt;
		this.setVisible(cbt!=null);
	}
	public  void registerTarget(colorBoardTarget cbt) {
		this.cbt=cbt;
	}
}
