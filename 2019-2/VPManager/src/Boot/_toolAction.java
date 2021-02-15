package Boot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Classes.MyButton;

public class _toolAction implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
		Main.g.tlp.chooseTool((MyButton) arg0.getSource());
	}

}
