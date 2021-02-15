package Pickaxe;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

public class CanvasPanel extends JPanel{
	Color bg=new Color(25,25,25);
	Font bFont=new Font("",Font.PLAIN,15);
	public CanvasPanel(int w, int h) {
		this.setSize(w,h);
		this.setBackground(bg);
		this.setLayout(null);
	}
}
