package Pickaxe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class NewFilePanel extends JPanel{
	Color bg=new Color(25,25,25),custom=new Color(5,204,226);
	Font bFont=new Font("",Font.PLAIN,15);
	Color textColor=new Color(5,204,226);
	MyLabel fileNameLabel,dirLabel,dirChecker,wLabel,hLabel,bgColor,bgColorShow,dotPxd,tip;
	MyTextField fileName,dir,width,height;
	MyButton chooseDir,cancel,ok;
	JComboBox presetColor;
	NewFilePanel(){
		this.setBackground(bg);
		this.setLayout(null);
		this.setSize(500, 350);

		fileNameLabel=new MyLabel("文件名");
		fileNameLabel.setSizex(150, 20).setLocationx(10, 20).setForeground(new Color(5,204,226));
		this.add(fileNameLabel);
		
		fileName=new MyTextField();
		fileName.setSizex(150, 30).setLocationx(10, 40).setBackgroundx(bg).setForeground(new Color(5,204,226));
		this.add(fileName);
		
		dotPxd=new MyLabel(".pxd");
		dotPxd.setSizex(150, 20).setLocationx(160,50).setFontx(bFont).setForeground(new Color(5,204,226));
		this.add(dotPxd);
		
		dirLabel=new MyLabel("目录");
		dirLabel.setSizex(150, 20).setLocationx(10, 80).setForeground(new Color(5,204,226));
		this.add(dirLabel);
		
		dir=new MyTextField();
		dir.setSizex(450, 30).setLocationx(10, 100).setBackgroundx(bg).setForeground(new Color(5,204,226));
		this.add(dir);
		
		chooseDir=new MyButton("...");
		chooseDir.setSizex(30,30).setLocationx(462,100).setForeground(textColor);
		chooseDir.addActionListener(new _selectFileAction());
		this.add(chooseDir);

		ok=new MyButton("开始");
		ok.setSizex(70, 30).setLocationx(10, this.getHeight()-40).setForeground(textColor);
		ok.addActionListener(new _selectFileAction());
		this.add(ok);
		
		cancel=new MyButton("取消");
		cancel.setSizex(70, 30).setLocationx(90, this.getHeight()-40).setForeground(textColor);
		cancel.addActionListener(new _selectFileAction());
		this.add(cancel);
		

		wLabel=new MyLabel("宽度");
		wLabel.setSizex(150, 20).setLocationx(10,160).setForeground(new Color(5,204,226));
		this.add(wLabel);
		
		width=new MyTextField();
		width.setSizex(50, 30).setLocationx(10, 180).setBackgroundx(bg).setForeground(new Color(5,204,226));
		width.addKeyListener(new KeyListener() {
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
		this.add(width);
		hLabel=new MyLabel("长度");
		hLabel.setSizex(150, 20).setLocationx(80,160).setForeground(new Color(5,204,226));
		this.add(hLabel);
		
		height=new MyTextField();
		height.setSizex(50, 30).setLocationx(80, 180).setBackgroundx(bg).setForeground(new Color(5,204,226));
		height.addKeyListener(new KeyListener() {
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
		this.add(height);
		
		tip=new MyLabel("设置的是像素格的数量  导出为图片(如png文件)时才设置图片大小");
		tip.setSizex(500, 20).setLocationx(140,180).setForeground(new Color(5,204,226));
		tip.setFont(new Font("",Font.ITALIC,12));
		this.add(tip);
		

		bgColor=new MyLabel("背景色");
		bgColor.setSizex(150, 20).setLocationx(10,220).setForeground(new Color(5,204,226));
		this.add(bgColor);
		
		bgColorShow=new MyLabel("■");
		bgColorShow.setSizex(30, 30).setLocationx(6,240).setForeground(Color.BLACK);
		bgColorShow.setFont(new Font("",Font.PLAIN,30));
		bgColorShow.addMouseListener(new _selectFileMouse());
		this.add(bgColorShow);
		
		String str[]={"黑色","白色","透明","自定义"};
		presetColor=new JComboBox(str);
		presetColor.setSize(70,30);
		presetColor.setLocation(38,240);
		presetColor.setForeground(new Color(5,204,226));
		presetColor.setBackground(new Color(25,25,25));
		presetColor.addActionListener(new _selectFileAction());
		this.add(presetColor);
	}
}
