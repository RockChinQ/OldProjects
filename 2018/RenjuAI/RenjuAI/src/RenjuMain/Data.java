package RenjuMain;

public class Data {
	static final String ver="Hydrogen";
	Config cf=new Config("Renju"+ver+".ini");
	public String style=cf.read("style");
	GUI g;
	Data(){
		g=new GUI(style);
	}
}
