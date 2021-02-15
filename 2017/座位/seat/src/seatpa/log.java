package seatpa;

public class log {
	getTime gt=new getTime();
	void addlog(String log){
		editgui.jta.setText(editgui.jta.getText()+"\n"+gt.getHourOfDay()+":"+gt.getMinute()+":"+gt.getSecond()+" "+log);
	}
}
