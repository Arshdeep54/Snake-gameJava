package snake.java;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class gameFrame extends JFrame {


    gameFrame(){
       gamePanel panel=new gamePanel();

        this.add(panel);

        this.setTitle("Snake game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

       this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }
}
