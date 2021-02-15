package tp2;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class action0 implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int a=javax.swing.JOptionPane.showConfirmDialog(null,"保存到\\theorem?\n此定理为:\n如果"+main.w.if_jta0.getText()+",\n那么"+main.w.then_jta0.getText(), "保存"+main.w.jtf.getText()+"到\\theorem?", 2);
		if(a==0){
			try{
				StringBuffer ifs=new StringBuffer(main.w.if_jta0.getText().replace('（', '('));
				ifs=new StringBuffer(ifs.toString().replace('）', ')'));
				//if处理
				String[] tmp0=ifs.toString().split("\n");
				StringBuffer tmps0=new StringBuffer();
				for(int i=0;i<tmp0.length;i++){
					tmps0.append(tmp0[i]);
				}
				
				String[] tmp1=tmps0.toString().split("\r");
				StringBuffer tmps1=new StringBuffer();
				for(int i=0;i<tmp1.length;i++){
					tmps1.append(tmp1[i]);
				}
				ifs=new StringBuffer(tmps1);
				//then处理
				
				String[] tmp2=main.w.then_jta0.getText().split("\n");
				StringBuffer tmps2=new StringBuffer();
				for(int i=0;i<tmp2.length;i++){
					tmps2.append(tmp2[i]);
				}
				
				String[] tmp3=tmps2.toString().split("\r");
				StringBuffer tmps3=new StringBuffer();
				for(int i=0;i<tmp1.length;i++){
					tmps3.append(tmp3[i]);
				}
				StringBuffer thens=new StringBuffer(tmps3);
				//
				File tr=new File("theorem");
				if(!(tr.exists()&&tr.isDirectory()))
					tr.mkdir();
				new file0().write("theorem\\"+main.w.jtf.getText()+".trm",ifs.toString()+"/*分割线*/"+thens.toString(),false);
				main.setStyle2();
				javax.swing.JOptionPane.showMessageDialog(null, "保存成功！");
				main.setStyle();
			}catch(Exception e){
				javax.swing.JOptionPane.showMessageDialog(null, "保存时出错！"+e);
			}
		}
	}

}
