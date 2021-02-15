package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Classes.*;
import Main.Main;

public class _toolAction implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
		Main.gui.tlp.toolSelected=(MyButton)arg0.getSource();
		Main.gui.tlp.setAllUnselected();
		Main.gui.tlp.toolSelected.setBackground(new Color(150,150,150));
	}

}
