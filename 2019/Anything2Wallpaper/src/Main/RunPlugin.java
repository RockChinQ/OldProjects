package Main;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class RunPlugin extends Thread {
	public class Plugin{
		String author;
		String version;
		String pluginName;
		String pluginDetail;
	}
	Class plugin;
	Object plugObj;
	String label;
	RunPlugin(Class plugin,String label){
			this.plugin=plugin;
			this.label=new String(label);
			try {
				this.plugObj=plugin.newInstance();
				Field labelField=plugin.getField("label");
				labelField.set(plugObj,label);
			} catch (NoSuchFieldException | SecurityException | InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("[System]Exception occurred when load some plugin,thread is now destoryed.");
				this.destroy();
			}
			
	}
	public void run() {
		try {
			/*
			ClassLoader classLoader = new URLClassLoader(new URL[]{new URL(fileUrlString)});
			Class HelloWorldClazz = classLoader.loadClass("terry.codex.HelloWorld");
			*/
			Method method = plugObj.getClass().getMethod("load",null);
			method.invoke(plugObj,null);
			method = plugObj.getClass().getMethod("run",null);
			method.invoke(plugObj,null);
		} catch (Exception e1) {// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
