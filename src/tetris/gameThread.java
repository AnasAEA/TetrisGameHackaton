package tetris;

import java.util.logging.Level;
import java.util.logging.Logger;


// Source : https://www.cs.miami.edu/home/visser/csc329-files/Games-Threads.pdf

public class gameThread extends Thread {
    
    //constructeur de GameThread (prends le GameArea comme parametre) 
    //on utilisera le GameArea dans la fonction run() pour executer le thread (deplacement vers le bas)
    
    //Variable d'instance
    private GameArea ga;
    private GameForm gf;
            
    private int score ;
    private int level =1;
    private int scoreParNiveau = 5;
    private int vitesseJeu=100;
    private int durre=500;
    
    
    
    public gameThread(GameArea ga , GameForm gf){
        this.ga = ga;
        this.gf = gf;
        
        //renitialization du score et du niveau
        gf.changeScore(score);
        gf.changeLevel(level);
        
    }
    
    
    @Override
    public void run(){
        //Deplacement vers le bas 
        //Creation d'une boucle infinie 
        
        while (true){
            ga.pieceGeneratrice();
            
            while (ga.toggleMoveDown()){
                try {
                    Thread.sleep(durre);
                } catch (InterruptedException ex) {
                    return ;
                }
            }
            
            if (ga.reachSurface()){
                Tetris.gameOver(score);
                break;
            }
            
            ga.passagePieceACouleur();
            score += 50 * ga.supprimerLigne();
            gf.changeScore(score);
            int lvl = score / scoreParNiveau +1;
            if (lvl > level){
                level = lvl;
                gf.changeLevel(level);
                //Augmenter la vitesse de deplacement en bas
                durre += vitesseJeu;
            }

        }

    }
    
}
