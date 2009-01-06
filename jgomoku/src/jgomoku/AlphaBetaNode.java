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
    public int moveValue;

    ValueMove(int row , int column , int value){
        super(row , column);
        moveValue=value;
    }
}

public class AlphaBetaNode {
    
    private char[][] boardPosition;
    private int searchDepth;
    private List<Move> proposedMoves;
    private boolean blackToMove;
    private int alpha , beta;
    private GomokuAi gAI;
    private ValueMove bestMove;
    private ValueMove currentMove;

    public AlphaBetaNode(char[][] bd , boolean blackToMove , int alpha ,
            int beta , int depth , GomokuAi gAI){
        boardPosition=bd;
        searchDepth=depth;
        this.blackToMove=blackToMove;
        this.alpha=alpha;
        this.beta=beta;
        this.gAI=gAI;
    }


    private void alphaBeta(){
         if(searchDepth==0){
            int positionValue=gAI.getPositionValue(boardPosition);
            bestMove=new ValueMove(-1 , -1 , positionValue);
            return;
        }

         if(blackToMove){
             if(gAI.isGameOver(boardPosition, 'w')){
                 bestMove=new ValueMove(-1 , -1 , -1000000);
                 return;
             }
         }
         else{
            if(gAI.isGameOver(boardPosition , 'b')){
                bestMove=new ValueMove(-1 , -1 , 1000000);
                return;
            }
         }

        proposedMoves=gAI.proposeMoves(boardPosition, blackToMove);
        Iterator<Move> it=proposedMoves.iterator();
        Move move;
        while(it.hasNext()){

            move=it.next();
            if(blackToMove){
               boardPosition[move.row][move.column]='b';
            }else{
               boardPosition[move.row][move.column]='w';
            }
            currentMove=(ValueMove) (new AlphaBetaNode(boardPosition,
                    !blackToMove,alpha,beta,searchDepth-1,gAI)).getBestMove();
            boardPosition[move.row][move.column]='o';

            if(currentMove==null || Thread.interrupted()){    
                bestMove=null;
                return;
              }

            
            alpha=maxValue(alpha,-(int)currentMove.moveValue);
            if(bestMove == null){
                bestMove=new ValueMove(move.row , move.column , alpha);
            }
            else if(bestMove.moveValue != alpha){
                bestMove.moveValue=alpha;
            }
            if(beta<=alpha){
                break;
            }
            
        }
    }
    
    public ValueMove getBestMove(){
        alphaBeta();
        return bestMove;
    }
    
    private int maxValue(int alpha,int beta){
        if(alpha>beta){
            return alpha;
        }
           return beta;
    }
}
