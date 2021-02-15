package elep;

import java.util.TimerTask;

public class data extends TimerTask{
	public void run() {
		hardware.loa.setText("电梯高度:"+hardware.floor);
		hardware.run.setText("状态:"+(software.run?"运行中":"已停止"));
		float fl=main.speed/10;
		hardware.speed.setText("运行频率:"+main.speed+"Hz|每秒"+fl+"层");
		hardware.task2.setText(""+software.task[0]+"→"+software.task[1]+"→"+software.task[2]+"→"+software.task[3]+"→"+software.task[4]+"→"+software.task[5]+"→"+software.task[6]+"→"+software.task[7]);
	}
}
