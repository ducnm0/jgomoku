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

public class GomokuGame extends BoardData{

    private char winner;
    private boolean newGame=true;
    GomokuGameHistory gameHistory;
    private boolean isGameOver;

    public GomokuGame(){
        super();
        gameHistory=new GomokuGameHistory();
        winner='o';
        isGameOver=false;
    }

    public GomokuGame(int size){
        super(size);
        gameHistory=new GomokuGameHistory();
        winner='o';
        isGameOver=false;
    }

    public GomokuGame(GomokuGameHistory ggh){
        gameHistory=ggh;
        isGameOver=true;
    }

    @Override
    public boolean moveWhite(int row , int column){
        if(isGameOver){
            return false;
        }
        boolean result=super.moveWhite(row , column);
        if(result){
            newGame=false;
            gameHistory.addMove(row , column);
            gameHistory.getNextMove();
            checkWinner('w');
        }
        return result;
    }

    @Override
    public boolean moveBlack(int row , int column){
        if(isGameOver){
            return false;
        }
        boolean result=super.moveBlack(row, column);
        if(result){
            newGame=false;
            gameHistory.addMove(row , column);
            gameHistory.getNextMove();
            checkWinner('b');
        }
        return result;
    }

    public Move previousMove(){
        return gameHistory.getPreviousMove();
    }

    public Move nextMove(){
        return gameHistory.getNextMove();
    }

    public boolean saveGame(String file){
        return gameHistory.saveGame(file);
    }

    public void checkWinner(char side){
        boolean isOver=false;
        if(side != 'w'){
            if(checkHorizontalWinner('b')){
                isOver=true;
            }
            if(!isOver && checkVerticalWinner('b')){
                isOver=true;
            }
            if(!isOver && checkMainDiagonalWinner('b')){
                isOver=true;
            }
            if(!isOver && checkSecondaryDiagonalWinner('b')){
                isOver=true;
            }
            if(isOver){
                winner='b';
                isGameOver=true;
                return;
            }
        }
        if(side != 'b'){
            if(checkHorizontalWinner('w')){
                isOver=true;
            }
            if(!isOver && checkVerticalWinner('w')){
                isOver=true;
            }
            if(!isOver && checkMainDiagonalWinner('w')){
                isOver=true;
            }
            if(!isOver && checkSecondaryDiagonalWinner('w')){
                isOver=true;
            }
            if(isOver){
                winner='w';
                isGameOver=true;
                return;
            }
        }
    }

    private boolean checkHorizontalWinner(char side){
        int row , column;
        int length=0;

        for(row=0 ; row<size ; row++){
            for(column=0 ; column<size ; column++){
                if(board[row][column] == side){
                    length++;
                }
                else{
                    length=0;
                }
                if(length == 5){
                    return true;
                }
            }
            length=0;
        }
        
        return false;
    }
    
    private boolean checkVerticalWinner(char side){
        int row , column;
        int length=0;

        for(column=0 ; column<size ; column++){
            for(row=0 ; row<size ; row++){
                if(board[row][column] == side){
                    length++;
                }
                else{
                    length=0;
                }
                if(length == 5){
                    return true;
                }
            }
            length=0;
        }
        
        return false;
    }

    private boolean checkMainDiagonalWinner(char side){
        int row , column;
        int length=0;
        int aux;

        //diagonals above , parallel to and including the main board matrice diagonal
        for(aux=0 ; aux<size ; aux++){
            for(column=size-1-aux , row=0 ; column<size ; column++ , row++){
                if(board[row][column] == side){
                    length++;
                }
                else{
                    length=0;
                }
                if(length == 5){
                    return true;
                }
            }
            length=0;
        }

        //diagonals below and parallel to the main board matrice diagonal
        for(aux=0 ; aux<size-1 ; aux++){
            for(row=size-1-aux , column=0 ; row<=size-1 ; row++ , column++){
                if(board[row][column] == side){
                    length++;
                }
                else{
                    length=0;
                }
                if(length == 5){
                    return true;
                }
            }
            length=0;
        }

        return false;
    }

    private boolean checkSecondaryDiagonalWinner(char side){
        int row , column;
        int length=0;
        int aux;

        //diagonals above , parallel to and including the secondary board matrice diagonal
        for(aux=0 ; aux<size ; aux++){
            for(row=aux , column=0 ; row>=0 ; row-- , column++){
                if(board[row][column] == side){
                    length++;
                }
                else{
                    length=0;
                }
                if(length == 5){
                    return true;
                }
            }
            length=0;
        }

        //diagonals below and parallel to the secondary board matrice diagonal
        for(aux=0 ; aux<size-1 ; aux++){
            for(column=size-1-aux , row=size-1 ; column<=size-1 ; column++ , row--){
                if(board[row][column] == side){
                    length++;
                }
                else{
                    length=0;
                }
                if(length == 5){
                    return true;
                }
            }
            length=0;
        }
        
        return false;
    }

    public boolean isNewGame(){
        return newGame;
    }

    public boolean isGameOver(){
        gameHistory.setGameFinished();

        return isGameOver;
    }

    public boolean isBlackWinner(){
        if(winner == 'b'){
            return true;
        }
        return false;
    }
}
