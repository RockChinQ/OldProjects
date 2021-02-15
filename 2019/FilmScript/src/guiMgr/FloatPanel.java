package guiMgr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import boot.Main;

public class FloatPanel extends JPanel implements  MouseListener,MouseMotionListener {
	public class border extends JPanel{
		public void paint(Graphics g) {
			g.setColor(this.getParent().getBackground());
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(new Color(70,120,200));
			g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
		}
	}
	
	class close extends JLabel{

		Color bg=Color.GRAY,paint=new Color(0,148,210);
		public close() {
			this.setSize(10, 10);
		}
		public close(Color bg,Color paint) {
			this.setSize(10, 10);
			this.bg=bg;
			this.paint=paint;
		}
		public void paint(Graphics g) {
			g.setColor(bg);
			g.fillRect(0, 0, 20, 10);
			g.setColor(paint);
			g.drawLine(2, 2, 8, 8);
			g.drawLine(8, 2, 2, 8);
			g.drawLine(1, 2, 7, 8);
			g.drawLine(7, 2, 1, 8);
		}
	}
	class windowed extends JPanel{

		Color bg=Color.GRAY,paint=new Color(0,148,210);
		public windowed() {
			this.setSize(10, 10);
		}
		public windowed(Color bg,Color paint) {
			this.setSize(10, 10);
			this.bg=bg;
			this.paint=paint;
		}
		public void paint(Graphics g) {
			g.setColor(bg);
			g.fillRect(0, 0, 20, 10);
			g.setColor(paint);
			g.drawRect(2, 2, 7, 6);
			g.drawRect(2, 3, 7, 1);
			//g.drawRect(1, 1, 7, 7);
		}
	}
	class dragBar extends JLabel{
		Color bg=Color.GRAY,point=new Color(0,148,210);
		public dragBar() {
			this.setSize(20, 10);
		}
		public dragBar(Color bg,Color point) {
			this.setSize(20, 10);
			this.bg=bg;
			this.point=point;
		}
		public void paint(Graphics g) {
			g.setColor(bg);
			g.fillRect(0, 0, 20, 10);
			g.setColor(point);
			for(int i=0;i<4;i++) {
				g.fillRect(i*5+2, 2, 3, 3);
			}
		}
	}
	public class title extends JLabel{
		public String title="Float Panel";
		Color bg=Color.GRAY,point=new Color(150,150,150);
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
			
			g.setFont(new Font(null,Font.PLAIN,12));
			FontMetrics fm = g.getFontMetrics();
			int w=fm.stringWidth(title);
			g.drawString(title, this.getWidth()/2-w/2, 12);
		}
	}
	public dragBar db;
	public close close;
	public windowed wed;
	public title tl;
	public border border=new border();
	
	public boolean dragging=false;
	public boolean wded=false;
	public Point begin=new Point(),beginOnPanel=new Point();
	public FloatPanel(){
		db=new dragBar();
		db.setSize(20, 10);
		db.setLocation(2, 3);
		super.add(db);
		db.addMouseListener(this);
		db.addMouseMotionListener(this);
		
		close=new close();
		close.setSize(10, 10);
		close.setLocation(this.getWidth()-12, 0);
		close.addMouseListener(this);
		super.add(close);
		
		wed=new windowed();
		wed.setSize(10, 10);
		wed.setLocation(this.getWidth()-28, 0);
		wed.addMouseListener(this);
		super.add(wed);
		
		tl=new title();
		tl.setSize(this.getWidth(), 10);
		tl.setLocation(0, 0);
		super.add(tl);
		
		border.setSize(this.getSize());
		border.setLocation(0, 0);
	}
	/**
	 * 背景色
	 * @param bg
	 */
	public void setBackgroundx(Color bg) {
		this.setBackground(bg);
		db.bg=bg;
		close.bg=bg;
		wed.bg=bg;
	}
	/**
	 * 重新计算大小
	 */
	public void resize() {
		this.remove(border);
		close.setLocation(this.getWidth()-12, 1);
		wed.setLocation(this.getWidth()-28, 1);
		tl.setSize(this.getWidth(), 14);
		border.setSize(this.getSize());
		super.add(border);
	}
	/**
	 * 窗口化
	 */
	public void windowed() {
		//this.setVisible(false);
		Main.gui.enwindowPanel(this);
	}
	/**
	 * 设置标题
	 * @param title
	 */
	public void setTitlex(String title) {
		this.tl.title=new String(title);
	}
	public void setSize(int w,int h) {
		super.setSize(w+2, h+2);
	}
	public Component add(Component c) {
		c.setLocation(c.getX()+1, c.getY()+1);
		return super.add(c);
	}
	
	public void mouseClicked(MouseEvent arg0) {
		if(!wded) {
			if(arg0.getSource()==close) {
				this.setVisible(false);
				Main.gui.bgp.wb.repaint();
				
			}else if(arg0.getSource()==wed) {
				this.windowed();
			}
		}
	}
	public void mouseEntered(MouseEvent arg0) {
		if(arg0.getSource()==db)
			Main.gui.mwd.setCursor(Cursor.MOVE_CURSOR);
	}
	public void mouseExited(MouseEvent arg0) {
		if(arg0.getSource()==db)
			Main.gui.mwd.setCursor(Cursor.DEFAULT_CURSOR);
	}
	public void mousePressed(MouseEvent arg0) {
		Point p=arg0.getPoint();
		begin.setLocation(p.getX()+this.getX()
		,p.getY()+this.getY());
		beginOnPanel.setLocation(p);
		//System.out.println(begin+" "+beginOnPanel);
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	public void mouseDragged(MouseEvent arg0) {
		if(!wded) {
			int dx=(int) (arg0.getX()+this.getX()-begin.getX()),
				dy=(int) (arg0.getY()+this.getY()-begin.getY());
			this.setLocation((int)begin.getX()+dx-beginOnPanel.x
					,(int)begin.getY()+dy-beginOnPanel.y);
			
			int x=this.getX(),y=this.getY();
			//System.out.println(x+" "+y+" "+this.getWidth()+" "+this.getHeight()+" ");
			if(x<0)
				x=0;
			if(y<0)
				y=0;
			if(x+this.getWidth()>Main.gui.bgp.getWidth())
				x=Main.gui.bgp.getWidth()-this.getWidth();
			if(y+this.getHeight()+40>Main.gui.bgp.getHeight())
				y=Main.gui.bgp.getHeight()-40-this.getHeight();
			this.setLocation(x, y);
			Main.gui.mwd.repaint();
		}
	}

	public void mouseMoved(MouseEvent arg0) {}
}
