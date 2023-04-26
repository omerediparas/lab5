import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
/**
 * GamePanel
 */
public class GamePanel extends JPanel {
    public static JFrame frame = new JFrame();
    public static int score = 0;
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
                // get the clicked button's parent and find all components of it and cast it to GameButton.
                searchForSameColor((button.getParent().getComponents()));
                score--;
                frame.setTitle("Score: " + score);       
            }   
        }
         // if same colors, proceed to make button deactivated and gray
        public void searchForSameColor(Component [] components){
            GameButton [] buttons = (GameButton []) components;
            boolean isSame = true;
            Color color = buttons[0].getBackground();
            for(int i = 0; i < buttons.length; i++)
            {
                if(color.equals(buttons[i].getBackground()) )
                {
                    isSame = false; 
                }

            }
            if(isSame)
            {
                for(GameButton element: buttons)
                {
                    element.setBackground(Color.gray);
                    element.setEnabled(false);
                    score += 10;
                }
            }
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