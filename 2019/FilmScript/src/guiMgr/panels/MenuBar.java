package guiMgr.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import boot.Main;
import log.Log;

public class MenuBar extends JMenuBar implements ActionListener {
	JMenu file=new JMenu("文件");
	JMenuItem file_new=new JMenuItem("新建项目")
			,file_save=new JMenuItem("保存")
			,file_saveas=new JMenuItem("另存为")
			,file_open=new JMenuItem("打开");
	
	public MenuBar() {
		
		file.add(file_new);
		file_new.addActionListener(this);
		file.add(file_save);
		file_save.addActionListener(this);
		//file.add(file_saveas);
		file_saveas.addActionListener(this);
		file.add(file_open);
		file_open.addActionListener(this);
		this.add(file);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==file_new) {
			if(Main.cfd.canClear()) {
				if(Main.cfd.askForProjName()) {
					Main.cfd.reset();
					Main.cfd.newProj();
					Log.record("New project."+Main.cfd.fileName);
					Main.showTip("项目创建完成", Main.MESSAGE);
				}
			}
		}else if(arg0.getSource()==file_save) {
			Main.cfd.pack();
		}else if(arg0.getSource()==file_open) {
			Main.cfd.askForFileAndOpen();
		}
	}
}
