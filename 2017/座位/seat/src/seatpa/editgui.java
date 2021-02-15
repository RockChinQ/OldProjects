package seatpa;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class editgui{
	class nnn{}
	public static GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
	public static Rectangle rec=ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
	public static int xl=(int)rec.getWidth();
	public static int yl=(int)rec.getHeight();
	static String[] s=new file0().read(main.ssb.toString()).split("&");
	static String filen=main.ssb.toString();
	static JPanel jp=new JPanel();
	static int l0,l1;
	static JButton[][] jbt;
	static boolean[][] isseat;
	static gui g;
	static JLabel bline=new JLabel(new ImageIcon(main.sou+"line.jpg"));
	static JButton saveb=new JButton(new ImageIcon(main.sou+"save.jpg"));
	static JButton crpi=new JButton("座位表图片");
	static StringBuffer[][] name;
	static boolean corr=false;
	static int ax=-1,bx=-1;
	static JButton sp=new JButton(new ImageIcon(main.sou+"setting.jpg"));
	static JTextArea jta= new JTextArea();
	static JButton undo=new JButton(new ImageIcon(main.sou+"undo2.jpg"));
	static JButton redo=new JButton(new ImageIcon(main.sou+"redo2.jpg"));
	static JButton help=new JButton(new ImageIcon(main.sou+"help.jpg"));
	editgui(){
		main.st=true;
		l0=Integer.parseInt(s[0]);
		l1=Integer.parseInt(s[1]);
		jbt=new JButton[l0][l1];
		isseat=new boolean[l0][l1];
		g=new gui(l0*90+15<550?550:l0*90+15,l1*53+80,0,0,true,"SeatEdit",true,false,true);
		name=new StringBuffer[l0][l1];
		/*javax.swing.plaf.metal.MetalLookAndFeel
		javax.swing.plaf.nimbus.NimbusLookAndFeel
		com.sun.java.swing.plaf.motif.MotifLookAndFeel
		com.sun.java.swing.plaf.windows.WindowsLookAndFeel
		com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel
		 */
		try{
			g.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			g.addWindowListener(new windowli());
			jp.setBackground(new Color(Integer.parseInt(main.s[3]),Integer.parseInt(main.s[4]),Integer.parseInt(main.s[5])));
			//jp.setBackground(new Color(191,210,242));
			g.setTitle("座位表-"+(new file0().read(main.sou+"openfile")));
			for(int a=0,c=0;a<l1;a++){
				for(int b=0;b<l0;b++){
					isseat[b][a]=Boolean.parseBoolean(s[c+2]);
					c++;
				}
			}
			int zh=0;
			if(main.s[1].equals("大"))
				zh=14;
			if(main.s[1].equals("中"))
				zh=12;
			if(main.s[1].equals("小"))
				zh=9;
			jp.setLayout(null);
			for(int a=0,c=0;a<l0;a++){
				for(int b=0;b<l1;b++){
					if(isseat[a][b]){
						name[a][b]=new StringBuffer(s[l1*l0+2+c]);
						jbt[a][b]=new JButton(name[a][b].toString());
						jbt[a][b].setSize(90,40);
						jbt[a][b].setLocation(a*90+5, b*45+10);
						jbt[a][b].addMouseListener(new actionli());
						jbt[a][b].setFont(new Font("",Font.PLAIN,zh));
						jp.add(jbt[a][b]);
						c++;
					}
				} 
			}
			int ly=l1*50+10;
			bline.setSize(l0*90+15, 3);
			bline.setLocation(0, ly-l1*3);
			jp.add(bline);
			
			saveb.setSize(90, 60);
			saveb.setLocation(5,ly-l1*3+10);
			jp.add(saveb);
			saveb.addActionListener(new actionli0());

			sp.setSize(90, 60);
			sp.setLocation(105,ly-l1*3+10);
			jp.add(sp);
			sp.addActionListener(new actionli0());
			
			crpi.setSize(120, 60);
			crpi.setLocation(205,ly-l1*3+10);
			crpi.setFont(new Font("",Font.PLAIN,zh));
			jp.add(crpi);
			crpi.addActionListener(new actionli0());
			
			undo.setSize(35, 35);
			undo.setLocation(555,ly-l1*3+10);
			undo.addActionListener(new action2());
			jp.add(undo);

			redo.setSize(35, 35);
			redo.setLocation(595,ly-l1*3+10);
			redo.addActionListener(new action3());
			jp.add(redo);

			help.setSize(35, 35);
			help.setLocation(635,ly-l1*3+10);
			help.addActionListener(new action4());
			jp.add(help);
			
			jta.setSize(120, 60);
			//jta.setLocation(370, ly-l1*3+10);
			jp.add(jta);
		   
			JScrollPane jsp = new JScrollPane(jta); //在文本框上添加滚动条
			jsp.setLocation(340, ly-l1*3+10);//设置矩形大小.参数依次为(矩形左上角横坐标x,矩形左上角纵坐标y，矩形长度，矩形宽度)
			jsp.setSize(200,60);
			jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//默认的设置是超过文本框才会显示滚动条，以下设置让滚动条一直显示
			jp.add(jsp);
			jta.setEditable(false);
			jta.setText("=====程序日志=====");
			new log().addlog("打开文件:\n"+filen);
			
			g.add(jp);
		}catch(NullPointerException e){
			//javax.swing.JOptionPane.showConfirmDialog(null, "Error:"+e);
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
