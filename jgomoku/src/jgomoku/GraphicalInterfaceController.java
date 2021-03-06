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

import javax.swing.*;

class LocalBoardData extends BoardData{

    public boolean isBlack(int row , int column){
        if(board[row][column] == 'b'){
            return true;
        }
        return false;
    }

    public boolean isWhite(int row , int column){
        if(board[row][column] == 'w'){
            return true;
        }
        return false;
    }

    public boolean isBlanck(int row , int column){
        if(board[row][column] == 'o'){
            return true;
        }
        return false;
    }

    public void reset(){
        int row , column;
        for(row=0 ; row<this.size ; row++){
            for(column=0 ; column<this.size ; column++){
                this.board[row][column]='o';
            }
        }
    }
}

public class GraphicalInterfaceController implements UserInterface{

    private JFrame guiFrame;
    private GraphicalInterface graphicalInterface;
    private GameController gc;
    private LocalBoardData boardData;
    private boolean blackToMove;
    private boolean waitForMove;
    private boolean formerDrawn;
    private int formerRow;
    private int formerColumn;

    GraphicalInterfaceController(int maxrow , int maxcolumn){

        graphicalInterface=new GraphicalInterface(this , maxrow , maxcolumn);
        graphicalInterface.setDoubleBuffered(true);
        guiFrame=new JFrame("jgomoku");
        guiFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        guiFrame.setContentPane(this.graphicalInterface);
        guiFrame.pack();
        guiFrame.setResizable(false);
        guiFrame.setVisible(true);
        boardData=new LocalBoardData();
        formerDrawn=false;
    }

    @Override
    public void printText(String text){
        guiFrame.setTitle("jgomoku " + text);
    }

    @Override
    public void removeBlack(int row , int column){
        boardData.removeBlack(row, column);
        graphicalInterface.drawBackgroundCell(row, column);
    }

    @Override
    public void removeWhite(int row , int column){
        boardData.removeWhite(row, column);
        graphicalInterface.drawBackgroundCell(row, column);
    }

    @Override
    public void moveBlack(int row , int column){
        boardData.moveBlack(row, column);
        graphicalInterface.drawBlackStone(row, column);
    }

    @Override
    public void moveWhite(int row , int column){
        boardData.moveWhite(row, column);
        graphicalInterface.drawWhiteStone(row, column);
    }

    @Override
    public void setCallback(GameController gc){
        this.gc=gc;
    }

    @Override
    public void getBlackMove(int whiteMoveRow , int whiteMoveColumn){
        waitForMove=true;
        blackToMove=true;
        moveWhite(whiteMoveRow , whiteMoveColumn);
    }

    @Override
    public void getWhiteMove(int blackMoveRow , int blackMoveColumn){
        moveBlack(blackMoveRow , blackMoveColumn);
        waitForMove=true;
        blackToMove=false;
    }

    @Override
    public void waitAiMove(boolean blackMove , int row , int column){
        waitForMove=false;
        if(blackMove){
            moveBlack(row , column);
        }
        else{
            moveWhite(row , column);
        }
        blackToMove=! blackToMove;
    }

    @Override
    public void gameFinished(boolean blackMove , int row , int column){
        waitForMove=false;
        this.boardData.reset();
        if(blackMove){
            moveBlack(row , column);
        }
        else{
            moveWhite(row , column);
        }
        graphicalInterface.saveGameButton.setEnabled(true);
        graphicalInterface.previousMoveButton.setEnabled(true);
        graphicalInterface.nextMoveButton.setEnabled(true);
    }

    public void startGame(boolean blackHuman , boolean whiteHuman){
        graphicalInterface.startGameButton.setEnabled(false);
        graphicalInterface.nextMoveButton.setEnabled(false);
        graphicalInterface.previousMoveButton.setEnabled(false);
        graphicalInterface.loadGameButton.setEnabled(false);
        this.waitForMove=blackHuman;
        blackToMove=true;
        graphicalInterface.newGameButton.setEnabled(true);
        gc.newGame(blackHuman, whiteHuman); // last for thread safety
    }

    public void mouseMoved(int row , int column){
        boolean aux=false;
        
        if(waitForMove){
            if(row == -1 && column == -1){
                if(formerDrawn == true){
                    graphicalInterface.drawBackgroundCell(formerRow, formerColumn);
                    formerDrawn=false;
                }
            }
            else{
                if(row != formerRow || column != formerColumn){
                    if(boardData.isBlanck(row, column)){
                        if(blackToMove){
                            graphicalInterface.drawBlackStone(row, column);
                        }
                        else{
                            graphicalInterface.drawWhiteStone(row, column);
                        }
                        aux=true;
                    }
                    
                    if(formerDrawn){
                        graphicalInterface.drawBackgroundCell(formerRow, formerColumn);
                        if(aux){
                            formerRow=row;
                            formerColumn=column;
                        }
                    }
                    else if(aux){
                        formerDrawn=true;
                        formerRow=row;
                        formerColumn=column;
                    }
                }
            }
        }
    }

    public void mouseClicked(int row , int column){
        if(waitForMove){
            if(boardData.isBlanck(row, column) ||
                    (row == formerRow && column == formerColumn)){

                this.formerDrawn=false;
                gc.sendPlayerInput("move " + row + " " + column);
            }
        }
    }

    public void getNextMove() {
        gc.sendPlayerInput("next");
    }

    public void getPreviousMove() {
        gc.sendPlayerInput("previous");
    }

    public void newGameButtonClicked() {
        if(gc.removeOldGame()){
            graphicalInterface.newGameButton.setEnabled(false);
            graphicalInterface.saveGameButton.setEnabled(false);
            graphicalInterface.nextMoveButton.setEnabled(false);
            graphicalInterface.previousMoveButton.setEnabled(false);
            graphicalInterface.drawAllBackgroundCells();
            graphicalInterface.startGameButton.setEnabled(true);
            graphicalInterface.loadGameButton.setEnabled(true);
            this.waitForMove=false;
            this.boardData.reset();
        }
        
    }

    public void saveFile(String fileOnDisk) {
        gc.sendPlayerInput("save  " + fileOnDisk);
    }

    public void loadFile(String fileOnDisk){
        gc.sendPlayerInput("load " + fileOnDisk);
    }

    @Override
    public void setReplayMode() {
        graphicalInterface.nextMoveButton.setEnabled(true);
        graphicalInterface.previousMoveButton.setEnabled(true);
        graphicalInterface.newGameButton.setEnabled(true);
        graphicalInterface.saveGameButton.setEnabled(false);
        graphicalInterface.loadGameButton.setEnabled(false);
        graphicalInterface.startGameButton.setEnabled(false);
    }
        
}
