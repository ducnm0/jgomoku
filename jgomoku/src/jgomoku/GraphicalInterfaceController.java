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
    public boolean isBlanck(int row , int column){
        if(board[row][column] == 'o'){
            return true;
        }
        return false;
    }
}

public class GraphicalInterfaceController implements UserInterface{

    private JFrame guiFrame;
    private GraphicalInterface graphicalInterface;
    private GameController gc;
    private LocalBoardData boardData;
    private boolean blackToMove;
    private boolean waitForMove;
    private boolean blackHuman , whiteHuman;
    boolean formerDrawn;
    int formerRow;
    int formerColumn;

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
        blackHuman=false;
        whiteHuman=false;
    }

    @Override
    public void printText(String text){
        guiFrame.setTitle("jgomoku " + text);
    }

    @Override
    public boolean removeBlack(int row , int column){

        return true;
    }

    @Override
    public boolean removeWhite(int row , int column){

        return true;
    }

    @Override
    public boolean moveBlack(int row , int column){

        return true;
    }

    @Override
    public boolean moveWhite(int row , int column){

        return true;
    }

    public String getUserInput(){

        return "";
    }

    @Override
    public void setCallback(GameController gc){
        this.gc=gc;
    }

    @Override
    public void getMoves(boolean whiteMoves , boolean blackMoves){
    
    }

    public void startGame(boolean blackHuman , boolean whiteHuman){
        gc.newGame(blackHuman, whiteHuman);
        waitForMove=true;
        blackToMove=true;
        graphicalInterface.startGameButton.setEnabled(false);
        graphicalInterface.nextMoveButton.setEnabled(false);
        graphicalInterface.previousMoveButton.setEnabled(false);
        graphicalInterface.loadGameButton.setEnabled(false);
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
            gc.sendPlayerInput("move " + row + " " + column);
        }
    }
        
}
