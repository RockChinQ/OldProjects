package PictureEditor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

public class action0 implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==main.w.exit){
			System.exit(0);
		}else if(arg0.getSource()==main.w.color){
			main.p.cl=JColorChooser.showDialog(main.w.g, "选取颜色", null);
			main.w.color.setForeground(main.p.cl);
		}else if(arg0.getSource()==main.w.bg){
			main.p.bg=JColorChooser.showDialog(main.w.g, "选取颜色", null);
			main.w.bg.setForeground(main.p.bg);
			main.w.p2d.trans=new Color(255-main.p.bg.getRed(),255-main.p.bg.getGreen(),255-main.p.bg.getBlue());
		}else if(arg0.getSource()==main.w.set_wh){
			main.w.p2d.gridwidth=Integer.parseInt(javax.swing.JOptionPane.showInputDialog(null, "长度"));
			main.w.p2d.gridHeight=Integer.parseInt(javax.swing.JOptionPane.showInputDialog(null, "高度"));
			main.w.p2d.setSize(main.w.p2d.panelwidth*main.w.p2d.gridwidth+1, main.w.p2d.panelHeight*main.w.p2d.gridHeight+1);
			main.w.p2d.repaint();
		}
	}

}
