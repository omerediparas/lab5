import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * GamePanel
 */
public class GamePanel extends JFrame{
    
    
    public GamePanel(int depth){
        setLayout(new GridLayout(2,2));
        if(depth == 0)
        {
            for(int i = 0; i < 4; i++)
            add(new GameButton());
        }
        else if(depth > 0)
        {
            for(int i = 0; i < 4; i++)
            add(new GamePanel(depth - 1));
        }
    }
    
}