import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
/**
 * GamePanel
 */
public class GamePanel extends JPanel {
    private static JFrame frame = new JFrame();
    private static int score;
    private static int grayPanelCount = 0;
    static int depth ;

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
            // 4 new gamePanels in case depth is positive
            for(int i = 0; i < 4; i++)
            {
                GamePanel panel = new GamePanel(depth - 1);
                // when we have gamePanels that directly involves buttons, check them for same color.
                if(depth == 1)
                {
                    searchForSameColor(panel.getComponents());      
                }
                add(panel);
            }   
            score = 10;
        }
    }
    
    private void searchForSameColor(Component[] components) {
        GameButton [] buttons = Arrays.copyOf(components, components.length, GameButton[].class);
        boolean isSame = true;
        Color color = buttons[0].getBackground();
        for(int i = 0; i < buttons.length; i++)
        {
            if(!color.equals(buttons[i].getBackground()) )
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
                frame.setTitle("Score: " + score);       
            }
            grayPanelCount++;  
        }
          
        if(score <= 0 || grayPanelCount == (int) Math.pow(4,depth))
        {
            int input = JOptionPane.showConfirmDialog(null
                , " Would you want to play again?", "Play again?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if(input == JOptionPane.YES_OPTION)
            {
                this.removeAll();
                initializeGame();

            }
            else if ( input == JOptionPane.NO_OPTION)
            {
                System.exit(0);
            }
        }
    }

    class ClickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().getClass().getName() == "GameButton")
            {
                GameButton button = (GameButton) e.getSource();
                button.setBackgroundColor();
                Component [] components = button.getParent().getComponents();
                // get the clicked button's parent and find all components of it and cast it to GameButton.
                searchForSameColor(components);
                
                score--;
                frame.setTitle("Score: " + score);       
            }
        }
         // if same colors, proceed to make button deactivated and gray
          
    }
    public static void main(String[] args) {

           
            frame.setTitle("Score: " + score);
        
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600,600);
            frame.setLocationRelativeTo(null);
        
            initializeGame();
    }
    public static void initializeGame(){
            grayPanelCount = 0;
            score = 10;
            depth = 2;
            frame.add(new GamePanel(depth));
            frame.setVisible(true);

    }
}