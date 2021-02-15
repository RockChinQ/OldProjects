package Pickaxe;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class PxdFileFilter extends FileFilter{

	@Override
	public boolean accept(File arg0) {
		if(arg0.isDirectory())
			return true;
		return arg0.getName().endsWith(".pxd");
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "PickaxeÏñËØ»­±à¼­ÎÄµµ(.pxd)";
	}
	
}
