package CAE;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

class spawnwindows extends Thread implements ActionListener,KeyListener{
	public static GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
	public static Rectangle rec=ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
	public static int xl=(int)rec.getWidth();
	public static int yl=(int)rec.getHeight();
	public static gui wi=new gui(630,300,xl/2-650/2,yl/2-225,true, "����������v1.1(160423<R>)",true);
	JButton jbt0=new JButton("�������");
	JButton jbt2=new JButton("���ڳ���");
	JButton jbt3=new JButton("��������");
	JButton jbt4=new JButton("�˳�����");
	String ss="<������ ����\"�������\"�����>";
	static JLabel jl1 = new JLabel();
	static JLabel jl2 = new JLabel();
	static JLabel jl3 = new JLabel();
	static JLabel jl4 = new JLabel();
	static JLabel jl5 = new JLabel();
	static JLabel jl6 = new JLabel();
	static JLabel jl7 = new JLabel();
	static JLabel jl8 = new JLabel();
	static JLabel jl9 = new JLabel();
	static JLabel jl10 = new JLabel();
	static JLabel[] jl = new JLabel[]{jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9,jl10};
	static JLabel l=new JLabel();
	static boolean b1=true;
	public void run(){
		wi.setResizable(false);
		
		jbt0.setSize(110,40);
		jbt0.setLocation(20,40);
		jbt0.addActionListener(this);
		
		jbt2.setSize(110,40);
		jbt2.setLocation(20,90);
		jbt2.addActionListener(this);
		
		jbt3.setSize(110,40);
		jbt3.setLocation(20,140);
		jbt3.addActionListener(this);
		
		jbt4.setSize(110,40);
		jbt4.setLocation(20,190);
		jbt4.addActionListener(this);
		
		l.setSize(450,15);
		l.setLocation(150,250);

		JPanel jp=new JPanel();
		jp.setLayout(null);
		jp.add(jbt0);
		jp.add(jbt2);
		jp.add(jbt3);
		jp.add(jbt4);
		jp.add(l);
		int ii=3;
		for(int i=0;i<10;){
			jl[i].setText(ss);
			jp.add(jl[i]);
			jl[i].setSize(650,15);
			jl[i].setLocation(170,ii);
			jl[i].addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e) {
					int in=0;
					for(int t=0;t<10;){
						if(e.getSource()==jl[t]){
							in=t;
						}
						t++;
					}
					if(!jl[in].getText().equals("<������ ����\"�������\"�����>")&&javax.swing.JOptionPane.showConfirmDialog(null,"��ȷ��Ҫɾ����������?")==0){
						main.c.del(in);
					}
				}
				public void mouseEntered(MouseEvent arg0) {
					l.setText("������ֱ�ǩ����ɾ�����趨������!");
				}
				public void mouseExited(MouseEvent arg0) {
					l.setText(null);
				}
				public void mousePressed(MouseEvent arg0) {}
				public void mouseReleased(MouseEvent arg0) {}
				
			});
			i++;
			ii=ii+25;
		}
		
		wi.addKeyListener(this);
		wi.add(jp);
		jp.setFocusable(true);
		wi.requestFocus();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbt0){
			if(b1){
				b1=false;
				new addevent();
			}else{
				addevent.gi.requestFocus();
			}
		}
		if(e.getSource()==jbt2){
			main.gi.setVisible(true);
		}
		if(e.getSource()==jbt3){
			setting.gi.setVisible(true);
		}
		if(e.getSource()==jbt4){
			File file=new File("exit.txt");
			if(file.exists()){

				FileReader fr = null;
				try {
					fr = new FileReader(file);
				} catch (Exception e1) {
					//file.getPath();
					javax.swing.JOptionPane.showMessageDialog(null,"����:"+e1+" �����˳�!");
					System.exit(0);
					main.w.del("cmdfile.bat");
				}
				BufferedReader br=new BufferedReader(fr);
				StringBuffer sb=new StringBuffer("");
				while(true){
					String str = null;
					try {
						str = br.readLine();
					} catch (Exception e1) {
						javax.swing.JOptionPane.showMessageDialog(null,"����:"+e1+" �����˳�!");
						System.exit(0);
						main.w.del("cmdfile.bat");
					}
					if(str==null){
						break;
					}
					sb=new StringBuffer(sb+str);
				}
				if(sb.toString().equals("ѯ��")){
					int r=JOptionPane.showConfirmDialog(null,"�Ƿ���С����ϵͳ���̣����\"��\"ֱ���˳�");
					if(r==0){
						spawnwindows.wi.setVisible(false);
					}else if(r==1){
						System.exit(0);
						main.w.del("cmdfile.bat");
					}
				}else if(sb.toString().equals("ֱ���˳�")){
					System.exit(0);
					main.w.del("cmdfile.bat");
				}else if(sb.toString().equals("��С��������")){
					spawnwindows.wi.setVisible(false);
				}
			}else if(!file.exists()){
				System.exit(0);
			}
		}
	}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {
		try {
			main.c.keycheck(e.getKeyChar());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public void keyTyped(KeyEvent e) {}
}

