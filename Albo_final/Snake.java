public class Snake 
{

// Stores locations for the snake
private final int[] x = new int[Board.getAllDots()];
private final int[] y = new int[Board.getAllDots()];

// Boolean for snake movement
private boolean movingLeft = false;
private boolean movingRight = false;
private boolean movingUp = false;
private boolean movingDown = false;

private int length = 0; // The lenght of the snack

public int getSnakeX(int index) {
    return x[index];
}

public int getSnakeY(int index) {
    return y[index];
}

public void setSnakeX(int i) {
    x[0] = i;
}

public void setSnakeY(int i) {
    y[0] = i;
}

public boolean isMovingLeft() {
    return movingLeft;
}

public void setMovingLeft(boolean movingLeft) {
    this.movingLeft = movingLeft;
}

public boolean isMovingRight() {
    return movingRight;
}

public void setMovingRight(boolean movingRight) {
    this.movingRight = movingRight;
}

public boolean isMovingUp() {
    return movingUp;
}

public void setMovingUp(boolean movingUp) {
    this.movingUp = movingUp;
}

public boolean isMovingDown() {
    return movingDown;
}

public void setMovingDown(boolean movingDown) {
    this.movingDown = movingDown;
}

public int getJoints() {
    return length;
}

public void setJoints(int j) {
    length = j;
}

public void move() {
    for (int i = length; i > 0; i--) {

        // Moves the parts of the snake with the cain
        // Everthing moves
        x[i] = x[(i - 1)];
        y[i] = y[(i - 1)];
    }

    // Moves snake to the left
    if (movingLeft) {
        x[0] -= Board.getDotSize();
    }
    // To the right
    if (movingRight) {
        x[0] += Board.getDotSize();
    }
    // Down
    if (movingDown) {
        y[0] += Board.getDotSize();
    }
    // Up
    if (movingUp) {
        y[0] -= Board.getDotSize();
    }

    // Dotsize represents the size of the snake parts, so a pixel of size
   
}
 }