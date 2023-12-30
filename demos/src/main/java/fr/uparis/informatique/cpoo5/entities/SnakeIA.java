package fr.uparis.informatique.cpoo5.entities;


import fr.uparis.informatique.cpoo5.utils.Coordinate;
import fr.uparis.informatique.cpoo5.utils.Direction;

import javafx.scene.shape.Rectangle;

public class SnakeIA extends SnakeAbstract{
    

    public SnakeIA(int x, int y) {
        super(x,y);
        
    }

   
    
    // setting the direction of the snake
    public void setDirectionIA(Coordinate coordinateFood , Coordinate coordinateIA) {
        if(coordinateFood.x==coordinateIA.x){
            if(coordinateFood.y>coordinateIA.y){
                this.direction = Direction.RIGHT;
            }else{
                this.direction = Direction.LEFT;
            }
        }else if(coordinateFood.y==coordinateIA.y){
            if(coordinateFood.x>coordinateIA.x){
                this.direction = Direction.DOWN;
            }else{
                this.direction = Direction.UP;
            }
        }else{
            if(coordinateFood.x>coordinateIA.x){
                this.direction = Direction.DOWN;
            }else{
                this.direction = Direction.UP;
            }
       }
    }

    

    // to move the snake
    public void moveP(int widthBound, int heightBound , Coordinate coordinateFood ,Coordinate coordinateIA) {
      setDirectionIA(coordinateFood , coordinateIA);
        if (this.direction == null) {
            return;
        }
        switch (direction) {
            case UP:
                snakeY -= speed;
                if (snakeY < 0) {
                    snakeY = heightBound - snakeHeight;
                }
                break;
            case DOWN:
                snakeY += speed;
                if (snakeY > heightBound - snakeHeight) {
                    snakeY = 0;
                }
                break;
            case LEFT:
                snakeX -= speed;
                if (snakeX < 0) {
                    snakeX = widthBound - snakeWidth;
                }
                break;
            case RIGHT:
                snakeX += speed;
                if (snakeX > widthBound - snakeWidth) {
                    snakeX = 0;
                }
                break;
            default:
                System.out.println("Invalid direction");
                return;
        }
        // move the body
        for (int i = body.size() - 1; i > 0; i--) {
            // get the priview
            Rectangle temp = body.get(i - 1);
            // update the position of i
            body.get(i).setX(temp.getX());
            body.get(i).setY(temp.getY());

        }
        // update the position of the head
        snakeRec.setX(snakeX);
        snakeRec.setY(snakeY);
    }

} 
    

