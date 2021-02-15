package PickViewer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {
	public static int x=0,y=0;
	static JButton rangeb=new JButton("起点区块 当前：0,0");
	static PickPanel pp;
	static JFrame mainwd;
	static PickImage pi;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		String image="map.pick";
		//if(new File(FileRW.read("")))
		System.out.println(本类_打开较慢_专用于储存地图数据.告诉我地图数据可好()+"\n"+(new File(image).getAbsolutePath()));
		FileRW.write("map.pick", 本类_打开较慢_专用于储存地图数据.告诉我地图数据可好(), false);
		
		if(!new File(image).exists()) {
			
			javax.swing.JOptionPane.showMessageDialog(null, "文件不存在");
			System.exit(0);
		}
		pi=new PickImage(image);
		mainwd=new JFrame("PickViewer");
		pp=new PickPanel(5,pi.w,pi.h);
		pp.gridLine=true;
		pp.frontAlpha=200;
		pp.loadPickImage(0, 0, pi);
		mainwd.setLayout(null);
		pp.setLocation(20, 40);
		mainwd.add(pp);
		mainwd.setSize(pp.getSize());
		mainwd.setLocation(100, 100);
		//mainwd.setVisible(true);
		mainwd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//zoom
		JButton zoomb=new JButton("缩放("+pp.zoom);
		zoomb.setSize(100, 30);
		zoomb.setLocation(10, 0);
		mainwd.add(zoomb);
		zoomb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				while(true) {
				try {
					int zoom=Integer.parseInt(javax.swing.JOptionPane.showInputDialog("请输入像素格大小   当前大小为："+pp.zoom));
					pp.zoom=zoom;
					zoomb.setText("缩放("+pp.zoom);
					mainwd.repaint();
					//mainwd.setSize(pp.getSize());
					mainwd.setVisible(true);
					break;
				}catch(Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null,"不合法数据");
					continue;
				}
				}
			}
			
		});
		//range
		rangeb.setSize(170, 30);
		rangeb.setLocation(110, 0);
		mainwd.add(rangeb);
		rangeb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				while(true) {
					try {
						int xt=Integer.parseInt(javax.swing.JOptionPane.showInputDialog("区块xxxxxxxx"));
						x=xt;
						break;
					}catch(Exception ex) {
						javax.swing.JOptionPane.showMessageDialog(null, "数据不合法，请输入数字");
					}
				}
				while(true) {
					try {
						int yt=Integer.parseInt(javax.swing.JOptionPane.showInputDialog("区块yyyyyy"));
						y=yt;
						break;
					}catch(Exception ex) {
						javax.swing.JOptionPane.showMessageDialog(null, "数据不合法，请输入数字");
					}
				}
				update();
			}
		});
		
		//direc
		JButton left=new JButton("<");
		left.setSize(60,30);
		left.setLocation(290, 0);
		mainwd.add(left);
		JButton right=new JButton(">");
		right.setSize(60,30);
		right.setLocation(350, 0);
		mainwd.add(right);
		JButton down=new JButton("↓");
		down.setSize(60, 30);
		down.setLocation(410, 0);
		mainwd.add(down);
		JButton up=new JButton("↑");
		up.setSize(60, 30);
		up.setLocation(470, 0);
		mainwd.add(up);
		
		
		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				x=x-1<0?0:x-1;
				update();
			}
		});
		right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				x=x+1;
				update();
			}
		});
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				y=y-1<0?0:y-1;
				update();
			}
		});
		down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				y++;
				update();
			}
		});
		mainwd.repaint();
		mainwd.setVisible(true);
	}
	public static void update() {
		
		pp.reset();
		pp.loadPickImage(0-(16*x), 0-(16*y), pi);
		rangeb.setText("起点区块 当前："+x+","+y);
		
		pp.resetFront();
		for(int i=0;i<pp.gridOfHeight;i++) {
			for(int j=0;j<pp.gridOfWidth;j++) {
				if(i<16&&j<16)
					continue;
				pp.setFront(j, i, new Color(0,0,0));
			}
		}
		
		mainwd.repaint();
	}
}
