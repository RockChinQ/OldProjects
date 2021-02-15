package tp;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class win {
	gui g=new gui(600,630,0,0,false,"文本格式编辑器",true,false,true);
	JPanel jp=new JPanel();
    JMenu filem = new JMenu(" 文件 "); 
    JMenuItem open = new JMenuItem("打开...     Ctrl+O"); 
    JMenuItem save = new JMenuItem("保存        Ctrl+S"); 
    JMenuItem saveas = new JMenuItem("另存为...  Ctrl+A"); 
    JMenu editm=new JMenu(" 编辑 ");
    
    
    
	JTextArea jta=new JTextArea();
	win(){
		jp.setLayout(null);
		jta.setSize(500, 500);
		jp.add(jta);
		JScrollPane jsp = new JScrollPane(jta); //在文本框上添加滚动条
		jsp.setLocation(15,15);//设置矩形大小.参数依次为(矩形左上角横坐标x,矩形左上角纵坐标y，矩形长度，矩形宽度)
		jsp.setSize(560,540);
		jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//默认的设置是超过文本框才会显示滚动条，以下设置让滚动条一直显示
		jp.add(jsp);
		jta.setEditable(true);
		

        JMenuBar menuBar = new JMenuBar(); 
        g.setJMenuBar(menuBar); 
         
        filem.add(open); 
        filem.add(save); 
        filem.add(saveas); 
        menuBar.add(filem); 
        open.addActionListener(new action0());
        save.addActionListener(new action0());
        saveas.addActionListener(new action0());
        
        jta.addKeyListener(new key0());
        g.addKeyListener(new key0());
		g.add(jp);
		g.setVisible(true);
	}
	void open(){
		String str=getOpenPath();
		File fi=new File(str);
		if(fi.length()>40000000){
			javax.swing.JOptionPane.showMessageDialog(null, "文件过大，可能打开失败！");
		}
		jta.setText("打开失败");
		main.file=new StringBuffer(str);
		jta.setText(new file0().read(str));
		javax.swing.JOptionPane.showMessageDialog(null, "打开成功！");
	}
	void save() {
		if(!new File(main.file.toString()).exists())
			main.file=new StringBuffer(getSavePath());
		new file0().write(main.file.toString(), jta.getText(),false);
	}
	void saveas(){
		new file0().write(getSavePath(), jta.getText(),false);
	}
	String getOpenPath(){
		JFileChooser jfc=new JFileChooser();
		int r = jfc.showOpenDialog(null);
		File f=jfc.getSelectedFile();
		return f.getPath();
	}
	String getSavePath(){
		JFileChooser jfc=new JFileChooser();
		int r = jfc.showSaveDialog(null);
		File f=jfc.getSelectedFile();
		return f.getPath();
	}
}
