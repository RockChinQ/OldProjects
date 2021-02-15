package Main;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class PluginLoader {
	ArrayList<RunPlugin> plugins=new ArrayList<RunPlugin>();
	public PluginLoader(){
		System.out.print("Loading PluginLoader...");
		
		System.out.println("DONE.");
	}
	public void reloadAllPlugin() {
		try {
			ClassLoader classLoader = new URLClassLoader(new URL[]
					{new URL(new File("D:\\").toURI().toString())});			
			Class te = classLoader.loadClass("loadPic_testPlugin.MainClass");
			plugins.add(new RunPlugin(te,"123"));
			plugins.get(0).start();
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	public void destroyPlugin(String label) {
		for(int i=0;i<plugins.size();i++) {
			if(plugins.get(i).label.equals(label)) {
				System.out.println("[System]Destroy:"+label);
				plugins.get(i).destroy();
			}
		}
	}
}
