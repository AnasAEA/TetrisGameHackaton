package tetris;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;



public class GameArea extends JPanel {
    
    private int nbLignes;
    private int nbColonnes;
    private int cellSize;
    
    //Initialization de l'instance de la piece
    private Piece piece; 
    
    public GameArea(JPanel placeholder, int colonnes){
        
        placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());
        
        nbColonnes =colonnes;
        cellSize = this.getBounds().width / nbColonnes;
        nbLignes = this.getBounds().height / cellSize;
        
        pieceGeneratrice();
    }
    
    //Creation de la fonction qui genere la piece
    public void pieceGeneratrice(){  
        piece = new Piece(new int[][] {{1},{1},{1},{1}} , Color.RED);
    }
    
    public void toggleMoveDown(){
        piece.moveDown();
        repaint();
    }&
    
    private void dessinerPiece(Graphics g) {
        
       int h = piece.getHeight();
       int w= piece.getWidth();
       int[][] tetris = piece.getPiece();
        
        for (int row =0 ; row < h; row++){
            for (int col=0 ; col< w;col++){
                if(tetris[row][col] == 1){
                    int x = (piece.getPosX()+ col)*cellSize;
                    int y= (piece.getPosY() + row) * cellSize;
                    
                    g.setColor(Color.RED);
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
        for (int y= 0 ; y<nbLignes ; y++){
            for (int x=0 ; x< nbColonnes; x++){
                g.drawRect(x * cellSize,y*cellSize,  cellSize, cellSize);
            }
        }
        dessinerPiece(g);
    }

    
}
