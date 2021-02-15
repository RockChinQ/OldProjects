public class test{
     public static void main(String[] args){
         long t1=System.currentTimeMillis();
         for(int i=1;i<=100000000;i++){}
         long t2=System.currentTimeMillis();
         System.out.println("Ê±¼ä£º"+(t2-t1)+"ºÁÃë");
     }
}