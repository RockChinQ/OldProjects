package seatpa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class action2 implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
		if(!record.ms.empty()){
			String t=record.ms.pop().toString();
			String[] s=t.split(" ");
			String te;
			if(s[0].equals("corr")){
				te="corr "+s[1]+" "+s[2]+" "+editgui.jbt[Integer.parseInt(s[1])][Integer.parseInt(s[2])].getText();
				editgui.jbt[Integer.parseInt(s[1])][Integer.parseInt(s[2])].setText(s[3]);
				record.re.push(te);
			}else if(s[0].equals("repl")){
				String tt=editgui.jbt[Integer.parseInt(s[1])][Integer.parseInt(s[2])].getText();
				editgui.jbt[Integer.parseInt(s[1])][Integer.parseInt(s[2])].setText(editgui.jbt[Integer.parseInt(s[3])][Integer.parseInt(s[4])].getText());
				editgui.jbt[Integer.parseInt(s[3])][Integer.parseInt(s[4])].setText(tt);
				te=t;
				record.re.push(te);
			}
		}
	}

}
