package CAE;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class setting {
	static gui gi=new gui(300,130,spawnwindows.xl/2-260,spawnwindows.yl/2-190,false,"程序设置",false);
	JPanel jp=new JPanel();
	JLabel jl0=new JLabel("点击\"退出程序\"时:");
	JComboBox jcb0=new JComboBox();
	JButton jbt0=new JButton("保存");
	JButton jbt1=new JButton("退出");
	setting(){
		gi.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		gi.addWindowListener(new WindowListener(){
			public void windowActivated(WindowEvent e) {}
			public void windowClosed(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {
				gi.setVisible(false);
				spawnwindows.wi.requestFocus();
			}
			public void windowDeactivated(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowOpened(WindowEvent e) {}
		});
		gi.setResizable(false);
		
		jl0.setSize(200,30);
		jl0.setLocation(30,10);
		
		jcb0.setSize(130,30);
		jcb0.setLocation(150,10);
		jcb0.addItem("询问");
		jcb0.addItem("直接退出");
		jcb0.addItem("最小化到托盘");
		
		jbt0.setSize(100,30);
		jbt0.setLocation(30,50);
		jbt0.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					main.w.dow("file\\exit.txt",(String)(jcb0.getSelectedItem()));
					gi.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		jbt1.setSize(100,30);
		jbt1.setLocation(150,50);
		jbt1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					gi.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		jp.setLayout(null);
		jp.add(jl0);
		jp.add(jcb0);
		jp.add(jbt0);
		jp.add(jbt1);
		gi.add(jp);
		gi.requestFocus();
	}
}
