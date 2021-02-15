package CAE;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import CAE.spawnwindows;

class addevent implements ActionListener{
	public static GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
	public static Rectangle rec=ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
	public static int xl=(int)rec.getWidth();
	public static int yl=(int)rec.getHeight();
	static gui gi=new gui(550,400,xl/2-260,yl/2-190,true,"添加任务",false);
	String s0="文字描述: 当 ",s1="等于",s2="不等于",s3="时",s4="执行 ",s5="时间",s6="取随机数",
			s7="键盘输入",s8="读取文件",s9="的内容",s10="文字提示",s11="文件内容提示",
			s12="获取一个随机数",s13="打开文件",s14="输入命令行指令",s15="向文件写入内容";
	
	JLabel jl0=new JLabel("条件:当:");
	JLabel jl1=new JLabel("参数:");
	JLabel jl2=new JLabel("无需参数");
	JLabel jl3=new JLabel("事件:");
	JLabel jl4=new JLabel("参数:    ⒈");
	JLabel jl5=new JLabel("参数为 需要提示的文字");
	JLabel jl6=new JLabel("              ⒉");
	JLabel jl7=new JLabel("无需参数");
	JLabel jl8=new JLabel("数据:");
	JLabel jl9=new JLabel("");
	JLabel jl10=new JLabel("注意:选择 \"键盘按下字符\" 选项时\"数据\"中只有第一个字符有效 且在主窗口按下才能有效");
	JLabel jl11=new JLabel("          \"时间\"的数据请表示为:\"时 分 秒\"(每个之间用空格隔开)");
	JLabel jl12=new JLabel("          \"取随机数\"会从0-100中取一个随机数  所以请在数据中输入0-100的数字");
	JLabel jl13=new JLabel("          点击文本框并按回车可以选择文件路径");
	JButton jbt0=new JButton("取消");
	JButton jbt1=new JButton("添加");
	JComboBox jcb0=new JComboBox();
	JComboBox jcb1=new JComboBox();
	JComboBox jcb2=new JComboBox();
	JTextField jtf0=new JTextField(2147483647);
	JTextField jtf1=new JTextField(2147483647);
	JTextField jtf2=new JTextField(2147483647);
	JTextField jtf3=new JTextField(2147483647);
	StringBuffer s;
	boolean b0=true;   //等于？
	addevent(){
		gi.setSize(550,400);
		gi.setResizable(false);
		gi.setVisible(true);
		gi.addWindowListener(new WindowListener(){
			public void windowActivated(WindowEvent arg0) {}
			public void windowClosed(WindowEvent arg0) {}
			public void windowClosing(WindowEvent arg0) {
				spawnwindows.b1=true;
				gi.dispose();
			}
			public void windowDeactivated(WindowEvent arg0) {}
			public void windowDeiconified(WindowEvent arg0) {}
			public void windowIconified(WindowEvent arg0) {}
			public void windowOpened(WindowEvent arg0) {}
			
		});
		final JPanel jp=new JPanel();
		jp.setLayout(null);
		
		jl0.setLocation(20,30);
		jl0.setSize(70,30);
		
		jl1.setLocation(20,70);
		jl1.setSize(70,30);
		
		jl2.setLocation(310,70);
		jl2.setSize(400,30);

		jl3.setLocation(20,110);
		jl3.setSize(70,30);
		
		jl4.setLocation(20,150);
		jl4.setSize(70,30);

		jl5.setLocation(310,150);
		jl5.setSize(170,30);

		jl6.setLocation(20,190);
		jl6.setSize(70,30);

		jl7.setLocation(310,190);
		jl7.setSize(170,30);

		jl8.setLocation(310,30);
		jl8.setSize(170,30);

		jl9.setLocation(20,230);
		jl9.setSize(550,30);

		jl10.setLocation(20,260);
		jl10.setSize(550,30);

		jl11.setLocation(20,275);
		jl11.setSize(550,30);

		jl12.setLocation(20,290);
		jl12.setSize(550,30);
		
		jl13.setLocation(20,305);
		jl13.setSize(550,30);
		
		jbt0.setLocation(105,330);
		jbt0.setSize(80,30);
		jbt0.addActionListener(this);
		
		jbt1.setLocation(15,330);
		jbt1.setSize(80,30);
		jbt1.addActionListener(this);
		
		jcb0.setLocation(80,30);
		jcb0.setSize(110,30);
		jcb0.addItem("时间");
		//jcb0.addItem("随机数");
		jcb0.addItem("键盘按下字符");
		jcb0.addItem("弹出输入框输内容");
		jcb0.addItem("读取文件内容");
		jcb0.addActionListener(this);
		
		jcb1.setLocation(195,30);
		jcb1.setSize(110,30);
		jcb1.addItem("等于");
		jcb1.addItem("不等于");
		jcb1.addActionListener(this);

		jcb2.setLocation(80,110);
		jcb2.setSize(150,30);
		jcb2.addItem("文字提示");
		jcb2.addItem("文件内容提示");
		jcb2.addItem("获取一个随机数");
		jcb2.addItem("打开一个文件");
		jcb2.addItem("命令行指令");
		jcb2.addItem("向文件写入内容");
		jcb2.addActionListener(this);
		
		jtf0.setLocation(350,30);
		jtf0.setSize(180,30);
		jtf0.addActionListener(this);
		
		jtf1.setLocation(80,70);
		jtf1.setSize(220,30);
		jtf1.addActionListener(this);

		jtf2.setLocation(80,150);
		jtf2.setSize(220,30);
		jtf2.addActionListener(this);

		jtf3.setLocation(80,190);
		jtf3.setSize(220,30);
		jtf3.addActionListener(this);
		
		jp.add(jl0);
		jp.add(jl1);
		jp.add(jl2);
		jp.add(jl3);
		jp.add(jl4);
		jp.add(jl5);
		jp.add(jl6);
		jp.add(jl7);
		jp.add(jl8);
		jp.add(jl9);
		jp.add(jl10);
		jp.add(jl11);
		jp.add(jl12);
		jp.add(jl13);
		jp.add(jbt0);
		jp.add(jbt1);
		jp.add(jcb0);
		jp.add(jcb1);
		jp.add(jcb2);
		jp.add(jtf0);
		jp.add(jtf1);
		jp.add(jtf2);
		jp.add(jtf3);
		gi.add(jp);
		s=new StringBuffer(jtf0.getText());
		jl9.setText(s0+jcb0.getSelectedItem()+jcb1.getSelectedItem()+jtf0.getText()+"  时    "+"执行:"+jcb2.getSelectedItem()+"  "+jtf2.getText()+"  "+jtf3.getText());
	}
	public void actionPerformed(ActionEvent e) {
		jl9.setText(s0+jcb0.getSelectedItem()+jcb1.getSelectedItem()+jtf0.getText()+"  时   "+"执行:"+jcb2.getSelectedItem()+"  "+jtf2.getText()+"  "+jtf3.getText());
		if(e.getSource()==jbt0){
			gi.dispose();
			spawnwindows.b1=true;
		}
		if(e.getSource()==jbt1){
			try{
				boolean b=false;
				boolean n=false;
				String ss0=(String)jcb0.getSelectedItem();
				if(!jtf0.getText().equals(s.toString())){
					if(ss0.equals("时间")){
						String[] s=jtf0.getText().split(" ");
						if(!s[0].equals(s.toString())&&Integer.parseInt(s[0])<=23&&Integer.parseInt(s[1])<=59&&Integer.parseInt(s[2])<=59&&
								Integer.parseInt(s[0])>=0&&Integer.parseInt(s[1])>=0&&Integer.parseInt(s[2])>=0){
							b=true;
						}
					}else if(ss0.equals("读取文件内容")){
						File f=new File(jtf1.getText());
						if(f.exists()){
							b=true;
						}
					}else if(ss0.equals("随机数")){
						if(Integer.parseInt(jtf0.getText())<=100&&Integer.parseInt(jtf0.getText())>=0){
							b=true;
						}
					}else{
						b=true;
					}
				}
				if(b){
					if(!((String)jcb2.getSelectedItem()).equals("获取一个随机数")&&!((String)jcb2.getSelectedItem()).equals("向文件写入内容")){
						if(!((String)jtf2.getText()).equals(s.toString())){
							n=true;
						}
					}else if(((String)jcb2.getSelectedItem()).equals("向文件写入内容")){
						if(!((String)jtf2.getText()).equals(s.toString())&&!((String)jtf3.getText()).equals(s.toString())){
							n=true;
						}
					}else if(((String)jcb2.getSelectedItem()).equals("获取一个随机数")){
						n=true;
					}
				}else{
					javax.swing.JOptionPane.showMessageDialog(null,"条件有误或为空  请正确输入条件!");
				}
				if(n&&check.c<10){
					int i=javax.swing.JOptionPane.showConfirmDialog(null, "你确定要添加此事件吗?");
					if(i==0){
						boolean o=((String)jcb1.getSelectedItem()).equals("等于")?true:false;
						main.c.add(ss0,o,jtf0.getText(),jtf1.getText(),"",(String)jcb2.getSelectedItem(),jtf2.getText(),jtf3.getText(),s0+jcb0.getSelectedItem()+jcb1.getSelectedItem()+jtf0.getText()+"  时   "+"执行:"+jcb2.getSelectedItem()+"  "+jtf2.getText()+"  "+jtf3.getText());
						spawnwindows.b1=true;
						gi.dispose();
					}
				}else if(b&&!n){
					javax.swing.JOptionPane.showMessageDialog(null,"任务有误或为空  请正确输入要执行的任务!");
				}else if(n&&check.c>=10){
					javax.swing.JOptionPane.showMessageDialog(null,"添加失败!添加的任务不能超过10个!");
				}
			}catch(Exception e1){
				javax.swing.JOptionPane.showMessageDialog(null,"条件或任务有误或为空  请正确输入!");
			}
		}
		if(e.getSource()==jcb0){
			String s=(String)jcb0.getSelectedItem();
			switch(s){
			case "读取文件内容":
				jl2.setText("参数为 读取文件路径");
				break;
			default:
				jl2.setText("无需参数");
			}
		}
		if(e.getSource()==jcb1){
			
		}
		if(e.getSource()==jcb2){
			String s=(String)jcb2.getSelectedItem();
			
			switch(s){
			case "文字提示":
				jl5.setText("参数为 需要提示的文字");
				jl7.setText("无需参数");
				break;
			case "文件内容提示":
				jl5.setText("参数为 文件路径");
				jl7.setText("无需参数");
				break;
			case "打开一个文件":
				jl5.setText("参数为 文件路径");
				jl7.setText("无需参数");
				break;
			case "命令行指令":
				jl5.setText("参数为 需要输入的命令");
				jl7.setText("无需参数");
				break;
			case "向文件写入内容":
				jl5.setText("参数为 文件路径");
				jl7.setText("参数为 内容");
				break;
			default:
				jl5.setText("无需参数");
				jl7.setText("无需参数");
			}
		}
		if(e.getSource()==jtf1||e.getSource()==jtf2){
			JFileChooser jfc=new JFileChooser();
			int r = jfc.showOpenDialog(null);
			File f=jfc.getSelectedFile();
			String str=f.getPath();
			((JTextComponent)e.getSource()).setText(str);
		}
	}
}
