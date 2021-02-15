package kgc_ser_p0;

public class notes {
	getTime gt=new getTime();
	void save(String s){
		new file0().write(main.sou+"notes.txt",gt.getYear()+":"+gt.getMonth()+":"+gt.getDayOfMonth()+":"+gt.getHourOfDay()+":"+gt.getMinute()+":"+gt.getSecond()+"---"+s,true);
	}
}
