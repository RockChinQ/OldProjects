package Boot;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ScriptFileFilter extends FileFilter {

	@Override
	public boolean accept(File arg0) {
		// TODO Auto-generated method stub
		if(arg0.isDirectory()||
				arg0.getName().endsWith(".vs"))
			return true;
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "项目脚本文件(.vs)";
	}

}