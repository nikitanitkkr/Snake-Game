/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.*;
import java.awt.Graphics;
import javax.swing.*;

/**
 *
 * @author nikita
 */
public class SnakePanel extends JPanel {
    @Override    
    protected void paintComponent ( Graphics g ) {
        Snake snake = Snake.snake;
        if ( snake.color == 0 ){
            g.setColor(Color.black);
        }
        else
            g.setColor(Color.gray);
        g.fillRect(0, 0, 400, 350);
        g.setColor(Color.GREEN);
        for (Point point : snake.snakeParts){
            /*if ( point.x == snake.head.x && point.y == snake.head.y )
            {
                g.setColor(Color.GREEN);
            }*/
            g.fillRect(point.x*10, point.y*10, 10, 10);
        }
        g.setColor(Color.red);
        g.fillOval(snake.head.x*10, snake.head.y*10, 5, 5);
        g.fillOval(snake.cherry.x*10, snake.cherry.y*10, 10, 10);
        //g.drawImage(new ImageIcon(getClass().getResource("/images/cherry.jpg")).getImage(), snake.cherry.x*10, snake.cherry.y*10, this);
    }
}
