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

        return Math.abs(v1.moveValue)-Math.abs(v0.moveValue); // reverse - largest is first
    }
    
}
public class GomokuMoveProposer {

    private List<ValueMove> movesToPropose;

    private int[][] nodeCoefficient;
    /*
     * number of possible winning lines that contain the node
     */

    private int[][] nodeInfluence;

    private static int size=15;
    /*
     * contains all five node lines that might bring a win
     */

    List<Move> proposeMoves(char[][] gomokuPosition , boolean blackToMove){
        List<Move> mList=new ArrayList<Move>();
        int row , column;
        int length , aux , i;
        Move m;

        int blackStones;
        int whiteStones;

        nodeInfluence=new int[15][15];
        nodeCoefficient=new int[15][15];

        char[] stoneHistory=new char[20];
        Move[] moveHistory=new Move[20];
        for(i=0 ; i<20 ; i++){
            moveHistory[i]=new Move();
        }

        /*
        for(row=0 ; row<15 ; row++){
            for(column=0 ; column<15 ; column++){
                if(gomokuPosition[row][column] == 'o'){
                    m=new Move(row , column);
                    mList.add(m);
                }
            }
        }
         */

        //all lines
        for(row=0 ; row<size ; row++){
            length=0;
            blackStones=0;
            whiteStones=0;
            for(column=0 ; column<size ; column++){
                length++;
                
                switch(gomokuPosition[row][column]){
                    case 'b':
                        blackStones++;
                        stoneHistory[length]='b';
                        break;
                    case 'w':
                        whiteStones++;
                        stoneHistory[length]='w';
                        break;
                    default:
                        stoneHistory[length]='o';
                }
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
                        for(i=5 ; i >=0 ; i--){
                            nodeCoefficient[moveHistory[length-i].row][moveHistory[length-i].column]++;
                            if(blackStones == 0){
                                nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]=nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]+3*blackStones/3;
                            }
                            else{
                                nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]=nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]-3*whiteStones/3;
                            }
                        }
                    }
                }
            }
        }

        //all columns
        for(column=0 ; column<size ; column++){
            length=0;
            blackStones=0;
            whiteStones=0;
            for(row=0 ; row<size ; row++){
                length++;
                switch(gomokuPosition[row][column]){
                    case 'b':
                        blackStones++;
                        stoneHistory[length]='b';
                        break;
                    case 'w':
                        whiteStones++;
                        stoneHistory[length]='w';
                        break;
                    default:
                        stoneHistory[length]='o';
                }
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
                        for(i=5 ; i >=0 ; i--){
                            nodeCoefficient[moveHistory[length-i].row][moveHistory[length-i].column]++;
                            if(blackStones == 0){
                                nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]=nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]+3*blackStones/3;
                            }
                            else{
                                nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]=nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]-3*whiteStones/3;
                            }
                        }
                    }
                }
            }
        }

        //diagonals above , parallel to and including the main board matrice diagonal
        for(aux=0 ; aux<size ; aux++){
            length=0;
            blackStones=0;
            whiteStones=0;
            for(column=size-1-aux , row=0 ; column<size ; column++ , row++){
                length++;
                switch(gomokuPosition[row][column]){
                    case 'b':
                        blackStones++;
                        stoneHistory[length]='b';
                        break;
                    case 'w':
                        whiteStones++;
                        stoneHistory[length]='w';
                        break;
                    default:
                        stoneHistory[length]='o';
                }
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
                        for(i=5 ; i >=0 ; i--){
                            nodeCoefficient[moveHistory[length-i].row][moveHistory[length-i].column]++;
                            if(blackStones == 0){
                                nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]=nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]+3*blackStones/3;
                            }
                            else{
                                nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]=nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]-3*whiteStones/3;
                            }
                        }
                    }
                }
            }
        }

        //diagonals below and parallel to the main board matrice diagonal
        for(aux=0 ; aux<size-1 ; aux++){
            length=0;
            blackStones=0;
            whiteStones=0;
            for(row=size-1-aux , column=0 ; row<=size-1 ; row++ , column++){
                length++;
                switch(gomokuPosition[row][column]){
                    case 'b':
                        blackStones++;
                        stoneHistory[length]='b';
                        break;
                    case 'w':
                        whiteStones++;
                        stoneHistory[length]='w';
                        break;
                    default:
                        stoneHistory[length]='o';
                }
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
                        for(i=5 ; i >=0 ; i--){
                            nodeCoefficient[moveHistory[length-i].row][moveHistory[length-i].column]++;
                            if(blackStones == 0){
                                nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]=nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]+3*blackStones/3;
                            }
                            else{
                                nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]=nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]-3*whiteStones/3;
                            }
                        }
                    }
                }
            }
        }

        //diagonals above , parallel to and including the secondary board matrice diagonal
        for(aux=0 ; aux<size ; aux++){
            length=0;
            blackStones=0;
            whiteStones=0;
            for(row=aux , column=0 ; row>=0 ; row-- , column++){
                length++;
                switch(gomokuPosition[row][column]){
                    case 'b':
                        blackStones++;
                        stoneHistory[length]='b';
                        break;
                    case 'w':
                        whiteStones++;
                        stoneHistory[length]='w';
                        break;
                    default:
                        stoneHistory[length]='o';
                }
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
                        for(i=5 ; i >=0 ; i--){
                            nodeCoefficient[moveHistory[length-i].row][moveHistory[length-i].column]++;
                            if(blackStones == 0){
                                nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]=nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]+3*blackStones/3;
                            }
                            else{
                                nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]=nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]-3*whiteStones/3;
                            }
                        }
                    }
                }
            }
        }

        //diagonals below and parallel to the secondary board matrice diagonal
        for(aux=0 ; aux<size-1 ; aux++){
            length=0;
            blackStones=0;
            whiteStones=0;
            for(column=size-1-aux , row=size-1 ; column<=size-1 ; column++ , row--){
                length++;
                switch(gomokuPosition[row][column]){
                    case 'b':
                        blackStones++;
                        stoneHistory[length]='b';
                        break;
                    case 'w':
                        whiteStones++;
                        stoneHistory[length]='w';
                        break;
                    default:
                        stoneHistory[length]='o';
                }
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
                        for(i=5 ; i >=0 ; i--){
                            nodeCoefficient[moveHistory[length-i].row][moveHistory[length-i].column]++;
                            if(blackStones == 0){
                                nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]=nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]+3*blackStones/3;
                            }
                            else{
                                nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]=nodeInfluence[moveHistory[length-i].row][moveHistory[length-i].column]-3*whiteStones/3;
                            }
                        }
                    }
                }
            }
        }
        
        for(row=0 ; row<15 ; row++){
            for(column=0 ; column < 15 ; column++){
                nodeInfluence[row][column]=nodeInfluence[row][column] * nodeCoefficient[row][column];
                if(nodeInfluence[row][column] != 0){
                    mList.add(new ValueMove(row , column , nodeInfluence[row][column]));
                }
            }
        }

        Collections.sort(mList, new ValueMoveComparator());
        if(mList.size() > 12){
            mList=mList.subList(0, 11);
        }
        if(mList.size() == 0){
            mList.add(new Move(7 , 7));
        }

        return mList;
    }
}
