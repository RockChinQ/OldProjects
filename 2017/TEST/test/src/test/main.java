package test;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
 
import javax.swing.JFrame; 
import javax.swing.JMenu; 
import javax.swing.JMenuBar; 
import javax.swing.JMenuItem; 
 
public class main extends JFrame { 
 
    /**
     * 
     */ 
    private static final long serialVersionUID = -5106828427995282232L; 
 
    public main() { 
        // TODO Auto-generated constructor stub  
        JMenuBar menuBar = new JMenuBar(); 
        setJMenuBar(menuBar); 
         
        JMenu menu = new JMenu("�˵�����"); 
        menuBar.add(menu); 
        JMenuItem menuItem = new JMenuItem("�˵�������"); 
        menu.add(menuItem); 
        JMenu menuSub = new JMenu("�Ӳ˵�����"); 
        menu.add(menuSub); 
        JMenuItem menuSub1Item = new JMenuItem("�Ӳ˵�������"); 
        menuSub.add(menuSub1Item); 
         
        JMenu menu2 = new JMenu("�˵�����2"); 
        menuBar.add(menu2); 
        JMenuItem menuItem2 = new JMenuItem("�˵�������2"); 
        menu2.add(menuItem2); 
        JMenu menuSub2 = new JMenu("�Ӳ˵�����2"); 
        menu2.add(menuSub2); 
        JMenuItem menuSub1Item2 = new JMenuItem("�Ӳ˵�������2"); 
        menuSub2.add(menuSub1Item2); 
         
        menuItem.addActionListener(new ActionListener(){ 
 
            @Override 
            public void actionPerformed(ActionEvent e) { 
                // TODO Auto-generated method stub  
                JMenuItem menuItem = (JMenuItem) e.getSource(); 
                System.out.println("�������Ĳ˵����ǣ�" + menuItem.getText()); 
            } 
             
        }); 
    } 
 
    /**
     * @param args
     */ 
    public static void main(String[] args) { 
        // TODO Auto-generated method stub  
        main frame = new main(); 
        frame.setTitle("�����˵���"); 
        frame.setVisible(true); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setBounds(100, 100, 400, 200); 
    } 
 
} 