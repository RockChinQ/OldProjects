import java.io.FileOutputStream;
import java.util.Calendar;
public class mycom{
    public static void main(String[] args) throws Exception{
        Calendar c=Calendar.getInstance();
        FileOutputStream fos=new FileOutputStream("C:\\rocksys.txt");
        String msg=(c.get(Calendar.MONTH)+1)+"��"+c.get(Calendar.DAY_OF_MONTH)+"��"+c.get(Calendar.HOUR_OF_DAY)+"ʱ"+c.get(Calendar.MINUTE)+"��";
        fos.write(msg.getBytes());
        fos.close();
        Runtime r=Runtime.getRuntime();
        r.exec("explorer.exe ::{20D04FE0-3AEA-1069-A2D8-08002B30309D}");
        System.exit(0);
    }
}