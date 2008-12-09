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

public class GomokuPositionEvaluator {
    private int positionValue=0;
    private int size=15;
    private char[][] boardData;

    public int getPositionValue(char[][] gomokuPosition){
        int row , column;
        int length;
        int blackStones , whiteStones;

        int i , aux;

        char[] stoneHistory=new char[20];
        Move[] moveHistory=new Move[20];
        for(i=0 ; i<20 ; i++){
            moveHistory[i]=new Move();
        }

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
                        if(blackStones == 0){
                            positionValue-=(3*whiteStones/3)*(5-whiteStones);
                        }
                        else{
                            positionValue+=(3*blackStones/3)*(5-blackStones);
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
                        if(blackStones == 0){
                            positionValue-=(3*whiteStones/3)*(5-whiteStones);
                        }
                        else{
                            positionValue+=(3*blackStones/3)*(5-blackStones);
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
                        if(blackStones == 0){
                            positionValue-=(3*whiteStones/3)*(5-whiteStones);
                        }
                        else{
                            positionValue+=(3*blackStones/3)*(5-blackStones);
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
                        if(blackStones == 0){
                            positionValue-=(3*whiteStones/3)*(5-whiteStones);
                        }
                        else{
                            positionValue+=(3*blackStones/3)*(5-blackStones);
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
                        if(blackStones == 0){
                            positionValue-=(3*whiteStones/3)*(5-whiteStones);
                        }
                        else{
                            positionValue+=(3*blackStones/3)*(5-blackStones);
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
                        if(blackStones == 0){
                            positionValue-=(3*whiteStones/3)*(5-whiteStones);
                        }
                        else{
                            positionValue+=(3*blackStones/3)*(5-blackStones);
                        }
                    }
                }
            }
        }

        System.out.println(positionValue);
        return positionValue;
    }
}