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

class ValueMoveComparator implements Comparator{

    @Override
    public int compare(Object arg0, Object arg1) {
        ValueMove v0=(ValueMove) arg0;
        ValueMove v1=(ValueMove) arg1;

        return v1.moveValue-v0.moveValue; // reverse - largest is first
    }
    
}
public class GomokuMoveProposer {

    private List<ValueMove> movesToPropose;

    private int[][] nodeInfluence;

    private static int size=15;
    /*
     * contains all five node lines that might bring a win
     */

    private List<ValueMove> mList=new ArrayList<ValueMove>();
    private int row , column;
    private int length , aux , i;
    private Move m;

    private int blackStones;
    private int whiteStones;

    private char[] stoneHistory=new char[20];
    private Move[] moveHistory=new Move[20];

    private char[][] gomokuPosition;
    private boolean blackToMove;

    void inLineStuff(){
        length++;
        switch(gomokuPosition[row][column]){
            case 'b':
                blackStones++;
                break;
            case 'w':
                whiteStones++;
                break;
        }
        stoneHistory[length]=gomokuPosition[row][column];
        moveHistory[length].row=row;
        moveHistory[length].column=column;
        
        if(length >= 5){
            if(length > 5 && stoneHistory[length - 5] == 'b'){
                blackStones--;
            }
            else if(length > 5 && stoneHistory[length-5] == 'w'){
                whiteStones--;
            }
            if(blackStones == 0 || whiteStones == 0){
                for(i=4 ; i >=0 ; i--){
                    if(stoneHistory[length-i] == 'o'){
                        nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]++;
                    }
                }
            }
        }
    }

    List<Move> proposeMoves(char[][] gomokuPosition , boolean blackToMove){
        this.gomokuPosition=gomokuPosition;
        this.blackToMove=blackToMove;
        nodeInfluence=new int[15][15];
        for(row=0 ; row<15 ; row++){
            for(column=0 ; column<15 ; column++){
                nodeInfluence[row][column]=0;
            }
        }

        stoneHistory=new char[20];
        moveHistory=new Move[20];
        for(i=0 ; i<20 ; i++){
            moveHistory[i]=new Move();
        }

        //all lines
        for(row=0 ; row<size ; row++){
            length=0;
            blackStones=0;
            whiteStones=0;
            for(column=0 ; column<size ; column++){
                inLineStuff();
            }
        }

        //all columns
        for(column=0 ; column<size ; column++){
            length=0;
            blackStones=0;
            whiteStones=0;
            for(row=0 ; row<size ; row++){
                inLineStuff();
            }
        }

        //diagonals above , parallel to and including the main board matrice diagonal
        for(aux=0 ; aux<size ; aux++){
            length=0;
            blackStones=0;
            whiteStones=0;
            for(column=size-1-aux , row=0 ; column<size ; column++ , row++){
                inLineStuff();
            }
        }

        //diagonals below and parallel to the main board matrice diagonal
        for(aux=0 ; aux<size-1 ; aux++){
            length=0;
            blackStones=0;
            whiteStones=0;
            for(row=size-1-aux , column=0 ; row<=size-1 ; row++ , column++){
                inLineStuff();
            }
        }

        //diagonals above , parallel to and including the secondary board matrice diagonal
        for(aux=0 ; aux<size ; aux++){
            length=0;
            blackStones=0;
            whiteStones=0;
            for(row=aux , column=0 ; row>=0 ; row-- , column++){
                inLineStuff();
            }
        }

        //diagonals below and parallel to the secondary board matrice diagonal
        for(aux=0 ; aux<size-1 ; aux++){
            length=0;
            blackStones=0;
            whiteStones=0;
            for(column=size-1-aux , row=size-1 ; column<=size-1 ; column++ , row--){
                inLineStuff();
            }
        }
        
        for(row=0 ; row<15 ; row++){
            for(column=0 ; column < 15 ; column++){
                if(nodeInfluence[row][column] != 0){
                    mList.add(new ValueMove(row , column , nodeInfluence[row][column]));
                }
            }
        }

        Collections.sort(mList, new ValueMoveComparator());
        if(mList.size() > 12){
            mList=mList.subList(0, 11);
        }
        /*
        if(mList.size() == 0){
            mList.add(new ValueMove(7 , 7 , 0));
        }
*/
        List<Move> l=new ArrayList<Move>();
        Iterator<ValueMove> it=mList.iterator();
        ValueMove m1;
        while(it.hasNext()){
            m1=it.next();
            l.add(new Move(m1.row , m1.column));
        }
        
        return l;
    }
}
