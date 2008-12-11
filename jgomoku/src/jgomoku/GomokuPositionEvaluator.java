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

    int row , column;
    int length;
    int blackStones , whiteStones;

    int blackTwos;
    int whiteTwos;
    int blackThrees;
    int whiteThrees;
    int blackFours;
    int whiteFours;

    int i , aux;

    char[] stoneHistory=new char[20];
    Move[] moveHistory=new Move[20];

    char[][] gomokuPosition;

    private void inLineStuff(){
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
                        if(blackStones == 0){
                            positionValue-=3*whiteStones/3;
                            switch(whiteStones){
                                case 2:
                                    whiteTwos++;
                                    break;
                                case 3:
                                    whiteThrees++;
                                    break;
                                case 4:
                                    whiteFours++;
                                    break;
                            }
                        }
                        else{
                            positionValue+=3*blackStones/3;
                            switch(blackStones){
                                case 2:
                                    blackTwos++;
                                    break;
                                case 3:
                                    blackThrees++;
                                    break;
                                case 4:
                                    blackFours++;
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }

    public int getPositionValue(char[][] gomokuPosition){

        this.gomokuPosition=gomokuPosition;
        for(i=0 ; i<20 ; i++){
            moveHistory[i]=new Move();
        }

        blackTwos=0;
        whiteTwos=0;
        blackThrees=0;
        whiteThrees=0;
        blackFours=0;
        whiteFours=0;

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

        return blackFours*100+blackThrees*10+blackTwos-whiteFours*100-whiteThrees*10-whiteTwos;
    }
}