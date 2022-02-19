
package tetris;

import java.awt.Color;

public class Piece {
    //Variable d'instances
    private int[][] piece;
    private Color couleur;
    private int x , y ;
    
    //constructeur
    public Piece(int[][] piece , Color couleur){
        this.piece = piece;
        this.couleur = couleur;
        
        //(x et y) de la case en haut à gauche
        x=4;
        y=0;
    }
    
    public int[][] getPiece(){ return piece;}
    public Color getCouleur(){ return couleur;}
    
    public int getHeight() { return piece.length;}
    public int getWidth() { return piece[0].length ;}
    public int getPosX() { return x;}
    public int getPosY() {return y;}
    
    public void moveDown(){ y++;}
    public void moveLeft(){ x--;}
    public void moveRight(){x++;}
}
