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

public class GomokuMoveProposer {
    char[][] gomokuPosition;
    boolean blackToMove;
    
    GomokuMoveProposer(char[][] pos , boolean blackToMove){
        this.gomokuPosition=pos;
        this.blackToMove=blackToMove;
    }

    List<Move> proposeMoves(){
        List<Move> mList=new ArrayList<Move>();
        int row , column;
        Move m;

        for(row=0 ; row<15 ; row++){
            for(column=0 ; column<15 ; column++){
                if(gomokuPosition[row][column] == 'o'){
                    m=new Move(row , column);
                    mList.add(m);
                }
            }
        }

        return mList;
    }
}
