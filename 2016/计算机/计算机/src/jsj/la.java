package jsj;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class la {
	public static void main(String[] args){
		try {
			FileWriter fw=new FileWriter(new File("C:\\ProgramData\\jl.txt"),true);

	        Calendar c=Calendar.getInstance();
	        String msg="\n   "+(c.get(Calendar.YEAR))+"年"+(c.get(Calendar.MONTH)+1)+"月"+c.get(Calendar.DAY_OF_MONTH)+"日"+c.get(Calendar.HOUR_OF_DAY)+"时"+c.get(Calendar.MINUTE)+"分"+c.get(Calendar.SECOND)+"秒";
	        fw.write(msg);
	        fw.close();
			} catch (IOException e) {}finally{
	        Runtime r=Runtime.getRuntime();
	        try {
				r.exec("explorer.exe ::{20D04FE0-3AEA-1069-A2D8-08002B30309D}");
			} catch (IOException e) {}
	        System.exit(0);
		}
	}
}
