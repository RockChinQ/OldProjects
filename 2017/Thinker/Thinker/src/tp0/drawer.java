package tp0;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class drawer {
	int mode=-1;
	public gui g=new gui(940,605,0,0,false,"Thinker-请绘制附图",true,true,true);
	draw2 p=new draw2(450,470);
	JPanel jp=new JPanel();
	JButton sela0=new JButton();
	boolean[][] isb=new boolean[450][470];
	public int[][] xay=new int[500000][5];//x,y,name,chose,num
	public int xayc=0;
	StringBuffer[][] name=new StringBuffer[450][470];
	ImageIcon ii0=new ImageIcon("point.jpg");
	ImageIcon ii1=new ImageIcon("point2.jpg");
	JButton jbt0=new JButton(ii0);
	int page=0;
	int page2=0;
	JButton[] poa=new JButton[18];
	JButton[] lia=new JButton[18];
	public int[][] papb=new int[500000][6];
	public int papbc=0;
	ImageIcon ii2=new ImageIcon("up.jpg");
	ImageIcon ii3=new ImageIcon("down.jpg");
	ImageIcon ii4=new ImageIcon("up.jpg");
	ImageIcon ii5=new ImageIcon("down.jpg");
	ImageIcon ii6=new ImageIcon("editing");
	JButton last=new JButton(ii2);
	JButton next=new JButton(ii3);
	JButton last2=new JButton(ii4);
	JButton next2=new JButton(ii5);
	JButton edit=new JButton(ii6);
	JLabel jl0=new JLabel("  点:(点击下面按钮以选取)");
	JLabel jl1=new JLabel("  线:(点击下面按钮以选取)");
	JLabel lin=new JLabel(new ImageIcon("line.jpg"));
	char[] cha=new char[26];
	int chac=0;
	int note=65;
	JButton con=new JButton("连接所选点");
	int chose=0;
	ImageIcon ii7=new ImageIcon("start.jpg");
	ImageIcon ii8=new ImageIcon("start2.jpg");
	JButton start=new JButton("开始");
	/*
	 * 
	 * edit
	 * 
	 * 
	 * 
	 * */
	JTextField jtf0=new JTextField();
	JTextField jtf1=new JTextField();
	JLabel e_jl0=new JLabel("X");
	JLabel e_jl1=new JLabel("Y");
	JButton e_del=new JButton("删除");
	JButton e_save=new JButton("保存");
	JButton e_setax=new JButton("x值全部设为");
	JButton e_setay=new JButton("y值全部设为");
	/*标号*/
	JLabel nextele=new JLabel("下一个字母:");
	JLabel nextnum=new JLabel("下一个下标:");
	JComboBox a_ele=new JComboBox();
	JComboBox a_num=new JComboBox();
	/**/
	editpa ep=new editpa();
	Thread t=new Thread();
	drawer(){
		g.setResizable(false);
		g.setLayout(null);
		p.setBorder(BorderFactory.createLineBorder(Color.black,2));
		p.setLayout(null);
		p.setLocation(80, 0);
		p.setSize(450, 500);
		p.addMouseListener(new mouse0());
		g.add(p);
		
		jbt0.setSize(40, 40);
		jbt0.setLocation(10, 20);
		jbt0.addActionListener(new action0());
		g.add(jbt0);
		
		jp.setSize(400, 470);
		jp.setLocation(535, 0);
		jp.setLayout(null);
		g.add(jp);
		
		jl0.setSize(500, 30);
		jl0.setLocation(5, 0);
		jp.add(jl0);
		
		jl1.setSize(500, 30);
		jl1.setLocation(5, 210);
		jp.add(jl1);
		
		
		lin.setSize(1200, 3);
		lin.setLocation(-200, 480);
		g.add(lin);
		
		last.setSize(30, 30);
		last.setLocation(365, 140);
		jp.add(last);
		last.addActionListener(new action1());
		
		next.setSize(30, 30);
		next.setLocation(365, 175);
		jp.add(next);
		next.addActionListener(new action1());
		
		last2.setSize(30, 30);
		last2.setLocation(365, 350);
		jp.add(last2);
		last2.addActionListener(new action1());
		
		next2.setSize(30, 30);
		next2.setLocation(365, 385);
		jp.add(next2);
		next2.addActionListener(new action1());
		
		con.setSize(150,40);
		con.setLocation(0, 430);
		con.setEnabled(false);
		con.addActionListener(new action2());
		con.setBackground(Color.white);
		jp.add(con);
		
		start.setSize(130, 40);
		start.setLocation(170, 430);
		start.addMouseListener(new mouse2());
		start.setFont(new Font("Serif",Font.CENTER_BASELINE,15));
		start.setForeground(Color.black);
		start.setEnabled(false);
		start.setBackground(Color.white);
		jp.add(start);
		
		for(int i=0;i<6;i++){
			poa[i]=new JButton();
			poa[i].setText("<暂无>");
			poa[i].setSize(120, 30);
			poa[i].setLocation(0, i*poa[i].getHeight()+30);
			poa[i].addMouseListener(new mouse1());
			poa[i].setBackground(Color.WHITE);
			jp.add(poa[i]);

			poa[i+6]=new JButton();
			poa[i+6].setText("<暂无>");
			poa[i+6].setSize(120, 30);
			poa[i+6].setLocation(120, i*poa[i].getHeight()+30);
			poa[i+6].addMouseListener(new mouse1());
			poa[i+6].setBackground(Color.WHITE);
			jp.add(poa[i+6]);

			poa[i+12]=new JButton();
			poa[i+12].setText("<暂无>");
			poa[i+12].setSize(120, 30);
			poa[i+12].setLocation(240, i*poa[i].getHeight()+30);
			poa[i+12].addMouseListener(new mouse1());
			poa[i+12].setBackground(Color.WHITE);
			jp.add(poa[i+12]);
		}
		for(int i=0;i<6;i++){
			lia[i]=new JButton();
			lia[i].setText("<暂无>");
			lia[i].setSize(120, 30);
			lia[i].setLocation(0, i*lia[i].getHeight()+240);
			lia[i].setBackground(Color.WHITE);
			lia[i].addActionListener(new liaac());
			jp.add(lia[i]);

			lia[i+6]=new JButton();
			lia[i+6].setText("<暂无>");
			lia[i+6].setSize(120, 30);
			lia[i+6].setLocation(120, i*lia[i].getHeight()+240);
			lia[i+6].setBackground(Color.WHITE);
			lia[i+6].addActionListener(new liaac());
			jp.add(lia[i+6]);

			lia[i+12]=new JButton();
			lia[i+12].setText("<暂无>");
			lia[i+12].setSize(120, 30);
			lia[i+12].setLocation(240, i*lia[i].getHeight()+240);
			lia[i+12].setBackground(Color.WHITE);
			lia[i+12].addActionListener(new liaac());
			jp.add(lia[i+12]);
		}
		//g.setVisible(true);
		ep.setSize(920, 300);
		ep.setLocation(0, 483);
		jtf0.setSize(60, 30);
		jtf1.setSize(60, 30);
		jtf0.setLocation(290, 10);
		jtf1.setLocation(390, 10);
		ep.add(jtf0);
		ep.add(jtf1);
		jtf0.setVisible(false);
		jtf0.addKeyListener(new keyli());
		jtf1.setVisible(false);
		jtf1.addKeyListener(new keyli());
		
		e_jl0.setVisible(false);
		e_jl1.setVisible(false);
		e_jl0.setSize(40, 40);
		e_jl1.setSize(40,40);
		e_jl0.setLocation(260, 10);
		e_jl1.setLocation(360, 10);
		ep.add(e_jl0);
		ep.add(e_jl1);
		
		e_del.setSize(80, 30);
		e_save.setSize(80, 30);
		e_del.setLocation(820, 10);
		e_del.addActionListener(new editac());
		e_save.setLocation(820, 50);
		e_save.addActionListener(new editac());
		ep.add(e_del);
		ep.add(e_save);
		e_del.setVisible(false);
		e_save.setVisible(false);
		
		e_setax.setSize(120, 30);
		e_setay.setSize(120, 30);
		e_setax.setLocation(250, 40);
		e_setay.setLocation(370, 40);
		ep.add(e_setax);
		ep.add(e_setay);
		e_setax.setVisible(false);
		e_setay.setVisible(false);
		e_setax.addActionListener(new action3());
		e_setay.addActionListener(new action3());
		//标号
		a_ele.setSize(70, 30);
		for(int a=0;a<26;a++){
			a_ele.addItem((char)('A'+a));
		}
		a_ele.setSelectedIndex(0);
		a_ele.setLocation(3, 90);
		g.add(a_ele);
		
		a_num.setSize(70, 30);
		for(int a=-4096;a<0;a++){
			a_num.addItem(a);
		}
		a_num.addItem(" ");
		for(int a=0;a<4096;a++){
			a_num.addItem(a);
		}
		a_num.setSelectedIndex(4096);
		a_num.setLocation(3,150);
		g.add(a_num);
		a_ele.addActionListener(new action4());
		
		nextele.setSize(200, 20);
		nextnum.setSize(200, 20);
		nextele.setLocation(3, 70);
		nextnum.setLocation(3, 120);
		g.add(nextele);
		g.add(nextnum);
		
		g.add(ep);
	}
	void refresh() {
		jl0.setText("  点-数量:"+(xayc)+"总页数:"+((xayc-1)/18+1)+"当前页:"+(page+1)+" :(点击下面按钮以选取  已选:"+chose+")");
		for(int a=0;a<18;a++){
			//System.out.println(a+"  "+poa[a].getText());
			if(xay[page*18+a][0]!=0&&xay[page*18+a][1]!=0&&xay[page*18+a][2]>=65/*&&xay[page*18+a][2]<65+26*/)
				poa[a%18].setText("("+xay[page*18+a][0]+","+xay[page*18+a][1]+") "+(char)xay[page*18+a][2]+"~"+(xay[page*18+a][4]==4097?"":xay[page*18+a][4]));
			else
				poa[a%18].setText("<暂无>");
			if(xay[page*18+a][3]==1){
				poa[a%18].setBackground(Color.YELLOW);
			}else{
				poa[a%18].setBackground(Color.WHITE);
			}
			//System.out.println(page+" "+(page*18+a)+" "+a);
		}
		if(chose>=2){
			con.setEnabled(true);
		}else{
			con.setEnabled(false);
		}
		if(xayc>=1){
			start.setEnabled(true);
		}else{
			start.setEnabled(false);
		}
		refreshEdit();
		g.setVisible(true);
	}
	void refresh2() {
		jl1.setText("  点-数量:"+(papbc)+"总页数:"+((papbc-1)/18+1)+"当前页:"+(page2+1)+" :(点击下面按钮以删除)");
		for(int a=0;a<18;a++){
			//System.out.println(papb[a][3]);
			if(papb[page2*18+a][0]>=65&&xay[page*18+a][2]<65+26)
				lia[a%18].setText(""+(char)papb[a][0]+"~"+(papb[a][3]==4097?"NULL":papb[a][3])+" "+(char)papb[a][1]+"~"+(papb[a][4]==4097?"NULL":papb[a][4]));
			else
				lia[a%18].setText("<暂无>");
			if(papb[page2*18+a][2]==1){
				lia[a%18].setBackground(Color.YELLOW);
			}else{
				lia[a%18].setBackground(Color.WHITE);
			}
			//System.out.println(page+" "+(page*18+a)+" "+a);
		}
		
		refreshEdit();
		g.setVisible(true);
	}
	void refreshEdit(){
		if(chose==1){
			editSec();
		}else if(chose>1){
			editThr();
		}else if(chose==0){
			editFor();
		}
	}
	void addline(char pa,char pb){
		papb[papbc][0]=pa;
		papb[papbc][1]=pb;
		papbc++;
		refresh2();
	}
	void editSec(){
		int ch=0;
		for(int a=0;a<xayc;a++){
			if(xay[a][3]==1){
				ch=a;
				break;
			}
		}
		ep.jl0.setIcon(new ImageIcon("editp.jpg"));
		ep.jl0.setText("选择:"+(char)xay[ch][2]);
		jtf0.setText(""+xay[ch][0]);
		jtf1.setText(""+xay[ch][1]);
		e_setax.setVisible(false);
		e_setay.setVisible(false);
		setEditVis(true);
		g.setVisible(true);
	}
	void editThr(){
		ep.jl0.setIcon(new ImageIcon("editps.jpg"));
		ep.jl0.setText("选择多个点:"+chose+"个");
		jtf0.setText(null);
		jtf1.setText(null);
		e_setax.setVisible(true);
		e_setay.setVisible(true);
		e_save.setVisible(false);
	}
	void editFor(){
		ep.jl0.setIcon(null);
		ep.jl0.setText("未选取任何点...");
		setEditVis(false);
		g.setVisible(true);
		e_setax.setVisible(false);
		e_setay.setVisible(false);
	}
	void setEditVis(boolean vis){
		jtf0.setVisible(vis);
		jtf1.setVisible(vis);
		e_jl0.setVisible(vis);
		e_jl1.setVisible(vis);
		e_del.setVisible(vis);
		e_save.setVisible(vis);
		
	}
	void delPoint(int x){
		//System.out.println(x);
		for(int b=0;b<papbc;b++){
			for(int a=0;a<papbc;a++){
				//System.out.println(a+" "+papbc+" "+papb[a][0]+" "+papb[a][1]+" "+xay[x][2]);
				if(papb[a][0]==xay[x][2]||papb[a][1]==xay[x][2]){
					delLine(a);
				}
			}
		}
		for(int a=x;a<xayc;a++){
			xay[a][0]=xay[a+1][0];
			xay[a][1]=xay[a+1][1];
			xay[a][2]=xay[a+1][2];
			xay[a][3]=xay[a+1][3];
		}
		xayc--;
		refresh();
		p.repaint();
	}
	void delLine(int x){
		for(int a=x;a<papbc;a++){
			papb[a][0]=papb[a+1][0];
			papb[a][1]=papb[a+1][1];
			papb[a][2]=papb[a+1][2];
		}
		papbc--;
		//System.out.println(papbc);
		refresh2();
		p.repaint();
	}
	void setnum(){
		a_num.removeAllItems();
		for(int a=-4096;a<0;a++){
			a_num.addItem(a);
		}
		a_num.addItem(" ");
		for(int a=0;a<4096;a++){
			a_num.addItem(a);
		}
		//____________
		for(int a=0;a<xayc;a++){
			//System.out.println((char)xay[a][2]+"  "+(char)a_ele.getSelectedItem());
			if((char)xay[a][2]==(char)a_ele.getSelectedItem()){
				//for(int b=-4096;b<4096;b++){
				//	if((int)a_ele.getItemAt(b)==xay[a][4]){
				//		System.out.println(0000);
				//		a_ele.remove(arg0);
				//	}
				//}
				a_num.removeItem(xay[a][4]==4097?" ":xay[a][4]);
			}
		}
		a_num.setSelectedIndex(4096);
	}
}
