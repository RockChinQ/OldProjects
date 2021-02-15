package PictureViewer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

public class action0 implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==main.w.open){
			try{
				String str=getOpenPath();
				String code= new file0().read(str);
				String[] code0=code.split("&");
				int w=Integer.parseInt(code0[0]),h=Integer.parseInt(code0[1]);
				main.w.g.remove(main.w.p2d);
				int gridsize=main.w.jsl.getValue();
				main.w.p2d=new Pixel2D(gridsize,gridsize,w,h,Color.white,true,Color.black,false,null);
				main.w.p2d.setLocation(10, 40);
				main.w.g.add(main.w.p2d);
				//System.out.println(main.w.p2d.panelwidth);
				main.w.p2d.drawByCode(0, 0, null,code);
				main.w.g.setSize(main.w.jsl.getValue()*main.w.p2d.panelwidth+41,main.w.jsl.getValue()*main.w.p2d.panelHeight+111);
				main.w.g.setCenter();
			}catch(Exception e){
				javax.swing.JOptionPane.showMessageDialog(null, "code²»ºÏ·¨!");
			}
		}
	}
	static String getSavePath(){
		JFileChooser jfc=new JFileChooser();
		int r = jfc.showSaveDialog(null);
		File f=jfc.getSelectedFile();
		return f.getPath();
	}
	static String getOpenPath(){
		JFileChooser jfc=new JFileChooser();
		int r = jfc.showOpenDialog(null);
		File f=jfc.getSelectedFile();
		return f.getPath();
	}

}
