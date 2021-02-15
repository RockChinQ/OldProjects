package powerp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class actevent implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<20;){
			if(e.getSource()==selectg.jbt[i]){
				window.gp.addlabel(Integer.parseInt(selectg.jbt[i].getText()));
			}
			i++;
		}
	}
}
