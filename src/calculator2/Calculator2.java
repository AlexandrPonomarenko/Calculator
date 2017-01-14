package calculator2;

import com.sun.imageio.plugins.jpeg.JPEG;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javafx.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Calculator2 extends JPanel
{
    private JButton displei;
    private JPanel panel;
    private double result;
    private String lastComand;
    private boolean start;
    
    public Calculator2()
    {
        setLayout(new BorderLayout());
        result = 0;
        lastComand = "=";
        start = true;
        displei = new JButton("0");
        displei.setEnabled(false);
        add(displei,BorderLayout.NORTH);
        
        
        ActionListener insert = new InsertAction();
        ActionListener command = new CommandAction();
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));
        
        addButton("7",insert);
        addButton("8",insert);
        addButton("9",insert);
        addButton("/",command);
        
        addButton("4",insert);
        addButton("5",insert);
        addButton("6",insert);
        addButton("*",command);
        
        addButton("1",insert);
        addButton("2",insert);
        addButton("3",insert);
        addButton("-",command);
        
        addButton("0",insert);
        addButton(".",insert);
        addButton("=",command);
        addButton("+",command);
        
        add(panel,BorderLayout.CENTER);
    }
    
    private void addButton(String str,ActionListener listener)
    {
        JButton button = new JButton(str);
        button.addActionListener(listener);
        panel.add(button);
    }
    
    
    private class InsertAction implements ActionListener
     {

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) 
        {
            String input = e.getActionCommand();
            if(start)
            {
                displei.setText("");
                start = false;
            }
            displei.setText(displei.getText()+ input);
        }
    }
    
    
    private class CommandAction implements ActionListener
    {

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) 
        {
            String command = e.getActionCommand();
            if(start)
            {
                if(command.equals("-"))
                {
                    displei.setText(command);
                    start = false;
                }
                else lastComand = command;
            }
            else 
            {
                calculeite(Double.parseDouble(displei.getText()));
                lastComand = command;
                start = true;
            }
            
        }
        
        public void calculeite(double  x)
        {
            if(lastComand.equals("+"))result += x;
            else if(lastComand.equals("-"))result -= x;
            else if(lastComand.equals("*"))result *= x;
            else if(lastComand.equals("/"))result /= x;
            else if(lastComand.equals("="))result = x;
            displei.setText("" + result);
        }
        
    }
    
    public static void main(String[] args) 
    {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() 
            {
                JFrame f = new JFrame();
                f.setSize(300, 300);
                f.setResizable(false);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.add(new Calculator2());
                f.setVisible(true);
            }
        });
        
        
        
    }
    
}
