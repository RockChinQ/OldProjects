package cps;
import javax.swing.JFrame;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JPanel;
public class start {
	public static void main(String[] args){
		GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle rec=ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds();
		new spawnwin();
	}
}
