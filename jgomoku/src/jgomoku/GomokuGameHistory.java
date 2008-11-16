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

import java.util.*;

public class GomokuGameHistory {
    private ArrayList moves;
    private int currentMove;
    private boolean gameFinished;
    private boolean blackWin;

    GomokuGameHistory(){
        moves=new ArrayList();
        gameFinished=false;
        currentMove=0;
    }

    public void addMove(int row , int column){
        Move m=new Move(row , column);
        moves.add(m);
        currentMove++;
    }

    public void setGameFinished(boolean blackWin){
        gameFinished=true;
        this.blackWin=blackWin;
    }

    public Move getNextMove(){
        if(! gameFinished){
            return null;
        }
        if(currentMove >= moves.size() - 1){
            return null;
        }
        currentMove++;
        return (Move) moves.get(currentMove);
    }

    public Move getPreviousMove(){
        if(! gameFinished){
            return null;
        }
        if(currentMove==0){
            return null;
        }
        currentMove--;
        return (Move) moves.get(currentMove);
    }

    public boolean loadGame(String filename){
        return false;
    }

    public boolean saveGame(String file){
        return false;
    }
}
