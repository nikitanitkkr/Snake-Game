/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author nikita
 */
public class Snake implements ActionListener {
    public int color = 0;
    JFrame frame;
    static Snake snake;
    SnakePanel snakePanel;
    Timer timer = new Timer(40, this);
    ArrayList<Point> snakeParts;
    static final int UP = 0,DOWN = 1,LEFT = 2,RIGHT = 3; 
    int ticks = 0 ,direction = RIGHT, score = 0;
    boolean over = false;
    Point head,cherry;
    
    public Snake() {
        //dim = Toolkit.getDefaultToolkit().getScreenSize();
        int score = 0;
        frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,350);
        frame.setResizable(false);
        frame.setVisible(true);
        //frame.setLocation(dim.width/2-frame.getWidth()/2, dim.height/2-frame.getHeight()/2);
        frame.add(snakePanel = new SnakePanel());
        snakeParts = new ArrayList<>();
        frame.addKeyListener(new TAdapter());
        head = new Point(1,0);
        snakeParts.add(new Point(0,0));
        snakeParts.add(new Point(1,0));
        cherry = new Point((int)(Math.random()*40),(int)(Math.random()*35)); 
        timer.start();
    }
    
     @Override
    public void actionPerformed(ActionEvent e) {
        snakePanel.repaint();
        ticks++;
        if (ticks%10 == 0 && over == false) {
            if ( color == 0 ) 
                color = 1;
            else
                color = 0;
            if ( direction == UP ){
                if ( head.y-1 >= 0 )
                    head = new Point(head.x,head.y-1);
                else 
                    over = true;
            }
            if ( direction == DOWN ){
                if ( head.y+1 < 35)
                    head = new Point(head.x,head.y+1);
                else 
                    over = true;
            }
            if ( direction == LEFT ){
                if ( head.x-1 >= 0 )
                    head = new Point(head.x-1,head.y);
                else 
                    over = true;
            }
            if ( direction == RIGHT ){
                if ( head.x+1 < 40) 
                    head = new Point(head.x+1,head.y);
                else 
                    over = true;
            }
            snakeParts.add(new Point(head.x,head.y));
            if ( head.equals(cherry)) {
                cherry = new Point((int)(Math.random()*40),(int)(Math.random()*35));
                score++;
            }
            else {
                  snakeParts.remove(0);
            }
            if (snakeParts.size() == 5) {
                timer.setDelay(20);
            }
            if ( over == true ){
                frame.dispose();
                JOptionPane.showMessageDialog(null, "GAME OVER"+"\nYOUR SCORE IS "+score);
            }
        }
        
    }
    
    class TAdapter extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if ( key == KeyEvent.VK_LEFT && direction != RIGHT ){
                direction = LEFT;
            }
            if ( key == KeyEvent.VK_RIGHT && direction != LEFT ){
                 direction = RIGHT;
            }
            if ( key == KeyEvent.VK_UP && direction != DOWN ){
                 direction = UP;
            }
            if ( key == KeyEvent.VK_DOWN && direction != UP ){
                 direction = DOWN;
            }
        }
        
    }
    
    /**
     *
     * @param args
     */
    
    public static void main(String[] args) {
       
        snake = new Snake();
    }
}
