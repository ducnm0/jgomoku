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
    private int LineOf2=0;
    private int LineOf3=0;
    private int LineOf4=0;
    private int LineOf5=0;

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

    private void checkHorizontalLine(char side){
        int row , column;
        int length=0;

        for(row=0 ; row<size ; row++){
            for(column=0 ; column<size ; column++){
                if(boardData[row][column] == side){
                    length++;
                }
                else{
                    length=0;
                }
            }
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
                    length=0;
                }
            }
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
                    length=0;
                }
            }
            length=0;
        }

        //diagonals below and parallel to the main boardData matrice diagonal
        for(aux=0 ; aux<size-1 ; aux++){
            for(row=size-1-aux , column=0 ; row<=size-1 ; row++ , column++){
                if(boardData[row][column] == side){
                    length++;
                }
                else{
                    length=0;
                }
            }
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
                    length=0;
                }
            }
            length=0;
        }

        //diagonals below and parallel to the secondary boardData matrice diagonal
        for(aux=0 ; aux<size-1 ; aux++){
            for(column=size-1-aux , row=size-1 ; column<=size-1 ; column++ , row--){
                if(boardData[row][column] == side){
                    length++;
                }
                else{
                    length=0;
                }
            }
            length=0;
        }
    }
}
