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
    private boolean isOver;

    public GomokuBoard(){
        super();
        gameHistory=new GomokuGameHistory();
        winner='o';
        isOver=false;
    }

    public GomokuBoard(int size){
        super(size);
        gameHistory=new GomokuGameHistory();
        winner='o';
        isOver=false;
    }

    public GomokuBoard(GomokuGameHistory ggh){
        gameHistory=ggh;
        isOver=true;
    }

    @Override
    public boolean moveWhite(int row , int column){
        if(isOver){
            return false;
        }
        boolean result=super.moveWhite(row , column);
        if(result){
            newGame=false;
            gameHistory.addMove(row , column);
            checkWinner('w');
        }
        return result;
    }

    @Override
    public boolean moveBlack(int row , int column){
        if(isOver){
            return false;
        }
        boolean result=super.moveBlack(row, column);
        if(result){
            newGame=false;
            gameHistory.addMove(row , column);
            checkWinner('b');
        }
        return result;
    }

    public Move previousMove(){
        Move m=gameHistory.getPreviousMove();
        return m;
    }

    public Move nextMove(){
        Move m=gameHistory.getNextMove();
        return m;
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
            if(!isOver && checkDiagonalRightWinner('b')){
                isOver=true;
            }
            if(!isOver && checkDiagonalLeftWinner('b')){
                isOver=true;
            }
            if(isOver){
                winner='b';
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
            if(!isOver && checkDiagonalRightWinner('w')){
                isOver=true;
            }
            if(!isOver && checkDiagonalLeftWinner('w')){
                isOver=true;
            }
            if(isOver){
                winner='w';
                return;
            }
        }
    }

    private boolean checkHorizontalWinner(char side){
        return false;
    }
    
    private boolean checkVerticalWinner(char side){
        return false;
    }

    private boolean checkDiagonalRightWinner(char side){
        return false;
    }

    private boolean checkDiagonalLeftWinner(char side){
        return false;
    }

    public boolean isNewGame(){
        return newGame;
    }

    public boolean isGameOver(){
        return isOver;
    }
}
