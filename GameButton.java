import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GameButton extends JButton {
    Random rand;
    public GameButton(){
        rand = new Random();
        int colorNumber = rand.nextInt(3);
        if(colorNumber == 0)
        {
            this.setBackground(Color.red);
        }
        else if(colorNumber == 1)
        {
            this.setBackground(Color.green);
        }
        else if(colorNumber == 2)
        {
            this.setBackground(Color.blue);
        }
    }
    }

