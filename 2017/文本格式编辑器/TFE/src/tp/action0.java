package tp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

public class action0 implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==main.w.open){
			main.w.open();
		}else if(arg0.getSource()==main.w.save){
			main.w.save();
		}else if(arg0.getSource()==main.w.saveas){
			main.w.saveas();
		}
	}
}
