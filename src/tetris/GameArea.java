package tetris;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import TypesTetris.LshapeD;
import TypesTetris.LshapeG;
import TypesTetris.Sshape;
import TypesTetris.Tshape;
import TypesTetris.Zshape;
import TypesTetris.carre;
import TypesTetris.stick;
import java.util.concurrent.ThreadLocalRandom;



public class GameArea extends JPanel {
    
    private int nbLignes;
    private int nbColonnes;
    private int cellSize;
    private Color[][] grilleColor;
    
    //Initialization de l'instance de la piece
    private Piece piece; 
    
    private Piece[] pieces;
    
    public GameArea(JPanel placeholder, int colonnes){
        
        placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());
        
        nbColonnes =colonnes;
        cellSize = this.getBounds().width / nbColonnes;
        //System.out.println(cellSize);
        nbLignes = this.getBounds().height / cellSize;
        
        pieces =  new Piece[] {new LshapeD() , new LshapeG() , new Sshape() , new Tshape() , new Zshape() , new carre() , new stick()};
        
        //Definition de la noiuvelle grille des pieces Colorées (non fonctionelles)
        //par defaut en Java tout les elements de ce tableau sont null
        // Valeur null = Couleur de l'arriere plan;
      //  grilleColor[0][4] = Color.BLUE;
       //pieceGeneratrice();

    }
    
    public static int randint(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max+1);
    }
    
    //Creation de la fonction qui genere la piece
    public void pieceGeneratrice(){
        int indiceAL= piece.randint(0 , pieces.length-1);
        
        piece = pieces[indiceAL];
        piece.iniPiece();
    }
    
    //fonction qui verifie qui verifie qu'on peut plus generer de nouvelles pieces
    public boolean reachSurface(){
        if(piece.getPosY() <0){
            piece = null;
            return true;
        }
        return false;
    }
    
    
    //Creation de l'ilusion de deplacement : on calcule les noveaux cordonnées de la piece puis on renetialize tout et on redessine la piece
    //Pour faire ca on va utiliser la methode repaint 
    //source: https://www.educba.com/repaint-in-java/
    //source : https://zetcode.com/javagames/animation/
    
    public boolean toggleMoveDown(){
        if(checkBottom() == true ){
            return false;
        }
        
        piece.moveDown();
        repaint();
        return true;

    }
    
    public void toggleMoveRight(){
        if(piece == null) return;
        if(checkRight()){
            return ;
        }    
        piece.moveRight();
        repaint();

    }
    
    public void toggleMoveLetf(){
        if(piece == null) return;
        if(checkLeft()){
            return;
        }
        piece.moveLeft();
        repaint();
    }
    
    public void toggleRotation(){
        if(piece == null) return;
        piece.rotate();
        repaint();
        
        //Pour le cas de la bordure gauche
        if(piece.getPosX() < 0) piece.setX(0);
        
        //Pour le cas de la bordure droite
        if(piece.getWidth() + piece.getPosX() >= nbColonnes) piece.setX(nbColonnes - piece.getWidth());
        
        //Pour le cas de la bodure de bas
        if (piece.getPosY() + piece.getHeight() >= nbLignes ) piece.setY(nbLignes - piece.getHeight());
        
    }
    
    public boolean checkBottom() {
        //Verifier si la position de la case en haut a gauche + la longuer de la piece egale a la longuer de la grille en 
        //fonction des cases
        if (piece == null) return true;
        if(piece.getPosY() + piece.getHeight() == this.getBounds().height / cellSize) 
            return true;
        
        
        int w = piece.getWidth();
        int h = piece.getHeight();
        
        
        for (int col = 0 ; col < w ; col++){
            for(int row =h-1 ; row >= 0 ; row--){
                
                if(piece.getPiece()[row][col] != 0){
                    
                    int x = col + piece.getPosX();// colonnes
                    int y = row + piece.getPosY() +1 ;//lignes
                    if(y < 0) break; 
                    if(grilleColor[y][x] != null){
                        return true;
                    }
                    break;
                }
            }
        }        
        
        return false;
    }
    
    public boolean checkLeft(){
        if(piece.getPosX() == 0){
            return true;
        }
        return false;
    }
    
    public boolean checkRight(){
        if(piece.getWidth() + piece.getPosX() == nbColonnes){
            return true;
        }
        return false;
    }
    //Fonction qui verifie qui trouve et supprime les lignes completes
    
    public int supprimerLigne(){
        int nbrLignesSup = 0;
        boolean ligneComplete;
        
        for (int r=nbLignes -1 ; r>=0; r--){
            
            ligneComplete = true;
            
            for (int c =0; c<nbColonnes; c++){
                if(grilleColor[r][c] == null){
                    ligneComplete= false;
                    break;
                }
                
            }        
            
            if(ligneComplete){
               suppLigne(r);
               nbrLignesSup++;
               deplacementLignes(r);
               suppLigne(0);
               //Après supprimer une ligne on se deplace de r-1 mais la ligne 
               //suivante a verifier c'est pas r-1 mais c'est r dabord
               
               r++;
               repaint();
            }
        }
        return nbrLignesSup;
        
    }
    
    private void suppLigne(int r){
        for (int i=0 ; i < nbColonnes ; i++){
                    grilleColor[r][i] = null;
                }        
    }
    
    
    //Fonction qui gere le deplacement de tout les lignes au dessus de celle supprimée
    //int r : c'est la position de la ligne supprimée
    private void deplacementLignes(int r){
        
        for (int  x = r; x>0 ; x--){ //  On peut pas supprimer la ligne 0 (car tu perds)
            for (int col = 0 ; col < nbColonnes ; col++){
                grilleColor[x][col] = grilleColor[x-1][col];
            }
        }
    }
    
    //Fonction qui renitialize la grille des couleurs
    public void initGrilleCouleur(){
        grilleColor = new Color[nbLignes][nbColonnes];
    }
    
    
    
    //Fonction pour transformer la piece a une suite de couleurs defenit dans la grille de couleurs
    public void passagePieceACouleur() {
        int[][] block = piece.getPiece();
        
        int longuerP = piece.getHeight();
        int largeurP = piece.getWidth();
        
        int posX = piece.getPosX();
        int posY = piece.getPosY();
        
        Color couleurP = piece.getCouleur();
        
        for (int i=0 ; i<longuerP ; i++){
            for (int j=0 ; j< largeurP ; j++){
                
                if(block[i][j] == 1){
                    grilleColor[posY + i][posX + j] = couleurP;
                }
            }
        }
        
    }
    
    
    
    
    private void dessinerPiece(Graphics g) {
        
       int h = piece.getHeight();
       int w= piece.getWidth();
       int[][] tetris = piece.getPiece();
        
        for (int row =0 ; row < h; row++){
            for (int col=0 ; col< w;col++){
                if(tetris[row][col] == 1){
                    int x = (piece.getPosX()+ col)*cellSize;
                    int y= (piece.getPosY() + row) * cellSize;
                    
                    g.setColor(piece.getCouleur());
                    g.fillRect(x, y, cellSize, cellSize);
                    g.setColor(Color.black);
                    g.drawRect(x,y,  cellSize, cellSize);
               }
            }
        }
    }
    
    private void dessinerGrilleColor(Graphics g){
        Color couleur;
        for (int l= 0 ; l<nbLignes ; l++){
            for (int c=0 ; c< nbColonnes; c++){
                couleur = grilleColor[l][c];
                if (couleur != null){
                    
                    int x = c * cellSize;
                    int y = l * cellSize;
                    
                    g.setColor(couleur);
                    g.fillRect(x, y, cellSize, cellSize);
                    g.setColor(Color.black);
                    g.drawRect(x,y,  cellSize, cellSize);
                    
                }
            }
            
            }
    }
    
    
    @Override 
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        /*
        for (int y= 0 ; y<nbLignes ; y++){
            for (int x=0 ; x< nbColonnes; x++){
                g.drawRect(x * cellSize,y*cellSize,  cellSize, cellSize);
            }
        }*/
        dessinerGrilleColor(g);
        dessinerPiece(g);
    }

    
}
