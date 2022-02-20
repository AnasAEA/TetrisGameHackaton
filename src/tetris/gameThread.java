package tetris;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anas 2
 */

// Source : https://www.cs.miami.edu/home/visser/csc329-files/Games-Threads.pdf

public class gameThread extends Thread {
    
    //constructeur de GameThread (prends le GameArea comme parametre) 
    //on utilisera le GameArea dans la fonction run() pour executer le thread (deplacement vers le bas)
    
    //Variable d'instance
    private GameArea ga;
    
    public gameThread(GameArea ga){
        this.ga = ga;
    }
    
    
    @Override
    public void run(){
        //Deplacement vers le bas 
        //Creation d'une boucle infinie 
        
        while (true){
            ga.toggleMoveDown();
        
            try {
            //sleep
            //source : https://www.journaldev.com/1020/thread-sleep-java
                Thread.sleep(1000);
            
            } catch (InterruptedException ex) {
                Logger.getLogger(gameThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
}
