package 去符号;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class main {
	static gui g=new gui(500,250,0,0,false,"去符号",true,false,true);
	static JButton jbt=new JButton("选择文件并转换");
	static JTextArea jta=new JTextArea();
	static JTextArea jta0=new JTextArea();
	public static void main(String[] args) throws Exception{
		g.setLayout(null);
		jbt.setSize(200, 50);
		jbt.setLocation(10, 10);
		jbt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String file=getOpenPath();
				String str=new file0().read(file);
				StringBuffer t0=new StringBuffer("");
				
				String[] te=str.split(jta.getText());
				int l0=te.length;
				for(int a=0;a<l0;a++)
					t0.append(te[a]+jta0.getText());
				new file0().write(getSavePath(), t0.toString(), false);
			}
		});
		g.add(jbt);
		
		jta.setSize(225, 100);
		jta.setLocation(10, 70);
		jta.setText("这里输入去掉的字符\n(请把这些字去掉再使用)");
		g.add(jta);
		jta0.setSize(225, 100);
		jta0.setLocation(250, 70);
		jta0.setText("这里输入要改成的字符\n(请把这些字去掉再使用)");
		g.add(jta0);
		g.setVisible(true);
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
