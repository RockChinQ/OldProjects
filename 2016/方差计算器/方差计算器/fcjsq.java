import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class 方差计算器
{
  public static void main(String[] args)
    throws Exception
  {
    while (true)
    {
      System.out.println("2016元旦快乐！\n欢迎使用方差计算器 来自：秦骏言   版权所有 假冒等死");
      System.out.println("====================================");
      InputStreamReader localInputStreamReader = new InputStreamReader(System.in);
      BufferedReader localBufferedReader = new BufferedReader(localInputStreamReader);
      System.out.print("输入数据数量并按回车：");
      String str = localBufferedReader.readLine();
      int i = Integer.parseInt(str);
      int[] arrayOfInt = new int[i];
      double d1 = 0.0D;
      for (int j = 0; j < arrayOfInt.length; ) {
        System.out.print("输入第" + (j + 1) + "个数：");
        str = localBufferedReader.readLine();
        arrayOfInt[j] = Integer.parseInt(str);
        d1 += arrayOfInt[j];
        j++;
      }
      System.out.println("数据输入完毕！正在计算......");
      double d2 = d1 / i;
      System.out.println("平均数：" + d2);
      double d3 = 0.0D;
      for (int k = 0; k < arrayOfInt.length; ) {
        double d5 = d2 - arrayOfInt[k];
        double d6 = d5 * d5;
        d3 += d6;
        k++;
      }
      double d4 = d3 / d2;
      System.out.println("方差：" + d4 + "\n感谢使用！！");
      System.out.print("输入任意键重新运行程序 或 输入 a 并按回车退出程序：");
      str = localBufferedReader.readLine();
      if (str == "a")
        System.exit(0);
    }
  }
}