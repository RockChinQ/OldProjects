package seatpa;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class action4 implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
		try{
			Desktop.getDesktop().open(new File(main.sou+"help.html"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
