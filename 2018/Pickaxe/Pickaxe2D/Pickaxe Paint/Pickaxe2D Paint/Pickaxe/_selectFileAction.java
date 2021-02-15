package Pickaxe;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class _selectFileAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource().equals(Boot.guim.sfp.openFile)) {  //打开
			JFileChooser jfc=new JFileChooser();
			jfc.setFileFilter(new PxdFileFilter());
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int state=jfc.showOpenDialog(null);
			if(state==JFileChooser.CANCEL_OPTION)
				return;
			Boot.file=new String(jfc.getSelectedFile().getAbsolutePath());
			Boot.guim.mainWd.setTitle("Pickaxe Paint-"+Boot.file);
			Boot.guim.sfp.setVisible(false);
			Boot.guim.showAllPanel(true);
			Boot.guim.cmdp.exec("echo 打开文件："+Boot.file);
			new EditingManager(Boot.file);
			//new EditingManager(20,20,Color.BLACK);
		}else if(arg0.getSource().equals(Boot.guim.sfp.newFile)) {    //新建
			Boot.guim.sfp.setVisible(false);
			Boot.guim.nfp.setVisible(true);
		}else if(arg0.getSource().equals(Boot.guim.nfp.chooseDir)) {    //选择目录
			JFileChooser jfc=new JFileChooser();
			jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int state=jfc.showOpenDialog(null);
			if(state==JFileChooser.CANCEL_OPTION)
				return;
			Boot.guim.nfp.dir.setText(new String(jfc.getSelectedFile().getAbsolutePath()));
		}else if(arg0.getSource().equals(Boot.guim.nfp.cancel)) {
			Boot.guim.nfp.setVisible(false);
			Boot.guim.sfp.setVisible(true);
		}else if(arg0.getSource().equals(Boot.guim.nfp.presetColor)) {
			switch(Boot.guim.nfp.presetColor.getSelectedItem().toString()) {
			case "黑色":{
				Boot.guim.nfp.bgColorShow.setForeground(Color.black);
				Boot.guim.nfp.bgColorShow.setText("■");
				break;
			}
			case "白色":{
				Boot.guim.nfp.bgColorShow.setForeground(Color.white);
				Boot.guim.nfp.bgColorShow.setText("■");
				break;
			}
			case "自定义":{
				Boot.guim.nfp.bgColorShow.setForeground(Boot.guim.nfp.custom);
				Boot.guim.nfp.bgColorShow.setText("■");
				break;
			}
			case "透明":{
				Boot.guim.nfp.bgColorShow.setForeground(new Color(255,255,255,0));
				Boot.guim.nfp.bgColorShow.setText("□");
				break;
			}
			}
		}else if(arg0.getSource().equals(Boot.guim.nfp.ok)) {         //开始   初始化编辑面板
			if(Boot.guim.nfp.width.getText().equals("")||Boot.guim.nfp.height.getText().equals("")) {
				javax.swing.JOptionPane.showMessageDialog(null, "请输入长宽", "不能创建", JOptionPane.WARNING_MESSAGE);
				return;
			}
			Boot.file=new String(Boot.guim.nfp.dir.getText()+"\\"+Boot.guim.nfp.fileName.getText()+".pxd");
			if(new File(Boot.file).exists()) {
				int i=javax.swing.JOptionPane.showConfirmDialog(null, "此文件已存在，继续将替换此文件。\n是否继续创建？",Boot.file,JOptionPane.OK_CANCEL_OPTION);
				if(i!=0) {
					return;
				}
			}
			Boot.guim.nfp.fileName.setText(null);
			Boot.guim.nfp.setVisible(false);
			Boot.guim.showAllPanel(true);
			Boot.guim.mainWd.setTitle("Pickaxe Paint-"+Boot.file);
			Boot.guim.cmdp.exec("echo 新建文件："+Boot.file);
			new FileRW().write(Boot.file, null, false);
			Boot.guim.clp.changeSettingColor(1);
			Boot.guim.clp.setColor(Boot.guim.nfp.bgColorShow.getForeground());
			Boot.guim.clp.changeSettingColor(0);
			
			new EditingManager(Integer.parseInt(Boot.guim.nfp.width.getText())
					,Integer.parseInt(Boot.guim.nfp.height.getText()),Boot.guim.nfp.bgColorShow.getForeground());
		}
	}
}
