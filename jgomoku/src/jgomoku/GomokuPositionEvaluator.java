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

    public int getPositionValue(char[][] boardData){
        int row , column;

        this.boardData=boardData;

        this.checkHorizontalLine('b');
        this.checkVerticalLine('b');
        this.checkMainDiagonalLine('b');
        this.checkSecondaryDiagonalLine('b');

        this.checkHorizontalLine('w');
        this.checkVerticalLine('w');
        this.checkMainDiagonalLine('w');
        this.checkSecondaryDiagonalLine('w');

        return positionValue;
    }

    private void modifyValues(int length , char side){
        int val;

        switch(length){
            case 0:
                return;
            case 1:
                return;
            case 2:
                if(side == 'b')positionValue+=1;
                else positionValue-=1;
            case 3:
                if(side == 'b')positionValue+=3;
                else positionValue-=3;
            case 4:
                if(side == 'b')positionValue+=9;
                else positionValue-=9;
            case 5:
                if(side == 'b')positionValue+=27;
                else positionValue-=27;
            default:
                if(side == 'b')positionValue+=27 + (length - 5) * 10;
                else positionValue-=27 + (length - 5) * 10;
        }
    }

    private void checkHorizontalLine(char side){
        int row , column;
        int length=0;

        for(row=0 ; row<size ; row++){
            for(column=0 ; column<size ; column++){
                if(boardData[row][column] == side){
                    length++;
                }
                else{
                    modifyValues(length , side);
                    length=0;
                }
            }
            modifyValues(length , side);
            length=0;
        }
    }

    private void checkVerticalLine(char side){
        int row , column;
        int length=0;

        for(column=0 ; column<size ; column++){
            for(row=0 ; row<size ; row++){
                if(boardData[row][column] == side){
                    length++;
                }
                else{
                    modifyValues(length , side);
                    length=0;
                }
            }
            modifyValues(length , side);
            length=0;
        }
    }

    private void checkMainDiagonalLine(char side){
        int row , column;
        int length=0;
        int aux;

        //diagonals above , parallel to and including the main boardData matrice diagonal
        for(aux=0 ; aux<size ; aux++){
            for(column=size-1-aux , row=0 ; column<size ; column++ , row++){
                if(boardData[row][column] == side){
                    length++;
                }
                else{
                    modifyValues(length , side);
                    length=0;
                }
            }
            modifyValues(length , side);
            length=0;
        }

        //diagonals below and parallel to the main boardData matrice diagonal
        for(aux=0 ; aux<size-1 ; aux++){
            for(row=size-1-aux , column=0 ; row<=size-1 ; row++ , column++){
                if(boardData[row][column] == side){
                    length++;
                }
                else{
                    modifyValues(length , side);
                    length=0;
                }
            }
            modifyValues(length , side);
            length=0;
        }
    }

    private void checkSecondaryDiagonalLine(char side){
        int row , column;
        int length=0;
        int aux;

        //diagonals above , parallel to and including the secondary boardData matrice diagonal
        for(aux=0 ; aux<size ; aux++){
            for(row=aux , column=0 ; row>=0 ; row-- , column++){
                if(boardData[row][column] == side){
                    length++;
                }
                else{
                    modifyValues(length , side);
                    length=0;
                }
            }
            modifyValues(length , side);
            length=0;
        }

        //diagonals below and parallel to the secondary boardData matrice diagonal
        for(aux=0 ; aux<size-1 ; aux++){
            for(column=size-1-aux , row=size-1 ; column<=size-1 ; column++ , row--){
                if(boardData[row][column] == side){
                    length++;
                }
                else{
                    modifyValues(length , side);
                    length=0;
                }
            }
            modifyValues(length , side);
            length=0;
        }
    }
}
