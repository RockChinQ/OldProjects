package 室内操同步;
import java.util.Calendar;
import java.awt.Desktop;
import java.io.File;
public class 室内操同步 {
	public static void main(String[] args) throws Exception{
		Calendar c=Calendar.getInstance();
		for(;;){
		    int h=c.get(Calendar.HOUR_OF_DAY);
			int m=c.get(Calendar.MINUTE);
			int s=c.get(Calendar.SECOND);
			if((h==10)&&(m==5)&&(s==6)){
				Desktop.getDesktop().open(new File("C:\\室内操2.mp4"));
				System.exit(0);
			}
		}
	}
}
