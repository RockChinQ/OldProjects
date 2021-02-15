package powerp;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class gamepanel extends JPanel{
	JButton[][] jbt=new JButton[25][25];
	ImageIcon icon = new ImageIcon(main.sou+"empty.png");
	int i0=0,i1=0;               //ÐÐ£¬ÁÐ
	JLabel tit=new JLabel(new ImageIcon(main.sou+"rw.png"));
	JLabel[] t0=new JLabel[20];
	gamepanel(){
		this.setLayout(null);
		for(int i=0;i<25;){
			for(int ii=0;ii<25;){
				jbt[i][ii]=new JButton(icon);
				jbt[i][ii].setSize(20, 20);
				jbt[i][ii].setLocation(i1, i0);
				jbt[i][ii].addActionListener(new actevent());
				this.add(jbt[i][ii]);
				i1+=20;
				ii++;
			}
			i1=0;
			i0+=20;
			i++;
		}
		tit.setSize(190,55);
		tit.setLocation(530,0);
		this.add(tit);
	}
	void addlabel(int g){
		String s=new file0().read("game\\game"+g+".pog");
		String[] sa=s.split(";");
		int[] swit=new int[sa.length];
	}
}
