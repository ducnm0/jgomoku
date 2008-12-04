 /*
  *Copyright 2008 Cristian Cols
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

class ValueMove extends Move{
    public float moveValue;

    ValueMove(int row , int column , float value){
        super(row , column);
        moveValue=value;
    }
}

public class AlphaBetaNode {
    
    private int positionValue;
    private List<AlphaBetaNode> children;
    private char[][] boardPosition;
    private int searchDepth;
    private List<Move> proposedMoves;
    private boolean blackToMove;
    private int alpha , beta;
    private GomokuAi gAI;
    private ValueMove bestMove;
    private ValueMove currentMove;

    public AlphaBetaNode(char[][] bd , boolean blackToMove , int alpha , int beta , int depth , GomokuAi gAI){
        boardPosition=bd;
        searchDepth=depth;
        this.blackToMove=blackToMove;
        this.alpha=alpha;
        this.beta=beta;
        this.gAI=gAI;
    }


    private void alphaBeta(){
        /*
         *
         *TODO
         * call moveproposer -> result in proposedMoves
         * recurse by creating new nodes for each move in proposedMoves
         *
         * currentMove=(new AlphaBetaNode(...)).getBestMove();
         * compare currentMove to bestMove and alphabeta
         *
         * or calculate position value if depth is 0
         * return best move with position value attached and make alpha beta decisions
         * check for thread.interrupted and return null if so all the way up
         *  simply:
         * if(Thread.interrupted){
         *  return null
         * }
         *(to implement stoping the thread if the user exits the current game on the ai move)
         *
         */
    }


    
    public Move getBestMove(){
        alphaBeta();
        return bestMove;
    }

    public AlphaBetaNode(BoardData bd , List<Move> proposedMoves){
        
    }
}
