
/** Used a youtube video to help create the snake game
 * Helped me to understand what each method, import, varible does
 * helped to show me how to use this methods and skill to do other things
 * The video was step by step instructions
 * I changed parts to what I thought would work better
 * Alot of the components of the game we haven't learned yet. but
 * the youtube video did a great job explaing and helped me learn how
 * everything works. It was really fun learing about it
 * I started watching the video near the begging of online school
 * Its been a fun project and has helped me to learn many new skills
 * and things to use when coding in Java in the futhure
 * 
 * 
 * Void main the Game class to start playing
 * Use the arrow keys to move
 * Dont touch the walls
 * Dont run into yourself
 */


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {



// Holds height and width of the window
private final static int width = 750;
private final static int height = 700;

// For the size of food and snake
private final static int size = 25;

// Max oixles


private final static int TOTALPIXELS = (width * height)
        / (size * size);

// Check to make sure working
private boolean inGame = true;

// Timer 
private Timer timer;

//Speed the snake moves
private static int speed = 70;

// Snake/Food
private Snake snake = new Snake();
private Food food = new Food();

public Board() {

    addKeyListener(new Keys());
    setBackground(Color.BLACK);
    setFocusable(true);

    setPreferredSize(new Dimension(width, height));

    initializeGame();
}

// Add components too screen
@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    draw(g);
}

// Draw our Snake & Food
void draw(Graphics g) {
    // Only draw if the snake is still alive
    if (inGame == true) {
        g.setColor(Color.green);
        g.fillRect(food.getFoodX(), food.getFoodY(), size, size); // food

        // Draw our snake.
        for (int i = 0; i < snake.getJoints(); i++) {
            // Snake's head
            if (i == 0) {
                g.setColor(Color.RED);
                g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i),
                        size, size);
                // Body of snake
            } else {
                g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i),
                        size, size);
            }
        }

        // Sync our graphics together
        Toolkit.getDefaultToolkit().sync();
    } else {
        // If snake dies, game over
        endGame(g);
    }
}

void initializeGame() {
    snake.setJoints(3); // set our snake's initial size

    // Create our snake's body
    for (int i = 0; i < snake.getJoints(); i++) {
        snake.setSnakeX(width / 2);
        snake.setSnakeY(height / 2);
    }
    
    

    // Generate our first 'food'
    food.createFood();

    // set the timer to record our game's speed / make the game move
    timer = new Timer(speed, this);
    timer.start();
}

// if our snake is in the close proximity of the food..
void checkFoodCollisions() {

    if ((proximity(snake.getSnakeX(0), food.getFoodX(), 20))
            && (proximity(snake.getSnakeY(0), food.getFoodY(), 20))) {

        System.out.println("intersection");
        // Add a 'joint' to our snake
        snake.setJoints(snake.getJoints() + 1);
        // Create new food
        food.createFood();
    }
}

// Used to check collisions with snake's self and board edges
void checkCollisions() {

    // If the snake hits its' own body
    for (int i = snake.getJoints(); i > 0; i--) {

        // Snake cant hit itself if it's not larger than 5
        if ((i > 5)
                && (snake.getSnakeX(0) == snake.getSnakeX(i) && (snake
                        .getSnakeY(0) == snake.getSnakeY(i)))) {
            inGame = false; // then the game ends
        }
    }

    // If the snake hits a board edge the game ends
    if (snake.getSnakeY(0) >= height) {
        inGame = false;
    }

    if (snake.getSnakeY(0) < 0) {
        inGame = false;
    }

    if (snake.getSnakeX(0) >= width) {
        inGame = false;
    }

    if (snake.getSnakeX(0) < 0) {
        inGame = false;
    }

    // If the game has ended, then we can stop our timer
    if (!inGame) {
        timer.stop();
    }
}

void endGame(Graphics g) {

    //  game over message
    String message = "Game over";

    // Changes font
    Font font = new Font("Times New Roman", Font.BOLD, 14);
    FontMetrics metrics = getFontMetrics(font);

    // Text to red
    g.setColor(Color.red);
    g.setFont(font);

    // draw message
    g.drawString(message, (width - metrics.stringWidth(message)) / 2,
            height / 2);

    System.out.println("Game Ended");

}

// Run constantly as long as we're in game.
@Override
public void actionPerformed(ActionEvent e) {
    if (inGame == true) {

        checkFoodCollisions();
        checkCollisions();
        snake.move();

        System.out.println(snake.getSnakeX(0) + " " + snake.getSnakeY(0)
                + " " + food.getFoodX() + ", " + food.getFoodY());
    }
    // Fix screen
    repaint();
}

private class Keys extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if ((key == KeyEvent.VK_LEFT) && (!snake.isMovingRight())) {
            snake.setMovingLeft(true);
            snake.setMovingUp(false);
            snake.setMovingDown(false);
        }

        if ((key == KeyEvent.VK_RIGHT) && (!snake.isMovingLeft())) {
            snake.setMovingRight(true);
            snake.setMovingUp(false);
            snake.setMovingDown(false);
        }

        if ((key == KeyEvent.VK_UP) && (!snake.isMovingDown())) {
            snake.setMovingUp(true);
            snake.setMovingRight(false);
            snake.setMovingLeft(false);
        }

        if ((key == KeyEvent.VK_DOWN) && (!snake.isMovingUp())) {
            snake.setMovingDown(true);
            snake.setMovingRight(false);
            snake.setMovingLeft(false);
        }

        if ((key == KeyEvent.VK_ENTER) && (inGame == false)) {

            inGame = true;
            snake.setMovingDown(false);
            snake.setMovingRight(false);
            snake.setMovingLeft(false);
            snake.setMovingUp(false);

            initializeGame();
        }
    }
}

private boolean proximity(int a, int b, int closeness) {
    return Math.abs((long) a - b) <= closeness;
}

public static int getAllDots() {
    return TOTALPIXELS;
}

public static int getDotSize() {
    return size;
}
}