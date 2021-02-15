package tp0;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class editpa extends JPanel{
	JLabel jl0=new JLabel("未选取任何点...");
	JLabel jl1=new JLabel(new ImageIcon("line2.jpg"));
	JTextField jtf0=new JTextField(3);
	editpa(){
		int xu0=920;
		this.setLayout(null);
		jl0.setSize(140,50);
		jl0.setLocation(20, 20);
		this.add(jl0);
		jl1.setSize(2,100);
		jl1.setLocation(230, 0);
		this.add(jl1);
		jtf0.setSize(40,20);
	}
}
