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
	gui g=new gui(600,630,0,0,false,"�ı���ʽ�༭��",true,false,true);
	JPanel jp=new JPanel();
    JMenu filem = new JMenu(" �ļ� "); 
    JMenuItem open = new JMenuItem("��...     Ctrl+O"); 
    JMenuItem save = new JMenuItem("����        Ctrl+S"); 
    JMenuItem saveas = new JMenuItem("���Ϊ...  Ctrl+A"); 
    JMenu editm=new JMenu(" �༭ ");
    
    
    
	JTextArea jta=new JTextArea();
	win(){
		jp.setLayout(null);
		jta.setSize(500, 500);
		jp.add(jta);
		JScrollPane jsp = new JScrollPane(jta); //���ı�������ӹ�����
		jsp.setLocation(15,15);//���þ��δ�С.��������Ϊ(�������ϽǺ�����x,�������Ͻ�������y�����γ��ȣ����ο��)
		jsp.setSize(560,540);
		jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//Ĭ�ϵ������ǳ����ı���Ż���ʾ�����������������ù�����һֱ��ʾ
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
			javax.swing.JOptionPane.showMessageDialog(null, "�ļ����󣬿��ܴ�ʧ�ܣ�");
		}
		jta.setText("��ʧ��");
		main.file=new StringBuffer(str);
		jta.setText(new file0().read(str));
		javax.swing.JOptionPane.showMessageDialog(null, "�򿪳ɹ���");
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
