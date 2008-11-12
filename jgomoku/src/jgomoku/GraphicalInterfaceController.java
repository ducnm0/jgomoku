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
    private GameController gc;
    private LocalBoardData boardData;
    private boolean blackToMove;

    GraphicalInterfaceController(int maxrow , int maxcolumn){

        GraphicalInterface guiPanel=new GraphicalInterface(this , maxrow , maxcolumn);
        guiFrame=new JFrame("jgomoku");
        guiFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        guiFrame.setContentPane(guiPanel);
        guiFrame.pack();
        guiFrame.setResizable(false);
        guiFrame.setVisible(true);
        boardData=new LocalBoardData();
    }

    public void printText(String text){

    }

    public boolean removeBlack(int row , int column){

        return true;
    }

    public boolean removeWhite(int row , int column){

        return true;
    }

    public boolean moveBlack(int row , int column){

        return true;
    }

    public boolean moveWhite(int row , int column){

        return true;
    }

    public String getUserInput(){

        return "";
    }

    public void setCallback(GameController gc){
        this.gc=gc;
    }

    public void getMoves(boolean whiteMoves , boolean blackMoves){
    
    }

    public void startGame(boolean blackHuman , boolean whiteHuman){
        
    }
}
