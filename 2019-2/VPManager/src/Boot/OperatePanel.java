package Boot;

import java.awt.Color;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Classes.FileRW;
import Classes.MyButton;
import Classes.MyLabel;

public class OperatePanel extends JPanel{
	MyLabel bg,drag;
	MyButton newFile,openFile,saveFile,export;
	MyButton pgmInfo;
	OperatePanel(){
		this.setLayout(null);
		this.setSize(155,67);
		this.setBackground(new Color(190,190,190));
		
		drag=new MyLabel(new ImageIcon("Files\\Image\\drag.png")).setSizex(50, 10)
				.setLocationx(4, 0);
		drag.addMouseListener(new _dragOrp());
		drag.addMouseMotionListener(new _dragOrp());
		this.add(drag);
		
		newFile=new MyButton(new ImageIcon("Files\\Image\\newfile.png")).setSizex(32, 32).setLocationx(10, 20);
		openFile=new MyButton(new ImageIcon("Files\\Image\\openfile.png")).setSizex(32,32).setLocationx(43,20);
		saveFile=new MyButton(new ImageIcon("Files\\Image\\save.png")).setSizex(32, 32).setLocationx(76,20);
		export=new MyButton(new ImageIcon("Files\\Image\\export.png")).setSizex(32, 32).setLocationx(109,20);
		newFile.addActionListener(new _operateAction());
		openFile.addActionListener(new _operateAction());
		saveFile.addActionListener(new _operateAction());
		export.addActionListener(new _operateAction());
		this.add(newFile);
		this.add(openFile);
		this.add(saveFile);
		this.add(export);
		
		bg=new MyLabel(new ImageIcon("Files\\Image\\orpbg.png"))
				.setSizex(this.getWidth(), this.getHeight()).setLocationx(0, 0);
		this.add(bg);
	}
	public void saveFileTo(String path) {
		int oldZoom=Main.g.ccp.zoom;
		Main.g.ccp.setZoom(1);
		StringBuffer fileStr=new StringBuffer();
		fileStr.append(_canvasMouse.vnum+";"+_canvasMouse.anum+";"+_canvasMouse.tnum+":::");
		for(int i=0;i<Main.g.cvp.eles.size();i++) {
			Element e=Main.g.cvp.eles.get(i);
			fileStr.append((e.name==null?"":e.name)+";:;")
			.append((e.describe==null?"":e.describe)+";:;")
			.append((e.label==null?"":e.label)+";:;")
			.append(e.num+";:;")
			.append(e.type+";:;")
			.append(e.marker+";:;")
			.append(e.location.x+","+e.location.y+";:;")
			.append(e.image.size()+":");
			for(int j=0;j<e.image.size();j++) {
				fileStr.append(new File(e.image.get(j).toString()).getName()+":");
			}
			fileStr.append(";:;");
			fileStr.append(";e;");
		}
		new FileRW().write(path, fileStr.toString(), false);
		Main.change=false;
		Main.g.ccp.setZoom(oldZoom);
	}
}
