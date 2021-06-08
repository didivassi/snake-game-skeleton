package academy.mindswap;

import academy.mindswap.field.Field;
import academy.mindswap.field.Position;
import academy.mindswap.gameobjects.fruit.Fruit;
import academy.mindswap.gameobjects.snake.Direction;
import academy.mindswap.gameobjects.snake.Snake;
import academy.mindswap.utils.RandomNumbers;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;


public class Game {

    private Snake snake;
    private Fruit fruit;
    private int delay;
    private int score;
    private int maxScore;
    private int gameOverLoop;


    public Game(int cols, int rows, int delay) {
        Field.init(cols, rows);
        snake = new Snake();
        this.delay = delay;
        score=0;
        maxScore=0;
    }

    public void start() throws InterruptedException {

         generateFruit(); // uncomment when it's time to introduce fruits

        while (true) {
            Thread.sleep(delay);
            Field.clearTail(snake);
            moveSnake();
            checkCollisions();
            Field.drawSnake(snake);
            Field.drawScore(score);
            Field.drawMaxScore(maxScore);
            printGameOver();
        }
    }

    private void generateFruit() {
            Position position=new Position(RandomNumbers.generateRandom(1,Field.getWidth()-2),RandomNumbers.generateRandom(1,Field.getHeight()-2));
            fruit=new Fruit(position);
            Field.drawFruit(fruit);
    }

    private void moveSnake() {
        Key k = Field.readInput();
        if (k != null) {
            switch (k.getKind()) {
                case Enter:
                    Field.clearSnake(snake);
                    Field.clearFruit(fruit);
                    Field.gameOver(null,true);
                    snake = new Snake();
                    generateFruit();
                    delay=100;
                    score=0;
                    return;

                case ArrowUp:
                    snake.move(Direction.UP);
                    return;

                case ArrowDown:
                    snake.move(Direction.DOWN);
                    return;

                case ArrowLeft:
                    snake.move(Direction.LEFT);
                    return;

                case ArrowRight:
                    snake.move(Direction.RIGHT);
                    return;
            }
        }
        snake.move();
    }

    private void checkCollisions() {
        Position head= snake.getHead();
        if(head.equals(fruit.getPosition())){
            snake.increaseSize();
            score++;
            delay-=3;
            generateFruit();
        }
        //die
        if(head.getCol()==0 || head.getCol()>=Field.getWidth()-1 || head.getRow()==0 || head.getRow()>=Field.getHeight()-1){
            gameOver();
        }
        int counter=0;
        for (Position body:snake.getFullSnake()) {
            if(counter>0){
                if(head.equals(body)){
                    gameOver();
                }
            }
            counter++;
        }
    }

    private void gameOver(){
        snake.die();
        maxScore=Math.max(score,maxScore);


    }
    private void printGameOver(){
        if(snake.isAlive()){return;}
        gameOverLoop++;
            if(gameOverLoop % 2==0){
                Field.gameOver(Terminal.Color.RED,false);
            }else{
                Field.gameOver(Terminal.Color.GREEN,false);
            }

    }
}
