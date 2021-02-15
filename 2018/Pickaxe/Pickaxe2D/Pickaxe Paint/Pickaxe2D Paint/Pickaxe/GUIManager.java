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
	
	//�˵�
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
	//��ȡ��Ļ�ı߽�
	Insets screenInsets=Toolkit.getDefaultToolkit().getScreenInsets(new JFrame().getGraphicsConfiguration());
	//��ȡ�ײ��������߶�
	int taskBarHeight=screenInsets.bottom;
	GUIManager(){
		printTime("Main");
		//��ʼ������
		//mainWd.setBackground(new Color(80,80,80));
		mainWd.setSize(xl,yl-taskBarHeight);
		mainWd.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainWd.setLocation((xl-mainWd.getWidth())/2,(yl-mainWd.getHeight())/2-15);
		mainWd.setLayout(null);
		mainWd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWd.add(bgPanel);
		mainWd.setAlwaysOnTop(true);
		mainWd.setTitle("Pickaxe Paint-Welcome");
		//�����˵�
		printTime("Menu");

		all=new JMenuBar();
		all.setSize(500,40);
		all.setLocation(0,0);
		this.mainWd.setJMenuBar(all);

		file=new JMenu();
		file.setText("�ļ�");
		all.add(file);
		
		fNew=new JMenuItem("�½�");
		fOpen=new JMenuItem("��");
		fSave=new JMenuItem("����");
		fSaveas=new JMenuItem("���Ϊ");
		fClose=new JMenuItem("�ر��ļ�");
		fExit=new JMenuItem("�˳�");
		
		file.add(fNew);
		file.add(fOpen);
		file.addSeparator();
		file.add(fSave);
		file.add(fSaveas);
		file.addSeparator();
		file.add(fClose);
		file.add(fExit);

		edit=new JMenu();
		edit.setText("�༭");
		all.add(edit);
		
		setting=new JMenu();
		setting.setText("����");
		all.add(setting);
		//��������ʼ��
		printTime("Back");
		bgPanel.setLocation(0,0);
		bgPanel.setSize(xl,yl);
		bgPanel.setLayout(null);
		bgPanel.setBackground(new Color(40,40,40));  //rgb
		//������
		printTime("Tool");
		Boot.ww.message.setText("���ڼ��ع�����...");
		toolp=new ToolPanel();
		toolp.setLocation(0, 30);
		toolp.setBorder(getBorder("����"));
		bgPanel.add(toolp);
		Boot.ww.message.setText("�������������.");
		//ͼ�������
		printTime("Layer");
		Boot.ww.message.setText("���ڼ���ͼ�������...");
		lmp=new LayerManagerPanel();
		lmp.setLocation(mainWd.getWidth()-lmp.getWidth()-18,30);
		lmp.setBorder(getBorder("ͼ��"));
		bgPanel.add(lmp);
		Boot.ww.message.setText("ͼ��������������.");
		//�����
		printTime("Command");
		Boot.ww.message.setText("���ڼ��������...");
		cmdp=new CommandPanel();
		cmdp.setLocation(toolp.getX()+toolp.getWidth()+5, this.mainWd.getHeight()-190);
		cmdp.setBorder(getBorder("����"));
		cmdp.setSize((mainWd.getWidth()-toolp.getWidth()-lmp.getWidth())/2-20,140);
		cmdp.cmd.setSize(cmdp.getWidth()-10,cmdp.getHeight()-30);
		bgPanel.add(cmdp);
		Boot.ww.message.setText("�����������.");	
		//���Կ�
		printTime("Property");
		Boot.ww.message.setText("���ڼ������Կ�...");
		pp=new PropertyPanel();
		pp.setLocation(toolp.getWidth()+(lmp.getX()-toolp.getWidth())/2, this.mainWd.getHeight()-190);
		pp.setBorder(getBorder("����"));
		pp.setSize((mainWd.getWidth()-toolp.getWidth()-lmp.getWidth())/2-20,140);
		bgPanel.add(pp);
		Boot.ww.message.setText("���Կ�������.");
		//��ɫ��
		printTime("Color");
		Boot.ww.message.setText("���ڼ�����ɫ��...");
		clp=new ColorPanel();
		clp.setLocation(mainWd.getWidth()-clp.getWidth()-18,lmp.getWidth()+lmp.getY()+5);
		clp.setBorder(getBorder("��ɫ��"));
		clp.setVisible(false);
		bgPanel.add(clp);
		Boot.ww.message.setText("��ɫ��������.");
		//����
		printTime("Canvas");
		Boot.ww.message.setText("���ڼ��ػ���...");
		cvp=new CanvasPanel((mainWd.getWidth()-toolp.getWidth()-lmp.getWidth())/2-20,pp.getY()-(toolp.getY()));
		cvp.setLocation(toolp.getX()+toolp.getWidth()+5,toolp.getY());
		cvp.setBorder(getBorder("����"));
		bgPanel.add(cvp);
		Boot.ww.message.setText("�����������.");
		//�زİ�
		printTime("Material");
		Boot.ww.message.setText("���ڼ����زİ�...");
		mtp=new MaterialPanel((mainWd.getWidth()-toolp.getWidth()-lmp.getWidth())/2-20,pp.getY()-(toolp.getY()));
		mtp.setLocation(toolp.getWidth()+(lmp.getX()-toolp.getWidth())/2,toolp.getY());
		mtp.setBorder(getBorder("�زİ�"));
		bgPanel.add(mtp);
		Boot.ww.message.setText("�زİ�������.");
		/*�����˵�
		printTime();
		Boot.ww.message.setText("���ڼ��ز˵�...");
		mp=new MenuPanel();
		mp.setLocation(0,0);
		//clp.setBorder(getBorder("��ɫ��"));
		bgPanel.add(mp);
		this.mainWd.setJMenuBar(this.mp.all);
		Boot.ww.message.setText("�˵��������.");
		*/
		//ѡ���ļ����
		printTime("SelectFile");
		Boot.ww.message.setText("���ڼ���ѡ���ļ����...");
		sfp=new SelectFilePanel();
		sfp.setLocation(mainWd.getWidth()/2-sfp.getWidth()/2,mainWd.getHeight()/2-sfp.getHeight()/2);
		sfp.setBorder(getBorder("ѡ���ļ�"));
		bgPanel.add(sfp);
		Boot.ww.message.setText("�������.");
		//�½��ļ����
		printTime("NewFile");
		Boot.ww.message.setText("���ڼ����½��ļ����...");
		nfp=new NewFilePanel();
		nfp.setLocation(mainWd.getWidth()/2-nfp.getWidth()/2,mainWd.getHeight()/2-nfp.getHeight()/2);
		nfp.setBorder(getBorder("�½��ļ�"));
		bgPanel.add(nfp);
		nfp.setVisible(false);
		Boot.ww.message.setText("�½��ļ����������.");
		
		
		Boot.ww.message.setText("��ʾ������");
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
		//����������
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
