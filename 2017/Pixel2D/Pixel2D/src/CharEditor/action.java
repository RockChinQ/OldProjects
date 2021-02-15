package CharEditor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class action implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int a=0;a<main.w.bc;a++){
			for(int b=0;b<main.w.bc;b++){
				if(e.getSource()==main.w.jbts[a][b]){
					main.w.jbts[a][b].setBackground(main.w.jbts[a][b].getBackground()==Color.yellow?Color.black:Color.yellow);
				}
			}
		}
	}

}
