/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgomoku;

/**
 *
 * @author colacristian
 */
public class BoardData {
    protected static char board[][];
    protected static int size;

    
 public BoardData(){
     int row , column;
        size=15;
        board=new char[15][15];
        for(row=0 ; row<15 ; row++){
            for(column=0 ; column<15 ; column++){
                board[row][column]='o';
            }
        }
 }
 public BoardData(int size){
        int row , column;

        board=new char[size][size];
        for(row=0 ; row<size ; row++){
            for(column=0 ; column<size ; column++){
                board[row][column]='o';
            }
        }
 }
  public boolean moveWhite(int row , int column){
        if(board[row][column] == 'o'){
            board[row][column]='w';
            return true;
        }
        return false;
    }

  public boolean moveBlack(int row , int column){
        if(board[row][column] == 'o'){
            board[row][column]='b';
            return true;
        }
        return false;
    }

 public boolean removeWhite(int row , int column){
        if(board[row][column] == 'w'){
            board[row][column]='o';
            return true;
        }
        return false;
    }

    public boolean removeBlack(int row , int column){
        if(board[row][column] == 'b'){
            board[row][column]='b';
            return true;
        }
        return false;
    }
}
