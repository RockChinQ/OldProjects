package p0;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class output {
	guicenter gc=new guicenter(window.xl/2-250,window.yl/2-150,500,300,true,false,false,"ÏûÏ¢");
	JPanel jp=new JPanel();
	void message(String s0,String[] s1){
		int i=s1.length;
		JLabel[] jl=new JLabel[i];
		if(i<2){
			JLabel jl1=new JLabel(s1[0]);
			jl1.setFont(new Font("Serif",Font.PLAIN,20));
			jp.add(jl1);
		}
		else{
			for(int i1=0;i1<i;){
				jl[i1].setText(s1[i1]);
				jl[i1].setFont(new Font("Serif",Font.PLAIN,20));
				jp.add(jl[i1]);
				i+=1;
			}
		}
		gc.setTitle(s0);
		gc.add(jp);
	}
}
