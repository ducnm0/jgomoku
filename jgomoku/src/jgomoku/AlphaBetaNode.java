 /*
  *Copyright 2008 Cristian Cola
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
    ValueMove(float value){
        super(0,0);
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
        proposedMoves=gAI.proposeMoves(boardPosition, blackToMove);
        currentMove=(ValueMove) (new AlphaBetaNode(boardPosition,blackToMove,alpha,beta,searchDepth-1,gAI)).getBestMove();

        if(currentMove==null || Thread.interrupted() ){
                bestMove=null;
                return;
        }
         if(searchDepth==0){
            int value=gAI.getPositionValue(boardPosition);
            bestMove=new ValueMove(value);
        }

        Iterator it=proposedMoves.iterator();
        while(it.hasNext()){
            Move move=(Move)it.next();
            if(blackToMove){
               boardPosition[move.row][move.column]='b';
            }else{
               boardPosition[move.row][move.column]='w';
            }
            currentMove=(ValueMove) (new AlphaBetaNode(boardPosition,blackToMove,alpha,beta,searchDepth-1,gAI)).getBestMove();
            boardPosition[move.row][move.column]='o';
            if(currentMove==null || Thread.interrupted()){
                bestMove=null;
                return;
              }
            
            alpha=maxValue(alpha,-(int)currentMove.moveValue);
            if(beta<=alpha){
                break;
            }
            
        }
    }


    
    public ValueMove getBestMove(){
        alphaBeta();
        return bestMove;
    }

    public AlphaBetaNode(BoardData bd , List<Move> proposedMoves){
        
    }

    
    private int maxValue(int alpha,int beta){
        if(alpha>beta){
            return alpha;
        }
           return beta;
        
    }
}
