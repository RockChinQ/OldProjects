package CharEditor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class window {
	
	JButton[][] jbts;
	int bc;
	window(){
		bc=Integer.parseInt(javax.swing.JOptionPane.showInputDialog("±ß³¤"));
		jbts=new JButton[bc][bc];
		JFrame jf=new JFrame();
		jf.setSize(300, 300);
		jf.setLocation(200, 200);
		jf.setTitle("Char Maker");
		jf.setLayout(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton save=new JButton("save");
		save.setSize(100,30);
		save.setLocation(10,10);
		save.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuffer sb=new StringBuffer();
				for(int a=0;a<bc;a++){
					for(int b=0;b<bc;b++){
						sb.append(main.w.jbts[b][a].getBackground()==Color.yellow?"1":"0");
					}
				}
				new file0().write("output.txt",javax.swing.JOptionPane.showInputDialog("¸Ã×Ö·û£º")+":"+sb.toString()+"#;", true);
			}
			
		});
		jf.add(save);
		int a=0,b=0;
		for(a=0;a<bc;a++){
			for(b=0;b<bc;b++){
				jbts[a][b]=new JButton();
				jbts[a][b].setSize(20,20);
				jbts[a][b].setLocation(a*20+10, b*20+50);
				jbts[a][b].addActionListener(new action());
				jbts[a][b].setBackground(Color.black);
				jf.add(jbts[a][b]);
			}
		}
		jf.setSize(a*20+50, b*20+100);
		jf.setVisible(true);
	}
}
