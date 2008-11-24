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

public class AlphaBetaNode {
    public float value;
    public List<AlphaBetaNode> children;
    char[][] boardPosition;

    public AlphaBetaNode(char[][] bd){
        boardPosition=bd;
    }

    public Move getBestMove(){
        Move m=new Move(0,0);

        return m;
    }

    public AlphaBetaNode(BoardData bd , List<Move> proposedMoves){
        
    }
}
