package CAE;

public class f extends Thread{
	boolean b=false;
	public void run(){
		//try {
			//Thread.sleep(1000);
		//} catch (InterruptedException e) {
			//e.printStackTrace();
		//}
		while(true){
			//System.out.println(spawnwindows.b1&&!b);
			//System.out.println(b);
			if(spawnwindows.b1&&!b){
				//System.out.println("2121211");
				spawnwindows.wi.requestFocus();
				b=true;
			}else if(!spawnwindows.b1){
				b=false;
			}
		}
	}
}
