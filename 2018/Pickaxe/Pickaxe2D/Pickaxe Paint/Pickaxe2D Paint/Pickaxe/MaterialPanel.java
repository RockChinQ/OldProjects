package Pickaxe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Pickaxe2Dr1.Pick2DPanel;

public class MaterialPanel extends JPanel{
	Color bg=new Color(25,25,25);
	Font bFont=new Font("",Font.PLAIN,15);
	
	JTabbedPane tp=new JTabbedPane(JTabbedPane.TOP,JTabbedPane.WRAP_TAB_LAYOUT);
	JPanel pxdFile,picFile;
	MyLabel pdfLabel,pcfLabel;
	Pick2DPanel pxd;
	JPanel pic;
	MaterialPanel(int w,int h){
		this.setBackground(bg);
		this.setLayout(null);
		this.setSize(w,h);
		
		tp.setSize(w-10,h-30);
		tp.setLocation(5,23);
		this.add(tp);

		pxdFile=new JPanel();
		pxdFile.setLayout(null);
		pxdFile.setSize(tp.getWidth(),tp.getHeight());
		tp.add("pxd文件", pxdFile);
		
		pdfLabel=new MyLabel("无pxd文件  点击选择");
		pdfLabel.setSizex(1050,25).setLocationx(0,0).setFont(new Font("",Font.ITALIC,15));
		pxdFile.add(pdfLabel);
		pdfLabel.addMouseListener(new _materialMouse());
		
		picFile=new JPanel();
		picFile.setLayout(null);
		picFile.setSize(tp.getWidth(),tp.getHeight());
		tp.add("图片文件", picFile);
		
		pcfLabel=new MyLabel("无图片文件  点击选择");
		pcfLabel.setSizex(150,25).setLocationx(0,0).setFont(new Font("",Font.ITALIC,15));
		picFile.add(pcfLabel);
		pcfLabel.addMouseListener(new _materialMouse());
		
	}
	void setPxdFile(Pick2DPanel panel) {
		this.pxd=panel;
		this.pxd.setLocation(0,25);
		this.pxdFile.add(pxd);
	}
	void setPxdZoom(int zi) {
		this.pxd.setGridSize(zi, zi);
		this.pxd.repaint();
		this.pxd.setSize(this.tp.getWidth(), this.tp.getHeight()-25);
	}
	void setPxdFile(Image img) {
	}
}
