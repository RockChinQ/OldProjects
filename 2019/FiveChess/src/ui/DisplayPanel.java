package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import data.DataBase;
import gameMgr.GameMgr;

public class DisplayPanel extends JPanel implements MouseListener{
	private short[][] data=new short[13][13];
	public DisplayPanel() {
		for(int i=0;i<13;i++) {
			for(int j=0;j<13;j++) {
				data[j][i]=-1;
			}
		}
		this.setSize(13*25+3, 13*25+3);
		this.setLocation(10, 10);
		this.addMouseListener(this);
	}
	public void paint(Graphics g) {
		g.setColor(new Color(100,100,100));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(new Color(200,200,200));
		
		for(int i=0;i<=13;i++) {
			g.drawLine(0, i*25+1,13*25, i*25+1);
			g.drawLine(i*25+1,0, i*25+1, 13*25);
		}
		for(int i=0;i<13;i++) {
			for(int j=0;j<13;j++) {
				if(data[j][i]==-1)
					continue;
				if(data[j][i]==1) {
					g.setColor(new Color(255,255,255));
				}else if(data[j][i]==0) {
					g.setColor(new Color(0,0,0));
				}
				g.fillOval(i*25+3, j*25+3, 19, 19);
			}
		}
		//зјБъ
		g.setColor(Color.white);
		for(int i=0;i<13;i++) {
			g.drawString(i+"",5, i*25+13);
			g.drawString(i+"", i*25+5, 13);
		}
	}
	public void updatePnlData(short[][] pnl) {
		this.data=pnl;
		System.out.println("updateData");
		this.repaint();
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(DataBase.playing) {
			int x=arg0.getX()/25,y=arg0.getY()/25;
			GameMgr.put(x, y);
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
