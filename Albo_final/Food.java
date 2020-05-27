public class Food {

private Snake snake = new Snake();
private int foodX; // Stores X
private int foodY; // Stores Y 


private final int Rand = 20;

public void createFood() // Places food randomly
{

    
    int location = (int) (Math.random() * Rand);
    foodX = ((location * Board.getDotSize()));

    location = (int) (Math.random() * Rand);
    foodY = ((location * Board.getDotSize()));

    if ((foodX == snake.getSnakeX(0)) && (foodY == snake.getSnakeY(0))) {
        createFood();
    }

}

public int getFoodX() {

    return foodX;
}

public int getFoodY() {
    return foodY;
}
}