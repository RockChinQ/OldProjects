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
	static gui gi=new gui(550,400,xl/2-260,yl/2-190,true,"�������",false);
	String s0="��������: �� ",s1="����",s2="������",s3="ʱ",s4="ִ�� ",s5="ʱ��",s6="ȡ�����",
			s7="��������",s8="��ȡ�ļ�",s9="������",s10="������ʾ",s11="�ļ�������ʾ",
			s12="��ȡһ�������",s13="���ļ�",s14="����������ָ��",s15="���ļ�д������";
	
	JLabel jl0=new JLabel("����:��:");
	JLabel jl1=new JLabel("����:");
	JLabel jl2=new JLabel("�������");
	JLabel jl3=new JLabel("�¼�:");
	JLabel jl4=new JLabel("����:    ��");
	JLabel jl5=new JLabel("����Ϊ ��Ҫ��ʾ������");
	JLabel jl6=new JLabel("              ��");
	JLabel jl7=new JLabel("�������");
	JLabel jl8=new JLabel("����:");
	JLabel jl9=new JLabel("");
	JLabel jl10=new JLabel("ע��:ѡ�� \"���̰����ַ�\" ѡ��ʱ\"����\"��ֻ�е�һ���ַ���Ч ���������ڰ��²�����Ч");
	JLabel jl11=new JLabel("          \"ʱ��\"���������ʾΪ:\"ʱ �� ��\"(ÿ��֮���ÿո����)");
	JLabel jl12=new JLabel("          \"ȡ�����\"���0-100��ȡһ�������  ������������������0-100������");
	JLabel jl13=new JLabel("          ����ı��򲢰��س�����ѡ���ļ�·��");
	JButton jbt0=new JButton("ȡ��");
	JButton jbt1=new JButton("���");
	JComboBox jcb0=new JComboBox();
	JComboBox jcb1=new JComboBox();
	JComboBox jcb2=new JComboBox();
	JTextField jtf0=new JTextField(2147483647);
	JTextField jtf1=new JTextField(2147483647);
	JTextField jtf2=new JTextField(2147483647);
	JTextField jtf3=new JTextField(2147483647);
	StringBuffer s;
	boolean b0=true;   //���ڣ�
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
		jcb0.addItem("ʱ��");
		//jcb0.addItem("�����");
		jcb0.addItem("���̰����ַ�");
		jcb0.addItem("���������������");
		jcb0.addItem("��ȡ�ļ�����");
		jcb0.addActionListener(this);
		
		jcb1.setLocation(195,30);
		jcb1.setSize(110,30);
		jcb1.addItem("����");
		jcb1.addItem("������");
		jcb1.addActionListener(this);

		jcb2.setLocation(80,110);
		jcb2.setSize(150,30);
		jcb2.addItem("������ʾ");
		jcb2.addItem("�ļ�������ʾ");
		jcb2.addItem("��ȡһ�������");
		jcb2.addItem("��һ���ļ�");
		jcb2.addItem("������ָ��");
		jcb2.addItem("���ļ�д������");
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
		jl9.setText(s0+jcb0.getSelectedItem()+jcb1.getSelectedItem()+jtf0.getText()+"  ʱ    "+"ִ��:"+jcb2.getSelectedItem()+"  "+jtf2.getText()+"  "+jtf3.getText());
	}
	public void actionPerformed(ActionEvent e) {
		jl9.setText(s0+jcb0.getSelectedItem()+jcb1.getSelectedItem()+jtf0.getText()+"  ʱ   "+"ִ��:"+jcb2.getSelectedItem()+"  "+jtf2.getText()+"  "+jtf3.getText());
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
					if(ss0.equals("ʱ��")){
						String[] s=jtf0.getText().split(" ");
						if(!s[0].equals(s.toString())&&Integer.parseInt(s[0])<=23&&Integer.parseInt(s[1])<=59&&Integer.parseInt(s[2])<=59&&
								Integer.parseInt(s[0])>=0&&Integer.parseInt(s[1])>=0&&Integer.parseInt(s[2])>=0){
							b=true;
						}
					}else if(ss0.equals("��ȡ�ļ�����")){
						File f=new File(jtf1.getText());
						if(f.exists()){
							b=true;
						}
					}else if(ss0.equals("�����")){
						if(Integer.parseInt(jtf0.getText())<=100&&Integer.parseInt(jtf0.getText())>=0){
							b=true;
						}
					}else{
						b=true;
					}
				}
				if(b){
					if(!((String)jcb2.getSelectedItem()).equals("��ȡһ�������")&&!((String)jcb2.getSelectedItem()).equals("���ļ�д������")){
						if(!((String)jtf2.getText()).equals(s.toString())){
							n=true;
						}
					}else if(((String)jcb2.getSelectedItem()).equals("���ļ�д������")){
						if(!((String)jtf2.getText()).equals(s.toString())&&!((String)jtf3.getText()).equals(s.toString())){
							n=true;
						}
					}else if(((String)jcb2.getSelectedItem()).equals("��ȡһ�������")){
						n=true;
					}
				}else{
					javax.swing.JOptionPane.showMessageDialog(null,"���������Ϊ��  ����ȷ��������!");
				}
				if(n&&check.c<10){
					int i=javax.swing.JOptionPane.showConfirmDialog(null, "��ȷ��Ҫ��Ӵ��¼���?");
					if(i==0){
						boolean o=((String)jcb1.getSelectedItem()).equals("����")?true:false;
						main.c.add(ss0,o,jtf0.getText(),jtf1.getText(),"",(String)jcb2.getSelectedItem(),jtf2.getText(),jtf3.getText(),s0+jcb0.getSelectedItem()+jcb1.getSelectedItem()+jtf0.getText()+"  ʱ   "+"ִ��:"+jcb2.getSelectedItem()+"  "+jtf2.getText()+"  "+jtf3.getText());
						spawnwindows.b1=true;
						gi.dispose();
					}
				}else if(b&&!n){
					javax.swing.JOptionPane.showMessageDialog(null,"���������Ϊ��  ����ȷ����Ҫִ�е�����!");
				}else if(n&&check.c>=10){
					javax.swing.JOptionPane.showMessageDialog(null,"���ʧ��!��ӵ������ܳ���10��!");
				}
			}catch(Exception e1){
				javax.swing.JOptionPane.showMessageDialog(null,"���������������Ϊ��  ����ȷ����!");
			}
		}
		if(e.getSource()==jcb0){
			String s=(String)jcb0.getSelectedItem();
			switch(s){
			case "��ȡ�ļ�����":
				jl2.setText("����Ϊ ��ȡ�ļ�·��");
				break;
			default:
				jl2.setText("�������");
			}
		}
		if(e.getSource()==jcb1){
			
		}
		if(e.getSource()==jcb2){
			String s=(String)jcb2.getSelectedItem();
			
			switch(s){
			case "������ʾ":
				jl5.setText("����Ϊ ��Ҫ��ʾ������");
				jl7.setText("�������");
				break;
			case "�ļ�������ʾ":
				jl5.setText("����Ϊ �ļ�·��");
				jl7.setText("�������");
				break;
			case "��һ���ļ�":
				jl5.setText("����Ϊ �ļ�·��");
				jl7.setText("�������");
				break;
			case "������ָ��":
				jl5.setText("����Ϊ ��Ҫ���������");
				jl7.setText("�������");
				break;
			case "���ļ�д������":
				jl5.setText("����Ϊ �ļ�·��");
				jl7.setText("����Ϊ ����");
				break;
			default:
				jl5.setText("�������");
				jl7.setText("�������");
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
