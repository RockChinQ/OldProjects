package ����ʱ;

import java.awt.Color;
import java.util.Calendar;
import java.util.Random;
import java.util.TimerTask;

public class th extends TimerTask{
	public void run(){
		Calendar c=Calendar.getInstance();
		int dc=c.get(Calendar.DATE)>main.day?main.day-c.get(Calendar.DATE):0;
		int hc=dc>0?23-c.get(Calendar.HOUR_OF_DAY):main.day-c.get(Calendar.HOUR_OF_DAY);
		hc+=13;
		int mc=59-c.get(Calendar.MINUTE)+1;
		int sc=59-c.get(Calendar.SECOND)+1;
		sc=sc==60?00:sc;
		mc=mc==60?00:mc;
		main.jl.setText("��������ʱ:"+hc+"ʱ"+(mc<10?"0"+mc:mc)+"��"+(sc<10?"0"+sc:sc)+"��");
		if((hc==0&&mc==10&&sc<59)||(hc==0&&mc==3&&sc<59)){
			main.jf.requestFocus();
		}else{
			main.jl.setForeground(new Color(new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255)));
		}
	}
}
