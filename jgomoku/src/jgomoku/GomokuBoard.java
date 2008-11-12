 /*
  *Copyright 2008 Cristian Achim
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  * http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing,
  * software distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  *
  */
package jgomoku;

public class GomokuBoard extends BoardData{

    private char winner;
    private boolean newGame=true;
    GomokuGameHistory gameHistory;

    public GomokuBoard(){
        super();
        winner='o';
    }

    public GomokuBoard(int size){
        super(size);
        winner='o';
    }

    public GomokuBoard(GomokuGameHistory ggh){
        gameHistory=ggh;
    }

    @Override
    public boolean moveWhite(int row , int column){
        boolean result=super.moveWhite(size, size);
        newGame=false;
        return result;
    }

    @Override
    public boolean moveBlack(int row , int column){
        boolean result=super.moveBlack(row, column);
        newGame=false;
        return result;
    }

    public char checkWinner(char side){
        char aux[][]=new char[size][size];
        int row,column;
        char s='o';
        int limit=size - 5;
        for(row=0 ; row<size ; row++){
            for(column=0 ; column<size ; column++){
                aux[row][column]=board[row][column];
            }
        }

        for(row=0 ; row<size ; row++){
            for(column=0 ; column<size ; column++){
                if(aux[row][column] == 'z'){
                    continue;
                }
                if(side == 'o'){
                    s=aux[row][column];
                }
                if(s != 'o' && s == side){
                    {
                        if(column <= limit){
                            if(checkHorizontalWinner(aux , row , column)){
                                winner=s;
                                return s;
                            }
                        }
                        if(row <= limit){
                            if(checkVerticalWinner(aux , row , column)){
                                winner=s;
                                return s;
                            }
                        }
                        if(row <= limit && column <= limit){
                            if(checkDiagonalWinner(aux , row , column)){
                                winner='s';
                                return s;
                            }
                        }
                    }
                }
            }
        }

        return 'o';
    }

    private boolean checkHorizontalWinner(char aux[][] , int row , int column){
        int length=1;
        char s=aux[row][column];
        
        while(column < size){
            column++;
            if(aux[row][column] != s){
                break;
            }
            length++;
            aux[row][column]='z';
        }
        if(length == 5){
            return true;
        }

        return false;
    }
    
    private boolean checkVerticalWinner(char aux[][] , int row , int column){
        int length=1;
        char s=aux[row][column];

        while(row < size){
            row++;
            if(aux[row][column] != s){
                break;
            }
            length++;
            aux[row][column]='z';
        }
        if(length == 5){
            return true;
        }

        return false;
    }

    private boolean checkDiagonalWinner(char aux[][] , int row , int column){
        int length=1;
        char s=aux[row][column];

        while(row < size && column < size){
            row++;
            column++;
            if(aux[row][column] != s){
                break;
            }
            length++;
            aux[row][column]='z';
        }
        if(length == 5){
            return true;
        }

        return false;
    }

    public boolean gameFinished(){
        if(winner == 'o'){
            return false;
        }
        return true;
    }

    public boolean isNewGame(){
        return newGame;
    }
}
