package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Classes.MyButton;
import Classes.MyLabel;
import Main.Const;

public class ToolPanel extends JPanel{
	MyLabel dragger;
	MyButton pointer,link,addVideo,addAudio,addTrans;
	
	Color cbg=new Color(0,0,0);
	Point dragPoint=new Point(),dpodr=new Point();
	MyButton toolSelected;
	ToolPanel(){
		this.setLayout(null);
		this.setSize(40, 300);
		this.setBackground(Const.bg);
		
		dragger=new MyLabel(new ImageIcon(Const.ipath+"dragger.png"))
				.setSizex(40,7).setLocationx(0, 0);
		dragger.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
			}
			public void mouseEntered(MouseEvent arg0) {
				Main.Main.gui.mainWd.setCursor(Cursor.MOVE_CURSOR);
			}
			public void mouseExited(MouseEvent arg0) {
				Main.Main.gui.mainWd.setCursor(Cursor.DEFAULT_CURSOR);
			}
			public void mousePressed(MouseEvent arg0) {
				Point p=arg0.getPoint();
				dragPoint.setLocation(p.getX()+Main.Main.gui.tlp.getX()
					,p.getY()+Main.Main.gui.tlp.getY());
				dpodr.setLocation(arg0.getPoint());
			}
			public void mouseReleased(MouseEvent arg0) {
			}
			
		});
		dragger.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent arg0) {
				int dx=(int) (arg0.getX()+Main.Main.gui.tlp.getX()-dragPoint.getX()),
					dy=(int) (arg0.getY()+Main.Main.gui.tlp.getY()-dragPoint.getY());
				Main.Main.gui.tlp.setLocation((int)dragPoint.getX()+dx-dpodr.x
						,(int)dragPoint.getY()+dy-dpodr.y);
			}
			public void mouseMoved(MouseEvent arg0) {
			}
		});
		this.add(dragger);
		
		pointer=new MyButton(new ImageIcon(Const.ipath+"pointer.png"))
				.setBackgroundx(new Color(150,150,150)).setSizex(32, 32).setLocationx(4, 7);
		pointer.addActionListener(new _toolAction());
		this.add(pointer);
		link=new MyButton(new ImageIcon(Const.ipath+"link.png"))
				.setBackgroundx(cbg).setSizex(32, 32).setLocationx(4, 40);
		link.addActionListener(new _toolAction());
		this.add(link);
		addVideo=new MyButton(new ImageIcon(Const.ipath+"addvideo.png"))
				.setBackgroundx(cbg).setSizex(32, 32).setLocationx(4, 73);
		addVideo.addActionListener(new _toolAction());
		this.add(addVideo);
		addAudio=new MyButton(new ImageIcon(Const.ipath+"addaudio.png"))
				.setBackgroundx(cbg).setSizex(32, 32).setLocationx(4, 106);
		addAudio.addActionListener(new _toolAction());
		this.add(addAudio);
		addTrans=new MyButton(new ImageIcon(Const.ipath+"addtrans.png"))
				.setBackgroundx(cbg).setSizex(32, 32).setLocationx(4,139);
		addTrans.addActionListener(new _toolAction());
		this.add(addTrans);
		this.toolSelected=this.pointer;
	}
	void setAllUnselected() {
		pointer.setBackground(cbg);
		link.setBackground(cbg);
		addVideo.setBackground(cbg);
		addAudio.setBackground(cbg);
		addTrans.setBackground(cbg);
	}
}
