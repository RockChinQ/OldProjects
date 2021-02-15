package lybp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class main {
	static gui g0=new gui(500,500,200,200,true,"留言板",true,true);
	static JButton jbt=new JButton("新建");
	static JPanel g=new JPanel();
	public static void main(String[] args) {
		g.setLayout(null);
		if(new file0().read("1.txt")=="1"){
			g0.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
		jbt.setSize(100, 50);
		jbt.setLocation(10, 5);
		g.add(jbt);
		jbt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String s0=javax.swing.JOptionPane.showInputDialog("输入你的留言:");
				if(!s0.equals("")){
					new file0().write("ly.txt",s0+" ",true);
					javax.swing.JOptionPane.showMessageDialog(null, "新建成功！");
				}else{
					javax.swing.JOptionPane.showMessageDialog(null, "必须输入有效内容！");
				}
			}
		});
		String s=new file0().read("ly.txt");
		String[] lys=s.split(" ");
		int len=lys.length;
		JLabel[] jl=new JLabel[len];
		int y=60;
		for(int i=0;i<len;){
			jl[i]=new JLabel(lys[i]);
			jl[i].setSize(1000, 50);
			jl[i].setLocation(10, y);
			jl[i].addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent arg0) {}
				public void mouseEntered(MouseEvent arg0) {}
				public void mouseExited(MouseEvent arg0) {}
				public void mousePressed(MouseEvent arg0) {
					javax.swing.JOptionPane.showMessageDialog(null,((JLabel) arg0.getSource()).getText());
				}
				public void mouseReleased(MouseEvent arg0) {}
				
			});
			g.add(jl[i]);
			i++;
			y=15*i+60;
		}
		g0.add(g);
		g0.setVisible(true);
	}

}
