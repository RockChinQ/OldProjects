package CAE;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class dothing {
	dothing(){
		
	}
	void run(String s0,String s1,String s2) throws Exception{
		if(s0.equals("文字提示")){
			javax.swing.JOptionPane.showMessageDialog(null,s1);
			return;
		}else if(s0.equals("文件内容提示")){
			File file=new File(s1.toString());
			FileReader fr = null;
			try {
				fr = new FileReader(file);
			} catch (Exception e) {
				javax.swing.JOptionPane.showMessageDialog(null,"出错:"+e+" 程序退出!");
				System.exit(0);
			}
			BufferedReader br=new BufferedReader(fr);
			StringBuffer sb=new StringBuffer("");
			while(true){
				String str = null;
				try {
					str = br.readLine();
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null,"出错:"+e+" 程序退出!");
					System.exit(0);
				}
				if(str==null){
					break;
				}
				sb=new StringBuffer(sb+str);
			}
			javax.swing.JOptionPane.showMessageDialog(null, sb);
		}else if(s0.equals("获取一个随机数")){
			int i=(int)(Math.random()*100);
			javax.swing.JOptionPane.showMessageDialog(null, "获取到的随机数:"+i);
		}else if(s0.equals("打开一个文件")){
			//Desktop.getDesktop().open(new File(s1));
			main.w.dow("cmdfile1.bat","\""+s1+"\"");
			Desktop.getDesktop().open(new File("cmdfile1.bat"));
		}else if(s0.equals("命令行指令")){
			main.w.dow("cmdfile.bat",s1);
			Desktop.getDesktop().open(new File("cmdfile.bat"));
			//main.w.del("cmdfile.bat");
		}else if(s0.equals("向文件写入内容")){
			main.w.dow(s1,s2);
		}
	}
}
