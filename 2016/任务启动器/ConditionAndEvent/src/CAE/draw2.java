package CAE;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.plaf.nimbus.State;

public class draw2 extends JPanel{
	String s=t1.getMessage();
	public void paint(Graphics g0){
		BufferedImage bi=new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics2D g=bi.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.GRAY);
		g.fillRect(93,214,129,53);
		g.fillRect(330,134,129,53);
		int[] i=new int[]{155,86,155,225};
		int[] i0=new int[]{92,118,145,118};
		g.fillPolygon(i,i0,i.length);
		g.setColor(Color.BLACK);
		((Graphics2D) g).setStroke(new BasicStroke(3));
		g.drawLine(155,13,155,92);
		g.drawLine(18, 118, 86, 118);
		g.drawLine(18, 12, 18, 118);
		g.drawLine(18, 12, 155, 12);
		g.drawLine(225, 118, 276, 118);
		g.drawLine(276, 118, 276, 163);
		g.drawLine(276, 163, 324, 163);
		g.drawLine(155, 145, 155, 209);
		g.drawLine(315, 157, 324, 163);
		g.drawLine(315, 168, 324, 163);
		g.drawLine(150, 200, 155, 209);
		g.drawLine(160, 200, 155, 209);
		g.drawLine(150, 82, 155, 92);
		g.drawLine(160, 82, 155, 92);
		g.setColor(Color.BLACK);
		((Graphics2D) g).setStroke(new BasicStroke(3));
		g.setFont(new Font("",Font.PLAIN,25));
		g.drawString("TASK", 363, 167);
		g.drawString("TASK", 126, 249);
		g.setFont(new Font("",Font.PLAIN,17));
		g.drawString("CONDITION", 115, 125);
		g.setFont(new Font("",Font.PLAIN,28));
		g.drawString("任务启动器"+main.v, 10, 300);
		g.drawString("作者:桂林市奎光学校187班秦骏言",10,330);
		g.drawString("版权所有 侵权必究! !",10, 360);
		g.drawString("联系方式:电话18778380572", 10, 390);
		g.drawString("邮件:1010553892@qq.com", 10, 420);
		g0.drawImage(bi, 0, 0, this);
		File f=new File("file\\图标.jpg");
		if(!f.exists()){
			try {
				((Graphics2D) g).setStroke(new BasicStroke(7));
				g.setColor(Color.BLUE);
				g.setFont(new Font("",Font.CENTER_BASELINE,15));
				g.drawString("任",0, 14);
				ImageIO.write(bi, "jpg", f);
				//String str=javax.swing.JOptionPane.showInputDialog("程序需要创建快捷方式  请输入创建位置(建议输入桌面位置:)");
				//main.w.dow("123.bat", "copy 任务启动器.lnk "+str);
				//Desktop.getDesktop().open(new File("123.bat"));
			} catch (IOException e) {
				javax.swing.JOptionPane.showMessageDialog(null, "出错:"+e);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		g0.drawImage(bi, 0, 0, this);
	}
}
