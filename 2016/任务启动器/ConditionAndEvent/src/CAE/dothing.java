package CAE;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class dothing {
	String s=t1.getMessage();
	dothing(){
		
	}
	void run(String s0,String s1,String s2) throws Exception{
		if(s0.equals("������ʾ")){
			javax.swing.JOptionPane.showMessageDialog(null,s1);
			return;
		}else if(s0.equals("�ļ�������ʾ")){
			File file=new File(s1.toString());
			FileReader fr = null;
			try {
				fr = new FileReader(file);
			} catch (Exception e) {
				javax.swing.JOptionPane.showMessageDialog(null,"����:"+e+" �����˳�!");
				System.exit(0);
			}
			BufferedReader br=new BufferedReader(fr);
			StringBuffer sb=new StringBuffer("");
			while(true){
				String str = null;
				try {
					str = br.readLine();
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null,"����:"+e+" �����˳�!");
					System.exit(0);
				}
				if(str==null){
					break;
				}
				sb=new StringBuffer(sb+str);
			}
			javax.swing.JOptionPane.showMessageDialog(null, sb);
		}else if(s0.equals("��ȡһ�������")){
			int i=(int)(Math.random()*100);
			javax.swing.JOptionPane.showMessageDialog(null, "��ȡ���������:"+i);
		}else if(s0.equals("��һ���ļ�")){
			//Desktop.getDesktop().open(new File(s1));
			main.w.dow("file\\cmdfile"+main.in+".bat","\""+s1+"\"");
			Desktop.getDesktop().open(new File("file\\cmdfile"+main.in+".bat"));
			main.w.del("file\\cmdfile"+main.in+".bat");
			main.in+=1;
		}else if(s0.equals("������ָ��")){
			main.w.dow("file\\cmdfile"+main.in+".bat",s1);
			Desktop.getDesktop().open(new File("file\\cmdfile"+main.in+".bat"));
			main.w.del("file\\cmdfile"+main.in+".bat");
			main.in+=1;
			//main.w.del("file\\cmdfile.bat");
		}else if(s0.equals("���ļ�д������")){
			main.w.dow(s1,s2);
		}else if(s0.equals("�ر�ĳ������")){
			main.w.dow("file\\cmdfile"+main.in+".bat","taskkill /im "+s1+" /t");
			Desktop.getDesktop().open(new File("file\\cmdfile"+main.in+".bat"));
			main.w.del("file\\cmdfile"+main.in+".bat");
			main.in+=1;
		}else if(s0.equals("�ػ�")){
			main.w.dow("file\\cmdfile"+main.in+".bat","shutdown -p");
			Desktop.getDesktop().open(new File("file\\cmdfile"+main.in+".bat"));
			main.w.del("file\\cmdfile"+main.in+".bat");
			main.in+=1;
		}
	}
}
