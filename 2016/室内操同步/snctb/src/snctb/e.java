package snctb;

public class e extends Thread{
	public void run(){
		try{
			sleep(2000);
			System.exit(0);
		}catch(Exception e){
			System.out.println("Ïß³Ìe´íÎó£º"+e);
		}
	}
}
