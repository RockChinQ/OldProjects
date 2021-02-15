package PictureEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

public class action2 implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==main.w.save){
			StringBuffer general=new StringBuffer(main.w.p2d.panelwidth+"&"+main.w.p2d.panelHeight+"&");
			for(int a=0;a<main.w.p2d.panelwidth;a++){
				for(int b=0;b<main.w.p2d.panelHeight;b++){
					if(main.w.p2d.picture[a][b]==null){
						general.append("n&");
					}else{
						general.append(main.w.p2d.picture[a][b].getRed()+","+main.w.p2d.picture[a][b].getGreen()+","+main.w.p2d.picture[a][b].getBlue()+"&");
					}
				}
			}
			new file0().write(getSavePath(), general.toString(), false);
		}else if(arg0.getSource()==main.w.open){
			main.w.p2d.resetPicture();
			main.w.p2d.drawByCode(0, 0, null, new file0().read(getOpenPath()));
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
