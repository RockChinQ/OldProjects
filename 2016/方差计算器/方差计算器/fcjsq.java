import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class ���������
{
  public static void main(String[] args)
    throws Exception
  {
    while (true)
    {
      System.out.println("2016Ԫ�����֣�\n��ӭʹ�÷�������� ���ԣ��ؿ���   ��Ȩ���� ��ð����");
      System.out.println("====================================");
      InputStreamReader localInputStreamReader = new InputStreamReader(System.in);
      BufferedReader localBufferedReader = new BufferedReader(localInputStreamReader);
      System.out.print("�����������������س���");
      String str = localBufferedReader.readLine();
      int i = Integer.parseInt(str);
      int[] arrayOfInt = new int[i];
      double d1 = 0.0D;
      for (int j = 0; j < arrayOfInt.length; ) {
        System.out.print("�����" + (j + 1) + "������");
        str = localBufferedReader.readLine();
        arrayOfInt[j] = Integer.parseInt(str);
        d1 += arrayOfInt[j];
        j++;
      }
      System.out.println("����������ϣ����ڼ���......");
      double d2 = d1 / i;
      System.out.println("ƽ������" + d2);
      double d3 = 0.0D;
      for (int k = 0; k < arrayOfInt.length; ) {
        double d5 = d2 - arrayOfInt[k];
        double d6 = d5 * d5;
        d3 += d6;
        k++;
      }
      double d4 = d3 / d2;
      System.out.println("���" + d4 + "\n��лʹ�ã���");
      System.out.print("����������������г��� �� ���� a �����س��˳�����");
      str = localBufferedReader.readLine();
      if (str == "a")
        System.exit(0);
    }
  }
}