package Boot;

import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Classes.Config;

public class GUI {
	JFrame mf=new JFrame();
	ToolPanel tlp;
	CanvasPanel cvp;
	RectPanel rtp;
	CVControlPanel ccp;
	EditorPanel etp;
	ThumbPanel tbp;
	OperatePanel orp;
	
	Config c=new Config("Files\\Setting\\frame.ini");
	GUI(){
		mf.setLocation(200,200);
		mf.setSize(1100,700);
		mf.setLayout(null);
		mf.setTitle("脚本编辑 "+Main.ver);
		mf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mf.addComponentListener(new ComponentListener() {
			public void componentHidden(ComponentEvent arg0) {
			}
			public void componentMoved(ComponentEvent arg0) {
			}
			public void componentResized(ComponentEvent arg0) {
				cvp.setSize(mf.getSize());
			}
			public void componentShown(ComponentEvent arg0) {
			}
			
		});
		mf.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {}
			public void windowClosed(WindowEvent arg0) {}
			public void windowClosing(WindowEvent arg0) {
				if(Main.change) {
					int chosen=javax.swing.JOptionPane.showConfirmDialog(null
							, "你的文件已改动，是否储存？", "请确认", JOptionPane.YES_NO_CANCEL_OPTION);
					//-1右上角 0是 1否 2取消
					if(chosen==-1||chosen==2)
						return;
					if(chosen==0) {
						String fp=Main.fileName;
						if(Main.fileName.equals("")) {
							JFileChooser jfc=new JFileChooser();
							jfc.setFileFilter(new ScriptFileFilter());
							int result=jfc.showSaveDialog(null);
							if(result==JFileChooser.APPROVE_OPTION){
								fp=new String(jfc.getSelectedFile().getAbsolutePath());
								if(!fp.endsWith(".vs")) {
									fp=new String(fp+".vs");
								}
							}else{
				            	return;
				            }
						}
						Main.g.orp.saveFileTo(fp);
					}
				}
				//write config
				c.write("extendedState",mf.getExtendedState()+"");
				c.write("x",mf.getX()+"");
				c.write("y", mf.getY()+"");
				c.write("width", mf.getWidth()+"");
				c.write("height", mf.getHeight()+"");
				
				mf.dispose();
			}
			public void windowDeactivated(WindowEvent arg0) {}
			public void windowDeiconified(WindowEvent arg0) {}
			public void windowIconified(WindowEvent arg0) {}
			public void windowOpened(WindowEvent arg0) {}
		});
		
		
		etp=new EditorPanel();
		etp.setLocation(70, 100);
		etp.setVisible(false);
		mf.add(etp);
		
		rtp=new RectPanel();
		rtp.setSize(100,100);
		rtp.setLocation(50, 50);
		//mf.add(rtp);

		tlp=new ToolPanel();
		tlp.setLocation(10, 250);
		mf.add(tlp);
		
		orp=new OperatePanel();
		orp.setLocation(820, 10);
		mf.add(orp);
		
		ccp=new CVControlPanel();
		mf.add(ccp);
		
		tbp=new ThumbPanel();
		mf.add(tbp);
		
		cvp=new CanvasPanel();
		cvp.setLocation(0, 0);
		cvp.setSize(mf.getSize());
		mf.add(cvp);
		
		init();
	}
	void init() {
		cvp.eles.add(new Element(Element.INFO,0
				,"项目信息","",new Point(150,200)));
		tbp.setLocation(10, 10);
		cvp.repaint();
		ccp.setLocation(360,10);
		mf.setVisible(true);
		//read config
		if(c.read("extendedState").equals("#undefined#")) {
			;
		}else {
			this.mf.setSize(Integer.parseInt(c.read("width")),Integer.parseInt(c.read("height")));
			this.mf.setLocation(Integer.parseInt(c.read("x")),Integer.parseInt(c.read("y")));
			this.mf.setExtendedState(Integer.parseInt(c.read("extendedState")));
		}
	}
}
