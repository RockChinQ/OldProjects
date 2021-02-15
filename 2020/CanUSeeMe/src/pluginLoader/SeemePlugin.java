package pluginLoader;

public abstract class SeemePlugin extends Thread{
	public String name="SeemePlugin";
	public int version=1;
	public String describe="A Seeme Plugin.";
	public String author="";
	public abstract void init(int uuid);
	public abstract int exit(int sysExitCode);
	public abstract void run();//Ö÷³ÌÐò
}
