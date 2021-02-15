package Main;

public class Main {
	public static Config cf;
	public static PluginLoader pl;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//cf=new Config("any2wp.ini");
		pl=new PluginLoader();
		pl.reloadAllPlugin();
	}

}
