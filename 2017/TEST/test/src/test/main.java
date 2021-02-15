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
         
        JMenu menu = new JMenu("菜单名称"); 
        menuBar.add(menu); 
        JMenuItem menuItem = new JMenuItem("菜单项名称"); 
        menu.add(menuItem); 
        JMenu menuSub = new JMenu("子菜单名称"); 
        menu.add(menuSub); 
        JMenuItem menuSub1Item = new JMenuItem("子菜单项名称"); 
        menuSub.add(menuSub1Item); 
         
        JMenu menu2 = new JMenu("菜单名称2"); 
        menuBar.add(menu2); 
        JMenuItem menuItem2 = new JMenuItem("菜单项名称2"); 
        menu2.add(menuItem2); 
        JMenu menuSub2 = new JMenu("子菜单名称2"); 
        menu2.add(menuSub2); 
        JMenuItem menuSub1Item2 = new JMenuItem("子菜单项名称2"); 
        menuSub2.add(menuSub1Item2); 
         
        menuItem.addActionListener(new ActionListener(){ 
 
            @Override 
            public void actionPerformed(ActionEvent e) { 
                // TODO Auto-generated method stub  
                JMenuItem menuItem = (JMenuItem) e.getSource(); 
                System.out.println("您单击的菜单项是：" + menuItem.getText()); 
            } 
             
        }); 
    } 
 
    /**
     * @param args
     */ 
    public static void main(String[] args) { 
        // TODO Auto-generated method stub  
        main frame = new main(); 
        frame.setTitle("创建菜单栏"); 
        frame.setVisible(true); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setBounds(100, 100, 400, 200); 
    } 
 
} 