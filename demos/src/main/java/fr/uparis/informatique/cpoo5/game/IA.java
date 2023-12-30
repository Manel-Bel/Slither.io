package fr.uparis.informatique.cpoo5.game;

import fr.uparis.informatique.cpoo5.entities.Snake;
import fr.uparis.informatique.cpoo5.entities.SnakeIA;

public class IA extends Person{
   
    public IA(String name , int x ,int y) {
        super(name);
        super.snake=new SnakeIA(x, y);
        
        
    }
  

    
}
