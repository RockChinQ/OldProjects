package hookp;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class window {
	static gui g=new gui(750,560,0,0,true,"运行日志",true,false,true);
	static JTextArea jta= new JTextArea();
	JPanel jp=new JPanel();
	window(){
		jp.setLayout(null);
		jta.setSize(120, 60);
		jp.add(jta);
	    
		JScrollPane jsp = new JScrollPane(jta); //在文本框上添加滚动条
		jsp.setLocation(5, 5);//设置矩形大小.参数依次为(矩形左上角横坐标x,矩形左上角纵坐标y，矩形长度，矩形宽度)
		jsp.setSize(735,520);
		jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//默认的设置是超过文本框才会显示滚动条，以下设置让滚动条一直显示
		jp.add(jsp);
		jta.setText("座位表编辑器脚本(.ses) 解释器\nV1.0版解释器 来自:Rock 保留所有权利\n=======================================================================");
		jta.setEditable(false);
		jta.setFont(new Font("",Font.BOLD,17));
		g.add(jp);
		g.setVisible(true);
	}
}
