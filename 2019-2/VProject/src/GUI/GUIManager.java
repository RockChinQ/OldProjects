package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Classes.MyFrame;
import Main.Const;

public class GUIManager {
	MyFrame mainWd;
	JPanel bg=new JPanel();
	
	ToolPanel tlp;
	CanvasPanel cvp;
	
	Point pressPoint;
	public GUIManager(){
		mainWd=new MyFrame(1200,700,0,0,false,"ÏîÄ¿±à¼­",true,false,true);
		mainWd.setLayout(null);
		bg.setLayout(null);
		bg.setBackground(new Color(30,30,30));
		bg.setSize(mainWd.getSize());
		bg.setLocation(0, 0);
		mainWd.add(bg);
		
		tlp=new ToolPanel();
		tlp.setLocation(2, mainWd.getHeight()/2-tlp.getHeight()/2);
		bg.add(tlp);
		
		cvp=new CanvasPanel();
		cvp.setLocation(0, 0);
		cvp.setSize(bg.getSize());
		cvp.setBackground(bg.getBackground());
		bg.add(cvp);
		
		mainWd.setVisible(true);
		cvp.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
			}
			public void mouseEntered(MouseEvent arg0) {
				mainWd.setCursor(Toolkit.getDefaultToolkit()
						.createCustomCursor(new ImageIcon(Const.ipath+"add.png")
								.getImage(),new Point(20
										,20), "stick"));
			}
			public void mouseExited(MouseEvent arg0) {
				mainWd.setCursor(Cursor.DEFAULT_CURSOR);
			}
			public void mousePressed(MouseEvent arg0) {
				pressPoint=arg0.getPoint();
			}
			public void mouseReleased(MouseEvent arg0) {
				tlp.repaint();
				tlp.setVisible(true);
			}
			
		});
		cvp.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent arg0) {
				if(tlp.toolSelected.equals(tlp.pointer)) {
					cvp.repaint();
					cvp.getGraphics().setColor(new Color(5,204,226));
					cvp.getGraphics().fillRect(pressPoint.x,pressPoint.y,
							arg0.getX()-pressPoint.x,arg0.getY()-pressPoint.y);
					tlp.repaint();
				}else if(tlp.toolSelected.equals(tlp.addVideo)) {
					
				}
			}
			public void mouseMoved(MouseEvent arg0) {
			}
			
		});
	}
}
