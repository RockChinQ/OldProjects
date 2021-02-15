package Pickaxe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorPanel extends JPanel{
	Color bg=new Color(25,25,25);
	Font bFont=new Font("",Font.PLAIN,15);
	
	MyLabel colorPoint[][],redPoint[][],chosenColor,chosenRed,fgColorLabel,bgColorLabel,tip,colorShowWindow;
	int red=128;
	Color fgColor=new Color(5,204,226),bgColor=new Color(5,204,226);
	//Color presetColor[]= {new Color(0,0,0),new Color(255,255,255),new Color(127,127,127),new Color(136,0,21)};
	int settingColor=0;  //0:fg  1:bg
	ColorPanel(){
		this.setSize(280,270);
		this.setLayout(null);
		this.setBackground(bg);
		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent arg0) {
			}
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				int x=arg0.getX(),y=arg0.getY();
				if(x>=10&&y>=20&&x<=2*85+10&&y<=2*85+20) {
					
				}else {
					colorShowWindow.setVisible(false);
				}
			}
			
		});
		
		colorShowWindow=new MyLabel("■");
		colorShowWindow.setFontx(new Font("",Font.PLAIN,58)).setSizex(60, 60)
		.setLocationx(0, 0).setForeground(new Color(5,204,226));
		colorShowWindow.setVisible(false);
		colorShowWindow.setBackground(Color.white);
		colorShowWindow.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.WHITE));
		this.add(colorShowWindow);

		chosenColor=new MyLabel(new ImageIcon(Boot.filePath+"chosenColor.png"));
		chosenColor.setSizex(10,10).setLocationx(10, 20);
		this.add(chosenColor);
		chosenRed=new MyLabel(new ImageIcon(Boot.filePath+"chosenColor.png"));
		chosenRed.setSizex(10,10).setLocationx(193,red/3*2+20-4);
		this.add(chosenRed);
		
		
		colorPoint=new MyLabel[85][85];
		for(int i=0;i<85;i++) {
			for(int j=0;j<85;j++) {
				colorPoint[j][i]=new MyLabel("█");
				colorPoint[j][i].setFontx(new Font("",Font.PLAIN,2)).setSizex(2, 2)
					.setLocationx(i*2+10, j*2+20).setForeground(new Color(red,j*3,i*3));
				colorPoint[j][i].setBackground(new Color(red,j*3,i*3));
				colorPoint[j][i].addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent arg0) {
					}
					public void mouseEntered(MouseEvent arg0) {
					}
					public void mouseExited(MouseEvent arg0) {
					}
					public void mousePressed(MouseEvent arg0) {
						setColor( ((MyLabel)arg0.getSource()).getForeground() );
					}
					public void mouseReleased(MouseEvent arg0) {
					}
					
				});
				colorPoint[j][i].addMouseMotionListener(new MouseMotionListener() {

					@Override
					public void mouseDragged(MouseEvent arg0) {
					}
					public void mouseMoved(MouseEvent arg0) {
						colorShowWindow.setVisible(true);
						MyLabel ml=(MyLabel) arg0.getSource();
						colorShowWindow.setLocation(ml.getX()+arg0.getX(),ml.getY()+arg0.getY());
						colorShowWindow.setForeground(ml.getForeground());
					}
					
				});
				this.add(colorPoint[j][i]);
			}
		}
		redPoint=new MyLabel[85][10];
		for(int i=0;i<85;i++) {
			for(int j=0;j<10;j++) {
				redPoint[i][j]=new MyLabel("█");
				redPoint[i][j].setFontx(new Font("",Font.PLAIN,2)).setSizex(2, 2)
				.setLocationx(j*2+190, i*2+20).setForeground(new Color(i*3,0,0));
				redPoint[i][j].addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent e) {
					}
					public void mouseEntered(MouseEvent e) {
					}
					public void mouseExited(MouseEvent e) {
					}
					public void mousePressed(MouseEvent e) {
						setColorBoard(((MyLabel)e.getSource()).getForeground().getRed());
					}
					public void mouseReleased(MouseEvent e) {
					}
					
				});
				this.add(redPoint[i][j]);
			}
		}

		fgColorLabel=new MyLabel("■");
		fgColorLabel.setFontx(new Font("",Font.PLAIN,48)).setSizex(52, 52)
		.setLocationx(10, 198).setForeground(fgColor);
		fgColorLabel.setBackground(Color.white);
		fgColorLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		fgColorLabel.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				changeSettingColor(0);
				if(arg0.getClickCount()==2) {
					Color c=JColorChooser.showDialog(null, "选择前景色", fgColor);
					if(c==null)
						return;
					setColor(c);
				}
			}
			public void mouseEntered(MouseEvent arg0) {
			}
			public void mouseExited(MouseEvent arg0) {
			}
			public void mousePressed(MouseEvent arg0) {
			}
			public void mouseReleased(MouseEvent arg0) {
			}
			
		});
		this.add(fgColorLabel);
		bgColorLabel=new MyLabel("■");
		bgColorLabel.setFontx(new Font("",Font.PLAIN,38)).setSizex(42, 42)
		.setLocationx(65, 200).setForeground(bgColor);
		bgColorLabel.setBackground(Color.white);
		bgColorLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		bgColorLabel.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				changeSettingColor(1);
				if(arg0.getClickCount()==2) {
					Color c=JColorChooser.showDialog(null, "选择背景色", bgColor);
					if(c==null)
						return;
					setColor(c);
				}
			}
			public void mouseEntered(MouseEvent arg0) {
			}
			public void mouseExited(MouseEvent arg0) {
			}
			public void mousePressed(MouseEvent arg0) {
			}
			public void mouseReleased(MouseEvent arg0) {
			}
			
		});
		this.add(bgColorLabel);

		tip=new MyLabel("前景色：");
		tip.setFontx(new Font("",Font.PLAIN,13)).setSizex(350, 30)
		.setLocationx(110, 198).setForeground(new Color(5,204,226));
		tip.setBackground(Color.white);
		this.add(tip);
		this.requestFocus();
		
		
		
		setColor(fgColor);
		changeSettingColor(0);
	}
	void setColorBoard(int red) {
		this.chosenColor.setLocation(0, 15);
		Color nowColor=(settingColor==0?fgColor:bgColor);
		if(nowColor.getRed()==red)
			this.chosenColor.setLocation(nowColor.getBlue()/3*2+10-4,nowColor.getGreen()/3*2+20-4);
		this.red=red;
		this.chosenRed.setLocation(194, red/3*2-4+20);
		for(int i=0;i<85;i++) {
			for(int j=0;j<85;j++) {
				colorPoint[j][i].setForeground(new Color(red,j*3,i*3));
			}
		}
	}
	void setChosenColor(Color c) {
		setColorBoard(c.getRed());
		this.chosenColor.setLocation(c.getBlue()/3*2+10-4,c.getGreen()/3*2+20-4);
	}
	void setColor(Color c) {
		if(settingColor==0) {
			fgColor=c;
			this.fgColorLabel.setForeground(fgColor);
			this.tip.setText("前景色(R,G,B)："+fgColor.getRed()+","+fgColor.getGreen()+","+fgColor.getBlue());
		}else {
			bgColor=c;
			this.bgColorLabel.setForeground(bgColor);
			this.tip.setText("背景色(R,G,B)："+bgColor.getRed()+","+bgColor.getGreen()+","+bgColor.getBlue());
		}
		setChosenColor(c);
	}
	void changeSettingColor(int s) {
		if(s==1) {
			this.settingColor=s;
			fgColorLabel.setBackground(Color.white);
			bgColorLabel.setBackground(Color.ORANGE);
			this.tip.setText("背景色(R,G,B)："+bgColor.getRed()+","+bgColor.getGreen()+","+bgColor.getBlue());
			setChosenColor(bgColor);
		}else if(s==0){
			this.settingColor=s;
			fgColorLabel.setBackground(Color.ORANGE);
			bgColorLabel.setBackground(Color.WHITE);
			this.tip.setText("前景色(R,G,B)："+fgColor.getRed()+","+fgColor.getGreen()+","+fgColor.getBlue());
			setChosenColor(fgColor);
		}
	}
}
