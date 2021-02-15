package Boot;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import Classes.MyButton;
import Classes.MyLabel;
import Classes.MyTextArea;
import Classes.MyTextField;
import OSCAPI.PicUtils;

public class EditorPanel extends JPanel{
	MyLabel bg,title,drag;
	MyLabel nameL,labelL,numL,describeL,imageL,addimg;
	MyTextField name,label,num;
	MyTextArea describe;
	
	Font tf=new Font("",Font.BOLD,17);
	Element e;
	EditorPanel(){
		this.setLayout(null);
		this.setSize(330, 264);
		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {
				Main.g.mf.setCursor(Cursor.DEFAULT_CURSOR);
			}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		this.setBackground(new Color(190,190,190));
		
		title=new MyLabel(new ImageIcon("Files\\Image\\infotitle.png"))
				.setSizex(400, 40).setLocationx(0, 0);
		this.add(title);

		drag=new MyLabel(new ImageIcon("Files\\Image\\drag.png")).setSizex(50, 10)
				.setLocationx(5,this.getHeight()-10);
		drag.addMouseListener(new _dragMouse2());
		drag.addMouseMotionListener(new _dragMouse2());
		this.add(drag);

		nameL=new MyLabel("名称:").setSizex(50, 30).setLocationx(10, 42).setFontx(tf);
		name=new MyTextField().setSizex(250, 30).setLocationx(55, 42);
		this.add(name);
		this.add(nameL);

		labelL=new MyLabel("标签:").setSizex(50, 30).setLocationx(10, 82).setFontx(tf);
		label=new MyTextField().setSizex(120, 30).setLocationx(55, 82);
		this.add(label);
		this.add(labelL);
		numL=new MyLabel("编号:").setSizex(50, 30).setLocationx(187, 82).setFontx(tf);
		num=new MyTextField().setSizex(30, 30).setLocationx(229, 82);
		num.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent arg0) {
			}
			public void keyReleased(KeyEvent arg0) {
			}
			public void keyTyped(KeyEvent arg0) {
				if('0'>arg0.getKeyChar()||arg0.getKeyChar()>'9') {
					arg0.consume();
				}
			}
			
		});
		this.add(num);
		this.add(numL);
		describeL=new MyLabel("描述:").setFontx(tf).setSizex(45, 30).setLocationx(10, 114);
		describe=new MyTextArea().setSizex(250, 100)
				.setLocationx(55, 116).setFontx(new Font("",Font.PLAIN,15));
		this.add(describeL);
		this.add(describe);
		imageL=new MyLabel("缩略图:").setSizex(120, 30).setLocationx(10,217).setFontx(tf);
		this.add(imageL);
		addimg=new MyLabel("管理").setSizex(120, 30).setLocationx(140,217).setFontx(tf);
		addimg.setForeground(new Color(0,128,255));
		/*addimg.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				/*注意：此处会大量消耗内存
				 * 即使JVM会自动清理但语法并不整洁
				JDialog imgm=new JDialog(Main.g.mf,"管理"+e.name+"的示意图");
				imgm.setLayout(null);
				ArrayList<ImageIcon> imgt=e.image;
				ArrayList<MyLabel> labela=new ArrayList<MyLabel>();
				int ww=200,wh=200,th=0;
				int x=10,y=10;
				for(int i=0;i<imgt.size();i++) {
					ImageIcon img=new ImageIcon(imgt.get(i).getImage().getScaledInstance
							((int)(imgt.get(i).getIconWidth()*0.05)
									,(int)(imgt.get(i).getIconHeight()*0.05), Image.SCALE_DEFAULT));
					labela.add(new MyLabel(img).setSizex(img.getIconWidth(), img.getIconHeight())
							.setLocationx(x, y));
					imgm.add(labela.get(labela.size()-1));
					MyButton del=new MyButton("删除-"+i).setSizex(70, 30).setLocationx(x,img.getIconHeight()+y+3);
					imgm.add(del);
					if(del.getX()+del.getWidth()>ww) {
						ww=del.getX()+del.getWidth()+20;
					}
					if(img.getIconHeight()+del.getHeight()>wh) {
						wh=img.getIconHeight()+del.getHeight();
					}
					if(img.getIconHeight()+32>th) {
						th=img.getIconHeight()+32;
					}
					if(i%3==0&&i!=0) {
						y+=th;
						x=10;
						th=0;
					}
				}
				MyLabel add=new MyLabel(new ImageIcon("Files\\Image\\addimg.png")).setSizex(50, 50).setLocationx(x, y);
				add.addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent arg0) {
						JFileChooser jfc=new JFileChooser();
						jfc.setFileFilter(new ImageFileFilter());
						jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
						int state=jfc.showOpenDialog(null);
						if(state==JFileChooser.CANCEL_OPTION)
							return;
						imgt.add(new ImageIcon(jfc.getSelectedFile().getAbsolutePath()));
					}
					public void mouseEntered(MouseEvent arg0) {}
					public void mouseExited(MouseEvent arg0) {}
					public void mousePressed(MouseEvent arg0) {}
					public void mouseReleased(MouseEvent arg0) {}
					
				});
				imgm.setVisible(true);
				imgm.setSize(ww, wh);
				imgm.setLocation(200, 0);
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});*/
		this.add(addimg);
		
		bg=new MyLabel(new ImageIcon("Files\\Image\\editor.png"))
				.setSizex(this.getWidth(),this.getHeight()).setLocationx(0, 0);
		this.add(bg);
		
	}
	void changeTitle(ImageIcon title) {
		this.title.setIcon(title);
	}
	void show(Element e) {
		this.requestFocus();
		this.e=e;
		switch(e.type) {
		case Element.INFO:{
			changeTitle(new ImageIcon("Files\\Image\\infotitle.png"));
			break;
		}
		case Element.VIDEO:{
			changeTitle(new ImageIcon("Files\\Image\\videotitle.png"));
			break;
		}
		case Element.AUDIO:{
			changeTitle(new ImageIcon("Files\\Image\\audiotitle.png"));
			break;
		}
		case Element.TRANSITION:{
			changeTitle(new ImageIcon("Files\\Image\\transtitle.png"));
			break;
		}
		}
		this.name.setText(e.name);
		this.label.setText(e.label);
		this.num.setText(e.num+"");
		this.describe.setText(e.describe);
		this.imageL.setText("缩略图:("+e.image.size()+"张)");
		
		this.setLocation((int)e.location.getX()+20, (int)e.location.getY()+Main.g.ccp.eheight+5);
		this.setVisible(true);
		Main.g.mf.repaint();
	}
	void save() {
		this.setVisible(false);
		if(e!=null) {
			e.name=name.getText();
			e.label=label.getText();
			e.num=Integer.parseInt(num.getText().equals("")?"0":num.getText());
			e.describe=describe.getText();
		}
		e=null;
	}
}
