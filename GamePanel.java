import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
/**
 * GamePanel
 */
public class GamePanel extends JPanel {
    public static JFrame frame;
    public GamePanel(int depth){
        setLayout(new GridLayout(2,2));
        if(depth == 0)
        {
            for(int i = 0; i < 4; i++)
            {
                GameButton button = new GameButton();
                add(button);
                button.addActionListener(new ClickListener());
            }
            
        }
        else if(depth > 0)
        {
            
            for(int i = 0; i < 4; i++)
            {
                GamePanel button = new GamePanel(depth - 1);
                add(button);
            }
            
        }
        
    }
    class ClickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().getClass().getName() == "GameButton")
            {
                GameButton button =  (GameButton) e.getSource();
                button.setBackgroundColor();
                
            }   
        }
        public static void score(){
        }
    }
    public static void main(String[] args) {
       
        int score = 0;
        frame.setTitle("Score: " + score);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        int depth = 2;
        frame.add(new GamePanel(depth));

        frame.setVisible(true);

    }
}