/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jgomoku;

/**
 *
 * @author colacristian
 */
public class TextBoardData extends BoardData {

    public TextBoardData(){
        super();
    }
    public TextBoardData(int size){
        super(size);
    }
    public char getValue(int row,int column){
        return board[row][column];
    }

}
