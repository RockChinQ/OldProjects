package fstj;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class win {
	static JFrame jf=new JFrame("分数统计--By Rock 版权所有 2016.7.8 ");
	static JPanel jp=new JPanel();
	JTextField jtf0=new JTextField(2147483647);
	static JTextField[] jtf=new JTextField[main.co];
	static int[] sco=new int[main.co];
	static JLabel[] jl=new JLabel[main.co];
	JLabel jl0=new JLabel("========================================================================================");
	JLabel jl1=new JLabel("总分:");
	JLabel jl3=new JLabel("平均分:");
	JLabel jl2=new JLabel("方差:");
	JLabel jl4=new JLabel("到");
	JLabel jl5=new JLabel("之间(不包含两数)的人数:");
	JLabel jl6=new JLabel("未填写的文本框将不计入计算 如果要计入请填写0");
	JLabel jl7=new JLabel("最高分:");
	JLabel jl8=new JLabel("最低分:");
	static JTextField tf0=new JTextField();
	static JTextField tf1=new JTextField();
	static JButton jbt=new JButton("刷新");
	win() throws Exception{
		jf.setSize(935,710);
		jf.setResizable(false);
		jf.setLocation(30, 20);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jp.setLayout(null);
		int ii=10;
		int i0=30;
		for(int i=0;i<main.co;){
			jtf[i]=new JTextField(5);
			jtf[i].setSize(60, 20);
			jtf[i].setLocation(i0+70, ii);
			jtf[i].setText("");
			jl[i]=new JLabel("第"+(i+1)+"个:");
			jl[i].setSize(80, 20);
			jl[i].setLocation(i0, ii);
			jp.add(jtf[i]);
			jp.add(jl[i]);
			ii+=28;
			if(ii>=420){
				ii=10;
				i0+=150;
			}
			i+=1;
		}
		jl0.setSize(950, 20);
		jl0.setLocation(0,465);
		jl0.setFont(new Font("Serif",Font.PLAIN,20));
		jl1.setSize(380, 20);
		jl1.setLocation(20,490);
		jl1.setFont(new Font("Serif",Font.PLAIN,22));
		jl2.setSize(380, 20);
		jl2.setLocation(20,520);
		jl2.setFont(new Font("Serif",Font.PLAIN,22));
		jl3.setSize(380, 20);
		jl3.setLocation(20,550);
		jl3.setFont(new Font("Serif",Font.PLAIN,22));
		jl4.setSize(380, 20);
		jl4.setLocation(420,492);
		jl4.setFont(new Font("Serif",Font.PLAIN,22));
		jl5.setSize(780, 20);
		jl5.setLocation(520,492);
		jl5.setFont(new Font("Serif",Font.PLAIN,22));
		jl6.setSize(980, 20);
		jl6.setLocation(20,640);
		jl6.setFont(new Font("Serif",Font.PLAIN,22));
		jl6.setForeground(Color.RED);
		jl7.setSize(380, 20);
		jl7.setLocation(20,580);
		jl7.setFont(new Font("Serif",Font.PLAIN,22));
		jl8.setSize(380, 20);
		jl8.setLocation(20,610);
		jl8.setFont(new Font("Serif",Font.PLAIN,22));
		tf0.setSize(50,30);
		tf0.setLocation(360, 490);
		tf1.setSize(50,30);
		tf1.setLocation(460, 490);
		jbt.setSize(120, 50);
		jbt.setLocation(530, 585);
		jbt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try{
					double zf=0,pj=0,fc=0,rs=0,zg=0,zd=0;
					int zdc=0,zgc=0;
					int cy=0;
					for(int i=0;i<main.co;){
						if(!jtf[i].getText().equals("")){
							zf+=Double.parseDouble(jtf[i].getText());
							cy+=1;
							if(Double.parseDouble(jtf[i].getText())>zg){
								zg=Double.parseDouble(jtf[i].getText());
								zgc=i+1;
							}
							if(!tf0.getText().equals("")&&!tf1.getText().equals("")&&Double.parseDouble(jtf[i].getText())>Math.min(Double.parseDouble(tf0.getText()), Double.parseDouble(tf1.getText()))&&Double.parseDouble(jtf[i].getText())<Math.max(Double.parseDouble(tf0.getText()), Double.parseDouble(tf1.getText()))){
								rs+=1;
							}
						}
						i+=1;
					}
					pj=zf/cy;
					zd=zg;
					zdc=zgc;
					double ls=0;
					for(int i0=0;i0<main.co;){
						if(!jtf[i0].getText().equals("")){
							ls+=(pj-Double.parseDouble(jtf[i0].getText()))*(pj-Double.parseDouble(jtf[i0].getText()));
							if(Double.parseDouble(jtf[i0].getText())<zd){
								zd=Double.parseDouble(jtf[i0].getText());
								zdc=i0+1;
							}
						}
						i0+=1;
					}
					fc=ls/cy;
					//System.out.println(zf+" "+pj+" "+fc);
					DecimalFormat format = new DecimalFormat("#.00");
					String s0 = format.format(zf);
					jl1.setText("总分:"+s0);
					String s1 = format.format(pj);
					jl3.setText("平均分:"+s1);
					String s2 = format.format(fc);
					jl2.setText("方差:"+s2+"(仅供参考)");
					String s3 = format.format(rs);
					jl5.setText("之间(不包含两数)的人数:"+s3+"("+(Math.round(rs/cy*10000)/100)+"%)");
					String s4 = format.format(zg);
					jl7.setText("最高分:"+s4+"(第"+zgc+")");
					String s5 = format.format(zd);
					jl8.setText("最低分:"+s5+"(第"+zdc+")");
					jl6.setText("未填写的文本框将不计入计算 如果要计入请填写0 本次计算参与人数:"+cy);
				}catch(Exception e){javax.swing.JOptionPane.showMessageDialog(null, "出错,请注意每个输入框是否输入的都是数字。错误代码:"+e);}
			}
		});
		jp.add(jl0);
		jp.add(jl1);
		jp.add(jl2);
		jp.add(jl3);
		jp.add(jl4);
		jp.add(jl5);
		jp.add(jl6);
		jp.add(jl7);
		jp.add(jl8);
		jp.add(tf0);
		jp.add(tf1);
		jp.add(jbt);
		jf.add(jp);
	}
}
