package Pickaxe;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class GUIManager {
	JFrame mainWd=new JFrame();
	
	ToolPanel toolp;
	CommandPanel cmdp;
	LayerManagerPanel lmp;
	SelectFilePanel sfp;
	NewFilePanel nfp;
	ColorPanel clp;
	MenuPanel mp;
	PropertyPanel pp;
	MaterialPanel mtp;
	CanvasPanel cvp;
	
	//菜单
	JMenuBar all;
	JMenu file,edit,setting;
	JMenuItem fNew,fOpen,fSave,fSaveas,fClose,fExit;
	
	Color titleColor=new Color(5,204,226);
	JPanel bgPanel=new JPanel();
	
	long time=0;
	
	GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
	Rectangle rec=ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
	int xl=(int)rec.getWidth();
	int yl=(int)rec.getHeight();
	//获取屏幕的边界
	Insets screenInsets=Toolkit.getDefaultToolkit().getScreenInsets(new JFrame().getGraphicsConfiguration());
	//获取底部任务栏高度
	int taskBarHeight=screenInsets.bottom;
	GUIManager(){
		printTime("Main");
		//初始化窗体
		//mainWd.setBackground(new Color(80,80,80));
		mainWd.setSize(xl,yl-taskBarHeight);
		mainWd.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainWd.setLocation((xl-mainWd.getWidth())/2,(yl-mainWd.getHeight())/2-15);
		mainWd.setLayout(null);
		mainWd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWd.add(bgPanel);
		mainWd.setAlwaysOnTop(true);
		mainWd.setTitle("Pickaxe Paint-Welcome");
		//顶部菜单
		printTime("Menu");

		all=new JMenuBar();
		all.setSize(500,40);
		all.setLocation(0,0);
		this.mainWd.setJMenuBar(all);

		file=new JMenu();
		file.setText("文件");
		all.add(file);
		
		fNew=new JMenuItem("新建");
		fOpen=new JMenuItem("打开");
		fSave=new JMenuItem("保存");
		fSaveas=new JMenuItem("另存为");
		fClose=new JMenuItem("关闭文件");
		fExit=new JMenuItem("退出");
		
		file.add(fNew);
		file.add(fOpen);
		file.addSeparator();
		file.add(fSave);
		file.add(fSaveas);
		file.addSeparator();
		file.add(fClose);
		file.add(fExit);

		edit=new JMenu();
		edit.setText("编辑");
		all.add(edit);
		
		setting=new JMenu();
		setting.setText("设置");
		all.add(setting);
		//背景面板初始化
		printTime("Back");
		bgPanel.setLocation(0,0);
		bgPanel.setSize(xl,yl);
		bgPanel.setLayout(null);
		bgPanel.setBackground(new Color(40,40,40));  //rgb
		//工具栏
		printTime("Tool");
		Boot.ww.message.setText("正在加载工具栏...");
		toolp=new ToolPanel();
		toolp.setLocation(0, 30);
		toolp.setBorder(getBorder("工具"));
		bgPanel.add(toolp);
		Boot.ww.message.setText("工具栏加载完毕.");
		//图层管理器
		printTime("Layer");
		Boot.ww.message.setText("正在加载图层管理器...");
		lmp=new LayerManagerPanel();
		lmp.setLocation(mainWd.getWidth()-lmp.getWidth()-18,30);
		lmp.setBorder(getBorder("图层"));
		bgPanel.add(lmp);
		Boot.ww.message.setText("图层管理器加载完毕.");
		//命令框
		printTime("Command");
		Boot.ww.message.setText("正在加载命令框...");
		cmdp=new CommandPanel();
		cmdp.setLocation(toolp.getX()+toolp.getWidth()+5, this.mainWd.getHeight()-190);
		cmdp.setBorder(getBorder("命令"));
		cmdp.setSize((mainWd.getWidth()-toolp.getWidth()-lmp.getWidth())/2-20,140);
		cmdp.cmd.setSize(cmdp.getWidth()-10,cmdp.getHeight()-30);
		bgPanel.add(cmdp);
		Boot.ww.message.setText("命令框加载完毕.");	
		//属性框
		printTime("Property");
		Boot.ww.message.setText("正在加载属性框...");
		pp=new PropertyPanel();
		pp.setLocation(toolp.getWidth()+(lmp.getX()-toolp.getWidth())/2, this.mainWd.getHeight()-190);
		pp.setBorder(getBorder("属性"));
		pp.setSize((mainWd.getWidth()-toolp.getWidth()-lmp.getWidth())/2-20,140);
		bgPanel.add(pp);
		Boot.ww.message.setText("属性框加载完毕.");
		//颜色板
		printTime("Color");
		Boot.ww.message.setText("正在加载颜色板...");
		clp=new ColorPanel();
		clp.setLocation(mainWd.getWidth()-clp.getWidth()-18,lmp.getWidth()+lmp.getY()+5);
		clp.setBorder(getBorder("颜色板"));
		clp.setVisible(false);
		bgPanel.add(clp);
		Boot.ww.message.setText("颜色板加载完毕.");
		//画布
		printTime("Canvas");
		Boot.ww.message.setText("正在加载画布...");
		cvp=new CanvasPanel((mainWd.getWidth()-toolp.getWidth()-lmp.getWidth())/2-20,pp.getY()-(toolp.getY()));
		cvp.setLocation(toolp.getX()+toolp.getWidth()+5,toolp.getY());
		cvp.setBorder(getBorder("画布"));
		bgPanel.add(cvp);
		Boot.ww.message.setText("画布加载完毕.");
		//素材板
		printTime("Material");
		Boot.ww.message.setText("正在加载素材板...");
		mtp=new MaterialPanel((mainWd.getWidth()-toolp.getWidth()-lmp.getWidth())/2-20,pp.getY()-(toolp.getY()));
		mtp.setLocation(toolp.getWidth()+(lmp.getX()-toolp.getWidth())/2,toolp.getY());
		mtp.setBorder(getBorder("素材板"));
		bgPanel.add(mtp);
		Boot.ww.message.setText("素材板加载完毕.");
		/*顶部菜单
		printTime();
		Boot.ww.message.setText("正在加载菜单...");
		mp=new MenuPanel();
		mp.setLocation(0,0);
		//clp.setBorder(getBorder("颜色板"));
		bgPanel.add(mp);
		this.mainWd.setJMenuBar(this.mp.all);
		Boot.ww.message.setText("菜单加载完毕.");
		*/
		//选择文件面板
		printTime("SelectFile");
		Boot.ww.message.setText("正在加载选择文件面板...");
		sfp=new SelectFilePanel();
		sfp.setLocation(mainWd.getWidth()/2-sfp.getWidth()/2,mainWd.getHeight()/2-sfp.getHeight()/2);
		sfp.setBorder(getBorder("选择文件"));
		bgPanel.add(sfp);
		Boot.ww.message.setText("加载完成.");
		//新建文件面板
		printTime("NewFile");
		Boot.ww.message.setText("正在加载新建文件面板...");
		nfp=new NewFilePanel();
		nfp.setLocation(mainWd.getWidth()/2-nfp.getWidth()/2,mainWd.getHeight()/2-nfp.getHeight()/2);
		nfp.setBorder(getBorder("新建文件"));
		bgPanel.add(nfp);
		nfp.setVisible(false);
		Boot.ww.message.setText("新建文件面板加载完毕.");
		
		
		Boot.ww.message.setText("显示主窗体");
		try {
			Thread.sleep(0);
		}catch(Exception e) {
			e.printStackTrace();
		}
		showAllPanel(false);
		Boot.ww.welw.dispose();
		mainWd.setVisible(true);
		mainWd.setAlwaysOnTop(false);
		System.out.println("Time:"+(new Date().getTime()-Boot.bootTime));
		//addAllPanel();
	}
	Border getBorder(String title) {
		Border border=BorderFactory.createTitledBorder(title);
		((TitledBorder) border).setTitleColor(titleColor);
		((TitledBorder) border).setTitleFont(new Font("",Font.BOLD,15));
		return border;
	}
	void showAllPanel(boolean visible) {
		//添加所有面板
		toolp.setVisible(visible);
		cmdp.setVisible(visible);
		lmp.setVisible(visible);
		clp.setVisible(visible);
		pp.setVisible(visible);
		all.setVisible(visible);
		cvp.setVisible(visible);
		mtp.setVisible(visible);
	}
	void printTime(String name) {
		long l=new Date().getTime();
		System.out.println(name+":"+(l-time));
		time=l;
	}
}
