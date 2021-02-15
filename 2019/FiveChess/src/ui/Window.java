package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import data.DataBase;
import gameMgr.GameMgr;
import ts.opt;

public class Window {
	public JFrame mainw=new JFrame();
	public DisplayPanel dp=new DisplayPanel();
	public JButton st2p=new JButton("2Player"),stai=new JButton("PlayWithAI")
			,back=new JButton("Back");
	public JLabel notice=new JLabel("no notice to display now.");
	public Window(){
		mainw.setSize(520, 400);
		mainw.setLocation(200, 100);
		mainw.setLayout(null);
		mainw.setTitle("FCAI");
		mainw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainw.setForeground(new Color(10,10,10));
		mainw.setBackground(new Color(10,10,10));
		

		st2p.setSize(120, 30);
		st2p.setLocation(345, 20);
		st2p.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {//2player starting
				// TODO Auto-generated method stub
				DataBase.playing=true;
				DataBase.gameMd=0;
				GameMgr.resetPnl();
			}
			
		});
		mainw.add(st2p);
		stai.setSize(120, 30);
		stai.setLocation(345, 80);
		stai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {//ai starting
				// TODO Auto-generated method stub
				DataBase.playing=true;
				DataBase.gameMd=DataBase.AIMode;
				GameMgr.resetPnl();
			}
			
		});
		mainw.add(stai);
		
		back.setSize(80, 30);
		back.setLocation(345, 160);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataBase.back();
			}
		});
		mainw.add(back);
		
		notice.setSize(200, 30);
		notice.setLocation(345, 120);
		mainw.add(notice);
		
		
		mainw.add(dp);
		mainw.setVisible(true);
		System.out.print("Ready");
		opt.printTime();
	}
}
