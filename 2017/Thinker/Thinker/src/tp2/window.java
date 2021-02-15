package tp2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class window {
	gui g=new gui(500,600,0,0,false,"输入定理",true,false,true);
	JPanel jp=new JPanel();
	JLabel if_if=new JLabel("如果：");
	JTextArea if_jta0=new JTextArea();
	JLabel if_help=new JLabel(new ImageIcon("help.png"));
	
	JLabel then_then=new JLabel("那么：");
	JTextArea then_jta0=new JTextArea();
	
	JButton fini=new JButton("完成");
	JLabel name=new JLabel("此定理名称:");
	JTextField jtf=new JTextField();
	window(){
		jp.setLayout(null);
		
		if_if.setFont(new Font("Serif",Font.ITALIC,18));
		if_if.setSize(100,20);
		if_if.setLocation(10, 10);
		jp.add(if_if);
		
		if_jta0.setSize(475, 220);
		if_jta0.setLocation(10, 40);
		if_jta0.setBorder(BorderFactory.createLineBorder(Color.gray,2));
		jp.add(if_jta0);
		
		if_help.setSize(20, 20);
		if_help.setLocation(70, 10);
		if_help.addMouseListener(new mouse0());
		jp.add(if_help);
		
		then_then.setFont(new Font("Serif",Font.ITALIC,18));
		then_then.setSize(100,20);
		then_then.setLocation(10, 270);
		jp.add(then_then);
		
		then_jta0.setSize(475, 220);
		then_jta0.setLocation(10, 300);
		then_jta0.setBorder(BorderFactory.createLineBorder(Color.gray,2));
		jp.add(then_jta0);

		name.setSize(80, 40);
		name.setLocation(10, 525);
		jp.add(name);
		
		jtf.setSize(100, 30);
		jtf.setLocation(90, 530);
		jp.add(jtf);
		
		fini.setSize(100, 40);
		fini.setLocation(200, 525);
		fini.addActionListener(new action0());
		jp.add(fini);
		
		g.add(jp);
	}
}
