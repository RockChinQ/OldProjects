package Lib;

public class Sys {
	public static void exit(String label) {
		Main.Main.pl.destroyPlugin(label);
	}
}
