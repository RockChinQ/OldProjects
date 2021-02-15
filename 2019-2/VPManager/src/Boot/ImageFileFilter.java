package Boot;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ImageFileFilter extends FileFilter {

	@Override
	public boolean accept(File arg0) {
		// TODO Auto-generated method stub
		if(arg0.isDirectory()||
				arg0.getName().endsWith(".png")
				||arg0.getName().endsWith(".bmp")
				||arg0.getName().endsWith(".jpg"))
			return true;
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "支持的图片文件(.bmp,.png,.jpg)";
	}

}
