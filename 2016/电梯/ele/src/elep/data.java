package elep;

import java.util.TimerTask;

public class data extends TimerTask{
	public void run() {
		hardware.loa.setText("���ݸ߶�:"+hardware.floor);
		hardware.run.setText("״̬:"+(software.run?"������":"��ֹͣ"));
		float fl=main.speed/10;
		hardware.speed.setText("����Ƶ��:"+main.speed+"Hz|ÿ��"+fl+"��");
		hardware.task2.setText(""+software.task[0]+"��"+software.task[1]+"��"+software.task[2]+"��"+software.task[3]+"��"+software.task[4]+"��"+software.task[5]+"��"+software.task[6]+"��"+software.task[7]);
	}
}
