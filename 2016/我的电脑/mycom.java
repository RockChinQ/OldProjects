import java.io.FileOutputStream;
import java.util.Calendar;
public class mycom{
    public static void main(String[] args) throws Exception{
        Calendar c=Calendar.getInstance();
        FileOutputStream fos=new FileOutputStream("C:\\rocksys.txt");
        String msg=(c.get(Calendar.MONTH)+1)+"月"+c.get(Calendar.DAY_OF_MONTH)+"日"+c.get(Calendar.HOUR_OF_DAY)+"时"+c.get(Calendar.MINUTE)+"分";
        fos.write(msg.getBytes());
        fos.close();
        Runtime r=Runtime.getRuntime();
        r.exec("explorer.exe ::{20D04FE0-3AEA-1069-A2D8-08002B30309D}");
        System.exit(0);
    }
}