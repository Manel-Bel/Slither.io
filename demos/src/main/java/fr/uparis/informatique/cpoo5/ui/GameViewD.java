package fr.uparis.informatique.cpoo5.ui;

import fr.uparis.informatique.cpoo5.entities.Food;
import fr.uparis.informatique.cpoo5.entities.Snake;
import fr.uparis.informatique.cpoo5.game.Game;
import fr.uparis.informatique.cpoo5.game.Player;
import fr.uparis.informatique.cpoo5.game.Game.DataPlayer;
import fr.uparis.informatique.cpoo5.utils.Direction;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameViewD extends Pane{
    private VBox finParti;
    private double heigth , width;
    private Pane score_pause;
     String s = " SCORE : ";
    // class parameters
   // private final Game court;
   // private final VBox fl;
    private final Pane gameRoot; // main node of the game
    private final double scale;
    public Text scor1,gover,compteARebours;
    Button replay ;
    private final Scene gamScene;
    private final Stage gameStage;
    boolean solo,ia;
    private double lastMouseX=0;
    private double lastMouseY=0;
    Button pause = new Button("pause");
    

    private Food food;;
    private Snake snake;

    AnimationTimer timer;
    static Scene menu1;
    Game game;

    
    public GameViewD(Scene menu,Stage stage , double scale, boolean solo, boolean ia) {
        this.score_pause=new Pane();
       // this.court = court;
        //this.fl = fl;
        this.gameStage = stage;
        this.scale = scale;
        gameRoot = new Pane();
        width=Menu.winWidth * this.scale;
        heigth=Menu.winHeight * this.scale;
        score(menu);
       this.setPrefWidth(Menu.winWidth * this.scale);
        this.setPrefHeight(Menu.winHeight * this.scale+500);

        gameRoot.setPrefWidth(Menu.winWidth * this.scale-100);
        gameRoot.setPrefHeight(Menu.winHeight * this.scale-100);
        this.getChildren().add(gameRoot);
        this.game = new Game(solo, ia);
        gamScene = new Scene(this);
        gameStage.setScene(gamScene);
        this.game=new Game(solo,ia);
        animate();
       // ball = new Circle();
       // initGame();
       addSnakesToTheScreen();
       setDirectionWhenMouseMoved();
       //setKeysScene();

    }

   
     private void addSnakesToTheScreen() {
        for (DataPlayer data : game.getDataPlayer()) {
            gameRoot.getChildren().addAll(data.player.getSnake().getBody());
        }
    }
    private void score(Scene menu){
       
        scor1 = new Text();
        scor1.setText(s);
        scor1.setLayoutX(10);
        scor1.setLayoutY(20);
        scor1.setStyle("-fx-font: 20 arial;");

        score_pause.getChildren().add(scor1);
        
        //score_pause.getChildren().add(pause);
        pause.setLayoutX(500);

        pause.setLayoutY(0);
        pause.setOnAction(a->{
            System.out.println("naf");
           paneQuit( menu);
           
        });
        score_pause.setPrefWidth(Menu.winWidth * this.scale);
        score_pause.setPrefHeight(200);
       this.getChildren().add(score_pause);
      
    }
    
   
        private void setDirectionWhenMouseMoved() {
            gameRoot.setOnMouseMoved(e -> {
                // lastMouseX = e.getScreenX();
                // lastMouseY = e.getScreenY();

                double deltaX = e.getX() - width / 2;
                double deltaY = heigth / 2 - e.getY();

                double angle = Math.toDegrees(Math.atan2(deltaY, deltaX));

                angle = (angle + 360) % 360;
                game.getDataPlayer().getFirst().player.getSnake().setAngle(angle);
                //System.out.println("Angle: " + angle);


                
                // double mouseX = e.getX();
                // double mouseY = e.getY();
                //System.out.println("scree x = "+e.getScreenX()+" scree y = "+e.getScreenY()+" y ="+e.getY()+" x = "+e.getX());
       
                // Votre logique pour déterminer la direction en fonction de la position de la souris
                // Assurez-vous d'ajuster cela en fonction de la logique de votre jeu et de votre classe Snake
                // Player p = (Player) game.getDataPlayer().getLast().player;
                
        
                // if (mouseX < lastMouseX) {
                //     if (mouseY < lastMouseY) {
                //         p.getSnake().setDirection(Direction.DOWN_RIGHT);
                //     } else if (mouseY == lastMouseX) {
                //         p.getSnake().setDirection(Direction.DOWN);
                //     } else {
                //         p.getSnake().setDirection(Direction.DOWN_LEFT);
                //     }
                // } else if(mouseX > lastMouseX){
                //     if (mouseY < lastMouseY) {
                //         p.getSnake().setDirection(Direction.UP);
                //     } else if (mouseY == lastMouseX) {
                //         p.getSnake().setDirection(Direction.UP);
                //     } else {
                //         p.getSnake().setDirection(Direction.UP);
                //     }
                // }else if (mouseX == lastMouseX) {
                //     if (mouseY < lastMouseY) {
                //         p.getSnake().setDirection(Direction.RIGHT);
                //     } else if (mouseY == lastMouseX) {
                //         p.getSnake().setDirection(Direction.RIGHT);
                //     } else {
                //         p.getSnake().setDirection(Direction.LEFT);
                //     }
                    
                        
                // }      
                // lastMouseX = mouseX;
                // lastMouseY = mouseY;   
                
            });
        }
        private void setKeysScene() {
                gamScene.setOnKeyPressed(e -> {
                    var keyCode = e.getCode();
                    if (!solo && !ia) {
                        Player p = (Player) game.getDataPlayer().getLast().player;
                        switch (keyCode) {
                            case Z:
                                p.getSnake().setDirection(Direction.UP);
                                break;
                            case S:
                                p.getSnake().setDirection(Direction.DOWN);
                                break;
                            case Q:
                                p.getSnake().setDirection(Direction.LEFT);
                                break;
                            case D:
                                p.getSnake().setDirection(Direction.RIGHT);
                                break;
                            default:
                                game.getDataPlayer().getFirst().player.getSnake()
                                        .setDirection(Direction.getDirection(e.getCode()));
                                break;
                        }
                    } else
                        game.getDataPlayer().getFirst().player.getSnake()
                                .setDirection(Direction.getDirection(e.getCode()));
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
           // game.updateCell(data);
            // check collision
            if (game.isAutoCollision(data)) {
                collision();
            }
           // System.out.println("food x = "+game.getFood().getX()+"food y= "+game.getFood().getY()+"snak  x = "+data.player.getSnake().getSnackX()+"snack y = "+data.player.getSnake().getSnackY());
            if (game.checkCollisionWithFood(data)) {
               
                gameRoot.getChildren().remove(game.getFood().getFood());
                game.eatFood(data);
                scor1.setText(s+data.player.getScore());
                Snake s = data.player.getSnake();
                gameRoot.getChildren().add(s.getBody().get(s.getBody().size() - 1));
            }
        }
        return false;
    }
    private void collision() {
        gover = new Text("COLLISION");
        gover.setLayoutX(10);
        gover.setLayoutY(50);
       
        
        gover.setStyle("-fx-font: 20 arial; -fx-fill: red;");
    
        double centerX = gameRoot.getWidth() / 2 - gover.getBoundsInLocal().getWidth() / 2;
        double centerY = gameRoot.getHeight() / 2 - gover.getBoundsInLocal().getHeight() / 2;
    
        gover.setLayoutX(centerX);
        gover.setLayoutY(centerY);
    
        timer.stop();
        gameRoot.getChildren().add(gover);

    }

    public void paneQuit(Scene menu){
        Stage s=new Stage();
        VBox pause=new VBox();
        pause.setMinWidth(300);
        pause.setMinHeight(250);
        Scene pau=new Scene(pause);
        pause.setAlignment(Pos.CENTER);
        pause.setSpacing(20);
        timer.stop();



      Button nvParti=new Button("Nouvelle Parti");
      Button repprendre =new Button(" Reprendre ");
        Button menuB =new Button(" Menu ");
        pause.getChildren().addAll(nvParti,repprendre,menuB);

        menuB.setOnAction(a->{
        gameStage.setScene(menu);
           gameStage.show();

          
        });
        repprendre.setOnAction(a->{
           
            animate();
        });
        nvParti.setOnAction(a->{
          
            game.resetScore();
            game.reset();
           
            animate();
        });
        s.setScene(pau);
        s.show();





    }

    private void replay() {
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
