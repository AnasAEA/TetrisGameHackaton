
package tetris;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public class Piece {
    //Variable d'instances
    private int[][] piece;
    private Color couleur;
    private int x , y ;
    private int[][][] pieces;
    private int nbreRotation;
    private Color[] couleursPossibles = {Color.green , Color.red , Color.MAGENTA , Color.CYAN}; 
    //constructeur
    public Piece(int[][] piece , Color couleur){
        this.piece = piece;
        this.couleur = couleur;
        iniRotation();
    }
    
   private void iniRotation(){
       pieces = new int[4][][];
       

       //4 rotations
       for (int i=0 ; i<4 ;i++){
           
            int lignes = piece[0].length;
            int colonnes = piece.length;
        
           //Forme de la piece a chaque rotation
           pieces[i] = new int[lignes][colonnes];
           
           for (int y=0 ; y< lignes; y++){
               for (int x=0 ; x<colonnes ; x++){
                   pieces[i][y][x] = piece[colonnes - x - 1][y];
               }
           }
           piece = pieces[i];
       }
       
        
   }
    public static int randint(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max+1);
    }
    //Pouvoir intializer la piece au dessus de la grille
     
    public void iniPiece(){
        nbreRotation = randint(0,3);
        piece = pieces[nbreRotation];
       y= -getHeight();
       x=4;
       
       couleur = couleursPossibles[randint(0 , couleursPossibles.length-1)];
    }
    
    
    public int[][] getPiece(){ return piece;}
    public Color getCouleur(){ return couleur;}
    
    public int getHeight() { return piece.length;}
    public int getWidth() { return piece[0].length ;}
    public int getPosX() { return x;}
    public int getPosY() {return y;}
    
    //Setters
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    
    
    public void moveDown(){ y++;}
    public void moveLeft(){ x--;}
    public void moveRight(){x++;}
    
    public void rotate() { 
        //Passage a la rotation suivante 90 degre
        nbreRotation++;
        if(nbreRotation > 3 ) nbreRotation =0;
        piece = pieces[nbreRotation];
        
    }
}
