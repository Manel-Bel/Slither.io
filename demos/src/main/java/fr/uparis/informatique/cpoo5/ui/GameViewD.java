package fr.uparis.informatique.cpoo5.ui;

import fr.uparis.informatique.cpoo5.entities.Food;
import fr.uparis.informatique.cpoo5.entities.Snake;
import fr.uparis.informatique.cpoo5.game.Game;
import fr.uparis.informatique.cpoo5.game.Player;
import fr.uparis.informatique.cpoo5.game.Game.DataPlayer;
import fr.uparis.informatique.cpoo5.utils.Direction;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameViewD {
    private VBox finParti;
    private double heigth , width;
    // class parameters
   // private final Game court;
   // private final VBox fl;
    private final Pane gameRoot; // main node of the game
    private final double scale;
    public Text scor1,scor,gover,compteARebours;
    Button replay ;
    private final Scene gamScene;
    private final Stage gameStage;
    boolean solo,ia;
    

    private Food food;;
    private Snake snake;

    AnimationTimer timer;
    static Scene menu1;
    Game game;

    public GameViewD(Stage stage , double scale, boolean solo, boolean ia) {
       // this.court = court;
        //this.fl = fl;
        this.gameStage = stage;
        this.scale = scale;
        gameRoot = new Pane();
        width=Menu.winWidth * this.scale;
        heigth=Menu.winHeight * this.scale;
       

        gameRoot.setPrefWidth(Menu.winWidth * this.scale);
        gameRoot.setPrefHeight(Menu.winHeight * this.scale);
        this.game = new Game(solo, ia);
        gamScene = new Scene(gameRoot);
        gameStage.setScene(gamScene);
        this.game=new Game(solo,ia);
        animate();
       // ball = new Circle();
       // initGame();
       addSnakesToTheScreen();
       setKeysScene();

    }

    // private void initGame() {
    //     this.food=new Food(0, 0);
    //     this.snake=new Snake(10, 0);
    //     gameRoot.getChildren().add(food.getFood());
    //     gameRoot.getChildren().add(snake.getSnack());

    // }

     private void addSnakesToTheScreen() {
        for (DataPlayer data : game.getDataPlayer()) {
            gameRoot.getChildren().addAll(data.player.getSnake().getBody());
        }
    }

    private void setKeysScene() {
            gamScene.setOnMouseMoved(e -> {
                
                double mouseX = e.getScreenX();;
                double mouseY = e.getScreenY();;
                System.out.println("ok ok");
                if (!solo && !ia) {
                    Player p = (Player) game.getDataPlayer().getLast().player;

                    double snakeX = p.getSnake().getSnackX();
                    double snakeY = p.getSnake().getSnackY();

                    // switch (keyCode) {
                    //     case Z:
                    //         p.getSnake().setDirection(Direction.UP);
                    //         break;
                    //     case S:
                    //         p.getSnake().setDirection(Direction.DOWN);
                    //         break;
                    //     case Q:
                    //         p.getSnake().setDirection(Direction.LEFT);
                    //         break;
                    //     case D:
                    //         p.getSnake().setDirection(Direction.RIGHT);
                    //         break;
                    //     default:
                    //         game.getDataPlayer().getFirst().player.getSnake()
                    //                 .setDirection(Direction.getDirection(e.getCode()));
                    //         break;
                    // }
                } else{
                    Player p = (Player) game.getDataPlayer().getFirst().player;
                    double snakeX = p.getSnake().getSnackX();
                    double snakeY = p.getSnake().getSnackY();

                    if (mouseX < snakeX) {
                        if (mouseY < snakeY) {
                            p.getSnake().setDirection(Direction.UP_LEFT);
                        } else if (mouseY > snakeY + p.getSnake().getSnakeHeight()) {
                            p.getSnake().setDirection(Direction.DOWN_LEFT);
                        } else {
                            p.getSnake().setDirection(Direction.LEFT);
                        }
                    } else if (mouseX > snakeX + p.getSnake().getSnakeWidth()) {
                        if (mouseY < snakeY) {
                            p.getSnake().setDirection(Direction.RIGHT);
                        } else if (mouseY > snakeY + p.getSnake().getSnakeHeight()) {
                            p.getSnake().setDirection(Direction.DOWN_RIGHT);
                        } else {
                            p.getSnake().setDirection(Direction.RIGHT);
                        }
                    } else {
                        if (mouseY < snakeY) {
                            p.getSnake().setDirection(Direction.UP);
                        } else if (mouseY > snakeY + p.getSnake().getSnakeHeight()) {
                            p.getSnake().setDirection(Direction.DOWN);
                        }
                    }
                }
            });
            
                   
        }

   

    private boolean update() {
      
        if (game.getFood() == null) {
           // System.out.println("gameview generation of food");
            gameRoot.getChildren().add(game.generateFood(this.width,this.heigth));
        }
        // move all the snakes
        for (DataPlayer data : game.getDataPlayer()) {
            data.player.moveSnake(game.getCoordFood(), data.occupiedCells.getFirst(),width,heigth);
            game.updateCell(data);
            // check collision
            if (game.isAutoCollision(data)) {
                // endOfGame = true;
                return true;
            }
           // System.out.println("food x = "+game.getFood().getX()+"food y= "+game.getFood().getY()+"snak  x = "+data.player.getSnake().getSnackX()+"snack y = "+data.player.getSnake().getSnackY());
            if (game.checkCollisionWithFood(data)) {
                System.out.println("check coll with food gameView");
                gameRoot.getChildren().remove(game.getFood().getFood());
                game.eatFood(data);
                Snake s = data.player.getSnake();
                gameRoot.getChildren().add(s.getBody().get(s.getBody().size() - 1));
            }
        }
        return false;
    }
    private void animate() {
            timer = new Animation();
            timer.start();
        }
    


    class Animation extends AnimationTimer {
        long last = 0;
        private final long waitInterval = 500_000_000; // 400ms

        @Override
        public void handle(long now) {
            // gameRoot.requestFocus();
            if (last == 0) {
                last = now;
                return;
            }
            // double deltaT = (now - last) / 1e9;
            if (now - last >= waitInterval) {
                if (update()) { // game ended
                    System.out.println("collision animation ends...");
                    stop();
                }
                last = now;
            }
        }

       
    }



}
