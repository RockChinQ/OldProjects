package zwbp;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class window {
	JWindow w=new JWindow();
	JPanel p0=new JPanel();
	JButton clo=new JButton(new ImageIcon(main.sou+"close0.jpg"));
	window(){
		w.setSize(600, 500);
		w.setLocation(10, 10);
		//w.setLayout(null);
		//´°¿Ú²Ù×÷
		p0.setLayout(null);
		clo.setSize(50,25);
		clo.setLocation(w.getWidth()-50, 0);
		clo.addMouseListener(new mouse0());
		p0.add(clo);
		
		w.add(p0);
		w.setVisible(true);
	}
	void close(){
		System.exit(0);
	}
}
