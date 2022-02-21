package tetris;
import javax.swing.JOptionPane;
//Main game class
public class Tetris {
    //Instance Variables
    
    private static GameForm gf;
    private static InterfacePrincipale ip ;
    private static LeaderbordForm lf;
    
    
    public static void start(){
        
        gf.setVisible(true);
        //creation du gameThread principal (mouvement en bas)
        gf.startGame();
        
    }
    
    //Affichage de leaderboard
    
    public static void showLeaderboard(){
        lf.setVisible(true);
    }
    
    
    //Affichage du Menu Principal
    
    public static void showMenu(){
        ip.setVisible(true);
    }
    
    //Affichage de Game Over 
    public static void gameOver(int score){
        //source:https://www.tutorialspoint.com/swingexamples/show_input_dialog_text.htm
        String nom = JOptionPane.showInputDialog("Game Over ! \n Entrer votre Nom.");
        
        //Enregister le nom  et le score dans leaderboard
        gf.setVisible(true);
        lf.ajouterJoueur(nom ,score);
    }
    
    public static void main(String[] args) {
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gf = new GameForm();
                ip = new InterfacePrincipale();
                lf = new LeaderbordForm();
        
               ip.setVisible(true);
            }
        });
        //Initiation des composantes

        
    }
    
}
