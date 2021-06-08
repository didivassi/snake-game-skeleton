package academy.mindswap.gameobjects.snake;

import academy.mindswap.field.Field;
import academy.mindswap.field.Position;

import java.util.LinkedList;

public class Snake {

    private final static int SNAKE_INITIAL_SIZE = 3;
    private Direction direction;
    private boolean alive;
    private int bodyLength;
    private final static Position INITIAL_POSITION= new Position(25,10);
    LinkedList<Position>  snakePositions= new LinkedList<>();
    public Snake(){
        alive=true;
        bodyLength=SNAKE_INITIAL_SIZE;
        direction=Direction.LEFT;

    }

    public void increaseSize() {

        bodyLength++;
        this.direction=direction;
        int multiplierCol=1;
        int multiplierRow=1;
        switch (direction){
            case UP:
                multiplierCol=0;
                multiplierRow=-1;
                break;
            case DOWN:
                multiplierCol=0;
                break;
            case LEFT:
                multiplierCol=-1;
                multiplierRow=0;
                break;
            case RIGHT:
                multiplierRow=0;

                break;
        }

        Position position= new Position(getTail().getCol()-multiplierCol,getTail().getRow()-multiplierRow);
        snakePositions.add(position);
    }

    public void move(Direction direction) {
        if(!alive){return;}


        this.direction=direction;
        int multiplierCol=1;
        int multiplierRow=1;
        switch (direction){
            case UP:
                multiplierCol=0;
                multiplierRow=-1;
                break;
            case DOWN:
                multiplierCol=0;
                break;
            case LEFT:
                multiplierCol=-1;
                multiplierRow=0;
                break;
            case RIGHT:
                multiplierRow=0;

                break;
        }

        Position previous=null;
        for (Position pos:snakePositions) {
            Position previousTemp=new Position(pos.getCol(),pos.getRow());
            if(previous==null){//head
                previous=new Position(pos.getCol()+multiplierCol,pos.getRow()+multiplierRow);
            }
            pos.setCol(previous.getCol());
            pos.setRow(previous.getRow());
            previous=previousTemp;
        }
    }

    public void move(){

        move(direction);
    }

    public void die() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public Position getHead() {
        if(snakePositions.size()==0){
            return INITIAL_POSITION;
        }
        return snakePositions.getFirst();
    }

    public Position getTail() {
        return getFullSnake().getLast();
    }

    public LinkedList<Position> getFullSnake(){

        if(snakePositions.size()>0){
            return snakePositions;
        }
        /*int multiplierCol=1;
        int multiplierRow=1;
        switch (direction){
            case UP:
                multiplierCol=0;
                break;
            case DOWN:
                multiplierRow=-1;
                multiplierCol=0;
                break;
            case LEFT:
                multiplierRow=-0;
                break;
            case RIGHT:
                multiplierRow=0;
                multiplierCol=-1;
                break;
        }*/

        for (int i = 0; i < bodyLength; i++) {
           // Position body = new Position( getHead().getCol()+i*multiplierCol,getHead().getRow()+i*multiplierRow);
            Position body = new Position( getHead().getCol()+i,getHead().getRow());
            snakePositions.add(body);
        }
       // System.out.println(snakePositions);
        return snakePositions;
    }

    public int getSnakeSize() {
        return bodyLength;
    }
}

