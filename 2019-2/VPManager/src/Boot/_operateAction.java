package Boot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class _operateAction implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(Main.g.orp.newFile)) {
			Main.g.etp.save();


			if(Main.change) {        //����ı�ѯ���Ƿ񴢴�
				int chosen=javax.swing.JOptionPane.showConfirmDialog(null
						, "����ļ��ѸĶ����Ƿ񴢴棿", "��ȷ��", JOptionPane.YES_NO_CANCEL_OPTION);
				//-1���Ͻ� 0�� 1�� 2ȡ��
				if(chosen==-1||chosen==2)
					return;
				if(chosen==0) {
					saveFile();
				}
			}
			
			Main.g.cvp.reset();
		}else if(arg0.getSource().equals(Main.g.orp.openFile)) {
			Main.g.etp.save();
			
			if(Main.change) {        //����ı�ѯ���Ƿ񴢴�
				int chosen=javax.swing.JOptionPane.showConfirmDialog(null
						, "����ļ��ѸĶ����Ƿ񴢴棿", "��ȷ��", JOptionPane.YES_NO_CANCEL_OPTION);
				//-1���Ͻ� 0�� 1�� 2ȡ��
				if(chosen==-1||chosen==2)
					return;
				if(chosen==0) {
					saveFile();
				}
			}
			
			JFileChooser jfc=new JFileChooser();
			jfc.setFileFilter(new ScriptFileFilter());
			int result=jfc.showOpenDialog(null);
			if(result==JFileChooser.APPROVE_OPTION){
				Main.g.cvp.loadFile(jfc.getSelectedFile().getAbsolutePath());
			}else{
            	return;
            }
		}else if(arg0.getSource().equals(Main.g.orp.saveFile)) {
			saveFile();
		}
	}
	void saveFile() {

		String fp=Main.fileName;
		if(!new File(Main.fileName).exists()) {
			JFileChooser jfc=new JFileChooser();
			jfc.setFileFilter(new ScriptFileFilter());
			int result=jfc.showSaveDialog(null);
			if(result==JFileChooser.APPROVE_OPTION){
				fp=new String(jfc.getSelectedFile().getAbsolutePath());
				if(!fp.endsWith(".vs")) {
					fp=new String(fp+".vs");
				}
			}else{
            	return;
            }
		}
		Main.g.orp.saveFileTo(fp);
	}
}
