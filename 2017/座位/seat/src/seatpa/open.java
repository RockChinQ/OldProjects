package seatpa;

import java.io.File;

import javax.swing.JFileChooser;

public class open {
	class nnn{}
	open(){
		try{
		JFileChooser jfc=new JFileChooser();
		int r = jfc.showOpenDialog(null);
		File f=jfc.getSelectedFile();
		String str=f.getPath();
		main.ssb=new StringBuffer(str);
		Thread.sleep(500);
		new editgui();
		}catch(Exception e){
			new file0().write(main.sou+"setting","false 大 17 154 217 234",false);
			javax.swing.JOptionPane.showMessageDialog(null,"抱歉，文件打开失败！\n原因："+e+"\n*以下是几点建议：\n(1)确认你打开的文件是本程序支持的编码格式(.stb)\n(2)重启本程序\n(3)选择的文件可能已损坏，请新建一份\n("+main.ssb+")","座位表编辑器",0,null);
			System.exit(-1);
		}
	}
}
