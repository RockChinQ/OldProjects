package Preference;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Classes.*;
import Display.DrawTimerTask;
import Kernel.ComputeImage;
import Main.*;

public class Prefere extends JPanel {
	MyLabel topCpt,topMnt,topStart;
	MyLabel lCellAmount,lIterCycle,lBoundCptType;
	MyTextField tCellX,tCellY,tIterCycle;
	ButtonGroup bt;
	JRadioButton bs,c1,c0,rdm;
	
	MyLabel lCellSize,lZoom,lDrawCycle,lWallPaper,lWpCycle;
	MyTextField tCellW,tCellH,tZoom,tDrawCycle,tWpCycle;
	MyButton cptCellAmount,update;
	JCheckBox wallPaper;
	
	MyLabel lSurRate;
	JSlider surRate;
	MyButton spawn,start,stop,next;
	
	Font topFont=new Font("",Font.BOLD,22),secFont=new Font("",Font.PLAIN,15);
	DataExch dat=Main.dat;
	
	boolean isStart=false;
	public Prefere(){
		this.setLayout(null);
		this.setLocation(0,0);
		this.setSize(400, 500);
		
		topCpt=new MyLabel("运算").setFontx(topFont)
				.setSizex(50, 30).setLocationx(10, 10);
		this.add(topCpt);

		lCellAmount=new MyLabel("细胞数量:").setFontx(secFont).setSizex(90, 20).setLocationx(15, 35);
		this.add(lCellAmount);
		lIterCycle=new MyLabel("迭代周期(秒):").setFontx(secFont).setSizex(130, 20).setLocationx(15, 55);
		this.add(lIterCycle);
		lBoundCptType=new MyLabel("边界处理方式:").setFontx(secFont).setSizex(130, 20).setLocationx(15, 75);
		this.add(lBoundCptType);

		tCellX=new MyTextField().setSizex(50, 20).setLocationx(105, 35);
		this.add(tCellX);
		tCellY=new MyTextField().setSizex(50, 20).setLocationx(155, 35);
		this.add(tCellY);
		tCellX.setText(dat.pWidth+"");
		tCellY.setText(dat.pHeight+"");
		tIterCycle=new MyTextField().setSizex(50, 20).setLocationx(105, 55);
		this.add(tIterCycle);
		tIterCycle.setText(dat.iterCycle+"");
		bt=new ButtonGroup();
		bs=new JRadioButton("边界环绕");
		bs.setSize(90, 30);
		bs.setLocation(115, 75);
		bt.add(bs);
		rdm=new JRadioButton("初始状态");
		rdm.setSize(100, 30);
		rdm.setLocation(205, 75);
		bt.add(rdm);
		c1=new JRadioButton("常量1");
		c1.setSize(70, 30);
		c1.setLocation(115, 105);
		bt.add(c1);
		c0=new JRadioButton("常量0");
		c0.setSize(70, 30);
		c0.setLocation(205, 105);
		bt.add(c0);
		this.add(bs);
		this.add(rdm);
		this.add(c1);
		this.add(c0);
		rdm.setSelected(true);
		
		
		topMnt=new MyLabel("监视图").setFontx(topFont)
				.setSizex(70, 30).setLocationx(10,135);
		this.add(topMnt);

		lCellSize=new MyLabel("缩略图上细胞尺寸(像素):").setSizex(170, 20).setFontx(secFont).setLocationx(15, 165);
		this.add(lCellSize);
		lZoom=new MyLabel("壁纸细胞尺寸:窗口细胞尺寸(支持浮点数):").setSizex(290, 20).setFontx(secFont).setLocationx(15, 225);
		this.add(lZoom);
		lDrawCycle=new MyLabel("绘制周期(秒,支持浮点数):").setSizex(180, 20).setFontx(secFont).setLocationx(15, 245);
		this.add(lDrawCycle);
		lWallPaper=new MyLabel("壁纸:").setSizex(35, 20).setFontx(secFont).setLocationx(15, 265);
		this.add(lWallPaper);
		lWpCycle=new MyLabel("壁纸设置周期(秒,支持浮点数):").setSizex(210, 20).setFontx(secFont).setLocationx(15, 285);
		this.add(lWpCycle);

		tCellW=new MyTextField().setSizex(50, 20).setLocationx(180, 165);
		tCellH=new MyTextField().setSizex(50, 20).setLocationx(232, 165);
		this.add(tCellW);
		this.add(tCellH);
		tCellW.setText(dat.cWidth+"");
		tCellH.setText(dat.cHeight+"");
		tZoom=new MyTextField().setSizex(50, 20).setLocationx(292, 225);
		this.add(tZoom);
		tZoom.setText(dat.zoomToWp+"");
		tDrawCycle=new MyTextField().setSizex(50, 20).setLocationx(185, 245);
		this.add(tDrawCycle);
		tDrawCycle.setText(dat.drawCycle+"");
		wallPaper=new JCheckBox("将监视图设为壁纸");
		wallPaper.setSize(140, 20);
		wallPaper.setLocation(55, 265);
		this.add(wallPaper);
		wallPaper.setSelected(dat.wallpaper);
		tWpCycle=new MyTextField().setSizex(50, 20).setLocationx(212, 285);
		this.add(tWpCycle);
		tWpCycle.setText(dat.wpCycle+"");
		cptCellAmount=new MyButton("根据屏幕分辨率确定细胞数量").setSizex(220, 30).setLocationx(15,187);
		this.add(cptCellAmount);
		cptCellAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int screenWidth=((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
				int screenHeight = ((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height); 
				tCellX.setText((int)(screenWidth/getInt(tCellW.getText()))+""); 
				tCellY.setText((int)(screenHeight/getInt(tCellH.getText()))+"");
				tZoom.setText("1.0");
			}
		});
		update=new MyButton("更新设置").setSizex(100, 30).setLocationx(270, 285);
		this.add(update);
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int pw=Main.dat.pWidth,ph=Main.dat.pHeight,gw=Main.dat.cWidth,gh=Main.dat.cHeight;
				updateSettings();
				if(getInt(tCellX.getText())!=pw||getInt(tCellY.getText())!=ph) {
					newPanel();
				}
				if(getInt(tCellW.getText())!=gw||getInt(tCellH.getText())!=gh) {
					Main.w.di.cptSize();
					Main.w.di.repaint();
				}
				if(isStart) {
					stop();
					start();
				}
			}
		});
		
		topStart=new MyLabel("开始").setFontx(topFont)
				.setSizex(50, 30).setLocationx(10,305);
		this.add(topStart);
		
		lSurRate=new MyLabel("初始生存率("+(int)(dat.surRate*100)+"%):").setSizex(135, 20).setLocationx(15, 330).setFontx(secFont);
		this.add(lSurRate);
		surRate=new JSlider(0,100,(int) (dat.surRate*100));
		surRate.setSize(200,50);
		surRate.setLocation(150, 330);
		surRate.setPaintTicks(true);
		surRate.setPaintLabels(true);
		surRate.setMajorTickSpacing(10);
		surRate.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lSurRate.setText("初始生存率("+surRate.getValue()+"%):");
			}
		});
		this.add(surRate);
		spawn=new MyButton("随机生成").setFontx(secFont).setSizex(100, 30).setLocationx(15, 370);
		this.add(spawn);
		spawn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dat.randomSpawn();
				Main.w.di.repaint();
			}
		});
		start=new MyButton("开始迭代").setFontx(secFont).setSizex(100, 30).setLocationx(15, 405);
		this.add(start);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				start();
			}
		});
		stop=new MyButton("停止迭代").setFontx(secFont).setSizex(100, 30).setLocationx(118, 405);
		this.add(stop);
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stop();
			}
		});
		next=new MyButton("下一代").setFontx(secFont).setSizex(100, 30).setLocationx(221, 405);
		this.add(next);
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.cpti.cptNext();
				Main.w.di.repaint();
			}
		});
	}
	void start() {
		if(!isStart) {
			Main.draw.schedule(Main.dtt,new Date(), (long) (Main.dat.drawCycle*1000.0));
			Main.cpt.schedule(Main.cpti,new Date(), (long) (Main.dat.iterCycle*1000.0));
			//System.out.println((long) (Main.dat.drawCycle*1000.0)+" "+(long) (Main.dat.iterCycle*1000.0));
			isStart=true;
			next.setEnabled(false);
		}
	}
	void stop() {
		if(isStart) {
			Main.cpti.cancel();
			Main.dtt.cancel();
			Main.cpti=new ComputeImage();
			Main.dtt=new DrawTimerTask();
			//Main.dtt.cancel();
			isStart=false;
			next.setEnabled(true);
		}
	}
	void updateSettings() {
		
		dat.cWidth=getInt(tCellW.getText());
		dat.cHeight=getInt(tCellH.getText());
		dat.pWidth=getInt(this.tCellX.getText());
		dat.pHeight=getInt(this.tCellY.getText());
		dat.iterCycle=getDouble(this.tIterCycle.getText());
		if(this.bs.isSelected())
			dat.boundCptType='s';
		else if(this.rdm.isSelected())
			dat.boundCptType='r';
		else if(this.c0.isSelected())
			dat.boundCptType='0';
		else
			dat.boundCptType='1';
		dat.drawCycle=getDouble(this.tDrawCycle.getText());
		dat.wallpaper=this.wallPaper.isSelected();
		dat.wpCycle=getDouble(this.tWpCycle.getText());
		dat.zoomToWp=getDouble(this.tZoom.getText());
		dat.surRate=new Double(this.surRate.getValue())/100;
	}
	void newPanel() {
		Main.cpti.generation=0;
		Main.dat.last=new boolean[dat.pHeight][dat.pWidth];
		dat.reset();
		Main.w.di.cptSize();
		Main.w.di.repaint();
	}
	int getInt(String s) {
		return Integer.parseInt(s);
	}
	double getDouble(String s) {
		return Double.parseDouble(s);
	}
}
