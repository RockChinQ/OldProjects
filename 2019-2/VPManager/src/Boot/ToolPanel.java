package Boot;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import Classes.*;

public class ToolPanel extends JPanel{
	MyLabel drag;
	MyButton pointer,addv,addt,adda,link,del;
	MyButton toolse;
	
	Color cbg=new Color(255,255,255);
	ToolPanel(){
		this.setBackground(new Color(190,190,190));
		this.setLayout(null);
		this.setSize(50,250);
		
		drag=new MyLabel(new ImageIcon("Files\\Image\\drag.png")).setSizex(50, 10)
				.setLocationx(0, 0);
		drag.addMouseListener(new _dragMouse());
		drag.addMouseMotionListener(new _dragMouse());
		this.add(drag);

		pointer=new MyButton(new ImageIcon("Files\\Image\\pointer.png"))
				.setSizex(44,44).setLocationx(4, 11).setBackgroundx(cbg);
		pointer.addActionListener(new _toolAction());
		this.add(pointer);
		link=new MyButton(new ImageIcon("Files\\Image\\link.png"))
				.setSizex(44,44).setLocationx(4, 56).setBackgroundx(cbg);
		link.addActionListener(new _toolAction());
		//this.add(link);
		addv=new MyButton(new ImageIcon("Files\\Image\\addv.png"))
				.setSizex(44,44).setLocationx(4, 101).setBackgroundx(cbg);
		addv.addActionListener(new _toolAction());
		this.add(addv);
		adda=new MyButton(new ImageIcon("Files\\Image\\adda.png"))
				.setSizex(44,44).setLocationx(4, 146).setBackgroundx(cbg);
		adda.addActionListener(new _toolAction());
		this.add(adda);
		addt=new MyButton(new ImageIcon("Files\\Image\\addt.png"))
				.setSizex(44,44).setLocationx(4, 191).setBackgroundx(cbg);
		addt.addActionListener(new _toolAction());
		this.add(addt);
		del=new MyButton(new ImageIcon("Files\\Image\\del.png"))
				.setSizex(44,44).setLocationx(4, 56).setBackgroundx(cbg);
		del.addActionListener(new _toolAction());
		this.add(del);
		
		this.toolse=pointer;
		chooseTool(pointer);
	}
	void chooseTool(MyButton chosen) {
		this.toolse.setBackground(new Color(255,255,255));
		this.toolse=chosen;
		this.toolse.setBackground(new Color(50,50,50));
	}
}
