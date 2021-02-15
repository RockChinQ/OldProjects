package elep;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class aceve implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
		for(int i=0;i<8;i++){
			if(arg0.getSource()==hardware.up[i]){
				hardware.up[i].setIcon(hardware.i1);
			}
			if(arg0.getSource()==hardware.down[i]){
				hardware.down[i].setIcon(hardware.j1);
			}
		}
	}
}
