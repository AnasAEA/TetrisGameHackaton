/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TypesTetris;

/**
 *
 * @author Anas 2
 */
import java.awt.Color;
import tetris.Piece;

/**
 *
 * @author Anas 2
 */
public class Tshape extends Piece{
    public Tshape(){
        super(new int[][] { {0,1,0} , {1 ,1,1}} ,Color.pink);
    } 
}