package snake.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class gamePanel extends JPanel implements ActionListener {


    JButton start;
    static final int WIDTH=600;
    static final int HEIGHT=600;
    static final int BLOCK=25;
    static final int GAME_UNITS=(WIDTH*HEIGHT)/BLOCK;
    static final int delay=150;
    final int[] x=new int[GAME_UNITS];
    final int[] y=new int[GAME_UNITS];
    int bodyParts=6;
    int fruitX;
    int fruitY;
    int fruitsEaten;
    char direction ='R';
    boolean moving=false;
    int oncePlayed=0;
    Timer timer;
    Random rand;



    gamePanel(){


        start=new JButton("start");
        start.setFocusable(false);
        start.setBounds(0,0,1000,100);
        start.addActionListener(this);

        rand=new Random();
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.setVisible(true);
        this.add(start);
        this.addKeyListener(new MyKeyAdapter(){});


    }
    public void startGame(){
        newApple();
        moving=true;
        oncePlayed = 1;
        timer=new Timer(delay,this);
        timer.start();

    }

    public void newApple() {
        fruitX=rand.nextInt((int)(WIDTH/BLOCK))*BLOCK;
        fruitY=rand.nextInt((int)(HEIGHT/BLOCK))*BLOCK;

    }

    public void paint(Graphics g){
        super.paint(g);
        draw(g);


      //
    }
    public void draw(Graphics g){
        if(moving) {
            /*for (int i = 0; i < HEIGHT / BLOCK; i++) {
                g.drawLine(i * BLOCK, 0, i * BLOCK, HEIGHT);
                g.drawLine(0, i * BLOCK, WIDTH, i * BLOCK);

            }*/
            g.setColor(new Color(102, 0, 153));
            g.fillOval(fruitX, fruitY, BLOCK, BLOCK);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], BLOCK, BLOCK);
                } else {
                    g.setColor(Color.CYAN);
                    g.fillRect(x[i], y[i], BLOCK, BLOCK);
                }
            }
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free",Font.BOLD,40));
            FontMetrics metrics=getFontMetrics(g.getFont());

            g.drawString("Score:"+fruitsEaten,(WIDTH-metrics.stringWidth("Score"+fruitsEaten))/2 , 30);


        }
        else{

            if(oncePlayed==1) gameOver(g);

        }
    }
    public void move(){

        for(int i=bodyParts;i>0;i--){
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        switch(direction){
            case 'R':
                x[0]=x[0]+BLOCK;
                break;
            case 'L':
                x[0]=x[0]-BLOCK;
                break;
            case 'U':
                y[0]=y[0]-BLOCK;
                break;
            case 'D':
                y[0]=y[0]+BLOCK;
                break;


        }
    }
    public void checkFruit(){
        if(x[0]==fruitX && y[0]==fruitY){
            fruitsEaten++;
            bodyParts++;


            newApple();

        }

    }
    public void checkCollisions(){

        for(int i=bodyParts;i<0;i--){
            if(x[0]==x[i] && y[0]==y[i]){
                moving=false;
            }
        }
        if(x[0]<0 || x[0]>WIDTH || y[0]<0 || y[0]>HEIGHT){
            moving=false;
        }
        if(!moving){
            timer.stop();


        }
    }
    public void gameOver(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,75));
        FontMetrics metrics=getFontMetrics(g.getFont());

        g.drawString("Game Over",(WIDTH-metrics.stringWidth("Game Over"))/2,(HEIGHT-metrics.stringWidth("Game Over"))/2);

        g.setColor(new Color(102, 0, 153));
        g.drawString("Score:"+fruitsEaten,(WIDTH-metrics.stringWidth("Score:"+fruitsEaten))/2 , 400);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==start){
            startGame();
            start.setVisible(false);
        }

        if(moving){
            move();
            checkFruit();
            checkCollisions();
        }
        repaint();
    }
    public class MyKeyAdapter extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction!='R'){
                        direction='L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction!='L'){
                        direction='R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction!='D'){
                        direction='U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction!='U'){
                        direction='D';
                    }
                    break;



            }
        }
    }
}
