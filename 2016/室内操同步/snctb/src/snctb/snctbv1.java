package snctb;
import javax.swing.JFrame;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import javax.swing.JLabel;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.util.Calendar;
import java.io.File;
import javax.swing.JPanel;
public class snctbv1{
	static JLabel jl0=new JLabel("");
	static JLabel jl=new JLabel("��ӭʹ��!  ����:187�� �ؿ���");
	static JLabel jl1=new JLabel("");
	static JLabel jl2=new JLabel("");
	JFrame jf=new JFrame("���ڲ�ͬ��");
	public snctbv1() throws Exception{
		final JPanel jp=new JPanel();
		JButton jbt=new JButton("�˳�");
		JButton jbt1=new JButton("����");
		GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle rec=ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
		int xl=(int)rec.getWidth();
		int yl=(int)rec.getHeight();
		jf.setLocation(xl/2-305/2,yl/2-190/2);
		jf.setSize(305,190);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jp.add(jbt1);
		jp.add(jl0);
		jp.add(jl1);
		jp.add(jl);
		jp.add(jl2);
		jp.add(jbt);
		jf.add(jp);
		jbt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jl1.setText("״̬���˳�");
				System.exit(0);
			}
		});
		jbt1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Image img=Toolkit.getDefaultToolkit().createImage("���ڲ�ͬ��.jpg");
				TrayIcon ti=new TrayIcon(img);
				ti.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e){
						if (e.getClickCount() == 1){
							jf.setVisible(true);
							SystemTray.getSystemTray().remove(ti);
						}
					}
				});
				try{
					if(true){
						SystemTray.getSystemTray().add(ti);
					}
					jf.setVisible(false);
				}catch(Exception e1){
					System.out.println("���̳���"+e1);
				}
			}
		});

		System.out.println("�˴��ڿ�����С����");
		File file=new File("F:\\save.txt");
		FileInputStream fis=new FileInputStream(file);
		byte[] data=new byte[(int)file.length()];
		fis.read(data);
		fis.close();
		String msg=new String(new String(data));
		jl0.setText("���ڲ�ͬ��v16.2.28 ");
		jl2.setText("���ߣ�187���ؿ��� "+msg+"��Ȩ���� ��ð�ؾ�");
		jl1.setText("״̬�������Ѽ��أ�");
		check ch=new check();
		jl1.setText("״̬���߳��ѿ�ʼ���У�");
		ch.start();
		for(;;){
			if(true){
				Calendar c=Calendar.getInstance();
				jl.setText("��ǰʱ�䣺"+c.get(Calendar.HOUR_OF_DAY)+"ʱ"+c.get(Calendar.MINUTE)+"��"+c.get(Calendar.SECOND)+"��");
			}
		}
	}
	public static void main(String[] args) throws Exception{
		new snctbv1();
	}
	static void display(String word) throws Exception{
		jl1.setText(word);
	}
}














