public class test{
     public static void main(String[] args){
         long t1=System.currentTimeMillis();
         for(int i=1;i<=100000000;i++){}
         long t2=System.currentTimeMillis();
         System.out.println("ʱ�䣺"+(t2-t1)+"����");
     }
}